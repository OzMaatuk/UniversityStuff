#include "Matrix.hpp"
#include "gtest/gtest.h"
#include "Rational.hpp"

TEST(matrix, matrix_test)
{
	Matrix<int> mat1(1, 1, std::vector<int>(1,2));

	EXPECT_EQ(1, mat1.getRowNum());
	EXPECT_EQ(1, mat1.getColNum());

	int t= 0;
	EXPECT_TRUE(mat1.hasTrace(t));
    EXPECT_TRUE(mat1.isSquareMatrix());
    
	Matrix<int> transpose_mat= mat1;
	EXPECT_EQ(transpose_mat, mat1.transpose());

	Matrix<int> mat2(1, 1, std::vector<int>(1,3));
    Matrix<int> add_mat(1, 1, std::vector<int> (1,5));
	EXPECT_EQ(add_mat, mat1+mat2);

	Matrix<int> multi_mat(1, 1, std::vector<int>(1,6));
	EXPECT_EQ(multi_mat, mat1*mat2);

	Matrix<int> scalar_multi_mat(1, 1, std::vector<int>(1,4));
	EXPECT_EQ(scalar_multi_mat, mat1*2);
}

TEST(matrix, matrix_test2)
{
    std::vector<int> myvector;
    int tempVal = 0;
    for(int j = 0; j<100; j++)
    {
        myvector.push_back(tempVal++);
    }
    
    Matrix<int> mat3(10,10,myvector);
	Matrix<int> mat1(1, 1, std::vector<int>(1,2));

	EXPECT_EQ(10,mat3.getRowNum());
	EXPECT_EQ(10,mat3.getColNum());
	EXPECT_EQ(1, mat1.getRowNum());
	EXPECT_EQ(1, mat1.getColNum());

	int t= 0;
	EXPECT_TRUE(mat1.hasTrace(t));
    EXPECT_TRUE(mat1.isSquareMatrix());
    
    t= 0;
	EXPECT_TRUE(mat3.hasTrace(t));
    EXPECT_TRUE(mat3.isSquareMatrix());
    EXPECT_EQ(495,t);
    
	Matrix<int> transpose_mat = mat3;

	EXPECT_NE(transpose_mat, mat3.transpose());
	t=0;
	EXPECT_TRUE(transpose_mat.hasTrace(t));
	EXPECT_EQ(495,t);
	Matrix<int> mat2(1, 1, std::vector<int>(1,3));
    Matrix<int> add_mat(1, 1, std::vector<int> (1,5));
	EXPECT_EQ(add_mat, mat1+mat2);

	Matrix<int> multi_mat(1, 1, std::vector<int>(1,6));
	EXPECT_EQ(multi_mat, mat1*mat2);

	Matrix<int> scalar_multi_mat(1, 1, std::vector<int>(1,4));
	EXPECT_EQ(scalar_multi_mat, mat1*2);
}


TEST(matrix, matrix_test3)
{
    std::vector<int> myvector;
   	int tempVal = 0;
   	for(size_t j = 0; j<100; j++)
   	{
       	myvector.push_back(tempVal++);
    }
    
    std::vector<int> myvector1;
    tempVal = 1;
    for(size_t j = 0; j<100; j++)
    {
      	myvector1.push_back(tempVal);
    }
    
    Matrix<int> mat3(10,10,myvector);
	Matrix<int> mat1(1, 1, std::vector<int>(1,2));
	Matrix<int> mat4(10,10,myvector1);

	EXPECT_EQ(10,mat3.getRowNum());
	EXPECT_EQ(10,mat3.getColNum());
	EXPECT_EQ(1, mat1.getRowNum());
	EXPECT_EQ(1, mat1.getColNum());

	int t=0;
	EXPECT_TRUE(mat1.hasTrace(t));
    EXPECT_TRUE(mat1.isSquareMatrix());
    
    t=0;
	EXPECT_TRUE(mat3.hasTrace(t));
    EXPECT_TRUE(mat3.isSquareMatrix());
    EXPECT_EQ(495,t);
    
	Matrix<int> transpose_mat = mat3;
	EXPECT_NE(transpose_mat, mat3.transpose());
	
	t=0;
	EXPECT_TRUE(transpose_mat.hasTrace(t));
	EXPECT_EQ(495,t);
	
	Matrix<int> mat2(1, 1, std::vector<int>(1,3));
    Matrix<int> add_mat(1, 1, std::vector<int> (1,5));
	EXPECT_EQ(add_mat, mat1+mat2);

	Matrix<int> multi_mat(1, 1, std::vector<int>(1,6));
	EXPECT_EQ(multi_mat, mat1*mat2);
	
	EXPECT_NE(mat3, mat3*mat4);

	Matrix<int> scalar_multi_mat(1, 1, std::vector<int>(1,4));
	EXPECT_EQ(scalar_multi_mat, mat1*2);
}


