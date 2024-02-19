/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.Controller;

import com.josephj9720.rockpaperscissors.Model.ClientModel;
import com.josephj9720.rockpaperscissors.View.ClientView;
import com.josephj9720.rockpaperscissors.utility.GameHistoryRecord;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josep
 */
public class ClientController {
    private Socket clientSocket;
    private ClientView clientView;
    private ClientModel clientModel;
    
    public ClientController(ClientView clientView, ClientModel clientModel){
        
        this.clientView = clientView;
        this.clientModel = clientModel;
        
        this.clientView.attachConnectionListener(new ConnectionListener());
        this.clientView.attachPlayRequestListener(new PlayRequestListener());
        this.clientView.attachAcceptListener(new AcceptListener());
        this.clientView.attachDeclineListener(new DeclineListener());
        this.clientView.attachGameHistoryListener(new GameHistoryListener());
        this.clientView.attachRockListener(new RockListener());
        this.clientView.attachPaperListener(new PaperListener());
        this.clientView.attachScissorsListener(new ScissorsListener());
        this.clientView.attachClientWindowClosedListener(new ClosingClientWindowListener());
        
    }
    
    public class ConnectionListener implements ActionListener{
       
        @Override
        public void actionPerformed(ActionEvent e) {
            if(clientView.isClientNameValid()){
                
                try{
                    
                    if(clientView.getConnectDisconnectButtonLabel().equalsIgnoreCase("connect")){
                        
                        clientSocket = new Socket("localhost", 6789);
                        StartThread();
                        
                        clientModel.setName(clientView.getClientName());
                        
                        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                        outToServer.writeBytes("-Name," + clientView.getClientName() + "\n");
                       
                    } else {
                        
                        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                        outToServer.writeBytes("-Remove\n");
                        
                        clientView.toggleConnectButton();
                        
                    }
                    
                } catch(IOException ex) {
                    
                    System.out.println(ex.getMessage());
                    
                }
                
            }
            
        }

    }

