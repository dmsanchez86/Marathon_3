package models;

import java.sql.ResultSet;

/**
 * @author Mao
 */
public class Queries extends Conection{

    public String[] getDataRunner(String runner) {
        String[] dataRunner = new String[2];
        try {
            conect();
            
            query = conection.prepareStatement(""
                    +   "SELECT r.Gender, r.DateOfBirth \n" +
                        "FROM runner r \n" +
                        "INNER JOIN user u ON r.Email = u.Email \n" +
                        "WHERE u.FirstName LIKE '%"+runner+"%' OR u.LastName LIKE '%"+runner+"%' LIMIT 1");
            
            data = query.executeQuery();
            
            while(data.next()){
                dataRunner[0] = data.getString("Gender");
                dataRunner[1] = data.getString("DateOfBirth");
            }
            
            return dataRunner;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultSet getDataRunners(String runner) {
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
    
    public String calculateCategory(String age){
        if(age == null || "".equals(age)){ return null; }
        
        int currentYear = 2016;
        int yearRunner = Integer.parseInt(age.split("-")[0]);
        
        int diff = currentYear - yearRunner;
        
        if(diff < 18){
            return "minor to 18";
        }else if(diff >= 18 && diff <= 29){
            return "18 - 29";
        }else if(diff >= 30 && diff <= 39){
            return "30 - 39";
        }else if(diff >= 40 && diff <= 55){
            return "40 - 55";
        }else if(diff >= 56 && diff <= 70){
            return "56 - 70";
        }else{
            return "more than 70";
        }
    }
}
