CC=arm-linux-gcc
EXEC=led_test1
OBJS=led_test.o
all:$(EXEC)
$(EXEC):$(OBJS)
	$(CC) -o $@ $(OBJS)
clean:
	-rm -f $(EXEC) *.elf *.gdb *.o
