import tensorflow as tf
import numpy as np

x = np.array([[0,0], [1,0], [0, 1], [1,1]], dtype=np.float32)
y = np.array([[0], [1], [1], [1]], dtype=np.float32)


W = tf.Variable(tf.random.normal([2, 1]))
b = tf.Variable(tf.random.normal([1]))

def h(x):
    return tf.sigmoid(tf.matmul(x, W) - b)

def loss(y, h):
    return -tf.reduce_mean(y*tf.math.log(h) + (1-y) * tf.math.log(1 - h))


optimizer = tf.keras.optimizers.legacy.SGD(learning_rate=0.1)


def run():
    with tf.GradientTape() as g:
        pred = h(x)
        cost = loss(y , pred)
    gradients = g.gradient(cost, [W,b])
    optimizer.apply_gradients(zip(gradients, [W, b]))


for step in range(10001):
    run()
    pred = tf.cast(h(x) > 0.3, dtype=tf.float32)
    acc = tf.reduce_mean(tf.cast(tf.equal(pred, y), dtype=tf.float32))
    if step % 1000 == 0:
        print(f'step : {step}, accuracy : {acc}')
        print(f'W : {W.value()}, b : {b.value()}')

print(f'W : {W.value()}, b : {b.value()}')
