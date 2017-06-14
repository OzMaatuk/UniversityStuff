#include "MyLinkedList.hpp"
#include "gtest/gtest.h"
#include <string>
using namespace std;

// remark: all testers rely on parameterless constructor, operator== or/and operator!=, size

TEST(MyLinkedList, emptyListTesters) {

	MyLinkedList mylist;
	MyLinkedList mylist2;
	EXPECT_EQ(0, mylist.size());
	EXPECT_EQ(0, mylist2.size());
	EXPECT_EQ(mylist, mylist2);
	
}

TEST(MyLinkedList, addIsInListArrCtorTesters) {

	MyLinkedList mylist;
	string firstKey= "aa";
	double firstVal= 1.5;
	string secondKey = "bb";
	double secondVal = 2.2;

	mylist.add(firstKey, firstVal);

	EXPECT_EQ(1, mylist.size());

	EXPECT_TRUE(mylist.isInList(firstKey, firstVal));
	EXPECT_FALSE(mylist.isInList(firstKey, secondVal));
	EXPECT_FALSE(mylist.isInList(secondKey, firstVal));
	EXPECT_FALSE(mylist.isInList(secondKey, secondVal));
	
	string keysArr[]= {firstKey};
	double valsArr[]= {firstVal};
	MyLinkedList mylist2( keysArr, valsArr, 1);
	EXPECT_EQ(mylist2, mylist);

	mylist.add(secondKey, secondVal);
	EXPECT_TRUE(mylist.isInList(firstKey, firstVal));
	EXPECT_TRUE(mylist.isInList(secondKey, secondVal));
	EXPECT_FALSE(mylist2.isInList(secondKey, secondVal));
	EXPECT_NE(mylist2, mylist);
	
}

TEST(MyLinkedList, privateTest1) {
	// to test private methods of MyLinkedList here, you'll need
	// to add to the MyLinkedList this line:
	//  FRIEND_TEST(MyLinkedList, privateTest1);
	// This is not a requirement (you can get 100 without it)
}
		
// add more tests here!
