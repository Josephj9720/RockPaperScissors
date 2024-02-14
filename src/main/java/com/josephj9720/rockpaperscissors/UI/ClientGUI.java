/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.UI;

import com.josephj9720.rockpaperscissors.utility.CellSelectionRenderer;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author josep
 */
public class ClientGUI {
    private JFrame frame;
    private ClassLoader classLoader;
    private JLabel clientNameLabel;
    private JTextField clientNameField;
    private JLabel playWithLabel;
    private JComboBox playersList;
    private JTextArea infoBox;
    private JTextPane resultBox;
    private JButton connectButton;
    private JButton playButton;
    private JButton acceptButton;
    private JButton declineButton;
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private Icon backgroundIcon;
    private Icon darkBackgroundIcon;
    private Icon rockIcon;
    private Icon darkRockIcon;
    private Icon paperIcon;
    private Icon darkPaperIcon;
    private Icon scissorsIcon;
    private Icon darkScissorsIcon;
    
    /**
     *
     */
    public void setupUI(){
        frame = new JFrame("RockPaperScissors Client");
        frame.setLayout(null);
        frame.setBounds(100, 100, 500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#e8f6ff")); //change later
        
        clientNameLabel = new JLabel("Client Name");
        clientNameLabel.setBounds(20, 20, 80, 40);
        frame.getContentPane().add(clientNameLabel);
        clientNameLabel.setVisible(true);
        
        clientNameField = new JTextField();
        clientNameField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        clientNameField.setBounds(110, 25, 180, 30);
        frame.getContentPane().add(clientNameField);
        clientNameField.setVisible(true);
        
        classLoader = ClassLoader.getSystemClassLoader();
        
        backgroundIcon = new ImageIcon(classLoader.getResource("lightblue-Background.png"));
        connectButton = new JButton("Connect");
        darkBackgroundIcon = new ImageIcon(classLoader.getResource("blue-Background.png"));
        connectButton.setIcon(backgroundIcon);
        connectButton.setRolloverIcon(darkBackgroundIcon);
        connectButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        connectButton.setHorizontalTextPosition(JButton.CENTER);
        connectButton.setVerticalTextPosition(JButton.CENTER);
        connectButton.setBounds(300, 25, 100, 30);
        frame.getContentPane().add(connectButton);
        connectButton.setVisible(true);
        
        playButton = new JButton("Play");
        playButton.setIcon(backgroundIcon);
        playButton.setRolloverIcon(darkBackgroundIcon);
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);
        playButton.setBounds(300, 85, 100, 30);
        frame.getContentPane().add(playButton);
        playButton.setVisible(false);
        
        acceptButton = new JButton("Yes");
        acceptButton.setIcon(backgroundIcon);
        acceptButton.setRolloverIcon(darkBackgroundIcon);
        acceptButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        acceptButton.setHorizontalTextPosition(JButton.CENTER);
        acceptButton.setVerticalTextPosition(JButton.CENTER);
        acceptButton.setBounds(355, 497, 60, 30);
        frame.getContentPane().add(acceptButton);
        acceptButton.setVisible(false);
        
        declineButton = new JButton("No");
        declineButton.setIcon(backgroundIcon);
        declineButton.setRolloverIcon(darkBackgroundIcon);
        declineButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        declineButton.setHorizontalTextPosition(JButton.CENTER);
        declineButton.setVerticalTextPosition(JButton.CENTER);
        declineButton.setBounds(415, 497, 60, 30);
        frame.getContentPane().add(declineButton);
        declineButton.setVisible(false);
        
        rockIcon = new ImageIcon(classLoader.getResource("lightblue-rock-icon.png"));
        rockButton = new JButton(rockIcon);
        darkRockIcon = new ImageIcon(classLoader.getResource("blue-rock-icon.png"));
        rockButton.setRolloverIcon(darkRockIcon);
        rockButton.setBackground(Color.BLUE);
        rockButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, false));
        rockButton.setBounds(415, 497, 60, 30);
        frame.getContentPane().add(rockButton);
        rockButton.setVisible(false);
        
        paperIcon = new ImageIcon(classLoader.getResource("lightblue-paper-icon.png"));
        paperButton = new JButton(paperIcon);
        darkPaperIcon = new ImageIcon(classLoader.getResource("blue-paper-icon.png"));
        paperButton.setRolloverIcon(darkPaperIcon);
        paperButton.setBackground(Color.BLUE);
        paperButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, false));
        paperButton.setBounds(415, 497, 60, 30);
        frame.getContentPane().add(paperButton);
        paperButton.setVisible(false);
        
        scissorsIcon = new ImageIcon(classLoader.getResource("lightblue-scissors-icon.png"));
        scissorsButton = new JButton(scissorsIcon);
        darkScissorsIcon = new ImageIcon(classLoader.getResource("blue-scissors-icon.png"));
        scissorsButton.setRolloverIcon(darkScissorsIcon);
        scissorsButton.setBackground(Color.BLUE);
        scissorsButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, false));
        scissorsButton.setBounds(415, 497, 60, 30);
        frame.getContentPane().add(scissorsButton);
        scissorsButton.setVisible(false);
        
        playWithLabel = new JLabel("Play With:");
        playWithLabel.setBounds(20, 80, 80, 40);
        frame.getContentPane().add(playWithLabel);
        playWithLabel.setVisible(false);
        
        playersList = new JComboBox<String>();
        playersList.setRenderer(new CellSelectionRenderer(playersList.getRenderer()));
        playersList.setBackground(Color.decode("#e8f6ff"));
        playersList.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        playersList.setBounds(110, 85, 180, 30);
        frame.getContentPane().add(playersList);
        playersList.setVisible(false);
        
        infoBox = new JTextArea();
        infoBox.setBounds(20, 500, 440, 30);
        infoBox.setBackground(null);
        infoBox.setFont(new Font("PLAIN", Font.BOLD, 15));
        frame.getContentPane().add(infoBox);
        infoBox.setVisible(true);
        infoBox.setEnabled(false);
        
        resultBox = new JTextPane();
        resultBox.setBounds(20, 150, 445, 225);
        resultBox.setEditable(false);
        resultBox.setFont(new Font("PLAIN", Font.BOLD, 50));
        frame.getContentPane().add(resultBox);
        resultBox.setVisible(false);
        
        StyledDocument document = resultBox.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        document.setParagraphAttributes(0, document.getLength(), center, false);
        
        //now setup buttons
        
        frame.setVisible(true);   
    }
    
    /**
     *
     */
    public void resetUI(){
        
    }
    
}
