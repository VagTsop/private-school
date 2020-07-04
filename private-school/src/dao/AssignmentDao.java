
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Assignment;

public class AssignmentDao extends SuperDao {

	private static final String FIND_ALL = "select * from assignment";
	private static final String INSERT_ASSIGNMENT = "insert into assignment (title, descr, submitDate, oralMax, totalMax, cid) values (?, ?, ?, ?, ?, ?)";

	public List<Assignment> findAll() {

		List<Assignment> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConn().prepareStatement(FIND_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Assignment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getInt(5), rs.getInt(6), rs.getInt(7)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return list;
	}

	public void create(Assignment a) {
		PreparedStatement ps = null;
		try {
			ps = getConn().prepareStatement(INSERT_ASSIGNMENT);
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getDescr());
			ps.setDate(3, Date.valueOf(a.getSubmitDate()));
			ps.setInt(4, a.getOralMax());
			ps.setInt(5, a.getTotalMax());
			ps.setInt(6, a.getCourse());
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(AssignmentDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
