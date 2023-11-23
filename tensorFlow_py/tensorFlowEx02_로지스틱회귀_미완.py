import pandas as pd
import tensorflow as tf
import sk

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

X = list(zip(Sex, Age, Parch, Embarked))


# Weight W, bias b 초기값 랜덤 설정
# WX + b = Y 형태가 되어야 하므로, W는 7 x 1 행렬
W = tf.Variable(tf.constant(0.01,shape=[4,1]))

# bias는 1차원으로 설정
b = tf.Variable(0.0)

# placeholder의 대체 함수 만들기, 2진분류이므로 sigmoid 함수 사용
def logistic_regression(X):
    return tf.transpose(tf.nn.sigmoid(tf.matmul(X, W)) + b)

# 손실 함수 정의
def loss_function(y_predict, y_true):
    return -tf.reduce_mean(tf.math.multiply(y_true, tf.math.log(y_predict)) + tf.math.multiply((1-y_true), tf.math.log(1-y_predict)))

# 정확도 함수 정의
def accuracy(y_predict, y_true):
    predict = tf.cast(y_predict > 0.5, dtype=tf.float32)
    return tf.reduce_mean(tf.cast(tf.equal(predict, y_true), dtype=tf.float32))


temp = logistic_regression(X)
# print(temp)
# print('-----------------------------------')
# print('-----------------------------------')
# print(-tf.multiply(Y, tf.math.log(temp)))
# print('-----------------------------------')
# print('-----------------------------------')
# print(-tf.multiply((1-Y), tf.math.log(1-temp)))
# print('-----------------------------------')
# print('-----------------------------------')
print(-(tf.multiply(Y, tf.math.log(temp)) + tf.multiply((1-Y), tf.math.log(1-temp))))
# print(-tf.reduce_mean(tf.multiply(Y, tf.math.log(temp)) + tf.multiply((1-Y), tf.math.log(1-temp))))
# print('-----------------------------------')
# print('-----------------------------------')



# 최적화 함수. SGD(확률적 경사하강법) 채택, 속도 문제로 keras의 SGD 사용
optimizer = tf.keras.optimizers.legacy.SGD(learning_rate=0.01)
# optimizer = tf.optimizers.SGD(learning_rate=0.002)


def run_optimization():
    with tf.GradientTape() as g:
        pred = logistic_regression(X)
        loss = loss_function(pred, Y)
    gradients = g.gradient(loss,[W,b])
    # print(f'loss : {loss}')
    # print(f'gradients : {gradients}')
    # print(f'W : {W}')
    # print(f'b : {b}')
    # print(f'zip(gradients,[W,b]) : {list(zip(gradients,[W,b]))}')
    optimizer.apply_gradients(zip(gradients,[W,b]))

# for step in range(10001):
#     run_optimization()
#
#     if step%1000 == 0:
#         pred = logistic_regression(X)
#         loss = loss_function(pred, Y)
#         acc = accuracy(pred, Y)
#         print(f'step : {step}, loss : {loss}\n W : {W.value()}, b : {b.value()}, accuracy : {acc}')
#
# pred = logistic_regression(X)
# loss = loss_function(pred, Y)
# acc = accuracy(pred, Y)
# print(f'W : {W.value()}, b : {b.value()}, accuracy : {acc}')
#
