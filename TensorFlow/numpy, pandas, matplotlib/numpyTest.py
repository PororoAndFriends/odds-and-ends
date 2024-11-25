import numpy as np

x = np.random.rand(5,6)
x = x.round(3) * 100
print(f'max : {x.max()}')
print(f'min : {x.min()}')
print(f'var : {x.var()}')
print(f'std : {x.std()}')
print(f'mean : {x.mean()}')

print('----------')

y = np.arange(0, 15).reshape(3, -1)
print(y)
print(y[1,2])
print(y[2,4])
print(y[1,1:3])
print(y[[1,2],[2,2]])
print(y[0:2, 3:5])

print('----------')

A = []
B = []
while(len(A) < 10):
    temp = np.random.randint(1, 30)
    if temp not in A:
        A.append(temp)

while(len(B) < 10):
    temp = np.random.randint(1, 30)
    if temp not in B:
        B.append(temp)
print(A)
print(B)

print(set(A) & set(B))

