package infinite.LeaveProject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class LeaveDeleteMain {
   public static void main(String[] args) {
	  Scanner sc=new Scanner(System.in);
	  LeaveDAO dao=new LeaveDAO();
	  System.out.println("Enter Leave Id");
	  int leaveId=sc.nextInt();
	  try {
		String leave=dao.deleteLeave(leaveId);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
}
}

