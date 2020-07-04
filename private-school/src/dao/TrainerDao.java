
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Trainer;

public class TrainerDao extends SuperDao {

	private static final String FIND_ALL = "select * from trainer";
	private static final String INSERT_TRAINER = "insert into trainer (tfname, tlname, subject) values (?, ?, ?)";

	public List<Trainer> findAll() {

		List<Trainer> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConn().prepareStatement(FIND_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Trainer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(TrainerDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return list;
	}

	public void create(Trainer t) {
		PreparedStatement ps = null;
		try {
			ps = getConn().prepareStatement(INSERT_TRAINER);
			ps.setString(1, t.getTfname());
			ps.setString(2, t.getTlname());
			ps.setString(3, t.getSubject());
		} catch (SQLException ex) {
			Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
