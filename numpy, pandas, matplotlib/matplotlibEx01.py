import matplotlib as mpl
import matplotlib.pyplot as plt
import numpy as np

plt.figure(1)    # 새로운 창 생성
plt.plot([1,2,3])

plt.figure(2)
plt.plot([1,4,9])

plt.figure(1)
plt.clf()   #clear figure
plt.plot([1,2,3], [1,2,3],[1,2,3], [1,8,27])
# 열거해서 한 번에 그리는 방법도 있음
# 혹은 plt.plot 을 연속해서 사용해도 한 그래프 안에 들어감

plt.subplot(2,2,1)
# 2행 2 열 중 1번째(우상단)
plt.plot([1,2,3])
plt.subplot(2,2,2)
plt.plot([1,4,9])
plt.subplot(2,2,3)
plt.plot([1,2,3], [1,2,3],[1,2,3], [1,8,27])
plt.subplot(2,2,4)
temp = np.arange(0,10,0.2)
plt.plot(temp, np.sin(np.sin(temp)))

plt.show()