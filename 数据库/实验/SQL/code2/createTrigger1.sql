use EDUC

go
create trigger t_inst_stu
on student
after insert
as
begin
declare @ClsNo char(6)
declare @num int
if exists(select * from inserted where ClsNO is not null)
	set @ClsNo = (select ClsNo from inserted)
	set @num = (select c_total from class where ClsNO = @ClsNo)
	if @num is null
		update class
		set c_total = 1
		where ClsNO = @ClsNo
	else
		update class
		set c_total = c_total + 1
		where ClsNO = @ClsNo;
end
go