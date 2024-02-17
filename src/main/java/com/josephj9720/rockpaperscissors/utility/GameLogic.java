/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.josephj9720.rockpaperscissors.utility;

/**
 *
 * @author josep
 */
public class GameLogic {
    
    public static final String ROCK = "rock";
    public static final String PAPER = "paper";
    public static final String SCISSORS = "scissors";
    
    public static Weapon playerMove(String playerMove){
        
        switch (playerMove) {
            case ROCK:
                return new Rock();
            case PAPER:
                return new Paper();
            default:
                return new Scissors();
        }
        
    }
    
    //using static keyword for interface doesn't change behavior
    //as you cannot instantiate an interface
    public interface Weapon {
        
        public boolean winsAgainst(String opponentPlayerMove);
        public boolean losesTo(String opponenPlayerMove);
        
    }
    
    
    public static class Rock implements Weapon{
        
        @Override
        public boolean winsAgainst(String opponentPlayerMove){
            return SCISSORS.equals(opponentPlayerMove);
        }

        @Override
        public boolean losesTo(String opponentPlayerMove){
            return PAPER.equals(opponentPlayerMove);
        }
        
    }
    
    public static class Paper implements Weapon{
        
        @Override
        public boolean winsAgainst(String opponentPlayerMove){
            return ROCK.equals(opponentPlayerMove);
        }

        @Override
        public boolean losesTo(String opponentPlayerMove){
            return SCISSORS.equals(opponentPlayerMove);
        }
        
    }
    
    public static class Scissors implements Weapon{
        
        @Override
        public boolean winsAgainst(String opponentPlayerMove){
            return PAPER.equals(opponentPlayerMove);
        }

        @Override
        public boolean losesTo(String opponentPlayerMove){
            return ROCK.equals(opponentPlayerMove);
        }
        
    }
    
}
