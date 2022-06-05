create database EDUC
on
(
name = "EDUC",
filename = "D:\jxgl\EDUC.mdf",
size = 10MB,
maxsize = 60MB,
filegrowth = 5%
)
log on
(
name = "EDUC_log",
filename = "D:\jxgl\EDUC_log.ldf",
size = 4MB,
maxsize = 10MB,
filegrowth = 1MB
)