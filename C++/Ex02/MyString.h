#pragma once

// returns the len for the input string
int strLen(const char s[]);

// Check if str2 is a sub string of str1, return the starting index on str2 in str1
// isCyclic and step been used like the description in the question
int extendedSubStr(int isCyclic, int step, const char* str1, const char* str2);

//--------------------------------------------------------------------//

// replace all delim chars to ';'
void replaceDelim(char str[], const char* delim);

// swaping chars between two places at the string
void swap(char s[],int i,int j);

// returns the lentgh of the word starting from index at the string str (not include ;)
int wordLen(char str[], int index);

// returns index of the next word in string str, FALSE if there is no more words
int nextWord(char str[], int index);

// swaping word at index1 with the word at index2 from the string str 
// index2 always be bigger then index1
void swapWords (char str[], int fWord);

// handle equaltion case when chars are equals at two indexs
// return TRUE if word at index1 is smaller then the word at index2, FALSE else
int whosBigger(char str[], int index1);

//Sorts the words in str separated by the chars in delim.
void sortDelim(char str[], const char* delim);