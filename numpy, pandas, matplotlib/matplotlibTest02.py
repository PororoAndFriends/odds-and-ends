import matplotlib.pyplot as plt

genre = ['romance', 'comedy', 'action', 'fantasy', 'thriller']
data = [10, 6, 4, 12, 8]

plt.pie(data, labels=genre, autopct='%.1f%%')

plt.show()