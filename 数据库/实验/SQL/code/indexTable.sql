use EDUC

create index Stusname
on student(Sname DESC)

create unique index Coucname
on course(Cname)

create index SCno
on sc(Sno ASC, Cno ASC, Score DESC)

exec sp_helpindex student
exec sp_helpindex course
exec sp_helpindex sc

drop index student.Stusname

exec sp_helpindex student
exec sp_helpindex course
exec sp_helpindex sc