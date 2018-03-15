/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Demande;
import Entity.User;
import IService.IDemandeService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hero
 */
public class DemandeService implements IDemandeService{
    private Connection con = DataSource.getInstance().getCon();

    @Override
    public Demande insertDemande(Demande demande) {
        try {
            String query  = "insert into `demande` (`requester`,`acceptor`,`dateDemande`) Values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,demande.getRequester().getId());
            ps.setInt(2,demande.getRequester().getId());
            ps.setDate(3, new Date(demande.getDateDemande().getTime()));
            int id = ps.executeUpdate();
            demande.setId(id);
            return demande;
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deleteDemande(Demande demande) {
        try {
            String query  = "delete from `demande` where id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,demande.getId());
            return ps.executeUpdate() > 0 ;
        } catch (SQLException ex) {
            Logger.getLogger(DemandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Demande> getDemandesByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
