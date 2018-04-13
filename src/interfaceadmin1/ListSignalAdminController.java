/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceadmin1;

import Core.Controller;
import Entity.Message;
import Entity.Signaler;
import Entity.User;
import IService.IMessageService;
import IService.ISignalerService;
import IService.IUserService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Achrafoun
 */
public class ListSignalAdminController extends Controller implements Initializable {
    private IUserService userService = this.getService().getUserService();
    private IMessageService messageService = this.getService().getMessageService();
    private ISignalerService signalerService = this.getService().getSignalerService();

    @FXML
    private AnchorPane holderPane;
    @FXML
    private VBox vboxRow;
    @FXML
    private Label username;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label tel;
    @FXML
    private Label mail;
    @FXML
    private Label nbSig;
    @FXML
    private Button affPub;
    @FXML
    private Button block;
    @FXML
    private Button affCauses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> lst = new ArrayList<>();
        List<Message> lstM = new ArrayList<>();
        List<Signaler> lstS = new ArrayList<>();
        
        System.out.println(signalerService.getSignaledUsers());
        
    }
    
    public HBox hRowItem(User u){
        Font prefFont = new Font("System", 12);
        HBox hbox = new HBox();
        hbox.setPrefSize(1097, 38);
        hbox.setStyle("-fx-border-color: black;");
        
        Label usernameL = new Label(u.getUsername());
        usernameL.setFont(prefFont);
        HBox.setMargin(usernameL, new Insets(10, 0, 0, 5));
        
        Label nomL = new Label(u.getNom());
        nomL.setFont(prefFont);
        HBox.setMargin(nomL, new Insets(10, 0, 0, 85));
        
        Label prenomL = new Label(u.getPrenom());
        prenomL.setFont(prefFont);
        HBox.setMargin(prenomL, new Insets(10, 0, 0, 85));
        
        Label telL = new Label(u.getTel());
        telL.setFont(prefFont);
        HBox.setMargin(telL, new Insets(10, 0, 0, 85));
        
        Label mailL = new Label(u.getEmail());
        mailL.setFont(prefFont);
        HBox.setMargin(mailL, new Insets(10, 0, 0, 50));
        
        Button affCause = new Button("Afficher Causes");
        affCause.setMnemonicParsing(false);
        HBox.setMargin(affCause, new Insets(7, 0, 0, 70));
        affCause.setId(u.getId().toString());
        affCause.setOnAction(this::afficherCausesAction);
        
        Button affMsg = new Button("Afficher Msgs");
        affMsg.setMnemonicParsing(false);
        HBox.setMargin(affMsg, new Insets(7, 0, 0, 70));
        affMsg.setId(u.getId().toString());
        affMsg.setOnAction(this::afficherPubAction);
        
        Button bloquer = new Button("Block");
        bloquer.setMnemonicParsing(false);
        HBox.setMargin(bloquer, new Insets(7, 0, 0, 70));
        bloquer.setId(u.getId().toString());
        bloquer.setOnAction(this::afficherPubAction);
        
        hbox.getChildren().addAll(usernameL,nomL,prenomL,telL,mailL,affCause,affMsg,bloquer);
        
        
        return hbox;
    }

    @FXML
    private void afficherPubAction(ActionEvent event) {
    }

    @FXML
    private void bloquerAction(ActionEvent event) {
    }

    @FXML
    private void afficherCausesAction(ActionEvent event) {
    }
    
}
