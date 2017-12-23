/*Name: Vicky Mohamamd
 *ID: 0895381
 */
package stock;
import java.text.DecimalFormat;

/**
 * a super class for the investment
 * @author vicky mohammad
 */
public class Investment {
    //decalre variable
    private String symbol, name;
    private int quantity = 0;
    private double price = 0;
    protected static int numberOfStock = 0;
    protected static int numberOfMutualFund = 0;
    //set object of 2 decimal format
    DecimalFormat twoDecimal = new DecimalFormat();
    
    /**
     * a constructor to create the super class
     * @param symbol to initialize for the stock
     * @param name to initialize for the stock
     * @param quaintity to initialize for the stock
     * @param price to initialize for the stock
     */
    public Investment(String symbol, String name, int quaintity, double price){
        //add books
        this.symbol = symbol;
        this.name = name;
        this.quantity = quaintity;
        this.price = price;
    }//end func
    
    /**
     * get the number of stocks 
     * @return the number of stocks
     */
    public int getNumberOfStock(){
        return numberOfStock;
    }//end func
    
    /**
     * get the number of mutual fund
     * @return get the number of mutual fund
     */
    public int getNumberOfMutualFund(){
        return numberOfMutualFund;
    }//end func
    
    /**
     * function to get symbol of the object
     * @return symbol of the object
     */
    public String getSymbol(){
        return symbol;
    }//end func
    
    /**
     * function to get the name
     * @return return the name of the stock
     */
    public String getName(){
        return name;
    }//end func
    
    /**
     * get the quantity of the class
     * @return the quantity of the stock
     */
    public int getQuantity(){
        return quantity;
    }//end func
    
    /**
     * function to get price
     * @return the price of the stock
     */
    public double getPrice(){
        return price;
    }//end func
    
    /**
     * set the price
     * @param price the price to be set
     */
    protected void setPrice(double price){
        this.price = price;
    }//end func
    
    /**
     * set the quantity of the investment
     * @param quantity that is to be set
     */
    protected void setQuantity(int quantity){
        this.quantity = quantity;
    }//end func
    
    /**
     * set the symbol of the investment
     * @param symbol that is to be set
     */
    protected void setSymbol(String symbol){
        this.symbol = symbol;
    }//end func
    
    /**
     * set the name of the investment
     * @param name that is to be set
     */
    protected void setName(String name){
        this.name = name;
    }//end func
    
    /**
     * check if the symbol is equal
     * @param otherObject that is being compared
     * @return the symbol
     */
    @Override public boolean equals(Object otherObject){
        if (otherObject == null){       
            return false;     
        }else if (getClass() != otherObject.getClass()){         
            return false;     
        }else{
            Investment otherInvestment = (Investment)otherObject;         
            return (symbol.equals(otherInvestment.symbol));//return true 
        }//end if
    }//end func
    
    /**
     * print the output
     * @return return the string of the stock
     */
    @Override public String toString(){
        twoDecimal.setMaximumFractionDigits(2);
        //display
        System.out.println("*************************************");
        System.out.println("Symbol: " + symbol);
        System.out.println("Name: " + name);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: $" + twoDecimal.format(price));
        System.out.println("*************************************");
        return String.format("\nSymbol: " + symbol +
              "\nName: " + name +
              "\nQuanity: " + quantity +
              "\nPrice: $" + price);
    }//end func
}//end class
