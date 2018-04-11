/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import APIs.ChatListener;
import APIs.MatchingListener;
import Core.Controller;
import Entity.Message;
import Entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author hero
 */
public class MatchingController extends Controller implements Initializable {

    @FXML
    private TextField messageBox;
    @FXML
    private ListView chatList;
    @FXML
    private ImageView userImage;
    @FXML
    private Label userName;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messageBox.addEventFilter(KeyEvent.KEY_PRESSED, ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                try {
                    sendButtonAction();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ke.consume();
            }
        });
    }    

    public void userLeaved()
    {
        waiting();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatList.getItems().clear();
            }
        });
        try {
            MatchingListener.sendRequest();
        } catch (IOException ex) {
            Logger.getLogger(MatchingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void startAction(ActionEvent event) {
        chatList.getItems().clear();
        try {
            MatchingListener.sendRequest();
        } catch (IOException ex) {
            Logger.getLogger(MatchingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stopAction(ActionEvent event) {
        try {
            MatchingListener.sendStop();
        } catch (IOException ex) {
            Logger.getLogger(MatchingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendButtonAction() throws IOException {
        String text = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
            Message message = new Message();
            message.setId(Integer.SIZE);
            message.setSender(this.getUser());
            message.setText(text);
            message.setDate(new Date());
            MatchingListener.send(message);
            messageBox.clear();
        }
    }

    public void addToChat(Message message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                HBox hBox = new HBox();
                Label userName = new Label(message.getSender().getPrenom()+" "+message.getSender().getNom()+" :");
                Label text = new Label(message.getText());
                hBox.getChildren().add(userName);
                hBox.getChildren().add(text);
                chatList.getItems().add(hBox);
            }
        });
    }

    @FXML
    private void nextAction(ActionEvent event) {
        chatList.getItems().clear();
        try {
            MatchingListener.sendRequest();
        } catch (IOException ex) {
            Logger.getLogger(MatchingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void found(User sender) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userImage.setImage(new Image(getClass().getResource("../Images/"+sender.getImage()).toExternalForm()));
                userName.setText(sender.getPrenom()+" "+sender.getNom());
                messageBox.setDisable(false);
            }
        });
    }

    public void waiting() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userImage.setImage(null);
                userName.setText("Waiting");
                messageBox.setDisable(true);
            }
        });
        
    }
    
}
