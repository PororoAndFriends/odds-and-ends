import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

a = pd.Series(np.linspace(-5, 5, 100))
b = a**2+1

a.plot()

plt.figure()

b.plot.pie()

temp = {'a' : a, 'b' : b}
df = pd.DataFrame(temp, columns=['a', 'b'])
df.plot.scatter(x='a', y = 'b')

plt.show()