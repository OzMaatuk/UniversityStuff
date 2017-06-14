/**Example on how to read characters from a file
*
* argv[]:
* main argument argv[] is an array holding the program parameters (their names,
* represented as strings).
* For example if you run your program as follows: <program name> <first input>
* then argv[0] is the program name and argv[1] points to its first input.
* In our case argv[1] is the name of the file we wish to open.
*
* FILE*: 
* For the moment you should use FILE *pFile like the example below, even though you don't understand
* what it means. (For the record: it is a pointer to a struct that handles the file reading).
*
* fopen(), fclose():
* fopen(arg,"r") is a function that opens the file <arg> for reading. When fopen
* fails it returns the macro NULL (defined in stdio.h). fclose(arg) closes the file for reading.
*
* fgetc():
* fgetc() is a function that reads one character from the file each time it is
* evoked and returns the current character (represented as an int).
*
* EOF:
* EOF is a macro that indicates the end-of-file character.
*/
#include <stdio.h>

int main (int argc, char *argv[])
{
    FILE *pFile; 
    int c;

    pFile=fopen(argv[1], "r");
    if (pFile==NULL)
    {
        printf("Error opening file\n");
        return 1;
    }
    else
    {
        c = fgetc(pFile);
        printf("First char is: '%c'\n", c);
    }
    fclose (pFile);
    
    return 0;
}
