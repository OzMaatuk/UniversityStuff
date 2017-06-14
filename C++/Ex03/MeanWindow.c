#include "MeanWindow.h"
#include "MeanWindowHelperFunctions.h"
#include <stdio.h>
#include <stdlib.h>
#define Max 100

// sum the cols and the rows in the matrix like were asked
void sumRowsCols (float mat[Max][Max] , int rows , int cols)
{
    // define indexs
	int i , j;
	
	// sum cols
	for (i = 0; i < rows; i++)
	{
		for ( j = 1; j < cols; j++)
		{
		    mat[i][j] = mat[i][j] + mat[i][j-1];
		}
	}
	
	// sum rows
	for (i = 1; i < rows; i++)
	{
		for (j = 0; j < cols; j++)
		{
			mat[i][j] = mat[i][j] + mat[i-1][j];
		}
	}
}

// implement func like were asked
void MeanWindow(const char *InFile, const char *OutFile)
{
    // deffIne vars
    // indexs
    int i = 0 , j = 0;
    // vars for saving windowns limits
    int limUp = 0, limDown = 0 , limLeft = 0 , limRight = 0;
    // count for reading references from file
    int refCount=0;
    // number of rows ans cols at the matrix
    int rows = 0 , cols = 0;
    // window size
    int winSize = 0;
    // radius
	float radius = 0;
	// vars for saving matrix values for calc formula
	float val1 = 0 , val2 = 0 , val3 = 0 , val4 = 0;
	
	// deffIne matrix
	float mat[Max][Max];
	float newMat[Max][Max];
	
	// open files, for read and for write
	FILE *fIn = fopen(InFile,"r");
	FILE *fOut = fopen(OutFile,"w");
	
	// if cant open file to read, error
	if (fIn == NULL)
	{
		printf("Error \n");
		exit(0);
	}
	
	// while its not the end of the file
	while(!feof(fIn))
	{
	    // refCount for 3 references from file
		refCount++;
		
		// num of rowss
		if(refCount==1)
		{
		    fscanf(fIn,"%d",&rows);
		}
		// num of colss
		else if (refCount==2)
		{
		    fscanf(fIn,"%d",&cols);
		}
		// window size
		else if(refCount==3)
		{
		    fscanf(fIn,"%d",&winSize);
		}
		// starts reading matrix from file
		else
		{
			for(i = 0 ;i<rows;i++)
			{
				for(j = 0 ;j<cols;j++)
				{
					fscanf(fIn ,"%f", &mat[i][j]);
				}
			}
		}
	}
	
	// init radius
	radius = (winSize-1)/2;
	
	// sum the matrix by cols and rows
	sumRowsCols(mat, rows, cols);
	
	for (i = 0; i < rows; i++)
	{
		for ( j = 0; j < cols; j++)
		{
		    // init lims
		    limUp = i - radius - 1;
		    limDown = i + radius;
		    limLeft = j - radius - 1;
		    limRight = j + radius;
			
			// checking limits...
			if (limDown >= rows)
			{
			    limDown = rows - 1;
			}
			if (limRight >= cols)
			{
			    limRight = cols - 1;
			}
			
			// sets vals for formula calc (kol ha ktzavot shel ha halon)
			val1 = mat[limDown][limRight];
			// checking limits again... and sets vals on for the currct case
			if (limUp < 0)
			{
			    val2 = 0;
			}
			else
			{
				val2 = mat[limUp][limRight];
			}
			if (limLeft < 0)
			{
			    val3 = 0;
			}
			else
			{
				val3 = mat[limDown][limLeft];
			}
			if ((limLeft < 0) || (limUp < 0))
			{
				val4 = 0;
			}
			else
			{
				val4 = mat[limUp][limLeft];
			}

			// init new matrix like the formula...
			newMat[i][j] = (val1 - val2 - val3 + val4)/(winSize*winSize);
		}
	}
	
	// prints references to the out file
	fprintf(fOut , "%d	" , rows);
	fprintf(fOut , "%d	" , cols);
	fprintf(fOut , "%d	" , winSize);
	fprintf(fOut, "\n");
	
	// prints new matrix to the out file
	for (i = 0; i < rows; i++)
	{
		for ( j = 0; j < cols; j++)
		{
			fprintf(fOut , "%f	" , newMat[i][j]);
		}
		fprintf(fOut,"\n");
	}

	// closing files
	fclose(fIn);
	fclose(fOut);
}