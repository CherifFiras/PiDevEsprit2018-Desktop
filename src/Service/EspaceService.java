/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Espace;
import Entity.Evenement;
import IService.IEspaceService;
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
public class EspaceService implements IEspaceService {
    private Connection con = DataSource.getInstance().getConnection();

    @Override
    public Espace getEspaceById(int id) {
                try {
            String query = "select * from espace where id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Espace espace;
            while(rs.next())
            {
                espace = new Espace(rs.getString("titre"), rs.getString("description"), rs.getString("adresse"), rs.getString("photo"), rs.getInt("etat"), rs.getInt("nbrating"), rs.getInt("rating"));
                return espace;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Espace> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
