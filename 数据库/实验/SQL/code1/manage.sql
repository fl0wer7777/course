use EDUC

go
create login lxf
with password = ' ', default_database = EDUC;
go

go
create user lxf
for login lxf;
go

go
create role EducQueryRole;
go