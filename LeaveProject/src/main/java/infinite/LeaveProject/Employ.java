package infinite.LeaveProject;

import java.sql.Date;

public class Employ {
	private int empId;
	private String Name;
	private String Email;
	private String Mob;
	private Date DOJ;
	private String Dept;
	private int mId;
	private int leaveAvail;
	
	@Override
	public String toString() {
		return "Employ [empId=" + empId + ", Name=" + Name + ", Email=" + Email + ", Mob=" + Mob + ", DOJ=" + DOJ
				+ ", Dept=" + Dept + ", mId=" + mId + ", leaveAvail=" + leaveAvail + "]";
	}

	public Employ(int empId, String name, String email, String mob, Date dOJ, String dept, int mId, int leaveAvail) {

		this.empId = empId;
		Name = name;
		Email = email;
		Mob = mob;
		DOJ = dOJ;
		Dept = dept;
		this.mId = mId;
		this.leaveAvail = leaveAvail;
	}

	public Employ() {
		
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getMob() {
		return Mob;
	}

	public void setMob(String mob) {
		Mob = mob;
	}

	public Date getDOJ() {
		return DOJ;
	}

	public void setDOJ(Date dOJ) {
		DOJ = dOJ;
	}

	public String getDept() {
		return Dept;
	}

	public void setDept(String dept) {
		Dept = dept;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public int getLeaveAvail() {
		return leaveAvail;
	}

	public void setLeaveAvail(int leaveAvail) {
		this.leaveAvail = leaveAvail;
	}
	
	
	
}
