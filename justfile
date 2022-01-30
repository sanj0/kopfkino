set shell := ["bash", "-uc"]

# builds the project using maven
build:
    mvn clean install

# do a quick build without cleaning
qb:
    mvn install

# also builds text classes into a separate directory
testbuild: build _tb

# quick test build
qtb: qb _tb

# runs the test main class
testrun: testbuild _tr

# runs test after a [[quick build]]
qtr: qtb _tr 

_tb:
    mkdir -p target/testbuild/
    javac -cp target/classes -d target/testbuild/ src/test/java/*.java
    
_tr:
    java -cp "target/*:target/testbuild/" TestMain
    
