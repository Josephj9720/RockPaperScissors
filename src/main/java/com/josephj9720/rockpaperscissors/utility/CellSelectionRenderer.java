/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.utility;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author josep
 */
public class CellSelectionRenderer extends DefaultListCellRenderer{
    
    private ListCellRenderer<Object> defaultRenderer;
    
    public CellSelectionRenderer(ListCellRenderer defaultRenderer){
        this.defaultRenderer = defaultRenderer;
    }
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus){
        
        Component component = defaultRenderer.getListCellRendererComponent(list, 
                value, index, isSelected, cellHasFocus);
        
        list.setSelectionBackground(Color.decode("#65b5e6"));
        
        if(component instanceof JLabel){
            
            if(isSelected){
                
                component.setBackground(Color.decode("#65b5e6"));
                
            } else {
                
                component.setBackground(Color.decode("#e8f6ff"));
                
            }
            
        } else {
            
            component.setBackground(Color.decode("#e8f6ff"));
            component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
        }
        
        return component;
        
    }
    
}
