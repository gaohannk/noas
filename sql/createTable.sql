create database noas;
USE noas;

create table User(
	id int primary key auto_increment,
    password varchar(20),
	name varchar(20)
);

create table Administrator(
	id int primary key auto_increment,
    foreign key(id) references User(id) on update cascade on delete cascade
);

create table Student(
	id int primary key auto_increment,
    foreign key(id) references User(id) on update cascade on delete cascade
);

create table Address(
	id int primary key auto_increment,
	street varchar(100),
    city varchar(20),
	state varchar(20),
	country varchar(20),
    belongTo int,
    foreign key(belongTo) references Student(id)
);

create table Application (
	id int primary key auto_increment,
	submittedBy int,
    foreign key(submittedBy)
		references Student(id)
		on update cascade
		on delete cascade,
	decisionStatus enum(
		'offer',
		'admission',
        'reject',
        'pending') not null,
	isPaid boolean
);

create table ApplicationFee (
	id int primary key auto_increment,
    price int,
	paidBy int,
    paidFor int,
    foreign key(paidBy) references Student(id) on update cascade on delete cascade,
	foreign key(paidFor) references Application(id) on update cascade on delete cascade
);

create table Statement (
	id int primary key auto_increment,
	partOf int,
	score Double not null,
    foreign key(partOf) references Application(id) on update cascade on delete cascade,
	testName enum(
		'toefl',
		'gre',
		'ielts') not null,
    statement varchar(1000),
    resume varchar(1000),
    foreign key(partOf) references Application(id) on update cascade on delete cascade
);


create table Access(
	accessBy int,
    access int,
    password varchar(20),
    primary key(accessBy,access) 
);

create table DecisionMake(
	madeBy int,
    make int,
    primary key(madeBy,make) 
);

