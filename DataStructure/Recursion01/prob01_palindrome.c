#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#define BUFFER_SIZE 100

bool is_palindrome(int start, int end, char* word) {

    if(start > end)    return true;
    else if(*(word + start) == *(word + end)){
        return is_palindrome(start + 1, end - 1, word);
    }
    else return false;

}

int main(){
    char word[BUFFER_SIZE];
    scanf("%s", word);

    bool result = is_palindrome(0, strlen(word) - 1, word);

    if (result) {
        printf("YES\n");
    } else printf("NO\n");
}