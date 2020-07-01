# Cinema-project
# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Author](#author)

# <a name="purpose"></a>Project purpose
Creation of cinema booking system with basic operations required for it.
<hr>
Without being authenticated you can register and login. There are specific urls for admins and users.
Only users can complete order and add tickets to shopping cart.
As admin you can adding new movies, movie sessions and cinema halls.
<hr>

# <a name="structure"></a>Project Structure
* Java 11
* Maven 3.6.0
* maven-checkstyle-plugin 3.1.1
* maven-war-plugin 3.2.3
* javax.servlet-api 3.1.0
* mysql-connector-java 8.0.17
* hibernate-validator 6.1.5.Final
* hibernate-core, hibernate-java8 5.4.5.Final
* spring-context, spring-orm, spring-webmvc, 
spring-security-core, spring-security-config, spring-security-web 5.2.0.RELEASE
* log4j 1.2.17
<hr>

# <a name="developer-start"></a>For developer

1. Open the project in your IDE.

2. Add Java SDK 11 or above in Project Structure.

3. Configure Tomcat:
add the artifact cinema-project:war exploded;
add as URL http://localhost:8080/

4. Use PostgresQL

5. Run the project.

6. For testing this API you can download Postman or another analogue.

7. For authorization, you must add a new header, where Authorization is key and Basic email:password is value, 
    where email:password must be encoded to Base64 format.

There are test data that you can use.
There’s one user already registered with ADMIN role (email = "admin", password = "123") and
one user with USER role (email = "shion@ukr.net"", password = "123"). You can change these 
test data in InjectDataController if you want.

# <a name="author"></a>Author

Nikolay Abysov

