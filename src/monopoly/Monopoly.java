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
        //Scanner
        Scanner scan = new Scanner(System.in);
        
        System.out.println("""
                           Welcome to:
                                                                                                                          
                                                                                                          88              
                                                                                                          88              
                                                                                                          88              
                           88,dPYba,,adPYba,   ,adPPYba,  8b,dPPYba,   ,adPPYba,  8b,dPPYba,   ,adPPYba,  88 8b       d8  
                           88P'   "88"    "8a a8"     "8a 88P'   `"8a a8"     "8a 88P'    "8a a8"     "8a 88 `8b     d8'  
                           88      88      88 8b       d8 88       88 8b       d8 88       d8 8b       d8 88  `8b   d8'   
                           88      88      88 "8a,   ,a8" 88       88 "8a,   ,a8" 88b,   ,a8" "8a,   ,a8" 88   `8b,d8'    
                           88      88      88  `"YbbdP"'  88       88  `"YbbdP"'  88`YbbdP"'   `"YbbdP"'  88     Y88'     
                                                                                  88                             d8'      
                                                                                  88                            d8'       
                           (IP owned by Hasbro Gaming)
                           
                           Before you aspiring capitalists get started, how many players are participating?
                           Type: '2', '3', or '4'.""");
        boolean choiceFail;
        int playerChoice;
        String p1Name = "";
        String p2Name = "";
        String p3Name = "";
        String p4Name = "";
        boolean p1Participating = true;
        boolean p2Participating = true;
        boolean p3Participating = false;
        boolean p4Participating = false;
        do{
            switch(scan.nextInt()){
                case 2 ->{
                    System.out.println("""
                                       
                                       You have selected: 2 players!
                                       It's gonna be a close one!
                                       """);
                    playerChoice = 2;
                    choiceFail = false;
                }
                case 3 ->{
                    System.out.println("""
                                       
                                       You have selected: 3 players!
                                       A three-way duel to bankruptcy!
                                       """);
                    playerChoice = 3;
                    p3Participating = true;
                    choiceFail = false;
                }
                case 4 ->{
                    System.out.println("""
                                       
                                       You have selected: 4 players!
                                       A full party!
                                       """);
                    playerChoice = 4;
                    p3Participating = true;
                    p4Participating = true;
                    choiceFail = false;
                }
                default ->{
                    System.out.println("\nNot a valid number of players. Please try again!\n");
                    choiceFail = true;
                }
            }
        }while(choiceFail == true);
        
        for(int i = 0; i < 4; i++){
            switch (i){
                case 0 ->{
                    if(p1Participating){
                            System.out.println("Player 1: What is your name?\nPlease input.");
                        do{
                            p1Name = scan.nextLine();
                            if(!p1Name.equals("")){
                                System.out.println("\nYour name is: " + p1Name + "!\n");
                                choiceFail = false;
                            }else{
                                choiceFail = true;
                            }
                        }while(choiceFail == true);
                    }
                }
                case 1 ->{
                    if(p2Participating){
                            System.out.println("Player 2: What is your name?\nPlease input.");
                        do{
                            p2Name = scan.nextLine();
                            if(!p2Name.equals("")){
                                System.out.println("\nYour name is: " + p2Name + "!\n");
                                choiceFail = false;
                            }else{
                                choiceFail = true;
                            }
                        }while(choiceFail == true);
                    }
                }
                case 2 ->{
                    if(p3Participating){
                            System.out.println("Player 3: What is your name?\nPlease input.");
                        do{
                            p3Name = scan.nextLine();
                            if(!p3Name.equals("")){
                                System.out.println("\nYour name is: " + p3Name + "!\n");
                                choiceFail = false;
                            }else{
                                choiceFail = true;
                            }
                        }while(choiceFail == true);
                    }
                }
                case 3 ->{
                    if(p4Participating){
                            System.out.println("Player 4: What is your name?\nPlease input.");
                        do{
                            p4Name = scan.nextLine();
                            if(!p4Name.equals("")){
                                System.out.println("\nYour name is: " + p4Name + "!\n");
                                choiceFail = false;
                            }else{
                                choiceFail = true;
                            }
                        }while(choiceFail == true);
                    }
                }
            }
        }
        
        Player player1 = new Player(p1Name, false, p1Participating, 1);
        Player player2 = new Player(p2Name, false, p2Participating, 2);
        Player player3 = new Player(p3Name, false, p3Participating, 3);
        Player player4 = new Player(p4Name, false, p4Participating, 4);
        
        Game game = new Game(player1, player2, player3, player4);
        System.out.println("\nStarting game!");
        game.run();
    }
}

