/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import APIs.ChatListener;
import Controller.NotificationController;
import Entity.User;
<<<<<<< HEAD
=======
import IService.IUserService;
>>>>>>> 9016e8775a264a50e6e0402d3c9f8ed5e92458f9
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hero
 */
public class Controller {
    public static AnchorPane holderPane;
    private static int userId;
<<<<<<< HEAD
    public static AnchorPane holderPane;
    private Service service = Service.getInstance();
    protected static void setUserId(int id)
=======
    private static NotificationController notificationController;
    private final Service service = Service.getInstance();
    private final IUserService userService = service.getUserService();
    public static void setUserId(int id)
>>>>>>> 9016e8775a264a50e6e0402d3c9f8ed5e92458f9
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
        return userService.getUserById(userId);
    }
    public Service getService()
    {
        return service;
    }
    
    public static NotificationController getNotificationController() {
        return notificationController;
    }

    public static void setNotificationController(NotificationController notificationController) {
        Controller.notificationController = notificationController;
    }
    
}
