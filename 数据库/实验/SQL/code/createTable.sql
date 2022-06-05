create table class
(
ClsNO char(6) not null primary key,
ClsName Varchar(16) not null,
Director varchar(10),
Specialty Varchar(30)
)

create table student 
(
Sno Char(8) not null primary key,
Sname Varchar(10) not null,
Sex char(2) check(Sex in('ÄĞ', 'Å®')),
ClsNO char(6),
Saddr varchar(20),
Sage numeric(3,0) check (Sage > 10 and Sage < 30),
Height Decimal(4,2),
Sdept varchar(30),
foreign key (ClsNO) references class(ClsNO)
)

create table course
(
Cno char(4) not null primary key,
Cname varchar(16) not null,
Cpno char(4),
Ccredit Tinyint,
foreign key(Cpno) references course(Cno)
)

create table sc
(
	Sno char(8) not null,
	CNO char(4) not null,
	Score Numeric(4,1) 
	primary key(Sno,Cno),
	foreign key(Sno) references Student(Sno),
	foreign key(Cno) references course(Cno)
)