# WB Taxi Permit Registration Automation App
This selenium app automates below registration page: 

http://transport.wb.gov.in/sta-luxury-taxi-registration/

# How to run
1. Download this project and go to delivery folder. 
2. Update `data.properties` with the data you want to use in registration page.
  Note: If any field looks not related, leave it blank.
3. After updating properties file above, run `run-app.bat` from Windows command line.

Note: When program is executed by user, it asks user to go ahead by 
pressing enter on Windows command line.

# For Developers
1. Check out project
2. Change the code as you want
3. Run `mvn clean install` to create an executable jar file that can replace previous one in delivery folder and you can run `run-app.bat` 
