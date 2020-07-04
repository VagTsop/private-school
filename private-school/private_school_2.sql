use private_school;

/* A list of all the students */
select * from student;

/* A list of all the trainers */
select * from trainer;

/* A list of all the assignments */
select * from assignment;

/* A list of all the courses */
select * from course;

/* All the students per course */
select c.ctitle, c.cstream, c.ctype, s.sfname, s.slname from course c, studentpercourse spc, student s
where c.cid = spc.cid and spc.sid = s.sid;

/* All the trainers per course */
select c.ctitle, c.cstream, c.ctype, t.tfname, t.tlname, t.subject from course c, trainerpercourse tpc, trainer t
where c.cid = tpc.cid and tpc.tid = t.tid;

/* All the assignments per course */
select * from course, assignment
where course.cid = assignment.cid;

/* All the assignments per course per student */
select * from student, grade, assignment, course
where student.sid = grade.sid and grade.aid = assignment.aid and assignment.cid = course.cid;

/* A list of students that belong to more than one courses */
select * from student
where sid in (
select sid from
(select student.sid, count(*) as registeredCourses from student, studentpercourse, course
where student.sid = studentpercourse.sid and studentpercourse.cid = course.cid
group by student.sid) as courseCount
where registeredCourses > 1);