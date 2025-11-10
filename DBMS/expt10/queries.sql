DELIMITER $$

CREATE PROCEDURE get_all_projects()
BEGIN
    SELECT * FROM project;
END $$

CREATE PROCEDURE get_employee_details(IN empid INT)
BEGIN
    SELECT * FROM employee WHERE Empno = empid;
END $$

CREATE PROCEDURE get_project_info(IN project_no INT)
BEGIN
    SELECT dnum AS dept_no, COUNT(eno) AS no_of_employees
    FROM project, workson
    WHERE Proj_num = project_no AND Proj_num = pno
    GROUP BY dnum;
END $$

CREATE PROCEDURE update_salary_for_employee(IN empid INT)
BEGIN
    DECLARE sal FLOAT;
    DECLARE c INT;
    DECLARE new_sal FLOAT;

    SELECT salary INTO sal FROM employee WHERE Empno = empid;
    SELECT COUNT(*) INTO c FROM workson WHERE eno = empid;

    IF sal > 50000 AND c > 2 THEN
        SET new_sal = sal * 1.05;
    ELSEIF sal >= 50000 AND sal <= 60000 AND c > 2 THEN
        SET new_sal = sal * 1.02;
    ELSEIF sal > 100000 AND c >= 1 THEN
        SET new_sal = sal * 1.01;
    ELSE
        SET new_sal = sal;
    END IF;

    SELECT empid AS EmpNo, sal AS Old_Salary, c AS No_of_Projects, new_sal AS New_Salary;
END $$

CREATE PROCEDURE update_salary_for_all()
BEGIN
    SELECT e.Empno, e.salary AS Old_Salary, COUNT(w.pno) AS No_of_Projects,
    CASE
        WHEN e.salary > 50000 AND COUNT(w.pno) > 2 THEN e.salary * 1.05
        WHEN e.salary >= 50000 AND e.salary <= 60000 AND COUNT(w.pno) > 2 THEN e.salary * 1.02
        WHEN e.salary > 100000 AND COUNT(w.pno) >= 1 THEN e.salary * 1.01
        ELSE e.salary
    END AS New_Salary
    FROM employee e
    LEFT JOIN workson w ON e.Empno = w.eno
    GROUP BY e.Empno;
END $$

DELIMITER ;
