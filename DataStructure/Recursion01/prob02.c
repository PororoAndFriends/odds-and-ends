#include <stdio.h>
#include <string.h>
#define BUFFER_SIZE 100


int compare(char *str1, char *str2, int index){
    if (*(str1 + index) < *(str2 + index)) {
        return -1;
    } else if (*(str1 + index) > *(str2 + index)) {
        return 1;
        // 두 단어의 인덱스번째 값이 같을 때
    } else {
        // 두 단어 중 하나가 끝을 만났다면
        if (strlen(str1) - 1 == index || strlen(str2) - 1 == index) {
            // 두 단어가 동시에 끝났다면 0
            if (strlen(str1) == strlen(str2)) return 0;
            // 두 단어 중 하나가 먼저 끝났다면 1 or -1
            else if (strlen(str1) > strlen(str2)) {
                return 1;
            } else return -1;
            // 두 단어가 같고 아직 두 단어 모두 끝을 만나지 않았다면 재귀함수 호출
        } else {
            return compare(str1, str2, index + 1);
        }
    }
}

int main(){
        char str1[BUFFER_SIZE];
        char str2[BUFFER_SIZE];
        scanf("%s", str1);
        scanf("%s", str2);

        int result = compare(str1, str2, 0);

    if (result == 0) {
        printf("'%s' is same with '%s'\n", str1, str2);
    } else if (result < 0) {
        printf("'%s' will com before then '%s'\n", str1, str2);
    }else{
        printf("'%s' will come after than '%s'\n", str1, str2);
    }

}