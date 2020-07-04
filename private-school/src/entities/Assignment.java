
package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Assignment {

	private int aid;
	private String title;
	private String descr;
	private LocalDate submitDate;
	private int oralMax;
	private int totalMax;
	private int cid;

	public Assignment() {
	}

	public Assignment(String title, String descr, LocalDate submitDate, int oralMax, int totalMax, int cid) {
		this.title = title;
		this.descr = descr;
		this.submitDate = submitDate;
		this.oralMax = oralMax;
		this.totalMax = totalMax;
		this.cid = cid;
	}

	public Assignment(int aid, String title, String descr, LocalDate submitDate, int oralMax, int totalMax, int cid) {
		this.aid = aid;
		this.title = title;
		this.descr = descr;
		this.submitDate = submitDate;
		this.oralMax = oralMax;
		this.totalMax = totalMax;
		this.cid = cid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public LocalDate getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}

	public int getOralMax() {
		return oralMax;
	}

	public void setOralMax(int oralMax) {
		this.oralMax = oralMax;
	}

	public int getTotalMax() {
		return totalMax;
	}

	public void setTotalMax(int totalMax) {
		this.totalMax = totalMax;
	}

	public int getCourse() {
		return cid;
	}

	public void setCourse(int cid) {
		this.cid = cid;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 13 * hash + this.aid;
		hash = 13 * hash + Objects.hashCode(this.title);
		hash = 13 * hash + Objects.hashCode(this.descr);
		hash = 13 * hash + Objects.hashCode(this.submitDate);
		hash = 13 * hash + this.oralMax;
		hash = 13 * hash + this.totalMax;
		hash = 13 * hash + this.cid;
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
		final Assignment other = (Assignment) obj;
		if (this.aid != other.aid) {
			return false;
		}
		if (this.oralMax != other.oralMax) {
			return false;
		}
		if (this.totalMax != other.totalMax) {
			return false;
		}
		if (!Objects.equals(this.title, other.title)) {
			return false;
		}
		if (!Objects.equals(this.descr, other.descr)) {
			return false;
		}
		if (!Objects.equals(this.submitDate, other.submitDate)) {
			return false;
		}
		if (!Objects.equals(this.cid, other.cid)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return aid + ". " + title + " " + descr + " " + " " + submitDate + " " + oralMax + " " + totalMax + " " + cid;
	}

}
