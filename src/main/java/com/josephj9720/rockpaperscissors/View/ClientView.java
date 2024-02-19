/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.View;

import com.josephj9720.rockpaperscissors.utility.CellSelectionRenderer;
import com.josephj9720.rockpaperscissors.utility.GameHistoryRecord;
import com.josephj9720.rockpaperscissors.utility.GameHistoryTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author josep
 */
public class ClientView {
    private JFrame frame;
    private JFrame gameHistoryFrame;
    private ClassLoader classLoader;
    private JLabel clientNameLabel;
    private JTextField clientNameField;
    private JLabel playWithLabel;
    private JComboBox playersList;
    private JTextArea infoBox;
    private JTextPane resultBox;
    private JTable gameHistoryTable;
    private JScrollPane gameHistoryScrollPane;
    private JButton connectButton;
    private JButton playButton;
    private JButton acceptButton;
    private JButton declineButton;
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton gameHistoryButton;
    private Icon backgroundIcon;
    private Icon darkBackgroundIcon;
    private Icon rockIcon;
    private Icon darkRockIcon;
    private Icon paperIcon;
    private Icon darkPaperIcon;
    private Icon scissorsIcon;
    private Icon darkScissorsIcon;
    private Icon gameHistoryIcon;
    private Icon darkGameHistoryIcon;
    
    public ClientView(){
        setupView();
    }
    
