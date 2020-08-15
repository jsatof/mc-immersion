JJ = javac
JAR = jar -cf
CLASS = -classpath ".:/mnt/d/develop/.libraries/BuildTools/spigot-1.15.2.jar" 

fishing: 
	$(JJ) $(CLASS) fishing/src/me/N0handles/fishing/*.java -d fishing/bin
	$(JAR) customFishing.jar fishing/bin/* fishing/src/*

food: 
	$(JJ) $(CLASS) food/src*.java
	$(JAR) customFood.har food/*

.PHONY: fishing food