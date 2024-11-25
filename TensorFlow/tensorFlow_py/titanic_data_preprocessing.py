import numpy as np
import pandas as pd
pd.set_option('display.max_columns', None)
pd.set_option('display.max_rows', None)
pd.set_option('mode.chained_assignment', None)

train_data = pd.read_csv('./train.csv')
test_data = pd.read_csv('./test.csv')
train_length = len(train_data)
age_mean = int(train_data['Age'].mean())
Y = train_data['Survived'].copy()

test_data = pd.read_csv('./test.csv')

train_data = train_data.drop(columns='PassengerId')
train_data = train_data.drop(columns='Name')
train_data = train_data.drop(columns='Ticket')
train_data = train_data.drop(columns='Cabin')

test_data = test_data.drop(columns='PassengerId')
test_data = test_data.drop(columns='Name')
test_data = test_data.drop(columns='Ticket')
test_data = test_data.drop(columns='Cabin')


for idx in range(train_length):
    # 나이 데이터가 비어있을 경우 평균값으로 대체
    if np.isnan(train_data['Age'][idx]):
        train_data['Age'][idx] = age_mean
    # 성별이 남성인 경우 1, 여성인 경우 0으로 인코딩
    if train_data['Sex'][idx] == 'male':
        train_data['Sex'][idx] = 1
    else:
        train_data['Sex'][idx] = 0
    # 탑승한 항구에 따라, C 이면 1, Q이면 2, S이면 3, None인 경우 0으로 설정
    if train_data['Embarked'][idx] == 'C':
        train_data['Embarked'][idx] = 1
    elif train_data['Embarked'][idx] == 'Q':
        train_data['Embarked'][idx] = 2
    elif train_data['Embarked'][idx] == 'S':
        train_data['Embarked'][idx] = 3
    else:
        train_data['Embarked'][idx] = 0

train_data['Embarked'] = train_data['Embarked'].astype(int)
train_data['Sex'] = train_data['Sex'].astype(int)

print(train_data.info())


train_data.to_csv('./train_preprocessed.csv', index=False)

test_data.to_csv('./test_preprocessed.csv', index=False)