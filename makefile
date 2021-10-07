
#compile
JC	=	javac
JCFLAGS	=	-g	-d	$(BASE)$(BINDIR)

#execute
J	=	java
JFLAGS	=	-classpath	./$(BASE)$(BINDIR)


BASE	=	Bisca/
BINDIR	=	bin/
SRCDIR	=	src/

CLASSES = \
	$(BASE)$(SRCDIR)*/*.java \
        $(BASE)$(SRCDIR)*/*/*.java \
        $(BASE)$(SRCDIR)*/*/*/*.java\

MAIN	=	biscagame.main.Main


default:	classes

# compile
classes:
	rm -rf $(BASE)$(BINDIR) && mkdir $(BASE)$(BINDIR)
	$(JC)	$(JCFLAGS)	$(CLASSES)

# execute
playbisca:
	$(J)	$(JFLAGS)	$(MAIN)
		
# remove bin
clean:
	rm	-rf	$(BASE)$(BINDIR)
