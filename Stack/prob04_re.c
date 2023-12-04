#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct stack{
    char list[100];
} *Stack;
int dex = -1;

void push(Stack s, char i){
    s->list[++dex] = i;
}

char pop(Stack s){
    dex--;
    return s->list[dex + 1];
}

bool is_empty(Stack s){
    return dex == -1;
}

char peek(Stack s){
    return s->list[dex];
}


int main(){
    Stack s = (Stack) malloc(sizeof(struct stack));
    char str[100];
    fgets(str, 100, stdin);
    int N;
    scanf("%d", &N);

    int pop_count = 0;

    for (int i = 0; i < strlen(str); i++) {
        if (!is_empty(s)) {
            while (!is_empty(s) && peek(s) < str[i] && pop_count != N) {
                pop(s);
                pop_count++;
            }
        }
        push(s, str[i]);
    }

    for (int i = 0; i < strlen(str) - N - 1; i++) {
        printf("%c", s->list[i]);
    }


}