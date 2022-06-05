use EDUC

select count(Sno) as total
from student;

select count(distinct Sno) as total
from sc;

select Cno, count(Sno) as total
from sc
group by Cno;

select sc.Sno, Sname
from sc, student
group by sc.Sno, student.Sno, Sname
having count(Cno) > 2
and sc.Sno = student.Sno;

select distinct Sname
from sc, student
where not exists
		(select *
		from sc
		where Sno = student.Sno and Cno = '0001');

select Sname
from student
where not exists
		(select *
		from course
		where not exists
				(select *
				from sc
				where Sno = student.Sno
				and Cno = course.Cno));

select distinct Sno
from sc x
where not exists
		(select *
		from sc y
		where y.Sno = '20170102'
		and not exists
				(select *
				from sc z
				where z.Sno = x.Sno
				and z.Cno = y.Cno));

select Cno
from sc, student
where sc.Sno = student.Sno
and Sname = 'Íõ¾ü'
intersect
select Cno
from sc, student
where sc.Sno = student.Sno
and Sname = 'Àî½Ü';