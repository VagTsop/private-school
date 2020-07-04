
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Course;

public class CourseDao extends SuperDao {

	private static final String FIND_ALL = "select * from course";
	private static final String FIND_BY_ID = "select * from course where cid = ?";
	private static final String INSERT_COURSE = "insert into course (ctitle, cstream, ctype, startDate, endDate) values (?, ?, ?, ?, ?)";

	public List<Course> findAll() {

		List<Course> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConn().prepareStatement(FIND_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate()));
			}
		} catch (SQLException ex) {
			Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return list;
	}

	public Course findById(int id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConn().prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();

			return new Course(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getDate(5).toLocalDate(), rs.getDate(6).toLocalDate());
		} catch (SQLException ex) {
			Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	public void create(Course c) {
		PreparedStatement ps = null;
		try {
			ps = getConn().prepareStatement(INSERT_COURSE);
			ps.setString(1, c.getTitle());
			ps.setString(2, c.getStream());
			ps.setString(3, c.getType());
			ps.setDate(4, Date.valueOf(c.getStartDate()));
			ps.setDate(5, Date.valueOf(c.getEndDate()));
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
