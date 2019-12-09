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
* `Book`: information about book and also partnerID where this book belong to.
* `BookReview`: review for particular book, consist of customerID and bookID of which review belong to.
* `CardProfile`: information of card which owned by customer, has customerID.
* `Customer`: basic credentials of user.
* `CustomerInfo`: information about specific user, has customerID of which customer belong to.
* `ShippingAddress`: information of shipping address that own by customer, has customerID
* `Partner`: basic credentials of partner.
* `PartnerInfo`: information about specific user, has partnerID of which partner belong to.
* `Order1`: consist information of one order, has bookID, customerID, partnerID, cardProfileID, shippingAddressID.
* `Order_Book`: information about book which linked to particular order (quantity, total).
#### Data Access Layer
##### `Repository`: interface define basic operation (CRUD) on any object with any type of ID (`public interface Repository<T, ID>`).
##### `AbstractRepository`: implement `Repository` interface, and supply `Session` to its extends class by getting `Session` from `SessionHolder`.
##### `Persistence` package: I'm using Hibernate as the Object-Relational Mapping for this project. Hibernate help "developers to more easily write applications whose data outlives the application process." For the purpose of configuration, I'm putting all of Hibernate setting including Database connection, entity registerating, etc. inside one XML file named `hibernate.cgf.xml`.
The persistancance layer will basicly consist of:
* `HibernateUtil`: load the configuration from `hibernate.cgf.xml` into application and construct a `Session Factory` which allows returning `Session` which used for data transactions.
* `SessionHolder`: help managing `Session`, ensure all operations on Session will happen on only single thread.
##### `Table` package: This will consist the data access login of each table.
* `Table`Repository: extends `Repository`, with new operations depends on the manner of that table.
* `Table`RepositoryImpl: extends `AbstractRepository` and implements `Table`Repository, communicate with Hibernate through `Session` to extract and return data.

#### Domain Layer: This package will be the bussiness of the application. Domain will make calls to DAL to perform operations on Data.
##### `Table` package: this will consist of interface and the implementation of the domain. 

#### Util
* `EntityUtil`: make testing easier by returning sample data for each table (will move it to test package).
* `ID`: generate ID for entity.
* `Password`: encrypt and return a new password.
#### Testing
There is one unit test class for each table in the database
For the purpose of testing, I choose in-memory database as the data persistence, this way will make testing easier and faster. It is specified in `hibernate.cgf.xml`: 
`
<property name="hibernate.connection.url">jdbc:hsqldb:mem:myDB</property>   
`
This package will consist of:
* `AbstractHibernateTest`: init `SessionFactory` and assign `Session` to `SessionHolder`, method to close and clear transactions.
* `Table`RepositoryImplTest: extends `AbstractHibernateTest`, consist of test cases for every table in database that define in `Table`Repository
### Phrase 2: Expose Service
This phrase will implement services layer to expose the API using the functionalities from `Domain Layer`. It will consist of:
##### `impl` package: service implementation, will implements service interface. The end point of exception handlers. API calls will be assign to `Acitivity` class.
##### `util` package: Transforming the Database Object to Representation Object
##### `workdflow` package: classes that work with Domain Layer to extract data and return the representation objects to service implementation.
##### `service` interface: define paths as well as parameters for each API.
### Phrase 3: Adding HATEOS
This phrase will focus on adding a addition `links` on the response of any object. This `links` will act as the guidance for client to navigate through the applications.
Each `links` will contain of 3 main components:
- `media-type`: determine the type of data returned, either JSON or XML
- `rel`: the relationship with current route
- `url`: the up-to-date url to the desire route from current route
