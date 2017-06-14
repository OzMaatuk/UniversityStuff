#include "myassert.hpp"
#include <vector>
#include <cstdlib>
#include <cmath>

template <class T> class Matrix
{
    
    public:
    
	// empty constractor: build 1X1 matrix for T var-type
    Matrix()
    {
		row=MIN_LENGTH;
		col=MIN_LENGTH;
		size_t i,j;
		// resize mat - resize function from vector
		mat.resize(MIN_LENGTH);
		
		// sets default value for the element
		for(i = 0; i < row; i++)
		{
			for(j = 0; j < col; j++)
			{
				// push back - vector func
				mat[i].push_back( T() );
			}
		}
    }
    
    // constractor: gets number of cols ,rows and values to put in.
    Matrix(size_t rows,size_t cols, std::vector<T> values)
    {
		// assert check rows-cols validation and save it.
        myassert(rows >= MIN_LENGTH);
        myassert(cols >= MIN_LENGTH);
        
        // set row and col
        row = rows;
        col = cols;
        
		// resize mat, and puts values
		size_t i,j,buff;
        mat.resize(rows);
        
        buff = 0;
        for(i = 0; i < rows; i++)
		{
            for(j = 0; j < cols; j++)
			{
				// uses the vector put_back func
				if (buff < values.size())
				{
                	mat[i].push_back(values.at(i*cols+j));
				}
				else
				{
					mat[i].push_back(T());
				}
				buff++;
			}
		}
    }
    
	// composite function for matrix
    Matrix<T> operator+(const Matrix<T>& other) const
    {
		// assert check for not null, and same matrixs length
		myassert(&other!=nullptr);
		myassert(getColNum()==other.getColNum());
		myassert(getRowNum()==other.getRowNum());

		// the new matrix for answare
		Matrix<T> newMat(row, col, std::vector<T>(row*col, T()));

		// for every element, calc the composite 
		size_t i,j;
		for(i = 0; i < getRowNum(); i++)
		{
			for(j = 0; j < getColNum(); j++)
			{
				// save in newMatrix
				newMat.mat[i][j] = mat[i][j]+other.mat[i][j];
			}
		}
			
		return newMat;
    }
    
    // protuct function for matrixs
    Matrix<T> operator*(const Matrix<T> &other) const
    {
		// assert check for not null, and product validation
		myassert(&other!=nullptr);
		myassert(getColNum()==other.getRowNum());

		// new matrix for answare
		Matrix<T> newMat(getRowNum(),other.getColNum(), std::vector<T>((getRowNum())*(other.getColNum()), T()));

		// for every element, calc the product
		size_t i,j,k;
		for(i = 0; i < getRowNum(); i++)
		{
			for(j=0;j<other.getColNum();j++)
			{
				// init the parameter type
				newMat.mat[i][j] = T();
				
				// make a product of this row with all the other col
				for(k=0;k<col;k++)
				{
					// calc row * col, and save the product
					newMat.mat[i][j] = newMat.mat[i][j]+(mat[i][k]*other.mat[k][j]);
				}
			}
		}

		return newMat;
    }
    
    // scalar protuct function for matrix
    Matrix operator*(const T& other) const
    {
		// assert check for not null
		myassert(&other!=nullptr);
		
		// new matrix for answare
        Matrix<T> newMat(row, col, std::vector<T>(row*col, T()));
		
		// for every element in matrix
		size_t i, j;
        for(i = 0; i < getRowNum(); i++)
		{
            for(j = 0; j < getColNum(); j++)
			{
				// save the scalar product
                newMat.mat[i][j]=mat[i][j]*other;
			}
		}
            
        return newMat;    
    }
   
	// transpose matrix function
    Matrix<T> transpose() const
    {
		// new matrix for answare
		Matrix newMat(col, row, std::vector<T>(col*row, T()));

		// for every element
		size_t i, j;
		for(i = 0; i < row; i++)
		{
			for(j = 0; j < col; j++)
			{
				// put the tranponsed element
				newMat.mat[j][i]=mat[i][j];
			}
		}
				
		return newMat;
    }
	
	// return false if its not a square matix, else return true and set other to the trace value
    bool hasTrace (T &other) const
    {
		// init other
		other=T();

		// if its a square matrix
		if(isSquareMatrix())
		{
			size_t i;
			// for every element in the diagonal
			for(i = 0; i < this->getRowNum(); i++)
			{
			   // calc the trace value (sum the alahson)
			   other=other+this->mat[i][i];
			}
			return true;
		}
		// if its not a square matrix
		else
		{
			// init other to 0
			other=0;
			return false;
		}
    }
    
	// equation implemention (== operator function for matrixs)
    bool operator==(const Matrix<T> &other) const
    {
        // if not the same length
        if((&other == nullptr) || (col != other.col) || (getRowNum() != other.getRowNum()))
		{
            return false;
        }
        
		// for every element is matrixs
        size_t i,j;
        for(i = 0; i < other.getRowNum(); i++)
		{
            for (j = 0; j < other.getColNum(); j++)
			{
				// check equation
                if(mat[i][j] != other.mat[i][j])
				{
					// if not equal
                    return false;
				}
			}
		}
		
        return true;
    }
    
    // // not equal implemention (!= operator function for matrixs)
    bool operator!=(const Matrix<T> &other) const
    {
		// uses == operator function for matrixs)
        if(this->operator == (other))
		{
			// if equals
            return false;
		}
        else
		{
			// if not equals
            return true;
		}
    }
    
    // check if the matrix is square, ture if does, false if not
    bool isSquareMatrix() const
    {
		// if cols number same as rows number
        if(this->getColNum() == this->getRowNum())
		{
            return true;
		}
        else
		{
            return false;
		}
    }
    
	// gets the number of rows
    size_t getRowNum() const
    {
        return row;
    }
    
	// gets the number of cols
    size_t getColNum() const
    {
        return col;
    }
    
    // gets the mat
    std::vector<std::vector<T>> getMat() const
    {
        return mat;
    }
    
    private:
    
	// matrix implemention as cpp vectors
    std::vector<std::vector<T>> mat;
    size_t row;
    size_t col;
    static const size_t MIN_LENGTH=1;
};