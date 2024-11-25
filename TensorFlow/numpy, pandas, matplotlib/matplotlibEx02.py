import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(0, 10, 100)
y1 = x
y2 = x+2
y3 = x+4
y4 = x+6

plt.plot(x,y1,'--y',x,y2,'>--m',x,y3,'o--k',x,y4,'.c')
# k : black
# - : 실선, -- : 파선,  :(콜론) : 점선, -. : 파선 점선 혼합선
plt.xlabel('x')
plt.ylabel('y')
# x,y 범위 제한
plt.xlim(0, 2)
plt.ylim(0,10)
plt.title('Graph')
plt.grid(True)
plt.legend(['data1', 'data2', 'data3', 'data4'], loc='lower right')
# loc 옵션을 설정하지 않으면 최적의 위치에 자동으로 설정됨

plt.text(0,0, 'origin')
# 원하는 점에 텍스트 지정 가능

plt.show()