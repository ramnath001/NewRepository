#
# Created by gmakemake (Ubuntu Sep  7 2011) on Thu May 17 18:04:44 2012
#

#
# Definitions
#

.SUFFIXES:
.SUFFIXES:	.a .o .c .C .cpp .s .S
.c.o:
		$(COMPILE.c) $<
.C.o:
		$(COMPILE.cc) $<
.cpp.o:
		$(COMPILE.cc) $<
.S.s:
		$(CPP) -o $*.s $<
.s.o:
		$(COMPILE.cc) $<
.c.a:
		$(COMPILE.c) -o $% $<
		$(AR) $(ARFLAGS) $@ $%
		$(RM) $%
.C.a:
		$(COMPILE.cc) -o $% $<
		$(AR) $(ARFLAGS) $@ $%
		$(RM) $%
.cpp.a:
		$(COMPILE.cc) -o $% $<
		$(AR) $(ARFLAGS) $@ $%
		$(RM) $%

CC =		gcc
CXX =		g++

RM = rm -f
AR = ar
LINK.c = $(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS)
LINK.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) $(LDFLAGS)
COMPILE.c = $(CC) $(CFLAGS) $(CPPFLAGS) -c
COMPILE.cc = $(CXX) $(CXXFLAGS) $(CPPFLAGS) -c
CPP = $(CPP) $(CPPFLAGS)
########## Default flags (redefine these with a header.mak file if desired)
CXXFLAGS =	-ggdb
CFLAGS =	-ggdb
CLIBFLAGS =	-lm
CCLIBFLAGS =	
########## End of default flags


CPP_FILES =	Clock_config.cpp Jam_config.cpp River_config.cpp clock.cpp jam.cpp river.cpp
C_FILES =	
PS_FILES =	
S_FILES =	
H_FILES =	Clock_config.h Jam_config.h River_config.h Solver.h
SOURCEFILES =	$(H_FILES) $(CPP_FILES) $(C_FILES) $(S_FILES)
.PRECIOUS:	$(SOURCEFILES)
OBJFILES =	Clock_config.o Jam_config.o River_config.o 

#
# Main targets
#

all:	clock jam river 

clock:	clock.o $(OBJFILES)
	$(CXX) $(CXXFLAGS) -o clock clock.o $(OBJFILES) $(CCLIBFLAGS)

jam:	jam.o $(OBJFILES)
	$(CXX) $(CXXFLAGS) -o jam jam.o $(OBJFILES) $(CCLIBFLAGS)

river:	river.o $(OBJFILES)
	$(CXX) $(CXXFLAGS) -o river river.o $(OBJFILES) $(CCLIBFLAGS)

#
# Dependencies
#

Clock_config.o:	Clock_config.h
Jam_config.o:	Clock_config.h Jam_config.h River_config.h Solver.h
River_config.o:	River_config.h
clock.o:	Clock_config.h River_config.h Solver.h
jam.o:	Clock_config.h Jam_config.h River_config.h Solver.h
river.o:	Clock_config.h River_config.h Solver.h

#
# Housekeeping
#

Archive:	archive.tgz

archive.tgz:	$(SOURCEFILES) Makefile
	tar cf - $(SOURCEFILES) Makefile | gzip > archive.tgz

clean:
	-/bin/rm $(OBJFILES) clock.o jam.o river.o core 2> /dev/null

realclean:        clean
	-/bin/rm -rf clock jam river 
