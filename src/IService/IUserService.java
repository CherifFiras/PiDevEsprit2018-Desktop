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
 * @author hero
 */
public interface IUserService {

    public List<User> searchResult(int id,Date datemin,Date datemax,String gender,List<String>occupation,List<String>religion,List<String>pays
            ,List<String>ville,List<String>region,List<String>films,List<String>series,List<String>livres,List<String>musiques);
}