#include <iostream>
#include <ctime>
#include <windows.h>
#include <chrono>
#include <random>
#include "omp.h"
#include "stdio.h"

using namespace std;

long n1, n2, n3;
long **A, **B;
int **C;

int **C1;

void lineMultiply()
{
	//#pragma omp parallel for
	for (long i = 0; i < n1; i++)
	{
		//#pragma omp parallel for
		for (long j = 0; j < n3; j++)
		{
			for (long k = 0; k < n2; k++)
				C[i][j] += A[i][k] * B[k][j];

		}
	}
}

void blockMul(long r, long q1, long q2, long q3)
{
	int x;
	int y;
	int z;

	//#pragma omp parallel for
	for (long i = 0; i < q1; i++)
	{
		x = i * r;
		//#pragma omp parallel for
		for (long j = 0; j < q3; j++)
		{
			y = j * r;
			for (long k = 0; k < q2; k++)	//	10-50 step 5 как сделать тестые
			{
				z = k * r;
				for (long m = 0; m < r; m++)
				{
					for (long n = 0; n < r; n++)
					{
						for (long p = 0; p < r; p++)
						{
							C[m + x][n + y] += A[m + x][p + z] * B[p + z][n + y];
						}
					}
				}
			}
		}

	}
}

void main()
{
	cout << "Enter n1 ";
	cin >> n1;
	cout << "Enter n2 ";
	cin >> n2;
	cout << "Enter n3 ";
	cin >> n3;
	int block;
	cout << "Enter block ";
	cin >> block;
	A = new long *[n1];
	B = new long *[n2];
	C = new int *[n1];
	C1 = new int *[n1];


	for (long i = 0; i < n1; i++)
	{
		A[i] = new long[n2];	
		for (long j = 0; j < n2; j++)
		{
			A[i][j] = (rand() % 200) - 100;
		}
	}
	for (long i = 0; i < n2; i++)
	{
		B[i] = new long[n3];
		for (long j = 0; j < n3; j++)
		{
			B[i][j] = (rand() % 200) - 100;
		}
	}
	for (long i = 0; i < n1; i++)
	{
		C[i] = new int[n3];
		C1[i] = new int[n3];
		for (long j = 0; j < n3; j++)
		{
			C[i][j] = 0;
			C1[i][j] = 0;

		}
	}

	chrono::high_resolution_clock::time_point start, finish;
	start = chrono::high_resolution_clock::now();
	lineMultiply();
	C1 = C;
	finish = chrono::high_resolution_clock::now();
	chrono::duration<double> time_elapsed =
		chrono::duration_cast<chrono::duration<double>>(finish - start);
	cout << endl << "line myltiply = " << time_elapsed.count() << endl;

	start = chrono::high_resolution_clock::now();
	blockMul(block, n1 / block, n2 / block, n3 / block);
	finish = chrono::high_resolution_clock::now();
	chrono::duration<double> time_elapsed5 =
		chrono::duration_cast<chrono::duration<double>>(finish - start);
	cout << endl << "block myltiply = " << time_elapsed5.count() << endl;

	/*for (long i = 0; i < n1; i++)
	{
		cout << endl;
			for (long j = 0; j < n2; j++)
			{
				cout << " " << A[i][j];
			}
	}
	for (long i = 0; i < n2; i++)
	{
		cout << endl;
			for (long j = 0; j < n3; j++)
			{
				cout << " " << B[i][j];
			}
	}*/
	for (long i = 0; i < n1; i++)
	{
	
		for (long j = 0; j < n3; j++)
		{
			if(C1[i][j]!=C[i][j])
				cout << "Some problems: " << C[i][j]<<" " << C1[i][j];
		}
	}
	system("pause");
}