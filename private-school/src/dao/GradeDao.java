
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Grade;

public class GradeDao extends SuperDao {

	private static final String FIND_BY_ID = "select * from grade where sid = ? and aid in (select aid from assignment where cid = ?)";
	private static final String INSERT_GRADE = "insert into grade (oralMark, totalMark, sid, aid) values (?, ?, ?, ?)";

	public List<Grade> findById(int sid, int cid) {

		List<Grade> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConn().prepareStatement(FIND_BY_ID);
			ps.setInt(1, sid);
			ps.setInt(2, cid);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Grade(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(GradeDao.class.getName()).log(Level.SEVERE, null, ex);
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

	public void create(Grade g) {
		PreparedStatement ps = null;
		try {
			ps = getConn().prepareStatement(INSERT_GRADE);
			ps.setInt(1, g.getOralMark());
			ps.setInt(2, g.getTotalMark());
			ps.setInt(3, g.getSid());
			ps.setInt(4, g.getAid());
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(GradeDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(GradeDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
