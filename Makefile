JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  s40230415_detector.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	@javac -g s40230415_detector.java
	@java s40230415_detector $(FILE1) $(FILE2)