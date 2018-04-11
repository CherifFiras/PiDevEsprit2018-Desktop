/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Entity.User;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hero
 */
public class Controller {
    private static int userId;
    public static AnchorPane holderPane;
    private Service service = Service.getInstance();
    protected static void setUserId(int id)
    {
        if(id == 0) return ;
        Controller.userId = id ;
    }
    public static int getUserId()
    {
        return Controller.userId;
    }
    
    public User getUser()
    {
        return new User();
    }
    public Service getService()
    {
        return service;
    }
}
