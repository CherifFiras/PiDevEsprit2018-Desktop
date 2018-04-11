/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.LayoutFrontController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LayoutFrontController12 implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private ImageView home;
    @FXML
    private ImageView espace;
    @FXML
    private ImageView evenement;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView interaction;
    @FXML
    private ImageView forum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sujet();
        } catch (IOException ex) {
            Logger.getLogger(LayoutFrontController12.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void home(MouseEvent event) {
                       
         }
        private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        
    }

    @FXML
    private void forum(MouseEvent event) throws IOException {
        
           
                FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource(("../View/cat.fxml")));
                     AnchorPane parentContent = null;
        try {
            parentContent = fxmlloader.load();
        } catch (IOException ex) {
            Logger.getLogger(LayoutFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
                     setNode(parentContent);          
    }
    
     private void sujet() throws IOException {
        
              FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource(("../View/Sujet_1.fxml")));
                     AnchorPane parentContent = null;

            
            parentContent = fxmlloader.load() ;
             setNode(parentContent);
        
                                
    }
    
}
