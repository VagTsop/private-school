
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Assignment;

public class AssignmentPerCourseDao extends SuperDao {

	private static final String FIND_BY_ID = "select * from assignment where cid = ?";

	public List<Assignment> findById(int id) {

		List<Assignment> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConn().prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Assignment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getInt(5), rs.getInt(6), rs.getInt(7)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(AssignmentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(AssignmentPerCourseDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return list;
	}
}
