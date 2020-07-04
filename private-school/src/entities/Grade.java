
package entities;

public class Grade {

	private int gid;
	private int oralMark;
	private int totalMark;
	private int sid;
	private int aid;

	public Grade() {
	}

	public Grade(int oralMark, int totalMark, int sid, int aid) {
		this.oralMark = oralMark;
		this.totalMark = totalMark;
		this.sid = sid;
		this.aid = aid;
	}

	public Grade(int gid, int oralMark, int totalMark, int sid, int aid) {
		this.gid = gid;
		this.oralMark = oralMark;
		this.totalMark = totalMark;
		this.sid = sid;
		this.aid = aid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getOralMark() {
		return oralMark;
	}

	public void setOralMark(int oralMark) {
		this.oralMark = oralMark;
	}

	public int getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(int totalMark) {
		this.totalMark = totalMark;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 79 * hash + this.gid;
		hash = 79 * hash + this.oralMark;
		hash = 79 * hash + this.totalMark;
		hash = 79 * hash + this.sid;
		hash = 79 * hash + this.aid;
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
		final Grade other = (Grade) obj;
		if (this.gid != other.gid) {
			return false;
		}
		if (this.oralMark != other.oralMark) {
			return false;
		}
		if (this.totalMark != other.totalMark) {
			return false;
		}
		if (this.sid != other.sid) {
			return false;
		}
		if (this.aid != other.aid) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return gid + ". " + oralMark + " " + totalMark + " " + sid + " " + aid;
	}

}
