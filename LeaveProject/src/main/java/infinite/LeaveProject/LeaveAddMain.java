package infinite.LeaveProject;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LeaveAddMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Leave leave = new Leave();
		Employ employ=new Employ();
		
		System.out.println("Enter Employee Id  ");
		leave.setEmpId(sc.nextInt());
		System.out.println("Enter Leave Start Date (yyyy-MM-dd)   ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date lstartDate = sdf.parse(sc.next());
			java.sql.Date startDate = new java.sql.Date(lstartDate.getTime());
			leave.setLeaveStartDate(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter Leave End Date (yyyy-MM-dd)  ");
		try {
			Date lendDate = sdf.parse(sc.next());
			java.sql.Date endDate = new java.sql.Date(lendDate.getTime());
			leave.setLeaveEndDate(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Enter Reason   ");
		leave.setLeaveReason(sc.next());
		
		LeaveDAO dao = new LeaveDAO();
		try {
			System.out.println(dao.addLeaveDao(leave));
		} catch (ClassNotFoundException | ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
}