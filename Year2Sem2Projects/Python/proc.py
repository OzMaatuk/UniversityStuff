## ----------------------------------------------------------------------------------------------------------------- ##

# befor running you have to
# install wmi lib
# https://pypi.python.org/pypi/WMI/
# the file is already in the zip, all you need to do is copy it to your python path and install
# its a python lib for working with Windows OS
# note that this code works only for Windows OS,
# for Linux OS there is other lib, and other commands to deal with (much simpler)

## ----------------------------------------------------------------------------------------------------------------- ##

from cgitb import reset

# importing proper libs
import wmi
import time
import os

## ----------------------------------------------------------------------------------------------------------------- ##

## -------- class of file with process and services lists -------- ##
class proFile:
  # f = None
  # fname = None
  # tmp = None

  ## -------- constractor. gets: file name, number of checks to run, delay between every check in sec-------- ##
  def __init__(self, fName, loops, pause):
	# init vars, opening file
    self.fname = fName
    self.f = open(fName, "w+")
	# creating tracker object and writing the list to file
    self.tmp = proTracker(loops, pause)
    self.tmp.printToFile(self.f)

    ## -------- constractor. gets file name, number of checks to run, delay between every check in sec-------- ##

  def getList(self):
	# opening file, creating list to reaturn
    self.f = open(self.fname, "r")
    curList = []
	# for every line in the the file, add an element for list
    for line in self.f:
      curList.append(line)
    return curList

## ----------------------------------------------------------------------------------------------------------------- ##

## -------- class of list of process and services list -------- ##
class proTracker:
  # trackList = []

  ## -------- constractor. gets: number of checks to run, delay between every check in sec -------- ##
  def __init__(self, loops, pause):
	# set the list
    self.trackList = []
	# making "loops" of checks every "pause" time
    for i in range (0,loops,1):
      self.trackList.append(proClass())
      time.sleep(pause)

  ## -------- printing list -------- ##
  def printList(self):
    print(self.trackList.__len__())
    for index in range (0, self.trackList.__len__(), 1):
      self.trackList.__getitem__(index).printList()

  ## -------- printing list to file -------- ##
  def printToFile(self, f):
    print(self.trackList.__len__())
    for index in range(0, self.trackList.__len__(), 1):
      self.trackList.__getitem__(index).printToFile(f)

## ----------------------------------------------------------------------------------------------------------------- ##

## -------- class of process and services list -------- ##
class proClass:
  # proList = []
  # c = None
  # t = 0

  ## -------- constractor -------- ##
  def __init__(self):
	# creating a list, wmi object and taking the cur time
    self.proList = []
    self.c = wmi.WMI()
    self.t = time.ctime()
	# adds time and cosmetic to the list
    self.proList.append("--------------------------------")
    self.proList.append("time: " + str(self.t))
    self.proList.append("----------- process: ------------")
	# add process name and PID for every process that wive got via wmi object by his Win32_Process function
    for process in self.c.Win32_Process():
      self.proList.append("Name: " + process.Name + ", PID: " + str(process.ProcessId) + ", Usage: " + str(process.WorkingSetSize))
    # add services name and type for every process that wive got via wmi object by his Win32_Process function
    self.proList.append("---------- services: ------------")
    for serv in self.c.Win32_Service():
      if (str(serv.ProcessId)!= "0"):
        self.proList.append("Name: " + serv.Name + ", Type: " + str(serv.ServiceType) + ", Works for: " + str(serv.ProcessId))
	  
  ## -------- printing list to file -------- ##
  def printToFile(self, f):
    for p in self.proList:
      f.write(p + "\n")

  ## -------- printing list -------- ##
  def printList(self):
    for p in self.proList:
      print(p + "\n")

## ----------------------------------------------------------------------------------------------------------------- ##

