#!/bin/bash
# Compiler le fichier Java avec le .jar
javac -cp "./lib/mariadb-java-client-3.3.2.jar" -d bin src/ConnexionBD.java

# Lancer le programme
java -cp "bin:lib/mariadb-java-client-3.3.2.jar" ConnexionBD