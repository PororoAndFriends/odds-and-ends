#include <stdio.h>
#include <stdbool.h>


int mark[15];
int N;

int abs(int num){
    if(num < 0) return -num;
    return num;
}

int n_queen(int level){
    int count = 0;

    if(level == N) return 1;


    for (int i = 0; i < N; i++) {
        bool do_able = true;
        for (int j = 0; j < level; j++) {
            if (mark[j] == i || level - j == abs(mark[j] - i)) {
                do_able = false;
                break;
            }
        }
        if (do_able) {
            mark[level] = i;
            count += n_queen(level + 1);
        }
    }

    return count;

}



int main(){
    scanf("%d", &N);

    printf("%d\n", n_queen(0));


}