/*
Oz Maatuk
305181158
Ex03
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <malloc.h>
#include <math.h>
#define maxSize 81
#define deps 5

typedef struct
{
	char name[15];
	int hour, min, sec;
}	task;

typedef struct
{
	char name[15];
	int department;
	float salary;
}	employee;

typedef struct 
{
	char name[10];
	int area, population;
}	state;

void Q1();
void Q2();
void Q3();
void Q4();
int dense(state *Arr, int Len);

void main()
{
	int choose = 0;
	printf("enter number of question:");
	scanf("%d", &choose);
	switch(choose)
	{
	case 1: Q1();
			break;
	case 2: Q2();
			break;
	case 3: Q3();
			break;
	case 4: Q4();
			break;
	}
}

void Q1()
{
	char *smallStr = NULL;
	char *bigStr = NULL;
	char *numStr = NULL;
	char *pSmallStr = NULL;
	char *pBigStr = NULL;
	char *pNumStr = NULL;
	char *str = (char *)malloc(maxSize * sizeof(char));
	char *tmp = NULL;
	int bigLen = 0, smallLen = 0, numLen = 0;

	printf("enter a string with max 80 chars \n");
	gets(str); 

	printf("you entered \n");
	puts(str);
	printf("your string length: %d \n", strlen(str));

	tmp = str;
	while (*tmp != '\0')
	{
		if (*tmp <= 'z' && *tmp >= 'a')
		{
			smallLen++;
		}
		else if (*tmp <= 'Z' && *tmp >= 'A')
		{
			bigLen++;
		}
		else if (*tmp <= '9' && *tmp >= '0')
		{
			numLen++;
		}
		tmp++;
	}

	printf("small digits: %d \n", smallLen);
	printf("big digits: %d \n", bigLen);
	printf("numbers digits: %d \n", numLen);

	smallStr = (char *)malloc((smallLen+1) * sizeof(char));
	bigStr = (char *)malloc((bigLen+1) * sizeof(char));
	numStr = (char *)malloc((numLen+1) * sizeof(char));
	
	pSmallStr = smallStr;
	pBigStr = bigStr;
	pNumStr = numStr;
	
	tmp = str;
	while (*tmp != '\0')
	{
		if (*tmp <= 'z' && *tmp >= 'a')
		{
			*smallStr = *tmp; 
			smallStr++;
		}
		else if (*tmp <= 'Z' && *tmp >= 'A')
		{
			*bigStr = *tmp;
			bigStr++;
		}
		else if (*tmp <= '9' && *tmp >= '0')
		{
			*numStr = *tmp;
			numStr++;
		}
		tmp++;
	}

	*smallStr = '\0';
	*bigStr = '\0';
	*numStr = '\0';
	
	puts(pSmallStr);
	puts(pBigStr);
	puts(pNumStr);

	free((char *)str);
	free((char *)pSmallStr);
	free((char *)pBigStr);
	free((char *)pNumStr);

	printf("Done! \n");
}

void Q2()
{
	int stateLen = 0;
	int i = 0;
	state *stateArr = NULL;

	printf("enter number of states: \n");
	scanf("%d", &stateLen);
	stateArr = (state *)malloc(stateLen * sizeof(state));

	printf("enter states information one after one \n");
	for (i = 0; i < stateLen; i++)
	{
		printf("state number %d: \n", i+1);
		printf("name- \n");
		scanf("%s", &stateArr[i].name);
		printf("area- \n");
		scanf("%d", &stateArr[i].area);
		printf("population- \n");
		scanf("%d", &stateArr[i].population);
	}
	printf("//////////////////////////////////////////\n");

	printf("number of states that big enough: %d \n", dense(stateArr, stateLen));

	free((state *)stateArr);
	printf("Done!\n");
}

int dense(state *Arr, int Len)
{
	int i = 0, tmp = 0, count = 0;
	for (i = 0; i < Len; i++)
	{
		tmp = Arr[i].population / Arr[i].area;
		if (tmp > 1000)
		{
			printf("state is big enough: %s \n", Arr[i].name);
			count++;
		}
	}
	return count;
}

void Q3()
{
	employee *employeeArr = NULL;
	int empLen = 0;
	int tmp = 0;
	int i = 0;
	int sum[deps] = {0, 0, 0, 0, 0};
	int count[deps] = {0, 0, 0, 0, 0};
	float avg[deps] = {0, 0, 0, 0, 0};
	printf("enter number of emploies: \n");
	scanf("%d", &empLen);
	employeeArr = (employee *)malloc(empLen * sizeof(employee));

	for (i = 0; i < empLen; i++)
	{
		printf("employee number %d: \n", i+1);
		printf("name: \n");
		scanf("%s", &employeeArr[i].name);
		printf("department: \n");
		scanf("%d", &employeeArr[i].department);
		printf("salary: \n");
		scanf("%f", &employeeArr[i].salary);
	}
	printf("///////////////////////////////////////\n");

	for (i = 0; i < empLen; i++)
	{
		sum[employeeArr[i].department] = sum[employeeArr[i].department] + employeeArr[i].salary;
		count[employeeArr[i].department]++;
	}

	for (i = 0; i < deps; i++)
	{
		avg[i] = (float)((float)sum[i] / (float)count[i]);
	}

	for (i = 0; i < deps; i++)
	{
		printf("department %d averge: %f \n", i, avg[i]);
	}

	free((employee *)employeeArr);
	printf("Done!\n");
}

void Q4()
{
	task *taskArr = NULL;
	int taskLen = 0;
	int i = 0;
	int hour = 0, min = 0, sec = 0;

	printf("enter number of tasks: \n");
	scanf("%d", &taskLen);
	taskArr = (task *)malloc(taskLen * sizeof(task));

	printf("enter tasks: \n");
	for (i = 0; i < taskLen; i++)
	{
		printf("task number: %d \n", i);
		printf("name - ");
		scanf("%s", &taskArr[i].name);
		printf("hour - ");
		scanf("%d", &taskArr[i].hour);
		printf("minute - ");
		scanf("%d", &taskArr[i].min);
		printf("secound - ");
		scanf("%d", &taskArr[i].sec);
	}
	printf("//////////////////\n");

	for (i = 0; i < taskLen; i++)
	{
		hour = hour + taskArr[i].hour;
		min = min + taskArr[i].min;
		sec = sec + taskArr[i].sec;
	}
	min = min + (sec/60);
	sec = sec % 60;
	hour = hour + (min/60);
	min = min % 60;

	printf("total time: %d:%d:%d \n", hour, min, sec);
	free((task *)taskArr);
	printf("Done!\n");

}