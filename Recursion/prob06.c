#include <stdio.h>
#include <stdlib.h>

int map[20][20];
int n;

void read_map(){
    FILE *fp = fopen("Recursion/input.txt", "r");
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
    print_map();


}