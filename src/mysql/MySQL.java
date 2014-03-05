package mysql;

import java.sql.*;

public class MySQL {
	Connection con = null;
	 public MySQL(){
		 connectDB();
		 
	 }
	 
	 public void connectDB(){
		 System.out.println(
				 "connecting....");
				 try {
				 //Statement stmt;
				 Class.forName("com.mysql.jdbc.Driver");
				 String url =
						 "jdbc:mysql://localhost:3306/mysql";
				 
				 Connection con =DriverManager.getConnection( url,"root", "root");
				 System.out.println("connecting established");
				 System.out.println("Connection: " + con);
				 this.con= con;
				// stmt = con.createStatement();
				 
				// createDB(stmt);
				// insert(stmt);
				 //query(stmt);
				 //con.close();
				 }catch( Exception e ) {
				 e.printStackTrace();
				 }//end catch
				return ;
	 }
	   public  void showTable(String tableName
     			) throws SQLException  {  
		   Statement stmt= con.createStatement();
		   String query = "SELECT * FROM " + tableName;
		   ResultSet rs = stmt.executeQuery(query);
		   ResultSetMetaData rsmd = rs.getMetaData();
		   int columnCount = rsmd.getColumnCount();
	     while (rs.next())
	     {  for (int i = 1; i <= columnCount; i++)
	        {  if (i > 1) System.out.print(", ");
	           System.out.print(rs.getString(i));
	        }
	        System.out.println();
	     }
	     rs.close();
  }

	 public  Statement update( String st) throws SQLException{
		 
		 PreparedStatement stmt = con.prepareStatement(st,
               Statement.RETURN_GENERATED_KEYS);
		 System.out.println(st);
		 stmt.executeUpdate();
		 return stmt;
	 }
	 public static void drop(Statement stmt) throws SQLException{
		 stmt.executeUpdate(
				 "DROP DATABASE JunkDB");

	 }
	 
	 public ResultSet query(String st) throws SQLException{
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(st);
		// System.out.println("Display all results:");
		 return rs;

	 }
	 
	}//end class 
