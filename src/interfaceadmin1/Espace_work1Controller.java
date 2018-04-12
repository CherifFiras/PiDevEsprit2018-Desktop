/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceadmin1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Nayer Ben Jaber
 */
public class Espace_work1Controller implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private TableView<?> listEspace;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> photo;
    @FXML
    private TableColumn<?, ?> longitude;
    @FXML
    private TableColumn<?, ?> latitude;
    @FXML
    private TableColumn<?, ?> iduser;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
