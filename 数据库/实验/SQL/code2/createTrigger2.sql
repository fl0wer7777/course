use EDUC

go
create trigger t_dele_stu
on student
after delete
as
begin
declare @ClsNo char(6)
declare @num int
if exists(select * from deleted where ClsNO is not null)
	set @ClsNo = (select ClsNo from deleted)
	set @num = (select c_total from class where ClsNO = @ClsNo)
	if @num is not null
		update class
		set c_total = c_total - 1
		where ClsNO = @ClsNo;
end
go