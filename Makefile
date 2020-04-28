######################################
# Author: 	Joost Vandorp, S. Smith	 #
# Revised: 	Thursday, Feb 24, 2017	 #
# Description:	"MAKEFILE"		 #
######################################

# Assumes JUnit is installed
# Assumes CLASSPATH has been set for Junit

JFLAGS = -g
#JCLASS = -cp ./src:.:$(CLASSPATH):./../../../eclipse/junit-4.5.jar
JCLASS = -cp ./src:.:$(CLASSPATH):/usr/share/java/junit4-4.5.jar # on mills
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $(JCLASS) $*.java

CLASSES = \
	src/DirectionT.java \
	src/ColourT.java \
	src/PointT.java \
	src/LineT.java \
	src/PathT.java \
	src/ObjectiveT.java \
	src/LevelT.java \
	src/BoardT.java \
	src/ModelT.java \
	src/ViewT.java \
	src/ControllerT.java \
	src/TestPointT.java \
	src/TestLineT.java \
	src/TestPathT.java \
	src/TestObjectiveT.java \
	src/TestLevelT.java \
	src/TestBoardT.java \
	src/TestModelT.java \

MAIN = AllTests

default: classes

classes: $(CLASSES:.java=.class)

doc:
	doxygen doxConfig
	cd latex && $(MAKE)

test: src/$(MAIN).class
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore $(MAIN)

dots: src/Dots.java
	$(JC) $(JCLASS) $(JFLAGS) src/Dots.java
	$(JVM) $(JCLASS) Dots
clean:
	rm -rf html
	rm -rf latex
	cd src
	rm **/*.class
