extern "C"{
#include "MyString.h"
}
#include "gtest/gtest.h"

#define TRUE 1
#define FALSE 0
#define NOTFOUND -1
#define OFFSET 1

TEST(strLen,strLen_test)
{
    const char * str = "";
    int val = strLen(str);
    EXPECT_EQ(0,val);
    str = "a";
    val = strLen(str);
    EXPECT_EQ(1,val);
    str = "abc";
    val = strLen(str);
    EXPECT_EQ(3,val);
}


TEST(replaceDelim,replaceDelim_test)
{
    const char * delim=",.*";
    char str[]="aaa*test,hello.world*abcd.zzz";
    char ans[]="aaa;test;hello;world;abcd;zzz";
    replaceDelim(str, delim);
    EXPECT_STREQ(ans,str);
    char str1[]="";
    char ans1[]="";
    replaceDelim(str1, delim);
    EXPECT_STREQ(ans1,str1);
    char str2[]="a*,b;c";
    char ans2[]="a;;b;c";
    replaceDelim(str2, delim);
    EXPECT_STREQ(ans2,str2);
    const char * delim3 = "a&^,";
    char str3[]="rfay;ti^de,";
    char ans3[]="rf;y;ti;de;";
    replaceDelim(str3, delim3);
    EXPECT_STREQ(str3,ans3);
}

TEST(swap,swap_test)
{
    char str[] = "abc";
    swap(str,0,2);
    EXPECT_STREQ("cba",str);
    char str1[] = "abc";
    swap(str1,2,2);
    EXPECT_STREQ("abc",str1);
    char str2[] = "aabbcc";
    swap(str2,1,4);
    EXPECT_STREQ("acbbac",str2);
}

TEST(wordLen,wordLen_test)
{
    char str[] = "aaa;test;hello;world;abcd;zzz";
    EXPECT_EQ(3,wordLen(str, 0));
    char str1[] = "aaa;test;hello;world;abcd;zzz";
    EXPECT_EQ(4,wordLen(str1, 4));
    char str2[] = "aaa;test;hello;world;abcd;zzz";
    EXPECT_EQ(5,wordLen(str2, 9));
}

TEST(nextWord,nextWord_test)
{
    char str[] = "aaa;test;hello;world;abcd;zzz";
    EXPECT_EQ(4 ,nextWord(str, 0));
    char str1[] = "aaa;test;hello;world;abcd;zzz";
    EXPECT_EQ(9 ,nextWord(str1, 5));
    char str2[] = "aaa;test;hello;world;abcd;zzz";
    EXPECT_EQ(15 ,nextWord(str2, 9));
}

TEST(swapWords,swapWords_test)
{
    char str[] = "aaa;test;hello;world;abcd;zzz";
    swapWords(str, 0);
    EXPECT_STREQ("test;aaa;hello;world;abcd;zzz",str);
    char str1[] = "aaa;test;hello;world;abcd;zzz";
    swapWords(str1, 4);
    EXPECT_STREQ("aaa;hello;test;world;abcd;zzz",str1);
    char str2[] = "aaa;test;hello;world;abcd;zzz";
    swapWords(str2, 9);
    EXPECT_STREQ("aaa;test;world;hello;abcd;zzz",str2);
}

TEST(whosBigger,whosBigger_test)
{
    char str[] = "aaa;bbb";
    EXPECT_EQ(TRUE ,whosBigger(str, 0));
    char str1[] = "bbb;aaa";
    EXPECT_EQ(FALSE ,whosBigger(str1, 0));
    char str2[] = "aaa;bbb;ccc";
    EXPECT_EQ(TRUE ,whosBigger(str2, 4));
    char str3[] = "aaa;ccc;bbb";
    EXPECT_EQ(FALSE ,whosBigger(str3, 4));
}


