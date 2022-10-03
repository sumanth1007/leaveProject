package infinite.LeaveProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class LeaveDAO {
      Connection con;
      PreparedStatement ps;
      
      
     
      
      public String approveLeave(int leaveId,int mId,String mgrCmt,String leaveStatus) throws ClassNotFoundException, SQLException {
    	  con=ConnectionHelper.getConnection();
    	  Leave leave=searchLeave(leaveId);
    	  EmployDAO dao=new EmployDAO();
    	  Employ employ=dao.searchEmploy(leave.getEmpId());
    	  int empId=employ.getEmpId();
    	  int mgId=employ.getmId();
    	  int days=leave.getNoOfDays();
    	  if(mId==mgId) {
    		  if(leaveStatus.toUpperCase().equals("YES")) {
    			  String sql="update LEAVE_HISTORY set LEAVE_STATUS='APPROVED' , LEAVE_MNGR_COMMENTS=? where LEAVE_ID=?";
    			  ps=con.prepareStatement(sql);
    			  ps.setString(1, mgrCmt);
    			  ps.setInt(2, leaveId);
    			  ps.executeUpdate();
    			  
    			 return "Leave Approved.....";
    		  }else {
    			  String sql="update LEAVE_HISTORY set LEAVE_STATUS='DENIED',LEAVE_MNGR_COMMENTS=? where LEAVE_ID=?";
    			  ps=con.prepareStatement(sql);
    			  ps.setString(1, mgrCmt);
    			  ps.setInt(2, leaveId);
    			  ps.executeUpdate();
    			  sql= "UPDATE EMPLOYEE SET EMP_AVAIL_LEAVE_BAL=EMP_AVAIL_LEAVE_BAL+? "
  						+ " WHERE EMP_ID=?";
  				ps = con.prepareStatement(sql);
  				ps.setInt(1, days);
  				ps.setInt(2, empId);
  				ps.executeUpdate();
  				return " Leave not Approved...";
    			  
    		  }
    	  }else {
    		  return "You are not an authorised manager";
    	  }
		
      }
      
      public List<Leave> showLeave(int empId) throws ClassNotFoundException, SQLException {
    		con = ConnectionHelper.getConnection();
    		String cmd = "select * from leave_history where EMP_ID=?";
    		ps = con.prepareStatement(cmd);
    		ps.setInt(1, empId);
    		ResultSet rs = ps.executeQuery();
    		Leave leave = null;
    		List<Leave> leaveList=new ArrayList<>();
    		while (rs.next()) {
    			leave = new Leave();
    			leave.setLeaveId(rs.getInt("LEAVE_ID"));
    			leave.setEmpId(rs.getInt("EMP_ID"));
    			leave.setMgrCmt(rs.getString("LEAVE_MNGR_COMMENTS"));
    			leave.setNoOfDays(rs.getInt("LEAVE_NO_OF_DAYS"));
    			leave.setLeaveStartDate(rs.getDate("LEAVE_START_DATE"));
    			leave.setLeaveEndDate(rs.getDate("LEAVE_END_DATE"));
    			leave.setLeaveType(LeaveType.valueOf(rs.getString("LEAVE_TYPE")));
    			leave.setLeaveStatus(LeaveStatus.valueOf(rs.getString("LEAVE_STATUS")));
    			leave.setLeaveReason(rs.getString("LEAVE_REASON"));
    			leaveList.add(leave);
    		}
    		return  leaveList;
    	}      
        
      public Leave searchLeave(int leaveId) throws ClassNotFoundException, SQLException {
  		con = ConnectionHelper.getConnection();
  		String cmd = "select * from leave_history where LEAVE_ID=?";
  		ps = con.prepareStatement(cmd);
  		ps.setInt(1, leaveId);
  		ResultSet rs = ps.executeQuery();
  		Leave leave = null;
  		if (rs.next()) {
  			leave = new Leave();
  			leave.setLeaveId(rs.getInt("LEAVE_ID"));
  			leave.setEmpId(rs.getInt("EMP_ID"));
  			leave.setMgrCmt(rs.getString("LEAVE_MNGR_COMMENTS"));

  			leave.setNoOfDays(rs.getInt("LEAVE_NO_OF_DAYS"));
  			leave.setLeaveStartDate(rs.getDate("LEAVE_START_DATE"));
  			leave.setLeaveEndDate(rs.getDate("LEAVE_END_DATE"));
  			leave.setLeaveType(LeaveType.valueOf(rs.getString("LEAVE_TYPE")));
  			leave.setLeaveStatus(LeaveStatus.valueOf(rs.getString("LEAVE_STATUS")));
  			leave.setLeaveReason(rs.getString("LEAVE_REASON"));
  			
  		}
  		return leave;
  	}      
      
      public String deleteLeave(int leaveId) throws ClassNotFoundException, SQLException {
    	  con=ConnectionHelper.getConnection();
    	  Scanner sc=new Scanner(System.in);
    	  Leave leave=searchLeave(leaveId);
    	  if(leave!=null) {
    		  String sql="delete from leave_history where LEAVE_ID=?";
        	  ps=con.prepareStatement(sql);
        	  ps.setInt(1, leaveId);
        	  ps.executeUpdate();
        	  return "Leave deleted....";
    	  }return "Record not found....";
    	  }
         
    	
	public String addLeaveDao(Leave leave) throws ParseException, ClassNotFoundException, SQLException {
  	  int h=0;
  	  Date today=new Date();
  	  Employ employ=new Employ();
  	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  	  SimpleDateFormat sd = new SimpleDateFormat("EEE");
  		  String da[] = new String[] {
  				"2022-10-05","2022-10-24" ,"2022-12-25" };
  		 
  
  	 int days=(int)((leave.getLeaveEndDate().getTime()-leave.getLeaveStartDate().getTime())/(1000*60*60*24));
        days++;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(leave.getLeaveStartDate());

        Calendar c2 = Calendar.getInstance();
        c2.setTime(leave.getLeaveEndDate());

        int sundays = 0;
        int saturday = 0;

        while (! c1.after(c2)) {
            if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ){
                saturday++; 
            }
            if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
         
         sundays++;
            }

            c1.add(Calendar.DATE, 1);
        }
        
       int w=sundays+saturday;
       for (String string : da) {
  		 if(sdf.parse(string).after(leave.getLeaveStartDate())&&sdf.parse(string).before(leave.getLeaveEndDate())) {
  					h++;
  			}
  		 if(sdf.parse(string).compareTo(leave.getLeaveStartDate())==0) {
  			 h++;
  		 }
  		 if(sdf.parse(string).compareTo(leave.getLeaveEndDate())==0) {
  			 h++;
  		 }
      	
  		 if(sd.format(leave.getLeaveStartDate().getTime()).toUpperCase()=="SUNDAY") {
  			 h++;
  		 }
  			 
  	  }
      
       int weekdays=days-w;
       int workingdays=weekdays-h;
       leave.setLeaveType(LeaveType.EL);
      
       leave.setLeaveStatus(LeaveStatus.PENDING);
  	   EmployDAO dao=new EmployDAO();
  	  employ = dao.searchEmploy(leave.getEmpId());
      
     
     days=(int) ((leave.getLeaveStartDate().getTime()-today.getTime())/(1000*60*60*24));
     days++;
     if(days<=0) {
    	
    	return "Leave Start Date can not be  yesterday date......\r\n";
     }
     days=(int) ((leave.getLeaveEndDate().getTime()-today.getTime())/(1000*60*60*24));
     days++;
     if(days<=0) {
    	
    	 return "Leave End Date can not be yesterday date........\r\n";
     }
