#include <stdio.h>
#define MAX 1000

int floor(int end, int data[], int K){
    if(end < 0) return -1;
    else if(data[end] <= K) return data[end];
    else    return floor(end-1, data, K);
}

int ceiling(int start, int data[], int K, int N) {
    if(start > N-1) return -1;
    else if(data[start] >= K) return data[start];
    else return ceiling(start+1, data, K, N);
}

int main(){

    int data[MAX];
    int N, K;
    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        scanf("%d", &data[i]);
    }
    scanf("%d", &K);

    printf("%d\n", floor(N - 1, data, K));

    printf("%d\n", ceiling(0, data, K, N));


}