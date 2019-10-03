# TechnicalTest

## How to build

To build the project, run 'gradle fatJar'.
A jar file called 'TechnicalTest-1.0-SNAPSHOT-all.jar' will appear in /build/libs/

You will need a MariaDB installed to order to run it correctly.
The scripts for creating the required database, user, tables, data, etc. are including in /DBScripts . Run them all after installing MariaDB.

## How to run

To run it, use java -jar command to run the jar mentioned above:
java -jar TechnicalTest-1.0-SNAPSHOT-all.jar

If you see this message, it means the http server is running:
Http Server is now listening to localhost:8080

## How to test

An HTML file is created for testing. It is located at
/src/test/test.html

You can open it with a browser to start testing. It will have fields to input for different functions. There are buttons corresponding to different functions. When a button is pressed, it will call the API of the http server and pass the parameters to it.
IN and OUT text areas will show data going in and out respectively.

## How long did I spend on this test? 

Around 1 working day, i.e. 8 hours.

## What would I have added or done differently if I had more time?

I think I would automate the testing process by adding something like JUnit tests. Or, write a better http client to call the API automatically. I could also use the http client to do stress test.

