/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author josep
 */
public class ClientThread extends Thread{
    
    private String clientName;
    private ClientThread opponent;
    private boolean isAvailable;
    private String playerMove; //choice in previous version
    private Socket connectionSocket;
    private List<ClientThread> clients;
    
    public ClientThread(){
        this.clientName = "";
    }
    
    public ClientThread(String clientName, Socket connectionSocket, List<ClientThread> clients){
        this.clientName = clientName;
        this.playerMove = "";
        this.isAvailable = true;
        this.connectionSocket = connectionSocket;
        this.clients = clients;
        this.opponent = new ClientThread();
    }
    
    public String getClientName(){
        return this.clientName;
    }
    
    public Socket getConnectionSocket(){
        return this.connectionSocket;
    }
    
    public boolean isAvailable(){
        return this.isAvailable;
    }
    
    private void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }
    
    private ClientThread getOpponent(){
        return this.opponent;
    }
    
    private void setOpponent(ClientThread opponent){
        this.opponent = opponent;
    }
    
    private String getPlayerMove(){
        return this.playerMove;
    }
    
    private void setPlayerMove(String playerMove){
        this.playerMove = playerMove;
    }
    
    private void resetPlayerMove(){
        this.playerMove = "";
    }
    
    private void removeClient(){
        
        try{
            

            DataOutputStream outToClient;

            for(int i = 0; i < clients.size(); i++){

                ClientThread client = clients.get(i);

                if(!client.getClientName().equalsIgnoreCase(this.clientName)){

                    outToClient = new DataOutputStream(client.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-removeList," + this.clientName +"\n");

                }

            }

            if(!"".equals(this.opponent.getClientName())){

                this.opponent.setOpponent(new ClientThread());
                this.opponent.setAvailable(true);

                for(int i = 0; i < clients.size(); i++){

                    ClientThread currentClient = clients.get(i);
                    String currentClientName = currentClient.getClientName();
                    String opponentName = this.opponent.getClientName();

                    if(!currentClientName.equalsIgnoreCase(this.clientName) && !currentClientName.equalsIgnoreCase(opponentName)){

                        outToClient = new DataOutputStream(currentClient.getConnectionSocket().getOutputStream());
                        outToClient.writeBytes("-addList," + this.opponent.getClientName() + "\n");

                    }

                    outToClient = new DataOutputStream(this.opponent.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-Stopped\n");

                }

            }

            clients.remove(this);
            connectionSocket.close();
            
        } catch(IOException ex){
            
            System.out.println(ex.getMessage());
            
        }
                    
    }
    
    private void requestToPlayWithOpponent(String opponentName){
                
        try{
            
            DataOutputStream outToClient;
            this.isAvailable = false;
            
            for(int i = 0; i < clients.size(); i++){
                
                ClientThread client = clients.get(i);
                
                if(client.getClientName().equals(opponentName)){
                    
                    this.opponent = client;
                    client.setOpponent(this);
                    outToClient = new DataOutputStream(this.opponent.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-Request," + this.clientName + "\n");
                    
                }
                
                if(!client.getClientName().equals(this.clientName) && !client.getClientName().equals(opponentName)){
                    
                    outToClient = new DataOutputStream(client.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-removeList," + this.clientName + "\n");
                    outToClient.writeBytes("-removeList," + opponentName + "\n");
                    
                }
                
                
            }
            
            
            
        } catch (Exception ex){
            
            System.out.println(ex.getMessage());
            
        }
        
    }
    
    private void acceptToPlayWithOpponent(){
                
        try{
            
            DataOutputStream outToClient = new DataOutputStream(this.opponent.getConnectionSocket().getOutputStream());
            outToClient.writeBytes("-Accepted\n");
            this.isAvailable = false;  
            
        } catch (Exception ex){
            
            System.out.println(ex.getMessage());
            
        }
        
    }
    
    private void declineToPlaywithOpponent(){
                
        try{
            
            DataOutputStream outToClient = new DataOutputStream(this.opponent.getConnectionSocket().getOutputStream());
            outToClient.writeBytes("-Declined\n"); 
            this.opponent.setAvailable(true);
            
            for(int i = 0; i < clients.size(); i++){
                
                ClientThread client = clients.get(i);
                String opponentName = this.opponent.getClientName();
                
                if(!client.getClientName().equals(this.clientName) && !client.getClientName().equals(opponentName)){
                    
                    outToClient = new DataOutputStream(client.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("addList," + this.clientName + "\n");
                    outToClient.writeBytes("addList," + opponentName + "\n");
                    
                }
                
            }
            
            this.opponent.getOpponent().setOpponent(new ClientThread());
            this.opponent.setOpponent(new ClientThread());
            
            
        } catch (Exception ex){
            
            System.out.println(ex.getMessage());
            
        }
        
    }
    
    private void stopPlayingWithOpponent(){
        
        try{
            
            DataOutputStream outToClient;
            
            for(int i = 0; i < clients.size(); i++){
                
                ClientThread client = clients.get(i);
                
                if(client.getClientName().equals(this.clientName)){
                    
                    outToClient = new DataOutputStream(client.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-Stopped\n");
                    this.setAvailable(true);
                    
                } else if (client.getClientName().equals(this.opponent.getClientName())){
                    
                    outToClient = new DataOutputStream(client.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-Stopped\n");
                    this.opponent.setAvailable(true);
                    
                } else {
                    
                    outToClient = new DataOutputStream(client.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-addList," + this.clientName + "\n");
                    outToClient.writeBytes("-addList," + this.opponent.getClientName() + "\n");
                    
                }
                
            }
            
            
            
        } catch (Exception ex){
            
            System.out.println(ex.getMessage());
            
        }
        
    }
    
    private void calculateGameResult(){
        
        try{
            
            DataOutputStream outToClient;
           
            System.out.println("{ " + clientName + ", " + playerMove + " }");
            
            String opponentPlayerMove = this.opponent.getPlayerMove();
            
            if(!this.playerMove.isEmpty() && !opponentPlayerMove.isEmpty()){
                
                if( GameLogic.playerMove(this.playerMove).winsAgainst(opponentPlayerMove) ){
                    
                    outToClient = new DataOutputStream(this.connectionSocket.getOutputStream());
                    outToClient.writeBytes("-Result,win," + opponent.getClientName() + "\n");
                    outToClient = new DataOutputStream(this.opponent.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-Result,lose,"+ this.clientName + "\n");
                    
                } else if( GameLogic.playerMove(this.playerMove).losesTo(opponentPlayerMove) ){
                    
                    outToClient = new DataOutputStream(this.connectionSocket.getOutputStream());
                    outToClient.writeBytes("-Result,lose," + opponent.getClientName() + "\n");
                    outToClient = new DataOutputStream(this.opponent.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-Result,win," + this.clientName + "\n");
                    
                    
                } else {
                    
                    outToClient = new DataOutputStream(this.connectionSocket.getOutputStream());
                    outToClient.writeBytes("-Result,tie," + opponent.getClientName() + "\n");
                    outToClient = new DataOutputStream(this.opponent.getConnectionSocket().getOutputStream());
                    outToClient.writeBytes("-Result,tie," + this.clientName + "\n");
                    
                }
                
                this.resetPlayerMove();
                this.opponent.resetPlayerMove();
                
            }

            
            
        } catch (Exception ex){
            
            System.out.println(ex.getMessage());
            
        }
            
    }
    
    @Override
    public void run(){
        
        try{
            
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            String clientSentence;
            
            while(true){
                
                clientSentence = inFromClient.readLine();
                
                if(clientSentence.startsWith("-Remove")){
                    
                    this.removeClient();
                    
                } else if (clientSentence.startsWith("-Request")){
                    
                    String[] splitSentence = clientSentence.split(",");
                    String opponentName = splitSentence[1];
                    this.requestToPlayWithOpponent(opponentName);
                         
                } else if (clientSentence.startsWith("-Accepted")){
                    
                    this.acceptToPlayWithOpponent();
                        
                } else if (clientSentence.startsWith("-Declined")){
                    
                    this.declineToPlaywithOpponent();
                    
                } else if (clientSentence.startsWith("-Stop")){
                    
                    this.stopPlayingWithOpponent();
                    
                } else if (clientSentence.startsWith("-Move")){
                    
                    String[] splitSentence = clientSentence.split(",");
                    this.playerMove = splitSentence[1];
                    this.calculateGameResult();
                    
                }
                
                //next if statement
                
            }
            
        } catch(IOException ex){
            
            System.out.println(ex.getMessage());
            
        }
        
    }
    
}
