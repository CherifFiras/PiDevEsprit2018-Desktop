/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entity.User;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Achrafoun
 */
public interface IUserService {
    //$u,$datemin,$datemax,$gender,$occupation,$religion,$pays,$ville,$region,$films,$series,$livres,$musiques
    public List<User> searchResult(int id,Date datemin,Date datemax,String gender,List<String>occupation,List<String>religion,List<String>pays
            ,List<String>ville,List<String>region,List<String>films,List<String>series,List<String>livres,List<String>musiques);
    
    public List<User> getAllUsers();
    
    public User getUserById(int id);
    
    public int countUsers();
    
    public int countActifUsers();
    
    public int countInactifUsers();
    
    public List<User> getSuggestionUsers();
    
    public List<User> getBlockedUsers();
    
    public void modifierUser(User u);
        
    
}