/*Name: Vicky Mohamamd
 *ID: 0895381
 */
package stock;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * class for the main menu
 * @author vicky mohammad
 */
public class Menu {
    //declare var
    private static String menu = " ", input = " ";
    private static String symbol = " ", name = " ";
    private static double price = 0;
    private static int quantity = 0;
    private static String searchRange = " ";
    private static boolean found = false;
    //dieclare objects
    private static Scanner scan = new Scanner(System.in);
    private static Portfolio portfolio = new Portfolio();
    private static ArrayList<Investment> hashIndex = new ArrayList<Investment>();
    private static HashMap<String, ArrayList<Integer>> hash = new HashMap <String, ArrayList<Integer>>();
    DecimalFormat twoDecimal = new DecimalFormat();
    
    /**
     * main function
     * @param args for the command line input
     */
    public static void main(String[] args) {
        //load the array 
        loadPortfolio(args[0]);
        //loop till program exit
        while(!menu.equalsIgnoreCase("quit")){
            //print all for debug
            //portfolio.toStringList();
            removeUpdateHash();
            //display the options
            System.out.println("1. Buy");
            System.out.println("2. Sell");
            System.out.println("3. Update");
            System.out.println("4. GetGain");
            System.out.println("5. Search");
            System.out.println("6. Quit");
            //ask the user for option
            System.out.println("[Enter the number or the command])");
            System.out.print("Which Option do you want: ");
            menu = scan.nextLine();
            //choosing the menu option
            if(menu.equalsIgnoreCase("buy") || menu.equals("1")){
                option1();
            }else if(menu.equalsIgnoreCase("sell") || menu.equals("2")){
                option2();
            }else if(menu.equalsIgnoreCase("update") || menu.equals("3")){
                option3();
            }else if(menu.equalsIgnoreCase("getgain") || menu.equals("4")){
                option4();
            }else if(menu.equalsIgnoreCase("search") || menu.equals("5")){   
                option5();
            }else if(menu.equalsIgnoreCase("quit") || menu.equals("6") || menu.equalsIgnoreCase("q")){
                //save the portfolio and exit
                savePortfolio(args[0]);
                System.out.println("Program has exited.");
                System.exit(0);
            }else{
                System.out.println("PLEASE ENTER THE AVAILABLE MENU");
            }//end if
        }//end if
    }//end main
    
    /**
     * a function to catch wrong format for int 
     * @param toBeConverted from the input to int
     * @return -1 if wrong format
     */
    public static int tryCatchInt(String toBeConverted){
        int number = 0;
        try{
            number = Integer.parseInt(toBeConverted);
        }catch(NumberFormatException E){
            System.out.println("WRONG FORMAT");
            return number = -1;
        }//end catch
        return number;
    }//end  func
    
    /**
     * a function to catch wrong format for int 
     * @param toBeConverted from input to int
     * @return -1 if wrong format
     */
    public static double tryCatchDouble(String toBeConverted){
        double number = 0;
        try{
            number = Double.parseDouble(toBeConverted);
        }catch(NumberFormatException E){
            System.out.println("WRONG FORMAT");
            return number = -1;
        }//end catch
        return number;
    }//end  func
    
    /**
     * a function to ask symbol
     */
    public static void askSymbol(){
        //enter symbol
        do{
            System.out.print("Enter Symbol: ");
            symbol = scan.nextLine();
        }while(symbol.equals("") || symbol.equals("@"));
    }//end func
    
    /**
     * a function to ask name
     */
    public static void askName(){
        //enter name
        do{
        System.out.print("Enter Name: ");
        name = scan.nextLine();
        }while(name.equals("") || name.equals("@"));
    }//end func
    
    /**
     * function ask quantity
     */
    public static void askQuantity(){
        //enter quanity
        do{
            System.out.print("Enter Quantity: ");
            input = scan.nextLine();
            quantity = tryCatchInt(input);
        }while(quantity < 1);
    }//end func
    
    /**
     * function ask price
     */
    public static void askPrice(){
        do{
            //enter quanity
            System.out.print("Enter Price: ");
            input = scan.nextLine();
            price = tryCatchDouble(input);
        }while(price < 0.01);
    }//end func
    
