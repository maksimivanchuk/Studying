//
//  main.cpp
//  Lab1
//
//  Created by Максим  on 12/15/18.
//  Copyright © 2018 Максим . All rights reserved.
//

#include <iostream>
#include <fstream>
#include <cmath>
using namespace std;


long int max_counter =1;
long int counter= 0;
long double y_0 =6.36704;//6.36704;
long double x_0= 2;
long double x_n= 12;
long double eps = 0.0000000001;


//  y(x)=(e^(2 x) (x^2 + 1) - 3 e^2 (x^2 - 1))/(e^(2 x) - 3 e^2)
//(e^(2*x)*(x^2 + 1) - 3*e^2*(x^2 - 1))/(e^(2*x) - 3*e^2)
long double func_true(long double x)
{
    return ((exp(2*x)*(pow(x,2) + 1) - 3*exp(2)*(pow(x,2) - 1))/(exp(2*x) - 3*exp(2))); //  y(x)=(e^(2 x) (x^2 + 1) - 3 e^2 (x^2 - 1))/(e^(2 x) - 3 e^2)
}

long double func(long double x,long double y)
{
    return (1+2*x+(2*pow(x,2)*y)-pow(x,4)-pow(y,2));//(y/x-(cos(y/x)/sin(y/x)));//(1+2*x+(2*pow(x,2)*y)-pow(x,4)-pow(y,2));
}

long double RK_4th(long double h,long double x,long double y)
{
    long double k1 = func(x,y);
    long double k2 = func(x+h/2,y+(h*k1)/2);
    long double k3 = func(x+h/2,y+(h*k2)/2);
    long double k4 = func(x+h,y+h*k3);
    return (y+(h/6)*(k1+2*k2+2*k3+k4));
}

long double func_MSI(long double h,long double x_new,long double y_some_new,long double constant)
{
    return (constant+(h/24)*(9*func(x_new,y_some_new)));
}

long double Constant_function(long double h,long double x2,long double y2,long double y1,long double y0)
{
    return  (y2+(h/24)*(19*func(x2,y2)-5*func(x2-h, y1)+func(x2-2*h, y0))); //(y_i+h/24*(19*f(xi,yi)-5*f(xi-1,yi-1)+f(xi-2,yi-2)))
}

long double MI(long double y_i, long double h, long double eps,long double x_new,long double constant)
{
    counter=1;
    long double y_new = func_MSI(h,x_new,y_i,constant); //constant = (y_i+h/24*(19*f(xi,yi)-5*f(xi-1,yi-1)+f(xi-2,yi-2)))
    long double y_prev=y_i;
    while (abs(y_new - y_prev) > eps)
    {
        y_prev=y_new;
        y_new = func_MSI(h,x_new,y_new,constant);
        counter++;
    }
    if(max_counter<counter)
        max_counter=counter;
    return y_new;
}

int main(int argc, const char * argv[]) {
    ofstream fout("output_h2.txt");
    ofstream fout1("output2.txt");
    long double h =0.001;
    long double y0=y_0;
    long double y1=RK_4th(h, x_0, y_0);
    long double y2=RK_4th(h, x_0+h, y1);
    
    fout.precision(11);
    fout1.precision(11);
    fout1.fixed;
    fout1<<"{{"<<x_0 <<", "<<fixed<< abs(y0-func_true(x_0))<<"}";
    fout1<<",{"<<(x_0+h)<<", "<<fixed<<abs(y1-func_true(x_0+h))<<"}";
    fout1<<",{"<<(x_0+2*h)<<", "<<fixed<<abs(y2-func_true(x_0+2*h))<<"}";
    fout<<"{{"<<x_0 <<", "<< y0<<"}";
    fout<<",{"<<(x_0+h)<<", "<<y1<<"}";
    fout<<",{"<<(x_0+2*h)<<", "<<y2<<"}";
    
    long double x_i=x_0+3*h;
    bool check = true;
    while(x_i<=x_n)
    {
        long double y_new=MI(y2,h,eps,x_i,Constant_function(h,x_i-h,y2,y1,y0));
        
        fout1<<",{"<<x_i<<", "<<fixed<<abs(y_new-func_true(x_i))<<"}";
        fout<<",{"<<x_i<<", "<<y_new<<"}";
        y0=y1;
        y1=y2;
        y2=y_new;
        
        if(x_i+h>x_n && check)
        {
            h=x_n-x_i;
            check=false;
        }
        x_i=x_i+h;
    }
    fout1<<"}"<<endl;
    fout<<"}"<<endl;
    fout1.close();
    fout.close();
    
    cout<<counter<<endl;
    
    return 0;
}
