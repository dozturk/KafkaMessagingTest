import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

/**
 * Created by OzorakM on 23.7.2018.
 */

@Service
public class dbAccess {
    @Value("${unix.connection.user}")
    String ip;
    @Value("${unix.connection.user}")
    String pass;
    @Value("${unix.connection.user}")
    String user;

    boolean isConnected;
    private Connection connection;

    public void connectIt()
    {

        try
        {
            isConnected=false;
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Missing Oracle JDBC Driver");
            e.printStackTrace();
            return;
        }
        //System.out.println("Driver intialized");
        connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":tCSSBF",user,pass);
            isConnected=true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        if(connection != null)
        {
            try {
                System.out.println("DB Connected");

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Connection failed");
        }
    }

    public void disconnectIt()
    {
        try {
            connection.close();
            System.out.println("DB Connection closed");
        }
        catch (Exception e)
        {
            System.out.println("Error!");
            e.printStackTrace();
            return;
        }
    }

    public void importIntoDB(String loginName,String password,String name,String email) throws SQLException{
        Statement statement = null;
        try{
            statement=connection.createStatement();
            statement.executeQuery("INSERT INTO loginDb (loginName,password,name,email) VALUES "+loginName+","+password+","+name+","+email+";");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public boolean clientExists(String loginName, String password){
        Statement statement = null;
        try{
            statement=connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT loginName,password FROM loginDb WHERE loginName = "+loginName+" AND password = "+password+";");
            String result_="";
            while(rs.next()){
                result_ = rs.getString("loginName");
            }
            if(!result_.equals(""))return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
