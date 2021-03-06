/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blogspot.na5cent.connectdb.plsql;

import com.blogspot.na5cent.connectdb.C3DBConfig;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anonymous
 */
public class Ex2FunctionMultiplyTwoNumbers {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                C3DBConfig.getUrl(),
                C3DBConfig.getUsername(),
                C3DBConfig.getPassword()
        );
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(C3DBConfig.getDriver());

        Connection connection = null;
        CallableStatement statement = null; /*** use callable ***/
        try {
            connection = getConnection();
            //------------------------------------------------------------------
            //1 define statement
            statement = connection.prepareCall("{ call ? := multiply_two_numbers(?, ?) }");
            
            //2 set input and register output
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.setInt(2, 7);
            statement.setInt(3, 3);
            
            //3 execute
            statement.executeQuery();
            
            //4 get result
            System.out.println("output index 1 = " + statement.getInt(1));
            //------------------------------------------------------------------
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}
