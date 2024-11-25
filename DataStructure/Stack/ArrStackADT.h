#ifndef ARR_STACKADT_H
#define ARR_STACKADT_H

#include <stdbool.h>

typedef int Item;

struct stack_type{
	Item *contents;
	int top;
	int size;
};

typedef struct stack_type *Stack;

Stack create();
void destroy(Stack s);
void make_empty(Stack s);
bool is_empty(Stack s);
void push(Stack s, Item i);
Item pop(Stack s);
Item peek(Stack s);

#endif
