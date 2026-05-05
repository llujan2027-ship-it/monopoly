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
        //sets the file path
        String propertiesPath = "Decks/Properties.csv";
        String chestPath = "Decks/CommunityChest.csv";
        String chancePath = "Decks/Chance.csv";
        //keeps the program running
        boolean run = true;
        //Scanner
        Scanner scan = new Scanner(System.in);
        //Where all contacts are kept
        ArrayList<Property> propertyList = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(propertiesPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Create a contact
                    Object[] tempArray = line.split(",");
                if(!tempArray[0].equals("Property Name")){
                    ArrayList<Object> toProp = new ArrayList<>(Arrays.asList(tempArray));
                    Property prop = new Property(toProp);
                    //Add to list
                    propertyList.add(prop);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: "
            + e.getMessage());
        }
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
    private int playerNumber;
    
    public Player(String name, boolean computer, boolean active, int number){
        this.playerName = name;
        this.playerCash = 1000;
        this.jailFree = false;
        this.playerJailed = 0;
        this.playerBroke = active;
        this.computerPlayer = computer;
        this.currentSpace = 0;
        this.playerNumber = number;
    }
    
    public void playerTurn(){
        System.out.println(this.getName() + "'s Turn!");
        //if(this.)
        //- IF NOT -
        // Wait for player prompt
        // Roll dice
        // Check space
        //  - UNOWNED PROPERTY -
        //   Ask player to buy
        //  - IF BOUGHT -
        //   Subtract cash from player, add property to playerProps
        //   Original property has owned set to true, and whoOwns set to playerNumber
        //  - IF NOT BOUGHT -
        //   
    }
    
    public void roll(){
        Die d1 = new Die();
        Die d2 = new Die();
        boolean doublesTrue = false;
        int doublesCounter = 0;
        do{
            
            int d1Roll = d1.roll();
            int d2Roll = d2.roll();
            int total = d1Roll + d2Roll;
            doublesTrue = (d1Roll == d2Roll);
            if(doublesTrue == true){
                doublesCounter++;
            }
            if(doublesCounter < 3){
                for(int i = 0; i < total; i++){
                    if(this.getSpace() == 39){
                        this.currentSpace = 0;
                        this.playerCash = this.playerCash + 200;
                        System.out.println("You passed Go! Collect $200.\nTotal cash: $"+this.getCash());
                    }else if(this.getSpace() < 39){
                        this.currentSpace++;
                    }
                }
            }else if(doublesCounter == 3){
                this.playerJailed = 3;
            }
        }while(doublesTrue == true);
        
    }
    
    public void rollJail(){
        Die d1 = new Die();
        Die d2 = new Die();
        int d1Roll = d1.roll();
        int d2Roll = d2.roll();
        if(d1Roll == d2Roll){
            this.playerJailed = 0;
            
        } else if(this.getJailed() > 1){
            playerJailed--;
        } else {
            if(this.checkJailFree()){
                //Ask to use JailFree
            }
        }
        if(this.getJailed() == 0){
            int total = d1Roll + d2Roll;
            for(int i = 0; i < total; i++){
                if(this.getSpace() == 39){
                    this.currentSpace = 0;
                    this.playerCash = this.playerCash + 200;
                }else if(this.getSpace() < 39){
                    this.currentSpace++;
                }
            }
        }
    }
    
    public String getName(){
        return this.playerName;
    }
    
    public int getCash(){
        return this.playerCash;
    }
    
    public int getJailed(){
        return this.playerJailed;
    }
    
    public int getSpace(){
        return this.currentSpace;
    }
    
    public int getNumber(){
        return this.playerNumber;
    }
    
    public boolean checkBroke(){
        return this.playerBroke;
    }
    
    public boolean checkJailFree(){
        return this.jailFree;
    }
    
    public boolean checkComputer(){
        return this.computerPlayer;
    }
    
    public ArrayList<Property> getProps(){
        return this.playerProps;
    }
    
    public void setName(String name){
        this.playerName = name;
    }
    
    public void changeCash(int cash){
        this.playerCash = this.playerCash + cash;
    }
    
    public void addProps(int propertySpace){
        //Search for property in propertyList via position
        //Add copy of property to player's PropertyList
        //Print confirmation message
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
    int propertyCost;
    int boardPosition;
    String propertyName;
    String propertySet;
    int buildings;
    boolean mortgaged;
    int mortgageValue;
    boolean owned;
    int whoOwns;
    
    public Property(ArrayList<Object> toProp){
        this.propertyName = toProp.get(0).toString();
        this.boardPosition = Integer.parseInt(toProp.get(1).toString());
        this.propertySet = toProp.get(2).toString();
        this.propertyCost = Integer.parseInt(toProp.get(3).toString());
        this.baseRent = Integer.parseInt(toProp.get(4).toString());
        this.house1Rent = Integer.parseInt(toProp.get(5).toString());
        this.house2Rent = Integer.parseInt(toProp.get(6).toString());
        this.house3Rent = Integer.parseInt(toProp.get(7).toString());
        this.house4Rent = Integer.parseInt(toProp.get(8).toString());
        this.hotelRent = Integer.parseInt(toProp.get(9).toString());
        this.houseCost = Integer.parseInt(toProp.get(10).toString());
        this.mortgageValue = Integer.parseInt(toProp.get(11).toString());
        this.buildings = 0;
        this.mortgaged = false;
        this.owned = false;
        this.whoOwns = 0;
    }
    
    public int calculateRent(Player owner){
        int finalRent = 0;
        int setCounter = 0;
        if(this.checkMortgaged() == false){
            finalRent = this.getRent();
            ArrayList<Property> tempProps = owner.getProps();
            switch(this.getSet()){
                case "Railroad" -> {
                    for(int i = 0; i < tempProps.size(); i++){
                        String tempSet = tempProps.get(i).getSet();
                        if(tempSet.equals("Railroad")){
                            setCounter++;
                        }
                    }
                    int railRent = 0;
                    for(int j = 0; j < setCounter; j++){
                        railRent = railRent + 25;
                    }
                    finalRent = railRent;
                }
                case "Utility" -> {
                    for(int i = 0; i < tempProps.size(); i++){
                        String tempSet = tempProps.get(i).getSet();
                        if(tempSet.equals("Utility")){
                            setCounter++;
                        }
                    }
                    Die d1 = new Die();
                    Die d2 = new Die();
                    int roll1, roll2;
                    // Wait for player prompt
                    roll1 = d1.roll();
                    roll2 = d2.roll();
                    int initialRoll = roll1 + roll2;
                    int finalRoll;
                    switch(setCounter){
                        case 1 -> {
                            finalRoll = initialRoll * 4;
                            finalRent = finalRoll;
                        }
                        case 2 -> {
                            finalRoll = initialRoll * 10;
                            finalRent = finalRoll;
                        }
                    }
                }
                default -> {
                    boolean completeSet = false;
                    String targetSet = this.getSet();
                    for(int i = 0; i < tempProps.size(); i++){
                        String tempSet = tempProps.get(i).getSet();
                        if(tempSet.equals(targetSet)){
                            setCounter++;
                        }
                    }
                    switch(targetSet){
                        case "Brown" -> {
                            if(setCounter == 2){
                                completeSet = true;
                            }
                        }
                        case "Dark Blue" -> {
                            if(setCounter == 2){
                                completeSet = true;
                            }
                        }
                        default -> {
                            if(setCounter == 3){
                                completeSet = true;
                            }
                        }
                    }
                    if(completeSet == true){
                        int tempRent = this.getRent() * 2;
                        switch(this.getBuildings()){
                            case 1 -> {
                                finalRent = this.getHouse1Rent();
                            }
                            case 2 -> {
                                finalRent = this.getHouse2Rent();
                            }
                            case 3 -> {
                                finalRent = this.getHouse3Rent();
                            }
                            case 4 -> {
                                finalRent = this.getHouse4Rent();
                            }
                            case 5 -> {
                                finalRent = this.getHotelRent();
                            }
                            default -> {
                                finalRent = tempRent;
                            }
                        }
                    }
                }
            }
            System.out.println("You musy pay $" + finalRent
                + " to " + owner.getName() + "!");
        }
        return finalRent;
    }
    
    public String getName(){
        return this.propertyName;
    }
    
    public int getRent(){
        return this.baseRent;
    }
    
    public int getHouse1Rent(){
        return this.house1Rent;
    }
    
    public int getHouse2Rent(){
        return this.house2Rent;
    }
    
    public int getHouse3Rent(){
        return this.house3Rent;
    }
    
    public int getHouse4Rent(){
        return this.house4Rent;
    }
    
    public int getHotelRent(){
        return this.hotelRent;
    }
    
    public int getCost(){
        return this.propertyCost;
    }
    
    public int getHouseCost(){
        return this.houseCost;
    }
    
    public int getPosition(){
        return this.boardPosition;
    }
    
    public String getSet(){
        return this.propertySet;
    }
    
    public int getBuildings(){
        return this.buildings;
    }
    
    public boolean checkMortgaged(){
        return this.mortgaged;
    }
    
    public int getMortgageValue(){
        return this.mortgageValue;
    }
    
    public int getWhoOwns(){
        return this.whoOwns;
    }
    
    public boolean checkOwned(){
        return this.owned;
    }
    
    @Override
    public String toString() {
        String returnString = this.getName();
        return returnString;
    }
}

