#include <stdio.h>
#define TRUE 1
#define FALSE 0
#define NOTFOUND -1
#define OFFSET 1

// returns the len for the input string
int strLen(const char s[])
{
	int i = 0;
	//run over the string, till it ends
	while (s[i] != '\0')
	{
		//increasing i for counting
		i++;
	}
	return i;
}

// Check if str2 is a sub string of str1, return the starting index on str2 in str1
// isCyclic and step been used like the description in the question
int extendedSubStr(int isCyclic, int step, const char* str1, const char* str2)
{
	int i, j, newI, count;
	int len1 = strLen(str1);
	int len2 = strLen(str2);
	
	// check trem for start working,
	// if our steps are negativ, or str1 is empty, or str2 is empty,
	// or we are checking cyclic string and str1 is shorter then str2
	// then there is no resone for checking
	if(step <= 0 || len1 == 0 || len2 == 0 || (isCyclic == FALSE && len1 < len2))
	{
		return NOTFOUND;
	}
	
	//else, start checking
	//goes over str1 to find first equal char in str2
	for (i = 0; i < len1; i++)
	{
		count = 0;
		//find match for first char at str2
		if (str1[i] == str2[0])
		{
			//forwordingbt steps over str1
			newI = i + step;
			count++;
			
			//check if str2 is just a char
			if (str2[1] == '\0')
			{
				return i;
			}
			
			if (isCyclic == FALSE)
			{
				//fowording by 1 over str2
				for (j = 1; j < len2; j++)
				{
					//finding matches
					if (str1[newI] == str2[j])
					{
						count++;
						newI = newI + step;
					}
					else
					{
						//if not match, break this for and start with new char at str1
						break;
					}
					//if we found all str2
					if (count == len2)
					{
						return i;
					}
				}
			}
			else //isCyclic
			{
				for (j = 1; j < len2; j++)
				{
					if (str1[newI % len1] == str2[j])
					{
						count++;
						newI = newI + step;
					}
					else
					{
						break;
					}
					
					if (count == len2)
					{
						return i;
					}
				}
			}
		}
	}
	return NOTFOUND;
}

//----------------------------------------------------------------------------//

// replace all delim chars to ';'
void replaceDelim(char str[], const char* delim)
{
	int sLen = strLen(str);
	int dLen = strLen(delim);
	int i = 0;
	
	for (i = 0; i < sLen; i++)
	{
		int j = 0;
		for (j = 0; j < dLen; j++)
		{
			if (str[i] == *(delim+j))
			{
				str[i] = ';';
			}
		}
	}
}

// swaping chars between two places at the string
void swap(char s[],int i,int j)
{
	char temp = s[i];
	s[i] = s[j];
	s[j] = temp;
}

// returns the lentgh of the word starting from index at the string str (not include ;)
int wordLen(char str[], int index)
{
	int len = 0;
	
	while (str[index] != ';' && str[index] != '\0')
	{
		index++;
		len++;
	}
	
	return len;
}

// returns index of the next word in string str, FALSE if there is no more words
int nextWord(char str[], int index)
{
	while (str[index] != ';')
	{
		if (str[index] == '\0')
		{
			return FALSE;
		}
		index++;
	}
	index++;
	return index;
}

// swaping word at index1 with the word at index2 from the string str 
// index2 always be bigger then index1
void swapWords (char str[], int fWord)
{
	//int fWordLen = wordLen(str, fWord);
	int sWord = nextWord(str, fWord);
	int sWordLen = wordLen(str, sWord);
	int buf = sWord - fWord;
	int i = 0;
	int j = 0;

	// if its last word, dont copy the '\0'
	if (str[sWord + sWordLen] == '\0')
	{
		sWordLen--;
	}
	
	// start moving chars bt 2 fors
	// first for if for the number of chars
	for (i = 0; i <= sWordLen; i++)
	{
		// second for is for the places to move
		for (j = 0; j < buf; j++)
		{
			swap(str, sWord - j + i, sWord - j - OFFSET + i);
		}
	}
	
	// mnikre kazte bo yesh ; besuf ha str
	if (str[strLen(str) - OFFSET] == ';')
	{
		// az taaavir ota buf mekomot ahora
		for (j = 0; j < buf - OFFSET; j++)
		{
			swap(str, sWord + sWordLen - j, sWord + sWordLen - j - OFFSET);
		}
	}
}

// handle equaltion case when chars are equals at two indexs
// return TRUE if word at index1 is smaller then the word at index2, FALSE else
int whosBigger(char str[], int index1)
{
	int index2 = nextWord(str,index1);
	
	// forword while same chars
	while (str[index1] == str[index2])
	{
		index1++;
		index2++;
		
		if (str[index2] == ';' || str[index2] == '\0')
		{
			// second word smaller then first
			return FALSE;
		}
		if (str[index1] == ';' || str[index1] == '\0')
		{
			// first word smaller then second
			return TRUE;
		}
		if (str[index1] == ';' && (str[index2] == ';' || str[index2] == '\0'))
		{
			// same word
			return NOTFOUND;
		}
	}

	//in this case, find diff char
	if (str[index1] < str[index2])
	{
		// first word smaller then second
		return TRUE;
	}
	// second word smaller then first
	return FALSE;
}

void sortDelim(char str[], const char* delim)
{
	//starting pos
	int i = 0;
	int j = 0;
	
	// replace all delim chars to ';'
	replaceDelim(str, delim);
	
	// sorting like buble sort...
	// goes thrwo all words in str
	while (nextWord(str, i) != FALSE)
	{
		j = 0;
		//// goes thrwo all words in str
		while (nextWord(str,j) != FALSE)
		{
			//printf("%s , i = %d , j = %d \n", str, i, j);
			// checks bigger word between the two
			if (whosBigger(str, j) == FALSE)
			{
				// swap if needed
				swapWords(str, j);
			}
			//else
			{
				// go to the next word from j
				j = nextWord(str,j);
			}
		}
		// again check from the next word from i
		i = nextWord(str, i);
	}
}