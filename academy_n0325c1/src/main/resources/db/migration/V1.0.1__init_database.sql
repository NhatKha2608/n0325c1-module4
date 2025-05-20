
CREATE TABLE clazz
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)       NULL,
    CONSTRAINT pk_clazz PRIMARY KEY (id)
);

CREATE TABLE student
(
    id                 INT AUTO_INCREMENT NOT NULL,
    name               VARCHAR(255)       NULL,
    score              DOUBLE             NOT NULL,
    student_profile_id INT                NULL,
    clazz_id           INT                NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE TABLE student_profile
(
    id    INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255)       NULL,
    cccd  VARCHAR(255)       NULL,
    CONSTRAINT pk_studentprofile PRIMARY KEY (id)
);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_CLAZZ FOREIGN KEY (clazz_id) REFERENCES clazz (id);

ALTER TABLE student
    ADD CONSTRAINT FK_STUDENT_ON_STUDENTPROFILE FOREIGN KEY (student_profile_id) REFERENCES student_profile (id);