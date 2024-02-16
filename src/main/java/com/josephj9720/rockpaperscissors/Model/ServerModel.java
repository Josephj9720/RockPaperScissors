/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.Model;

import com.josephj9720.rockpaperscissors.utility.ClientThread;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josep
 */
public class ServerModel {
    private List<ClientThread> clients;
    
    public ServerModel(){
        clients = new ArrayList<>();
    }
    
    public List<ClientThread> getClients(){
        return this.clients;
    }
    
    public int getClientCount(){
        return this.clients.size();
    }
    
    public void addClient(String clientName, Socket connectionSocket){
        clients.add(new ClientThread(clientName, connectionSocket, clients));
        clients.get(clients.size() - 1).start();
        
    }
    
    public String getNameOfClientAt(int index){
        return clients.get(index).getClientName();
    }
    
    public ClientThread getClientAt(int index){
        return clients.get(index);
    }
}
