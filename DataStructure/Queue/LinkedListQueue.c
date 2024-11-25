#include <stdio.h>
#include <stdlib.h>
#include "LinkedListQueue.h"


struct node{
    Item data;
    struct node *next;
};

struct queue_type{
    struct node *front;
    struct node *rear;
    int size;
};

void terminate(const char *message){
    printf("%s\n", message);
    exit(EXIT_FAILURE);
}

int get_size(Queue q){
    return q->size;
}

Queue create(){
    Queue q = (Queue)malloc(sizeof(struct queue_type));

    if(q == NULL){
        terminate("queue didn't created");
    }

    q->front = NULL;
    q->rear = NULL;
    q->size = 0;

    return q;
}

void distroy(Queue q){
    make_empty(q);
    free(q);
}

void make_empty(Queue q){
    while(!is_empty(q)){
        dequeue(q);
    }
    q->size = 0;
}

bool is_empty(Queue q){
    return q->size == 0;
}

void enqueue(Queue q, Item i){
    Node temp = malloc(sizeof(struct node));
    temp->data = i;
    temp->next = NULL;

    if(q->front == NULL) {
        q->front = temp;
        q->rear = temp;
    }else{
        q->rear->next = temp;
        q->rear= temp;
    }
    q->size++;
}

Item dequeue(Queue q){
    Node old;
    Item i;

    if(is_empty(q)){
        terminate("argument queue is empty");
    }
    old = q->front;
    i = old->data;
    q->front = old->next;
    if(q->front == NULL){
        q->rear = NULL;
    }
    free(old);
    q->size--;

    return i;
}

Item peek(Queue q){
    if(is_empty(q)){
        terminate("argument queue is empty");
    }
    return q->front->data;
}
