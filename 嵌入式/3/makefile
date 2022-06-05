CC=arm-linux-gcc
EXEC=main
OBJS=main.o kblistener.o
all:$(EXEC)

$(EXEC):$(OBJS)
	$(CC) -lpthread -o main $(OBJS) 

main.o: main.c 
	$(CC) -I include -c main.c  

kblistener.o: src/kblistener.c 
	$(CC) -I include -c src/kblistener.c 

clean:
	-rm -f $(EXEC) *.elf *.gdb *.o
                                             