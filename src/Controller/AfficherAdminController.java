/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller;
import Core.DataSource;
import Entity.Evenement;
import IService.IEvenementService;
import Service.EvenementService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author DJAZIA
 */
public class AfficherAdminController extends Controller implements Initializable {

    FileChooser saveFileChooser = new FileChooser();    
    File saveFile;
    File srcFile, destFile;
    
    @FXML
    private TableColumn<Evenement, ?> image;
    @FXML
    private TableColumn<Evenement, Number> nbplaces;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> place;

    private final IEvenementService es = this.getService().getEvenementService();

    @FXML
    private TableView<Evenement> display;
    @FXML
    private DatePicker dateEvenement;
    @FXML
    private TextField nbplacess;
    @FXML
    private TextField placee;
    @FXML
    private TextField titree;
    @FXML
    private TextArea descriptionn;
    @FXML
    private JFXButton Update;
    @FXML
    private JFXButton DELETE;
    private ImageView picview;
    @FXML
    private JFXButton Image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherevent();
    }

    public void afficherevent() {
        ObservableList ok = FXCollections.observableArrayList();
        ok = es.getAll();
        display.setItems(null);
        display.setItems(ok);
        nbplaces.setCellValueFactory(new PropertyValueFactory<>("nbplaces"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        place.setCellValueFactory(new PropertyValueFactory<>("titreCordination"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));

    }

    @FXML
    private void ajouter(ActionEvent event) {

        LocalDate localDate = dateEvenement.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        java.util.Date date = Date.from(instant);
        java.sql.Date dtsql = new java.sql.Date(date.getTime());
        Evenement e = new Evenement(srcFile.getName(), 0, dtsql, titree.getText(), descriptionn.getText(), placee.getText());
        es.insertEvenement(e);
        afficherevent();
        nbplacess.setText(null);
        titree.setText(null);
        descriptionn.setText(null);
        placee.setText(null);
        
    }

    @FXML
    private void modifierEvenements(ActionEvent event) {

        EvenementService ev = new EvenementService();

        JFXTreeTableColumn<Evenement, String> Titre = new JFXTreeTableColumn<>("Titre");
        Titre.setPrefWidth(150);
        Titre.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getTitre());
            }
        });

        JFXTreeTableColumn<Evenement, String> Nbrplace = new JFXTreeTableColumn<>("Nbr place");
        Nbrplace.setPrefWidth(150);
        Nbrplace.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getValue().getNbplaces()));
            }
        });

        JFXTreeTableColumn<Evenement, String> Date = new JFXTreeTableColumn<>("Date");
        Date.setPrefWidth(150);
        Date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getDateEvenement().toString());
            }
        });

        JFXTreeTableColumn<Evenement, String> Desc = new JFXTreeTableColumn<>("Description");
        Desc.setPrefWidth(150);
        Desc.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getDescription());
            }
        });

        JFXTreeTableColumn<Evenement, String> Place = new JFXTreeTableColumn<>("Place");
        Place.setPrefWidth(150);
        Place.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Evenement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Evenement, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().getTitreCordination());
            }
        });

        Titre.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                        new TextFieldEditorBuilder());
        });
        Titre.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = t.getNewValue();
            t.getTreeTableView()
                        .getTreeItem(t.getTreeTablePosition()
                                    .getRow())
                        .getValue().setTitre(t.getNewValue());
            ev.updateEvenement(id, "titre", newValue);
        });

        Nbrplace.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                        new TextFieldEditorBuilder());
        });
        Nbrplace.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = t.getNewValue();
            t.getTreeTableView()
                        .getTreeItem(t.getTreeTablePosition()
                                    .getRow())
                        .getValue().setNbplaces(Integer.parseInt(t.getNewValue()));
            ev.updateEvenement(id, "nbrplaces", newValue);
        });

        Date.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                        new TextFieldEditorBuilder());
        });
        Date.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = t.getNewValue();
            t.getTreeTableView()
                        .getTreeItem(t.getTreeTablePosition()
                                    .getRow())
                        .getValue().setDateEvenement(java.sql.Date.valueOf(t.getNewValue()));
            ev.updateEvenement(id, "dateEvenement", newValue);
        });

        Desc.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                        new TextFieldEditorBuilder());
        });
        Desc.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = t.getNewValue();
            t.getTreeTableView()
                        .getTreeItem(t.getTreeTablePosition()
                                    .getRow())
                        .getValue().setDescription(t.getNewValue());
            ev.updateEvenement(id, "description", newValue);
        });

        Place.setCellFactory((TreeTableColumn<Evenement, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                        new TextFieldEditorBuilder());
        });
        Place.setOnEditCommit((CellEditEvent<Evenement, String> t) -> {
            int id = t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().getId();

            String newValue = t.getNewValue();
            t.getTreeTableView()
                        .getTreeItem(t.getTreeTablePosition()
                                    .getRow())
                        .getValue().setTitreCordination(t.getNewValue());
            ev.updateEvenement(id, "titreCordination", newValue);
        });

        ObservableList<Evenement> myList = ev.getAll();

        JFXTreeTableView<Evenement> treeview = new JFXTreeTableView<>();
        final TreeItem<Evenement> root = new RecursiveTreeItem<Evenement>(myList, RecursiveTreeObject::getChildren);

        treeview.getColumns().setAll(Titre, Nbrplace, Date, Desc, Place);
        treeview.setRoot(root);
        treeview.setShowRoot(false);
        treeview.setEditable(true);

        Dialog diag = new Dialog();
        GridPane grid = new GridPane();
        grid.add(treeview, 0, 0);
        TextField input = new TextField();
        input.setPromptText("Rechercher ..");
        grid.add(input, 0, 1);
        input.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                treeview.setPredicate(new Predicate<TreeItem<Evenement>>() {
                    @Override
                    public boolean test(TreeItem<Evenement> t) {

                        boolean flag = t.getValue().getTitre().contains(newValue);
                        return flag;
                    }
                });
            }
        });
        diag.getDialogPane().setContent(grid);
        ButtonType Ajouter = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        diag.getDialogPane().getButtonTypes().add(Ajouter);
        diag.setResultConverter(new Callback() {
            @Override
            public Object call(Object param) {
                if (param == ButtonType.OK) {

                }
                return null;
            }
        });
        diag.showAndWait();
        if (!diag.isShowing()) {
            this.Update.getScene().getRoot().setEffect(null);
        }

    }

    @FXML
    private void supprimerEvenements(ActionEvent event) {
        Evenement e = display.getSelectionModel().getSelectedItem();
        EvenementService ev = new EvenementService();
        int ide = e.getId();
        ev.deleteEvenement(ide);
        display.getItems().removeAll(display.getSelectionModel().getSelectedItem());
        display.getSelectionModel().select(null);
    }

    @FXML
    private void UploadImage(ActionEvent event) {
        File file = saveFileChooser.showOpenDialog(null);  
        if (file != null) {                
            srcFile = file;
            if (srcFile != null) {
                try {
                    String p = System.getProperty("user.dir")+"/src/images/"+srcFile.getName();
                    copyFile(srcFile, new File(p));
                } catch (IOException ex) {
                    Logger.getLogger(AfficherAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
     public void copyFile(File sourceFile, File destFile) throws IOException {
        if ( !destFile.exists() ) { destFile.createNewFile(); }

    try (
        FileChannel in = new FileInputStream( sourceFile ).getChannel();
        FileChannel out = new FileOutputStream( destFile ).getChannel(); ) {

        out.transferFrom( in, 0, in.size() );
    }
     }   
    }
