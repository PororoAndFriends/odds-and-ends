import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

x = np.linspace(0,10, 100)


y1 = np.sin(x)
y2 = np.cos(x)


plt.subplot(2,1,1)
plt.plot(x,  y1)
plt.subplot(2,1,2)
plt.plot(x,y2)

plt.show()
