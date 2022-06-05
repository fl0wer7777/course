use EDUC

go
create view V_SC_G(Sno, Sname, Cno, Cname, Score)
as
select student.Sno, Sname, course.Cno, Cname, Score
from student, course, sc
where student.Sno = sc.Sno
and course.Cno = sc.CNO;
go

go
create view V_YEAR(Sno, Syear)
as
select student.Sno, (DATEPART(yy, getdate()) - student.Sage)
from student;
go

go
create view V_AVG_S_G(Sno, Cnum, avgScore)
as
select Sno, count(Cno), avg(Score)
from sc
group by Sno;
go

go
create view V_AVG_C_G(Cno, Cnum, avgScore)
as
select Cno, count(Sno), avg(Score)
from sc
group by Cno;
go