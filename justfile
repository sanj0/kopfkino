set shell := ["bash", "-uc"]
# builds the project using maven
build:
    mvn clean install
# also builds text classes into a separate directory
testbuild: build
    mkdir target/testbuild/
    javac -cp target/classes -d target/testbuild/ src/test/java/*.java
# runs the test main class
testrun: testbuild
    find target/classes -name "*.class" > classpath.txt
    find target/testbuild -name "*.class" >> classpath.txt
    java -cp "target/*:target/testbuild/" TestMain
    rm classpath.txt
