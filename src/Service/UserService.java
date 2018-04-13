/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Controller.SujetController;
import Core.DataSource;
import Entity.Category;
import Entity.User;
import IService.IUserService;
import interfaceadmin1.UIController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

/**
 *
 * @author hero
 */
public class UserService implements IUserService {
    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

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
                ps.setString(entry.getKey(), entry.getValue());
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                users.add(User.createUser(rs));
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

    @Override
    public List<User> getAllUsers() {
        String req = "SELECT * FROM user";
        ResultSet rs= null;
        try {
            rs = ste.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getBoolean("enabled"), rs.getString("salt"), rs.getDate("lastLogin"), rs.getString("roles"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("genre"), rs.getString("pays"), rs.getString("region"), rs.getString("ville"), rs.getString("tel"), rs.getString("placeNaiss"), rs.getString("religion"), rs.getString("apropos"), rs.getString("facebook"), rs.getString("twitter"), rs.getString("instagram"), rs.getString("image"), rs.getDate("updatedAt"), rs.getString("occupation")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        String req = "SELECT * FROM user where id=?";
        ResultSet rs= null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        User u = null;
        try {
            while (rs.next()){
                u = User.createUser(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public int countUsers() {
        String req = "SELECT count(*) as cu FROM user where role not like %ROLE_SUPER_ADMIN%";
        ResultSet rs= null;
        try {
            rs = ste.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cu = 0;
        try {
            while (rs.next()){
                cu = rs.getInt("cu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cu;
    }

    @Override
    public int countActifUsers() {
        String req = "SELECT COUNT(*) FROM user u WHERE DATE_ADD(u.last_login, INTERVAL 7 DAY)>= now() and role not like %ROLE_SUPER_ADMIN%";
        ResultSet rs= null;
        try {
            rs = ste.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cu = 0;
        try {
            while (rs.next()){
                cu = rs.getInt("cu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cu;
    }

    @Override
    public int countInactifUsers() {
        String req = "SELECT COUNT(*) FROM user u WHERE DATE_ADD(u.last_login, INTERVAL 7 DAY)< now() and role not like %ROLE_SUPER_ADMIN%";
        ResultSet rs= null;
        try {
            rs = ste.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cu = 0;
        try {
            while (rs.next()){
                cu = rs.getInt("cu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cu;
    }

    @Override
    public List<User> getSuggestionUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getBlockedUsers() {
        String req = "SELECT * FROM user where enabled != 1 and role not like %ROLE_SUPER_ADMIN%";
        ResultSet rs= null;
        try {
            rs = ste.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<User> users = new ArrayList<>();
        try {
            while (rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getBoolean("enabled"), rs.getString("salt"), rs.getDate("lastLogin"), rs.getString("roles"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance"), rs.getString("genre"), rs.getString("pays"), rs.getString("region"), rs.getString("ville"), rs.getString("tel"), rs.getString("placeNaiss"), rs.getString("religion"), rs.getString("apropos"), rs.getString("facebook"), rs.getString("twitter"), rs.getString("instagram"), rs.getString("image"), rs.getDate("updatedAt"), rs.getString("occupation")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public void modifierUser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteData(ActionEvent event,ObservableList<User>list,TableView<User> table) throws SQLException {
        
           list=FXCollections.observableArrayList();
        User panier = table.getSelectionModel().getSelectedItem();
        String a = panier.getEmail();
     
        String queryy ="DELETE FROM user WHERE email=?";

        
        PreparedStatement prss= con.prepareStatement(queryy);
        prss.setString(1, a);
        prss.executeUpdate();  
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        table.getSelectionModel().select(null);
             
       
    }
    
    
}
