import numpy as np

a = np.array([1,2,3,4,5,6,7,8,9,10])
a = a.reshape(5,-1)
print(a)
print('----------')
b = [1,2,3]
print(b)
print('----------')
c = np.zeros((3,1))
print(c)
print('----------')
d = np.eye(4)
print(d)
print('----------')
e = np.ones((3,3))
print(e)
print('----------')
f = a.astype(float)
print(f)
print('----------')
g = f.astype(int)
print(g)
print('----------')
h = np.random.rand(3,4)
print(h)
print('----------')
i = np.random.randint(10, size=(4,4))
print(i)
print('----------')
j = np.random.randint(1,10)
print(j)
print('----------')
print(i>3)
print('----------')
print(f'sum : {a.sum()}')
print(f'mean : {a.mean()}')
print(f'var : {a.var()}')
print(f'std : {a.std()}')
print(f'max : {a.max()}, min : {a.min(())}')
print(f'누적합 : {a.cumsum()}')
print(f'누적곱 : {a.cumprod()}')
print('----------')
k = np.arange(1, 5).reshape(2,2)
l = np.arange(1,5).reshape(2,2)
print(f'dot : {k.dot(l)}')
print(f'trans : {k.transpose()}')
print(f'inv : {np.linalg.inv(k)}')
print(f'det : {np.linalg.det(k)}')
print(k.shape)
print('----------')
print(k[[0,1],[0,1]])
print(k[k>1])
print('----------')
m = np.arange(1,10).reshape(3,3)
print(m[0,0:3])










