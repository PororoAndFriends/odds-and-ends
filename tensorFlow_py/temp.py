import pandas as pd
import tensorflow as tf
import numpy as np
data = pd.read_csv('./train_preprocessed.csv')
# test = pd.read_csv('./test_preprocessed.csv')

Y = data['Survived'].copy()
data = data.drop(columns='Survived')

Pclass = data['Pclass']
Sex = data['Sex']
Age = data['Age']
SibSp = data['SibSp']
Parch = data['Parch']
Fare = data['Fare']
Embarked = data["Embarked"]

X = list(zip(Pclass, Sex, Age, SibSp, Parch, Fare, Embarked))


# x(입력), y(결과) 데이터
x_train = np.array([-50, -40, -30, -20, -10, -5, 0, 5, 10, 20, 30, 40, 50,
                    -15, 1, 3, -7, 2.5,
                    7, 77, 33, 52, 80])
y_train = np.array([0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1,
                    0, 0, 0, 0, 0,
                    1, 1, 1, 1, 1])

# keras의 다차원 계층 모델인 Sequential를 레이어를 만든다.
model = tf.keras.models.Sequential()

# 입력이 1차원이고 출력이 1차원임을 뜻함 - Dense는 레이어의 종류
model.add(tf.keras.layers.Dense(5, input_dim=1))
model.add(tf.keras.layers.Dense(3))
model.add(tf.keras.layers.Dense(1, activation='sigmoid'))

# 종합
model.summary()

# Optimizer: Stochastic gradient descent (확률적 경사 하강법)
sgd = tf.keras.optimizers.SGD(learning_rate=0.01)

# Loss function: binary_crossentropy (이진 교차 엔트로피)
model.compile(optimizer=sgd, loss='binary_crossentropy', metrics=['binary_accuracy'])

# 주어진 X와 y데이터에 대해서 오차를 최소화하는 작업을 200번 시도합니다.
hist = model.fit(x_train, y_train, batch_size=1, epochs=5, validation_split=0.2)

# 모델 테스트
print(model.predict([1, 2, 3, 4, 4.5]))
print(model.predict([11, 21, 31, 41, 500]))