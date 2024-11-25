import numpy as np
import pandas as pd

index = ['a','b','c', 'd']
a = pd.Series([1,2,3, np.nan], index=index)
print(a)
print(a.index)
print(a.values)
print('---------------')
dic = {'a' : 0, 'b':1,'c':2}
b = pd.Series(dic)
print(b)
print('---------------')
# temp = pd.date_range(start='2020-01-01', end='2020-01-03')
# temp = pd.date_range(start='2020/0101', end='2020-01-03')
# temp = pd.date_range(start='2020-01-01', periods=3)
temp = pd.date_range(start='2020-01-01', periods=3, freq='W')
c = pd.Series([1,2,3], index=temp)
print(c)
print('---------------')
dic = {'a':[0,1,2], 'b':['kor', 'jap', 'US'], 'c':[100, 20, 80]}
d = pd.DataFrame(dic, index=pd.date_range('2020/01/01', periods=3))
print(d)
print('---------------')
e = pd.DataFrame(np.arange(1,10).reshape(3,3))
f = pd.DataFrame(np.arange(4,0,-1).reshape(2,2))
print(e+f)
print('---------------')
print(e/f)
print('---------------')
e['sum'] = e.sum(axis=1)
print(e)
print('---------------')
g = pd.DataFrame(np.arange(0,60).reshape(10,-1))
print(g.head())
print('---------------')
print(g.tail(3))
print('---------------')
# 열 지정 : 변수[열][행범위]
print(g[1][1:3])
print('---------------')
# 전치행렬
print(g.T)
print('---------------')
h = pd.DataFrame(np.arange(0,4).reshape(2,2))
i = pd.DataFrame(np.arange(4,8).reshape(2,2))

# 세로 병합 : append
print(h._append(i, ignore_index=True))
# 가로 병합 : join
print(h.join(i))

# 키값을 기준으로 병합 : merge
# 키설정 : 옵션 on=키어트리뷰터, 없을 시 공통 열이름을 기준을 병합

