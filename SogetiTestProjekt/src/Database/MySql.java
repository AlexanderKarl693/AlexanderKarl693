package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class MySql {

	static String connString = "jdbc:mysql://localhost:3306/sogetitestdb?" + "user=root&password=Lernen@2021";
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static boolean LoginUser(String userName, String passWord) throws Exception {
	    boolean val = false;
    	String sql = "select ID,Name from User where name = \""+userName+"\" AND password = \""+passWord+"\";" ;	   	 
        try {
	        	connect = null;
	            connect = DriverManager.getConnection(connString);	
	            // Statements allow to issue SQL queries to the database
	            PreparedStatement  statement = connect.prepareStatement(sql);
	            // Result set get the result of the SQL query
	            val = statement.execute(sql);   
	            System.out.println(sql);
	            
        } catch (Exception e) {
            throw e;
        } 
        return val;  
    }
    
    public static ResultSet SelectAllOnlineUser() throws Exception {
	    String sql = "select ID,Name from User where IsOnline = 1;";	   	 
        try {
	        	connect = null;
	        	resultSet = null;
	        	statement = null;
	            connect = DriverManager.getConnection(connString);
	            // Statements allow to issue SQL queries to the database
	            statement = connect.createStatement();
	            // Result set get the result of the SQL query
	            resultSet = statement.executeQuery(sql);	 
	            System.out.println(sql);
	            return resultSet;       
        } catch (Exception e) {
            throw e;
        } 
    }  
    
    public  static  void   AddUser(String name, String password){
 	   String sql = "insert into User (name,password,IsOnline) values(\""+name+"\",\""+password+"\",0);";
 	   try {	 
				connect = null;
				resultSet = null;
				statement = null;
				connect = DriverManager.getConnection(connString);
			    // Statements allow to issue SQL queries to the database
			    statement = connect.createStatement();
			    // Result set get the result of the SQL query
			    statement.executeUpdate(sql);
			    connect.close();
 	   		}
 	   catch(SQLException ex){
 		   System.out.println(ex.getMessage()+"\n"+ sql);
 	   }
    }
    
    public  static  void   DeleteUser(String name, String password)
            throws SQLException
        {
	    	String sql = "delete from User where "+name+","+password+" );";
	    	connect = null;
	    	resultSet = null;
	    	statement = null;
	        connect = DriverManager.getConnection(connString);
	        // Statements allow to issue SQL queries to the database
	        statement = connect.createStatement();
	        // Result set get the result of the SQL query
	        resultSet = statement.executeQuery(sql);
            connect.close();
        }
    
    public  static  boolean   UpdateUser(String name, String password) throws Exception
        {   
 	       String setUserOnline = "update User Set IsOnline = 1 where name = \""+name+"\" and password = \""+password+"\";";
        	   boolean userExist = false;			   
        	   try {
					userExist = LoginUser( name, password);
					if (userExist == true) {		
			           	   try {	      	   		
				       	    	 connect = null;
				    	    	 resultSet = null;
				    	    	 statement = null;
				    	         connect = DriverManager.getConnection(connString);
				    	         // Statements allow to issue SQL queries to the database
				    	         statement = connect.createStatement();
				    	         // Result set get the result of the SQL query
				    	         statement.executeUpdate(setUserOnline);
				    	         System.out.println(setUserOnline);
				                 connect.close();
				    		     return userExist;
			 	   			}
					  	   catch(SQLException ex){
					  		   System.out.println(ex.getMessage() +"\n"+ setUserOnline);
					  	   	}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}      	   
        	   return userExist;
        }
    
    public static int getNumberRows(ResultSet rs){
    	int count = 0;
        try{
        	while (rs.next()) {
        		// retrieve and print the values for the current row
        		String i = rs.getString("Name");
        		System.out.println("ROW = " + i + " ");
        		count++;
        	}
        } 
        catch (Exception e){
           System.out.println("Error getting row count");
           e.printStackTrace();
        }
        return count;
    } 
}
