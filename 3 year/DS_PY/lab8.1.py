#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri May 17 14:57:20 2019

@author: innasenuta
"""
import numpy as np
import tensorflow as tf
from keras.models import model_from_json
from keras.preprocessing import image

# Загружаем изображение
img_path = '5.png'
img = image.load_img(img_path, target_size=(28, 28), grayscale=True)

# Преобразуем изображением в массив numpy
x = image.img_to_array(img)

# Инвертируем и нормализуем изображение
x = 255 - x
x /= 255
x = np.expand_dims(x, axis=0)
json_file = open("mnist_model2.json", "r")
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)
loaded_model.load_weights("mnist_model2.h5")
loaded_model.compile(loss="categorical_crossentropy", optimizer="adam", metrics=["accuracy"])
prediction = loaded_model.predict(x)
print(np.argmax(prediction))
