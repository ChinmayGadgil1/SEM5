create view view1 as select * from employee where deptno=(select deptno from dept where dname='admin');
SELECT TABLE_NAME, IS_UPDATABLE FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_SCHEMA = 'company' AND TABLE_NAME = 'view1';

create view view2 as select proj_num,pname,project.location,empname,dname,salary from employee natural join dept join workson on empno=eno join project on pno=proj_num;
SELECT TABLE_NAME, IS_UPDATABLE FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_SCHEMA = 'company' AND TABLE_NAME = 'view2';

create view view3 as select deptno,dname,count(empno)as no_of_emps,avg(salary) from employee natural join dept group by deptno;
SELECT TABLE_NAME, IS_UPDATABLE FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_SCHEMA = 'company' AND TABLE_NAME = 'view3';

create view view4 as select empname,empno,salary,pno,pname as works_on,hours_per_week from employee join workson on empno=eno join project on pno=proj_num;

create view view5 as select pno,count(empno) as no_of_emps,sum(hours_per_week) as tot_time from view4 group by pno; 
