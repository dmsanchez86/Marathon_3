package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Mao
 */
public class Queries extends Conection{

    public String[] getDataRunner(String runner) {
        String[] dataRunner = new String[3];
        try {
            conect();
            
            query = conection.prepareStatement(""
                    +   "SELECT r.Gender, r.DateOfBirth, r.RunnerId \n" +
                        "FROM runner r \n" +
                        "INNER JOIN user u ON r.Email = u.Email \n" +
                        "WHERE u.FirstName LIKE '%"+runner+"%' OR u.LastName LIKE '%"+runner+"%' LIMIT 1");
            
            data = query.executeQuery();
            
            while(data.next()){
                dataRunner[0] = data.getString("Gender");
                dataRunner[1] = data.getString("DateOfBirth");
                dataRunner[2] = data.getString("RunnerId");
            }
            
            return dataRunner;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public String[] getDataCompetititonRunner(String idRunner) {
        String[] dataRunner = new String[10]; 
        
        try {
            conect();
            
            query = conection.prepareStatement(""
                    +   "SELECT m.MarathonId, m.CityName, m.YearHeld, et.EventTypeName, et.EventTypeId , re.RaceTime, ru.Email, ru.Gender,ro.RoleId, r.RegistrationId\n" +
                        "FROM marathon m\n" +
                        "INNER JOIN event e ON e.MarathonId = m.MarathonId\n" +
                        "INNER JOIN eventtype et ON e.EventTypeId = et.EventTypeId\n" +
                        "INNER JOIN registrationevent re ON re.EventId = e.EventId\n" +
                        "INNER JOIN registration r ON r.RegistrationId = re.RegistrationId\n" +
                        "INNER JOIN runner ru ON r.RunnerId = ru.RunnerId \n" +
                        "INNER JOIN user u ON ru.Email = u.Email\n" +
                        "INNER JOIN role ro ON u.RoleId = ro.RoleId\n" +
                        "WHERE ru.RunnerId = ?"
                    + "");
            query.setInt(1, Integer.parseInt(idRunner));
            
            data = query.executeQuery();
            
            while(data.next()){
                dataRunner[0] = data.getString("RegistrationId");
                dataRunner[1] = data.getString("Gender");
                dataRunner[2] = data.getString("MarathonId");
                dataRunner[3] = data.getString("EventTypeId");                
            }
            
            return dataRunner;
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public ResultSet getDataAllCompetititonsRunners(String registrationId, String gender, String marathonId, String eventTypeId) {
        try {
            conect();
            
            query = conection.prepareStatement(""
                    +   "SELECT m.MarathonId,m.MarathonName,m.CityName, m.YearHeld, et.EventTypeName, et.EventTypeId, re.RaceTime, e.MaxParticipants,rs.RegistrationStatusId, ru.Gender\n" +
                        "FROM marathon m\n" +
                        "INNER JOIN event e ON e.MarathonId = m.MarathonId\n" +
                        "INNER JOIN eventtype et ON e.EventTypeId = et.EventTypeId\n" +
                        "INNER JOIN registrationevent re ON re.EventId = e.EventId\n" +
                        "INNER JOIN registration r ON r.RegistrationId = re.RegistrationId\n" +
                        "INNER JOIN registrationstatus rs ON r.RegistrationStatusId = rs.RegistrationStatusId\n" +
                        "INNER JOIN runner ru ON r.RunnerId = ru.RunnerId\n" +
                        "INNER JOIN gender g ON ru.Gender = g.Gender\n" +
                        "INNER JOIN user u ON ru.Email = u.Email\n" +
                        "INNER JOIN role ro ON u.RoleId = ro.RoleId\n" +
                        "WHERE rs.RegistrationStatusId = ? AND ru.Gender = ? AND m.MarathonId = ? AND et.EventTypeId = ?"
                    + "");
            query.setInt(1, Integer.parseInt(registrationId));
            query.setString(2, gender);
            query.setInt(3, Integer.parseInt(marathonId));
            query.setString(4, eventTypeId);
            
            data = query.executeQuery();
            
            return data;
        } catch (SQLException | NumberFormatException e) {
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
    
    public String formatRaceTime(String raceTime){ 
        // validate if the time is not null
        if(raceTime == null || raceTime == "NULL"){
            return "0";
        }
        
        int time = Integer.parseInt(raceTime);
        
        int hours = time / 3600;
        int minutes = (time - (hours * 3600)) / (60);
        int seconds = (time) - ((hours * 3600) + (minutes * 60));
        
        return hours+"<b>H</b> "+minutes+"<b>M</b> "+seconds+"<b>S</b>" ;
    }
}
