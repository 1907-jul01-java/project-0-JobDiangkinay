drop table if exists AccountCredentials;
drop table if exists Persons;
drop table if exists BankAccounts;
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
	UserName varchar not null
);
create table BankAccounts (
	bID serial primary key,
	AccountNumber char(9) unique not null,
	Balance real,
	UserName varchar not null
);
insert into AccountCredentials(UserName, Password, UserType) values ('adminjob', 'passjob', 'ADMIN');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('Job', 'Diangkinay', '9494909895', 'adminjob');

insert into AccountCredentials(UserName, Password, UserType) values ('empkeith', 'passkeith', 'EMPLOYEE');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('Keith', 'Torres', '9494909894', 'empkeith');

insert into AccountCredentials(UserName, Password, UserType) values ('empjob', 'passjob', 'EMPLOYEE');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('JobEmp', 'DiangkinayEmp', '9494909895', 'empjob');

insert into AccountCredentials(UserName, Password, UserType) values ('userjob', 'passjob', 'USER');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('JobUser', 'DiangkinayUser', '9494909895', 'userjob');
insert into BankAccounts(AccountNumber, Balance, UserName) values ('123456789', 500, 'userjob');