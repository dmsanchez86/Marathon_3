/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Mao
 */
public class Queries extends Conection{
    
    public String validateLogin(String email, String password){
        String response = null;
        
        try {
            conect();
            
            query = conection.prepareStatement("SELECT * FROM user WHERE Email = ? AND Password = ?");
            query.setString(1, email);
            query.setString(2, password);
            
            data = query.executeQuery();
            
            while(data.next()){
                response = data.getString("RoleId");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return response;
    }

    public ResultSet getCompetitionsEvents() {
        try {
            conect();
            
            query = conection.prepareStatement(""
                    +   "SELECT et.EventTypeName, e.Cost, e.EventId\n" +
                        "FROM eventtype et\n" +
                        "INNER JOIN event e ON e.EventTypeId = et.EventTypeId\n" +
                        "INNER JOIN marathon m ON e.MarathonId = m.MarathonId\n" +
                        "WHERE m.MarathonId = 5 ORDER BY e.Cost DESC");
            
            data = query.executeQuery();
            
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultSet get_Charities() {
        try {
            conect();
            
            query = conection.prepareStatement("SELECT * FROM charity");
            data = query.executeQuery();
            
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public String[] get_Data_Charity(int idCharity) {
        String[] dataCharity = new String[3];
        
        try {
            conect();
            
            query = conection.prepareStatement("SELECT * FROM charity WHERE CharityId = ?");
            query.setInt(1, idCharity);
            data = query.executeQuery();
            
            while(data.next()){
                dataCharity[0] = data.getString("CharityName");
                dataCharity[1] = data.getString("CharityDescription");
                dataCharity[2] = data.getString("CharityLogo");
            }
            
            return dataCharity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int number_Charities() {
        int n = 0;
        
        try {
            conect();
            
            query = conection.prepareStatement("SELECT COUNT(*) FROM charity");
            data = query.executeQuery();
            
            while(data.next()){
                n = data.getInt(1);
            }
            
            return n;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public ResultSet get_Kits() {
        try {
            conect();
            
            query = conection.prepareStatement("SELECT * FROM racekitoption ORDER BY Cost DESC");
            data = query.executeQuery();
            
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getIdRunner(String emailUser) {
        String id = "";
        
        try {
            conect();
            
            query = conection.prepareStatement("SELECT RunnerId FROM runner WHERE Email = ?");
            query.setString(1, emailUser);
            
            data = query.executeQuery();
            
            while(data.next()){
                id = data.getString("RunnerId");
            }
            
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    public boolean registerToEvent(String idRunner, String date, String idKit, int registrationStatus, int cost, String idCharity, int sponsorshipTarget) {
        try {
            conect();
            
            query = conection.prepareStatement("INSERT INTO registration VALUES(?,?,?,?,?,?,?,?)");
            query.setInt(1, 0);
            query.setInt(2, Integer.parseInt(idRunner));
            query.setString(3, date);
            query.setString(4, idKit);
            query.setInt(5, registrationStatus);
            query.setInt(6, cost);
            query.setInt(7, Integer.parseInt(idCharity));
            query.setInt(8, sponsorshipTarget);
            query.executeUpdate();
            
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public int countCountries() {
        int n = 0;
        
        try {
            conect();
            
            query = conection.prepareStatement("SELECT COUNT(*) FROM country");
            data = query.executeQuery();
            
            while(data.next()){
                n = data.getInt(1);
            }
            
            return n;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public ResultSet getCountries() {
        try {
            conect();
            
            query = conection.prepareStatement("SELECT * FROM country");
            data = query.executeQuery();
            
            return data;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String[] dataUser(String emailUser) {
        String[] dataUser = new String[5];
        try {
            conect();
            
            query = conection.prepareStatement("SELECT u.FirstName, u.LastName, r.Gender, r.DateOfBirth, c.CountryName "
                    + "FROM user u "
                    + "INNER JOIN runner r ON r.Email = u.Email "
                    + "INNER JOIN country c ON c.CountryCode = r.CountryCode "
                    + "WHERE u.Email = ?");
            query.setString(1, emailUser);
            
            data = query.executeQuery();
            
            while(data.next()){
                dataUser[0] = data.getString("FirstName");
                dataUser[1] = data.getString("LastName");
                dataUser[2] = data.getString("Gender");
                dataUser[3] = data.getString("DateOfBirth");
                dataUser[4] = data.getString("CountryName");
            }
            
            return dataUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean changePassword(String email, String password) {
        try {
            conect();
            
            query = conection.prepareStatement("UPDATE user SET Password = ? WHERE Email = ?");
            query.setString(1, password);
            query.setString(2, email);
            query.executeUpdate();
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateProfile(String email, String name, String lastname, String gender, String dateBirth, String codeCountry) {
        try {
            conect();
            
            query = conection.prepareStatement("UPDATE user u, runner r SET u.FirstName = ?, u.LastName = ?, r.Gender = ?, r.DateOfBirth = ?, r.CountryCode = ? WHERE u.Email = ?");
            query.setString(1, name);
            query.setString(2, lastname);
            query.setString(3, gender);
            query.setString(4, dateBirth);
            query.setString(5, codeCountry);
            query.setString(6, email);
            int n = query.executeUpdate();
            
            if(n > 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean registerUser(String email, String password, String name, String lastname, String gender, String dateBirth, String codeCountry) {
        try {
            conect();
            
            query = conection.prepareStatement("INSERT INTO user VALUES(?,?,?,?,?)");
            query.setString(1, email);
            query.setString(2, password);
            query.setString(3, name);
            query.setString(4, lastname);
            query.setString(5, "R");
            
            int r = query.executeUpdate();
            
            if(r > 0){
                query = conection.prepareStatement("INSERT INTO runner VALUES(?,?,?,?,?)");
                query.setInt(1, 0);
                query.setString(2, email);
                query.setString(3, gender);
                query.setString(4, dateBirth);
                query.setString(5, codeCountry);
                
                int j = query.executeUpdate();
                
                if(j > 0){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean verifyEmail(String email) {
        try {
            conect();
            
            query = conection.prepareStatement("SELECT COUNT(*) FROM user WHERE Email = ?");
            query.setString(1, email);
            
            ResultSet data = query.executeQuery();
            
            while(data.next())
                return true;
            
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
