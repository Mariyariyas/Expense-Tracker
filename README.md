# Expense-Tracker
 Expense-Tracker allow users to add, delete, and view their expenses. The application should also provide a summary of the expenses.
 https://roadmap.sh/projects/expense-tracker


You can Run this through command line interface or Postman Tool.

How to RUN:

mvnw.cmd spring-boot:run

Add, Update, and Delete Expense:
--------------------------------
Add Expenses - POST: curl -X POST http://localhost:8080/api/expenses
-H "Content-Type: application/json"
-d "{"amount":"Add amount","category":"CATEGORY","description":"Add Expense"}"

UPDATE Expenses - PUT : curl -X POST http://localhost:8080/tasks/update/{id}
-H "Content-Type: application/json"
-d "{"amount":"Add amount","category":"CATEGORY","description":"Add Expense"}"

DELETE Expenses - Delete : curl -X DELETE http://localhost:8080/api/expenses/delete/{id}

List All Expenses:
------------------
curl http://localhost:8080/api/expenses/listall

View a summary of all expenses
-------------------------------
curl http://localhost:8080/api/expenses/summary

View a summary of expenses for a specific month
------------------------------------------------
curl http://localhost:8080/api/expenses/summary/{june}


🗂 Setting Up the Database
This project uses MySQL. If you're setting it up for the first time:

Make sure MySQL is installed and running.
Create the database:
Import the schema and data:
Keep Your application.properties as Is spring.datasource.url=jdbc:mysql://localhost:3306/Expense spring.datasource.username=root spring.datasource.password=yourpassword spring.jpa.hibernate.ddl-auto=update
Others just need to set their local MySQL username/password correctly.
