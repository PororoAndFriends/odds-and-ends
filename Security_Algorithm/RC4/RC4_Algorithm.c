#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

void print_list(unsigned char *list, int length);
void swap(unsigned char *list, int i, int j);


int main() {

    /* key setting */
    srand((unsigned int) time(NULL));
    int K_length = rand() % 256;
    char *K = (char *) malloc(sizeof(char) * K_length);
    for (int i = 0; i < K_length; i++) {
        K[i] = rand() % 256;
    }

    /* Initialization */
    int state_length = 256;
    unsigned char S[state_length];
    unsigned char T[state_length];
    for (int i = 0; i < state_length; i++) {
        S[i] = i;
        T[i] = K[i % K_length];
    }


    /* Initial Permutation of S */
    int j = 0;
    for (int i = 0; i < state_length; i++) {
        j = (j + S[i] + T[i]) % state_length;

        swap(S, i, j);
    }


    /* Stream Generation and Test */
    int i = 0; j = 0;
    unsigned char *plaintext = "hello world!";
    printf("plain text : %s\n", plaintext);

    unsigned char *ciphertext = (unsigned char *) malloc(sizeof(unsigned char) * strlen(plaintext));

    for (int k = 0; k < strlen(plaintext); k++) {

        i = (i + 1) % 256;
        j = (j + S[i]) % 256;

        swap(S, i, j);

        unsigned char t = (S[i] + S[j]) % 256;
        unsigned char key = S[t];

        printf("%d ^ %d = %d\n", key, plaintext[k], key ^ plaintext[k]);

        ciphertext[k] = key ^ plaintext[k];
    }

    printf("ciphertext : %s\n", ciphertext);
    for (i = 0; i < strlen(ciphertext); i++) {
        printf("%d ", plaintext[i]);
    }
    printf("\n");

    for (i = 0; i < strlen(ciphertext); i++) {
        printf("%d ", ciphertext[i]);
    }

}

void print_list(unsigned char *list, int length) {

    for (int i = 0; i < length; i++) {
        printf("%d ", list[i]);
    }
    printf("\n");
}

void swap(unsigned char *list, int i, int j){
    unsigned char temp = list[i];
    list[i] = list[j];
    list[j] = temp;
}

