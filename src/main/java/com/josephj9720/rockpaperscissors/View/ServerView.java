/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.View;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author josep
 */
public class ServerView {
    
       private JFrame frame;
       private JLabel connectionStatusLabel;
       
    public ServerView(){
        setupView();
    }
    
    private void setupView(){
        frame = new JFrame("RockPaperScissors Server");
        frame.setLayout(null);
        frame.setBounds(100, 100, 300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#e8f6ff"));
        
        connectionStatusLabel = new JLabel("No Clients Connected");
        connectionStatusLabel.setBounds(80, 30, 200, 30);
        connectionStatusLabel.setForeground(Color.red);
        frame.getContentPane().add(connectionStatusLabel);
    }
    
    public void showView(){
        frame.setVisible(true);
    }
    
    public void setConnectionStatusLabel(String status, Color color){
        connectionStatusLabel.setText(status);
        connectionStatusLabel.setForeground(color);
    }
    
}