TEST(matrix, constructors_test)
{
	Matrix<int> mat1(4, 3, std::vector<int>{1,2,3,4,5,6,7,8,9,10,11,1});
	Matrix<int> mat2(mat1);
	
	EXPECT_EQ(mat1,mat2);
	EXPECT_TRUE(mat1==mat2);
	EXPECT_EQ(4, mat2.getRowNum());
	
	Matrix<int> mat3=Matrix<int>();
	EXPECT_EQ(1, mat3.getRowNum());
	EXPECT_EQ(1, mat3.getColNum());
	
	EXPECT_FALSE(mat3==mat2);
	EXPECT_FALSE(mat3==mat1);
	EXPECT_TRUE(mat3==mat3);
	
	mat3=mat1;
	EXPECT_TRUE(mat1==mat3);
	EXPECT_TRUE(mat3==mat2);
}
 
 
TEST(matrix, op_kefel_firstTest)
{
	Matrix<int> mat1(3, 2, std::vector<int>{1,2,3,4,5,6});
	Matrix<int> mat2(2, 3, std::vector<int>{1,2,3,4,5,6});

	Matrix<int> mat3(3, 3, std::vector<int>{9,12,15,19,26,33,29,40,51});

	EXPECT_EQ(3, (mat1*mat2).getRowNum());
	EXPECT_EQ(3, (mat1*mat2).getColNum());
	
	EXPECT_EQ(mat3, mat1*mat2);
}

TEST(matrix, op_plus_firstTest)
{
	Matrix<int> mat1(3, 3, std::vector<int>{1,2,3,4,5,6,7,8,9});
	Matrix<int> mat2(3, 3, std::vector<int>{9,8,7,6,5,4,3,2,1});

	Matrix<int> plusMat(3, 3, std::vector<int>(9,10));

	EXPECT_EQ(3, (mat1+mat2).getRowNum());
	EXPECT_EQ(3, (mat1+mat2).getColNum());

	EXPECT_EQ(plusMat, mat1+mat2);
	EXPECT_EQ(mat2+mat1, mat1+mat2);
}

TEST(matrix, isSquare_hasTrace_Rational)
{
	Matrix<Rational> mat1(2, 2, std::vector<Rational>(4,Rational("2/1")));
	EXPECT_EQ(2, mat1.getRowNum());
	EXPECT_EQ(2, mat1.getColNum());
	
	EXPECT_TRUE(mat1.isSquareMatrix());
	
	Rational trace;
	EXPECT_TRUE(mat1.hasTrace(trace));
	EXPECT_EQ(Rational(4),trace);
	
	Matrix<Rational> mat2(2, 1, std::vector<Rational>{Rational(10),Rational("10")});
	EXPECT_EQ(2, mat2.getRowNum());
	EXPECT_EQ(1, mat2.getColNum());
	
	EXPECT_FALSE(mat2.isSquareMatrix());
	
	EXPECT_FALSE(mat2.hasTrace(trace));
	EXPECT_EQ(Rational(0),trace);
}

TEST(matrix, isSquare_hasTrace)
{
	Matrix<int> mat1(3, 3, std::vector<int>{1,2,3,1,2,3,1,2,3});
	EXPECT_EQ(3, mat1.getRowNum());
	EXPECT_EQ(3, mat1.getColNum());
	
	EXPECT_TRUE(mat1.isSquareMatrix());
	
	int trace=0;
	EXPECT_TRUE(mat1.hasTrace(trace));
	EXPECT_EQ(6,trace);
	
	Matrix<int> mat2(2, 1, std::vector<int>{10,10});
	EXPECT_EQ(2, mat2.getRowNum());
	EXPECT_EQ(1, mat2.getColNum());
	
	EXPECT_FALSE(mat2.isSquareMatrix());
	
	trace=0;
	EXPECT_FALSE(mat2.hasTrace(trace));
	EXPECT_EQ(0,trace);
}

