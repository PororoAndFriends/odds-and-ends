#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int map[15][15];
int n, k;

typedef struct node{
    int x;
    int y;
    struct node *next;
} *Node;

typedef struct queue_type{
    struct node *front;
    struct node *rear;
    int size;
} *Queue;

typedef Node Item;

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

// 데이터를 모두 사용한 후 나중에 free
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
        Item temp = dequeue(q);
        free(temp);
    }
    q->size = 0;
}

void distroy(Queue q){
    make_empty(q);
    free(q);
}

void enqueue(Queue q, Item i){
    if(q->front == NULL) {
        q->front = i;
        q->rear = i;
    }else{
        q->rear->next = i;
        q->rear= i;
    }
    q->size++;
}

Item peek(Queue q){
    if(is_empty(q)){
        terminate("argument queue is empty");
    }

    return q->front;
}

void print_map(){
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++) {
            printf("%d ", map[i][j]);
        }
        printf("\n");
    }
}

void read_map(){
    FILE *fp = fopen("./input1.txt", "r");
    fscanf(fp, "%d", &n);

    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++) {
            fscanf(fp, "%d", &map[i][j]);
        }
    }

    fscanf(fp, "%d", &k);

    fclose(fp);
}

bool moveable(Node cur, int dir){
    // 동, 남, 서, 북 순서
    if(dir == 0 && cur->y + 1 < n && map[cur->x][cur->y+1] == 0) {
        return true;
    } else if (dir == 1 && cur->x + 1 < n && map[cur->x + 1][cur->y] == 0) {
        return true;
    } else if (dir == 2 && cur->y - 1 >= 0 && map[cur->x][cur->y - 1] == 0) {
        return true;
    } else if (dir == 3 && cur->x - 1 >= 0 && map[cur->x - 1][cur->y] == 0) {
        return true;
    } else {
        return false;
    }

}

Node move_to(Node cur, int dir) {
    Node temp = (Node) malloc(sizeof (struct node));
        if(dir == 0){
            temp->x = cur->x;
            temp->y = cur->y + 1;
        }else if(dir == 1){
            temp->x = cur->x + 1;
            temp->y = cur->y;
        }else if(dir == 2){
            temp->x = cur->x;
            temp->y = cur->y - 1;
        }else if(dir == 3){
            temp->x = cur->x - 1;
            temp->y = cur->y;
        }else{
            terminate("direction error");
        }

        return temp;
}

int play_simulate(int x, int y){
    int count = 1;
    Node cur = (Node) malloc(sizeof(struct node));
    cur->x = x;
    cur->y = y;
    map[x][y] = -1;

    Queue q = create();
    enqueue(q, cur);

    while (!is_empty(q)) {
        cur = dequeue(q);
        if (map[cur->x][cur->y] == -k-1) {
            break;
        }
        for (int dir = 0; dir < 4; dir++) {
            if (moveable(cur, dir)) {
                Node p = move_to(cur, dir);
                map[p->x][p->y] = map[cur->x][cur->y] - 1;
                count++;
                enqueue(q, p);
            }
        }
        free(cur);
    }
    distroy(q);
    return count;
}

int main(){
    read_map();
    struct node max;
    int max_count = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (map[i][j] == 0) {

                int count = play_simulate(i,j);

                if (count > max_count) {
                    max.x = i;
                    max.y = j;
                    max_count = count;
                }
                read_map();
            }
        }
    }

    printf("%d %d\n", max.x, max.y);
    printf("%d\n", max_count);


    return 0;
}
