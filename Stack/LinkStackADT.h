#ifndef LINK_STACKADT_H
#define LINK_STACKADT_H

#include <stdbool.h>

typedef int Item;

struct node{
	Item data;
	struct node *next;
};

struct stack_type{
	struct node *top;
};

typedef struct stack_type *Stack;
typedef struct node *Node;

Stack create();
void destroy(Stack s);
bool is_empty(Stack s);
void push(Stack s, Item i);
Item pop(Stack s);
Item peek(Stack s);

#endif
