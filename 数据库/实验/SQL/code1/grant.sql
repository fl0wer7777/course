use EDUC

grant select
on student
to EducQueryRole;

grant insert, update
on course
to EducQueryRole;

grant select, insert, update, delete
on class
to EducQueryRole;

grant select, insert, update, delete
on student
to lxf;

--grant all privileges
--on class
--to EducQueryRole;

--grant all privileges
--on student
--to lxf;

exec sp_addrolemember 'EducQueryRole', 'lxf';