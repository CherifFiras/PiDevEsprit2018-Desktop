/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Evenement;
import IService.IParticipationService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hero
 */
public class ParticipationService implements IParticipationService {
<<<<<<< HEAD
    private Connection con = DataSource.getInstance().getCon();
//     {
//         try {
//             String req="UPDATE `event` SET `participants`=? WHERE `id`=?";
//             PreparedStatement prs = con.prepareStatement(req);
//             prs.setInt(1,(ev.getNbreParticipants())+1);
//             prs.setInt(2,ev.getId());
//             prs.executeUpdate();
//             System.out.println("services.ServiceEvents.increment()");  
//  
//         } catch (SQLException e) {
//             System.out.println("404");
//         }
//         
//     }
    @Override
       public   void increment(Evenement ev,int id)
     {
         try {
             String req="UPDATE `Evenement` SET `nbplaces`=? WHERE `id`=?";
             PreparedStatement prs = con.prepareStatement(req);
             prs.setInt(1,(ev.getNbplaces())+1);
             prs.setInt(2,ev.getId());
             prs.executeUpdate();

  
         } catch (SQLException e) {
             System.out.println("404");
         }
         
     }
    @Override
         public   void decrement(Evenement ev,int id)
     {
         try {
             String req="UPDATE `Evenement` SET `nbplaces`=? WHERE `id`=?";
             PreparedStatement prs = con.prepareStatement(req);
             prs.setInt(1,(ev.getNbplaces())-1);
             prs.setInt(2,ev.getId());
             prs.executeUpdate();
  
         } catch (SQLException e) {
             System.out.println("404");
         }
         
     }
=======
    private Connection con = DataSource.getInstance().getConnection();
>>>>>>> 9016e8775a264a50e6e0402d3c9f8ed5e92458f9
}