class Die {
    private final int MAX = 6; // maximum face value
    private int faceValue; // current value showing on the die
    //-----------------------------------------------------------------
    // Constructor: Sets the initial face value.
    //-----------------------------------------------------------------
    public Die() {
        faceValue = 1;
    }
    //-----------------------------------------------------------------
    // Rolls the die and returns the result.
    //-----------------------------------------------------------------
    public int roll() {
        faceValue = (int) (Math.random() * MAX) + 1;
        return faceValue;
    }
    //-----------------------------------------------------------------
    // Face value mutator.
    //-----------------------------------------------------------------
    public void setFaceValue(int value) {
        faceValue = value;
    }
    //-----------------------------------------------------------------
    // Face value accessor.
    //-----------------------------------------------------------------
    public int getFaceValue() {
        return faceValue;
    }
    //------------------------------------------------------------
    // Returns a string representation of this die.
    //------------------------------------------------------------
    @Override
    public String toString() {
    this.roll();
    String result = Integer.toString(faceValue);
    return result;
    }
}

class CommunityChest{
    String cardText;
    int cashChange;
    String effect;
    
    public CommunityChest(){
        
    }
}

class Chance{
    String cardText;
    int cashChange;
    String effect;
    
    public Chance(){
        
    }
}

/*
TODO:
 - Chance class
 - CC class
 - Add/Sub properties
 - All Property methods
 - Game loop
 - Add players
 - Computer implementation if time
*/