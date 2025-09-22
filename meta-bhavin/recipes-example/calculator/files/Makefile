
CC ?= $(CC)
CFLAGS ?= $(CFLAGS)

# Object file  for main program
OBJS = main.o

# Shared Libraries (passed from the bb file)
LIBS = ''

# Final binary (passed from the bb file)
TARGET = ''

all: $(TARGET)


# Link final binary with libs
$(TARGET): $(OBJS) $(LIBS)
# 	$(CC) $(CFLAGS) -o $@ $(OBJS) -L. -ladd -lsubtract -lmultiply -ldivide
	$(CC) -o $@ $(OBJS) -L. -ladd -lsubtract -lmultiply -ldivide $(LDFLAGS) 



# Versioned shared library
libadd.so.1.0: libadd.o
	$(CC) -shared -Wl,-soname,libadd.so.1 -o $@ $^ $(LDFLAGS)

# Symlink for development linking
libadd.so: libadd.so.1.0
	ln -sf libadd.so.1.0 $@

libsubtract.so.1.0: libsubtract.o
	$(CC) -shared -Wl,-soname,libsubtract.so.1 -o $@ $^ $(LDFLAGS)
libsubtract.so: libsubtract.so.1.0
	ln -sf libsubtract.so.1.0 $@

libmultiply.so.1.0: libmultiply.o
	$(CC) -shared -Wl,-soname,libmultiply.so.1 -o $@ $^ $(LDFLAGS)
libmultiply.so: libmultiply.so.1.0
	ln -sf libmultiply.so.1.0 $@

libdivide.so.1.0: libdivide.o
	$(CC) -shared -Wl,-soname,libdivide.so.1 -o $@ $^ $(LDFLAGS)
libdivide.so: libdivide.so.1.0
	ln -sf libdivide.so.1.0 $@




# Compile object files 
%.o: %.c calc.h
	$(CC) $(CFLAGS) -c $< -o $@


# Install into Yocto sysroot or rootfs 
install: all
	install -d $(DESTDIR)$(BINDIR)
	install -d $(DESTDIR)$(LIBDIR)



	install -m 0755 $(TARGET) $(DESTDIR)$(BINDIR)/
# 	install -m 0644 libadd.so libsubtract.so libmultiply.so libdivide.so $(DESTDIR)$(LIBDIR)/
	install -m 0644 libadd.so.1.0 libsubtract.so.1.0 libmultiply.so.1.0 libdivide.so.1.0 $(DESTDIR)$(LIBDIR)/

# 	Create symlinks for -dev linking
	cd $(DESTDIR)$(LIBDIR) && ln -sf libadd.so.1.0 libadd.so
	cd $(DESTDIR)$(LIBDIR) && ln -sf libsubtract.so.1.0 libsubtract.so
	cd $(DESTDIR)$(LIBDIR) && ln -sf libmultiply.so.1.0 libmultiply.so
	cd $(DESTDIR)$(LIBDIR) && ln -sf libdivide.so.1.0 libdivide.so

# Cleanup
clean:
	rm -f *.o *.a *.so $(TARGET)
