package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connect {

		/*
		 * Connection String for Database
		 * */
		static String url = "jdbc:sqlite:src/Database/TestUserDB.db";
    
		/*
		 * Returns all Users, who are Online 
		 * */
       public  static  ResultSet SelectUser()
           
        {
    	   String sql = "SELECT ID, Name FROM User where IsOnline = 1";// 
    	   try {
               Connection          conn = DriverManager.getConnection(url);
               PreparedStatement   ps   = conn.prepareStatement( sql);
               return ps.executeQuery();    		   
    	   }
	  	   catch(SQLException ex){
	  		   System.out.println(ex.getMessage() +"\n"+ sql);
	  	   	}    	   
    	   return null;
        }
       
       /*
        * 
        * */
       public  static  ResultSet   SelectUser(String userName, String passWord)
               throws SQLException
           {
               Connection          conn = DriverManager.getConnection(url);
               PreparedStatement   ps   = conn.prepareStatement("select ID,Name from User where username = \""+userName+"\" AND pasword = \""+passWord+"\";" );
               if(ps != null)
               {
            	   conn.close();
               }
               return ps.executeQuery();
           }      
       
       public  static  void   AddUser(String name, String password){
    	   String sql = "insert into User (name,password,IsOnline) values(\""+name+"\",\""+password+"\",0);";
    	   try {	 
	                 Connection          conn = DriverManager.getConnection(url);
	                 Statement   stmt   = conn.createStatement();
	                 stmt.execute(sql);
	    		     conn.close();
    	   		}
    	   catch(SQLException ex){
    		   System.out.println(ex.getMessage()+"\n"+ sql);
    	   }

       }
       
       public  static  ResultSet   DeleteUser(String name, String password)
               throws SQLException
           {
               Connection          conn = DriverManager.getConnection(url);
               PreparedStatement   ps   = conn.prepareStatement("delete from User where "+name+","+password+" );");
               return ps.executeQuery();
           }
       
       public  static  void   UpdateUser(String name, String password)
           { String sql = "update User Set IsOnline = 1 where name = \""+name+"\" and password = \""+password+"\";";
        	   try {
	      	   		
	                 Connection          conn = DriverManager.getConnection(url);
	                 Statement   stmt   = conn.createStatement();
	                 stmt.execute(sql);
	    		     conn.close();
  	   			}
		  	   catch(SQLException ex){
		  		   System.out.println(ex.getMessage() +"\n"+ sql);
		  	   	}
           }
       
}