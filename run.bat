call deploy.bat
echo "starting exe: sfi-gen-file-affido-ade-0.0.1-SNAPSHOT.jar"
cd target && java -Xms128m -Xmx384m -Xnoclassgc -jar sfi-gen-file-affido-ade-0.0.1-SNAPSHOT.jar --type S && cd ..
