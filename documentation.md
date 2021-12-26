# Pizza-Baker
### This is a Pizza ordering web system. This project was given by University course professor as Assignment for understanding and learning Database ( Postgres ) System.



Soft wrap
1
# Entity Relationship Diagram
2
​
3
​
4
​
5
![erd](erd.png)
6
​
7
​
8
​
9
​
10
​
11
# Packages Diagram
12
​
13
![packages_diagram](packages_diagram.png)
14
​
15
​
16
​
17
- **Controllers:** Is the package that receives all the request from the web clients. It is responsible to use the other packages of the system, to solve the request and generate the response to the user.
18
- **Entities:** Here we have the POJOs of the system. Just classes without behavior, for easier manipulation of the data in memory.
19
- **Daos:** This package is a middleware between the database and the application. All the communication with the database is made throw this package.
20
- **Utils:** Just a package with utilities to not repeat code. For example, here we have a function that receives an string and normalize it.
21
​
22
​
23
​
24
# Technologies Used
25
​
26
​
27
​
28
- **Spring Boot:** As the main framework to build the web application.
29
- **Spring MVC:** For request resolving essentially.
30
- **Thymeleaf:** For templating (inject data on the html docs, and execute loops and conditions)
31
- **HTML5, CSS and Javascript:** For the frontend.
32
- **AJAX:** To get the ingredient details in the ingredients page, without refreshing the page.
33
- **JDBC:** To connect with the database.
34
- **org.json:** For json parsing.
35
- **Maven:** For dependencies management.


## Pages on System

<img width="1440" alt="Screenshot 2021-02-11 at 01 16 01" src="https://user-images.githubusercontent.com/28604009/147419399-b891a59d-aaf2-4a04-a69d-4f88fb7f8078.png">

<img width="1440" alt="Screenshot 2021-02-11 at 01 37 58" src="https://user-images.githubusercontent.com/28604009/147419391-52458464-0b6e-4559-8b27-e92c4957292a.png">

<img width="1440" alt="Screenshot 2021-02-11 at 01 29 13" src="https://user-images.githubusercontent.com/28604009/147419394-f096f93d-fc62-4881-9f0a-7c37043d112a.png">

<img width="1440" alt="Screenshot 2021-02-11 at 01 37 58 (1)" src="https://user-images.githubusercontent.com/28604009/147419401-b6ce4f1b-2e4d-45ed-b0ab-3d51c8648802.png">
