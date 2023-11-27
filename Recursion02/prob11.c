#include <stdio.h>
int a[1000000];
int n;

void clean_numlist(int level){

    if(level < 1)   return;
    else clean_numlist(level - 1);

    // 첫 단계에서 2칸 간격으로 지우기 때문에
    int step = level + 1;
    int count = 0;
    for (int i = 0; i < n; i++) {
        if(a[i] != 0) count++;
        if (count == step) {
            a[i] = 0;
            count = 0;
        }
    }
}

int main(){

    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        a[i] = i+1;
    }

    clean_numlist(n);

    if(a[n-1] != 0) printf("yes");
    else printf("no");

    return 0;
}