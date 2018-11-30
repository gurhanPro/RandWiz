CREATE TABLE CLIENTS
(
clientId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
fullName VARCHAR(60),
street VARCHAR(60),
suburb VARCHAR(50),
city VARCHAR(20),
country VARCHAR(20),
phone VARCHAR(15),
dateCreated VARCHAR(60),
clientBalance double,
companyId INTEGER,
CONSTRAINT clients_pk PRIMARY KEY (clientId)
);

CREATE TABLE COMPANY
(
companyId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
name VARCHAR(60),
description VARCHAR(60),
street VARCHAR(60),
suburb VARCHAR(50),
city VARCHAR(20),
country VARCHAR(20),
phone VARCHAR(15),
CONSTRAINT company_pk PRIMARY KEY (companyId)
);

CREATE TABLE ADMIN
(
adminId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
username VARCHAR(60),
password VARCHAR(60),
passwordHint VARCHAR(60),
adminBalance double,
totalDeposited double,
totalWithdrawn double,
companyId INTEGER,
masterUsername  VARCHAR(60),
masterPassword VARCHAR(60),
dateCreated VARCHAR(60),
CONSTRAINT admin_pk PRIMARY KEY (adminId)
);





CREATE TABLE DEPOSIT
(
depositId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
clientId INTEGER,
depositAmount double,
timestamps VARCHAR(60),
comments VARCHAR(250),
CONSTRAINT deposit_pk PRIMARY KEY (depositId)
);



//withdraw

CREATE TABLE WITHDRAW
(
withdrawId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
clientId INTEGER,
withdrawAmount double,
timestamps VARCHAR(60),
comments VARCHAR(250),
CONSTRAINT withdraw_pk PRIMARY KEY (withdrawId)
);


//transfer
CREATE TABLE TRANSFER
(
transferId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
clientIdFrom INTEGER,
clientIdTo INTEGER,
transferAmount double,
timestamps VARCHAR(60),
comments VARCHAR(250),
CONSTRAINT transfer_pk PRIMARY KEY (transferId)
);



CREATE TABLE TRANSACTIONTYPE
(
transactionTypeId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
transctionTypeName VARCHAR(60),
CONSTRAINT transactionType_pk PRIMARY KEY (transactionTypeId)
);

CREATE TABLE TRANSACTIONS
(
transactionId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
transactionTypeId INTEGER,
clientId INTEGER,
handlerId INTEGER,
timestamps VARCHAR(60),
CONSTRAINT transaction_pk PRIMARY KEY (transactionId)
);