TEST(matrix, transpose_firstTest)
{
	Matrix<int> mat1(3, 3, std::vector<int>{1,2,3,1,2,3,1,2,3});
	
	EXPECT_TRUE(mat1.isSquareMatrix());
	
	int trace=0;
	EXPECT_TRUE(mat1.hasTrace(trace));
	EXPECT_EQ(6,trace);
	
	Matrix<int> mat2=mat1.transpose();
	
	EXPECT_EQ(3, mat2.getRowNum());
	EXPECT_EQ(3, mat2.getColNum());
	
	trace=0;
	EXPECT_TRUE(mat2.hasTrace(trace));
	EXPECT_EQ(6,trace);
	
	Matrix<int> mat3(3, 3, std::vector<int>{1,1,1,2,2,2,3,3,3});
	EXPECT_EQ(mat2, mat3);
	
	EXPECT_EQ(mat1, mat2.transpose());
}

TEST(matrix, transpose_secondTest)
{
	Matrix<int> mat1(3, 2, std::vector<int>{1,2,3,4,5,6});
	EXPECT_EQ(3, mat1.getRowNum());
	EXPECT_EQ(2, mat1.getColNum());
	
	EXPECT_FALSE(mat1.isSquareMatrix());
	
	int trace=0;
	EXPECT_FALSE(mat1.hasTrace(trace));
	EXPECT_EQ(0,trace);
	
	Matrix<int> mat2=mat1.transpose();
	
	EXPECT_EQ(2, mat2.getRowNum());
	EXPECT_EQ(3, mat2.getColNum());
	// trace=0;
	EXPECT_FALSE(mat2.hasTrace(trace));
	EXPECT_EQ(0,trace);
	
	Matrix<int> mat3(2, 3, std::vector<int>{1,3,5,2,4,6});
	EXPECT_EQ(mat2, mat3);
	
	EXPECT_EQ(mat1, mat2.transpose());
}

TEST(matrix, op_kefel_secondTest)
{
	Matrix<double> doubleMat1(2,4,std::vector<double>(8,10));
	Matrix<double> doubleMat2(4,2,std::vector<double>(8,2.5));
	
	Matrix<double> kefel1(2, 2, std::vector<double>(4,100));
	Matrix<double> kefel2(4, 4, std::vector<double>(16,50));
	
	EXPECT_EQ(kefel1,(doubleMat1*doubleMat2));
	EXPECT_EQ(2, (doubleMat1*doubleMat2).getRowNum());
	EXPECT_EQ(2, (doubleMat1*doubleMat2).getColNum());
	EXPECT_EQ(kefel2, doubleMat2*doubleMat1);
	
	EXPECT_EQ(4, (doubleMat2*doubleMat1).getRowNum());
	EXPECT_EQ(4, (doubleMat2*doubleMat1).getColNum());
	
	EXPECT_NE(kefel2, kefel1);
}

TEST(matrix, op_kefel_scalar1)
{
	Matrix<int> mat1(3, 2, std::vector<int>{1,2,3,4,5,6});
	Matrix<int> kefel(3, 2, std::vector<int>{2,4,6,8,10,12});

	EXPECT_EQ(3, (mat1*2).getRowNum());
	EXPECT_EQ(2, (mat1*2).getColNum());
	
	EXPECT_EQ(3, (mat1*4).getRowNum());
	EXPECT_EQ(2, (mat1*6).getColNum());
	
	EXPECT_EQ(kefel, mat1*2);
	EXPECT_EQ(mat1, mat1*1);
}


TEST(matrix, op_kefel_scalar2)
{
	Matrix<double> doubleMat1(4,2,std::vector<double>(8,3.0));
	Matrix<double> kefel(4, 2, std::vector<double>(8,9.0));
	EXPECT_EQ(4, (doubleMat1*3).getRowNum());
	EXPECT_EQ(2, (doubleMat1*3).getColNum());
	EXPECT_EQ(4, (doubleMat1*4).getRowNum());
	EXPECT_EQ(2, (doubleMat1*6).getColNum());
	EXPECT_EQ(kefel, doubleMat1*3);
	EXPECT_EQ(doubleMat1, doubleMat1*1);
}

