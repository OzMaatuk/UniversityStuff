#pragma once
#include <string>
using namespace std;

class MyNode;

class MyLinkedList
{
    public:
    
    // delete all elements in list
    void delete_List();
    
    // copy all elements from list
    void copyList(const MyLinkedList& other);
    
    // default empty constracture
    MyLinkedList();
    
    // other constracors
    MyLinkedList(const std::string* keysArr,const double* valsArr,size_t len);  // arrays parameters constructor
    MyLinkedList(const MyLinkedList& other);   // deep copy constructor
    
    // destructor
    ~MyLinkedList();
    
    // implement = operator for linked list
    MyLinkedList& operator=(const MyLinkedList& other);
    
    // add gets element by vals and add it to the list
    void add(const std::string& key, const double& val);
    
    // remove element from list by string key
    size_t remove (const std::string& st);
    
    // return if the element that passing by his vals, is in the list
    bool isInList(const std::string& key, const double& value) const;
    
    // return the sum of the list elements
    double sumList() const;	
    
    // return the size of the list
    size_t size() const;
    
    // implement == operator for linked list comperation
    bool operator==(const MyLinkedList& other) const;
    
    // implement != operator for linked list comperation
    bool operator!=(const MyLinkedList& other) const;
    
    private:
    
    // pointers of head and tail of the list
    MyNode *head, *last;
    // lentgh of the list
    size_t length;
    
};
