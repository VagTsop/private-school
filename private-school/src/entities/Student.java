
package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Student {

	private int sid;
	private String sfname;
	private String slname;
	private LocalDate dob;
	private double tuitionFees;

	public Student() {
	}

	public Student(String sfname, String slname, LocalDate dob, double tuitionFees) {
		this.sfname = sfname;
		this.slname = slname;
		this.dob = dob;
		this.tuitionFees = tuitionFees;
	}

	public Student(int sid, String sfname, String slname, LocalDate dob, double tuitionFees) {
		this.sid = sid;
		this.sfname = sfname;
		this.slname = slname;
		this.dob = dob;
		this.tuitionFees = tuitionFees;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSfname() {
		return sfname;
	}

	public void setSfname(String sfname) {
		this.sfname = sfname;
	}

	public String getSlname() {
		return slname;
	}

	public void setSlname(String slname) {
		this.slname = slname;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public double getTuitionFees() {
		return tuitionFees;
	}

	public void setTuitionFees(double tuitionFees) {
		this.tuitionFees = tuitionFees;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 73 * hash + this.sid;
		hash = 73 * hash + Objects.hashCode(this.sfname);
		hash = 73 * hash + Objects.hashCode(this.slname);
		hash = 73 * hash + Objects.hashCode(this.dob);
		hash = 73 * hash + (int) (Double.doubleToLongBits(this.tuitionFees)
				^ (Double.doubleToLongBits(this.tuitionFees) >>> 32));
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Student other = (Student) obj;
		if (this.sid != other.sid) {
			return false;
		}
		if (Double.doubleToLongBits(this.tuitionFees) != Double.doubleToLongBits(other.tuitionFees)) {
			return false;
		}
		if (!Objects.equals(this.sfname, other.sfname)) {
			return false;
		}
		if (!Objects.equals(this.slname, other.slname)) {
			return false;
		}
		if (!Objects.equals(this.dob, other.dob)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return sid + ". " + sfname + " " + slname + ", " + dob + ", " + tuitionFees;
	}

}
