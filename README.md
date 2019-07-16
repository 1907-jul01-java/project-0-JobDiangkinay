# Console Banking App Program
## Juan Gabriel Diangkinay
Command Line Banking App Program for Project 0 that uses Core Java language and PostgreSql for the database to 
save the bank accounts and transactions.

# User Stories
- [x] List
- Customer can apply to open a account.
- Customer Accounts can view their Personal infos.
- Customer Account can transfer,withdraw and deposit money to their accounts.
- Customer Account can request for a joint account.
- Customer Account can view past transactions.
- Employee & Admin can view all of User Accounts.
- Employee & Admin can accept or deny pending Accounts.
- Employee & Admin can accept or deny pending Joint Accounts.
- Admin can Withdraw, deposit and transfer from all accounts.
- Admin can view All Transactions.
- Admin can cancel an account.

# Instructions
# Build Postgres projectzero
Change directory into /db and run:
>docker build -t projectzero .

Then run a container:
>docker run -d -p 5432:5432 projectzero

# Compile, Package, & Execute with Maven
To compile and execute, run:
```
mvn compile
mvn exec:java
```

To package an executable jar and execute, run:
```
mvn clean package
java -jar target/project0-1.0-SNAPSHOT.jar
```