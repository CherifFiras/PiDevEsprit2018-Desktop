/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceadmin1;

import Entity.Espace;
import Service.EspaceService;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nayer Ben Jaber
 */
public class Espace_work1Controller implements Initializable {

 private EspaceService esp;
    @FXML
    private AnchorPane holderPane;
    private StackPane contnainer;
    @FXML
    private TableView<Espace> listEspace;
    @FXML
    private TableColumn<Espace, String> titre;
    @FXML
    private TableColumn<Espace, String> description;
    @FXML
    private TableColumn<Espace, String> adresse;
    @FXML
    private TableColumn<Espace, String> email;
    @FXML
    private TableColumn<Espace, String> photo;
    @FXML
    private TableColumn<Espace, Float> longitude;
    @FXML
    private TableColumn<Espace, Float> latitude;
    @FXML
    private TableColumn<Espace, Integer> iduser;
    private Alert ActionsAlert;
    private ButtonType confirmer;
    private ButtonType supprimer;
    private ButtonType annuler;
    private Alert ConfirmDelete;
    private ButtonType Oui;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_buttons();
        EspaceController espace = new EspaceController();
        esp = new EspaceService();
        ArrayList arrayList = (ArrayList) espace.afficherEspaceConfirmer();

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        photo.setCellValueFactory(new PropertyValueFactory<>("Photo"));
        longitude.setCellValueFactory(new PropertyValueFactory<>("longi"));
        latitude.setCellValueFactory(new PropertyValueFactory<>("lat"));
        iduser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        listEspace.setItems(observableList);

        listEspace.setRowFactory(tv -> {
            TableRow<Espace> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty())) {
                    Optional<ButtonType> result = ActionsAlert.showAndWait();
                    if (result.isPresent() && result.get() == supprimer) {

                        esp.removeEspace(row.getItem().getId());
                        listEspace.getItems().removeAll(listEspace.getSelectionModel().getSelectedItem());
                        listEspace.getSelectionModel().select(null);

                    } else {
                        // if annuler clicked 
                    }

                }

            });
            return row;
        }
        );
    }

    public void set_content(Node widget) {
        contnainer.getChildren().clear();
        contnainer.getChildren().add(widget);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(widget);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void init_buttons() {
        confirmer = new ButtonType("Confirmer");
        supprimer = new ButtonType("Supprimer");
        annuler = new ButtonType("Annuler");
        ActionsAlert = new Alert(Alert.AlertType.CONFIRMATION);
        ActionsAlert.setContentText("Que voulez vous faire avec cet espace");
        ActionsAlert.setTitle("MySoulMate");
        ActionsAlert.setHeaderText("Gestion Espace");
        ActionsAlert.getButtonTypes().clear();
        ActionsAlert.getButtonTypes().addAll(confirmer, supprimer, annuler);

    }
  
    
}
