/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.Model;

import com.josephj9720.rockpaperscissors.utility.GameHistoryRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josep
 */
public class ClientModel {
    private String name;
    private List<GameHistoryRecord> gameHistory;
    
    public ClientModel(){
        this.name = "";
        this.gameHistory = new ArrayList<>();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void addGameHistoryRecord(GameHistoryRecord record){
        this.gameHistory.add(record);
    }
    
    public int getTotalNumberOfWins(){
        return this.gameHistory.size();
    }
    
    public List<GameHistoryRecord> getGameHistory(){
        return this.gameHistory;
    }
    
}