    /**
     * ask price for the search
     * @return the -1 if error
     */
    public static double askPriceForSearch(){
        double number = 0;
        //enter quanity
        System.out.print("Enter Price: ");
        searchRange = scan.nextLine();
        //if wrong formate enter -1
        try{
            price = Double.parseDouble(searchRange);
        }catch(NumberFormatException E){
            return number = -1;
        }//end catch
        return number;
    }//end func
    
    /**
     * ask the type of investment from the user
     */
    public static void askType(){
        //ask the user for the type
        boolean flag = false;
        do{
            //print and ask option
            System.out.println("1. Stock");
            System.out.println("2. MutualFund");
            System.out.print("Enter Option: ");
            menu = scan.nextLine();
            if(menu.equalsIgnoreCase("Stock")){
                flag = true;
            }else if(menu.equalsIgnoreCase("MutualFund")){
                flag = true;
            }else if(menu.equalsIgnoreCase("1")){
                flag = true;
            }else if(menu.equalsIgnoreCase("2")){
                flag = true;
            }//end if
        }while(flag == false);
    }//end if

    /**
     * option 1
     */
    public static void option1(){
        //print and ask option
        askType();
        //option for stock and mutual fund
        if(menu.equalsIgnoreCase("stock") || menu.equals("1")){
            askSymbol();
            askName();
            askQuantity();
            askPrice();
            //check if it exist
            int found = 0;
            for(int x=0; x<portfolio.list.size(); x++){
                if (portfolio.list.get(x).getSymbol().equalsIgnoreCase(symbol) && portfolio.list.get(x) instanceof Stock){
                    System.out.println("Found existing symbol");
                    System.out.println("Buying more existing investment...");
                    portfolio.getStock(x).buy(price, quantity);
                    found = 1;
                    break;
                }//end if
            }//end for
            if(found == 0){
                //add the stock list
                portfolio.addStock(symbol, name, quantity, price); 
                addHash(name, portfolio.list.size());
            }//end if
        }else if(menu.equalsIgnoreCase("mutualfund") || menu.equals("2")){
            askSymbol();
            askName();
            askQuantity();
            askPrice();                  
            //check if it exist
            int found = 0;
            for(int x=0; x<portfolio.list.size(); x++){
                if (portfolio.list.get(x).getSymbol().equalsIgnoreCase(symbol) && portfolio.list.get(x) instanceof MutualFund){
                    System.out.println("Buying existing investment...");
                    portfolio.getMutualFund(x).buy(price, quantity);
                    found = 1;
                    break;
                }//end if
            }//end for
            if(found == 0){
                //add the mutual list
                portfolio.addMutual(symbol, name, quantity, price); 
                addHash(name, portfolio.list.size());
            }//end if
        }//end if
    }//end func
    
    /**
     * option 2
     */
    public static void option2(){
        //ask which list you want to sell
        askType();        
        //option choice to sell
        if(menu.equalsIgnoreCase("stock") || menu.equals("1")){
            //declare var
            int found = 0;
            //ask user input
            askSymbol();
            //search for the stock
            for(int x = 0; x < portfolio.list.size();x++){
                if(portfolio.list.get(x).getSymbol().equalsIgnoreCase(symbol) && portfolio.list.get(x) instanceof Stock){
                    //symbol is found, print the info
                    System.out.println("Symbol found...");
                    portfolio.getStock(x).toString();
                    askQuantity();
                    askPrice();
                    //sell the stock
                    portfolio.getStock(x).sell(quantity);
                    found = 1;
                    if(quantity >= portfolio.getStock(x).getQuantity()){
                        portfolio.list.remove(x);
                        removeUpdateHash();
                    }//end if
                }//end if
            }//end list
            if(found != 1){
                System.out.println("COULD NOT FIND SYMBOL");
            }else{
                System.out.println("Selling " + quantity);
            }//end if
        }else if(menu.equalsIgnoreCase("mutualfund") || menu.equals("2")){
            //declare var
            int found = 0;
            //ask user input
            askSymbol();
            //search for the symbol
            for(int x = 0; x < portfolio.list.size();x++){
                if(portfolio.list.get(x).getSymbol().equalsIgnoreCase(symbol) && portfolio.list.get(x) instanceof MutualFund){
                    //symbol is found, print the info
                    System.out.println("Symbol found...");
                    portfolio.getMutualFund(x).toString();
                    askQuantity();
                    askPrice();
                    //sell the mutualfund
                    portfolio.getMutualFund(x).sell(quantity);
                    found = 1;
                    if(quantity >= portfolio.getMutualFund(x).getQuantity()){
                        portfolio.list.remove(x);
                        removeUpdateHash();
                    }//end if
                }//end if
            }//end list
            if(found != 1){
                System.out.println("COULD NOT FIND SYMBOL");
            }else{
                System.out.println("Selling " + quantity);
            }//end if
        }//end if
    }//end func
       
