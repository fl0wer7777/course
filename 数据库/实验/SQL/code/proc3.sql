use EDUC

go
create proc p_stu_info3 (@Sno char(8))
as
begin
select *
from student
where Sno = @Sno;
end
go

exec p_stu_info3 '20171001';