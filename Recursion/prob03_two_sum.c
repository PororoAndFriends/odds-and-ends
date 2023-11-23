#include <stdio.h>
#define MAX 1000

int two_sum(int start, int end, int data[], int sum, int count){
    if(start >= end){
        return count;
    }else if(data[start] + data[end] == sum){
        count++; start++; end--;
    }else if(data[start] + data[end] > sum) {
        end--;
    }else{
        start++;
    }
    return two_sum(start, end, data, sum, count);
}

int main(){

    int data[MAX];
    int N, K;
    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        scanf("%d", &data[i]);
    }
    scanf("%d", &K);

    int result = two_sum(0, N - 1, data, K, 0);

    printf("%d", result);
}