    /**
     * option 3
     */
    public static void option3(){
        //check the if there is any investment in the list
        if(portfolio.list.size() != 0){
            //check if there is a stock in the list
            if(portfolio.list.get(0).getNumberOfStock() != 0){
                //goes through the list and updat the stock price
                for(int x = 0; x < portfolio.list.size();x++){
                    if(portfolio.list.get(x) instanceof Stock){
                        //ask for the price
                        do{
                            System.out.print("Enter the new updated price for (" + portfolio.getStock(x).getName() + ") stock: ");
                            input = scan.nextLine();
                            price = tryCatchDouble(input);
                        }while(price < x);
                        //update the new price
                        portfolio.getStock(x).priceChanged(price);
                    }//end if
                }//end for
            }//end if
            //check if there is mutualfund in the list
            if(portfolio.list.get(0).getNumberOfMutualFund() != 0){
                //goes through the mutual list and update the price
                for(int x = 0; x < portfolio.list.size();x++){
                    if(portfolio.list.get(x) instanceof MutualFund){
                        //ask for the price
                        do{
                            System.out.print("Enter the new updated price for (" + portfolio.getMutualFund(x).getName() + ") mutual fund: ");
                            input = scan.nextLine();
                            price = tryCatchDouble(input);
                        }while(price < x);
                        //update the new price
                        portfolio.getMutualFund(x).priceChanged(price);
                    }//end if
                }//end for
            }//end if
        }else{
            System.out.println("No investment available");
        }//end if
    }//end func
            
    /**
     * option 4
     */
    public static void option4(){
        DecimalFormat decimalGain = new DecimalFormat();
        decimalGain.setMaximumFractionDigits(2);
        double totalGain = 0;
        //add all the gain in the list
        if(portfolio.list.size() != 0){
            if(portfolio.list.get(0).getNumberOfStock() != 0){
                for(int x=0; x<portfolio.list.size();x++){
                    if(portfolio.list.get(x) instanceof Stock){
                        totalGain = totalGain + portfolio.getStock(x).getGain();           
                        System.out.println("Your gain for Stock is " + decimalGain.format(portfolio.getStock(x).getGain()));
                    }else if(portfolio.list.get(x) instanceof MutualFund){
                        totalGain = totalGain + portfolio.getMutualFund(x).getGain();
                    System.out.println("Your gain for Mutual is " + decimalGain.format(portfolio.getMutualFund(x).getGain()));
                    }//end if
                }//end for
            }//end if
        }//end if
        System.out.println("Your total gain " + decimalGain.format(totalGain));
    }//end func
    
