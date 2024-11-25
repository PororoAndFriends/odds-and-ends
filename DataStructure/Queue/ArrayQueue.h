#ifndef ArrayQueue_H
#define ArrayQueue_H
#define CAPACITY 100
#include <stdbool.h>

typedef int Item;

typedef struct queue_type *Queue;

typedef struct node *Node;

Queue create();
void distroy(Queue q);
void make_empty(Queue q);
bool is_empty(Queue q);
bool is_full(Queue q);
void enqueue(Queue q, Item i);
Item dequeue(Queue q);
Item peek(Queue q);
int get_size(Queue q);

#endif
