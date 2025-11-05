CREATE USER 'emp1'@'localhost' IDENTIFIED BY 'emp1@123';
CREATE USER 'emp2'@'localhost' IDENTIFIED BY 'emp2@123';
CREATE USER 'emp3'@'localhost' IDENTIFIED BY 'emp3@123';

GRANT ALL PRIVILEGES ON company.view1 TO 'emp1'@'localhost';

GRANT SELECT , UPDATE (empname, deptno) ON company.employee TO 'emp2'@'localhost' WITH GRANT OPTION;

GRANT SELECT (empname, deptno), UPDATE (empname, deptno) ON company.employee TO 'emp1'@'localhost';

GRANT ALL PRIVILEGES ON *.* TO 'emp3'@'localhost' WITH GRANT OPTION;


SHOW GRANTS FOR 'emp1'@'localhost';
SHOW GRANTS FOR 'emp2'@'localhost';
SHOW GRANTS FOR 'emp3'@'localhost';


REVOKE ALL PRIVILEGES ON company.view1 FROM 'emp1'@'localhost';

SHOW PRIVILEGES;

CREATE INDEX empid_index ON company.employee(empno);

SELECT user, host, Select_priv, Insert_priv, Update_priv, Delete_priv, Create_priv, Drop_priv, Grant_priv, Alter_priv, Execute_priv FROM mysql.user;

