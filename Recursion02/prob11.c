#include <stdio.h>
#include <stdbool.h>
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

bool lucky(int n, int k){
    if (n % k == 0) {
        return false;
    } else if (n < k) {
        return true;
    }else{
        lucky(n - n / k, k + 1);
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

    if(lucky(n, 2)) printf("yes");
    else printf("no");

    return 0;
}