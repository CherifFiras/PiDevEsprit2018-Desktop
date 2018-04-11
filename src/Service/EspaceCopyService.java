/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import IService.IEspaceCopyService;
import java.sql.Connection;

/**
 *
 * @author hero
 */
public class EspaceCopyService implements IEspaceCopyService {
    private Connection con = DataSource.getInstance().getConnection();
}
