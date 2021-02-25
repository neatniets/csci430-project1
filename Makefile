.PHONY: all clean
OBJS := $(patsubst %.java, %.class, $(wildcard *.java))
JAVAC := javac

all: $(OBJS)

clean:
	-rm *.class

UserInterface.class: $(OBJS)

$(OBJS): %.class: %.java
	$(JAVAC) $<
