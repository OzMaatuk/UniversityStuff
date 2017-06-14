#include <stdio.h>
#define UPDOWNCH 32

int main()
{
	//define vars, ask from user a input and scan it
	char ch;
	//printf("enter some charecter:");
	scanf("%c",&ch);
	
	//check all optianal cases
	if(ch>='A' && ch<='Z')
	{
		//if its upper letter
	    printf("%c->%c\n",ch,ch+UPDOWNCH);
	}
	else if(ch>='a' && ch<='z')
	{
		//if its lower letter
		printf("%c->%c\n",ch,ch-UPDOWNCH);
	}
	else if(ch>='0' && ch<='9')
	{
		//if its number, find its numric value and prints his pow
		printf("%d\n", (ch-'0')*(ch-'0'));
	}
	else
	{
		//if not all latest cases, just print
		printf("%c->Invalid Input\n",ch);
	}
	
	return 0;
}
