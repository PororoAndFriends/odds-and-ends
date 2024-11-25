#include <stdio.h>
#include <stdlib.h>
int seq[16],seq_temp[16], N, K;


// 처음 바뀌는 값 -> 0 ~ 15-k으로 계산
// 처음 바뀌는 값을 정하면 K를 하나 내리고, 처음 바뀌는 값을 하나 올린 후 다시 재귀!

void print_seq(){
    for (int i = 0; i < N; i++) {
        printf("%d", seq_temp[i]);
    }
    printf(" ");
}

void solv(int index, int k){

    if(k==0){
        for (int i = index; i < N; i++) {
            seq_temp[i] = seq[i];
        }
        print_seq();
        return;
    }else if(index >= N)    return;
    else{
        if(seq[index] == 1){
            seq_temp[index] = 0;
            solv(index + 1, k - 1);
            seq_temp[index] = 1;
            solv(index + 1, k);
        }else{
            seq_temp[index] = 0;
            solv(index + 1, k);
            seq_temp[index] = 1;
            solv(index + 1, k - 1);
        }
    }
}





int main(){

    scanf("%d", &N);
    scanf("%d", &K);

    char buffer[16];
    scanf("%s", buffer);

    for (int i = 0; i < N; i++) {
        seq[i] = (buffer[i]) - '0';
    }

    solv(0, K);

}