days=(int)((leave.getLeaveEndDate().getTime()-leave.getLeaveStartDate().getTime())/(1000*60*60*24));
if(days<=0) {
	return "Leave Start Date cant not be greater than Leave End Date.......\r\n";
 }
if(employ!=null) {
	  int balanceLeave=employ.getLeaveAvail();
     if(balanceLeave-workingdays<0) {
	    	 return "Leave balance is unsufficient";
	     }
	     else {
	    	 leave.setNoOfDays(workingdays);
	    	 int d=balanceLeave-workingdays;
	    	 con=ConnectionHelper.getConnection();
	    	 String sql="insert into LEAVE_HISTORY(LEAVE_NO_OF_DAYS,EMP_ID,LEAVE_START_DATE,LEAVE_END_DATE,LEAVE_TYPE,LEAVE_STATUS,LEAVE_REASON)"
	    	 +"values(?,?,?,?,?,?,?)";
	    	 
	    	 ps=con.prepareStatement(sql);
	    	 ps.setInt(1, leave.getNoOfDays());
	    	
	    	 ps.setInt(2, leave.getEmpId());
	    	 ps.setDate(3, leave.getLeaveStartDate());
	    	 ps.setDate(4, leave.getLeaveEndDate());
	    	 ps.setString(5, leave.getLeaveType().toString());
	    	 ps.setString(6, leave.getLeaveStatus().toString());
	    	 ps.setString(7, leave.getLeaveReason());
	    	 ps.executeUpdate();
	    	
	    	 sql="update employee set EMP_AVAIL_LEAVE_BAL=? where EMP_ID=?";
	    	 ps=con.prepareStatement(sql);
	    	 ps.setInt(1, d);
	    	 ps.setInt(2, leave.getEmpId());
	    	 ps.executeUpdate();
	    	 return "Leave Applied......";
	     }
}else {
	return "Record not found";
}

	}

	
}
