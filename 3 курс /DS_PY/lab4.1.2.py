import numpy as np

def nonlin(x,deriv=False):
	if(deriv==True):
           return x*(1-x)

	return 1/(1+np.exp(-x))
    
X = np.array([  [0,1],
                [1,0],
                [0,0],
                [1,1]])
                
y = np.array([[1,1,0,0]]).T

np.random.seed(1)

# случайно инициализируем веса, в среднем - 0
syn0 = 2*np.random.random((2,5)) - 1
syn1 = 2*np.random.random((5,2)) - 1
syn2 = 2*np.random.random((2,1)) - 1

for j in range(60000):

	# проходим вперёд по слоям 0, 1 и 2
    l0 = X
    l1 = nonlin(np.dot(l0,syn0))
    l2 = nonlin(np.dot(l1,syn1))
    l3 = nonlin(np.dot(l2,syn2))

    # как сильно мы ошиблись относительно нужной величины?
    l3_error = y - l3
    
    if (j% 10000) == 0:
        print("Error:" + str(np.mean(np.abs(l3_error))))
        
    # в какую сторону нужно двигаться?
    # если мы были уверены в предсказании, то сильно менять его не надо
    l3_delta = l3_error*nonlin(l3,deriv=True)

    # как сильно значения l1 влияют на ошибки в l2?
    l2_error = l3_delta.dot(syn2.T)
    
    # в каком направлении нужно двигаться, чтобы прийти к l1?
    # если мы были уверены в предсказании, то сильно менять его не надо
    l2_delta = l2_error * nonlin(l2,deriv=True)
    
    l1_error = l2_delta.dot(syn1.T)
    l1_delta = l1_error * nonlin(l1,deriv=True)
    
    
    syn2 += l2.T.dot(l3_delta)
    syn1 += l1.T.dot(l2_delta)
    syn0 += l0.T.dot(l1_delta)