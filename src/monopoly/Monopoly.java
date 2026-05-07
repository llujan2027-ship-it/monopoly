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
import java.util.Collections;
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
        ArrayList<CommunityChest> chestDeck = new ArrayList<>();
        ArrayList<Chance> chanceDeck = new ArrayList<>();
        ArrayList<Player> playerList = new ArrayList<>();
        int PlayersActive = 0;
        
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
        try (BufferedReader reader = new BufferedReader(new FileReader(chestPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Create a contact
                    Object[] tempArray = line.split(",");
                if(!tempArray[0].equals("Card flavor text")){
                    ArrayList<Object> toCard = new ArrayList<>(Arrays.asList(tempArray));
                    CommunityChest card = new CommunityChest(toCard);
                    //Add to list
                    chestDeck.add(card);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: "
            + e.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(chancePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Create a contact
                    Object[] tempArray = line.split(",");
                if(!tempArray[0].equals("Card flavor text")){
                    ArrayList<Object> toCard = new ArrayList<>(Arrays.asList(tempArray));
                    Chance card = new Chance(toCard);
                    //Add to list
                    chanceDeck.add(card);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: "
            + e.getMessage());
        }
        Collections.shuffle(chestDeck);
        Collections.shuffle(chanceDeck);
        Player player1 = new Player("test", false, true, 1);
        Player player2 = new Player("test", false, true, 2);
        Player player3 = new Player("test", false, true, 3);
        Player player4 = new Player("test", false, true, 4);
    }
}

class Game {
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    
    public Game(Player p1, Player p2, Player p3, Player p4){
        this.player1 = p1;
        this.player2 = p2;
        this.player3 = p3;
        this.player4 = p4;
    }
    /*public void playerTurn(){
        System.out.println(this.getName() + "'s Turn!");
        if(this.getJailed() == 0){
        //- IF TRUE -
        // Wait for player prompt
        // Roll dice
        // Check space
        //  - UNOWNED PROPERTY -
        //   Ask player to buy
        //  - IF BOUGHT -
        //   Subtract cash from player, add property to playerProps
        //   Original property has owned set to true, and whoOwns set to playerNumber
        //  - IF NOT BOUGHT -
        }else{
         this.rollJail();
        }
    }*/
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
                for(int i = 0; i < 39; i++){
                    if(this.getSpace() == 39){
                        this.setSpace(0);
                        this.changeCash(200);
                        System.out.println("You passed Go! Collect $200."
                                + "\nTotal cash: $"+this.getCash());
                    }else if(this.getSpace() < 39){
                        this.setSpace(this.getSpace() + 1);
                    }
                }
            }else if(doublesCounter == 3){
                this.setJailed(3);
            }
        }while(doublesTrue == true);
        
    }
    
    public void rollJail(){
        
        if(this.checkJailFree()){
            //Ask to use JailFree
        }else if(this.getCash() > 50){
            //Ask to pay $50
        }
        
        Die d1 = new Die();
        Die d2 = new Die();
        int d1Roll = d1.roll();
        int d2Roll = d2.roll();
        if(d1Roll == d2Roll){
            this.setJailed(0);
            
        } else if(this.getJailed() > 1){
            this.setJailed(playerJailed - 1);
        }
        if(this.getJailed() == 0){
            int total = d1Roll + d2Roll;
            for(int i = 0; i < 39; i++){
                    if(this.getSpace() == 39){
                        this.setSpace(0);
                        this.changeCash(200);
                        System.out.println("You passed Go! Collect $200."
                                + "\nTotal cash: $"+this.getCash());
                    }else if(this.getSpace() < 39){
                        this.setSpace(this.getSpace() + 1);
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
    
    public void setSpace(int space){
        this.currentSpace = space;
    }
    public void setJailed(int turns){
        this.playerJailed = turns;
    }
    
    public void setJailFree(boolean TOrF){
        this.jailFree = TOrF;
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
    
    public CommunityChest(ArrayList<Object> toCard){
        this.cardText = toCard.get(0).toString();
        this.cashChange = Integer.parseInt(toCard.get(1).toString());
        this.effect = toCard.get(2).toString();
    }
    
    public void playCard(Player player){
        System.out.println(this.getText());
        switch(this.getEffect()){
            case "none" -> {
                player.changeCash(this.getCashDiff());
                System.out.println("Total cash: $"+player.getCash());
            }
            case "JailFree" -> {
                player.setJailFree(true);
            }
            case "PerPlayer" -> {
                int othersPay = -this.getCashDiff();
                for(int i = 1; i <= 4; i++){
                    switch(i){
                        case 1 -> {
                            
                        }
                        case 2 -> {
                            
                        }
                        case 3 -> {
                            
                        }
                        case 4 -> {
                            
                        }
                    }
                }
            }
            case "Repairs" ->{
                ArrayList<Property> tempArray = player.getProps();
                int totalHouses = 0;
                int totalHotels = 0;
                int doshLost = 0;
                for(int i = 0; i < tempArray.size(); i++){
                    if(tempArray.get(i).getBuildings() < 5){
                        totalHouses = totalHouses + tempArray.get(i).getBuildings();
                    }else if(tempArray.get(i).getBuildings() == 5){
                        totalHotels = totalHotels + 1;
                    }
                }
                totalHouses = totalHouses * 25;
                totalHotels = totalHotels * 100;
                doshLost = doshLost - (totalHouses + totalHotels);
                player.changeCash(doshLost);
                System.out.println("You paid: $" + doshLost + "!"
                        + "\nTotal cash: $" + player.getCash());
            }
            case "Jail" -> {
                player.setSpace(10);
                player.setJailed(3);
            }
            case "GO" -> {
                player.setSpace(0);
                player.changeCash(200);
                System.out.println("Total cash: $"+player.getCash());
            }
        }
    }
    
    public String getText(){
      return this.cardText;  
    }
    
    public int getCashDiff(){
        return this.cashChange;
    }
    
    public String getEffect(){
        return this.effect;
    }
}

class Chance{
    String cardText;
    int cashChange;
    String effect;
    
    public Chance(ArrayList<Object> toCard){
        this.cardText = toCard.get(0).toString();
        this.cashChange = Integer.parseInt(toCard.get(1).toString());
        this.effect = toCard.get(2).toString();
    }
    
    public void playCard(Player player){
        System.out.println(this.getText());
        switch(this.getEffect()){
            case "none" -> {
                player.changeCash(this.getCashDiff());
                System.out.println("Total cash: $"+player.getCash());
            }
            case "JailFree" -> {
                player.setJailFree(true);
            }
            case "PerPlayer" -> {
                //TODO
            }
            case "Repairs" ->{
                ArrayList<Property> tempArray = player.getProps();
                int totalHouses = 0;
                int totalHotels = 0;
                int doshLost = 0;
                for(int i = 0; i < tempArray.size(); i++){
                    if(tempArray.get(i).getBuildings() < 5){
                        totalHouses = totalHouses + tempArray.get(i).getBuildings();
                    }else if(tempArray.get(i).getBuildings() == 5){
                        totalHotels = totalHotels + 1;
                    }
                }
                totalHouses = totalHouses * 25;
                totalHotels = totalHotels * 100;
                doshLost = doshLost - (totalHouses + totalHotels);
                player.changeCash(doshLost);
                System.out.println("You paid: $" + doshLost + "!"
                        + "\nTotal cash: $" + player.getCash());
            }
            case "Jail" -> {
                player.setSpace(10);
                player.setJailed(3);
            }
            case "GO" -> {
                player.setSpace(0);
                player.changeCash(200);
                System.out.println("Total cash: $"+player.getCash());
            }
            case "Boardwalk" -> {
                while(player. getSpace() != 39){
                    if(player.getSpace() < 39){
                        player.setSpace(player.getSpace() + 1);
                    }
                }
            }
            case "IllinoisAve" -> {
                while(player. getSpace() != 24){
                    if(player.getSpace() == 39){
                        player.setSpace(0);
                        player.changeCash(200);
                        System.out.println("You passed Go! Collect $200.\nTotal cash: $"+player.getCash());
                    }else if(player.getSpace() < 39){
                        player.setSpace(player.getSpace() + 1);
                    }
                }
            }
            case "StCharlesPlace" -> {
                while(player. getSpace() != 11){
                    if(player.getSpace() == 39){
                        player.setSpace(0);
                        player.changeCash(200);
                        System.out.println("You passed Go! Collect $200.\nTotal cash: $"+player.getCash());
                    }else if(player.getSpace() < 39){
                        player.setSpace(player.getSpace() + 1);
                    }
                }
            }
            case "Railroad" -> {
                boolean railCheck = true;
                do {
                    switch (player.getSpace()) {
                        case 5 -> {
                            System.out.println("You landed on: Reading Railroad!");
                            railCheck = false;
                        }
                        case 15 -> {
                            System.out.println("You landed on: Pennsylvania Railroad!");
                            railCheck = false;
                        }
                        case 25 -> {
                            System.out.println("You landed on: B. & O. Railroad!");
                            railCheck = false;
                        }
                        case 35 -> {
                            System.out.println("You landed on: Short Line!");
                            railCheck = false;
                        }
                        case 39 -> {
                            player.setSpace(0);
                            player.changeCash(200);
                            System.out.println("You passed Go! Collect $200.\nTotal cash: $"+player.getCash());
                        }
                        default -> {
                            player.setSpace(player.getSpace() + 1);
                        }
                    }
                }while(railCheck == true);
            }
            case "Utility" -> {
                boolean utilCheck = true;
                do {
                    switch (player.getSpace()) {
                        case 12 -> {
                            System.out.println("You landed on: Electric Company!");
                            utilCheck = false;
                        }
                        case 28 -> {
                            System.out.println("You landed on: Water Works!");
                            utilCheck = false;
                        }
                        case 39 -> {
                            player.setSpace(0);
                            player.changeCash(200);
                            System.out.println("You passed Go! Collect $200.\nTotal cash: $"+player.getCash());
                        }
                        default -> {
                            player.setSpace(player.getSpace() + 1);
                        }
                    }
                }while(utilCheck == true);
            }
            case "Back3" -> {
                for(int i = 0; i < 3; i++){
                    player.setSpace(player.getSpace() - 1);
                }
            }
            case "ReadRailroad" -> {
                while(player. getSpace() != 5){
                    if(player.getSpace() == 39){
                        player.setSpace(0);
                        player.changeCash(200);
                        System.out.println("You passed Go! Collect $200.\nTotal cash: $"+player.getCash());
                    }else if(player.getSpace() < 39){
                        player.setSpace(player.getSpace() + 1);
                    }
                }
            }
        }
    }
    
    public String getText(){
      return this.cardText;  
    }
    
    public int getCashDiff(){
        return this.cashChange;
    }
    
    public String getEffect(){
        return this.effect;
    }
}

/*
TODO:
 - Chance class
 - CC class
 - Add/Sub properties
 - All Property methods
 - Game class
 - Add players
 - Computer implementation if time
*/