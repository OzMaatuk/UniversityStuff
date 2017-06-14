#include "MyLinkedList.hpp"
using namespace std;    

// node class for list element implemention
class MyNode
{    
	// friend class MyLinkedList;
    public:
	
	// cur node val
    double val;
    
	// cur node key
    string key;
	
	// prev and next node pointers
    MyNode *next = nullptr, *prev = nullptr;
    
    // parameters constructor
    MyNode(const string& newKey,const double& newVal);
    
    // deep copy constructor
    MyNode(const MyNode& other);
};

// parameters constructor
MyNode::MyNode(const string& newKey,const double& newVal)
{   
    val = newVal;
    key = newKey;
}

// deep copy constructor
MyNode::MyNode(const MyNode& other)
{  
    key = other.key;
    val = other.val;
}

// default empty constracture
MyLinkedList::MyLinkedList() : length(0)
{
     head = nullptr;
     last = nullptr;
}

// constructor for getting arrays of vals for nodes and setting linked list
MyLinkedList::MyLinkedList(const string* keysArr,const double* valsArr, size_t len) : length(0)
{ 
	head = nullptr;
	last = nullptr;
	size_t i;
	
	// for all arrays elements
	for (i = 0; i < len; i++)
	{
		// add a combenation of val and key as a node to the list
		add(keysArr[i],valsArr[i]);
	}   
}

// deep copy constructor
MyLinkedList::MyLinkedList(const MyLinkedList& other): length(0)
{
    head = nullptr;
    last = nullptr;
	
	// refering this list to a copy of other list (using copy func)
    this->copyList(other);
}

// delete all elements in list
void MyLinkedList::delete_List()
{
    if(length > 0)
    {
		// for the end of the list (next node null)
        for(MyNode *cur  = head; cur!=nullptr; cur = cur->next)
		{
			delete (cur->prev);
		}
        delete last;
    }
    length = 0;
}

// copy all elements from list
void MyLinkedList::copyList(const MyLinkedList& other)
{
	// delete list
    delete_List();
    if(other.length>0)
    {
        length = 0;
		// for all source list elements
        for(MyNode *temp = other.head; temp!=nullptr; temp = temp->next)
        {
			// add element to list
            add(temp->key, temp->val);
        }
    }
}

// destructor
MyLinkedList::~MyLinkedList()   
{   
     delete_List();
}

// implement = operator for linked list
MyLinkedList& MyLinkedList::operator=(const MyLinkedList& other)
{
	// delete list
    delete_List();
	
    // saving this head
    MyNode *node = other.head;
	
	// while not the end of the other list
    while (node != nullptr)
	{
		// add element the this list by other list vals
        add (node->key, node->val);
		// forward
        node=node->next;
    }
    
    length = other.size();
    return *this;
}

// add income element by vals and add it to the list
void MyLinkedList::add(const string& key, const double& val)
{
	// creating the new node by the getting vals
    MyNode *node = new MyNode(key,val);
    
	// if this list empty, set the new node for head and last
    if(head == nullptr || length == 0)
    {   
        head = node;
        last = head;
    }
    else // else... add it to the end of the list
    {
        node->prev = last;
        if (last != nullptr)
        {
            last->next = node;
        }
        last = node;
        
        if (length == 1)
        {
            head->prev = nullptr;
            last->next = nullptr;
        }
    }
    length++;
}

// remove this list nodes from the "newKey" node to the end, return num of nodes that was removed
size_t MyLinkedList::remove(const string& newKey)
{
    size_t count = 0;
	
	// if empty return 0
    if(length == 0)
	{
		return count;
	}
    
	// saving this head
    MyNode *cur = head;
	
	// while not the end of this list
    while(cur != nullptr)
    {
        MyNode *curPrev = cur->prev;;
		MyNode *curNext = cur->next;;
		
		// check if its the key for start removing
        if(cur->key == newKey)
        {
			//if not only one node
			if (!(curNext == nullptr && curPrev == nullptr))
			{
			    // if its the head
			    if (curPrev == nullptr)
			    {
			        printf("here1\n");
			        head = head->next;
			        head->prev = nullptr;
			        curNext = head;
			        
			        /*if empty
                    if (head == nullptr)
                    {
                        last = nullptr;
                    }
                    */
			    }
			    // else if its the last
			    else if (curNext == nullptr)
			    {
			        printf("here2\n");
			        curPrev->next = nullptr;
			        last = curPrev;
			    }
			    // else just a node
			    else
			    {
			        printf("here3\n");
			        // disconnect node from list
			        curNext->prev = curPrev;
			        curPrev->next = curNext;
			    }
			}
			// delete and forward
			if (cur != nullptr)
			{
			    delete cur;
			}

			// update...
            length--;
            count++;
		}
		cur = curNext;
	}
    return count;
}

// return if the element that passing by his vals, is in the list
bool MyLinkedList::isInList(const string& key, const double& value) const
{
	// saving this head
    MyNode *tmp = head;
    
	// while not the end of this list
    while(tmp != nullptr)
    {
		// if found equation
       if((key.compare(tmp->key)==0) && (tmp->val==value))
       {
	       return true; 
       }
	   // forward
       tmp = tmp->next; 
    }
    return false;
}

// return the sum of the list elements
double MyLinkedList::sumList() const
{
    double sum = 0;
	
	// for the end of the list (next node null)
    for(MyNode *cur = head; cur != nullptr; cur = cur->next)
	{
		// add val to sum
		sum += cur->val;
	}
    return sum;
}

// return the size of the list
size_t MyLinkedList::size() const
{    
    return length;  
}

// implement == operator for linked list comperation
    bool MyLinkedList::operator==(const MyLinkedList& other)const
{
	// if null or not the same lentgh
    if(&other == nullptr || length != other.length)
    {
        return false;
    }
	
	// saving tmps node pointer for going over the lists
    MyNode* tmp = head;
    MyNode* otherTmp = other.head;
	
	// goes over all first list
    while(tmp != nullptr)
    {
		// if the cur node are bot equals
        if((tmp->key != otherTmp->key) || (tmp->val != otherTmp->val))
        {
            return false;
        }
		// else continue...
        else
        {
            tmp = tmp->next;
            otherTmp = otherTmp->next;
        }
    }
    return true;
}
  
// implement != operator for linked list comperation
bool MyLinkedList::operator!=(const MyLinkedList& other)const  
{
    if((operator==(other)))
	{
		// if they are equal (using == operator) return false;
		return false;
	}
    else
	{
		// else...
		return true;
	}
}