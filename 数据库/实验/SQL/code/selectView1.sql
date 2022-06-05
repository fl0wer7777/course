use EDUC

select distinct V_SC_G.Sno, Sname, V_AVG_S_G.avgScore
from V_SC_G, V_AVG_S_G
where V_SC_G.Sno = V_AVG_S_G.Sno
and V_AVG_S_G.avgScore >= 90;

select distinct V_SC_G.Sno, V_SC_G.Cno, Score, avgScore
from V_SC_G, V_AVG_C_G
where not exists
		(select *
		from V_AVG_C_G
		where V_SC_G.Score <= V_AVG_C_G.avgScore
		and V_SC_G.Cno = V_AVG_C_G.Cno)
and V_SC_G.Cno = V_AVG_C_G.Cno;

select distinct V_SC_G.Sno, Sname
from V_SC_G, V_YEAR
where V_SC_G.Sno = V_YEAR.Sno
and V_YEAR.Syear = 1995;