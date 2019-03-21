//
//  main.cpp
//  Lab2
//
//  Created by Максим  on 12/16/18.
//  Copyright © 2018 Максим . All rights reserved.
//

#include <iostream>
#include <cmath>
#include <fstream>
using namespace std;

long double x_n=4;
long double eps = 0.0000000001;

/*
 y(x) = tan(x - log(e^x + 1) + log(2))
 y(x) = tg(x - ln(e^x + 1) + ln(2))
 */
long double initial_func(long double x)
{
    return tan(x - log(exp(x) + 1) + log(2));
}

long double func(long double x,long double y)
{
    return ((pow(y,2)+1)/((exp(x)+1)));
}

void predictor_corrector(long double x0,long double y0,long double h,long double &predictor,long double &corrector)
{
    long double y1=y0+h*func(x0,y0)/2;
    long double y2=y0+h*(2*func(x0,y0)/3+func(x0+h/2, y1)/3)/3;
    long double y3=y0+h*(func(x0,y0)+3*func(x0+h/3, y2))/8;
    predictor=y0+h*(func(x0,y0)-(3*func(x0+h/3, y2))+4*func(x0+h/2, y3))/2;
    corrector=y0+h*(func(x0,y0)+(4*func(x0+h/2, y3))+func(x0+h, predictor))/6;
}

long double optimal_step( long double h, long double predictor, long double corrector)
{
    return pow(((eps*pow(h,4))/abs(corrector-predictor)),0.25);
}

int main(int argc, const char * argv[]) {
    ofstream fout("output.txt");
    ofstream fout1("output1.txt");
    ofstream fout2("output2.txt");
    long double h =0.00001;
    int iterations = 0;
    long double x_i=0;
    long double y_i = 0;
    long double ster=h;
    long double pre = 0;
    long double cor = 0;

    fout.precision(11);
    fout1.precision(11);
    fout2.precision(11);
    
    fout<<"{{"<<x_i <<", "<< y_i<<"}";
    fout1<<"{{"<<iterations<<", "<< h<<"}";
    fout2<<"{{"<<x_i<<", "<< fixed<<abs(initial_func(x_i)-y_i)<<"}";
    while (x_i!=x_n)
    {
        if ((x_i+h)>=x_n)
            h=x_n-x_i;
        
        predictor_corrector(x_i,y_i, h, pre, cor);
        x_i+=h;
        fout2<<",{"<<x_i <<", " <<fixed<<abs(initial_func(x_i)-cor)<<"}";
        h=optimal_step(h,pre,cor);
        y_i=cor;
        iterations++;
        fout<<",{"<<x_i <<", " << cor<<"}";
        fout1<<",{"<<iterations <<", " << h<<"}";
        
    }
    fout<<"}";
    fout1<<"}";
    fout2<<"}";
    return 0;
}
