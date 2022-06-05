use EDUC

go
create trigger inst_cursor
on student
after insert
as
begin
declare @ClsNo char(6)
declare @num int
declare cur cursor for select ClsNo from inserted
open cur
fetch next from cur into @ClsNo
while @@FETCH_STATUS = 0
	begin
	if @ClsNo is not null
		select @num = c_total from class where ClsNO = @ClsNo
		if @num is null
			update class
			set c_total = 1
			where ClsNO = @ClsNo
		else
		update class
		set c_total = c_total + 1
		where ClsNO = @ClsNo
	fetch next from cur into @ClsNo
	end
	close cur
	deallocate cur
end
go