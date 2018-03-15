/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller;
import Entity.Espace;
import IService.IAvisEspaceService;
import IService.IEspaceService;

/**
 *
 * @author hero
 */
public class EspaceController extends Controller {
    private IEspaceService espaceService = this.getService().getEspaceService();
    private IAvisEspaceService avisEspaceService = this.getService().getAvisEspaceService();
    public void getEspace()
    {
        Espace espace = espaceService.getEspaceById(2);
        System.out.println(espace.getTitre());
    }
    
    public static void main(String[] main)
    {
        EspaceController espaceController = new EspaceController();
        espaceController.getEspace();
    }
}
