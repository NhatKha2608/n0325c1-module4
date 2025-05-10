
create table department (
                            id int primary key auto_increment,
                            name varchar(50)
);

create table employee(
                         id int primary key auto_increment,
                         name varchar(50),
                         dob date,
                         gender varchar(10),
                         salary double,
                         phone varchar(20),
                         department_Id int,
                         foreign key (department_Id) references department(id)
);

