insert into clazz (id, name) VALUES (1, 'Lớp 1A');
insert into clazz (id, name) VALUES (2, 'Lớp 1B');

insert into student_profile (id, cccd, email) values (1, '101010', 'acd@gmail.com');
insert into student_profile (id, cccd, email) values (2, '202020', 'bbb@gmail.com');
insert into student_profile (id, cccd, email) values (3, '303030', 'ccc@gmail.com');


insert into student (id, name, score, student_profile_id, clazz_id) values (1, 'Thịnh', 9.6, 1, 1);
insert into student (id, name, score, student_profile_id, clazz_id) values (2, 'Điệp', 9.5, 2, 2);
insert into student (id, name, score, student_profile_id, clazz_id) values (3, 'Bảo', 9.7, 3, 1);