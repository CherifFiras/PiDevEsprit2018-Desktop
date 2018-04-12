/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Message;
import Entity.Signaler;
import Entity.User;
import IService.ISignalerService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hero
 */
public class SignalerService implements ISignalerService {
<<<<<<< HEAD
    private Connection con = DataSource.getInstance().getCon();
    private Statement ste;
=======
    private Connection con = DataSource.getInstance().getConnection();
>>>>>>> d17f97ee5a47138237512e68bcc9638598b21fd1

    public SignalerService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SignalerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public HashMap<User,List<Signaler>> getSignaledUsers() {
        HashMap<User,List<Signaler>> map = new HashMap<>();
        String req = "select u.*,u.id as idudb,s.*,m.text as txt,m.date as dt,s.cause as cause from user u join signaler s on u.id=s.idUser join message m on u.id=m.sender where u.enabled!=0";
        List<Signaler> signaleds = new ArrayList<>();
        try {       
            ResultSet rs = ste.executeQuery(req);            
            while (rs.next()){
                User user = User.createUser(rs);
                if(!map.keySet().contains(user))
                {
                    map.put(user, new ArrayList<>());
                    user.setMessageList(new ArrayList<>());
                }
                User found = map.keySet().stream().filter((u)->{
                   return user.equals(u);
                }).findFirst().get();
                found.getMessageList().add(new Message(0, null, null, rs.getString("txt"), rs.getDate("dt")));
                map.get(found).add(new Signaler(rs.getString("cause"), 0));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return map;
    }

    @Override
    public void ajouterSignal(Signaler s) {
        try {
            String req = "INSERT INTO signaler (cause, idUser) VALUES (?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, s.getCause());
            pre.setInt(2, s.getIdUser());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SignalerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
