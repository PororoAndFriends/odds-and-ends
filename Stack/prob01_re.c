#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct stack {
    int list[100];
} *Stack;

int dex = -1;

void push(Stack s, int i){
    dex++;
    s->list[dex] = i;
}

int pop(Stack s){
    dex--;
    return s->list[dex + 1];
}

int main(){

    Stack s = (Stack)malloc(sizeof(struct stack));

    char str[100];
    fgets(str,100,  stdin);
    int count = 0;


    for (int i = 0; i < strlen(str); i++) {
        if (str[i] == '(') {
            count++;
            printf("%d ", count);
            push(s, count);
        } else if (str[i] == ')') {
            printf("%d ", pop(s));
        }
    }





}