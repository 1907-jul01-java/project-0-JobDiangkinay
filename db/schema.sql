drop table if exists BankAccounts;
drop table if exists PendingBankAccounts;
drop table if exists PendingJointAccounts;
drop table if exists Transactions;
drop table if exists Persons;
drop table if exists AccountCredentials;
create table AccountCredentials (
	credID serial primary key,
	UserName varchar unique not null,
	Password text not null,
	UserType text not null
);
create table Persons (
	pID serial primary key,
	FirstName text not null,
	LastName text not null,
	PhoneNumber text,
	UserName varchar not null references AccountCredentials(UserName)
);
create table BankAccounts (
	bID serial primary key,
	AccountNumber char(9) not null,
	Balance numeric(10,2),
	UserName varchar not null references AccountCredentials(UserName)
);
create table PendingBankAccounts(
	pbID serial primary key,
	AccountNumber char(9) not null,
	Balance numeric(10,2),
	UserName varchar not null
);
create table PendingJointAccounts(
	pbID serial primary key,
	AccountNumber char(9) not null,
	Balance numeric(10,2),
	UserName varchar not null
);
create table Transactions(
	tID serial primary key,
	SourceUser varchar not null,
	SourceAccount char(9) not null,
	Amount numeric(10,2),
	TransactionType varchar not null,
	DestinationAccount char(9),
	TransactionDate varchar
);
insert into AccountCredentials(UserName, Password, UserType) values ('adminjob', 'passjob', 'ADMIN');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('Juan', 'Fabros', '9494909895', 'adminjob');

insert into AccountCredentials(UserName, Password, UserType) values ('empben', 'passben', 'EMPLOYEE');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('Benjamin', 'Garcia', '9494909894', 'empben');

insert into AccountCredentials(UserName, Password, UserType) values ('empkenny', 'passkenny', 'EMPLOYEE');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('Kenny', 'Bui', '2252525494', 'empkenny');

insert into AccountCredentials(UserName, Password, UserType) values ('userjob', 'passjob', 'USER');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('Job', 'Diangkinay', '9494909895', 'userjob');
insert into BankAccounts(AccountNumber, Balance, UserName) values ('123456789', 500, 'userjob');
insert into BankAccounts(AccountNumber, Balance, UserName) values ('123456787', 1500, 'userjob');
insert into PendingBankAccounts(AccountNumber, Balance, UserName) values ('123456786', 1000, 'userjob');

insert into AccountCredentials(UserName, Password, UserType) values ('userkeith', 'passkeith', 'USER');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('Keith', 'Torres', '9052875395', 'userkeith');
insert into BankAccounts(AccountNumber, Balance, UserName) values ('123456788', 500, 'userkeith');