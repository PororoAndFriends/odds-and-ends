#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define STACK_SIZE 100
#define BUFFER_SIZE 100

typedef int item;

typedef struct stack{
    item contents[STACK_SIZE];
    int top;
} *Stack;

bool is_empty(Stack s){
    return s->top == -1;
}

void push(Stack s, item item1){
    s->top++;
    s->contents[s->top] = item1;
    return;
}

item pop(Stack s) {

    if (!is_empty(s)) {
        s->top--;
        return s->contents[s->top + 1];
    }
    printf("stack is empty\n");
    exit(1);
}

item peek(Stack s){
    if (!is_empty(s)) {
        return s->contents[s->top];
    }
    printf("stack is empty\n");
    exit(1);
}

Stack create_stack(){
    Stack s = (Stack) malloc(sizeof(struct stack));
    s->top = -1;
    return s;
}

int get_prior(char operator){
    if (operator == '+' || operator == '-') {
        return 1;
    } else if (operator == '*' || operator == '/') {
        return 2;
    } else if (operator == ')' || operator == '(') {
        return 0;
    }else {
        printf("operator Error\n");
        exit(1);
    }
}

char *proc_oper(Stack s, char *pos, char operator){


    if(is_empty(s) || operator == '('){
        push(s, operator);
        return pos;
    }else{
        while (!is_empty(s) && get_prior(peek(s)) >= get_prior(operator)) {
            char temp = pop(s);
            if (temp != '('){
                sprintf(pos, "%c ", temp);
                pos += 2;
            }

            }
    }

    if (operator != ')'){
        push(s, operator);

    }

    return pos;
}

char *get_postfix(char *postfix){
    // get expression by stdin
    char expression[BUFFER_SIZE];
    fgets(expression, BUFFER_SIZE, stdin);
    expression[strlen(expression)-1] = '\0';

    char *pos = postfix;
    Stack s = create_stack();

    char *token = strtok(expression, " ");
    while (token != NULL) {
        if (token[0] >= '0' && token[0] <= '9') {
            sprintf(pos, "%s ", token);
            pos += strlen(token) + 1;
        } else if (token[0] > -1) {
            pos = proc_oper(s, pos, token[0]);
        }else{
            printf("expression Error\n");
            exit(1);
        }
        token = strtok(NULL, " ");
    }

    while (!is_empty(s)) {
        sprintf(pos, "%c ", pop(s));
        pos += 2;
    }
    sprintf(pos, "\0");

    free(s);
    return postfix;
}

int cal_oper(int n1, int n2, char oper){
    if(oper == '+'){
        return n1 + n2;
    }else if(oper == '-'){
        return n1 - n2;
    }else if(oper == '*'){
        return n1 * n2;
    }else if(oper == '/'){
        return n1 / n2;
    }else{
        printf("operator Error\n");
        exit(1);
    }
}

int calc_postfix(char *pos){

    char *token = strtok(pos, " ");

    Stack num_s = create_stack();

    while (token != NULL) {
        if (token[0] >= '0' && token[0] <= '9') {
            push(num_s, atoi(token));
        } else if (token[0] > -1) {
            int num2 = pop(num_s);
            int num1 = pop(num_s);
            push(num_s, cal_oper(num1, num2, token[0]));
        }else{
            printf("expression Error\n");
            exit(1);
        }
        token = strtok(NULL, " ");
    }

    return pop(num_s);
}

int main(){
    char post[BUFFER_SIZE];
    get_postfix(post);


    int result = calc_postfix(post);
    printf("%d\n", result);
}
