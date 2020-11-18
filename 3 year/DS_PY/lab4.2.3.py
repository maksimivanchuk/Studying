#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Apr  3 17:49:30 2019

@author: innasenuta
"""

        
FIO = np.array([[1,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,1,1,0,1,0,0,1,0,1,0,1,0,1,0,0,1,0,1,1,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0],
       [1,0,0,0,0,0,1,1,1,0,0,0,1,1,1,0,1,0,1,0,1,1,0,0,1,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,1],
       [1,0,0,1,1,1,0,1,0,1,0,0,0,1,1,0,1,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,1,1,0,1,0,0,0,1,1,0,1,0,0,0,1,1,0,0,1,1,1,0]])
y = np.array([[1, 0, 0],
              [0, 1, 0],
              [0, 0, 1]])
    
np.random.seed(1)

# случайно инициализируем веса, в среднем - 0
syn0 = 2 * np.random.random((56, 32)) - 1
syn1 = 2 * np.random.random((32, 32)) - 1
syn2 = 2 * np.random.random((32, 3)) - 1

for j in range(100000):

    # проходим вперёд по слоям 0, 1 и 2
    l0 = FIO
    l11 = np.dot(l0, syn0)
    l1 = nonlin(l11)
    l22 = np.dot(l1, syn1)
    l2 = nonlin(l22)
    l33 = np.dot(l2, syn2)
    l3 = nonlin(l33)

    # как сильно мы ошиблись относительно нужной величины?
    l3_error = y - l3

    if (j % 10000) == 0:
        print ("Обучение Error: " + str(np.mean(np.abs(l3_error))))

    l3_delta = (l3_error) * nonlin(l3, deriv=True)
    l2_error = np.dot(l3_delta, syn2.T)
    l2_delta = l2_error * nonlin(l2, deriv=True)
    l1_error = np.dot(l2_delta, syn1.T)
    l1_delta = l1_error * nonlin(l2, deriv=True)

    syn2 += l2.T.dot(l3_delta)
    syn1 += l1.T.dot(l2_delta)
    syn0 += l0.T.dot(l1_delta)

print('прогноз: ', l3)