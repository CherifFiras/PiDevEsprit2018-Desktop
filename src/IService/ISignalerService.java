/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entity.Signaler;
import Entity.User;
import java.util.List;

/**
 *
 * @author Achrafoun
 */
public interface ISignalerService {
    
    public List<Signaler> getSignaledUsers();
    
    public void ajouterSignal(User u);
}