TEST(extendedSubStr,extendedSubStr_test)
{
  const char * str1="abc";
  const char * str2="abca";
  const char * str3="";
  const char * str4="xyz";
  const char * str5="0123abcabcabc";
  const char * str6="abcabca";
  const char * str7="abcdexyaghz";
  const char * str8="0a1b2c3";
  int step1=1;
  int step2=2;
  int not_cyclic=0;
  int cyclic=1;
  
  EXPECT_EQ(NOTFOUND, extendedSubStr(0,1,"abc","abca"));
  ASSERT_EQ(-1,extendedSubStr(not_cyclic,step1,str1,str2));
  ASSERT_EQ(0,extendedSubStr(cyclic,step1,str1,str2));
  ASSERT_EQ(-1,extendedSubStr(not_cyclic,step1,str3,str4));
  ASSERT_EQ(-1,extendedSubStr(not_cyclic,step1,str4,str3));
  ASSERT_EQ(4,extendedSubStr(not_cyclic,step1,str5,str1));
  ASSERT_EQ(0,extendedSubStr(cyclic,step1,str6,str1));
  ASSERT_EQ(-1,extendedSubStr(not_cyclic,step1,str7,str4));
  ASSERT_EQ(1,extendedSubStr(not_cyclic,step2,str8,str1));
  EXPECT_EQ(3, extendedSubStr(0, 3, "t3e2s1tcba", "2"));
}

TEST(sortDelim,sortDelim_test)
{
  
    const char * delim=",.*";
    char str1[]="aaa*test,hello.world*abcd.zzz";
    char str2[]="test,riku.world.zzz.kuku";
    char str3[]="aa*cc,bb.ee*dd.ff";
    char str4[]="aaa*aa,a.wor*ab.wor";
    char str5[] ="zbc*g,hello.world*ad.hello1,123";
    char check_str1[]="aaa;abcd;hello;test;world;zzz";
    char check_str2[]="kuku;riku;test;world;zzz";
    char check_str3[]="aa;bb;cc;dd;ee;ff";
    char check_str4[]="a;aa;aaa;ab;wor;wor";
    
    
    sortDelim(str1,delim);
    ASSERT_STREQ(str1,check_str1);
    sortDelim(str2,delim);
    ASSERT_STREQ(str2,check_str2);
    sortDelim(str3,delim);
    ASSERT_STREQ(str3,check_str3);
    sortDelim(str4,delim);
    ASSERT_STREQ(str4,check_str4);
    sortDelim(str5, delim);
    EXPECT_STREQ("123;ad;g;hello;hello1;world;zbc", str5);
  
}

int main(int ac, char* av[])
{
  testing::InitGoogleTest(&ac, av);
  return RUN_ALL_TESTS();
}


