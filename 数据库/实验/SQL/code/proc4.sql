use EDUC

go
create proc p_stu_grade(@Sno char(8))
as
begin
select Sname, CNO, Score
from student, sc
where student.Sno = sc.Sno
and sc.Sno = @Sno;
end
go

exec p_stu_grade '20171001';