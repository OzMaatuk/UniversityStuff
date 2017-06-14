extern "C" {
    #include "MeanWindow.h"
	#include "MeanWindowHelperFunctions.h"
}
#include "gtest/gtest.h"
#include <iostream>
#include <cstdlib>
#include <string>
#include <sstream>

TEST(MeanWindow, FirstTest) {
	std::string TestInFile = "Examples/MeanWindow.in.1";              // given Input example
	std::string TestOutFile = "Examples/MeanWindow.out.1";            // givem Output example for comppersion
	std::string TestOutDest = "testMean.out";                            // output for the test
    MeanWindow(TestInFile.c_str(), TestOutDest.c_str());              // Call for the LinSeperator function with the inut and output file
    std::string diffCMD = "diff -w " + TestOutFile + " " + TestOutDest;
	int result = std::system(diffCMD.c_str());                          // should return 0 on success. 1 for difference
    ASSERT_EQ(0, result);
}

TEST(MeanWindow, SecondTest) {
	std::string TestInFile = "Examples/MeanWindow.in.2";              // given Input example
	std::string TestOutFile = "Examples/MeanWindow.out.2";            // givem Output example for comppersion
	std::string TestOutDest = "testMean2.out";                            // output for the test
    MeanWindow(TestInFile.c_str(), TestOutDest.c_str());              // Call for the LinSeperator function with the inut and output file
    std::string diffCMD = "diff -w " + TestOutFile + " " + TestOutDest;
	int result = std::system(diffCMD.c_str());                          // should return 0 on success. 1 for difference
    ASSERT_EQ(0, result);
}

TEST(MeanWindow, ThirdTest) {
	std::string TestInFile = "Examples/MeanWindow.in.5";              // given Input example
	std::string TestOutFile = "Examples/MeanWindow.out.5";            // givem Output example for comppersion
	std::string TestOutDest = "testMean3.out";                            // output for the test
    MeanWindow(TestInFile.c_str(), TestOutDest.c_str());              // Call for the LinSeperator function with the inut and output file
    std::string diffCMD = "diff -w " + TestOutFile + " " + TestOutDest;
	int result = std::system(diffCMD.c_str());                          // should return 0 on success. 1 for difference
    ASSERT_EQ(0, result);
}

TEST(MeanWindow, FourTest) {
	std::string TestInFile = "Examples/MeanWindow.in.6";              // given Input example
	std::string TestOutFile = "Examples/MeanWindow.out.6";            // givem Output example for comppersion
	std::string TestOutDest = "testMean4.out";                            // output for the test
    MeanWindow(TestInFile.c_str(), TestOutDest.c_str());              // Call for the LinSeperator function with the inut and output file
    std::string diffCMD = "diff -w " + TestOutFile + " " + TestOutDest;
	int result = std::system(diffCMD.c_str());                          // should return 0 on success. 1 for difference
    ASSERT_EQ(0, result);
}

TEST(MeanWindow, FiveTest) {
	std::string TestInFile = "Examples/MeanWindow.in.8";              // given Input example
	std::string TestOutFile = "Examples/MeanWindow.out.8";            // givem Output example for comppersion
	std::string TestOutDest = "testMean5.out";                            // output for the test
    MeanWindow(TestInFile.c_str(), TestOutDest.c_str());              // Call for the LinSeperator function with the inut and output file
    std::string diffCMD = "diff -w " + TestOutFile + " " + TestOutDest;
	int result = std::system(diffCMD.c_str());                          // should return 0 on success. 1 for difference
    ASSERT_EQ(0, result);
}