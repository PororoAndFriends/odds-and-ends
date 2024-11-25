import pandas as pd
import numpy as np

a = np.arange(10)

a += 1

b = np.arange(10)
c = np.arange(11)



# 리스트끼리의 연산은 인덱스별 연산
print(a-b)
print('-----------------')
print(a*b)
print('-----------------')
# set끼리의 연산은 합, 교집합
print(set(a) - set(b))
