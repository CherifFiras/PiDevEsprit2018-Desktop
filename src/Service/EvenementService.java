/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Evenement;
import IService.IEvenementService;
import java.sql.Connection;
import java.sql.Date;
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
public class EvenementService implements IEvenementService {
    private Connection con = DataSource.getInstance().getCon();
    

    @Override
    public Evenement getEvenementById(int id) {
        try {
            String query = "select * from evenement where id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Evenement evenement;
            while(rs.next())
            {
                evenement = new Evenement(rs.getInt("id"), rs.getString("imageEve"), rs.getInt("nbplaces"), rs.getDate("dateEvenement") , rs.getString("titre"), rs.getString("description"), rs.getString("titreCordination"));
                return evenement;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Evenement insertEvenement(Evenement e) {
        try {
            String query = "INSERT INTO `evenement`(`image_eve`, `nbplaces`, `dateEvenement`, `titre`, `description`, `titreCordination`) VALUES(?,?,?,?,?,?)";
            PreparedStatement prs = con.prepareStatement(query);
            prs.setString(1, e.getImageEve());
            prs.setInt(2, e.getNbplaces());
            prs.setDate(3, new Date(e.getDateEvenement().getTime()));
            prs.setString(4, e.getTitre());
            prs.setString(5, e.getDescription());
            prs.setString(6, e.getTitreCordination());
            int id = prs.executeUpdate();
            e.setId(id);
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Evenement> getAll() {
        try {
            String query = "select * from evenement";
            Statement ste = con.createStatement();
            ResultSet rs = ste.executeQuery(query);
            List<Evenement> evenements = new ArrayList<>();
            while (rs.next()) {
                evenements.add(new Evenement(rs.getInt("id"), rs.getString("image_Eve"), rs.getInt("nbplaces"), rs.getDate("dateEvenement") , rs.getString("titre"), rs.getString("description"), rs.getString("titreCordination")));
                
            }
            return evenements;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean deleteEvenement(Evenement e) {
        try {
            Connection con = DataSource.getInstance().getCon();
            String query="DELETE from evenement where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, e.getId());
            ps.executeUpdate();
            System.out.println("Suppression avec Succés !");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateEvenement(Evenement e) {
        try {
            String query = "Update evenement set nbplaces=? , dateEvenement=?, titre=?, description=?, titreCordination=?";
            PreparedStatement prs = con.prepareStatement(query);
            prs.setInt(1, e.getNbplaces());
            prs.setDate(2, new Date(e.getDateEvenement().getTime()));
            prs.setString(3, e.getTitre());
            prs.setString(4, e.getDescription());
            prs.setString(5, e.getTitreCordination());
            int id = prs.executeUpdate();
            e.setId(id);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   
}
