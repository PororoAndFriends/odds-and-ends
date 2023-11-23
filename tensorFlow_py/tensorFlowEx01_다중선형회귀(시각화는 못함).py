import tensorflow as tf

# X : n x 3 행렬의 다중선형회귀
X = [[73., 80., 75.], [93., 88., 93.], [89., 91., 90.], [96., 98., 100.], [73., 66., 70.], [80., 71., 98.]]

# Y : 종속변수. n x 1 행렬
Y = [[76.], [92.5], [90.], [98.], [71.], [83.]]

# Weight W, bias b 초기값 랜덤 설정
# WX + b =y Y 형태가 되어야 하므로, W는 3 x 1 행렬
W = tf.Variable(tf.random.normal([3, 1]), name='weight')

# bias는 1차원으로 설정
b = tf.Variable(tf.random.normal([1]), name='bias')

# placeholder의 대체 함수 만들기
def linear_regression(X):
    return tf.matmul(X,W) + b

# 손실함수 정의(최소제곱법 사용)
def loss_function(y_predict, y_true):
    return tf.reduce_mean(tf.square(y_predict - y_true))

# 최적화 함수. SGD(확률적 경사하강법) 채택
optimizer = tf.optimizers.SGD(learning_rate=0.00003)

def run_optimization():
    with tf.GradientTape() as g:
        pred = linear_regression(X)
        loss = loss_function(pred, Y)
    gradients = g.gradient(loss,[W,b])
    # print(f'loss : {loss}')
    # print(f'gradients : {gradients}')
    # print(f'W : {W}')
    # print(f'b : {b}')
    # print(f'zip(gradients,[W,b]) : {list(zip(gradients,[W,b]))}')
    optimizer.apply_gradients(zip(gradients,[W,b]))

for step in range(10001):
    run_optimization()

    if step%1000 == 0:
        pred = linear_regression(X)
        loss = loss_function(pred, Y)
        print(f'step : {step}, loss : {loss}, W : {W.value()}, b : {b}')

print(f'W : {W.value()}, b : {b.value()}')
