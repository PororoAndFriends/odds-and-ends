#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct node{
    int x, y;
    struct node *next;
} *Node;

typedef Node Item;

typedef struct queue_type{
    struct node *front;
    struct node *rear;
    int size;
} *Queue;

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

bool is_empty(Queue q){
    return q->size == 0;
}

Item dequeue(Queue q){

    if(is_empty(q)){
        terminate("argument queue is empty");
    }
    Node old = q->front;
    q->front = old->next;
    if(q->front == NULL){
        q->rear = NULL;
    }
    q->size--;

    return old;
}

void make_empty(Queue q){
    while(!is_empty(q)){
        Node temp = dequeue(q);
        free(temp);
    }
    q->size = 0;
}

void distroy(Queue q){
    make_empty(q);
    free(q);
}

void enqueue(Queue q, Node data){

    if(q->front == NULL) {
        q->front = data;
        q->rear = data;
    }else{
        q->rear->next = data;
        q->rear= data;
    }
    q->size++;
}

Item peek(Queue q){
    if(is_empty(q)){
        terminate("argument queue is empty");
    }
    return q->front;
}

int map[20][20];
int n;

void read_map(){
    FILE *fp = fopen("./input2.txt", "r");
    fscanf(fp, "%d", &n);

    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++) {
            fscanf(fp, "%d", &map[i][j]);
        }
    }
    fclose(fp);
}

void print_map(){
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++) {
            printf("%d ", map[i][j]);
        }
        printf("\n");
    }
}

bool moveable(int x, int y, int dir){
    // 동, 남, 서, 북 순서
    if(dir == 0 && y + 1 < n && map[x][y + 1] == 0) {
        return true;
    } else if (dir == 1 && x + 1 < n && map[x + 1][y] == 0) {
        return true;
    } else if (dir == 2 && y - 1 >= 0 && map[x][y - 1] == 0) {
        return true;
    } else if (dir == 3 && x - 1 >= 0 && map[x - 1][y] == 0) {
        return true;
    } else {
        return false;
    }
}

int main(){
    read_map();

    Queue q = create();
    Node cur = (Node) malloc(sizeof(struct node));
    cur->x = 0;
    cur->y = 0;
    map[0][0] = -1;

    enqueue(q, cur);

    while(!is_empty(q)){
        cur = dequeue(q);
        int cur_count = map[cur->x][cur->y];

        for(int dir=0; dir<4; dir++){
            int x = cur->x;
            int y = cur->y;

            while(moveable(x, y, dir)){
                if(dir == 0)            y++;
                else if (dir == 1)      x++;
                else if (dir == 2)      y--;
                else if (dir == 3)      x--;
                map[x][y] = cur_count - 1;
                Node temp = (Node)malloc(sizeof(struct node));
                temp->x = x;
                temp->y = y;
                enqueue(q, temp);
            }
            if(x == n-1 && y == n-1)    break;


        }
        free(cur);
    }

    print_map();
    printf("%d", -(map[n-1][n-1]+2));

}