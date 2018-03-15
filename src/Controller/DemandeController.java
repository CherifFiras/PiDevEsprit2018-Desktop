/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller;
import Entity.Demande;
import Entity.User;
import IService.IDemandeService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hero
 */
public class DemandeController extends Controller {
    private IDemandeService demandeService = this.getService().getDemandeService();
    
    public void ajouterDemande()
    {
        Demande demande = new Demande();
        User u1 = new User();
        User u2 = new User();
        u1.setId(5);
        u2.setId(4);
        demande.setAcceptor(u1);
        demande.setRequester(u2);
        demande.setDateDemande(new Date());
        demande =  demandeService.insertDemande(demande);
        System.out.println(demande.getId());
    }
    
    public void supprimerDemande()
    {
        User u1 = new User();
        User u2 = new User();
        u1.setId(4);
        u2.setId(5);
        Demande demande = new Demande();
        demande.setId(1);
        boolean b =  demandeService.deleteDemande(demande);
        System.out.println(b);
    }
    
    public void getDemandes()
    {
        User u1 = new User();
        u1.setId(5);
        List<Demande> demandes = demandeService.getDemandesByUser(u1);
        demandes.stream().forEach((d) -> {
            System.out.println(d.getRequester());
        });
    }
    
    
    public static void main(String[] args)
    {
        DemandeController demandeController = new DemandeController();
        demandeController.getDemandes();
    }
}
