/*Name: Vicky Mohamamd
 *ID: 0895381
 */
package stock;
import java.text.DecimalFormat;

/**
 * class for mutual fund
 * @author Vicky Mohammad
 */
public class MutualFund extends Investment{
    //decalre variables
    private double bookValue = 0;
    private double gain = 0;
    public static final double MUTUAL_FEE = 45;
    DecimalFormat twoDecimal = new DecimalFormat();
    
    /**
     * constructor to create a new mutual 
     * @param symbol the symbol of the object
     * @param name the name of object
     * @param quantity the amount
     * @param price of the object
     */
    public MutualFund(String symbol, String name, int quantity, double price){
        super(symbol, name, quantity, price);
        this.bookValue = quantity * price;
        super.numberOfMutualFund = super.numberOfMutualFund + 1;
    }//end func
 
    /**
     * get book value of mutual fund
     * @return book value of the object
     */
    public double getBookValue(){
        return bookValue;
    }//end func
    
    /**
     * change the price the new price
     * @param newPrice of the object
     */
    public void priceChanged(double newPrice){
        //set the new value when price is changed
        setPrice(newPrice);
        gain = (getQuantity() * newPrice - MUTUAL_FEE)- bookValue;
    }//end func
    
    /**
     * get the gain of mutual fund
     * @return the gain of the object
     */
    public double getGain(){
        priceChanged(getPrice());
        return gain;
    }//end func
    
    /**
     * calculate the when buying 
     * @param price of the mutual
     * @param quantity of the mutual
     */
    public void buy(double price, int quantity ){
        //declare variables
        int totalPreQuantity;
        //calculate when buying
        totalPreQuantity = getQuantity();
        setQuantity(getQuantity() + quantity);
        setPrice(price);
        this.bookValue = bookValue * ((totalPreQuantity + (double)quantity)/totalPreQuantity);
        //count, and add the number of mutualFund
        super.numberOfMutualFund = super.numberOfMutualFund + 1;
    }//end if
    
    /**
     * calculate the quantity to sell
     * @param quantityToSell the amount 
     */
    public void sell(int quantityToSell){
        //decalre var
        boolean printSell = false;
        double paymentRecieved = 0;
        twoDecimal.setMaximumFractionDigits(2);
        //check if the the user is selling more than the quantity
        if(this.getQuantity() >= quantityToSell){
            //remove the number of mutualFund
            if(getQuantity() == quantityToSell){
                printSell = true;
                numberOfMutualFund = numberOfMutualFund - 1;
            }//end if
            double totalPreQuantity = getQuantity();
            setQuantity(getQuantity() - quantityToSell);
            this.bookValue = this.bookValue * ((totalPreQuantity - (double)quantityToSell)/totalPreQuantity);
            //payment recieved after sell
            paymentRecieved = (quantityToSell * getPrice()) - 45;
            System.out.println("Payment received " + twoDecimal.format(paymentRecieved));
            //print if it is sold
            if(printSell == true){
                System.out.println("Selling all what you have...");
            }//end if
        }else{
            //remove the number of stock
            numberOfMutualFund = numberOfMutualFund - 1;
            //payment recieved after sell
            paymentRecieved = (getQuantity() * getPrice()) - 45;
            System.out.println("QUANTITY OUT OF BOUND");
            System.out.println("Selling all what you have..");
            System.out.println("Payment received " + twoDecimal.format(paymentRecieved));
        }//end if
    }//end func
    
    /**
     * check if the are the same object
     * @param otherObject that is to be check
     * @return true if its the same
     */
    @Override public boolean equals(Object otherObject){
        if (otherObject == null){
            return false;     
        }else if (getClass() != otherObject.getClass()){  
            return false;     
        }else{
            return true;
        }//end if
    }//end func
    
    /**
     * the data string that will be saved to a file
     * @return the string data to be saved to a file
     */
    public String data(){
        return ("m" + "@" + getSymbol() + "@" + getName() + "@" + getQuantity() + "@" + getPrice());
    }//end 
    
    /**
     * a function to print out the mutual fund
     * @return return the string of the object
     */
    @Override public String toString(){
       //set object of 2 decimal format
       twoDecimal.setMaximumFractionDigits(2);
       //display
       System.out.println("*************************************");
       System.out.println("Type: Mutual Fund");
       System.out.println("Symbol: " + getSymbol());
       System.out.println("Name: " + getName());
       System.out.println("Quantity: " + getQuantity());
       System.out.println("Price: $" + twoDecimal.format(getPrice()));
       System.out.println("Book Value: $" + twoDecimal.format(bookValue));
       System.out.println("*************************************");
       return String.format("\nSymbol: " + getSymbol() +
              "\nName: " + getName() +
              "\nQuanity: " + getQuantity() +
              "\nPrice: $" + getPrice() +
              "\nStock value: $" + bookValue);
    }//end func
}//end class
