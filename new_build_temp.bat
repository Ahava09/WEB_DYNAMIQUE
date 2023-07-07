:: Création du dossier temporaire temp
mkdir temp
cd temp

:: Création de la structure du fichier
mkdir WEB-INF
mkdir page
mkdir src
cd WEB-INF
mkdir classes
mkdir lib
cd ../../

:: Compilation du modele
copy .\lib.jar .\temp\WEB-INF\lib
copy .\commons-io-2.7.jar .\temp\WEB-INF\lib
copy .\gson-2.8.6.jar .\temp\WEB-INF\lib
for /r .\model\ %f in (*.java) do copy "%f" .\temp\src\%~nxf
javac -source 17 -cp .\temp\WEB-INF\lib\lib.jar -parameters -d .\temp\WEB-INF\classes .\temp\src\*.java
rmdir /S /Q .\temp\src

:: Copie des Autre fichiers
copy .\web\page\all.jsp .\temp\page\
copy .\web\page\input.jsp .\temp\page\
copy .\web\index.jsp .\temp\
copy .\web\WEB-INF\web.xml .\temp\WEB-INF\

:: Déploiement vers tomcat
jar -cvf fw.war -C .\temp\ .
copy .\fw.war C:\


