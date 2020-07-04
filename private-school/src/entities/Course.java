/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Course {

	private int cid;
	private String title;
	private String stream;
	private String type;
	private LocalDate startDate, endDate;

	public Course() {
	}

	public Course(String title, String stream, String type, LocalDate startDate, LocalDate endDate) {
		this.title = title;
		this.stream = stream;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Course(int cid, String title, String stream, String type, LocalDate startDate, LocalDate endDate) {
		this.cid = cid;
		this.title = title;
		this.stream = stream;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + this.cid;
		hash = 59 * hash + Objects.hashCode(this.title);
		hash = 59 * hash + Objects.hashCode(this.stream);
		hash = 59 * hash + Objects.hashCode(this.type);
		hash = 59 * hash + Objects.hashCode(this.startDate);
		hash = 59 * hash + Objects.hashCode(this.endDate);
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
		final Course other = (Course) obj;
		if (this.cid != other.cid) {
			return false;
		}
		if (!Objects.equals(this.title, other.title)) {
			return false;
		}
		if (!Objects.equals(this.stream, other.stream)) {
			return false;
		}
		if (!Objects.equals(this.type, other.type)) {
			return false;
		}
		if (!Objects.equals(this.startDate, other.startDate)) {
			return false;
		}
		if (!Objects.equals(this.endDate, other.endDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return cid + ". " + title + ", " + stream + ", " + type + ", " + startDate + ", " + endDate;
	}

}
