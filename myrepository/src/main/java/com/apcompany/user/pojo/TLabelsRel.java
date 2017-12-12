package com.apcompany.user.pojo;

import java.sql.Timestamp;

public class TLabelsRel {

	private int id;
	private int status;
	private String labelname;
	private int parentsid;
	private Timestamp ctime;
	private Timestamp utime;
	private int depth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public int getParentsid() {
		return parentsid;
	}

	public void setParentsid(int parentsid) {
		this.parentsid = parentsid;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getUtime() {
		return utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int dep) {
		this.depth = dep;
	}

	@Override
	public String toString() {
		return "TLabelsRel [id=" + id + ", status=" + status + ", labelname="
				+ labelname + ", parentsid=" + parentsid + ", ctime=" + ctime
				+ ", utime=" + utime + "]";
	}

}
