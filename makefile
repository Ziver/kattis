JC = javac
JFLAGS = -g
GC = g++
GFLAGS = -g
.SUFFIXES: .java .class .cpp .exe


JAVA_SOURCE_FILES = \
		Infiltration.java \
		InfiltrationCamp.java

CPP_SOURCE_FILES = \
		kemija.cpp \
		timebomb.cpp


all: java cpp


java: $(JAVA_SOURCE_FILES:.java=.class)

cpp: $(CPP_SOURCE_FILES:.cpp=.exe)


%.class: %.java
	$(JC) $(JFLAGS) $<

%.exe: %.cpp
	$(GC) $(GFLAGS) -o $@ $<

clean:
	$(RM) *.class
	$(RM) *.exe