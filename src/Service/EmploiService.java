/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Emploi;
import Entity.User;
import IService.IEmploiService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hero
 */
public class EmploiService implements IEmploiService {
    private Connection con = DataSource.getInstance().getConnection();

    @Override
    public List<Emploi> getAllEmploisByUser(User u) {
        String req = "SELECT * FROM emploi where idUser=? orderby dateDebut asc";
        ResultSet rs= null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, u.getId());
            rs = ps.executeQuery(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Emploi> e = new ArrayList<>();
        try {
            while (rs.next()){
                e.add(new Emploi(rs.getInt("id"), rs.getString("contenu"), rs.getString("dateDebut"), rs.getString("dateFin"), (User) rs.getObject("idUser")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    @Override
    public void ajouterEmploi(Emploi e, User u) {
        try {
            String req = "INSERT INTO centre_interet (contenu, dateDebut, dateFin, idUser) VALUES (?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, e.getContenu());
            pre.setString(2, e.getDateDebut());
            pre.setString(3, e.getDateFin());
            pre.setInt(4, e.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CentreInteretService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierEmploi(Emploi e) {
        try {
            String req = "update emploi set contenu=?, dateDebut=?, dateFin=? where idUser=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, e.getContenu());
            pre.setString(2, e.getDateDebut());
            pre.setString(3, e.getDateFin());
            pre.setInt(4, e.getIdUser().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CentreInteretService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerEmploi(Emploi e) {
        try {
            String req = "delete from emploi where id=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, e.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CentreInteretService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
