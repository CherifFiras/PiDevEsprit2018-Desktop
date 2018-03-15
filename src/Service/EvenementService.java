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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                evenement = new Evenement(rs.getInt("id"), rs.getInt("nbplaces"),rs.getString("titre"), rs.getString("description"), rs.getString("titrecordination"));
                return evenement;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Evenement insertEvenement(Evenement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evenement> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEvenement(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateEvenement(Evenement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
