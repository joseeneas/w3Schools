#include <stdio.h>
#include <stdlib.h>

void merge(double left[], int leftSize, double right[], int rightSize, double result[])
{
    int i = 0, j = 0, k = 0;

    while (i < leftSize && j < rightSize)
    {
        if (left[i] < right[j])
        {
            result[k++] = left[i++];
        }
        else
        {
            result[k++] = right[j++];
        }
    }

    while (i < leftSize)
    {
        result[k++] = left[i++];
    }

    while (j < rightSize)
    {
        result[k++] = right[j++];
    }
}

void mergeSort(double arr[], int length)
{
    int step = 1;

    while (step < length)
    {
        for (int i = 0; i < length; i += 2 * step)
        {
            int leftSize = step;
            int rightSize = step;
            if (i + step >= length)
                leftSize = length - i;
            if (i + 2 * step >= length)
                rightSize = length - i - step;

            double left[leftSize];
            double right[rightSize];

            for (int j = 0; j < leftSize; ++j)
            {
                left[j] = arr[i + j];
            }

            for (int j = 0; j < rightSize; ++j)
            {
                right[j] = arr[i + step + j];
            }

            double merged[leftSize + rightSize];
            merge(left, leftSize, right, rightSize, merged);

            for (int j = 0; j < leftSize + rightSize; ++j)
            {
                arr[i + j] = merged[j];
            }
        }

        step *= 2;
    }
}

int main()
{
    double arr[] = {3, 7, 6, -10, 15, 23.5, 55, -13};
    int length = sizeof(arr) / sizeof(arr[0]);

    mergeSort(arr, length);

    printf("Sorted array: ");
    for (int i = 0; i < length; ++i)
    {
        printf("%.1f ", arr[i]);
    }
    printf("\n");

    return 0;
}

// C