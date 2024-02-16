/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.utility;

/**
 *
 * @author josep
 */
public class GameHistoryRecord {
    private String opponent;
    private String gameResult;
    
    GameHistoryRecord(String oponent, String gameResult){
        this.opponent = oponent;
        this.gameResult = gameResult;
    }
    
    public void setOponent(String oponent){
        this.opponent = oponent;
    }
    
    public String getOponent(){
        return this.opponent;
    }
    
    public void setGameResult(String gameResult){
        this.gameResult = gameResult;
    }
    
}
