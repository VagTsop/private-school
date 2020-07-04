/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Student;

public class StudentDao extends SuperDao {

	private static final String FIND_ALL = "select * from student";
	private static final String FIND_BY_ID = "select * from student where sid = ?";
	private static final String INSERT_STUDENT = "insert into student (sfname, slname, dob, tuitionFees) values (?, ?, ?, ?)";

	public List<Student> findAll() {

		List<Student> list = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConn().prepareStatement(FIND_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
						rs.getDouble(5)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return list;
	}

	public Student findById(int sid) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConn().prepareStatement(FIND_BY_ID);
			ps.setInt(1, sid);
			rs = ps.executeQuery();
			rs.next();

			return new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(),
					rs.getDouble(5));
		} catch (SQLException ex) {
			Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				rs.close();
				ps.close();
				getConn().close();
			} catch (SQLException ex) {
				Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return null;
	}

	public void create(Student s) {
		PreparedStatement ps = null;
		try {
			ps = getConn().prepareStatement(INSERT_STUDENT);
			ps.setString(1, s.getSfname());
			ps.setString(2, s.getSlname());
			ps.setDate(3, Date.valueOf(s.getDob()));
			ps.setDouble(4, s.getTuitionFees());
			ps.executeUpdate();
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
