#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri May 17 14:57:20 2019

@author: innasenuta
"""

from keras.applications.vgg16 import VGG16
from keras.preprocessing import image
from keras.applications.vgg16 import preprocess_input, decode_predictions
import numpy as np
model = VGG16(weights='imagenet')
img = image.load_img('cat.jpeg', target_size=(224, 224))
x = image.img_to_array(img)
x = np.expand_dims(x, axis=0)
x = preprocess_input(x)
preds = model.predict(x)
print('Результаты распознавания:', decode_predictions(preds, top=3)[0])
