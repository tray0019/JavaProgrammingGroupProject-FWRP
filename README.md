The Food Waste Reduction Platform (FWRP) aims to address the global issue of food waste by connecting food retailers, consumers, and charitable organizations. This document outlines the architecture, database design, and testing approach for the FWRP application.
TODO: 

In group of 4, we are working on this assignment. 


#Application Architecture
Presentation Layer: Create JSP pages for visitors, registration and login as a consumer, retailer or charitable organization, listing inventory.
Business Layer: Develop java classes with the use of Apache NetBeans to handle application logic. Use DAO design patterns to abstract and encapsulate all access to the data source. Implement DTOs to transfer data between the DAOs and the business logic classes which will be the servlet classes and java classes.
Database Layer: Set up RDBMS with the use of MySQL and create tables according to ERD. Use JDBC to connect to the database, execute queries, and handle transactions.
Testing: Write JUnit tests for Java methods, especially those handling business logic. Test JSP pages to ensure they correctly display data from the backend and handle user input as expected.
Version Control: GitHub for version control, regularly committing changes and collaborating among team members.

Documentation: MS Office for document creation.
Communication: MS Teams for team communication and collaboration.
UML: UMLet for creating UML diagrams.

#Class Diagram
Users: Represents a user of the platform with attributes like name, email, password, and userType. Subclass of User, represents.
Consumer: Subclass of User, represents a consumer with additional attributes like purchase.
Retailer: Subclass of User, represents a retailer with additional attributes like business_name.
CharitableOrganization: Subclass of User, represents a charitable organization with additional attributes like organization name.
Inventory: Manages the inventory of a retailer and represents individual food items with attributes like name, quantity, and reduced price or free for donation.
Subscription: Represents a user's subscription to receive surplus food alerts.
Claims: Tracks claims made by charitable organizations for surplus food items.
