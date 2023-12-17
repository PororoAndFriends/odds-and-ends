#include <stdio.h>


int n_queen(int level, int bord[15], int N);

int abs(int num) {
    if(num < 0) return -num;
    else return num;
}

int main(){

    // bord[j] 는 j번째 열에 놓여있는 퀸의 행을 저장
    int bord[15] = {0};
    int N = 14;
    int count  = n_queen(0, bord, N);
    printf("%d", count);
}

int n_queen(int level, int bord[15], int N) {

    int count = 0;

    // i는 검사하는 행, level은 검사하는 열
    for (int i = 0; i < N; i++) {
        int doable = 1;
        // bord[j]는 이미 퀸이 놓여있는 행, j는 이미 퀸이 놓여있는 열
        for (int j = 0; j < level; j++){
            if(i == bord[j] || abs(bord[j]-i) == abs(j - level)) {
                doable = 0;
                break;
            }
        }
        // 만약 검사하는 위치에 착수가 가능하다면
        if (doable) {
            // 만약 검사하는 마지막 열이라면 가능한 경우의 수기 때문에 1을 리턴
            if(level == N-1){
                return 1;
            }
            // 마지막 열이 아니라면 해당 열을 bord배열에 저장 후 그 다음 열의 경우의 수 계산 후 저장
            bord[level] = i;
            count += n_queen(level + 1, bord, N);

            // 가능한 모든 수를 반복해가면 count값을 늘려감
        }
    }

    return count;
}

