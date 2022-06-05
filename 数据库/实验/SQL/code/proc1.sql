use EDUC

go
create proc p_stu_info1
as
begin
select *
from student, sc
where student.Sno = sc.Sno
and Sage < 21
and Sex = 'ÄÐ';
end
go

exec p_stu_info1;