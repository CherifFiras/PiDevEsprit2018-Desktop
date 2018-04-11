/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.AvisEspace;
import Entity.Espace;
import Entity.PhotoEspace;
import Service.AvisEspaceService;
import Service.EspaceService;
import Service.PhotoEspaceService;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Nayer Ben Jaber
 */
public class EspaceContenuController implements Initializable {
    public static int ide;
    @FXML
    private AnchorPane holderPane;
    EspaceService esp = new EspaceService();
    PhotoEspaceService pht = new PhotoEspaceService();
    @FXML
    private Label titre;
    @FXML
    private ImageView image;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image3;
    @FXML
    private Label description;
    @FXML
    private Label adresse;
    @FXML
    private Label email;
    private Pane p;
    @FXML
    private VBox bb;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image31;
    @FXML
    private JFXButton map;
    @FXML
    private Rating rating;
    @FXML
    private JFXButton switchUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        infoespace();
    }

    public void infoespace() {
        InfoEspacefrontController ics = new InfoEspacefrontController();
        List<AvisEspace> ratings = new ArrayList<AvisEspace>();
        AvisEspaceService avis = new AvisEspaceService();
        int id = ics.idp;
        VBox v = new VBox();
        HBox b = new HBox();
        Espace espace = esp.getEspaceById(id);
        PhotoEspace photo = pht.getPhotoById(espace.getId());
                                ide = espace.getId();

        titre.setText(espace.getTitre());
        titre.setWrapText(true);
        description.setText(espace.getDescription());
        description.setWrapText(true);
        description.setPrefWidth(400);
        adresse.setText(espace.getAdresse());
        adresse.setWrapText(true);
        email.setText(espace.getEmail());
        adresse.setWrapText(true);
        ratings = avis.getRating(espace.getId());
        if (!ratings.isEmpty()) {
            int h = 0;
            int d = 0;
            for (int i = 0; i < ratings.size(); i++) {
                d = d + ratings.get(i).getRating();
                h++;

                float m = d / h;
                rating.setRating(m);
            }
        } else {
            rating.setRating(0);
        }
        rating.setTranslateX(0);
        rating.setDisable(true);
        File f = new File("C:/Users/Nayer Ben Jaber/Documents/NetBeansProjects/PiDevEsprit2018-Desktop/src/Images/" + espace.getPhoto());
        Image img = new Image(f.toURI().toString());
        image.setImage(img);
        File f1 = new File("C:/Users/Nayer Ben Jaber/Documents/NetBeansProjects/PiDevEsprit2018-Desktop/src/Images/" + photo.getPhoto1());
        Image img1 = new Image(f1.toURI().toString());
        image1.setImage(img1);
        File f2 = new File("C:/Users/Nayer Ben Jaber/Documents/NetBeansProjects/PiDevEsprit2018-Desktop/src/Images/" + photo.getPhoto2());
        Image img2 = new Image(f2.toURI().toString());
        image3.setImage(img2);
        File f3 = new File("C:/Users/Nayer Ben Jaber/Documents/NetBeansProjects/PiDevEsprit2018-Desktop/src/Images/" + photo.getPhoto3());
        Image img3 = new Image(f3.toURI().toString());
        image11.setImage(img3);
        File f4 = new File("C:/Users/Nayer Ben Jaber/Documents/NetBeansProjects/PiDevEsprit2018-Desktop/src/Images/" + photo.getPhoto4());
        Image img4 = new Image(f4.toURI().toString());
        image31.setImage(img4);
    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

    }

    @FXML
    private void switchMap(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(("../View/Map.fxml")));
        AnchorPane parentContent = fxmlloader.load();
        setNode(parentContent);

    }

    @FXML
    private void switchUpdate(ActionEvent event) throws IOException {
                FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource(("../View/UpdateEspace.fxml")));
        AnchorPane parentContent = fxmlloader.load();
        setNode(parentContent);
    }

}
