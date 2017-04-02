ReadMe
This document contains the demonstration to illustrate how to use the software. 
1. Directories Structure:
  |_noas
	|_ bin
	|_ lib
	|_ sql
	|_ src
	|_ MANIFEST.MF
	|_ UML.jpg
	|_ Use Case.pdf
	|_ ReadMe.docx
	|_ ReadMe.txt
	|_ noas.iml

2. Prepare Database and Data:
For test the implemenation of this software, we first need to create tables in database and insert some data.
In sql folder, execute createTable.sql in mysql to create all necessary tables. Then execute the following sql file to insert data for each table in order. 
	user.sql
	student.sql
	address.sql
	administrator.sql
	application.sql
	applicationfee.sql
	access.sql
	decisionmake.sql
	statement.sql

Enter mysql interactive environment under sql folder. In mysql execute:

source ./createTable.sql;
source ./user.sql;
source ./student.sql;
source ./address.sql;
source ./adminstrator.sql;
source ./application.sql;
source ./applicationfee.sql;
source ./access.sql;
source ./decisionmake.sql;
source ./statement.sql;

3. Compile source file in src folder either using command line or IDE. Execute following code in terminal:

cd ./src
javac *.java -d ./bin
cd ../bin
jar cvfm MyApp.jar MANIFEST.MF

4. Demo:
Execute the main function in DBUtil. In bin folder, execute command：

java -jar MyApp.jar 

The output is:

Test 1: A student pay application Fee for an application
Update Count is 1
Update Count is 1
Test 2: Get an application status
OFFER
Test 3: An administrator get application statement for an application
Statement Id: 4
Score : 9.0
TestName : toefl
Statement : business
Resume : A
Application Id : 5
false
Test 4: Make a decision for an application
Test 5: A user update password
Update Count is 1
Test 6: Check fee-paid status for an application
true
Process finished with exit code 0
