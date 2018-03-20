/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entity.Evenement;
import java.util.List;

/**
 *
 * @author hero
 */
public interface IEvenementService {
    public Evenement getEvenementById(int id);
    public Evenement insertEvenement(Evenement e);
    public List<Evenement> getAll();
    public boolean deleteEvenement(Evenement e);
    public boolean updateEvenement(Evenement e);
}
