/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Controller.NotificationController;
import Entity.User;
import IService.IUserService;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hero
 */
public class Controller {
    public static AnchorPane holderPane;
    private static int userId;
    private Service service = Service.getInstance();
    private static NotificationController notificationController;
    private final IUserService userService = service.getUserService();
    public static void setUserId(int id)
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
