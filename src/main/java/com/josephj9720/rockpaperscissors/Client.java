/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.josephj9720.rockpaperscissors;
import com.josephj9720.rockpaperscissors.Controller.ClientController;
import com.josephj9720.rockpaperscissors.Model.ClientModel;
import com.josephj9720.rockpaperscissors.View.ClientView;
/**
 *
 * @author josep
 */
public class Client {

    public static void main(String[] args) throws Exception {
        ClientView clientView = new ClientView();
        ClientModel clientModel = new ClientModel();
        ClientController clientController = new ClientController(clientView, clientModel);
        clientView.showView();
       
    }
}
