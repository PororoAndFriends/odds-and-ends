#include <stdio.h>
#include <stdlib.h>
#include "ArrayQueue.h"

struct queue_type{
    Item *contents;
    int front;
    int rear;
    int capacity;
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
    Queue q = malloc(sizeof(struct queue_type));
    if(q == NULL){
        terminate("queue didn't created");
    }
    q->contents = (Item *) malloc(CAPACITY * sizeof(Item));
    if(q->contents == NULL){
        free(q);
        terminate("queue contents didn't created");
    }

    q->front = 0;
    q->rear = -1;
    q->size = 0;
    q->capacity = CAPACITY;

    return q;
}

void distroy(Queue q){
    free(q->contents);
    free(q);
}

void make_empty(Queue q){
    q->front = 0;
    q->rear = -1;
    q->size = 0;
}

bool is_empty(Queue q){
    return q->size == 0;
}

bool is_full(Queue q){
    return q->size == q->capacity;
}

Item dequeue(Queue q){
    if(is_empty(q)){
        terminate("argument queue is empty");
    }
    Item i = q->contents[q->front];
    q->front = (q->front+1) % (q->capacity);
    q->size--;

    return i;
}

Item peek(Queue q){
    if(is_empty(q)){
        terminate("argument queue is empty");
    }

    return q->contents[q->front];
}

void reallocate(Queue q){
    Item *temp = (Item *)malloc(2 * q->capacity * sizeof(Item));
    if(temp ==NULL){
        terminate("reallcated failed");
    }
    int j = q->front;

    for (int i = 0; i < q->size; i++) {
        temp[i] = q->contents[j];
        j = (j+1)%(q->capacity);
    }
    free(q->contents);
    q->front = 0;
    q->rear = q->size-1;
    q->contents = temp;
    q->capacity *= 2;
}

void enqueue(Queue q, Item i){
    if(is_full(q)){
        reallocate(q);
    }
    // capacity보다 크거나 같다면 기존 rear+1값이 아닌 처음으로 돌아간 값이 들어감
    // circle queue를 위한 코드
    q->rear = (q->rear + 1) % (q ->capacity);
    q->contents[q->rear] = i;
    q->size++;
}