package infinite.LeaveProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployDAO {
   Connection con;
   PreparedStatement ps;
     
   
   public Employ searchEmploy(int empId) throws ClassNotFoundException, SQLException {
	   con=ConnectionHelper.getConnection();
	   String sql="select * from employee where EMP_ID=?";
	   ps=con.prepareStatement(sql);
	   ps.setInt(1, empId);
	   ResultSet rs=ps.executeQuery();
	  Employ employ=null;
	  if(rs.next()) {
		  employ=new Employ();
		  employ.setEmpId(rs.getInt("EMP_ID"));
			employ.setName(rs.getString("EMP_NAME"));
			employ.setEmail(rs.getString("EMP_MAIL"));
			employ.setMob(rs.getString("EMP_MOB_NO"));
			employ.setDOJ(rs.getDate("EMP_DT_JOIN"));
			employ.setDept(rs.getString("EMP_DEPT"));
			employ.setmId(rs.getInt("EMP_MANAGER_ID"));
			employ.setLeaveAvail(rs.getInt("EMP_AVAIL_LEAVE_BAL"));
	  }return employ;
   }
   
	public List<Employ> showEmploy() throws ClassNotFoundException, SQLException{
		con=ConnectionHelper.getConnection();
		String sql="select * from employee";
		ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		List<Employ> employList=new ArrayList<Employ>();
		Employ employ=null;
		while(rs.next()) {
			employ=new Employ();
			employ.setEmpId(rs.getInt("EMP_ID"));
			employ.setName(rs.getString("EMP_NAME"));
			employ.setEmail(rs.getString("EMP_MAIL"));
			employ.setMob(rs.getString("EMP_MOB_NO"));
			employ.setDOJ(rs.getDate("EMP_DT_JOIN"));
			employ.setDept(rs.getString("EMP_DEPT"));
			employ.setmId(rs.getInt("EMP_MANAGER_ID"));
			employ.setLeaveAvail(rs.getInt("EMP_AVAIL_LEAVE_BAL"));
			employList.add(employ);
			
		}return employList;
				
	}
	
	
}