class Game {
        Player player1;
        Player player2;
        Player player3;
        Player player4;
        ArrayList<Property> propertyList = new ArrayList<>();
        ArrayList<CommunityChest> chestDeck = new ArrayList<>();
        ArrayList<CommunityChest> chestUsed = new ArrayList<>();
        ArrayList<Chance> chanceDeck = new ArrayList<>();
        ArrayList<Chance> chanceUsed = new ArrayList<>();
        ArrayList<Player> playerList = new ArrayList<>();
        int playersBroke = 0;
        int totalTurns = 1;
        String propertiesPath = "Decks/Properties.csv";
        String chestPath = "Decks/CommunityChest.csv";
        String chancePath = "Decks/Chance.csv";
        //keeps the program running
        boolean run = true;
        Scanner scan = new Scanner(System.in);
        
    public Game(Player p1, Player p2, Player p3, Player p4){
        //sets the file path
        try (BufferedReader reader = new BufferedReader(new FileReader(this.propertiesPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Create a property
                    Object[] tempArray = line.split(",");
                if(!tempArray[0].equals("Property Name")){
                    ArrayList<Object> toProp = new ArrayList<>(Arrays.asList(tempArray));
                    Property prop = new Property(toProp);
                    //Add to list
                    this.propertyList.add(prop);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: "
            + e.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(this.chestPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Create a chest card
                    Object[] tempArray = line.split(",");
                if(!tempArray[0].equals("Card flavor text")){
                    ArrayList<Object> toCard = new ArrayList<>(Arrays.asList(tempArray));
                    CommunityChest card = new CommunityChest(toCard);
                    //Add to list
                    this.chestDeck.add(card);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: "
            + e.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(this.chancePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Create a chance card
                    Object[] tempArray = line.split(",");
                if(!tempArray[0].equals("Card flavor text")){
                    ArrayList<Object> toCard = new ArrayList<>(Arrays.asList(tempArray));
                    Chance card = new Chance(toCard);
                    //Add to list
                    this.chanceDeck.add(card);
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the file: "
            + e.getMessage());
        }
        Collections.shuffle(this.chestDeck);
        Collections.shuffle(this.chanceDeck);
        this.player1 = p1;
        playerList.add(player1);
        this.player2 = p2;
        playerList.add(player2);
        this.player3 = p3;
        playerList.add(player3);
        this.player4 = p4;
        playerList.add(player4);
        
        for(int i = 0; i < playerList.size(); i++){
            if(playerList.get(i).checkBroke()){
                playersBroke++;
            }
        }
    }
    
    public void run(){
        while(run == true){
            System.out.println("\n\n___________Round "+totalTurns+"!___________\n\n");
            /*System.out.println(
                "Before: "+
                player1.getName() +
                " | " +
                System.identityHashCode(player1)
            );
            
            boolean broke = player1.checkBroke();
            
            System.out.println(
                "After check: "+
                player1.getName() +
                " | " +
                System.identityHashCode(player1)
            );*/
            if(!player1.checkBroke()){
                /*System.out.println(
                    "Inside if: "+
                    player1.getName() +
                    " | " +
                    System.identityHashCode(player1)
                );*/
                System.out.println("\n\n~~~~~~~~~~ "+player1.getName()+ "'s Turn! ~~~~~~~~~~\n\n");
                playerTurn(player1);
                if(player1.checkBroke()){
                    System.out.println("You're broke! Game over!");
                }
                if(player2.checkBroke()){
                    playersBroke = 1;
                    if(player3.checkBroke()){
                        playersBroke = 2;
                        if(player4.checkBroke()){
                            playersBroke = 3;
                        }
                    }
                }
            }
                /*for(char c : player1.getName().toCharArray()){
                    System.out.println((int)c);
                }*/
            //System.out.println(player1.getName());
            if(!player2.checkBroke()){
                System.out.println("\n\n~~~~~~~~~~ "+player2.getName()+ "'s Turn! ~~~~~~~~~~\n\n");
                playerTurn(player2);
                if(player2.checkBroke()){
                    System.out.println("You're broke! Game over!");
                }
                if(player1.checkBroke()){
                    playersBroke = 1;
                    if(player3.checkBroke()){
                        playersBroke = 2;
                        if(player4.checkBroke()){
                            playersBroke = 3;
                        }
                    }
                }
            }
            if(!player3.checkBroke()){
                System.out.println("\n\n~~~~~~~~~~ "+player3.getName()+ "'s Turn! ~~~~~~~~~~\n\n");
                playerTurn(player3);
                if(player3.checkBroke()){
                    System.out.println("You're broke! Game over!");
                }
                if(player1.checkBroke()){
                    playersBroke = 1;
                    if(player2.checkBroke()){
                        playersBroke = 2;
                        if(player4.checkBroke()){
                            playersBroke = 3;
                        }
                    }
                }
            }
            if(!player4.checkBroke()){
                System.out.println("\n\n~~~~~~~~~~ "+player4.getName()+ "'s Turn! ~~~~~~~~~~\n\n");
                playerTurn(player4);
                if(player4.checkBroke()){
                    System.out.println("You're broke! Game over!");
                }
                if(player1.checkBroke()){
                    playersBroke = 1;
                    if(player2.checkBroke()){
                        playersBroke = 2;
                        if(player3.checkBroke()){
                            playersBroke = 3;
                        }
                    }
                }
            }
            System.out.println("\n\n___________Round "+totalTurns+" end!___________\n\n");
            totalTurns++;
            if(playersBroke == 3){
                if(!player1.checkBroke()){
                    System.out.println("Congrats! "+player1.getName()+" wins!");
                }else if(!player2.checkBroke()){
                    System.out.println("Congrats! "+player2.getName()+" wins!");
                }else if(!player3.checkBroke()){
                    System.out.println("Congrats! "+player3.getName()+" wins!");
                }else if(!player4.checkBroke()){
                    System.out.println("Congrats! "+player4.getName()+" wins!");
                }
            }
        }
    }
    
    public void playerTurn(Player player){
        if(player.getJailed() == 0){
            player.roll(this);
        }else{
         player.rollJail(this);
        }
        System.out.println("\n\n~~~~~~~~~~ Turn end! ~~~~~~~~~~\n\n");
    }
    
    public void checkSpace(Player player){
        int playerSpace = player.getSpace();
        switch(playerSpace){
            case 0 ->{
                System.out.println("You landed on: GO! Enjoy your $200!");
            }
            case 1 ->{
                checkProp(player, this.propertyList.get(0));
            }
            case 2 ->{
                System.out.println("You landed on: Community Chest! What will it be?");
                playChest(player);
            }
            case 3 ->{
                checkProp(player, this.propertyList.get(1));
            }
            case 4 ->{
                System.out.println("You landed on: Income Tax! Pay up $200!");
                player.changeCash(-200);
            }
            case 5 ->{
                checkProp(player, this.propertyList.get(22));
            }
            case 6 ->{
                checkProp(player, this.propertyList.get(2));
            }
            case 7 ->{
                System.out.println("You landed on: Chance! Test your luck!");
                playChance(player);
            }
            case 8 ->{
                checkProp(player, this.propertyList.get(3));          
            }
            case 9 ->{
                checkProp(player, this.propertyList.get(4));          
            }
            case 10 ->{
                System.out.println("You landed on: Just Visiting! Hello, suckers!");
            }
            case 11 ->{
                checkProp(player, this.propertyList.get(5));           
            }
            case 12 ->{
                checkProp(player, this.propertyList.get(26));            
            }
            case 13 ->{
                checkProp(player, this.propertyList.get(6));         
            }
            case 14 ->{
                checkProp(player, this.propertyList.get(7));     
            }
            case 15 ->{
                checkProp(player, this.propertyList.get(23));       
            }
            case 16 ->{
                checkProp(player, this.propertyList.get(8));          
            }
            case 17 ->{
                System.out.println("You landed on: Community Chest! What will it be?");
                playChest(player);
            }
            case 18 ->{
                checkProp(player, this.propertyList.get(9));         
            }
            case 19 ->{
                checkProp(player, this.propertyList.get(10));        
            }
            case 20 ->{
                System.out.println("You landed on: Free Parking! Have a nice stay!");
            }
            case 21 ->{
                checkProp(player, this.propertyList.get(11));        
            }
            case 22 ->{
                System.out.println("You landed on: Chance! Test your luck!");
                playChance(player);
            }
            case 23 ->{
                checkProp(player, this.propertyList.get(12));           
            }
            case 24 ->{
                checkProp(player, this.propertyList.get(13));   
            }
            case 25 ->{
                checkProp(player, this.propertyList.get(24));      
            }
            case 26 ->{
                checkProp(player, this.propertyList.get(14));       
            }
            case 27 ->{
                checkProp(player, this.propertyList.get(15));       
            }
            case 28 ->{
                checkProp(player, this.propertyList.get(27));  
            }
            case 29 ->{
                checkProp(player, this.propertyList.get(16));      
            }
            case 30 ->{
                System.out.println("You landed on: Go to Jail! Aw, rats!");
                player.setJailed(3);
                player.setSpace(10);
            }
            case 31 ->{
                checkProp(player, this.propertyList.get(17));         
            }
            case 32 ->{
                checkProp(player, this.propertyList.get(18));        
            }
            case 33 ->{
                System.out.println("You landed on: Community Chest! What will it be?");
                playChest(player);
            }
            case 34 ->{
                checkProp(player, this.propertyList.get(19));
            }
            case 35 ->{
                checkProp(player, this.propertyList.get(25));        
            }
            case 36 ->{
                System.out.println("You landed on: Chance! Test your luck!");
                playChance(player);
            }
            case 37 ->{
                checkProp(player, this.propertyList.get(20));      
            }
            case 38 ->{
                System.out.println("You landed on: Luxury Tax! Pay up $100!");
                player.changeCash(-100);
            }
            case 39 ->{
                checkProp(player, this.propertyList.get(21));      
            }
        }
    }
    
    public void playChest(Player player){
        chestDeck.get(0).playCard(this, player);
        chestUsed.add(chestDeck.get(0));
        chestDeck.remove(0);
        if(chestDeck.isEmpty()){
            System.out.println("Chest deck empty! Reshuffling...");
            chestDeck = chestUsed;
            Collections.shuffle(chestDeck);
        }
    }
    
    public void playChance(Player player){
        chanceDeck.get(0).playCard(this, player);
        chanceUsed.add(chanceDeck.get(0));
        chanceDeck.remove(0);
        if(chanceDeck.isEmpty()){
            System.out.println("Chance deck empty! Reshuffling...");
            chanceDeck = chanceUsed;
            Collections.shuffle(chanceDeck);
        }
    }
    
    public void buyProperty(Player player, Property property){
        if(player.getCash() <= property.getCost()){
            System.out.println("""
                               You're too broke to afford this property.
                               Come back when you're a little richer.""");
        }else{
            System.out.println("Would you like to purchase this property? You have $" + player.getCash());
            System.out.println(property.getName() + " costs $" + property.getCost());
            System.out.println("Input 'y' or 'n' to make your choice.");
            boolean rollLoop = true;
            while(rollLoop){
                String temp = scan.next();
                if(temp.equals("y")){
                    rollLoop = false;
                    System.out.println("Cha-Ching! " + property.getName() + " sold to " + player.getName() + "!");
                    player.changeCash(-property.getCost());
                    player.addProps(property.getPosition(), propertyList);
                    System.out.println("Your total cash: " + player.getCash());
                    System.out.println("Your total properties:\n" + player.getProps());
                }else if(temp.equals("n")){
                    rollLoop = false;
                    System.out.println("Just perusing the plot...");
                }
            }
            
        }
    }
    
    public void checkProp(Player player, Property property){
        String flavorText = "You landed on: " + property.getName() + "!";
        switch(property.getName()){
            case "Reading Railroad" ->{
                flavorText += " All aboard!";
            }
            case "Pennsylvania Railroad" ->{
                flavorText += " All aboard!";
            }
            case "B. & O. Railroad" ->{
                flavorText += " All aboard!";
            }
            case "Short Line" ->{
                flavorText += " All aboard!";
            }
            case "Electric Company" ->{
                flavorText += " Bzzzzap!";
            }
            case "Water Works" ->{
                flavorText += " Sploosh!";
            }
        }
        System.out.println(flavorText);
        
        if(!property.checkOwned()){
            buyProperty(player, property);
        }else if(property.getWhoOwns() == player.getNumber()){
            System.out.println("Just cruising by...");
        }else{
            switch(property.getWhoOwns()){
                case 1 ->{
                    player.changeCash(property.calculateRent(player1));
                }
                case 2 ->{
                    player.changeCash(property.calculateRent(player2));
                }
                case 3 ->{
                    player.changeCash(property.calculateRent(player3));
                }
                case 4 ->{
                    player.changeCash(property.calculateRent(player4));
                }
            }
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
    //Unimplemented
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
    
    //Scanner
    Scanner scan = new Scanner(System.in);
    
    public void roll(Game game){
        System.out.println("Input 'y' to roll dice when ready!");
        boolean rollLoop = true;
        while(rollLoop){
            String temp = scan.next();
            if(temp.equals("y")){
                rollLoop = false;
                Die d1 = new Die();
                Die d2 = new Die();
                boolean doublesTrue = false;
                int doublesCounter = 0;
                do{
                    System.out.println("\nRolling...");
                    int d1Roll = d1.roll();
                    int d2Roll = d2.roll();
                    int total = d1Roll + d2Roll;
                    System.out.println("You rolled: "+d1Roll+" and "+d2Roll+", totalling to "+total+"!\n");
                    doublesTrue = (d1Roll == d2Roll);
                    if((doublesTrue == true) && (doublesCounter < 3)){
                        System.out.println("\nThat's doubles! Go again!");
                        doublesCounter++;
                    }
                    if(doublesCounter < 3){
                        for(int i = 0; i < total; i++){
                            if(this.getSpace() == 39){
                                this.setSpace(0);
                                this.changeCash(200);
                                System.out.println("""
                                                   
                                                   You passed Go! Collect $200.
                                                   Total cash: $"""+this.getCash()+"\n");
                            }else if(this.getSpace() < 39){
                                this.setSpace(this.getSpace() + 1);
                            }
                        }
                        game.checkSpace(this);
                    }else if(doublesCounter == 3){
                        System.out.println("\nThat's three!!! Go to jail, sucker!!!");
                        this.setJailed(3);
                        this.setSpace(10);
                    }
                }while(doublesTrue == true);
            }
        }
    }
    
    public void rollJail(Game game){
        System.out.println("You are jailed. Womp womp!");
        
        if(this.checkJailFree()){
            System.out.println("\nYou have a Get Out of Jail Free card.\nWould you like to use it?");
            System.out.println("Input 'y' or 'n' to make your choice.");
            boolean rollLoop = true;
            while(rollLoop){
                String temp = scan.next();
                if(temp.equals("y")){
                    rollLoop = false;
                    System.out.println("\nInstant freedom!!! Take your turn as normal.");
                    this.setJailed(0);
                    this.setJailFree(false);
                    this.roll(game);
                }else if(temp.equals("n")){
                    rollLoop = false;
                    System.out.println("\nSaving it for later...");
                }
            }
            
        }else if(this.getCash() > 50){
            System.out.println("Would you like to pay $50 to get out now?");
            System.out.println("You currently have $"+this.getCash()+".");
            System.out.println("Input 'y' or 'n' to make your choice.");
            boolean rollLoop = true;
            while(rollLoop){
                String temp = scan.next();
                if(temp.equals("y")){
                    rollLoop = false;
                    System.out.println("Instant freedom!!! Take your turn as normal.");
                    this.setJailed(0);
                    this.changeCash(-50);
                    System.out.println("You now have $"+this.getCash()+".");
                    this.roll(game);
                }else if(temp.equals("n")){
                    rollLoop = false;
                    System.out.println("Nope, you feel lucky today!!!");
                }
            }
        }
        
        if((this.getJailed() == 0) && (this.getSpace() == 10)){
            System.out.println(""" 
                               Input 'y' to roll dice when ready!""");
            boolean rollLoop = true;
            while(rollLoop){
                String temp = scan.next();
                if(temp.equals("y")){
                    rollLoop = false;
                    System.out.println("Rolling...");
                    Die d1 = new Die();
                    Die d2 = new Die();
                    int d1Roll = d1.roll();
                    int d2Roll = d2.roll();
                    System.out.println("You rolled: "+d1Roll+" and "+d2Roll+"!");
                    if(d1Roll == d2Roll){
                        System.out.println("Doubles!!! You're free!!!");
                        this.setJailed(0);

                    } else if(this.getJailed() > 1){
                        System.out.println("Aw rats, we'll get 'em next time...");
                        this.setJailed(playerJailed - 1);
                    } else if(this.getJailed() == 1){
                        System.out.println("That was your last shot... Pay $50 and be free!");
                        this.changeCash(-50);
                        System.out.println("You currently have $"+this.getCash()+".");
                    }
                    if((this.getJailed() == 0) && (this.getSpace() == 10)){
                        int total = d1Roll + d2Roll;
                        System.out.println("You move "+total+" spaces.");
                        for(int i = 0; i < total; i++){
                            if(this.getSpace() == 39){
                                this.setSpace(0);
                                this.changeCash(200);
                                System.out.println("""
                                                   You passed Go! Collect $200.
                                                   Total cash: $"""+this.getCash());
                            }else if(this.getSpace() < 39){
                                this.setSpace(this.getSpace() + 1);
                            }
                        }
                        game.checkSpace(this);
                    }
                }
            }
        }
    }
    
    public String getName(){
        return playerName;
    }
    
    public int getCash(){
        return playerCash;
    }
    
    public int getJailed(){
        return playerJailed;
    }
    
    public int getSpace(){
        return currentSpace;
    }
    
    public int getNumber(){
        return playerNumber;
    }
    
    public boolean checkBroke(){
        if(this.getCash() <= 0){
            playerBroke = true;
        }
        return playerBroke;
    }
    
    public boolean checkJailFree(){
        return jailFree;
    }
    
    public boolean checkComputer(){
        return computerPlayer;
    }
    
    public ArrayList<Property> getProps(){
        return playerProps;
    }
    
    public void setSpace(int space){
        currentSpace = space;
    }
    public void setJailed(int turns){
        playerJailed = turns;
    }
    
    public void setJailFree(boolean TOrF){
        jailFree = TOrF;
    }
    
    public void changeCash(int cash){
        playerCash = playerCash + cash;
    }
    
    public void addProps(int propertySpace, ArrayList<Property> propList){
        for(Property p : propList){
            if(p.getPosition() == propertySpace){
                playerProps.add(p);
                p.setOwned(true);
                p.setWhoOwns(getNumber());
            }
        }
        for(int i = 0; i < playerProps.size() - 1; i++){
            int position1 = propList.indexOf(playerProps.get(i));
            int position2 = propList.indexOf(playerProps.get(i + 1));
            if(position1 > position2){
                Property temp = playerProps.get(i + 1);
                playerProps.remove(i + 1);
                playerProps.add(i + 1, playerProps.get(i));
                playerProps.remove(i);
                playerProps.add(i, temp);
            }
        }
    }
    
    @Override
    public String toString() {
        String returnString = this.getName();
        return returnString;
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
    boolean completeSet;
    
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
        this.completeSet = false;
    }
    
    public int calculateRent(Player owner){
        int finalRent = 0;
        int setCounter = 0;
        //Scanner
        Scanner scan = new Scanner(System.in);
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
                    System.out.println(""" 
                               Input 'y' to roll dice when ready!""");
                    boolean rollLoop = true;
                    while(rollLoop){
                        String temp = scan.next();
                        if(temp.equals("y")){
                            rollLoop = false;
                            System.out.println("Rolling...");
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
                            System.out.println("You rolled: "+roll1+" and "+roll2+",\n"
                                    + "adding and multiplying to "+finalRent+"!");
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
            System.out.println("You must pay $" + finalRent
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
    
    public boolean checkSet(){
        return this.completeSet;
    }
    
    public void setOwned(boolean TorF){
        owned = TorF;
    }
    
    public void setWhoOwns(int owner){
        whoOwns = owner;
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
    
    public void playCard(Game game, Player drawing){
        System.out.println(this.getText());
        switch(this.getEffect()){
            case "none" -> {
                drawing.changeCash(this.getCashDiff());
                System.out.println("Total cash: $"+drawing.getCash());
            }
            case "JailFree" -> {
                drawing.setJailFree(true);
            }
            case "PerPlayer" -> {
                int othersPay = -this.getCashDiff();
                for(int i = 1; i <= 4; i++){
                    switch(i){
                        case 1 -> {
                            if(!game.player1.checkBroke() && !game.player1.equals(drawing)){
                                drawing.changeCash(getCashDiff());
                                game.player1.changeCash(othersPay);
                            }
                        }
                        case 2 -> {
                            if(!game.player2.checkBroke() && !game.player2.equals(drawing)){
                                drawing.changeCash(getCashDiff());
                                game.player2.changeCash(othersPay);
                            }
                        }
                        case 3 -> {
                            if(!game.player3.checkBroke() && !game.player3.equals(drawing)){
                                drawing.changeCash(getCashDiff());
                                game.player3.changeCash(othersPay);
                            }
                        }
                        case 4 -> {
                            if(!game.player4.checkBroke() && !game.player4.equals(drawing)){
                                drawing.changeCash(getCashDiff());
                                game.player4.changeCash(othersPay);
                            }
                        }
                    }
                }
                System.out.println("Total cash: $"+drawing.getCash());
            }
            case "Repairs" ->{
                ArrayList<Property> tempArray = drawing.getProps();
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
                drawing.changeCash(doshLost);
                System.out.println("You paid: $" + doshLost + "!"
                        + "\nTotal cash: $" + drawing.getCash());
            }
            case "Jail" -> {
                drawing.setSpace(10);
                drawing.setJailed(3);
            }
            case "GO" -> {
                drawing.setSpace(0);
                drawing.changeCash(200);
                System.out.println("Total cash: $"+drawing.getCash());
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
    
    public void playCard(Game game, Player drawing){
        System.out.println(this.getText());
        switch(this.getEffect()){
            case "none" -> {
                drawing.changeCash(this.getCashDiff());
                System.out.println("Total cash: $"+drawing.getCash());
            }
            case "JailFree" -> {
                drawing.setJailFree(true);
            }
            case "PerPlayer" -> {
                int othersPay = -this.getCashDiff();
                for(int i = 1; i <= 4; i++){
                    switch(i){
                        case 1 -> {
                            if(!game.player1.checkBroke() && !game.player1.equals(drawing)){
                                drawing.changeCash(getCashDiff());
                                game.player1.changeCash(othersPay);
                            }
                        }
                        case 2 -> {
                            if(!game.player2.checkBroke() && !game.player2.equals(drawing)){
                                drawing.changeCash(getCashDiff());
                                game.player2.changeCash(othersPay);
                            }
                        }
                        case 3 -> {
                            if(!game.player3.checkBroke() && !game.player3.equals(drawing)){
                                drawing.changeCash(getCashDiff());
                                game.player3.changeCash(othersPay);
                            }
                        }
                        case 4 -> {
                            if(!game.player4.checkBroke() && !game.player4.equals(drawing)){
                                drawing.changeCash(getCashDiff());
                                game.player4.changeCash(othersPay);
                            }
                        }
                    }
                }
                System.out.println("Total cash: $"+drawing.getCash());
            }
            case "Repairs" ->{
                ArrayList<Property> tempArray = drawing.getProps();
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
                drawing.changeCash(doshLost);
                System.out.println("You paid: $" + doshLost + "!"
                        + "\nTotal cash: $" + drawing.getCash());
            }
            case "Jail" -> {
                drawing.setSpace(10);
                drawing.setJailed(3);
            }
            case "GO" -> {
                drawing.setSpace(0);
                drawing.changeCash(200);
                System.out.println("Total cash: $"+drawing.getCash());
            }
            case "Boardwalk" -> {
                while(drawing. getSpace() != 39){
                    if(drawing.getSpace() < 39){
                        drawing.setSpace(drawing.getSpace() + 1);
                    }
                }
            }
            case "IllinoisAve" -> {
                while(drawing. getSpace() != 24){
                    if(drawing.getSpace() == 39){
                        drawing.setSpace(0);
                        drawing.changeCash(200);
                        System.out.println("You passed Go! Collect $200.\nTotal cash: $"+drawing.getCash());
                    }else if(drawing.getSpace() < 39){
                        drawing.setSpace(drawing.getSpace() + 1);
                    }
                }
            }
            case "StCharlesPlace" -> {
                while(drawing. getSpace() != 11){
                    if(drawing.getSpace() == 39){
                        drawing.setSpace(0);
                        drawing.changeCash(200);
                        System.out.println("You passed Go! Collect $200.\nTotal cash: $"+drawing.getCash());
                    }else if(drawing.getSpace() < 39){
                        drawing.setSpace(drawing.getSpace() + 1);
                    }
                }
            }
            case "Railroad" -> {
                boolean railCheck = true;
                do {
                    switch (drawing.getSpace()) {
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
                            drawing.setSpace(0);
                            drawing.changeCash(200);
                            System.out.println("You passed Go! Collect $200.\nTotal cash: $"+drawing.getCash());
                        }
                        default -> {
                            drawing.setSpace(drawing.getSpace() + 1);
                        }
                    }
                }while(railCheck == true);
            }
            case "Utility" -> {
                boolean utilCheck = true;
                do {
                    switch (drawing.getSpace()) {
                        case 12 -> {
                            System.out.println("You landed on: Electric Company!");
                            utilCheck = false;
                        }
                        case 28 -> {
                            System.out.println("You landed on: Water Works!");
                            utilCheck = false;
                        }
                        case 39 -> {
                            drawing.setSpace(0);
                            drawing.changeCash(200);
                            System.out.println("You passed Go! Collect $200.\nTotal cash: $"+drawing.getCash());
                        }
                        default -> {
                            drawing.setSpace(drawing.getSpace() + 1);
                        }
                    }
                }while(utilCheck == true);
            }
            case "Back3" -> {
                for(int i = 0; i < 3; i++){
                    drawing.setSpace(drawing.getSpace() - 1);
                }
            }
            case "ReadRailroad" -> {
                while(drawing. getSpace() != 5){
                    if(drawing.getSpace() == 39){
                        drawing.setSpace(0);
                        drawing.changeCash(200);
                        System.out.println("You passed Go! Collect $200.\nTotal cash: $"+drawing.getCash());
                    }else if(drawing.getSpace() < 39){
                        drawing.setSpace(drawing.getSpace() + 1);
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
 - Finish Game class
    - Specifically, finish run() method
*/