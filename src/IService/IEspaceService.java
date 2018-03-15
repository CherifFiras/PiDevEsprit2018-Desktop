/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entity.Espace;
import java.util.List;

/**
 *
 * @author hero
 */
public interface IEspaceService {
    public Espace getEspaceById(int id);
    public List<Espace> getAll();
}
