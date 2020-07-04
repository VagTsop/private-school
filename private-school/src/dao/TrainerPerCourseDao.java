
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Trainer;

public class TrainerPerCourseDao extends SuperDao {

	private static final String FIND_BY_ID = "select * from trainer " + "where tid in "
			+ "(select tid from trainerpercourse, course"
			+ " where trainerpercourse.cid = course.cid and course.cid = ?)";

	public List<Trainer> findById(int id) {

		List<Trainer> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConn().prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Trainer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(TrainerPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
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