    /**
     * option 5
     */
    public static void option5(){
        //decalre var
        double inputPrice;
        found = false;
        //enter symbol
        System.out.print("Enter Symbol: ");
        symbol = scan.nextLine();
        //enter name
        System.out.print("Enter KeyWord: ");
        name = scan.nextLine();
        //enter the price
        inputPrice = askPriceForSearch();
        //check if there is an investment in the list
        //to prevent null exception
        if(portfolio.list.size() != 0){
            //find the inverstment in the listS
            System.out.println("Total number of stocks in portfolio: " + portfolio.list.get(0).getNumberOfStock());
            System.out.println("Total number of mutual funds in portfolio: " + portfolio.list.get(0).getNumberOfMutualFund());
            System.out.println("Searching...");
            if(portfolio.list.get(0).getNumberOfStock() != 0){
                //declare var
                int match = 0;
                //check for stock the list
                
                for(int x = 0; x < portfolio.list.size();x++){
                    if(portfolio.list.get(x) instanceof Stock){
                        
                        //check for the are a match
                        String[] splitName = name.toLowerCase().split(" +");
                        
                        //get the index of the hash and print if match
                        try{
                           for(int y=0; y < splitName.length; y++) {
                               if(hash.get(splitName[y]).contains(x+1)){
                                   match++;
                               }//end if
                           }//end if
                        }catch(Exception e){
                            //getting a hash key that doesnt exist, so no match
                            match = 0;
                        }//end try

                        //for debuging
                        //System.out.println("S: " + symbol + "N: " + name + "IP: " + inputPrice);

                        //check print the name if it match
                        if(symbol.equals("") == true && name.equals("") == true && searchRange.equals("") == true){
                            for(int y = 0; y<portfolio.list.size(); y++){
                                if(portfolio.list.get(y) instanceof Stock){
                                    portfolio.getStock(y).toString();
                                    found = true;
                                }//end if
                            }//end if
                            break;
                        }else if(match >= splitName.length && searchRange.equals("") == true && symbol.equals("") == true){
                            portfolio.getStock(x).toString();
                            found = true;
                        }else if(symbol.equalsIgnoreCase(portfolio.getStock(x).getSymbol()) && searchRange.equals("") == true && name.equals("") == true){
                            portfolio.getStock(x).toString();
                            found = true;
                        }else if(symbol.equals("") == true && name.equals("") == true && searchRange.equals("") != true){
                            checkRangeValue(x);
                        }else if(match >= splitName.length && searchRange.equals("") != true && symbol.equals("") == true){ 
                            checkRangeValue(x);
                        }else if(symbol.equalsIgnoreCase(portfolio.getStock(x).getSymbol()) && searchRange.equals("") != true && name.equals("") == true){
                            checkRangeValue(x);
                        }else if(symbol.equalsIgnoreCase(portfolio.getStock(x).getSymbol()) && searchRange.equals("") == true && match >= splitName.length){
                            portfolio.getStock(x).toString();
                            found = true;
                        }else if(symbol.equalsIgnoreCase(portfolio.getStock(x).getSymbol()) && searchRange.equals("") != true && match >= splitName.length){
                            checkRangeValue(x);
                        }//end if
                        //reset match
                        match = 0;
                    }//end if
                }//end for
            }//end if

            if(portfolio.list.get(0).getNumberOfMutualFund() != 0){
                //declare var
                int match = 0;
                //check for stock the list
                for(int x = 0; x < portfolio.list.size();x++){ 
                    if(portfolio.list.get(x) instanceof MutualFund){
                        //check for the are a match
                        String[] splitName = name.toLowerCase().split(" +");
                        
                        //get the index of the hash and print if match
                        try{
                           for(int y=0; y < splitName.length; y++) {
                               if(hash.get(splitName[y]).contains(x+1)){
                                   match++;
                               }//end if
                           }//end if
                        }catch(Exception e){
                            //getting a hash key that doesnt exist, so no match
                            match = 0;
                        }//end try

                        //for debuging
                        //System.out.println("S: " + symbol + "N: " + name + "IP: " + inputPrice);

                        //check print the name if it match
                        if(symbol.equals("") == true && name.equals("") == true && searchRange.equals("") == true){
                            for(int y = 0; y<portfolio.list.size(); y++){
                                if(portfolio.list.get(y) instanceof MutualFund){
                                    portfolio.getMutualFund(y).toString();
                                    found = true;
                                }//end if
                            }//end if
                            break;
                        }else if(match >= splitName.length && searchRange.equals("") == true && symbol.equals("") == true){
                            portfolio.getMutualFund(x).toString();
                            found = true;
                        }else if(symbol.equalsIgnoreCase(portfolio.getMutualFund(x).getSymbol()) && searchRange.equals("") == true && name.equals("") == true){
                            portfolio.getMutualFund(x).toString();
                            found = true;
                        }else if(symbol.equals("") == true && name.equals("") == true && searchRange.equals("") != true){
                            checkRangeValue(x);
                        }else if(match >= splitName.length && searchRange.equals("") != true && symbol.equals("") == true){ 
                            checkRangeValue(x);
                        }else if(symbol.equalsIgnoreCase(portfolio.getMutualFund(x).getSymbol()) && searchRange.equals("") != true && name.equals("") == true){
                            checkRangeValue(x);
                        }else if(symbol.equalsIgnoreCase(portfolio.getMutualFund(x).getSymbol()) && searchRange.equals("") == true && match >= splitName.length){
                            portfolio.getMutualFund(x).toString();
                            found = true;
                        }else if(symbol.equalsIgnoreCase(portfolio.getMutualFund(x).getSymbol()) && searchRange.equals("") != true && match >= splitName.length){
                            checkRangeValue(x);
                        }//end if
                        //reset match
                        match = 0;
                    }//end if
                }//end for
            }//end if
            //if nothing found then print to the user
            if(found == false){
                System.out.println("Cannot find what you are looking for.");
            }//end if
        }else{
            System.out.println("No investment in the portfolio...");
        }//end if
    }//end func
    
