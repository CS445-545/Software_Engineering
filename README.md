# TrackMyPassport 

# Links for the documents

Link to the set up and work flow document - https://drive.google.com/file/d/1I31awKyR5Zhiv0Z2lwAI4_1boZRwu18k/view?usp=drive_link

Link to the design document - https://docs.google.com/document/d/1GTrhDMapBnnqRzrptBS2fNExgqlWL5nA/edit?usp=drive_link&ouid=105881010644857215437&rtpof=true&sd=true

# Set up for the project

1)	Create a folder ‘Deveopment_avecto’ in C Drive.
 
2)	Install XAMPP- https://www.apachefriends.org/download.html (8.2.4/ PHP 8.2.4)
 
    a.	Create a folder in D drive by the name ‘Xampp’.
     
    b.	Install Xampp in this folder.
 
3)	Open the XAMPP Control panel.
   
    a.	Click on the start button of Apache and MySQL.
     
    b.	After MySQL starts and is running click on the ‘admin’ button besides start/stop button.
     
    c.	You will see a web Browser(PHPMYADMIN). Click on the new button to create a Database.
     
    d.	Type-‘passportstatus’ and click create.
  
4)	Download the Maven- https://maven.apache.org/download.cgi
   
    a.	Install the apache-maven-3.9.4-bin.zip version.
     
    b.	Extract the zip file.
    
    c.	Create a folder in C:\Program Files, by the name ‘Maven’.
     
    d.	Put the extracted file in this folder.

5)	After downloading Maven, we need to add its path to the environment variables.
   
    a.	Go to the setting of your system and search for ‘Control Panel’.
    
    b.	 In the Control panel, search for ‘Environment Variables’.
     
    c.	Click on ‘Edit the system environment variables’.
    
    d.	Now, click on ‘Environment Variables’
     
    e.	Go to system variables(the one below) and click on new.
    
    f.	Type variable name- ‘MAVEN_HOME’ and variable value-C:\Program Files\Maven\apache-maven-3.9.3\bin. Click OK.
     
    g.	Again, go to the system variables(the one below) and click on ‘path’ and then ‘edit’.
     
    h.	Click on ‘new’ and add- ‘%MAVEN_HOME%\bin’.
   

# Project Demo

1)	Open Visual Studio Code and go to ‘File’ and then ‘Open folder’
 
2)	Open the project directory where the project is placed and click ‘select folder’.
   
4)	Open terminal in Visual Studio Code and type ‘mvn spring-boot:run’. The code will be build.
 
5)	Then open the web Browser and type the URL- http://localhost:8080/login.
   
7)	Web Page will be displayed.
 
8)	The work flow will the as follows:
   
    a.	Click on ‘New Applicant’. Enter the username, email id and password to create an account.
     
    b.	Click on register. If the email id is already present it will return back to the same page and information have to be re filled.
    
    c.	If email is not present, you will have to enter the OTP sent to the email id you have registered with.
     
    d.	If the OTP entered is incorrect, it will throw an exception.
    
    e.	If the OTP is correct it will ask the applicant to fill in the details for their passport booking.
     
    f.	Once the application is submitted, an ‘Application Id’ and relevant details will be sent to the applicant’s registered email id. Application id will be           used  to check the status of application.
     
    g.	Applicant can track the passport status by clicking on the ‘Track status’ option provided on the main home page below.
     
    h.	Once Applicant clicks on the track status button, they have to enter application Id and birthdate for verification.
    
    i.	Applicants will receive an email update if there is any progress on their application. If their passport is completely approved they will receive an email        with their E-passport.
     
    j.	Applicants whose passport have expired can renew their passports by clicking on the ‘Applicant Login’ on the main home page.
    
    k.	It’s status will be displayed if there is no exception.

  	 l.	If applicant forget’s it’s application id. It has to go the applicant login and click on the forget password.
 
    m.	The application details will be sent to the registered email id. Applicants can then go back and click on ’ Track Status’ to see their status.

    n.	The username and password for admin login is ‘admin’. Only admins can login to the admin section using the username and password.
     
    o.	Admin’s are of two types Passport officer and Police officer. Applicants details will be first approved by Passport Officer and only then it will go to 
       the police Officer. Applicants details will be pop up on their pages on the date when Applicants have booked their appointment.

10) After running the application you can click on the ‘stop’ button for both Apache and MySQL.

# References
 
Used 'https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/' for reference

Used 'https://reflectoring.io/spring-boot-web-controller-test/' for reference to write unit test cases.



 
