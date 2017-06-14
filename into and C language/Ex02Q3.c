/*
Oz Maatuk
305181158
Ex02
Q3
C Programing
*/

#include <stdio.h>

void even(int *arr)
{
	int i = 0;
	int j = 0;
	int evenln = 0;
	int evencol = 0;
	int sumln = 0;
	int sumcol = 0;
	for (i = 0; i < 10; i++)
	{
		for (j = 0; j < 10; j++)
		{
			sumln = sumln + *(arr+(10*i+j));
			sumcol = sumcol + *(arr+(10*j+i));
		}
		if ((sumln % 2) == 0)
		{
			evenln++;
		}
		if ((sumcol % 2) == 0)
		{
			evencol++;
		}
		sumln = 0;
		sumcol = 0;
	}
	*arr = evenln;
	*(arr+1) = evencol;
}

void main()
{
	int tmp = 0;
	int i = 0;
	int j = 0;
	int arr[10][10];
	printf("enter matrix 10X10 \n");
	for (i = 0; i < 10; i++)
	{
		for (j = 0; j < 10; j++)
		{
			scanf("%d", &tmp);
			arr[i][j] = tmp;
		}
	}
	even(arr[0]);
	printf("even cols: %d \n even lines: %d \n", arr[0][1], arr[0][0]);
}