SRC := main-sdbusplus.cpp
TARGET := bhavin-demo-service

# Set PKG_CONFIG if not already set
PKG_CONFIG ?= pkg-config

# Use PKG_CONFIG_PATH from the build environment
CXX ?= $(CROSS_CXX)
CXXFLAGS += -O2 -g -std=c++23 $(shell $(PKG_CONFIG) --cflags dbus-1 sdbusplus)
LDLIBS   += $(shell $(PKG_CONFIG) --libs dbus-1 sdbusplus)

# Ensure LDFLAGS are used
LDFLAGS ?=

.PHONY: all clean

all: $(TARGET)

$(TARGET): $(SRC)
	$(CXX) $(CXXFLAGS) $(LDFLAGS) -o $@ $^ $(LDLIBS)

clean:
	rm -f $(TARGET)
