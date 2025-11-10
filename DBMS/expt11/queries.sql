CREATE TABLE table1(a1 INT);
CREATE TABLE table2(a2 INT);
CREATE TABLE table3(a3 INT);
CREATE TABLE table4(a4 INT, b4 INT);

DELIMITER $$

CREATE TRIGGER trg_table1_changes
AFTER INSERT ON table1
FOR EACH ROW
BEGIN
    INSERT INTO table2(a2) VALUES (NEW.a1);

    DELETE FROM table3 WHERE a3 = NEW.a1;

    UPDATE table4 SET b4 = b4 + 1 WHERE a4 = NEW.a1;
END $$

DELIMITER ;


CREATE TABLE emp_proj(
    empid INT,
    num_projects INT,
    total_hrs FLOAT
);

CREATE TABLE proj_info(
    proj_no INT,
    num_employees INT,
    total_hrs FLOAT
);

DELIMITER $$

CREATE TRIGGER trg_workson_delete
AFTER DELETE ON workson
FOR EACH ROW
BEGIN
    UPDATE emp_proj
    SET num_projects = num_projects - 1,
        total_hrs = total_hrs - OLD.hours_per_week
    WHERE empid = OLD.eno;

    UPDATE proj_info
    SET num_employees = num_employees - 1,
        total_hrs = total_hrs - OLD.hours_per_week
    WHERE proj_no = OLD.pno;
END $$

DELIMITER ;
