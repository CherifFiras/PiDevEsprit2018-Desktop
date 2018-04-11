/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Message;
import Entity.User;
import IService.IMessageService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hero
 */
public class MessageService implements IMessageService {
    private Connection con = DataSource.getInstance().getCon();

    @Override
    public List<Message> fetchMessage(User cUser,User oUser) {
        List<Message> messages = new ArrayList<>();
        ResultSet resultUser;
        String query = "select * from Message m where(m.sender in (?,?) and m.receiver in (?,?)) order by m.date ASC";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cUser.getId());
            ps.setInt(2, oUser.getId());
            ps.setInt(3, cUser.getId());
            ps.setInt(4, oUser.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ps = con.prepareStatement("select * from user where id = ?");
                ps.setInt(1, rs.getInt("sender"));
                resultUser = ps.executeQuery();
                resultUser.next();
                User sender = User.createUser(resultUser);
                ps.setInt(1, rs.getInt("receiver"));
                resultUser = ps.executeQuery();
                resultUser.next();
                User receiver = User.createUser(resultUser);
                Message message = new Message(rs.getInt("id"), sender, receiver, rs.getString("text"), rs.getDate("date"));
                messages.add(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messages;
    }

    @Override
    public Message insertMessage(Message message) {
        String query = "INSERT INTO message (sender,receiver,text,date) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getSender().getId());
            ps.setInt(2, message.getReceiver().getId());
            ps.setString(3, message.getText());
            ps.setDate(4, new java.sql.Date(message.getDate().getTime()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                message.setId(rs.getInt(1));
                Logger.getGlobal().log(Level.INFO, message.getId().toString());
                return message;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Message getMessageById(int id) {
        String query="SELECT * FROM message WHERE id=?";
        ResultSet resultUser;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setText(rs.getString("text"));
                message.setDate(rs.getTimestamp("date"));
                ps = con.prepareStatement("select * from user where id = ?");
                ps.setInt(1, rs.getInt("sender"));
                resultUser = ps.executeQuery();
                resultUser.next();
                User sender = User.createUser(resultUser);
                ps.setInt(1, rs.getInt("receiver"));
                resultUser = ps.executeQuery();
                resultUser.next();
                User receiver = User.createUser(resultUser);
                message.setSender(sender);
                message.setReceiver(receiver);
                return message;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
