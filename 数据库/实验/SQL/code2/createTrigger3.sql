use EDUC

go
create trigger t_update_stu
on student
after update
as
begin
declare @ClsNo1 char(6)
declare @num1 int
declare @ClsNo2 char(6)
declare @num2 int
if exists(select * from deleted where ClsNO is not null)
	set @ClsNo1 = (select ClsNo from deleted)
	set @num1 = (select c_total from class where ClsNO = @ClsNo1)
	if @num1 is not null
		update class
		set c_total = c_total - 1
		where ClsNO = @ClsNo1;
if exists(select * from inserted where ClsNO is not null)
	set @ClsNo2 = (select ClsNo from inserted)
	set @num2 = (select c_total from class where ClsNO = @ClsNo2)
	if @num2 is null
		update class
		set c_total = 1
		where ClsNO = @ClsNo2
	else
		update class
		set c_total = c_total + 1
		where ClsNO = @ClsNo2;
end
go