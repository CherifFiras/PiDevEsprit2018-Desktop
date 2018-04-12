/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entity.Message;
import Entity.User;
import java.util.List;

/**
 *
 * @author hero
 */
public interface IMessageService {
<<<<<<< HEAD
    
    public List<Message> fetchMessage(User cUser,User oUser);
    public Message insertMessage(Message message);
    public Message getMessageById(int id);
    public Message getMessageSenderByUser(int id);
=======
    public List<Message> fetchMessage(User cUser,User oUser);
    public Message insertMessage(Message message);
    public Message getMessageById(int id);
>>>>>>> d17f97ee5a47138237512e68bcc9638598b21fd1
}
