/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.User;
import IService.IUserService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hero
 */
public class UserService implements IUserService {
    private Connection con = DataSource.getInstance().getCon();

    @Override
    public List<User> searchResult(int id, Date datemin, Date datemax, String gender, List<String> occupation, List<String> religion, List<String> pays, 
            List<String> ville, List<String> region, List<String> films, List<String> series, List<String> livres, List<String> musiques) {
        List<User> users = new ArrayList<>();
        List<String> interets = new ArrayList<>();
        boolean empty = true;
        Map<Integer,String> argsMap = new HashMap<>();
        int counter = 3;
        try {
            String query = "select DISTINCT * from user u left join `centre_interet` i on u.id = i.IdUser "
                    + "where (`date_naissance` BETWEEN  ? AND ? ) and (u.id != ?) ";
            if(!"".equals(gender))
            {
                query += "and ( genre in (?) ) ";
                argsMap.put(++counter,gender);
            }
            if(!occupation.isEmpty())
            {
                query+="and (u.occupation in ("+statementArgs(occupation) +")) ";
                for(String s : occupation)
                    argsMap.put(++counter, s);
            }
            if(!religion.isEmpty())
            {
                query+="and (u.religion in ("+statementArgs(religion) +")) ";
                for(String s : religion)
                    argsMap.put(++counter, s);
            }
            if(!pays.isEmpty())
            {
                query+="and (u.pays in ("+statementArgs(pays) +")) ";
                for(String s : pays)
                    argsMap.put(++counter, s);
            }
            if(!ville.isEmpty())
            {
                query+="and (u.religion in ("+statementArgs(ville) +")) ";
                for(String s : ville)
                    argsMap.put(++counter, s);
            }
            if(!region.isEmpty())
            {
                query+="and (region in ("+statementArgs(region) +")) ";
                for(String s : region)
                    argsMap.put(++counter, s);
            }
            if(!films.isEmpty())
            {
                interets.addAll(films);
                empty = false;
            }
            if(!series.isEmpty())
            {
                interets.addAll(series);
                empty = false;
            }
            if(!livres.isEmpty())
            {
                interets.addAll(livres);
                empty = false;
            }
            if(!musiques.isEmpty())
            {
                interets.addAll(musiques);
                empty = false;
            }
            if(!empty)
            {
                query+="and (i.contenu in ("+statementArgs(interets) +")) ";
                for(String s : interets)
                    argsMap.put(++counter, s);
            }
            query +="group by nom,prenom,username";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, datemax);
            ps.setDate(2, datemin);
            ps.setInt(3, id);
            for(Map.Entry<Integer,String> entry:argsMap.entrySet())
            {
                System.out.println(entry.getValue());
                ps.setString(entry.getKey(), entry.getValue());
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                users.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    private String statementArgs(List<String> args)
    {
        String t="";
        for(int i=0;i<args.size();i++)
        {
          t+=",?";
        }
        t=t.replaceFirst(",","");
        return t;
    }
}
