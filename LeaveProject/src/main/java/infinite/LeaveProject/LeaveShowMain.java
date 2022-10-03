package infinite.LeaveProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LeaveShowMain {
   public static void main(String[] args) {
	   Connection con;
		PreparedStatement ps;
		  LeaveDAO dao=new LeaveDAO();  
		 Scanner sc=new Scanner(System.in);
		  System.out.println("Enter Employ Id");
			int empId=sc.nextInt();
			try {
				List<Leave> leave=dao.showLeave(empId);
				if(leave!=null) {
					System.out.println(leave);
				}else {
					System.out.println("Record not found.....");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
}
}
