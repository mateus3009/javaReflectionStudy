NAME := controle_faculdade

MAIN_CLASS := main.ControleFaculdade

DIR_SOURCE := src
DIR_TARGET := target

SOURCE := $(shell find $(DIR_SOURCE) -type f -name *.java)
OBJECT := $(subst $(DIR_SOURCE),$(DIR_TARGET),$(SOURCE:.java=.class))

APP := $(DIR_TARGET)/$(NAME).jar

.PHONY : all  clean re

all : $(DIR_TARGET)/$(NAME).jar

clean :
	rm -rf $(DIR_TARGET)

re : clean all

$(APP) : $(OBJECT)
	jar -cfe $(APP) $(MAIN_CLASS) -C target/ . 

$(OBJECT) : $(SOURCE)
	javac -d target/ $(SOURCE)
