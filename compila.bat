@echo off

if not exist target (
	echo  *** Criando Diretorio target ***
	mkdir target
	mkdir target\classes
)

echo  *** Compilando projeto ***
javac -sourcepath src\ -d target\classes src\*.java
pause