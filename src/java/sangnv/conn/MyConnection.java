/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sangnv.conn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Shang
 */
public class MyConnection implements Serializable {

    public static Connection getMyConnection() throws NamingException, SQLException {

        Context ctx = new InitialContext();
        Context envCtx = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("DBcon");
        Connection conn = ds.getConnection();
        return conn;
    }
    
    public static Connection getMyOldConnection()throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=UserDB", "sa", "1");
        return conn;
    }
}
