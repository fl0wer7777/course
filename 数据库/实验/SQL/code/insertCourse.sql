use EDUC

insert
into course
values
('0001', '高等数学', null, 6),
('0002', '操作系统', null, 3),
('0003', '计算机基础', '0001', 3),
('0004', '数据结构', '0001', 4),
('0005', '数据库', '0004', 5),
('0006', '思修', null, 4),
('0007', '纲要', null, 4),
('0008', '马原', null, 4),
('0009', '毛概', null, 4),
('0010', '军理', null, 4),
('0011', '大学语文', null, 2),
('0012', 'LaTeX', null, 2),
('0013', '离散数学', '0001', 6),
('0014', '线性代数', '0001', 6),
('0015', '大学物理', '0001', 6)