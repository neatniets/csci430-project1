.PHONY: all clean
OBJS := $(patsubst %.java, %.class, $(wildcard *.java))
OBJS := $(filter-out Product_tester.class, $(OBJS))
JAVAC := javac

all: $(OBJS)

clean:
	-rm *.class

Product_tester.class: %.class: %.java
	$(JAVAC) $<

$(OBJS): %.class: %.java
	$(JAVAC) $<
