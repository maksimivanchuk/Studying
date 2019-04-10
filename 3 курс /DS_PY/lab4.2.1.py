import numpy as np

def nonlin(x, deriv=False):
    if (deriv == True):
        return (x * (1 - x))

    return 1 / (1 + np.exp(-x))


X = np.array([[1.9585, 1.9585, 1.9585, 1.9666],
              [1.9585, 1.9585, 1.9666, 1.9707],
              [1.9585, 1.9666, 1.9707, 1.9653],
              [1.9666, 1.9707, 1.9653, 1.9538],
              [1.9707, 1.9653, 1.9538, 1.9538],
              [1.9653, 1.9538, 1.9538, 1.9538],
              [1.9538, 1.9538, 1.9538, 1.9597],
              [1.9538, 1.9538, 1.9597, 1.9640],
              [1.9538, 1.9597, 1.9640, 1.9697]])
x_max = X.max()
y = np.array([[1.9707],
              [1.9653],
              [1.9538],
              [1.9538],
              [1.9538],
              [1.9597],
              [1.9640],
              [1.9697],
              [1.9542]])
y_max = y.max()
for i in range(9):
    for j in range(4):
        X[i][j]= X[i][j] / x_max
        
for i in range(9):
    for j in range(1):
         y[i][j]= y[i][j] / y_max

#  X = X / 10  # если Х, Y > 1 , плохой прогноз, т.к. сигмоида не больше 1 дает
#  y = y / 10

np.random.seed(1)

# случайно инициализируем веса, в среднем - 0
syn0 = 2 * np.random.random((4, 9)) - 1
syn1 = 2 * np.random.random((9, 1)) - 1

for j in range(10000):

    # проходим вперёд по слоям 0, 1 и 2
    l0 = X
    l11 = np.dot(l0, syn0)
    l1 = nonlin(l11)
    l22 = np.dot(l1, syn1)
    l2 = nonlin(l22)

    # как сильно мы ошиблись относительно нужной величины?
    l2_error = y - l2

    if (j % 1000) == 0:
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

# прогноз

X = np.array([[1.9597, 1.9640, 1.9697, 1.9542],
              [1.9585, 1.9585, 1.9585, 1.9666]
              ])
y = np.array([[1.9509],
              [1.9707]])
for i in range(2):
    for j in range(4):
        X[i][j]= X[i][j] / x_max
        
for i in range(2):
    for j in range(1):
        y[i][j]= y[i][j] / y_max
#  X = X / 10
#  y = y / 10

l0 = X
l11 = np.dot(l0, syn0)
l1 = nonlin(l11)
l22 = np.dot(l1, syn1)
l2 = nonlin(l22)

# как сильно мы ошиблись относительно нужной величины?
l2_error = y - l2
        
for i in range(2):
    for j in range(1):
        l2[i][j]= l2[i][j] *y_max
print('ошибка прогноза ', l2_error)
print('  прогноз ', l2)
