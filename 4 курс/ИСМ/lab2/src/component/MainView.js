import React from 'react'

import raw_dataset from '../data/dataset.json'

function shuffle(a) {
    var j, x, i;
    for (i = a.length - 1; i > 0; i--) {
        j = Math.floor(Math.random() * (i + 1));
        x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
    return a;
}

class MainView extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            properties: [
                "tempo", "beats", "chroma_stft",
                "rmse", "spectral_centroid", "spectral_bandwidth"
            ],
            classes: ["Nokia", "Samsung", "Apple"],
            classFieldName: "label",
            testValue: {},
            realClass: "",
            guessedClasses: null
        }

    }

    metric = (xi, xj) => {
        let res = 0.0;
        Object.keys(xi).forEach(key => {
            if (this.state.properties.includes(key)) {
                res += Math.pow((xi[key] - xj[key]), 2)
            }
        })
        return Math.sqrt(res);
    }

    bi = (xj, kip) => {
        const proximities = kip.map(member => this.metric(xj, member));
        let proximitySum = 0.0;
        proximities.forEach(prox => proximitySum += prox);
        return Math.min(...proximities)  //proximitySum / kip.length 
    }
    b = (xj, KP) => {
        return KP.map(clazz => ({
            name: clazz.name,
            prox: this.bi(xj, clazz.members)
        }))
    }
    guessClass = (xt, KP) => {
        const proximities = this.b(xt, KP);
        const guessedClasses = proximities.filter(prox => {
            return prox.prox === Math.min(...proximities.map(p => p.prox));
        }).map(clazz => clazz.name);
        return {
            classes: guessedClasses,
            vector: proximities
        }
    }

    mapPropToLabel = prop => {
        switch (prop) {
            case "tempo":
                return "Год";
            case "beats":
                return "Мп камера";
            case "chroma_stft":
                return "Мб ОЗУ";
            case "rmse":
                return "GB память ";
            case "spectral_centroid":
                return "Количество кнопок";
            case "spectral_bandwidth":
                return "Bluetooth";
            case "rolloff":
                return "Спад амплитудно-частотной характеристики";
            case "zero_crossing_rate":
                return "Скорость изменения знака сигнала";

        }
    }

    mapPropToValueBounds = (prop, dataset) => {

        const bounds = {
            min: Math.min(...dataset.map(item => item[prop])),
            max: Math.max(...dataset.map(item => item[prop]))
        }

        return bounds;

    }

    onGuessButtonPress = (KP) => {
        let testValue = {};
        this.state.properties.forEach(prop => {
            testValue = {
                ...testValue,
                [prop]: document.getElementById(prop).value
            }
        })
        testValue = {
            ...testValue,
            [this.state.classFieldName]: document.getElementById('real-class').value
        }

        const guessResult = this.guessClass(testValue, KP);

        this.setState({
            realClass: testValue[this.state.classFieldName],
            guessedClasses: guessResult.classes,
            vector: guessResult.vector
        })



    }

    render() {
        {
            const dataset = raw_dataset.filter(item => {
                return this.state.classes.includes(item[this.state.classFieldName]);
            });

            shuffle(dataset);

            const N = dataset.length;
            const median = parseInt(N / 2);
            const XP = dataset.slice(0, median);
            const XT = dataset.slice(median + 1, N - 1);

            const XPClasses = [...new Set(XP.map(item => item.label))].sort();
            const XTClasses = [...new Set(XT.map(item => item.label))].sort();

            const KP = XPClasses.map(clazz => {
                const classMembers = XP.filter(member => member.label === clazz);
                return {
                    name: clazz,
                    members: classMembers
                };
            });

            const results = [];
            for (let i = 0; i < XT.length; i++) {
                results.push({
                    "Real class": XT[i].label,
                    "Guessed class": this.guessClass(XT[i], KP).classes
                })
            }

            let numberOfGuessedClass = 0;

            results.forEach(item => {
                if (item["Guessed class"] == item["Real class"]) {
                    numberOfGuessedClass++;
                }
            })

            let numberOfWrongClasses = XT.length - numberOfGuessedClass;

            return (
                <div className="container">
                 	<div className="row">
                 		 <div className="col-md-12">
							{
								"Accuracy: "  + (numberOfGuessedClass / XT.length) * 100
							}
                 		 </div> 
                 	</div>

                    <div className="row">
                        
                        <div className="col-md-12">
                            <div className="row">
                                <div className="col-md-6">
                                    <label for="real-class">Класс</label>
                                    <input id="real-class" className="form-control" defaultValue={XT[0][this.state.classFieldName]}></input>
                                    {
                                        this.state.properties.map(prop => (
                                            <div>
                                                <label for={prop}>{this.mapPropToLabel(prop)}</label>
                                                <input id={prop} className="form-control" name={prop}
                                                    min={this.mapPropToValueBounds(prop, dataset).min}
                                                    max={this.mapPropToValueBounds(prop, dataset).max}
                                                    placeholder={this.mapPropToLabel(prop)} defaultValue={XT[0][prop]}>
                                                </input>
                                            </div>
                                        ))
                                    }
                                    <button onClick={() => this.onGuessButtonPress(KP)} className="btn">Попытаться распознать</button>
                                </div>
                                <div className="col-md-6">
                                    {
                                        this.state.guessedClasses ?
                                            <div>
                                                <h1><strong>Результат:</strong></h1>
                                                <h2>Реальный класс: {this.state.realClass}</h2>
                                                <h2 className={
                                                    this.state.realClass == this.state.guessedClasses
                                                        ? "text-success"
                                                        : "text-failure"}>
                                                    Полученныe классы: {this.state.guessedClasses}
                                                </h2>
                                                <h3>Вектор расстояний: </h3>
                                                <ul>
                                                    {
                                                        this.state.vector.map(item => {
                                                            return (
                                                                <li>
                                                                    <p>Класс: {item.name}</p>
                                                                    <p>Метрика: {item.prox}</p>
                                                                </li>
                                                            )
                                                        })
                                                    }
                                                </ul>
                                            </div>
                                            : ""
                                    }
                                </div>
                            </div>

                            {/* <ul>
                                {
                                    results.map(item => {
                                        return (
                                            <li>
                                                <ul>
                                                    {
                                                        "Real: " + item["Real class"] + " --- Guessed: " + item["Guessed class"]
                                                    }
                                                </ul>
                                            </li>
                                        )
                                    })
                                }
                            </ul> */}
                        </div>
                    </div>
                </div>
            )
        }
    }
}

export default MainView;