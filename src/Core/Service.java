/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import IService.IUserService;
import Service.UserService;

/**
 *
 * @author hero
 */
public class Service {
    private static Service service ;
    private IUserService iUserService;
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
        if(iUserService == null)
            iUserService = new UserService();
        return iUserService;
    }
}
