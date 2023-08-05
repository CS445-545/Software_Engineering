# TrackMyPassport
Used 'https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/' for reference

Install Xampp- https://www.apachefriends.org/download.html
Install Maven :https://maven.apache.org/download.cgi  download  apache-maven-3.9.4-bin.zip
Extract above zip folder in c drive/programFiles/Maven
Add MAVEN_HOME environment variable with value as above path
Set Path Environment Variable to include %MAVEN_HOME%\bin

Steps-

  1)Start Apache and MySQL from Xampp Control Panel.

  2)Create a Database- 'passportstatus'.

  3)To run the Code, open the project in Visual Studio Code and run- 'mvn spring-boot:run'

  4)Open Web Browser and use the url- 'http://localhost:8080/login' 

  5)Username- 'admin' and password- 'admin' are the credentials for Admin Login
