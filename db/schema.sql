drop table if exists AccountCredentials;
drop table if exists Persons;
drop table if exists BankAccounts;
drop table if exists PendingBankAccounts;
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
	AccountNumber char(9) not null,
	Balance numeric(10,2),
	UserName varchar not null
);
create table PendingBankAccounts(
	pbID serial primary key,
	AccountNumber char(9) not null,
	Balance numeric(10,2),
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
insert into BankAccounts(AccountNumber, Balance, UserName) values ('123456787', 1500, 'userjob');
insert into PendingBankAccounts(AccountNumber, Balance, UserName) values ('123456786', 1000, 'userjob');

insert into AccountCredentials(UserName, Password, UserType) values ('userkeith', 'passuserkeith', 'USER');
insert into Persons(FirstName, LastName, PhoneNumber, UserName) values ('KeithUser', 'TorresUser', '9052875395', 'userkeith');
insert into BankAccounts(AccountNumber, Balance, UserName) values ('123456788', 500, 'userkeith');