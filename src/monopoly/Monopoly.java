/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package monopoly;

/**
 *
 * @author llujan2027
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
public class Monopoly {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

class Player {
    private String playerName;
    private int playerCash;
    private ArrayList<Property> playerProps = new ArrayList<>();
    private boolean jailFree;
    private int playerJailed;
    private boolean playerBroke;
    private boolean computerPlayer;
    private int currentSpace;
    
    public Player(String name, boolean computer, boolean active){
        playerName = name;
        playerCash = 1000;
        jailFree = false;
        playerJailed = 0;
        playerBroke = active;
        computerPlayer = computer;
        currentSpace = 0;
    }
    
    public void playerTurn(){
        
    }
    
    public void roll(){
        
    }
    
    public void rollJail(){
        
    }
    
    public String getName(){
        return playerName;
    }
    
    public int getCash(){
        return playerCash;
    }
    
    public ArrayList<Property> getProps(){
        return playerProps;
    }
    
    public void setName(String name){
        playerName = name;
    }
    
    public void changeCash(int cash){
        playerCash = playerCash + cash;
    }
    
    public void changeProps(String property, String addOrTake){
        
    }
}

class Property {
    int baseRent;
    int house1Rent;
    int house2Rent;
    int house3Rent;
    int house4Rent;
    int hotelRent;
    int houseCost;
    int boardPosition;
    String propertyName;
    String propertySet;
    int buildings;
    boolean completeSet;
    boolean mortgaged;
    int mortgageValue;
    boolean owned;
    int whoOwns;
    
    public Property(int rent, int position, String name, String set, int mortgage,
            int house1, int house2, int house3, int house4, int hotel, int building){
        baseRent = rent;
        house1Rent = house1;
        house2Rent = house2;
        house3Rent = house3;
        house4Rent = house4;
        hotelRent = hotel;
        houseCost = building;
        boardPosition = position;
        propertyName = name;
        propertySet = set;
        buildings = 0;
        completeSet = false;
        mortgaged = false;
        mortgageValue = mortgage;
        owned = false;
        whoOwns = 0;
    }
    
    
}