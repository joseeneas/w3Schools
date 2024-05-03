#include <stdio.h>

int main()
{
    int prev2 = 0, prev1 = 1;
    int newFibo;

    printf("%d\n", prev2);
    printf("%d\n", prev1);

    for (int fibo = 0; fibo < 18; fibo++)
    {
        newFibo = prev1 + prev2;
        printf("%d\n", newFibo);
        prev2 = prev1;
        prev1 = newFibo;
    }

    return 0;
}

// C