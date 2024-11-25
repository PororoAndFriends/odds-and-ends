import tensorflow as tf
import numpy as np

x = np.array([[0,0], [1,0], [0, 1], [1,1]], dtype=np.float32)
y = np.array([[1], [1], [1], [0]], dtype=np.float32)


model = tf.keras.Sequential()
model.add(tf.keras.layers.Dense(units=2, activation=tf.keras.activations.sigmoid, input_shape=(2,)))
model.add(tf.keras.layers.Dense(units=1, activation=tf.keras.activations.sigmoid))

model.compile(optimizer=tf.keras.optimizers.SGD(learning_rate=0.1), loss='binary_crossentropy', metrics=tf.metrics.binary_accuracy)

history = model.fit(x,y, epochs=3000, batch_size=1)

hidden_1 = model.layers[0]
W1, b1 = hidden_1.get_weights()
hidden_2 = model.layers[1]
W2, b2 = hidden_2.get_weights()

test_loss, test_acc = model.evaluate(x,  y, verbose=2)
print(f'loss : {test_loss}, acc : {test_acc}')

# print(f'W1 : {W1}, b1 : {b1}')
# print(f'W2 : {W2}, b2 : {b2}')

