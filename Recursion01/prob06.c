#include <stdio.h>
#include <stdbool.h>
#define PATH 0
#define WALL 1
#define PASSED 2
#define FAILED 3

int map[20][20];
int n;
int offset[4][2] = {{-1,0}, {0, 1}, {1,0}, {0, -1}};

int find_solve(int x, int y);

void read_map(){
    FILE *fp = fopen("Recursion01/input.txt", "r");
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

int main(){
    read_map();
    int count = find_solve(0, 0);
    printf("%d", count);
}


int find_solve(int x, int y) {
    int count = 0;
    if(x < 0 || x >= n ||  y < 0 || y >= n) return 0;
    else if(map[x][y] == WALL || map[x][y] == FAILED || map[x][y] == PASSED) return 0;

    if(x == n-1 && y == n-1){
        return 1;
    }else{
        map[x][y] = PASSED;
        for (int i = 0; i < 4; i++) {
            int x_ = x + offset[i][0];
            int y_ = y + offset[i][1];
            count += find_solve(x_, y_);
        }
        map[x][y] = PATH;
    }
    return count;
}
