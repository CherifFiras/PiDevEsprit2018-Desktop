/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import IService.IScolariteService;
import java.sql.Connection;

/**
 *
 * @author hero
 */
public class ScolariteService implements IScolariteService {
    private Connection con = DataSource.getInstance().getCon();
}
