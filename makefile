JJ = javac
JAR = jar -cf
CLASS = -classpath ".:/mnt/d/develop/.libraries/BuildTools/spigot-1.15.2.jar" 

fishing: 
	$(JJ) $(CLASS) fishing/src/me/N0handles/fishing/*.java -d fishing/bin
	$(JAR) customFishing.jar fishing/*

food: 
	$(JJ) $(JARS) immersion/src/me/N0handles/FishingSimulator/*.java

.PHONY: fishing food