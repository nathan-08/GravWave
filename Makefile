%.class: %.java
	javac $<

.PHONY: all
all: main.class Panel.class

.PHONY: run
run: main.class Panel.class
	java main

.PHONY: clean
clean:
	rm *.class

