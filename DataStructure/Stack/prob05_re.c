#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define WALL 1
#define VISITED 2
#define NO_WAY 3

typedef struct stack{
    int list[100];
} *Stack;
int dex = -1;

void push(Stack s, int i){
    s->list[++dex] = i;
}

int pop(Stack s){
    dex--;
    return s->list[dex + 1];
}

bool is_empty(Stack s){
    return dex == -1;
}

int peek(Stack s){
    return s->list[dex];
}

int N;
int map[100][100];

void read_map(){
    FILE *fp = fopen("Stack/input.txt", "r");
    fscanf(fp, "%d", &N);

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

int get_dir(int x, int y){
    if (x >= 1 && map[x - 1][y] == WALL)	return 0;
    else if (y + 1 < N && x >= 1 && map[x - 1][y + 1] == WALL)	return 1;
    else if (y + 1 < N && map[x][y + 1] == WALL)	return 2;
    else if (y + 1 < N && x + 1 < N && map[x + 1][y + 1] == WALL)	return 3;
    else if (x + 1 < N && map[x + 1][y] == WALL)	return 4;
    else if (y >= 1 && x + 1 < N && map[x + 1][y - 1] == WALL)	return 5;
    else if (y >= 1 && map[x][y - 1] == WALL)	return 6;
    else if (y >= 1 && x >= 1 && map[x + 1][y - 1] == WALL)	return 7;
    else return -1;
}

int offset[8][2] = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };

int make_component(int x, int y){
    map[x][y] = VISITED;
    int c = 1;
    Stack s = (Stack) malloc(sizeof(struct stack));

    int dir = get_dir(x, y);
    push(s, dir);
    if (dir != -1) {
        x += offset[dir][0];
        y += offset[dir][1];
    }

    while (!is_empty(s)) {

        if(map[x][y] == 1)
            c++;

        map[x][y] = VISITED;

        dir = get_dir(x, y);

        if (dir != -1) {
            push(s, dir);
        }else{
            map[x][y] = NO_WAY;
            dir = pop(s);
            dir = (dir + 4) % 8;
        }
        x += offset[dir][0];
        y += offset[dir][1];
    }
    return c;
}

int main(){
    read_map();

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if(map[i][j] == 1){
                int c = make_component(i, j);
                printf("%d ", c);
            }
        }
    }


}