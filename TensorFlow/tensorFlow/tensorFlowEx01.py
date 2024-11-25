# Tensor : 데이터를 표현하는 방식, 2차원 행렬을 더 높은 차원으로 확장(벡터의 일반화된 형태)
#        -> 데이터 플로우 그래프(Dataflow Graph)를 통해 데이터의 흐름을 노드와 엣지를 통한 방향 그래프로 표현
#               노드 : Operation, 엣지 : Data Array
#               -> 이 기능을 Tensor + Flow 를 합쳐 TensorFlow 라고 함
#
# Deep Learning 종류
# 1. CNN(Convolutional Neural Network)
#   -> 필터학습을 통한 비전 분야 활용
# 2. RNN(Recurrent Neural Network) : 순차적 데이터(서열 데이터)를 통해 패턴, 정보를 추출
#   -> 텍스트, 음성, 음악, 영상 등의 분야에서 활용
# 3. RBS(Restricted Boltzmann Machine), DBN
#   -> 제한된 블츠만 머신 : 비지도 학습, 차원축소, 분류, 선형회귀 분석 필터링, 특징값 학습, 주제 모델링 등에 활용
#
#
# 머신러닝 : 데이터를 인간이 처리하여 컴퓨터를 학습 -> 사람에 따라 성능이 달라짐
# 딥러닝 : 인간의 작업 생략, 알고리즘을 이용하여 스스로 분석 -> 사람의 능력이 중요하진 않지만 데이터가 머신러닝에 비해 훨씬 많아야 함
#
# CPU : 직렬 처리 -> 복잡한 연산
# GPU : 병렬 처리 -> 간단한 연산