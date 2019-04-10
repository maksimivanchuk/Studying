#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Apr  3 17:27:10 2019

@author: innasenuta
"""

x_train = np.array([[0.645, 33, 4, 0.34],
              [2.880, 152, 14, 1.5],
              [1.110, 88, 5, 0.475],
              [10.400, 260, 3, 6],
              [2.880, 152, 3, 1.5],
              [9.895, 122, 30, 6.2],
              [3.695, 136, 3, 3],
              [2.170, 116, 5, 0.6],
              [27.895, 278, 150, 11]])

x_test = np.array([[0.645, 33, 4, 0.34],
              [2.880, 152, 14, 1.5],
              [0.2, 1, 4, 0.2]])


mean1 = x_test.mean(axis=0)
std1 = x_test.std(axis=0)

mean = x_train.mean(axis=0)
std = x_train.std(axis=0)

x_train -= mean
x_train /= std

x_test -= mean1
x_test /= std1

# 0 - легковой, 1 - пассажирский, 2 - грузовой
y = np.array([[1, 0, 0],
              [0, 1, 0],
              [1, 0, 0],
              [0, 0, 1],
              [0, 0, 1],
              [0, 1, 0],
              [0, 0, 1],
              [1, 0, 0],
              [0, 1, 1]])

np.random.seed(1)

# случайно инициализируем веса, в среднем - 0
syn0 = 2 * np.random.random((4, 3)) - 1
syn1 = 2 * np.random.random((3, 3)) - 1


for j in range(100000):

    # проходим вперёд по слоям 0, 1 и 2
    l0 = x_train
    l11 = np.dot(l0, syn0)
    l1 = nonlin(l11)
    l22 = np.dot(l1, syn1)
    l2 = nonlin(l22)

    # как сильно мы ошиблись относительно нужной величины?
    l2_error = y - l2

    if (j % 10000) == 0:
        print ("Обучение Error: " + str(np.mean(np.abs(l2_error))))

    # в какую сторону нужно двигаться?
    # если мы были уверены в предсказании, то сильно менять его не надо
    l2_delta = (l2_error) * nonlin(l2, deriv=True)

    # как сильно значения l1 влияют на ошибки в l2?
    l1_error = np.dot(l2_delta, syn1.T)

    # в каком направлении нужно двигаться, чтобы прийти к l1?
    # если мы были уверены в предсказании, то сильно менять его не надо
    l1_delta = l1_error * nonlin(l1, deriv=True)

    syn1 += l1.T.dot(l2_delta)
    syn0 += l0.T.dot(l1_delta)
    
l0 = x_test
l11 = np.dot(l0, syn0)
l1 = nonlin(l11)
l22 = np.dot(l1, syn1)
l2 = nonlin(l22)
print('прогноз: ', l2)