
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Student;

public class StudentPerCourseDao extends SuperDao {

	private static final String FIND_BY_ID = "select * from student " + "where sid in "
			+ "(select sid from studentpercourse, course"
			+ " where studentpercourse.cid = course.cid and course.cid = ?);";
	private static final String ASSIGN_STUDENT_TO_COURSE = "insert into studentpercourse (sid, cid) values (?, ?)";

	private static final String FIND_COURSE_FROM_STUDENT = "select course.cid from course, studentpercourse where course.cid = studentpercourse.cid and sid = ?";
	private static final String STUDENTS_REGISTERED_TO_MORE_THAN_ONE_COURSE = "select sid from "
			+ "(select student.sid, count(*) as registeredCourses from student, studentpercourse, course "
			+ "where student.sid = studentpercourse.sid and studentpercourse.cid = course.cid "
			+ "group by student.sid) as courseCount " + "where registeredCourses > 1";

	public List<Student> findById(int id) {

		List<Student> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConn().prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getDouble(5)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return list;
	}

	public void create(int studentId, int courseId) {
		PreparedStatement ps = null;
		try {
			ps = getConn().prepareStatement(ASSIGN_STUDENT_TO_COURSE);
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public List<Integer> findCourseFromStudent(int studentId) {

		List<Integer> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConn().prepareStatement(FIND_COURSE_FROM_STUDENT);
			ps.setInt(1, studentId);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
			return list;
		} catch (SQLException ex) {
			Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	public List<Student> findStudentsRegisteredToMoreThanOneCourse() {

		List<Student> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConn().prepareStatement(STUDENTS_REGISTERED_TO_MORE_THAN_ONE_COURSE);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new StudentDao().findById(rs.getInt(1)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(StudentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return list;
	}
}
