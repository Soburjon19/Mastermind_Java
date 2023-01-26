default: My_Mastermind.java
	javac My_Mastermind.java

play: My_Mastermind.class 
	java My_Mastermind

auto: My_Mastermind.class 
	java My_Mastermind

all: My_Mastermind.clss
	java My_Mastermind

clean:
	rm -rf src/*.class
