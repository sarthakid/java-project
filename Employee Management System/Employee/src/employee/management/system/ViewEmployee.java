package employee.management.system;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
//import net.proteanit.sql.DbUtils;
import java.awt.event.*;
public class ViewEmployee extends JFrame implements ActionListener{
	
	JTable table;
    Choice cemployeeId;
    JButton search,update, back;
	
	 ViewEmployee(){
		 getContentPane().setBackground(Color.WHITE);
	     setLayout(null);
	     
	     JLabel searchlbl = new JLabel("Search by Employee Id");
	        searchlbl.setBounds(20, 20, 150, 20);
	        add(searchlbl);
	        
	        cemployeeId = new Choice();
	        cemployeeId.setBounds(180, 20, 150, 20);
	        add(cemployeeId);
	     
	     try {
         	Connection c;
     		Statement s=null;
     		try {
     			
     			Class.forName("com.mysql.cj.jdbc.Driver");
     			c=DriverManager.getConnection("jdbc:mysql:///employeemanagementsystem","root","1234");
     			s= c.createStatement();
     			System.out.print("Connection Successful");
     		}catch(Exception e) {
     			e.printStackTrace();
     		}
     		
     		String query="select * from employee";
     		ResultSet rs = s.executeQuery(query);
//     		ResultSetMetaData rsmd= rs.getMetaData();
//     		
//     		DefaultTableModel model = (DefaultTableModel) table.getModel();
//     		int cols=rsmd.getColumnCount();
//     		String[] colName= new String[cols];
//     		
//     		for(int i=0; i<cols; i++)
//     			colName[i]=rsmd.getColumnName(i+1);
//     		model.setColumnIdentifiers(colName);
     		
     		
            while(rs.next()) {
                cemployeeId.add(rs.getString("empId"));
            }
     		
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
     		
	     JScrollPane jsp = new JScrollPane(table);
	        jsp.setBounds(0, 100, 900, 600);
	        add(jsp);
	        
	        search = new JButton("Search");
	        search.setBounds(20, 70, 80, 20);
	        search.addActionListener(this);
	        add(search);
	        
	        update = new JButton("Update");
	        update.setBounds(120, 70, 80, 20);
	        update.addActionListener(this);
	        add(update);
	        
	        back = new JButton("Back");
	        back.setBounds(220, 70, 80, 20);
	        back.addActionListener(this);
	        add(back);
     		
	     setSize(900, 700);
	     setLocation(300, 100);
	     setVisible(true);
	 }
	 
	 public void actionPerformed(ActionEvent ae) {
	        if (ae.getSource() == search) {
	            String query = "select * from employee where empId = '"+cemployeeId.getSelectedItem()+"'";
	            try {
	            	Connection c;
	         		Statement s=null;
	         		try {
	         			
	         			Class.forName("com.mysql.cj.jdbc.Driver");
	         			c=DriverManager.getConnection("jdbc:mysql:///employeemanagementsystem","root","1234");
	         			s= c.createStatement();
	         			System.out.print("Connection Successful");
	         		}catch(Exception e) {
	         			e.printStackTrace();
	         		}
	         		
	                ResultSet rs = s.executeQuery(query);
	              //  table.setModel(DbUtils.resultSetToTableModel(rs));
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }else if (ae.getSource() == update) {
	            setVisible(false);
	            new UpdateEmployee(cemployeeId.getSelectedItem());
	        } else {
	            setVisible(false);
	            new Home();
	        }
	    }

	public static void main(String[] args) {
		new ViewEmployee();

	}

}
