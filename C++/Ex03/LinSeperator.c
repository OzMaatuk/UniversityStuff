#include "LinSeperator.h"
#include "LinSeperatorHelperFunctions.h"

#include <stdio.h>
#include <stdlib.h>

#define MAX 200 
#define FILEENDS -1
#define NEGATIVE -1
#define POSTIVE 1
#define OFFSET 1

struct Oranges 
{ 
	double vec[MAX];
}; typedef struct Oranges Oranges;

void LinSeperator(const char *In, const char *Out)
{
    // define indexs
    int i = 0, j = 0;
    // define vars for dimintion, number of pos oranges, number of neg oranges, ans y
    int dim ,numOfPos ,numOfNeg, y;
    // define vars for syn of catezit mul, and tmp
	double syn = 0 , tmp = 0;
	// define oranges struct ov vector
	Oranges w ,xi;
	
	// open files for read ans for write
	FILE *inFile= fopen(In,"r");
	FILE *outFile = fopen(Out,"w");
	
	// if cant open file to read, error
	if (inFile == NULL)
	{
		printf("Error\n");
		exit(0);
	}
	
	// scaning references from file
	// dimontion
	fscanf(inFile,"%d",&dim);
	// number of postive
	fscanf(inFile,"%d",&numOfPos);
	// number of negative
	fscanf(inFile,"%d",&numOfNeg);
	
	// init w orange struct vector
	for (i = 0; i < dim ; i++)
	{
		w.vec[i] = 0;
	}
	
	// init y for start scaning postive oranges
	y = POSTIVE;
	
	// scaning from file the postive oranges
	for ( i = 0; i < numOfPos + numOfNeg; i++)
	{
	    // scaning values for xi orange struct vector
		for (j = 0; j < dim; j++)
		{
			fscanf(inFile,"%lf,",&xi.vec[j]);
		}
		
		//calc syn of cartezit mul
		for (j = 0; j < dim; j++)
		{
			syn = syn + (xi.vec[j] * w.vec[j]);
		}
		
		// check id done scaning postive oranges
		if (i >= numOfPos)
		{
		    // change y for scaning negative oranges
		    y = NEGATIVE;
		}
		
		// if the syn of the catezit mul is not equals to the relative y
		if (y * syn <= 0)
		{
			for (j = 0; j < dim; j++)
			{
			    w.vec[j] = w.vec[j] + y * xi.vec[j];
			}
		}
	}
	
	// continue scaning for "y less" oranges till end of file
	while ( (fscanf(inFile,"%lf,",&tmp)) != FILEENDS)
	{
	    // init syn
		syn = 0;
		
		// start calc the cartezit mul, this is first step becouse of scaned first sample
		syn = syn + (w.vec[0] * tmp);
		
		// and continue to the next samples
		for (i = 1; i < dim; i++)
		{
			fscanf(inFile,"%lf,",&tmp);
			syn = syn + (w.vec[i] * tmp);
		}
		
		// write to the out file the syn of teh orange
		if (syn >= 0)
		{
		    // 1 if postive orange
		    fprintf(outFile,"1\n");
		}
		else
		{
		    // -1 if negative orange
		    fprintf(outFile,"-1\n");
		}
	}
	
	// closing files
	fclose(inFile);
	fclose(outFile);
}
