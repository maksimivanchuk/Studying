//
//  main.cpp
//  Lab3
//
//  Created by Максим  on 12/20/18.
//  Copyright © 2018 Максим . All rights reserved.
//

/*
 k1=f(xn,yn+k1/6-k3/6);
 k2=f(xn+h/2,yn+1/12*k1+5/12*k2);
 k3=f(xn+h,yn+k1/2+k2/3+k3/6);
 yn+1= yn+h/6*(k1+4*k2+k3);
 */
#include <iostream>
#include <math.h>
#include <stdlib.h>
#include <fstream>
using namespace std;

const int n = 100001;// when h=10^-5
double matrix[n][n]={};
double x[n]={};

//y(x) = 2 x - 9.23231 e^(0.292893 x) - 0.0399225 e^(1.70711 x) + 8
//2*x - 9.23231*e^(0.292893*x) - 0.0399225*e^(1.70711*x) + 8
long double initial_func(long double x)
{
    return (2*x - 9.23231*exp(0.292893*x) - 0.0399225*exp(1.70711*x) + 8);
}
void gausss()
{
     for (int i=0; i<n; i++)
     {
         for (int k=i+1; k<n; k++)
         {
            long double buf = -matrix[k][i]/matrix[i][i];
             for (int j=i; j<n; j++)
             {
                 if (i==j)
                 {
                     matrix[k][j]=0;
                 }
                 else
                 {
                     matrix[k][j] = matrix[k][j]+ buf * matrix[i][j];
                 }
             }
             x[k]=x[k]+buf*x[i];
         }
     }
}
void gausss_reverse()
{
    x[n-1]=x[n-1]/matrix[n-1][n-1];
   matrix[n-1][n-1]=1;
    for (int i=n-2;i>=0;i--) {
       long double buf=0;
        for (int j=i+1;j<=n-1;j++)
            buf=buf+matrix[i][j]*x[j];
        x[i]=(x[i]-buf)/matrix[i][i];
    }
}
//y(x0)*((2*(pow(h,2)-1))/(h+pow(h,2))-2)+y(x1)*(2/(h+pow(h,2)))=1
void initial_boundary_condition(long double h,long double &coeff_y_0,long double &coeff_y_1)
{
    coeff_y_0=((2*(pow(h,2)-1))/(h+pow(h,2))-2);
    coeff_y_1=(2/(h+pow(h,2)));
}
//y(xn-1)(1/(pow(h,2)-h))+y(xn)((pow(h,2)/4-1)/(pow(h,2)-h))=-2+((pow(h,2)/2)/(pow(h,2)-h))
void final_boundary_condition(long double h,long double &coeff_y_n_prev,long double &coeff_y_n)
{
    coeff_y_n_prev=(1/(pow(h,2)-h));
    coeff_y_n=((pow(h,2)/4-1)/(pow(h,2)-h));
}
//y(xk-1)(1/pow(h,2)+1/h)+y(xk)(0.5-2/pow(h,2))+y(xk+1)(1/pow(h,2)-1/h)=xk
void  other_coefficient(long double h,long double &coeff_y_k_prev,long double &coeff_y_k,long double &coeff_y_k_next)
{
    coeff_y_k_prev=(1/pow(h,2)+1/h);
    coeff_y_k=(0.5-2/pow(h,2));
    coeff_y_k_next=(1/pow(h,2)-1/h);
}

int main()
{
    ofstream fout("output.txt");
    long double h = 0.00001;
    long double coeff_y_0 = 0;
    long double coeff_y_1 = 0;
    long double coeff_y_k_prev = 0;
    long double coeff_y_k = 0;
    long double coeff_y_k_next = 0;
    long double coeff_y_n_prev = 0;
    long double coeff_y_n = 0;
    
    initial_boundary_condition(h,coeff_y_0,coeff_y_1);
    final_boundary_condition(h,coeff_y_n_prev,coeff_y_n);
    other_coefficient(h,coeff_y_k_prev,coeff_y_k,coeff_y_k_next);
    
    matrix[0][0]=coeff_y_0;
    matrix[0][1]=coeff_y_1;
    matrix[n-1][n-2]=coeff_y_n_prev;
    matrix[n-1][n-1]=coeff_y_n;
    x[0]=1;
    x[n-1]=-2+(pow(h,2)/2)/(pow(h,2)-h);
    
    for (int i=1; i<n-1; i++){
        for (int j=0; j<n; j++)
        {
            if (i-1==j)
                matrix[i][j]=coeff_y_k_prev;
            if (i==j)
                matrix[i][j]=coeff_y_k;
            if (i+1==j)
                matrix[i][j]=coeff_y_k_next;
        }
        x[i]=h*i;
    }
    gausss();
    gausss_reverse();
    fout.precision(12);
    fout<<"{";
    for (int i = 0; i<n; i++){
        fout<<"{"<< double(i)/double(n)<<","<<x[i]<<"},";
        fout << endl;
    }
    fout.close();
    return 0;
}
