/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Controller.NotificationController;
import Entity.User;
import IService.IUserService;
import java.util.Calendar;
import java.util.Date;
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
    public String getLongDateFormat(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR)+" "+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);
    }
    public static NotificationController getNotificationController() {
        return notificationController;
    }

    public static void setNotificationController(NotificationController notificationController) {
        Controller.notificationController = notificationController;
    }
    
}
