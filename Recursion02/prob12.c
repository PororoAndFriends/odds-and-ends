#include <stdio.h>
#include <stdbool.h>
#define WALL 1
#define PASSED 2

int N, map[16][16], cur[2], goal[2];
int offset[4][2] = {{-1, 0},
                    {0,  1},
                    {1,  0},
                    {0,  -1}};

void read_map(){
    FILE *fp = fopen("Recursion02/input.txt", "r");

    fscanf(fp, "%d", &N);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            fscanf(fp, "%d", &map[i][j]);
        }
    }

    fscanf(fp, "%d %d", &cur[0], &cur[1]);
    fscanf(fp, "%d %d", &goal[0], &goal[1]);

    fclose(fp);
}

bool solv(int x_, int y_){

    if (x_ == goal[0] && y_ == goal[1]) {
        return true;
    }

    for (int i = 0; i < 4; i++) {
        int x = x_;
        int y = y_;

        while (x >= 0 && x < N && y >= 0 && y < N && map[x][y] != WALL) {
            x += offset[i][0];
            y += offset[i][1];
        }

        x += offset[i][0];
        y += offset[i][1];

        while (x >= 0 && x < N && y >= 0 && y < N && map[x][y] != WALL) {
            if(map[x][y] == PASSED){
                x += offset[i][0];
                y += offset[i][1];
                continue;
            }
            map[x][y] = PASSED;
            bool flag = solv(x, y);
            if(flag) return true;
            x += offset[i][0];
            y += offset[i][1];
        }
    }
    return false;

}

void print_map(){
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            printf("%d ", map[i][j]);
        }
        printf("\n");
    }
}

int main(){

    read_map();
    bool flag = solv(cur[0], cur[1]);

    if(flag) printf("YES");
    else printf("NO");

}