package infinite.LeaveProject;

import java.sql.SQLException;
import java.util.Scanner;

public class ApproveLeaveMain {
   public static void main(String[] args) {
	   int leaveId, mgrId;
		String managerComments, status;
		LeaveDAO dao=new LeaveDAO();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Leave Id   ");
		leaveId = sc.nextInt();
		System.out.println("Enter Manager Id  ");
		mgrId = sc.nextInt();
		System.out.println("Enter Manager Comments   ");
		managerComments = sc.next();
		System.out.println("Status (Yes Or No)   ");
		status = sc.next().toUpperCase();
		try {
			System.out.println(dao.approveLeave(leaveId, mgrId, managerComments, status));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