/*
extern "C" {
#include "MyString.h"
}
#include "gtest/gtest.h"
#include <limits.h>

TEST(extendedSubStr , MyStringTest){
    // change instead of 1 to the index.
    EXPECT_EQ(3 , extendedSubStr(0,1,"abcde","de"));
    EXPECT_EQ(0 , extendedSubStr(1,1,"abc","abca"));
    EXPECT_EQ(-1 , extendedSubStr(0,1,"abvar","de"));
    EXPECT_EQ(0 , extendedSubStr(1,2,"psidnfkgfjlhogyfe1d","pinkfloyed"));
    EXPECT_EQ(0 , extendedSubStr(1,10000,"a","aaaaaaaaaaa"));
    EXPECT_EQ(-1 , extendedSubStr(0,2,"abc","abca"));
    EXPECT_EQ(-1 , extendedSubStr(0,1,"t3e2s1tcba","test"));
    EXPECT_EQ(0 , extendedSubStr(0,2,"t3e2s1tcba","test"));
    EXPECT_EQ(4 , extendedSubStr(-90000,2,"e1d1P1i1n1k1 1F1l1o1y1","Pink Floyed"));
    EXPECT_EQ(-1 , extendedSubStr(-5000,2,"abc","abca"));
    
    //test 2
    EXPECT_EQ(1 , extendedSubStr(-702, 3, "HelloWorld!", "eor!lWlHlod"));
    EXPECT_EQ(6 ,  extendedSubStr(0, 2, "HelloWorld!", "ol!"));
    EXPECT_EQ(1 ,extendedSubStr(0, 3, "HelloWorld!", "eor!"));
    EXPECT_EQ(0 ,extendedSubStr(0, 2, "t3e2s1tcba", "test"));
}

TEST(extendedSubStr, someTests) {
	int a;

	a=extendedSubStr(0, 2, "t3e2s1tcba", "test");
	EXPECT_EQ(0, a );
	a=extendedSubStr(0, 2, "t3e2s1tCba", "teSt");
	EXPECT_EQ(-1, a );
	a=extendedSubStr(0, 2, "t3E2S1tCba", "teSt");
	EXPECT_EQ(-1, a );
	a=extendedSubStr(0, 3, "t3e2s1tcba", "test");
	EXPECT_EQ(-1, a);
	a= extendedSubStr(0, 3, "t3e2s1tcba", "2t");
	 EXPECT_EQ(3,a);
	 a=extendedSubStr(0, 3, "t", "2t");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(0, 3, "t3e2s1tcba", "2");
	 EXPECT_EQ(3, a);
	 a=extendedSubStr(0, -1, "t3e2s1tcba", "test");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(0, -30, "t3e2s1tcba", "test");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(0, 0, "t3e2s1tcba", "test");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(1, 2, "t3e2s1tcba", "test");
	 EXPECT_EQ(0, a);
	 a=extendedSubStr(1, 2, "t3e2s1tcba", "testb3");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(1, 5, "saba", "test");
	 EXPECT_EQ(-1, a);
	a=extendedSubStr(1, 2, "saba", "sbs");
	EXPECT_EQ(0, a);
	a=extendedSubStr(1, 2, "saba", "sba");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(1, 2, "sab", "ss");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(1, 4, "sab", "ss");
	 EXPECT_EQ(-1, a);
	a=extendedSubStr(1, 4, "sab", "sa");
	EXPECT_EQ(0, a);
	a=extendedSubStr(1, -1, "sab", "s");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(1, -90, "sab", "ss");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(1, 0, "sab", "ss");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(1, 0, "sab", "s");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(-1, 2, "t3e2s1tcba", "test");
	 EXPECT_EQ(0, a);
	 a=extendedSubStr(-1, 2, "t3e2s1tcba", "testbt");
	 EXPECT_EQ(0, a);
	 a=extendedSubStr(-1, 5, "saba", "test");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(-1, 2, "saba", "sbs");
	 EXPECT_EQ(0, a);
	a=extendedSubStr(-1, 2, "saba", "sba");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(-1, 2, "sab", "ss");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(-1, 4, "sab", "ss");
	 EXPECT_EQ(-1, a);
	a=extendedSubStr(-1, 4, "sab", "sa");
	EXPECT_EQ(0, a);
	a=extendedSubStr(-1, -1, "sab", "s");
	EXPECT_EQ(-1, a);
	a=extendedSubStr(-1, -90, "sab", "ss");
	 EXPECT_EQ(-1, a);
	 a=extendedSubStr(-1, 0, "sab", "ss");
	EXPECT_EQ(-1, a);
	a=extendedSubStr(-1, 0, "sab", "s");
	EXPECT_EQ(-1, a);
	
	
}

TEST(sortDelim, someTests) {
	
	char str[] = "sab";
 	const char *delim = ";,'";
 	char str1[] = "sab,h";
 	char str2[] = "sab'a";
 	char str3[] = "sa;b";
 	const char *delim2 = "'";
 	const char *delim3 = ",*.";
 	char str4[] = "abc*test,hello.world*abcd.zzz";
 	char str5[] ="zbc*g,hello.world*ad.hello1,123";
 	char str6[] ="sab,.";
 	char str7[] ="b,avd.;s";
 	const char *delim4="";
 	const char *delim5=",v";
 	sortDelim(str, delim);
    EXPECT_STREQ("sab",str );
    sortDelim(str1, delim);
     EXPECT_STREQ("h;sab", str1);
     sortDelim(str, delim);
     EXPECT_STREQ("sab", str);
     sortDelim(str, delim2);
     EXPECT_STREQ("sab", str);
     sortDelim(str2, delim2);
     EXPECT_STREQ("a;sab", str2);
     sortDelim(str1, delim);
     EXPECT_STREQ("h;sab", str1);
     sortDelim(str,delim);
     EXPECT_STREQ("sab", str);
     sortDelim(str3, delim2);
     EXPECT_STREQ("sa;b", str3);
     sortDelim(str4, delim3);
     EXPECT_STREQ("abc;abcd;hello;test;world;zzz", str4);
     sortDelim(str5, delim3);
    EXPECT_STREQ("123;ad;g;hello;hello1;world;zbc", str5);
    sortDelim(str6, delim4);
    EXPECT_STREQ("sab,.", str6);
    sortDelim(str7, delim5);
    EXPECT_STREQ("a;b;d.;s", str7);
       
    
}
*/

