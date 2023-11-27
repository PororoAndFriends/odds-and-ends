#include <stdio.h>

/*
1. 다음의 순환 함수의 반환값을 x와 y의 함수로 나타내면?
    int fun1(int x, int y){
        if (x > y)  return 0;
        return y + fun1(x, y-1);
    }
 */

// 정답 : return = 시그마(밑 : k = x, 위 : y) k


/*
 2. 다음의 순환함수의 반환값을 n의 함수로 나타내면?
 // Assume that n >= 1
int fun2(int n){
    if(n == 1)
        return 0;
    else
        return 1 + fun2(n/2);
}
 */

// 정답 :  return = [log2 N], []는 가우스 함수


/*
3. 다음의 순환함수가 결과적으로 하는 일은?
    void fun3(int n){
    if (n == 0)
        return;
    fun3(n/2);
    printf("%d", n%2);
    }
 */

// 정답 : 2진수 변환 출력


/*
4. 다음의 순환함수가 결과적으로 하는 일은?
    void fun4(int n){
    if (n > 1)
        fun4(n-1);
    for (int i = 0; i < n; i++)
        printf(" * ");
    }
 */

// 정답 : n! 만큼의 별 출력


/*
5. 다음의 함수 fun5의 반환값을 a와 b에 관한 식으로 표현하면?
    int fun(int x, int y){
        if (y == 0) return 0;
        return (x + fun(x, y-1));
        }

    int fun5(int a, int b){
    if (b == 0) return 1;
    return fun(a, fun5(a, b-1));
    }
 */

// 정답 : return = a^b


/*
6. 다음 함수가 결과적으로 하는 일을 최대한 간명하게 설명하라.
    int fun6(int a[], int n){
        if(n == 1)  return a[0];

        int x = fun6(a, n-1);
        return (x > a[n-1] ? x : a[n-1]);
    }
 */

// 정답 : 배열 a의 0번 인덱스 부터 n-1번째 인덱스 중 가장 큰 값을 찾아냄


/*
7. 다음 함수가 결과적으로 하는 일을 최대한 간명하게 설명하라.
    double fun7(double a[], int n){
        if (n==1) return a[0];
        else
            return (a[n-1] + (n-1)*fun7(a, n-1))/n;
    }
 */

// 정답 : 배열 a의 0번 인덱스부터 n-1번째 인덱스까지의 평균을 구함


/*
8. 다음 함수가 결과적으로 하는 일을 최대한 간명하게 설명하라.
    int fun8(int a, int b){
        if (b == 0)
            return 1;
        if (b % 2 == 0)
            return fun8(a*a, b/2);
        return fun8(a*a, b/2)*a;
    }
 */

// 정답 : a^b


/*
9. 다음 함수가 결과적으로 하는 일을 최대한 간명하게 설명하라.
    void fun9(int arr[], int start_index, int end_index){
        if(start_index >= end_index)
            return;
        int min_index;
        int temp;
    // Assume that minIndex() returns index of minimum value in array arr[start_index...end_index]

        min_index = minIndex(arr, start_index, end_index);
        temp = arr[start_index];
        arr[start_index] = arr[min_index];
        arr[min_index] = temp;
        fun9(arr, start_index + 1, end_index);
    }

*/

// 정답 : start_index부터 end_index까지를 정렬하는 알고리즘
