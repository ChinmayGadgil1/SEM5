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

DELIMITER $$

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

    UPDATE employee
    SET salary = new_sal
    WHERE Empno = empid;

    SELECT empid AS EmpNo, sal AS Old_Salary, c AS No_of_Projects, new_sal AS New_Salary;
END $$

DELIMITER ;


DELIMITER $$

CREATE PROCEDURE update_salary_for_all()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE empid INT;

    DECLARE emp_cursor CURSOR FOR SELECT Empno FROM employee;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN emp_cursor;

    read_loop: LOOP
        FETCH emp_cursor INTO empid;
        IF done THEN
            LEAVE read_loop;
        END IF;
        CALL update_salary_for_employee(empid);
    END LOOP;

    CLOSE emp_cursor;
END $$

DELIMITER ;


DELIMITER ;
