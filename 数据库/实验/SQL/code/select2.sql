use EDUC

select Sno, Sname
from student, class
where student.ClsNO = class.ClsNO
and class.Specialty = '计算机应用';

select distinct student.Sno
from student, sc
where student.Sno = sc.Sno;

select Sno, Score * 0.75
from sc
where CNO = '0001'
and Score between 80 and 90;

select Sno, Sname, Specialty
from student, class
where student.ClsNO = class.ClsNO
and Specialty in ('计算机应用', '数学')
and Sname like '张%';



select distinct sc.Sno, Sname, Score
from student, sc
where student.Sno = sc.Sno
and student.Sname != '张三'
and Score > (select Score
			from student, sc
			where student.Sno = sc.Sno
			and student.Sname = '张三'
			and CNO = '0001');

select Sname
from student
where Sno in (select distinct Sno
			from sc
			where Cno != '0002');

select x.Cno, y.Cpno
from course x, course y
where x.Cpno = y.Cno;