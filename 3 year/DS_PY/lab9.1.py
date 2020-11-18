import numpy as np
import tensorflow as tf 
from keras.applications.vgg16 import preprocess_input 
from keras.models import model_from_json
from keras.preprocessing import image
from keras.utils import CustomObjectScope
from keras.initializers import glorot_uniform

with CustomObjectScope({'GlorotUniform': glorot_uniform()}):
# Список классов
    classes = ['Cat', 'Dog']
# Загружаем обученную модель 
    json_file = open('vgg16_cat_dogs.json', 'r') 
    loaded_model_json = json_file.read() 
    json_file.close()
    loaded_model = model_from_json(loaded_model_json) 
    loaded_model.load_weights('vgg16_cat_dogs.h5')
# Компилируем модель
    loaded_model.compile(optimizer='rmsprop', loss='categorical_crossentropy', metrics=['accuracy'])
# Загружаем изображение для распознавания
    img = image.load_img('cat.jpeg', target_size=(150, 150))

    x = image.img_to_array(img)
    x = np.expand_dims(x, axis=0) 
    x = preprocess_input(x)
# Запускаем распознавание 
    prediction = loaded_model.predict(x) 
    print(classes[int(prediction[0][0])])