@echo off

if exist target\classes (
echo *** Executando Projeto ***
java -cp target\classes ServerMain)
pause