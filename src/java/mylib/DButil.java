/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author acer
 */
public class DButil {
    public static Connection makeConnection() throws Exception{
        Connection cn= null;
        String IP="localhost";
        String instanceName="TRI-QUYET\\TRI_QUYET";
        String port="1433";
        String uid="sa";
        String pwd="12345";
        String db="test";
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://" +IP+"\\"+ instanceName+":"+port
                 +";databasename="+db+";user="+uid+";password="+pwd  
                + ";useUnicode=true;characterEncoding=UTF-8";
        Class.forName(driver);
        cn=DriverManager.getConnection(url);
        return cn;
    }

}