    private class PlayRequestListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                
                if(clientView.getPlayStopButtonLabel().equalsIgnoreCase("play")){

                    if(clientView.isPlayerSelected()){
                        
                        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

                        clientView.togglePlayButton();
                        clientView.disablePlayersList();

                        String playerName = clientView.getPlayersListSelection();
                        String sendingSentence = "-Request," + playerName + ",\n";
                        outToServer.writeBytes(sendingSentence);
                        clientView.displayInfo("Requesting to play with " + playerName + ".\n");

                    } 
                    
                } else {
                    
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    String sentindSentence = "-Stop\n";
                    outToServer.writeBytes(sentindSentence);
                    
                }
                
            } catch(IOException ex){
                
                System.out.println(ex.getMessage());
                
            } 
            
        }

    }

    private class AcceptListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                clientView.hideAcceptDeclineButtons();
                String sendingSentence = "-Accepted\n";
                outToServer.writeBytes(sendingSentence);
                clientView.showRockPaperScissorsButtons();
                clientView.clearInfo();
                
            } catch(IOException ex){
                
                System.out.println(ex.getMessage());
                
            }
            
        }

    }

    private class DeclineListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                clientView.togglePlayButton();
                clientView.hideAcceptDeclineButtons();
                clientView.displayInfo("You declined the request. Game not started.");
                clientView.resetPlayersListSelection();
                clientView.enablePlayersList();
                String sendingSentence = "-Declined\n";
                outToServer.writeBytes(sendingSentence);
                clientView.clearInfo();
                
            } catch(IOException ex){
                
                System.out.println(ex.getMessage());
                
            }
        }

    }

    private class GameHistoryListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            clientView.createGameHistoryFrame(clientModel.getGameHistory());
            
            clientView.attachGameHistoryWindowClosedListener(new ClosingGameHistoryWindowListener());
            clientView.disableGameHistoryButton();
            
        }

    }

    private class RockListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                String sendingSentence = "-Move,rock\n";
                outToServer.writeBytes(sendingSentence);
                clientView.disableRockPaperScissorsButtons();
                clientView.clearInfo();
                
            } catch(IOException ex) {
                
                System.out.println(ex.getMessage());
                
            }
            
        }

    }

    private class PaperListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                String sendingSentence = "-Move,paper\n";
                outToServer.writeBytes(sendingSentence);
                clientView.disableRockPaperScissorsButtons();
                clientView.clearInfo();
                
            } catch(IOException ex){
                
                System.out.println(ex.getMessage());
                
            }
            
        }

    }

    private class ScissorsListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try{
                
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                String sendingSentence = "-Move,scissors\n";
                outToServer.writeBytes(sendingSentence);
                clientView.disableRockPaperScissorsButtons();
                clientView.clearInfo();
                
            } catch(IOException ex){
                
                System.out.println(ex.getMessage());
                
            }
            
        }

    }
    
    private class ClosingClientWindowListener extends WindowAdapter{
        
        @Override
        public void windowClosing(WindowEvent we){
            
            try{
                
                if(clientSocket == null) {
                    
                    System.exit(0);
                    
                } else {
                    
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    outToServer.writeBytes("-Remove\n");

                    clientView.toggleConnectButton();

                    System.exit(0);
                    
                }
                
                
            } catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
        
    }
    
    private class ClosingGameHistoryWindowListener extends WindowAdapter{
        
        @Override
        public void windowClosing(WindowEvent we){
            
            clientView.enableGameHistoryButton();
            
        }
            
        
    }
    
    private void StartThread(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                
                try {
                    
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String receivedSentence;
                    //problems are mainly when disconnecting
                    while(true){
                        
                        receivedSentence = inFromServer.readLine();
                        if(receivedSentence == null){
                            continue;
                        }
                        System.out.println("Here is what the client receives: " + receivedSentence.split(",")[0]);
                        
                        if(receivedSentence.startsWith("-Connected")){
                            
                            clientView.toggleConnectButton();
                            clientView.disableClientNameField();
                            
                        } else if (receivedSentence.startsWith("-Taken")){
                            
                            clientView.displayInfo("This name is already taken. Please use another one!");
                            
                        } else if (receivedSentence.startsWith("-addList")){
                            
                            String[] splitSentence = receivedSentence.split(",");
                            String playerName = splitSentence[1];
                            clientView.addToPlayerList(playerName);
                            
                        } else if (receivedSentence.startsWith("-removeList")){
                            
                            String[] splitSentence = receivedSentence.split(",");
                            String playerName = splitSentence[1];
                            clientView.removeFromPlayersList(playerName);
                            
                        } else if (receivedSentence.startsWith("-Result")){
                            
                            String[] splitSentence = receivedSentence.split(",");
                            String result = splitSentence[1];
                            String opponentName = splitSentence[2];
                            
                            System.out.println("Client side result received: " + "{ " + clientModel.getName() + ", " + result + " }");
                            
                            clientView.setResultBox(result);
                            clientModel.addGameHistoryRecord(new GameHistoryRecord(opponentName, result));
                            
                        } else if (receivedSentence.startsWith("-Accepted")){
                            
                            clientView.displayInfo("Request Accepted.");
                            clientView.disablePlayersList();
                            clientView.showRockPaperScissorsButtons();
                            clientView.hideAcceptDeclineButtons();
                            
                        } else if (receivedSentence.startsWith("-Declined")){
                            
                            clientView.displayInfo("Request declined. Please select another player!");
                            clientView.togglePlayButton();
                            clientView.resetPlayersListSelection();
                            clientView.enablePlayersList();
                            clientView.hideAcceptDeclineButtons();;
                            
                        } else if (receivedSentence.startsWith("-Request")){
                            
                            String[] splitSentence = receivedSentence.split(",");
                            String playerName = splitSentence[1];
                            clientView.setPlayersListSelection(playerName);
                            clientView.displayInfo(playerName + " wants to play! Do you accept?");
                            clientView.disablePlayersList();
                            clientView.showAcceptDeclineButtons();
                            clientView.togglePlayButton();
                            
                        } else if (receivedSentence.startsWith("-Stopped")){
                            
                            clientView.hideResultBox();
                            clientView.resetPlayersListSelection();
                            clientView.enablePlayersList();
                            clientView.togglePlayButton();
                            clientView.hideAcceptDeclineButtons();
                            clientView.displayInfo("The game has stopped.");
                            clientView.hideRockPaperScissorsButtons();
                            
                        }
                        
                    }
                    
                    
                } catch (IOException | InterruptedException ex) {
                    
                    System.out.println(ex.getMessage());
                    
                }
                
            }
            
        }).start();
    }
}