    /**
     * save all the data to the the fileName
     * @param fileName of the data
     */
    public static void savePortfolio(String fileName){
        // Write to a file
        BufferedWriter writer;
        try{
            System.out.println("***************************************");
            System.out.println("Saving...");
            writer = new BufferedWriter(new FileWriter(fileName));
            for(int x = 0; x < portfolio.list.size(); x++){
                if(portfolio.list.get(x) instanceof Stock){
                    writer.write(portfolio.getStock(x).data());
                    //print what is saving for debug
                    //System.out.println(portfolio.getStock(x).data());
                }else if(portfolio.list.get(x) instanceof MutualFund){
                    writer.write(portfolio.getMutualFund(x).data());
                    //print what is saving for debug
                    //System.out.println(portfolio.getMutualFund(x).data());
                }//end if
                writer.newLine();
            }//end if
            System.out.println("Saved.");
            System.out.println("***************************************");
            writer.close();
        } catch(IOException e){
            System.out.println("Failed to write to the file.");
            System.out.println("Please follow the instruction carefully.");
        }//end try
    }//end func
    
    /**
     * load the file to the fileName
     * @param fileName of the data
     */
    public static void loadPortfolio(String fileName){
        // Read the file
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String symbol = "";
            String name = "";
            int quantity = 0;
            int index = 0;
            double price = 0;
            //load the while until the end of the lsit
            System.out.println("***************************************");
            System.out.println("Loading...");
            while (line != null){
                String[] data = line.split("@");
                
                //for debug
                //System.out.println(line);
                /*for(int x=0; x < data.length; x++){
                    System.out.println("Split: " + data[x]);
                }*/
                
                //convert string to int or double
                symbol = data[1];
                name = data[2];
                quantity = Integer.parseInt(data[3]);
                price = Double.parseDouble(data[4]);
                
                //load data for stock
                if(data[0].equals("s")){
                   portfolio.addStock(symbol, name, quantity, price);
                   addHash(name, portfolio.list.size());
                   //print what its loading
                   //portfolio.getStock(index).toString();
                }//en if 
                //load data for mutual
                if(data[0].equals("m")){
                   portfolio.addMutual(symbol, name, quantity, price);
                   addHash(name, portfolio.list.size());
                   //portfolio.getMutualFund(index).toString();
                }//end if
                index++;
                line = reader.readLine();
            }//end while
            System.out.println("Loaded.");
            System.out.println("***************************************");
            reader.close();
        } catch(IOException e){
            System.out.println("No file detected.");
        }//end try
    }//end if
  
    /**
     * add a hash
     * @param key the string of the sentence
     * @param index the index that is that word exist on the array
     */
    public static void addHash(String key, int index){
        //split the search in each words
        String split[] = key.toLowerCase().split(" ");
        
        //add all the index to all to hash
        for(int x = 0; x < split.length; x++){
            //set a new array list
            ArrayList<Integer> arrayIndex = new ArrayList<Integer>();
            try{
                //copy the array
                for(int y = 0; y < hash.get(split[x]).size(); y++){
                    arrayIndex.add(hash.get(split[x]).get(y));
                }//end for
            }catch(Exception E){
                //skip the the copy and move on    
            }//end try
            //for debug
            //System.out.println("arrayIndex: " + arrayIndex);
            //add the new index to array
            arrayIndex.add(index);
            hash.put(split[x], arrayIndex);
            //for debug
            //System.out.println(split[x] + hash.get(split[x]));
        }//end if
    }//end method
    
    /**
     * method to refresh the hash
     */
    public static void removeUpdateHash(){
        //remove the hash
        hash.clear();
        //restore the hash
        for(int x=0; x < portfolio.list.size();x++){
            String stringBuffer = "";
            stringBuffer = portfolio.list.get(x).getName();
            addHash(stringBuffer, x+1);
        }//end for
    }//end if

    /**
     * check check the range value
     * @param x for the element of the list
     */
    public static void checkRangeValue(int x){
        try{
            //if it is in the price range then print
            if(searchRange.matches(".*\\d+.*-.*\\d+.*")){
                String[] split = searchRange.split("-");
                //System.out.println("Split 1: " + split[0]);
                //System.out.println("Split 2: " + split[1]);
                double num1 = Double.parseDouble(split[0]);
                double num2 = Double.parseDouble(split[1]);
                if(portfolio.list.get(x) instanceof Stock){
                    if(num1 <= portfolio.getStock(x).getPrice() && portfolio.getStock(x).getPrice() <= num2){
                        portfolio.getStock(x).toString();
                        found = true;
                    }//end if
                }else if(portfolio.list.get(x) instanceof MutualFund){
                    if(num1 <= portfolio.getMutualFund(x).getPrice() && portfolio.getMutualFund(x).getPrice() <= num2){
                        portfolio.getMutualFund(x).toString();
                        found = true;
                    }//end if
                }//end if
            }else if(searchRange.matches("-.*\\d+.*")){
                String[] split = searchRange.split("-");
                //System.out.println("Split 1: " + split[0]);
                //System.out.println("Split 2: " + split[1]);
                double num2 = Double.parseDouble(split[1]);
                if(portfolio.list.get(x) instanceof Stock){
                    if(portfolio.getStock(x).getPrice() <= num2){
                        portfolio.getStock(x).toString();
                        found = true;
                    }//end if
                }else if(portfolio.list.get(x) instanceof MutualFund){
                    if(portfolio.getMutualFund(x).getPrice() <= num2){
                        portfolio.getMutualFund(x).toString();
                        found = true;
                    }//end if
                }//end if
            }else if(searchRange.matches(".*\\d+.*-")){
                String[] split = searchRange.split("-");
                //System.out.println("Split 1: " + split[0]);
                double num1 = Double.parseDouble(split[0]);
                if(portfolio.list.get(x) instanceof Stock){
                    if(portfolio.getStock(x).getPrice() >= num1){
                        portfolio.getStock(x).toString();
                        found = true;
                    }//end if
                }else if(portfolio.list.get(x) instanceof MutualFund){
                    if(portfolio.getMutualFund(x).getPrice() >= num1){
                        portfolio.getMutualFund(x).toString();
                    }//end if
                }//end if
            }else if(searchRange.matches(".*\\d+.*")){
                String[] split = searchRange.split("-");
                //System.out.println("Split 1: " + split[0]);
                double num1 = Double.parseDouble(split[0]);
                if(portfolio.list.get(x) instanceof Stock){
                    if(portfolio.getStock(x).getPrice() == num1){
                        portfolio.getStock(x).toString();
                        found = true;
                    }//end if
                }else if(portfolio.list.get(x) instanceof MutualFund){
                    if(portfolio.getMutualFund(x).getPrice() == num1){
                        portfolio.getMutualFund(x).toString();
                        found = true;
                    }//end if
                }//end if
            }//end if
        }catch(Exception e){
            System.out.println("WRONG FORMAT FOR RANGE");
        }//end if
    }//end func5
    
}//end class