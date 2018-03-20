/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller;
import Entity.Evenement;
import IService.IEvenementService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author hero
 */
public class EvenementController extends Controller {
    private IEvenementService evenementService = this.getService().getEvenementService();
    
    public void getEvenement()
    {
        Evenement e = evenementService.getEvenementById(4);
        System.out.println(e.getTitre());
    }
    
    public void afficherEvenements()
    {
        List<Evenement> evenements = evenementService.getAll();
        evenements.forEach((e) -> {
            System.out.println(e);
        });
    }
    
    public void ajouterEvenement()
    {
        Calendar c = Calendar.getInstance();
        c.set(2018, 03, 18);
        Date date = new Date(c.getTimeInMillis());
        Evenement e = new Evenement("efezf", 100, date , "test", "description", "esprit");
        evenementService.insertEvenement(e);
    }
    
    public void updateEvenement()
    {
        Calendar c = Calendar.getInstance();
        c.set(2019, 03, 18);
        Date date = new Date(c.getTimeInMillis());
        Evenement e = new Evenement("efezf", 100, date , "test", "description", "esprit");
        evenementService.updateEvenement(e);
    }
    
    public void deleteEvenement()
    {
        Evenement e = new Evenement(107);
        evenementService.deleteEvenement(e);
    }
    public static void main(String[] Args)
    {
        EvenementController evenementController = new EvenementController();
        evenementController.deleteEvenement();
    }
}
