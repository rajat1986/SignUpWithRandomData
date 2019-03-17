# SignUpWithRandomData

# DESCRIPTION -
	This automation suite is a baseline framework to test Facebook Sign Up Page with Random test data.
	
# Assumptions -
  1. On successful signup, email or phone verification is done.
  2. On failure, account is blocked or error is shown on the Sign Up page.
  3. Test Data generated is not an existing user.
  4. Email Verification is not in automation scope.
  
# Limitations -
  1. Automation suite only supports email id.
  2. Only Google chrome is supported, but support can be extended to other browsers. Code is easily extendable.
  3. Verified on Windows 10.
 
# Requirements -
  1. Java version 8
  2. TestNG defined in pom.xml
  3. Apache Maven 3.6
  4. Selenium-Java

# Execution -
  Using Maven Command -	
   mvn install
   mvn test
