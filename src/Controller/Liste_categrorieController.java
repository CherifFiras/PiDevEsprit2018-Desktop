/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller;
import Core.DataSource;
import Entity.Category;
import Entity.sujet;
import Service.serviceCategory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Liste_categrorieController implements Initializable {

    @FXML
    private TableColumn<Category, Integer> id;
    @FXML
    private TableColumn<Category, String> libelle;
    @FXML
    private TableColumn<Category, String> description;
    @FXML
    private TableColumn<Category, String> date;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<Category> table;
    
    Connection cnx= DataSource.getInstance().getConnection();
    public Statement ste;
    public ResultSet rs;
    private Label label;
    public static Category p;
    public int i ;
    
     private ObservableList<Category> list;
    @FXML
    private Button delete;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         System.out.println("======> + ====>"+Controller.getUserId());
          serviceCategory sv = new serviceCategory();
          list = FXCollections.observableArrayList();

        try {  
            ResultSet rs= cnx.createStatement().executeQuery("select * FROM categorie");
             while (rs.next()) {
                 
                list.add(new Category(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(Liste_categrorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id_category"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("Libelle"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        date.setCellValueFactory(new PropertyValueFactory<>("date_ajout"));
 
        table.setItems(null);
        table.setItems(list);
        table.setEditable(true);

        
        libelle.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        
        
           Parent root = null;
                 try {
                     root = FXMLLoader.load(getClass().getResource("../View/Admin_categorie.fxml"));
                 } catch (IOException ex) {
                     Logger.getLogger(SujetController.class.getName()).log(Level.SEVERE, null, ex);
                 }
        
        Node node =(Node)event.getSource();
                 Stage stage = (Stage)node.getScene().getWindow();
            stage.setScene(new Scene(root));
        
        
    }

    @FXML
    private void deleteData(ActionEvent event) throws SQLException {

          serviceCategory spa= new serviceCategory();
          spa.deleteData(event, list, table);

        
    }
    
}
