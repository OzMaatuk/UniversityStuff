#include <stdio.h>
#include <stdlib.h>
#define LIMIT_DOWN_X -35
#define LIMIT_UP_X 35
#define LIMIT_UP_Y 10
#define LIMIT_DOWN_Y -10
#define ACCURACY 0.5

int main()
{
	//define vars
	int x,y;
	double a,b,c,d,sol;
	
	//prints msgs for user and scan for vars
	printf("y(x)=a+b*x+c*x^2+d*x^3\n");
	printf("Select a:\n");
	scanf("%lf",&a);
	printf("Select b:\n");
	scanf("%lf",&b);
	printf("Select c:\n");
	scanf("%lf",&c);
	printf("Select d:\n");;
	scanf("%lf",&d);
	
	//prints the poly
	printf("y(x)=(%.3lf)+(%.3lf)*x+(%.3lf)*x^2+(%.3lf)*x^3\n" ,a,b,c,d);
	
	//starts running for the graph area
	for(y=LIMIT_UP_Y;y>=LIMIT_DOWN_Y;y--)
	{
		for(x=LIMIT_DOWN_X;x<=LIMIT_UP_X;x++)
		{
			//calc the solution
			sol = a+b*x+c*x*x+d*x*x*x;
			
			//prints the graph
			if((sol-y)<ACCURACY && (sol-y)>-ACCURACY)
			{
				//prints * for the poly point
				printf("*");
			}
			else if(x==0 && y==0)
			{
				//print the center of x,y axis
				printf("+");
			}
			else if(x==0 && y!=0)
			{
				//prints | for y axis
				printf("|");
			}
			else if(x!=0 && y==0)
			{
				//prints - for x axis
				printf("-");
			}
			else
			{
				//prints scape for blank area
				printf(" ");
			}
		}
		//line down...
		printf("\n");
	}
	return 0;
}
