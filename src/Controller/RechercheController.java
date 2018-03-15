/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller;
import Entity.User;
import IService.IUserService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author hero
 */
public class RechercheController extends Controller {
    private IUserService userService = this.getService().getUserService();
    public void search()
    {
        Calendar c = Calendar.getInstance();
        c.set(1990, 1, 1);
        Date dmax = new Date(c.getTimeInMillis());
        c.set(2000, 1, 1);
        Date dmin = new Date(c.getTimeInMillis());
        List<User> users = userService.searchResult(3, dmin, dmax, "M", new ArrayList<>(Arrays.asList("Ingenieur","Aastronaute","Acteur/Actrisse")), new ArrayList<>(), 
                new ArrayList<>(Arrays.asList("tn","fr","us")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(Arrays.asList("Game of Thrones")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        System.out.println(users);
    }
    public static void main(String[] Args)
    {
        RechercheController rc = new RechercheController();
        rc.search();
    }
}
