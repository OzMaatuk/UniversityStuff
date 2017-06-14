#########################################################
# Student information
#########################################################
# change to your ID as it appears in moodle!
ID= 12345678
#########################################################

#########################################################
# compiler stuff 
#########################################################
CC= gcc
CFLAGS= -Wall -Wvla -Werror -g
#COPTFLAGS= -O -DNDEBUG

CXX= g++
CXXFLAGS= $(CFLAGS) -D_GLIBCXX_DEBUG -std=c++11

CXXTESTLINKFLAGS= -lgtest -lgtest_main -pthread
##########################################################


##########################################################
# sources files
##########################################################
SRCSNOMAIN= MyLinkedList.cpp

# There is no SRCTESTMAIN as we are linking with gtest_main that has main
SRCSTESTNOMAIN= MyLinkedListTest.cpp
##########################################################


#######################
# executables name
#######################
TEST= MyLinkedListTest
#######################


#########################################################
# actions
#########################################################
all: $(TEST)
	@read -p "Press [Enter] key to start memory check of test executable..." MEMCHK
	valgrind --leak-check=full -v ./$(TEST)

$(TEST): $(subst .cpp,.o,$(SRCSNOMAIN)) $(subst .cpp,.o,$(SRCSTESTNOMAIN))
	$(CXX) $(CXXFLAGS) $^ -o $@ $(CXXLINKFLAGS) $(CXXTESTLINKFLAGS)
	./$@

clean:
	rm *.o $(TEST) -f

depend: $(SRCSTESTNOMAIN)
	makedepend -Y -- $(CXXFLAGS) -- $^

zipfile:
	zip $(ID).zip MyLinkedList.hpp $(SRCSNOMAIN) $(SRCSTESTNOMAIN)

checkzipfile:
	rm checkSubmission -fr ; \
	mkdir checkSubmission ; \
	cd checkSubmission ; \
	cp ../$(ID).zip . ;\
	unzip $(ID).zip ;\
	cp ../Makefile . ;\
	make ;\
	cd ..

##############################################
# installs that should be done once
##############################################
installonce: gtestinstall makedependinstall valgrindinstall

gtestinstall: 
	sudo apt-get install libgtest-dev
	sudo apt-get install cmake
	cd /usr/src/gtest; \
	sudo cmake CMakeLists.txt; \
	sudo make; \
	sudo cp *.a /usr/lib
	sudo chmod u+r /usr/lib/libgtest.a
	sudo chmod u+r /usr/lib/libgtest_main.a

makedependinstall:
	sudo apt-get install xutils-dev
##############################################

.PHONY: all clean depend installonce gtestinstall makedependinstall valgrindinstall


# DO NOT DELETE THIS LINE -- make depend depends on it.

MyLinkedListTest.o: MyLinkedList.hpp
