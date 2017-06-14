#########################################################
# Student information
#########################################################
# change to your ID as it appears in moodle!
ID= 12345678
# If you used additional header files, add them here and run make depend
MYADDITIONALHEADERFILES= 
#########################################################

#########################################################
# compiler stuff 
#########################################################
CC= gcc
CFLAGS= -Wall -Wvla -Werror -g
#COPTFLAGS= -O -DNDEBUG

CXX= g++
CXXFLAGS= $(CFLAGS) 

CEXELINKFLAGS= -lm
CXXTESTLINKFLAGS= -lgtest -lgtest_main -pthread
##########################################################

#######################
# executables name
#######################
EXE1= CaseChange
EXE2= DrawPolynomial
#######################

##########################################################
# sources files
##########################################################
SRCSEXE1MAIN= $(EXE1).c
SRCSEXE2MAIN= $(EXE2).c
SRCSEXE1= $(SRCSEXE1MAIN)
SRCSEXE2= $(SRCSEXE2MAIN)
##########################################################

#########################################################
# actions
#########################################################
all: $(EXE1) $(EXE2)
	if [ -d "Out" ]; then echo ""; else mkdir Out; fi
	@echo ""
	@echo "Running Tests"
	for n in $(shell seq 1 8); do\
        	./$(EXE1) < ex1_test_files/CaseChange/case_test$$n.in > Out/outC$$n.out; \
    	done; \
	for n in $(shell seq 1 10); do\
		./$(EXE2) < ex1_test_files/DrawPolynomial/draw$$n.in > Out/draw$$n.out; \
    	done; \
	for n in $(shell seq 1 8); do\
		diff -w ex1_test_files/CaseChange/case_test$$n.out Out/outC$$n.out; \
    	done; \
	for n in $(shell seq 1 10); do\
		diff -w ex1_test_files/DrawPolynomial/draw$$n.out Out/draw$$n.out; \
    	done;
	@echo "End Of Testing"

$(EXE1): $(subst .c,.o,$(SRCSEXE1))
	$(CC) $(CFLAGS) $^ -o $@ $(CEXELINKFLAGS)

$(EXE2): $(subst .c,.o,$(SRCSEXE2))
	$(CC) $(CFLAGS) $^ -o $@ $(CEXELINKFLAGS)
	
clean:
	rm *.o $(EXE1) $(EXE2) -f
	rm Out/*.out
	rmdir Out
	rm checkSubmission -fr

depend: $(SRCSEXE1) $(SRCSEXE2) 
	makedepend -Y -- $(CXXFLAGS) -- $^

zipfile:
	zip $(ID).zip $(SRCSEXE1MAIN) $(SRCSEXE2MAIN)

checkzipfile:
	rm checkSubmission -fr ; \
	mkdir checkSubmission ; \
	cd checkSubmission ; \
	mkdir tmp ;\
	cd tmp ;\
	cp ../../$(ID).zip . ;\
	unzip $(ID).zip ;\
	cp $(SRCSEXE1) .. ;\
	cp $(SRCSEXE2) .. ;\
	cd .. ;\
	cp ../Makefile . ;\
	mkdir ex1_test_files ; \
	cd ex1_test_files/ ; \
	mkdir CaseChange  ; \
	mkdir DrawPolynomial  ; \
	cd ..;\
	cp ../ex1_test_files/CaseChange/*.in ex1_test_files/CaseChange ;\
	cp ../ex1_test_files/CaseChange/*.out ex1_test_files/CaseChange ;\
	cp ../ex1_test_files/DrawPolynomial/*.in ex1_test_files/DrawPolynomial ;\
	cp ../ex1_test_files/DrawPolynomial/*.out ex1_test_files/DrawPolynomial ;\
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
	sudo chmod ugoa+r /usr/lib/libgtest.a
	sudo chmod ugoa+r /usr/lib/libgtest_main.a

makedependinstall:
	sudo apt-get install xutils-dev

valgrindinstall:
	sudo apt-get install valgrind
##############################################

.PHONY: all clean depend installonce gtestinstall makedependinstall valgrindinstall zipfile


# DO NOT DELETE THIS LINE -- make depend depends on it.

