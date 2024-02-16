/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors;

import com.josephj9720.rockpaperscissors.Controller.ServerController;
import com.josephj9720.rockpaperscissors.Model.ServerModel;
import com.josephj9720.rockpaperscissors.View.ServerView;

/**
 *
 * @author josep
 */
public class Server {
    
    public static void main(String[] args) throws Exception {
        
        ServerView serverView = new ServerView();
        ServerModel serverModel = new ServerModel();
        ServerController serverController = new ServerController(serverView, serverModel);
        serverView.showView();
        
    }
    
}
