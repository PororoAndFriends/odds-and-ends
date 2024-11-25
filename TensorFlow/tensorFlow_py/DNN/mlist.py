import keras
import tensorflow as tf
import matplotlib.pyplot as plt
import numpy as np
from keras.utils import to_categorical as to_cat

mnist = tf.keras.datasets.mnist
(x_train, y_train), (x_test, y_test) = mnist.load_data()

## 데이터값 시각화
# plt.figure()
# plt.imshow(x_train[0], cmap=plt.cm.binary)
# plt.colorbar()
# plt.show()


# 데이터 max-min 스케일링, 이후 cross-entropy에서 오버플로우 방지
# 밝기는 0~255의 값을 가짐
x_train = x_train / 255.
x_test = x_test / 255.

# One-Hot 인코딩
# label의 값은 0~9의 값을 가짐
y_train_categorical = to_cat(y_train, 10)
y_test_categorical = to_cat(y_test, 10)


# 모델 생성
model = keras.Sequential()
model.add(keras.layers.Flatten(input_shape=(28, 28)))
model.add(keras.layers.Dense(units=100, activation='relu'))
model.add(keras.layers.Dense(units=10, activation='softmax'))


# 모델 컴파일
# metrics : 평가지표
# sparse_categorical_accuracy는 ordinal 인코딩, 그냥 categorical_accuracy는 one-hot 인코딩
model.compile(optimizer=keras.optimizers.legacy.Adam(learning_rate=0.001),
              loss=keras.losses.categorical_crossentropy,metrics=keras.metrics.categorical_accuracy)


# 모델 학습
model.fit(x_train, y_train_categorical, epochs=100, batch_size=100)


# 정확도 검증
#verbose 가 1이면 loss 반환, 2이면 loss와 accuacy 반환
test_loss, test_acc = model.evaluate(x_test,  y_test_categorical, verbose=2)

print(f'\nTest accuracy: {test_acc}')