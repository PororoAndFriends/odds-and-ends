#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define QUEUE_SIZE 100
#define PATH 0
#define WALL 1
#define VISITED -1

int N;
int map[15][15];

typedef struct pos{
    int x;
    int y;
} *Item;

typedef struct queue{
    Item content[QUEUE_SIZE];
    int front;
    int rear;
    int cap;
    int size;
} *Queue;

Queue create_queue(){
    Queue temp = (Queue) malloc(sizeof(struct queue));
    temp->cap = QUEUE_SIZE;
    temp->front = 0;
    temp->rear = -1;
    temp->size = 0;

    return temp;
}

void enqueue(Queue q, Item i){
    q->rear++;
    q->content[q->rear] = i;
    q->size++;
}

Item dequeue(Queue q){
    Item i = q->content[q->front];
    q->front++;
    q->size--;
    return i;
}

Item top(Queue q){
    return q->content[q->front];
}

bool is_empty(Queue q) {
    return q->size == 0;
}

void read_map(){
    FILE *fp = fopen("Queue/input2.txt", "r");
    fscanf(fp, "%d", &N);

    if (fp == NULL) {
        printf("File pointer Error\n");
        exit(1);
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            fscanf(fp, "%d", &map[i][j]);
        }
    }
}

void print_map(){
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%d ", map[i][j]);
        }
        printf("\n");
    }
}

int offset[4][2] = {
        {-1, 0},
        {0, 1},
        {1, 0},
        {0, -1}
};

bool check_movable(int x, int y, int dir){

    int x_ = x + offset[dir][0];
    int y_ = y + offset[dir][1];

    if (x_ > -1 && y_ > -1 && x_ < N && y_ < N && map[x_][y_] == 0) {
        map[x_][y_] = map[x][y] - 1;
        return true;
    }
    return false;
}

int main(){
    read_map();
    Queue q = create_queue();

    Item f = (Item) malloc(sizeof(struct pos));
    f->x = 0;
    f->y = 0;
    enqueue(q, f);
    map[0][0] = VISITED;

    while (!is_empty(q)) {
        Item temp = dequeue(q);


        if (temp->x == N - 1 && temp->y == N - 1) {
            printf("find path\n");
            print_map();
            exit(0);
        }


        for (int i = 0; i < 4; i++) {
            if(check_movable(temp->x, temp->y, i)){
                Item p = (Item) malloc(sizeof(struct pos));
                p->x = temp->x + offset[i][0];
                p->y = temp->y + offset[i][1];
                enqueue(q, p);
            }
        }
        free(temp);
    }
    printf("No Path\n");
    return 0;
}