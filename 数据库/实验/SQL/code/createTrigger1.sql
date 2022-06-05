use EDUC

go
create trigger t_inst_stu
on student
for insert
as
begin
if exists(select * from inserted where ClsNO <> '')
	update class
	set c_total = c_total + 1
	where ClsNO = (select ClsNO from inserted);
end
go