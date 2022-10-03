package infinite.LeaveProject;

import java.sql.SQLException;
import java.util.Scanner;

public class EmploySearchMain {
      public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
    	EmployDAO dao=new EmployDAO();
    	System.out.println("Enter Employ Id:");
    	int empid=sc.nextInt();
		try {
			Employ employ=dao.searchEmploy(empid);
			if(employ!=null) {
				System.out.println(employ);
			}else {
				System.out.println("Record not found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
