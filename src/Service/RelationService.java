/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Core.DataSource;
import IService.IRelationService;
import java.sql.Connection;

/**
 *
 * @author hero
 */
public class RelationService implements IRelationService{
    private Connection con = DataSource.getInstance().getCon();
}
