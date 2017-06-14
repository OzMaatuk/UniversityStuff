/*
Oz Maatuk
305181158
Ex02
Q2
C Programing
*/

#include <stdio.h>

void max_repeats(int *arr, int *len)
{
	int max = 0;
	int i = 0;
	int count = 0;
	for (i = 0; i < *len+1; i++)
	{
		if (max == *(arr+i))
		{
			count++;
		}
		if (max < *(arr+i))
		{
			max = *(arr+i);
			count = 1;
		}
	}
	*arr = max;
	*len = count;
}

void main()
{
	int i = 0;
	int arr[10];
	int tmp = 0;
	int *p;
	printf("enter 10 integers \n");
	for (i = 0; i < 10; i++)
	{
		scanf("%d", &tmp);
		arr[i] = tmp;
	}
	p = arr;
	max_repeats(p, &i);
	printf("max = %d, repeats = %d \n", *p, i);
}