build:
	mvn package

clean:
	mvn clean

testbuild: build _tb

testrun: testbuild _tr

_tb:
	mkdir -p target/testbuild/
	javac -cp target/classes -d target/testbuild/ src/test/java/*.java

_tr:
	java -cp "target/*:target/testbuild/" TestMain

