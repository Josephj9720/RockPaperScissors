/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.Controller;

import com.josephj9720.rockpaperscissors.Model.ServerModel;
import com.josephj9720.rockpaperscissors.View.ServerView;
import com.josephj9720.rockpaperscissors.utility.ClientThread;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author josep
 */
public class ServerController {
    private ServerSocket serverSocket;
    private ServerView serverView;
    private ServerModel serverModel;
    
    public ServerController(ServerView serverView, ServerModel serverModel) throws IOException{
        
        this.serverView = serverView;
        this.serverModel = serverModel;       
        this.serverSocket = new ServerSocket(6789);
        
        this.ListenForConnectionThread();
        this.CountConnectedClientsThread();
        
    }
    
    public void ListenForConnectionThread(){
        
        new Thread(new Runnable(){
            @Override
            public void run() {
                
                Socket connectionSocket;
                DataOutputStream outToNewClient;
                BufferedReader inFromClient;
                
                while(!serverSocket.isClosed()){
                    
                    try{
                        
                        connectionSocket = serverSocket.accept();
                        
                        boolean taken = false;
                        inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                        String clientSentence = inFromClient.readLine();
                        outToNewClient = new DataOutputStream(connectionSocket.getOutputStream());
                        String[] newClientName = clientSentence.split(",");
                        
                        for(int i = 0; i < serverModel.getClientCount(); i++){
                            
                            if(clientSentence.startsWith("-Name")){
                                
                                if(serverModel.getNameOfClientAt(i).equalsIgnoreCase(newClientName[1])){
                                    
                                    outToNewClient.writeBytes("-Taken\n");
                                    taken = true;
                                    i = serverModel.getClientCount();
                                    
                                }
                                
                            }
                            
                        }
                        
                        if (!taken && !newClientName[1].trim().isEmpty()){
                            
                            outToNewClient.writeBytes("-Connected\n");
                            
                            serverModel.addClient(newClientName[1], connectionSocket);
                            System.out.println("Just Added Client: " + newClientName[1]);
                            
                            for(int i = 0; i < serverModel.getClientCount(); i++){
                                
                                if(!serverModel.getNameOfClientAt(i).equalsIgnoreCase(newClientName[1])){
                                    
                                    ClientThread existingClient = serverModel.getClientAt(i);
                                    DataOutputStream sendToExistingClient = 
                                            new DataOutputStream(existingClient.getConnectionSocket().getOutputStream());
                                    sendToExistingClient.writeBytes("-addList," + newClientName[1] + "\n");
                                    
                                    if(existingClient.isAvailable()){
                                        outToNewClient.writeBytes("-addList," + existingClient.getClientName() + "\n");
                                    }
                                    
                                }
                                
                            }
                            
                        }
                        
                        
                    } catch (IOException ex) {
                        
                        System.out.println(ex.getMessage());
                        
                    }
                    
                }
                
            }
            
        }).start();
        
    }
    
    private void CountConnectedClientsThread(){
        
        new Thread(new Runnable(){
            @Override
            public void run() {
                
                try{
                    
                    while(true){
                        int clientCount = serverModel.getClientCount();
                        //System.out.println(clientCount);
                        
                        if(clientCount == 1){
                            
                            serverView.setConnectionStatusLabel("1 Client Connected", Color.BLUE);
                            
                        } else if (clientCount > 1){
                            
                            serverView.setConnectionStatusLabel(clientCount + " Clients Connected", Color.BLUE);
                            
                        } else {
                            
                            serverView.setConnectionStatusLabel("No Clients Connected", Color.RED);
                            
                        }
                        
                        Thread.sleep(1000);
                        
                    }
                    
                } catch(InterruptedException ex) {
                    
                    System.out.println(ex.getMessage());
                    
                }
                
            }
            
        }).start();
        
    }
    
}
