/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import APIs.ChatListener;
import Controller.NotificationController;
import Entity.User;
import IService.IUserService;

/**
 *
 * @author hero
 */
public class Controller {
    private static int userId;
    private static NotificationController notificationController;
    private final Service service = Service.getInstance();
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
