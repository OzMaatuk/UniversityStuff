/*
Oz Maatuk
305181158
Ex04
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void Q1();
void Q2();
void Q3();
void Q4();

void main()
{
	int choose = 0;
	printf("Ex04 - C Course, enter question number: \n");
	scanf("%d", &choose);
	printf("///////////////////////////////////////////////////// \n");
	switch (choose)
	{
		case 1:
			Q1();
			break;
		case 2:
			Q2();
			break;
		case 3:
			Q3();
			break;
		case 4:
			Q4();
			break;
	}
	printf("///////////////////////////////////////////////////// \n");
	printf("End \n");
}

void Q1()
{
	char tmp = ' ';
	char peula = ' ';
	int ezer = 0;
	int num1 = 0;
	int num2 = 0;
	int over = 0;
	int result = 0;

	FILE *f = fopen("expr.txt", "rt");
	if (!f)
	{
		printf("File not found \n");
		exit(1);
	}
	else
	{
		printf("file found \n");

		while (!feof(f))
		{
			fscanf (f, "%c", &tmp);
			if (feof(f))
			{
				break;
			}
			if ((tmp == '+') || (tmp == '-') || (tmp == '/') || (tmp == '*'))
			{
				over = 1;
				peula = tmp;
			}
			else
			{
				ezer = (((int)tmp) - '0');
				if (over == 0)
				{
					if (num1 != 0)
					{
						num1 = num1 * 10;
					}
					num1 = num1 + ezer;
				}
				else
				{
					if (num2 != 0)
					{
						num2 = num2 * 10;
					}
					num2 = num2 + ezer;
				}
			}
		}

		switch(peula)
		{
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				result = num1 / num2;
				break;
		}
		printf("to calc: %d %c %d = %d \n", num1, peula, num2, result);
		printf("DONE!! \n");
	}
	fflush(f);
	fclose(f);
}

void Q2()
{
	int n = 0;
	int i = 0;
	int tmp = 0;
	int num1 = 1;
	int num2 = 1;
	char filename[50];
	FILE *f = NULL;

	printf("enter file name (without .txt): \n");
	scanf("%s", &filename);
	strcat(filename, ".txt");

	f = fopen(filename, "w+t");
	if(!f)
	{
		printf("cant creat file \n");
	}
	else
	{
		printf("file opened/created \n");

		printf("enter length of fibunachi: \n");
		scanf("%d", &n);

		fprintf(f, "pibunachi is: %d", num1);
		fprintf(f, ", %d", num2);

		for (i = 0; i < (n-2); i++)
		{
			fprintf(f, ", %d", (num1+num2));
			tmp = num2;
			num2 = num1 + num2;
			num1 = tmp;
		}
	}

	fclose(f);
	printf("DONE!! \n");
}

void Q3()
{
	char filename1[50];
	char filename2[50];
	char tmp = ' ';
	FILE *f1 = NULL;
	FILE *f2 = NULL;

	printf("enter first file name to copy from (without .txt): \n");
	scanf("%s", &filename1);
	strcat(filename1, ".txt");
	printf("enter secound file name to copy to (without .txt): \n");
	scanf("%s", &filename2);
	strcat(filename2, ".txt");

	f1 = fopen(filename1, "rt");
	if (!f1)
	{
		printf("file dosnt exist \n");
	}
	else
	{
		printf("file found \n");
		printf("start copying \n");

		f2 = fopen(filename2, "w+t");

		while (1)
		{
			tmp = fgetc(f1);
			if (tmp == EOF)
			{
				break;
			}
			else
			{
				putc(tmp, f2);
			}
		}

		printf("copying succeded! \n");
	}
	fclose(f1);
	fclose(f2);
}

void Q4()
{
	char filename[50];
	FILE *f = NULL;
	int tmp1 = 0;
	int tmp2 = 0;
	int result = 1;

	printf("enter file name (with integers) to read from (without .txt): \n");
	scanf("%s", &filename);
	strcat(filename, ".txt");

	f = fopen(filename, "rt");
	if (!f)
	{
		printf("file not found");
	}
	else
	{
		printf("file found, start checking.... \n");

		fscanf(f, "%d", &tmp1);
		while (!feof(f))
		{
			fscanf(f, "%d", &tmp2);	
			if (tmp1 > tmp2)
			{
				result = 0;
				break;
			}
			tmp1 = tmp2;
		}

		if (result == 0)
		{
			printf("the numbers in the file are not in ascending order \n");
		}
		else
		{
			printf("the numbers in the file are in ascending order \n");
		}
	}

	printf("DONE! \n");
	fclose(f);
}