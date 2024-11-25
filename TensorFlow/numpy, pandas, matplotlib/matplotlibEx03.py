import matplotlib.pyplot as plt

height = [178, 165, 188, 160, 187, 185, 165, 176]
weight = [72, 67, 65, 64, 90, 85, 53, 64]

# 산점도 그래프
plt.figure("Scatter Graph")
plt.scatter(height, weight, s = 200, c = 'b')
# s : 마커 사이즈, c : 마커 색
plt.xlabel('height')
plt.ylabel('weight')
plt.grid(True)


# 막대 그래프
plt.figure('bar Graph')
plt.subplot(1,2,1)
# 세로 막대
plt.bar(height, weight, width=0.5)
plt.title('row')
plt.subplot(1,2,2)
# 가로 막대
plt.barh(height, weight)
plt.title('col')


# 히스토그램
plt.figure('Histogram')
temp = [78, 84, 12, 100, 56, 45, 89, 56, 64, 31, 18, 65, 4, 34, 78, 46, 84, 89, 45, 95, 97, 95]
plt.hist(temp, bins=5)
# bins : 범위의 갯수
plt.grid()


# 파이 그래프
plt.figure('Pie Graph')
temp = [6, 7, 4, 2, 1]
explode_value = [0.1,0.1,0,0,0]
fruit = ['apple', 'banana', 'strawberry', 'orange', 'grape']
plt.pie(temp, labels=fruit, autopct='%.1f%%', startangle=90, explode=explode_value, shadow=True)
# autopct : 오토 퍼센티지

plt.show()


# plt.savefig(filename)         옵션 dpi : 기본 72