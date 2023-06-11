@echo off

if exist target (
echo *** Iniciando RMI Registry ***
cd target\classes
start rmiregistry
)else (
echo *** Diretorio target nao existe ***
)
pause