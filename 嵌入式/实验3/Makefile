CC= arm-linux-gcc
EXEC= adc_test
OBJS= adc_test.o
CFLAGS+=
LDFLAGS+= -static
all: $(EXEC)
$(EXEC): $(OBJS)
	$(CC) $(LDFLAGS) -o $@ $(OBJS)
clean:
	-rm -f $(EXEC) *.elf *.gdb *.o