//Rational test
TEST(matrix, transpose_Rational)
{
	Matrix<Rational> mat1(2, 2, std::vector<Rational>{Rational("1/1"),Rational(2),Rational(3,1),Rational("-4/-1")});
	EXPECT_EQ(2, mat1.getRowNum());
	EXPECT_EQ(2, mat1.getColNum());
	
	EXPECT_TRUE(mat1.isSquareMatrix());
	
	Rational trace;
	EXPECT_TRUE(mat1.hasTrace(trace));
	EXPECT_EQ(Rational(5),trace);
	
	Matrix<Rational> mat2=mat1.transpose();
	
	EXPECT_EQ(2, mat2.getRowNum());
	EXPECT_EQ(2, mat2.getColNum());
	
	EXPECT_TRUE(mat2.hasTrace(trace));
	EXPECT_EQ(Rational(5),trace);
	
	Matrix<Rational> mat3(2, 2, std::vector<Rational>{Rational(1),Rational(3),Rational(2),Rational(4)});
	EXPECT_EQ(mat2, mat3);
	
	EXPECT_EQ(mat1, mat2.transpose());
}

TEST(matrix, constructors_test_Rational_hastrase)
{
	Matrix<Rational> mat1(2, 2, std::vector<Rational>(4,Rational(2,3)));
	Matrix<Rational> mat2(mat1);
	
	Matrix<Rational> mat5(2, 2, std::vector<Rational>(4,Rational(4,3)));
	EXPECT_EQ(mat5,mat1+mat1);
	
	EXPECT_EQ(mat5,mat1*2);
	
	Matrix<Rational> mat6(2, 2, std::vector<Rational>(4,Rational(8,9)));
	EXPECT_EQ(mat6,mat1*mat1);
	Matrix<Rational> mat7(mat1*mat1);
	EXPECT_EQ(mat1,mat2);
	EXPECT_TRUE(mat1==mat2);
	EXPECT_EQ(2, mat2.getRowNum());
	
	Rational t= Rational(1,1);
	EXPECT_TRUE(mat1.hasTrace(t));
	EXPECT_EQ(Rational(4,3),t);
	
	Matrix<Rational> mat3=Matrix<Rational>();
	EXPECT_EQ(1, mat3.getRowNum());
	EXPECT_EQ(1, mat3.getColNum());
	
	EXPECT_FALSE(mat3==mat2);
	EXPECT_FALSE(mat3==mat1);
	EXPECT_TRUE(mat3==mat3);
	
	mat3=mat1;
	EXPECT_TRUE(mat1==mat3);
	EXPECT_TRUE(mat3==mat2);
}

TEST(matrix, op_kefel_Rational)
{
	Matrix<Rational> mat1(1, 3, std::vector<Rational>{Rational(1),Rational(2),Rational(3)});
	Matrix<Rational> mat2(3, 1, std::vector<Rational>{Rational(1),Rational(2),Rational(3)});

	Matrix<Rational> mat3(1,1, std::vector<Rational>{Rational(14)});

	EXPECT_EQ(1, (mat1*mat2).getRowNum());
	EXPECT_EQ(1, (mat1*mat2).getColNum());
	
	EXPECT_EQ(mat3, mat1*mat2);
	
	Matrix<Rational> mat4(3,3, std::vector<Rational>{Rational(1),Rational(2),Rational(3),Rational(2),Rational(4),Rational(6),Rational(3),Rational(6),Rational(9)});

	EXPECT_EQ(3, (mat2*mat1).getRowNum());
	EXPECT_EQ(3, (mat2*mat1).getColNum());
	
	EXPECT_EQ(mat4, mat2*mat1);	
}

TEST(matrix, op_kefel_scalar_Rational)
{
	Matrix<Rational> mat1(2, 3, std::vector<Rational>{Rational(1),Rational(2),Rational(3),Rational(1),Rational(2),Rational(3)});
	Matrix<Rational> mat2(2, 1, std::vector<Rational>{Rational(1,1),Rational("2/1")});
	Matrix<Rational> kefel1(2,3, std::vector<Rational>{Rational(4),Rational(8),Rational(12),Rational(4),Rational(8),Rational(12)});
	Matrix<Rational> kefel2(2,1, std::vector<Rational>{Rational(3,2),Rational(6,2)});
	Matrix<Rational> kefel3(2,1,std::vector<Rational>(2,Rational()));
	EXPECT_EQ(kefel1, mat1*Rational(4));
	EXPECT_EQ(kefel2, mat2*Rational(3,2));
	EXPECT_EQ(kefel3, mat2*Rational(0));
	EXPECT_EQ(mat2, mat2*Rational(1));
}