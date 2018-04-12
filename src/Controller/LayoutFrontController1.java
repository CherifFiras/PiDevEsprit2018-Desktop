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
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nayer Ben Jaber
 */
public class LayoutFrontController1 extends beblio implements Initializable {

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
    @FXML
    private AnchorPane holderPane;
    VBox v = new VBox();
    AnchorPane an = new AnchorPane();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            sujet();
        } catch (IOException ex) {
            Logger.getLogger(LayoutFrontController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void home() throws IOException {
        
                  
    }
        private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

    
        
    }

    @FXML
    private void forum(MouseEvent event) {
       
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
        
              FXMLLoader fxmlloader= new FXMLLoader(getClass().getResource(("../View/Sujet.fxml")));
                     AnchorPane parentContent = null;

            
            parentContent = fxmlloader.load() ;
             setNode(parentContent);
        
                                
    }
        


    
}
