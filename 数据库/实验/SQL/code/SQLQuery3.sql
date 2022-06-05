USE EDUC 
GO

--CREATE TRIGGER T
--ON student
--AFTER INSERT
--AS
--BEGIN
--	DECLARE @NUM INT;
--	SELECT @NUM = COUNT(*) FROM student;
--	IF EXISTS(SELECT * FROM inserted)
--	UPDATE class SET c_total = @NUM;
--END 
--GO

select *
from class

insert
into student
values ('20171112', 'Ò¼', 'ÄÐ', 'SW01', null, 20, 1.75, null);

select *
from class