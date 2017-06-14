/*
Oz Maatuk
305181158
excercise 01
*/

#include <stdio.h>
#include <math.h>

int main(void)
{
	int x = 0;
	printf("Oz Maatuk \n");
	printf("305181158 \n");
	printf("Matala 01 \n");
	printf("Course C \n");
	printf("press question number \n");
	scanf("%d", &x);
	switch(x)
	{
		case 1: Q1();
			break;
		case 2: Q2();
			break;
		case 3: Q3();
			break;
		case 4: Q4();
			break;
		case 5: Q5();
			break;
	}
	return;
}

int Q1()
{
	int currNum = 0;
	int maxNum = 0;
	int sumNum = 0;
	int countNums = 0;
	printf("start writing numbers, the last one will be negative \n");

	scanf("%d", &currNum);
	while (currNum >= 0)
	{
		sumNum = sumNum + currNum;
		countNums++;
		
		if (maxNum < currNum)
		{
			maxNum = currNum;
		}		

		scanf("%d", &currNum);
		printf("\n");
	}

	printf("the maximum number is: %d \n", maxNum);
	printf("the adding of the numbers is: %d \n", sumNum);
	printf("you entred %d numbers \n", maxNum+1);
	return;
}

int Q2()
{
	int x = 1;
	int y = 1;
	int z = 1;
	int count = 0;
	int lim = 1000; //(int)sqrt(1000) == 31

	while (x < lim)
	{
		while (y < lim)
		{
			while (z < lim)
			{
				if ((x*x + y*y) == (z*z))
				{
					printf("[%d, %d, %d] \n", x, y, z);
					count++;
				}
				z++;
			}
			z = 1;
			y++;
		}
		y = 1;
		x++;
	}
	printf("there is %d number matchs \n", count);
	return;
}

int Q3()
{
	int hight = 0;
	int width = 0;
	int i = 0;
	int j = 0;
	printf("enter hight and width \n");
	scanf("%d", &hight);
	scanf("%d", &width);
	for (i = 0; i < hight; i++)
	{
		for (j = 0; j < width; j++)
		{
			char c = 'A';
			int min = i;
			if (min > j)
			{
				min = j;
			}
			if (min > (hight-i-1))
			{
				min = (hight-i-1);
			}
			if (min > (width-j-1))
			{
				min = (width-j-1);
			}
			printf("%c", c+min);
		}
		printf("\n");
	}
	return;
}

int Q4()
{
	int num1 = 0;
	printf("enter number");
	scanf("%d", &num1);
	num1 = inverse(num1);
	printf("%d", num1);
	return;
}

int inverse(int num1)
{
	int num2 = 0;
	int tmp = num1 % 10;
	num2 = tmp;
	num1 = num1 / 10;
	while (num1 != 0)
	{
		tmp = num1 % 10;
		num2 = num2 * 10;
		num2 = num2 + tmp;
		num1 = num1 / 10;
	}
	return num2;
}

int Q5()
{
	int hight = 0;
	int width = 0;
	int mid = 0;
	int i = 0;
	int j = 0;
	printf("enter hight");
	scanf("%d", &hight);
	width = 2*hight;
	mid = width/2+1;
	for (i = 0; i <= hight; i++)
	{
		for (j = 0; j < mid-i; j++)
		{
			printf(" ");
		}
		for (j = 0; j < (2*i-1); j++)
		{
			printf("*");
		}
		printf("\n");
	}
	return;
}