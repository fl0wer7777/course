use EDUC

go
create proc p_stu_info2(@Sno char(8))
as
begin
select *
from student
where Sage = (select Sage
			from student
			where Sno = @Sno)
and Sno != @Sno;
end
go

exec p_stu_info2 '20171001';