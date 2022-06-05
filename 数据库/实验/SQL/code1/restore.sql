use EDUC

restore database tt
from disk = 'e:\EDUC2.bak'
with
move 'EDUC' to 'e:\tt.mdf',
move 'EDUC_log' to 'e:\tt_log.ldf';