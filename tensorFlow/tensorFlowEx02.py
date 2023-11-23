# Tensor : Rank, Shape, Type 3요소로 구성
    # Rank : 데이터의 차원
        #   e.g. Scalar : Rank0
        #       Vector : Rank1
        #      Matrix : Rank2
        #       3-Tensor : Rank3
        #      n-Tensor : Rankn

    # Shape : Tensor가 몇개의 행열을 갖는지 의미
        #   e.g. A 0-D tensor. A Scalar
        #        A 1-D tensor. with shape[5] : 5개의 행
        #        A 2-D tensor. with shape[3,4] : 3x4 행렬

    # Types : 데이터 타입
    #   e.g.tf.float32~float64, tf.int8~64
    #       tf.bool, tf.string, tf.complex64~128(복소수, 각각 32, 64비트의 실수부 허수부로 나뉨)
    #       tf.qint8~32(양자화 연산에 사용되는 정수)

# Graph : 데이터 분석, Node, Edge로 구성
    # Node : Data Flow Graph Computation이라고도 불림. 지정된 연산 시행
    # Edge : 데이터들이  Edge 들을 통해 흘러감

# Session : 전체 또는 일부를 구동시킬 수 있도록 만들어줌(연산을 시행하기 위한 단계)


# 연산자 종류
#   add, subtract, multiply, divide, pow, mod, abs(절댓값)
#   logical_and(논리곱을 통한 bool값 반환), logical_not, logical_or
#   greater, greater_equal, less, less_equal

import tensorflow as tf

a = tf.constant(5, name='a')
b = tf.constant(3, name='b')
print(a)
c = tf.multiply(a,b,name='c')
d = tf.add(a,b,name='d')
e = tf.add(c,d,name='e')
tf.print(e)
