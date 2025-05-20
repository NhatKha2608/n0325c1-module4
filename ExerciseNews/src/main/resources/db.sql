create database employee_management;
use employee_management;
create table employee(
                         id int primary key auto_increment,
                         name varchar(50),
                         dob date,
                         gender varchar(10),
                         salary double,
                         phone varchar(20),
                         departmentId int,
                         foreign key (departmentId) references department(id)
);

create table department (
                            id int primary key auto_increment,
                            name varchar(50)
);

insert into department values (1,'Quản lí');
insert into department values (2,'Kế toán');
insert into department values (3,'Sale-Marketing');
insert into department values (4,'Sản xuất');

insert into Employee value (1, 'Hoàng Văn Hải', '1990-01-15', 'MALE', 15000000.00, '0975123542', 1);
insert into Employee value (2, 'Trần Thị Hoài', '1985-05-20','FEMALE', 14500000.00, '096786968', 2);
insert into Employee value (3, 'Lê Văn Sỹ', '1992-03-10', 'MALE', 15500000.00, '0988881110', 3);
insert into Employee value (4, 'Phạm Duy Khánh', '1988-07-15', 'FEMALE', 14800000.00, '0986555333', 4);
insert into Employee value (5, 'Hoàng Văn Nam', '1995-09-25', 'MALE', 15200000.00, '0973388668', 4);


select *from employee;