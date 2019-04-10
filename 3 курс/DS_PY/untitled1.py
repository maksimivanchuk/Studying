#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Mar 13 18:40:10 2019

@author: innasenuta
"""

def hello():
    print("Hello")
def fib(n):
    a = b = 1
    for i in range(n-2):
        a,b=b,a+b
    return b