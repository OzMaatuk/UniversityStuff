#include "MyLinkedList.hpp"
#include "gtest/gtest.h"
#include <string>
using namespace std;  

TEST(MyLinkedList, emptyListTesters)
{
	MyLinkedList mylist;
	MyLinkedList mylist2;
	EXPECT_EQ(0, mylist.size());
	EXPECT_EQ(0, mylist2.size());
	EXPECT_EQ(mylist, mylist2);
}

TEST(MyLinkedList, addisInListArrConstreactorTesters)
{
	MyLinkedList mylist;
	string firstKey= "element1";
	double firstVal= 1.5;
	string secondKey = "element2";
	double secondVal = 2.2;

	mylist.add(firstKey, firstVal);

	EXPECT_EQ(1, mylist.size());

	EXPECT_TRUE(mylist.isInList(firstKey, firstVal));
	EXPECT_FALSE(mylist.isInList(firstKey, secondVal));
	EXPECT_FALSE(mylist.isInList(secondKey, firstVal));
	EXPECT_FALSE(mylist.isInList(secondKey, secondVal));
	
	string keysArr[]= {firstKey};
	double valsArr[]= {firstVal};
	MyLinkedList mylist2(keysArr, valsArr, 1);
	EXPECT_EQ(mylist2, mylist);

	mylist.add(secondKey, secondVal);
	EXPECT_TRUE(mylist.isInList(firstKey, firstVal));
	EXPECT_TRUE(mylist.isInList(secondKey, secondVal));
	EXPECT_FALSE(mylist2.isInList(secondKey, secondVal));
	EXPECT_NE(mylist2, mylist);
}

TEST(MyLinkedList, removeElementTester)
{
	MyLinkedList mylist;
	MyLinkedList mylist2;
	MyLinkedList mylist3(mylist2);
	EXPECT_EQ(0, mylist.size());
	EXPECT_EQ(0, mylist2.size());
	EXPECT_EQ(0, mylist3.size());
	
	EXPECT_EQ(mylist, mylist2);
	EXPECT_EQ(0,mylist.remove("Sagi"));
	EXPECT_NE(mylist.remove("Sagi"),2);

	EXPECT_EQ(0,mylist.sumList());
	EXPECT_NE(mylist.sumList(),1);
	EXPECT_EQ(mylist2.sumList(),mylist.sumList());
	EXPECT_EQ(mylist.remove("B"),mylist2.remove("K"));
	EXPECT_EQ(mylist.isInList("B",2),mylist2.isInList("R",3));
	
	mylist = mylist3;
	EXPECT_EQ(0, mylist.size());
	EXPECT_EQ(mylist,mylist3);
	EXPECT_EQ(mylist3,mylist);
}


TEST(MyLinkedList, addisInListArrConstreactorTesters2)
{
	MyLinkedList mylist;
	string firstKey= "element1";
	double firstVal= 1.5;
	string secondKey = "element2";
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
	
	MyLinkedList mylist3(mylist2);
	EXPECT_EQ(mylist2, mylist3);
	EXPECT_EQ(mylist2.sumList(), mylist3.sumList());
    EXPECT_EQ(mylist2.size(), mylist3.size());
    
    mylist2.add(secondKey,secondVal);
    EXPECT_NE(mylist2, mylist3);
	EXPECT_NE(mylist2.sumList(), mylist3.sumList());
    EXPECT_NE(mylist2.size(), mylist3.size());
	EXPECT_NE(mylist3,mylist);
    
    string keysArr2[]= {"shimiTavori","shimiTavori","someNode","YEAR","someNode"};
	double valsArr2[]= {1,2,2,3,1};
	
    MyLinkedList mylist4(keysArr2,valsArr2,5);
    EXPECT_EQ(5, mylist4.size());
    EXPECT_EQ(9, mylist4.sumList());
    EXPECT_FALSE(mylist4.isInList(firstKey, firstVal));
	EXPECT_FALSE(mylist4.isInList(secondKey, secondVal));
    mylist4.add(firstKey, firstVal);
    mylist4.add(secondKey, secondVal);
    EXPECT_EQ(7, mylist4.size());
    EXPECT_EQ(12.7, mylist4.sumList());
    
    EXPECT_TRUE(mylist4.isInList(firstKey, firstVal));
	EXPECT_TRUE(mylist4.isInList(secondKey, secondVal));
	EXPECT_TRUE(mylist4.isInList("shimiTavori",1));
    EXPECT_TRUE(mylist4.isInList("shimiTavori",2));
    EXPECT_FALSE(mylist4.isInList("YEAR",2)); 
    EXPECT_TRUE(mylist4.isInList("YEAR",3)); 
    
    EXPECT_EQ(2,mylist4.remove("shimiTavori"));
    EXPECT_EQ(0,mylist4.remove("shimiTavori"));
    EXPECT_EQ(5, mylist4.size());
    EXPECT_EQ(9.7,mylist4.sumList());
    EXPECT_FALSE(mylist4.isInList("shimiTavori",1));
    EXPECT_FALSE(mylist4.isInList("shimiTavori",2));
    
    mylist4.add("shimiTavori",1);
    mylist4.add("shimiTavori",2);
    
    EXPECT_EQ(7, mylist4.size());
    EXPECT_EQ(12.7, mylist4.sumList());
    EXPECT_TRUE(mylist4.isInList("shimiTavori",1));
    EXPECT_TRUE(mylist4.isInList("shimiTavori",2));
    EXPECT_EQ(mylist2.remove("element1"),mylist.remove("element1"));
    EXPECT_EQ(1,mylist2.size());
    EXPECT_EQ(1,mylist2.remove("element2"));
    EXPECT_EQ(0,mylist2.size());
    mylist2.add("element1",1.5);
    EXPECT_NE(mylist2, mylist);
    
    mylist2=mylist;
    EXPECT_EQ(mylist2,mylist);
    EXPECT_EQ(2,mylist4.remove("someNode"));
    EXPECT_EQ(2,mylist4.remove("shimiTavori"));
    EXPECT_EQ(3,mylist4.size());
}