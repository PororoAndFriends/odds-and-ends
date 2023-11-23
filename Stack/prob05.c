#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#define INIT_CAPACITY 100
#define MAX 50
#define VISITED 2
#define BACK 3

typedef int Item;

typedef struct {
	int x;
	int y;
} Location;

struct stack_type {
	Item* contents;
	int top;
	int size;
};

int map[MAX][MAX];
int len;
FILE* fp = fopen("C:/Users/PKNU/Downloads/input.txt", "r");
Location loc;

typedef struct stack_type* Stack;

int offset[8][2] = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };

static void terminate(const char* message) {
	printf("%s\n", message);
	exit(1);
}

Stack create() {
	Stack s = (Stack)malloc(sizeof(struct stack_type));
	if (s == NULL)	terminate("Error in create. stack could not be created");
	s->contents = (Item*)malloc(INIT_CAPACITY * sizeof(Item));
	if (s->contents == NULL) {
		free(s);
		terminate("Error in create. stack could not be created");
	}
	s->top = -1;
	s->size = INIT_CAPACITY;
	return s;
}

void destroy(Stack s) {
	free(s->contents);
	free(s);
}

void reallocate(Stack s) {
	Item* temp = (Item*)malloc(2 * s->size * sizeof(Item));

	for (int i = 0; i < s->top; i++) {
		temp[i] = s->contents[i];
	}
	free(s->contents);
	s->contents = temp;
	s->size = 2 * (s->size);
}

void make_empty(Stack s) {
	s->top = -1;
}

bool is_full(Stack s) {
	return s->size == s->top;
}

void push(Stack s, Item i) {
	if (is_full(s))	reallocate(s);
	s->top++;
	s->contents[s->top] = i;
}

bool is_empty(Stack s) {
	return  s->top == -1;
}

Item pop(Stack s) {
	Item i = s->contents[s->top];
	s->top--;

	return i;
}

Item peek(Stack s) {
	return s->contents[s->top];
}

void print_map() {
	for (int j = 0; j < len; j++) {
		for (int k = 0; k < len; k++) {
			printf("%d ", map[j][k]);
		}
		printf("\n");
	}
}

void read_map() {
	fscanf(fp, "%d", &len);

	for (int i = 0; i < len; i++) {
		for (int j = 0; j < len; j++) {
			fscanf(fp, "%d", &map[i][j]);
		}
	}
}

bool find_1() {
	for (int i = 0; i < len; i++) {
		for (int j = 0; j < len; j++) {
			if (map[i][j] == 1) {
				loc.x = i;
				loc.y = j;
				return true;
			}
		}
	}
	return false;
}

int get_direction() {
	
	//start north, clockwise
	int x = loc.x;
	int y = loc.y;

	if (x >= 1 && map[x - 1][y] == 1)	return 0;
	else if (y + 1 < len && x >= 1 && map[x - 1][y + 1] == 1)	return 1;
	else if (y + 1 < len && map[x][y + 1] == 1)	return 2;
	else if (y + 1 < len && x + 1 < len && map[x + 1][y + 1] == 1)	return 3;
	else if (x + 1 < len && map[x + 1][y] == 1)	return 4;
	else if (y >= 1 && x + 1 < len && map[x + 1][y - 1] == 1)	return 5;
	else if (y >= 1 && map[x][y - 1] == 1)	return 6;
	else if (y >= 1 && x >= 1 && map[x + 1][y - 1] == 1)	return 7;
	else return -1;

}

void move_to(int dir) {
	loc.x += offset[dir][0];
	loc.y += offset[dir][1];
}

int main() {

	int n;
	fscanf(fp, "%d", &n);

	for (int i = 0; i < n; i++) {

		read_map();
		Stack s = create();

		// find walls
		bool flag = find_1();
		// find component and paint VISITED or BACK
		while (flag) {

			// paint fisrt place
			map[loc.x][loc.y] = VISITED;
			int count = 1;
			
			while (1) {

				int dir = get_direction();

				// if have a direction to move
				if (dir != -1) {
					count++;
					move_to(dir);
					push(s, dir);
					map[loc.x][loc.y] = VISITED;
				}
				// if don't have a direction so go back
				else {
					if (!is_empty(s)) {
						map[loc.x][loc.y] = BACK;
						dir = pop(s);
						dir = (dir + 4) % 8;
						move_to(dir);
					}
					// back to first place and have no direction to move
					else
					{
						break;
					}
				}
			}
			printf("%d ", count);
			// find the new component
			// it couldn't find the searched component cause searched component(1) already changed to VISITED(2) or BACK(3)
			// exactly, the place that we start to search will be VISITED and other places will be BACK
			flag = find_1();
		}

		destroy(s);
		printf("\n");
	}

	fclose(fp);
}