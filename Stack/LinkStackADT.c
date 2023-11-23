#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "LinkStackADT.h"

static void terminate(const char *message){
	printf("%s\n", message);
	exit(1);
}

Stack create(){
	Stack s = (Stack)malloc(sizeof(struct stack_type));
	s->top = NULL;
	return s;
}

Node create_node_instance(Item i){
	Node temp = (Node)malloc(sizeof(struct node));
	temp->data = i;
	temp->next = NULL;
}

void destroy(Stack s){
	Node p = s->top;
	Node q = NULL;
	
	while(p != NULL){
		q = p;
		p = p->next;
		free(q);
	}
	free(s);
}

void push(Stack s, Item i){
	Node temp = create_node_instance(i);
	temp->next = s->top;
	s->top = temp;
}

bool is_empty(Stack s){
	return  s->top == NULL;
}

Item pop(Stack s){
	Node temp = s->top;
	Item i = temp->data;
	s->top = temp->next;
	free(temp);
	
	return i;
};

Item peek(Stack s){
	return s->top->data;
};



