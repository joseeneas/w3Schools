#include <stdio.h>
#include <stdlib.h>

void bubbleSort(int arr[], int n)
{
    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < n - i - 1; ++j)
        {
            if (arr[j] > arr[j + 1])
            {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

void radixSortWithBubbleSort(int arr[], int n)
{
    int max_val = arr[0];
    for (int i = 1; i < n; ++i)
    {
        if (arr[i] > max_val)
            max_val = arr[i];
    }

    int exp = 1;
    while (max_val / exp > 0)
    {
        int radixArray[10][n];
        int count[10] = {0};

        for (int i = 0; i < n; ++i)
        {
            int radixIndex = (arr[i] / exp) % 10;
            radixArray[radixIndex][count[radixIndex]++] = arr[i];
        }

        for (int i = 0; i < 10; ++i)
        {
            bubbleSort(radixArray[i], count[i]);
        }

        int index = 0;
        for (int i = 0; i < 10; ++i)
        {
            for (int j = 0; j < count[i]; ++j)
            {
                arr[index++] = radixArray[i][j];
            }
        }

        exp *= 10;
    }
}

int main()
{
    int myArray[] = {170, 45, 75, 90, 802, 24, 2, 66};
    int n = sizeof(myArray) / sizeof(myArray[0]);
    printf("Original array: ");
    for (int i = 0; i < n; ++i)
        printf("%d ", myArray[i]);
    printf("\n");

    radixSortWithBubbleSort(myArray, n);

    printf("Sorted array: ");
    for (int i = 0; i < n; ++i)
        printf("%d ", myArray[i]);
    printf("\n");

    return 0;
}

// C