## -------- comp two list and write to file, gets two lists, and a file name to write -------- ##
def compList(curList, resentList, fname):
  # open file
  f = open(fname, "w+")
  # writing cosmetics
  f.write("--------------------------------\n")
  f.write("has started: \n")
  f.write("--------------------------------\n")
  # for every PID process or service name in current list, if its not in the resent list, so its new process
  for firstItem in curList:
    toPrint = 0;
    tmpLine = str(firstItem).split(',')
    # if this line represent process
    if ("Usage:" in firstItem):
      for secItem in resentList:
        if (tmpLine[1] in secItem):
          toPrint = 1;
      if (toPrint == 0):
        f.write(firstItem + "\n")
    # now for services
    toPrint = 0;
    if ("Works for:" in firstItem):
      for secItem in resentList:
        if (tmpLine[0] in secItem):
          toPrint = 1;
      if (toPrint == 0):
        f.write(firstItem + "\n")
    # cosmetic servises and process titles
    if ("---------- services: ------------" in firstItem):
      f.write(firstItem + "\n")
    if ("----------- process: ------------" in firstItem):
      f.write(firstItem + "\n")

  # writing cosmetics
  f.write("--------------------------------\n")
  f.write("has stopped: \n")
  f.write("--------------------------------\n")
  # for every PID process or service name in resent list, if its not in the current list, so its process that stopped
  for firstItem in resentList:
    toPrint = 0;
    tmpLine = str(firstItem).split(',')
    # if this line represent process
    if ("Usage:" in firstItem):
      for secItem in curList:
        if (tmpLine[1] in secItem):
          toPrint = 1;
      if (toPrint == 0):
        f.write(firstItem + "\n")
    # now for services
    toPrint = 0;
    if ("Works for:" in firstItem):
      for secItem in curList:
        if (tmpLine[0] in secItem):
          toPrint = 1;
      if (toPrint == 0):
        f.write(firstItem + "\n")
    # cosmetic servises and process titles
    if ("---------- services: ------------" in firstItem):
      f.write(firstItem + "\n")
    if ("----------- process: ------------" in firstItem):
      f.write(firstItem + "\n")

  # writing cosmetics
  f.write("--------------------------------\n")
  f.write("still running: \n")
  f.write("--------------------------------\n")
  # for every PID process in resent list, if its in the current list, so check usage
  for firstItem in curList:
    # parameters for avg
    avgUsage = 0;
    count = 0;
    tmpLine = str(firstItem).split(',')
    # if this line represent process
    if ("Usage:" in firstItem):
      for secItem in resentList:
        # if this line represent process
        if ("Usage:" in secItem):
          tmpSecLine = str(secItem).split(',')
          # check if same PID
          if (tmpLine[1] == tmpSecLine[1]):
            # gets integer usage, sum and count
            usage2 = int(str(tmpSecLine[2]).split(':')[1])
            avgUsage = avgUsage + usage2
            count = count + 1
      # save current proc usage
      usage1 = int(str(tmpLine[2]).split(':')[1])
      # if found
      if (avgUsage != 0):
        if (usage1 < (avgUsage/count)):
          f.write(tmpLine[0][5:] + " with " + tmpLine[1] + " is taierd\n")
        else:
          f.write(tmpLine[0][5:] + " with " + tmpLine[1] + " is working harder\n")

## ----------------------------------------------------------------------------------------------------------------- ##

## -------- get list from file (same as in proFile)-------- ##
def getList(fname):
  f = open(fname, "r")
  curList = []
  for line in f:
    curList.append(line)
  return curList

## -------- print List, gets list and for every element, print -------- ##
def printList(l):
    for p in l:
      print(p + "\n")

## ----------------------------------------------------------------------------------------------------------------- ##

## ----- Main ----- ##
# while not telling to stop
while (1):
  #  prints menu
  print("")
  print("**** all path need to be writen in full name include .txt ****")
  print("Hello, what action do you want to do?")
  print("1. get current process list")
  print("2. get couple of process list, with delay between")
  print("3. get old process list from file")
  print("4. make new process file")
  print("5. compare current process list, with old file of process")
  print("6. EXIT")
  print("")
  cNum = input("please enter your choice: ")
  print("")

  if (cNum == 1):
    # creat a process list object and prints the list
    tmp = proClass()
    tmp.printList()

  if (cNum == 2):
    loopsNum = input("please enter how many process list you want to execute: ")
    delay = input("please enter the delay between every process list: ")
	# create a process tracker object and prints the list
    tmp = proTracker(loopsNum, delay)
    tmp.printList()

  if (cNum == 3):
    filePath = raw_input("please enter file path: ")
	# if its a path
    if (os.path.isfile(filePath)):
      printList(getList(filePath))
    else:
      print("file not found!")

  if(cNum == 4):
    filePath = raw_input("please enter file path: ")
    loopsNum = input("please enter how many process list you want to execute in the file: ")
    delay = input("please enter the delay between every process list: ")
	# creat a process file object and prints the list
    proFile(filePath, loopsNum, delay)

  if (cNum == 5):
    filePath = raw_input("please enter old process file path: ")
    newFilePath = raw_input("please enter file path for writing cur process: ")
    newDiffFilePath = raw_input("please enter file path for writing comp (will overwrite the file): ")
	# if its a path
    if (os.path.isfile(filePath)):
	  # gtes list from file
      resentList = getList(filePath)
	  # creat a process file object for only one check
      tmp = proFile(newFilePath, 1, 0)
	  # gtes list from pro file object
      curList = tmp.getList()
	  # makes comp!
      compList(curList, resentList, newDiffFilePath)
    else:
      print("file not found!")

  if (cNum == 6):
    break

## ----------------------------------------------------------------------------------------------------------------- ##

# oldPro = proFile("1.txt", 2, 5)
# print("now!")
# time.sleep(5)
# print("done!")
# tmp = proFile("2.txt", 1, 0)

# curList = tmp.getList()
# listFromFile = oldPro.getList()
# f = open("3.txt", "w+")
# compList(curList, listFromFile, f)

## ----------------------------------------------------------------------------------------------------------------- ##