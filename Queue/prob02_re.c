#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#define SIZE 300
#define PATH 0
#define WALL 1


typedef struct locate{
    int x;
    int y;
} *Locate;

typedef Locate Item;


Item queue[SIZE];
int rear = -1;
int front = 0;
int size = -1;
int map[100][100];
int N;

void enqueue(Item item){
    queue[++rear] = item;
    size++;
}

Item dequeue(){
    if (rear != -1) {
        front++;
        size--;
        return queue[front - 1];
    }else{
        exit(1);
    }
}

bool is_empty(){
    return size == -1;
}

void print_map(){
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%d ", map[i][j]);
        }
        printf("\n");
    }
}

void read_map(){
    FILE *fp = fopen("Queue/input2.txt", "r");
    fscanf(fp, "%d", &N);

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            fscanf(fp, "%d", &map[i][j]);
        }
    }
}

int offset[4][2] = {{-1, 0},
                    {0,  1},
                    {1,  0},
                    {0,  -1}};


int main(){
    read_map();

    Locate l = (Locate) malloc(sizeof(struct locate));
    l->x = 0;
    l->y = 0;
    enqueue(l);

    while (!is_empty()) {

        Locate temp = dequeue();

        if (temp->x == N - 1 && temp->y == N - 1) {
            break;
        }

        for (int i = 0; i < 4; i++) {
            int x = temp->x;
            int y = temp->y;
            x += offset[i][0];
            y += offset[i][1];

            while (x >= 0 && x < N && y >= 0 && y < N && map[x][y] != WALL) {
                if(map[x][y] == PATH) {
                    map[x][y] = map[temp->x][temp->y]-1;
                    Locate t = (Locate) malloc(sizeof(struct locate));
                    t->x = x;
                    t->y = y;
                    enqueue(t);
                }
                x += offset[i][0];
                y += offset[i][1];
            }
        }
        free(temp);
    }

    printf("%d", -map[N - 1][N - 1] - 1);

}
