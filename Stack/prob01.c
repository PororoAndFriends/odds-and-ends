#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#define INIT_CAPACITY 100

typedef int Item;

struct stack_type{
	Item *contents;
	int top;
	int size;
};

typedef struct stack_type *Stack;

static void terminate(const char *message){
	printf("%s\n", message);
	exit(1);
}

Stack create(){
	Stack s = (Stack)malloc(sizeof(struct stack_type));
	if(s == NULL)	terminate("Error in create. stack could not be created");
	s->contents = (Item *)malloc(INIT_CAPACITY * sizeof(Item));
	if(s->contents == NULL){
		free(s);
		terminate("Error in create. stack could not be created");
	}
	s->top = -1;
	s->size = INIT_CAPACITY;
	return s;
}

void destroy(Stack s){
	free(s->contents);
	free(s);
}

void reallocate(Stack s){
	Item *temp = (Item *)malloc(2 * s->size * sizeof(Item));
	
	for(int i=0; i<s->top; i++){
		temp[i] = s->contents[i];
	}
	free(s->contents);
	s->contents = temp;
	s->size = 2 * (s->size);	
}

void make_empty(Stack s){
	s->top = -1;
}

bool is_full(Stack s){
	return s->size == s->top;
}

void push(Stack s, Item i){
	if(is_full(s))	reallocate(s);
	s->top++;
	s->contents[s->top] = i;
}

bool is_empty(Stack s){
	return  s->top == -1;
}

Item pop(Stack s){
	Item i = s->contents[s->top];
	s->top--;
	
	return i;
};

Item peek(Stack s){
	return s->contents[s->top];
};



int main(){
	
	char buffer[100];
	fgets(buffer, 100, stdin);
	int len = strlen(buffer);
	Stack s = create();
	
	int index = 1;
	
	for(int i=0;i<len;i++){
		if(buffer[i] == '('){
			push(s, index);
			printf("%d ", index);
			index++;
		}else if(buffer[i] == ')'){
			int temp = pop(s);
			printf("%d ", temp);
		}
		
	}
	
	
}
