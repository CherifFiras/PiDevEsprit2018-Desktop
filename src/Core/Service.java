/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import IService.*;
import Service.*;

/**
 *
 * @author hero
 */
public class Service {
    private static Service service ;
    private IUserService userService;
    private ICentreInteretService centreInteretService;
    private IDemandeService demandeService;
    private IEmploiService emploiService;
    private ILoisirService loisirService;
    private IMessageService messageService;
    private IRelationService relationService;
    private IScolariteService scolariteService;
    private ISignalerService signalerService;
    private Service()
    {
        
    }
    public static Service getInstance()
    {
        if(service == null)
            service = new Service();
        return service;
    }

    public IUserService getUserService()
    {
        if(userService == null)
            userService = new UserService();
        return userService;
    }
    
    public ICentreInteretService getCentreInteretService()
    {
        if(centreInteretService == null)
            centreInteretService = new CentreInteretService();
        return centreInteretService;
    }
    
    public IDemandeService getDemandeService()
    {
        if(demandeService == null)
            demandeService = new DemandeService();
        return demandeService;
    }
    
    public IEmploiService getEmploiService()
    {
        if(emploiService == null)
            emploiService = new EmploiService();
        return emploiService;
    }
    
    public ILoisirService getLoisirService()
    {
        if(loisirService == null)
            loisirService = new LoisirService();
        return loisirService;
    }
    
    public IMessageService getMessageService()
    {
        if(messageService == null)
            messageService = new MessageService();
        return messageService;
    }
    
    public IRelationService getRelationService()
    {
        if(relationService == null)
            relationService = new RelationService();
        return relationService;
    }
    
    public IScolariteService getScolariteService()
    {
        if(scolariteService == null)
            scolariteService = new ScolariteService();
        return scolariteService;
    }
    
    public ISignalerService getSignalerService()
    {
        if(signalerService == null)
            signalerService = new SignalerService();
        return signalerService;
    }
}
