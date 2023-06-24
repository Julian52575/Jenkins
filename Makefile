
NAME 	= 	math

all :
	gcc -o $(NAME) src/main.c

clear :
	rm math

.PHONY:
	all clear
