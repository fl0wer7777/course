use EDUC

insert
into student
values
('20171101', 'ÕÅÈı', 'ÄĞ', 'CS01', null, 19, null, null)

update student 
set ClsNO = 'CS02'
where ClsNO = 'CS01' and Sage <= 20;

delete
from student 
where ClsNO = 'CS02' and Sage >= 20;