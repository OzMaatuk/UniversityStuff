#include "Rational.hpp"
#include "gtest/gtest.h"

TEST(rational, rational_tests1)
{
    Rational r1(2,4);
    Rational r2(1,1);
       
    EXPECT_EQ(1, r1.getNumerator());
	EXPECT_EQ(2, r1.getDenominator());
	EXPECT_EQ(Rational(3,2), r1+r2);
	EXPECT_EQ(Rational(1,2), r1*r2);
}

TEST (rational,rational_tests2) 
{
    Rational r1(2,4);
    Rational r2(1,1);
    Rational r3(3,5);
    Rational r4(2);
    Rational r5("1/2");
    
    EXPECT_EQ(1, r1.getNumerator());
	EXPECT_EQ(2, r1.getDenominator());
	EXPECT_NE(1,r1.getDenominator());
	EXPECT_EQ(Rational(21,10), r1+r2+r3);
	EXPECT_EQ(Rational(1,2), r1*r2);
	EXPECT_EQ(2, r4.getNumerator());
	EXPECT_EQ(1, r4.getDenominator());
	EXPECT_EQ(1, r5.getNumerator());
	EXPECT_EQ(2, r5.getDenominator());
	EXPECT_TRUE(r1.getNumerator() ==r2.getNumerator());
	EXPECT_TRUE(r3.getNumerator() !=r2.getNumerator());
	EXPECT_FALSE(r1.getNumerator() !=r2.getNumerator());
	EXPECT_FALSE(r3.getNumerator() ==r2.getNumerator());
}

TEST (rational,rational_tests3) 
{
    Rational r1(5,4);
    Rational r2(0,1);
    Rational r3(0,5);
    Rational r4(2);
    Rational r5("888/2");
    
    EXPECT_EQ(5, r1.getNumerator());
	EXPECT_EQ(4, r1.getDenominator());
	EXPECT_NE(1,r1.getDenominator());
	EXPECT_NE(Rational(21,10), r1+r2+r3);
	EXPECT_NE(Rational(1,2), r1*r2);
	EXPECT_EQ(2, r4.getNumerator());
	EXPECT_EQ(1, r4.getDenominator());
	EXPECT_EQ(444, r5.getNumerator());
	EXPECT_EQ(1, r5.getDenominator());
	EXPECT_TRUE(r1.getNumerator() !=r2.getNumerator());
	EXPECT_TRUE(r3.getNumerator() ==r2.getNumerator());
	EXPECT_FALSE(r1.getNumerator() ==r2.getNumerator());
	EXPECT_FALSE(r3.getNumerator() !=r2.getNumerator());
}

TEST (rational,rational_tests5) 
{
    Rational r1(1,4);
    Rational r2(1,1);
    Rational r3(3,5);
    Rational r4(2);
    Rational r5("7576567/1");
    Rational r6(5,4);
    Rational r7(3,20);
    EXPECT_EQ(r6,r1+r2);
    EXPECT_EQ(r7,r3*r1);
    EXPECT_EQ(1, r1.getNumerator());
	EXPECT_EQ(4, r1.getDenominator());
	EXPECT_NE(1,r1.getDenominator());
	EXPECT_NE(Rational(21,10), r1+r2+r3);
	EXPECT_NE(Rational(1,2), r1*r2);
	EXPECT_EQ(2, r4.getNumerator());
	EXPECT_EQ(1, r4.getDenominator());
	EXPECT_EQ(7576567, r5.getNumerator());
	EXPECT_EQ(1, r5.getDenominator());
	EXPECT_TRUE(r1.getNumerator() ==r2.getNumerator());
	EXPECT_TRUE(r3.getNumerator() !=r2.getNumerator());
	EXPECT_TRUE(r1.getNumerator() ==r2.getNumerator());
	EXPECT_FALSE(r3.getNumerator() ==r2.getNumerator());
}

TEST (rational,rational_tests6)
{
    Rational r1(4897,675);
    Rational r2(1,1);
    Rational r3(1853,675);
    Rational r4(2);
    Rational r5("0/89708");
    
    EXPECT_EQ(4897, r1.getNumerator());
	EXPECT_EQ(675, r1.getDenominator());
	EXPECT_NE(1,r1.getDenominator());
	EXPECT_EQ(Rational(5572,675), r1+r2);
	EXPECT_EQ(Rational(4897,675), r1*r2);
	EXPECT_EQ(2, r4.getNumerator());
	EXPECT_EQ(1, r4.getDenominator());
	EXPECT_EQ(0, r5.getNumerator());
	EXPECT_EQ(1, r5.getDenominator());
	EXPECT_TRUE(r1.getNumerator() !=r2.getNumerator());
	EXPECT_TRUE(r3.getNumerator() !=r2.getNumerator());
	EXPECT_TRUE(r1.getNumerator() !=r2.getNumerator());
	EXPECT_FALSE(r3.getNumerator() ==r2.getNumerator());
}

TEST(Constructors, String)
{
    Rational r1("5/1");
	EXPECT_EQ(5, r1.getNumerator());
	EXPECT_EQ(1, r1.getDenominator());
    
    Rational r2("0/1000");
	EXPECT_EQ(0, r2.getNumerator());
	EXPECT_EQ(1, r2.getDenominator());
    
    Rational r3("-100/-80");
	EXPECT_EQ(5, r3.getNumerator());
	EXPECT_EQ(4, r3.getDenominator());
    
    Rational r4("100/1000");
	EXPECT_EQ(1, r4.getNumerator());
	EXPECT_EQ(10,r4.getDenominator());
	
	EXPECT_EQ(Rational(5,1), r1+r2);
}

