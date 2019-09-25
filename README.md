# e-commerce-comp433

This repository is the final project of Web Services Programming class, and it functions like a book store site market where customer can look for, buys their books while partners can supply their products to customer though the service.

## Functionalities
### Customer
* Register and login for account
* Searching for books by title
* Purchase an order
* Accept Credit Card payment
* Checking status of order
* Order Cancel
### Partner
* Register, login and create their profile
* Upload their products to website
* Get order from customer
* Receive notifications of the fullfillment from orders

## Development progress
### Phrase 1: Domail Modeling
#### Entity
Based on the above functionalities, the entity model will consist of 9 models: 
* Book: information about book and also partnerID where this book belong to.
* BookReview: review for particular book, consist of customerID and bookID of which review belong to.
* CardProfile: information of card which owned by customer, has customerID.
* Customer: basic credentials of user.
* CustomerInfo: information about specific user, has customerID of which customer belong to.
* ShippingAddress: information of shipping address that own by customer, has customerID
* Partner: basic credentials of partner.
* PartnerInfo: information about specific user, has partnerID of which partner belong to.
* Order1: consist information of one order, has bookID, customerID, partnerID, cardProfileID, shippingAddressID.
* Order_Book: information about book which linked to particular order (quantity, total).
#### Persistence
I'm using Hibernate as the Object-Relational Mapping for this project. Hibernate help "developers to more easily write applications whose data outlives the application process." For the purpose of configuration, I'm putting all of Hibernate setting including Database connection, entity registerating, etc. inside one XML file named `hibernate.cgf.xml`.
The persistancance layer will basicly consist of:
* HibernateUtil: load the configuration from `hibernate.cgf.xml` into application and construct a `Session Factory` which allows returning `Session` which used for data transactions.
* SessionHolder: help managing `Session`, ensure all operations on Session will happen on only single thread.
#### Repository
Service will asking data to data persistence by using repository. This package will basically consist of:
* Repository: interface define basic operation (CRUD) on any object with any type of ID (`public interface Repository<T, ID>`).
* AbstractRepository: implement `Repository` interface, and supply `Session` to its extends class by getting `Session` from `SessionHolder`.
* `Table`Repository: extends `Repository`, add new operations depends on purpose of that table.
* `Table`RepositoryImpl: extends `AbstractRepository` and implements `Table`Repository, communicate with Hibernate through `Session` to extract and return data.
#### Util
* EntityUtil: make testing easier by returning sample data for each table (will move it to test package).
* ID: generate ID for entity.
* Password: encrypt and return a new password.
#### Testing
For the purpose of testing, I pick in-memory database as the data persistence, this way will make testing easier and faster. It is specified in `hibernate.cgf.xml`: 
`
<property name="hibernate.connection.url">jdbc:hsqldb:mem:myDB</property>   
`
This package will consist of:
* AbstractHibernateTest: init `SessionFactory` and assign `Session` to `SessionHolder`, method to close and clear transactions.
* `Table`RepositoryImplTest: consist of test cases for every table in database that define in `Table`Repository
