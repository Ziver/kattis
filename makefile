JFLAGS = -g
JC = javac
.SUFFIXES: .java .class


SOURCEFILES = \
		Infiltration.java

CLASSFILES: $(SOURCEFILES:.java=.class)


all: $(CLASSFILES)

%.class: %.java
	$(JC) $(JFLAGS) $<

clean:
	$(RM) *.class
	$(RM) *.exe