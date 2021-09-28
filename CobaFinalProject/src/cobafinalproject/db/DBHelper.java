
package cobafinalproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBHelper {
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DB = "koperasi";
    private static final String MYCONN = "jdbc:mysql://localhost/"+DB;
    
    
    public static Connection getConnection(String driver) throws SQLException{
        //dicek lagi kalo misal error
        Connection conn=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(MYCONN,USER, PASSWORD);
            System.out.println("Library ada");
        } catch (ClassNotFoundException ex) {
            System.out.println("Library tidak ada");
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      return conn;               
}



}
