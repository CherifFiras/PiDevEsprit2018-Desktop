/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import IService.ICentreInteretService;
import java.sql.Connection;

/**
 *
 * @author hero
 */
public class CentreInteretService implements ICentreInteretService {
    private Connection con = DataSource.getInstance().getCon();
}
