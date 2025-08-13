create table Project(
    Pno int,
    Pname varchar(25) NOT NULL UNIQUE,
    location varchar(25),
    dnum int,
    PRIMARY KEY (Pno),
    foreign key (dnum) references dept(deptno)
);

create table works_on(
    eno int,
    pno int,
    hours_per_week float,
    PRIMARY KEY (eno, pno),
    foreign key (eno) references Employee(Empno),
    foreign key (pno) references Project(Pno)
);

ALTER TABLE Employee ADD CONSTRAINT fk_deptno FOREIGN KEY (deptno) REFERENCES dept(deptno);
ALTER TABLE Employee ADD COLUMN bdate DATE;
ALTER table dept add UNIQUE (dname);
alter table works_on alter column hours_per_week set default 20;
alter table Project change column Pno Proj_num int;

