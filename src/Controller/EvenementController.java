/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller;
import Entity.Evenement;
import IService.IEvenementService;
import Service.EvenementService;

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
    
    public static void main(String[] Args)
    {
        EvenementController evenementController = new EvenementController();
        evenementController.getEvenement();
    }
}