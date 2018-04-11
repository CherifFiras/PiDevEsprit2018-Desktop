/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Publication;
import Entity.User;
import IService.IPublicationService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Achrafoun
 */
public class PublicationService implements IPublicationService{
    private Connection con = DataSource.getInstance().getConnection();

    @Override
    public List<Publication> getPublicationByUser(User u) {
        String req = "SELECT * FROM publication where idUser=? orderby datePublication desc";
        ResultSet rs= null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, u.getId());
            rs = ps.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Publication> p = new ArrayList<>();
        try {
            while (rs.next()){
                p.add(new Publication(rs.getInt("id"), rs.getString("contenu"), rs.getDate("datePublication"), (User) rs.getObject("idUser")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public void ajouterPublication(Publication p) {
        try {
            String req = "INSERT INTO publication (contenu, datePublication, idUser) VALUES (?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, p.getContenu());
            
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            pre.setTimestamp(2, date);
            
            pre.setInt(3, p.getIdUser().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CentreInteretService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierPublication(Publication p) {
        try {
            String req = "update publication set contenu=?, datePublication=? where idUser=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, p.getContenu());
            
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            pre.setTimestamp(2, date);
            
            pre.setInt(3, p.getIdUser().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CentreInteretService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerPublication(Publication p) {
        try {
            String req = "delete from publication where id=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, p.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CentreInteretService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
