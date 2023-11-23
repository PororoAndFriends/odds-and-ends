#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef char *Item;

struct node{
	Item data;
	struct node *next;
};

struct stack_type{
	struct node *top;
	char *name;
};

typedef struct stack_type *Stack;
typedef struct node *Node;

static void terminate(const char *message){
	printf("%s\n", message);
	exit(1);
}

Stack create(char *name){
	Stack s = (Stack)malloc(sizeof(struct stack_type));
	s->top = NULL;
	s->name = name;
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

Stack stackList[100];
int stackIndex = 0;

Stack find_stack(char *name){
	Stack temp;
	for(int i=0;i<stackIndex;i++){
		if(strcmp(stackList[i]->name, name) == 0){
			temp = stackList[i];
			break;
		}
	}
	if(temp == NULL)	printf("Stack not founded\n");
	return temp;
}

int main(){
	
	char buffer[100];
	char *command;
	char *word1;
	char *word2;
	

	
	while(1){
		printf("$ ");
		fgets(buffer, 100, stdin);
		buffer[strlen(buffer)-1] = '\0';
		command = strtok(buffer, " ");
		word1 = strtok(NULL, " ");
		
		if(strcmp(command, "create") == 0){
			Stack temp = create(strdup(word1));
			stackList[stackIndex++] = temp;
		}else if (strcmp(command, "push") == 0){
			Stack s = find_stack(word1);
			word2 = strtok(NULL, " ");
			push(s, strdup(word2));
		}else if(strcmp(command, "pop") == 0){
			Stack s = find_stack(word1);
			pop(s);
		}else if(strcmp(command, "list") == 0){
			Stack s = find_stack(word1);
			Node p = s->top;
			while(p != NULL){
				printf("%s\n", p->data);
				p = p->next;
			}
		}else if(strcmp(command, "exit") == 0){
			break;
		}
	}
	
	
}


