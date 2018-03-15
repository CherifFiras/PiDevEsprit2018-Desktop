/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import Entity.Signaler;
import Entity.User;
import IService.ISignalerService;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author hero
 */
public class SignalerService implements ISignalerService {
    private Connection con = DataSource.getInstance().getCon();

    @Override
    public List<Signaler> getSignaledUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterSignal(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
