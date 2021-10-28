NAME := controle_faculdade

MAIN_CLASS := main.ControleFaculdade

DIR_SOURCE := src
DIR_TARGET := target

SOURCE := $(shell find $(DIR_SOURCE) -type f -name "*.java")
OBJECT := $(subst $(DIR_SOURCE),$(DIR_TARGET),$(SOURCE:.java=.class))

APP := $(DIR_TARGET)/$(NAME).jar

.PHONY : all fclean re

all : $(DIR_TARGET)/$(NAME).jar

re : clean all

clean :
	rm $(APP)

fclean :
	rm -rf $(OBJECT)

$(APP) : $(OBJECT)
	jar -cfe $(APP) $(MAIN_CLASS) -C target/ . 

$(DIR_TARGET)/%.class : %.java
	javac -d target $<