    /**
     *
     */
    private void setupView(){
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
        connectButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        connectButton.setHorizontalTextPosition(JButton.CENTER);
        connectButton.setVerticalTextPosition(JButton.CENTER);
        connectButton.setBounds(300, 25, 100, 30);
        int connectButtonOffset = connectButton.getInsets().left;
        connectButton.setIcon(resizeIcon((ImageIcon) backgroundIcon, 100 - connectButtonOffset, 30 - connectButtonOffset));
        connectButton.setRolloverIcon(resizeIcon((ImageIcon) darkBackgroundIcon, 100 - connectButtonOffset, 30 - connectButtonOffset));
        frame.getContentPane().add(connectButton);
        connectButton.setVisible(true);
        
        playButton = new JButton("Play");
        playButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        playButton.setHorizontalTextPosition(JButton.CENTER);
        playButton.setVerticalTextPosition(JButton.CENTER);
        playButton.setBounds(300, 85, 100, 30);
        int playButtonOffset = playButton.getInsets().left;
        playButton.setIcon(resizeIcon((ImageIcon) backgroundIcon, 100 - playButtonOffset, 30 - playButtonOffset));
        playButton.setRolloverIcon(resizeIcon((ImageIcon) darkBackgroundIcon, 100 - playButtonOffset, 30 - playButtonOffset));
        frame.getContentPane().add(playButton);
        playButton.setVisible(false);
        
        gameHistoryIcon = new ImageIcon(classLoader.getResource("history-line-icon.png"));
        darkGameHistoryIcon = new ImageIcon(classLoader.getResource("darkhistory-line-icon.png"));
        gameHistoryButton = new JButton();
        gameHistoryButton.setBackground(Color.decode("#e8f6ff"));
        gameHistoryButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        gameHistoryButton.setBounds(410, 25, 30, 30);
        int gameHistoryButtonOffset = gameHistoryButton.getInsets().left;
        gameHistoryButton.setIcon(resizeIcon((ImageIcon) gameHistoryIcon, 30 - gameHistoryButtonOffset, 30 - gameHistoryButtonOffset));
        gameHistoryButton.setRolloverIcon(resizeIcon((ImageIcon) darkGameHistoryIcon, 30 - gameHistoryButtonOffset, 30 - gameHistoryButtonOffset));
        frame.getContentPane().add(gameHistoryButton);
        gameHistoryButton.setVisible(false);
        
        acceptButton = new JButton("Yes");
        acceptButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        acceptButton.setHorizontalTextPosition(JButton.CENTER);
        acceptButton.setVerticalTextPosition(JButton.CENTER);
        acceptButton.setBounds(355, 497, 60, 30);
        int acceptButtonOffset = acceptButton.getInsets().left;
        acceptButton.setIcon(resizeIcon((ImageIcon) backgroundIcon, 60 - acceptButtonOffset, 30 - acceptButtonOffset));
        acceptButton.setRolloverIcon(resizeIcon((ImageIcon) darkBackgroundIcon, 60 - acceptButtonOffset, 30 - acceptButtonOffset));
        frame.getContentPane().add(acceptButton);
        acceptButton.setVisible(false);
        
        declineButton = new JButton("No");
        declineButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, false));
        declineButton.setHorizontalTextPosition(JButton.CENTER);
        declineButton.setVerticalTextPosition(JButton.CENTER);
        declineButton.setBounds(415, 497, 60, 30);
        int declineButtonOffset = declineButton.getInsets().left;
        declineButton.setIcon(resizeIcon((ImageIcon) backgroundIcon, 60 - declineButtonOffset, 30 - declineButtonOffset));
        declineButton.setRolloverIcon(resizeIcon((ImageIcon) darkBackgroundIcon, 60 - declineButtonOffset, 30 - declineButtonOffset));
        frame.getContentPane().add(declineButton);
        declineButton.setVisible(false);
        
        rockIcon = new ImageIcon(classLoader.getResource("lightblue-rock-icon.png"));
        rockButton = new JButton();
        darkRockIcon = new ImageIcon(classLoader.getResource("blue-rock-icon.png"));
        rockButton.setBackground(Color.BLUE);
        rockButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, false));
        rockButton.setBounds(45, 150, 100, 100);
        int rockButtonOffset = rockButton.getInsets().left;
        rockButton.setIcon(resizeIcon((ImageIcon) rockIcon, 100 - rockButtonOffset, 100 - rockButtonOffset));
        rockButton.setRolloverIcon(resizeIcon((ImageIcon) darkRockIcon, 100 - rockButtonOffset, 100 - rockButtonOffset));
        frame.getContentPane().add(rockButton);
        rockButton.setVisible(false);
        
        paperIcon = new ImageIcon(classLoader.getResource("lightblue-paper-icon.png"));
        paperButton = new JButton();
        darkPaperIcon = new ImageIcon(classLoader.getResource("blue-paper-icon.png"));
        paperButton.setBackground(Color.BLUE);
        paperButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, false));
        paperButton.setBounds(195, 150, 100, 100);
        int paperButtonOffset = paperButton.getInsets().left;
        paperButton.setIcon(resizeIcon((ImageIcon) paperIcon, 100 - paperButtonOffset, 100 - paperButtonOffset));
        paperButton.setRolloverIcon(resizeIcon((ImageIcon) darkPaperIcon, 100 - paperButtonOffset, 100 - paperButtonOffset));
        frame.getContentPane().add(paperButton);
        paperButton.setVisible(false);
        
        scissorsIcon = new ImageIcon(classLoader.getResource("lightblue-scissors-icon.png"));
        scissorsButton = new JButton();
        darkScissorsIcon = new ImageIcon(classLoader.getResource("blue-scissors-icon.png"));
        scissorsButton.setBackground(Color.BLUE);
        scissorsButton.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3, false));
        scissorsButton.setBounds(340, 150, 100, 100);
        int scissorsButtonOffset = scissorsButton.getInsets().left;
        scissorsButton.setIcon(resizeIcon((ImageIcon) scissorsIcon, 100 - scissorsButtonOffset, 100 - scissorsButtonOffset));
        scissorsButton.setRolloverIcon(resizeIcon((ImageIcon) darkScissorsIcon, 100 - scissorsButtonOffset, 100 - scissorsButtonOffset));
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
        playersList.addItem("Players Available");
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
    }
    
    /**
     *
     */
    public void resetView(){
        acceptButton.setVisible(false);
        declineButton.setVisible(false);
        playButton.setText("Play");
        playButton.setVisible(false);
        playWithLabel.setVisible(false);
        infoBox.setText("");
        infoBox.setVisible(false);
        resultBox.setText("");
        resultBox.setVisible(false);
        this.resetPlayersList();
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorsButton.setEnabled(true);
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissorsButton.setVisible(false);
        clientNameField.setText("");
        clientNameField.setEnabled(true);
        gameHistoryButton.setVisible(false);
        gameHistoryButton.setEnabled(true);
        connectButton.setText("Connect");
        
        if(gameHistoryFrame != null){
            gameHistoryFrame.dispose();
        }
    }
    
    public boolean isClientNameValid(){
        String clientName = clientNameField.getText();
        //regular expression ^ = start, . = any char, * = any # times, $ = end of line //, = or, - = up to
        return clientName.matches("^[A-Z].*$") || clientName.matches("^[a-z].*$");
            
    }
    
    public void showView(){
        frame.setVisible(true);
    }
    
    public String getClientName(){
        return clientNameField.getText();
    }
    
    public void disableClientNameField(){
        clientNameField.setEnabled(false);
    }
    
    public void enableClientNameField(){
        clientNameField.setEnabled(true);
    }
    
    public void displayInfo(String info){
        infoBox.setText(info);
    }
    
    public void clearInfo(){
        infoBox.setText("");
    }
    
    public void disablePlayersList(){
        playersList.setEnabled(false);
    }
    
    public void enablePlayersList(){
        playersList.setEnabled(true);
    }
    
    public void showPlayersList(){
        playersList.setVisible(true);
    }
    
    public void resetPlayersListSelection(){
        playersList.setSelectedItem("Players Available");
    }
    
    private void resetPlayersList(){
        playersList.removeAllItems();
        playersList.addItem("Players Available");
        playersList.setVisible(false);
        playersList.setEnabled(true);
    }
    
    public void setPlayersListSelection(String playerName){
        playersList.setSelectedItem(playerName);
    }
    
    public String getPlayersListSelection(){
        return (String) playersList.getSelectedItem();
    }
    
    public void addToPlayerList(String playerName){
        playersList.addItem(playerName);
    }
    
    public void removeFromPlayersList(String playerName){
        playersList.removeItem(playerName);
    }
    
    public boolean isPlayerSelected(){
        return playersList.getSelectedItem() != playersList.getItemAt(0) 
                && playersList.isEnabled();
    }
    
    public void showRockPaperScissorsButtons(){
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissorsButton.setVisible(true);
    }
    
    public void hideRockPaperScissorsButtons(){
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissorsButton.setVisible(false);
    }
    
    public void disableRockPaperScissorsButtons(){
        rockButton.setEnabled(false);
        paperButton.setEnabled(false);
        scissorsButton.setEnabled(false);
    }
    
    public void enableRockPaperScissorsButtons(){
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorsButton.setEnabled(true);
    }
    
    public void showAcceptDeclineButtons(){
        acceptButton.setVisible(true);
        declineButton.setVisible(true);
    }
    
    public void hideAcceptDeclineButtons(){
        acceptButton.setVisible(false);
        declineButton.setVisible(false);
    }
    
    public void showResultBox(){
        resultBox.setVisible(true);
    }
    
    public void hideResultBox(){
        resultBox.setVisible(false);
    }
    
    public void setResultBox(String result) throws InterruptedException{
        switch(result){
            case "win":
                displayWin();
                break;
            
            case "tie":
                displayTie();
                break;
            
            case "lose":
                displayLose();
                break;
                    
        }
    }
    
    private void displayWin() throws InterruptedException{
        
        this.hideRockPaperScissorsButtons();
        resultBox.setText("\nYOU WIN!");
        this.showResultBox();
        this.disablePlayConnectButtons();
        Random random = new Random();
        
        for(int i = 0; i < 20; i++){
            
            Color color = this.getColor(random.nextInt(7) + 1);
            resultBox.setForeground(color);
            resultBox.setBorder(BorderFactory.createLineBorder(color, 3, false));
            Thread.sleep(150);
              
        }
        
        this.hideResultBox();
        this.enablePlayConnectButtons();
        this.enableRockPaperScissorsButtons();
        this.showRockPaperScissorsButtons();
          
    }
    
    private void displayTie() throws InterruptedException{
        
        this.hideRockPaperScissorsButtons();
        resultBox.setText("\nIT IS A TIE!");
        this.showResultBox();
        this.disablePlayConnectButtons();
        
        for(int i = 0; i < 4; i++){
            
            Color color = (i % 2) == 0 ? this.getColor(9) : this.getColor(8);
            resultBox.setForeground(color);
            resultBox.setBorder(BorderFactory.createLineBorder(color, 3, false));
            Thread.sleep(750);
              
        }
        
        this.hideResultBox();
        this.enablePlayConnectButtons();
        this.enableRockPaperScissorsButtons();
        this.showRockPaperScissorsButtons();
    }
    
    private void displayLose() throws InterruptedException{
        
        this.hideRockPaperScissorsButtons();
        resultBox.setText("\nYOU LOSE!");
        this.showResultBox();
        this.disablePlayConnectButtons();
        
        for(int i = 0; i < 3; i++){
            
            Color color = (i % 2) == 0 ? this.getColor(2) : this.getColor(9) ;
            resultBox.setForeground(color);
            resultBox.setBorder(BorderFactory.createLineBorder(color, 3, false));
            Thread.sleep(1000);
              
        }
        
        this.hideResultBox();
        this.enablePlayConnectButtons();
        this.enableRockPaperScissorsButtons();
        this.showRockPaperScissorsButtons();
        
    }
    
    private Color getColor(int i){
        
        Color c = Color.WHITE;

        switch(i) {

            case 1:
                    c = Color.BLUE;
                    break;

            case 2:
                    c = Color.RED;
                    break;

            case 3:
                    c = Color.CYAN;
                    break;

            case 4:
                    c = Color.GREEN;
                    break;

            case 5:
                    c = Color.MAGENTA;
                    break;

            case 6:
                    c = Color.PINK;
                    break;

            case 7:
                    c = Color.ORANGE;
                    break;
                    
            case 8:
                    c = Color.YELLOW;
                    break;

            case 9:
                    c = Color.BLACK;
                    break;
        }


        return c;
    }
    
    public void disablePlayConnectButtons(){
        playButton.setEnabled(false);
        connectButton.setEnabled(false);
    }
    
    public void enablePlayConnectButtons(){
        playButton.setEnabled(true);
        connectButton.setEnabled(true);
    }
    
    
    public void toggleConnectButton(){
        if(connectButton.getText().equals("Connect")){
            
            connectButton.setText("Disconnect");
            playButton.setVisible(true);
            playWithLabel.setVisible(true);
            infoBox.setVisible(true);
            playersList.setVisible(true);
            gameHistoryButton.setVisible(true);
            
            
        } else {
            
            this.resetView();
            
        }
    }
    
    public void togglePlayButton(){
        if(playButton.getText().equals("Play")){
            
            playButton.setText("Stop");
            
        } else {
            
            playButton.setText("Play");
            
        }
    }
    
    public String getPlayStopButtonLabel(){
        return playButton.getText();
    }
    
    public String getConnectDisconnectButtonLabel(){
        return connectButton.getText();
    }
    
    public void disableGameHistoryButton(){
        gameHistoryButton.setEnabled(false);
    }
    
    public void enableGameHistoryButton(){
        gameHistoryButton.setEnabled(true);
    }
    
    public void createGameHistoryFrame(List<GameHistoryRecord> gameHistory){
        
        gameHistoryFrame = new JFrame("RockPaperScissors Client - Game History");
        gameHistoryFrame.setLayout(new BorderLayout());
        gameHistoryFrame.setBounds(frame.getX() + frame.getWidth() + 10, frame.getY(), 300, 400);
        gameHistoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        GameHistoryTableModel model = new GameHistoryTableModel();
        model.addColumn("Opponent");
        model.addColumn("Game Result");
        model.addRows(gameHistory);
        
        gameHistoryTable = new JTable(model);
        gameHistoryTable.setFillsViewportHeight(true);
        gameHistoryTable.setBackground(Color.decode("#65b5e6"));
        
        gameHistoryScrollPane = new JScrollPane(gameHistoryTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gameHistoryScrollPane.setBounds(0, 0, 300, 300);
        gameHistoryScrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#e8f6ff"), 10, false));
        gameHistoryFrame.getContentPane().add(gameHistoryScrollPane, BorderLayout.CENTER);
        
        gameHistoryFrame.setVisible(true);
        
    }
    
    public void addGameHistoryRecord(GameHistoryRecord gameHistoryRecord){
        GameHistoryTableModel model = (GameHistoryTableModel) gameHistoryTable.getModel();
        model.addRow(gameHistoryRecord);
    }
    
    public void attachConnectionListener(ActionListener listenerForConnectButton){
        connectButton.addActionListener(listenerForConnectButton);
    }
    
    public void attachPlayRequestListener(ActionListener listenerForPlayButton){
        playButton.addActionListener(listenerForPlayButton);
    }
    
    public void attachAcceptListener(ActionListener listenerForAcceptButton){
        acceptButton.addActionListener(listenerForAcceptButton);
    }
    
    public void attachDeclineListener(ActionListener listenerForDeclineButton){
        declineButton.addActionListener(listenerForDeclineButton);
    }
    
    public void attachRockListener(ActionListener listenerForRockButton){
        rockButton.addActionListener(listenerForRockButton);
    }
    
    public void attachPaperListener(ActionListener listenForPaperButton){
        paperButton.addActionListener(listenForPaperButton);
    }
    
    public void attachScissorsListener(ActionListener listenForScissorsButton){
        scissorsButton.addActionListener(listenForScissorsButton);
    }
    
    public void attachGameHistoryListener(ActionListener listenForGameHistoryButton){
        gameHistoryButton.addActionListener(listenForGameHistoryButton);
    }
    
    public void attachGameHistoryWindowClosedListener(WindowAdapter listenForClosingGameHistoryWindow){
        gameHistoryFrame.addWindowListener(listenForClosingGameHistoryWindow);
    }
    
    public void attachClientWindowClosedListener(WindowAdapter listenForClosingClientWindow){
        frame.addWindowListener(listenForClosingClientWindow);
    }
    
    private ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight){
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    
    
    
}
