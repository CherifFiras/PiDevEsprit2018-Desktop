/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Controller.NotificationController;
import Entity.User;
<<<<<<< HEAD
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
=======
import IService.IUserService;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.layout.AnchorPane;
>>>>>>> d17f97ee5a47138237512e68bcc9638598b21fd1

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
<<<<<<< HEAD
    
    //----------
    public static void getLocationByIp() throws IOException, GeoIp2Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        String ip = in.readLine();
        System.out.println(ip);

        //String ip = "your-ip-address";
        String dbLocation = "GeoLite2-City.mmdb";

        File database = new File(dbLocation);
        DatabaseReader dbReader = new DatabaseReader.Builder(database).build();

        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String countryName = response.getCountry().getName();
        String cityName = response.getCity().getName();
        String postal = response.getPostal().getCode();
        String state = response.getLeastSpecificSubdivision().getName();       
        System.out.println("CountryName: "+countryName+"  ,CityName: "+cityName+"  ,PostCode: "+postal+"  ,State: "+state);        
    }    
=======
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
>>>>>>> d17f97ee5a47138237512e68bcc9638598b21fd1
    
}
