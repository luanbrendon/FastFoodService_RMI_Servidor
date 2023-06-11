@echo off

if exist target (
	echo  *** Gerando Stubs ***
	rmic -d target\classes -classpath target\classes CalculadoraImpl
) else (
	echo  *** Diretorio target nao existe ***
)