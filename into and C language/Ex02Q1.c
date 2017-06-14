/*
Oz Maatuk
305181158
Ex02
Q1
C Programing
*/

#include <stdio.h>

void minmax(int *num, int *ans)
{
	int min = 9;
	int max = 0;
	int tmp = 0;

	while (*num > 0)
	{
		tmp = *num%10;
		if (tmp < min)
		{
			min = tmp;
		}
		if (tmp > max)
		{
			max = tmp;
		}
		*num = *num/10;
	}
	*num = max;
	*ans = min;
}

void main()
{
	int num = 0;
	int num2 = 0;
	printf("enter a number \n");
	scanf("%d", &num);
	minmax(&num, &num2);
	printf("max = %d, min = %d \n", num, num2); 
}