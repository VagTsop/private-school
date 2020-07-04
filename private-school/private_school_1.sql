create database private_school;
use private_school;

create table student (
	sid int unsigned auto_increment primary key,
    sfname varchar(10) not null,
    slname varchar(10) not null,
    dob date,
    tuitionFees decimal(6,2)
);

create table course (
	cid int unsigned auto_increment primary key,
    ctitle varchar(20) not null,
    cstream varchar(4) not null,
    ctype varchar(10) not null,
    startDate date,
    endDate date
);

create table trainer (
	tid int unsigned auto_increment primary key,
    tfname varchar(10) not null,
    tlname varchar(10) not null,
    subject varchar(10)
);

create table assignment (
	aid int unsigned auto_increment primary key,
    title varchar(10) not null,
    descr varchar(45),
    submitDate date not null,
    oralMax int not null,
    totalMax int not null,
    cid int unsigned,
    foreign key (cid) references course(cid)
);

create table studentPerCourse (
	sid int unsigned,
    cid int unsigned,
    primary key (sid, cid),
    foreign key (sid) references student(sid),
    foreign key (cid) references course(cid)
);

create table trainerPerCourse (
	tid int unsigned,
    cid int unsigned,
    primary key (tid, cid),
    foreign key (tid) references trainer(tid),
    foreign key (cid) references course(cid)
);

create table grade (
	gid int unsigned auto_increment primary key,
    oralMark int not null,
    totalMark int not null,
	sid int unsigned,
    aid int unsigned,
    foreign key (sid) references student(sid),
    foreign key (aid) references assignment(aid)
);

/* Students */
insert into student (sfname, slname, dob, tuitionFees)
values ("John", "Black", "20010101", 1500);
insert into student (sfname, slname, dob, tuitionFees)
values ("Jack", "White", "20020202", 1600);
insert into student (sfname, slname, dob, tuitionFees)
values ("Nick", "Smith", "20030303", 1700);
insert into student (sfname, slname, dob, tuitionFees)
values ("Frank", "Jackson", "20040404", 1800);

/* Courses */
insert into course (ctitle, cstream, ctype, startDate, endDate)
values ("CB9", "Java", "full", "20191014", "20200122");
insert into course (ctitle, cstream, ctype, startDate, endDate)
values ("CB9", "Java", "part", "20191014", "20200417");
insert into course (ctitle, cstream, ctype, startDate, endDate)
values ("CB9", "C#", "part", "20191014", "20200417");
insert into course (ctitle, cstream, ctype, startDate, endDate)
values ("CB9", "C#", "part", "20191014", "20200417");

/* Trainers */
insert into trainer (tfname, tlname, subject)
values ("Tasos", "Lelakis", "Java");
insert into trainer (tfname, tlname, subject)
values ("Giorgos", "Pasparakis", "C#");
insert into trainer (tfname, tlname, subject)
values ("Kostas", "Minaidis", "Front End");

/* Assignments */
insert into assignment (title, descr, submitDate, oralMax, totalMax, cid)
values ("Part A", null, "20191104", 20, 100, 1);
insert into assignment (title, descr, submitDate, oralMax, totalMax, cid)
values ("Part B", null, "20191118", 15, 100, 1);

/* StudentPerCourse */
insert into studentpercourse (sid, cid)
values (1, 1);
insert into studentpercourse (sid, cid)
values (2, 3);
insert into studentpercourse (sid, cid)
values (3, 1);	
insert into studentpercourse (sid, cid)
values (3, 4);
insert into studentpercourse (sid, cid)
values (4, 1);

/* TrainerPerCourse */
insert into trainerpercourse (tid, cid)
values (1, 1);
insert into trainerpercourse (tid, cid)
values (2, 1);
insert into trainerpercourse (tid, cid)
values (3, 1);
insert into trainerpercourse (tid, cid)
values (1, 2);
insert into trainerpercourse (tid, cid)
values (2, 2);
insert into trainerpercourse (tid, cid)
values (2, 3);
insert into trainerpercourse (tid, cid)
values (3, 3);

/* Grades */
insert into grade (oralMark, totalMark, sid, aid)
values (15, 85, 1, 1);
insert into grade (oralMark, totalMark, sid, aid)
values (15, 85, 3, 1);
insert into grade (oralMark, totalMark, sid, aid)
values (15, 55, 4, 1);
insert into grade (oralMark, totalMark, sid, aid)
values (7, 79, 1, 2);
insert into grade (oralMark, totalMark, sid, aid)
values (3, 46, 3, 2);