TEST(equals , NOTequal )
{
    Rational r1(3,-5);
    Rational r2(-3,5);
    Rational r3(10,-4);
    Rational r4(20,-8);
    EXPECT_TRUE(r1 == r2);
    EXPECT_TRUE(r1 != r3);
    EXPECT_FALSE(r1 == r4);
    Rational r5(0,1);
    EXPECT_EQ(0, r5.getNumerator());
	EXPECT_EQ(1, r5.getDenominator());
	EXPECT_EQ(-3, r2.getNumerator());
	EXPECT_EQ(5, r2.getDenominator());

    EXPECT_TRUE( r5 != r2);
}

TEST(constructorTwoInts ,constructorTwoIntsTest)
{
    Rational r1(-1,-5);
    EXPECT_EQ(1,r1.getNumerator());
    EXPECT_EQ(5,r1.getDenominator());
    
    Rational r2(1,-5);
    EXPECT_EQ(-1,r2.getNumerator());
    EXPECT_EQ(5,r2.getDenominator());
    
    Rational r22(6,3);
    EXPECT_EQ(2,r22.getNumerator());
    EXPECT_EQ(1,r22.getDenominator());
    
    Rational r23(6,-3);
    EXPECT_EQ(-2,r23.getNumerator());
    EXPECT_EQ(1,r23.getDenominator());
    
    Rational r33(-6,18);
    EXPECT_EQ(-1,r33.getNumerator());
    EXPECT_EQ(3,r33.getDenominator());
    
    Rational r34(-6,-18);
    EXPECT_EQ(1,r34.getNumerator());
    EXPECT_EQ(3,r34.getDenominator());
    
}

TEST(constructorString ,constructorStringTest)
{
    Rational r3("1/5");
    EXPECT_EQ(1,r3.getNumerator());
    EXPECT_EQ(5,r3.getDenominator());
    
    Rational r4("1/-5");
    EXPECT_EQ(-1,r4.getNumerator());
    EXPECT_EQ(5,r4.getDenominator());
    
    Rational r5("6/3");
    EXPECT_EQ(2,r5.getNumerator());
    EXPECT_EQ(1,r5.getDenominator());
    
    Rational r6("6/-3");
    EXPECT_EQ(-2,r6.getNumerator());
    EXPECT_EQ(1,r6.getDenominator());
    
    Rational r33("-6/18");
    EXPECT_EQ(-1,r33.getNumerator());
    EXPECT_EQ(3,r33.getDenominator());
    
    Rational r34("-6/-18");
    EXPECT_EQ(1,r34.getNumerator());
    EXPECT_EQ(3,r34.getDenominator());
    
}

TEST (equal, isEQTest)
{
     Rational r1("-1/6");
     Rational r2(-1, 6);
     EXPECT_TRUE (r1==r2); 
     
     Rational r3("-1/6");
     Rational r4(1, -6);
     EXPECT_TRUE (r3==r4); 
     
     Rational r12("12/6");
     Rational r42(4, 2);
     EXPECT_TRUE (r12==r42); 
     
     Rational r33("-33/3");
     Rational r44(44, -4);
     EXPECT_TRUE (r33==r44);
     
     Rational r50(50,1);
     Rational r0(100, 2);
     EXPECT_TRUE (r50==r0); 
     
     Rational r50s("50/1");
     Rational r0s("100/ 2");
     EXPECT_TRUE (r50s==r0s);
     EXPECT_TRUE (r50s==r0s && r50==r50s);
     
     Rational r333("-34364563/443");
     Rational r444(34364563, -443);
     EXPECT_TRUE (r333==r444);
     
 }
 
TEST(nonequal, isntEQTest)
{
     Rational r1("-1/6");
     Rational r2(-1, 6);
     EXPECT_FALSE (r1!=r2); 
     
     Rational r3("-1/6");
     Rational r4(1, -6);
     EXPECT_FALSE (r3!=r4); 
     
     Rational r12("12/6");
     Rational r42(4, 2);
     EXPECT_FALSE (r12!=r42); 
     
     Rational r33("-33/3");
     Rational r44(44, -4);
     EXPECT_FALSE (r33!=r44);
     
     Rational r50(50,1);
     Rational r0(100, 2);
     EXPECT_FALSE (r50!=r0); 
     
     Rational r50s("50/1");
     Rational r0s("100/ 2");
     EXPECT_FALSE (r50s!=r0s);
     EXPECT_FALSE (r50s!=r0s && r50!=r50s);
 }
 
TEST(multyAdd, multyAddTest)
{
    Rational r1(2,4);
    Rational r2(1,1);
       
	EXPECT_EQ(Rational(3,2), r1+r2);
	EXPECT_EQ(Rational(1,2), r1*r2);
	
 	Rational r3(7,9);
    Rational r4(6,9);
 	
 	EXPECT_EQ(Rational(14,27), r3*r4);
 	EXPECT_EQ(Rational(13,9), r3+r4);
	
 	Rational r5(3,4);
    Rational r6(7,8);
 	EXPECT_EQ(Rational(21,32), r5*r6);
 	EXPECT_EQ(Rational(13,8), r5+r6);
	
 	Rational r7(13,15);
    Rational r8(7,8);
 	EXPECT_EQ(Rational(209,120), r7+r8);
 	EXPECT_EQ(Rational(91,120), r7*r8);
 	
 	Rational r17("133/1225");
    Rational r18(141,12225);
 	EXPECT_EQ(Rational(3426,28525), r17+r18);
 	EXPECT_EQ(Rational("893/713125"), r17*r18);
}
 
TEST(rational, rational_tests22)
{
    Rational r3(2,4);
    Rational r4(4,5);
    Rational r5(4,8);
    ASSERT_EQ(r3, r5);
    ASSERT_NE(r3, r4);
    ASSERT_NE(r4, r5);
    
}