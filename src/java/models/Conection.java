package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author mauro
 */
public class Conection {
  protected Connection conection;
  protected PreparedStatement query;
  protected ResultSet data;
  
  public boolean conect(){
    try {
      Class.forName("com.mysql.jdbc.Driver");
      conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/marathon", "root", "");
    } catch (ClassNotFoundException | SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }
  
  public boolean disconect(){
    try {
      this.conection.close();
    } catch (SQLException ex) {
      System.out.println(ex);
      return false;
    }
    return true;
  }
  
  public Connection get_conection(){
      return this.conection;
  }
}
