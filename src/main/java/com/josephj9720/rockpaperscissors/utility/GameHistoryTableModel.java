/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josep
 */
public class GameHistoryTableModel extends DefaultTableModel{
   
    
    public void addRow(GameHistoryRecord gameHistoryRecord){
        
        String[] data = {gameHistoryRecord.getOponent(), gameHistoryRecord.getGameResult()};
        addRow(data);
    }
    
    public void addRows(List<GameHistoryRecord> gameHistory){
        
        for(int i = 0; i < gameHistory.size(); i++){
            
            addRow(gameHistory.get(i));
            
        }
        
    }
          
}
