/*Name: Vicky Mohamamd
 *ID: 0895381
 */
package stock;
import java.text.DecimalFormat;

/**
 * class for stock
 * @author Vicky Mohammad
 */
public class Stock extends Investment {
    //decalre variables
    private double bookValue = 0;
    private double gain = 0;
    public static final double STOCK_FEE = 9.99;
    DecimalFormat twoDecimal = new DecimalFormat();
    
    /**
     * a constructor to create a stock
     * @param symbol to initialize for the stock
     * @param name to initialize for the stock
     * @param quantity to initialize for the stock
     * @param price to initialize for the stock
     */
    public Stock(String symbol, String name, int quantity, double price){
        //add books
        super(symbol, name, quantity, price);
        this.bookValue = quantity * price + 9.99;
        super.numberOfStock = super.numberOfStock + 1;
    }//end func
    
    /**
     * get the book value
     * @return  return the book value of the stock
     */
    public double getBookValue(){
        return bookValue;
    }//end func
    
    /**
     * set new price
     * @param newPrice set the new price 
     */
    public void priceChanged(double newPrice){
        //set the new value when price is changed
        setPrice(newPrice);
        gain = (getQuantity() * newPrice - STOCK_FEE)- bookValue;
    }//end func
    
    /**
     * get the price
     * @return return the gain
     */
    public double getGain(){
        //update the new price
        priceChanged(getPrice());
        return gain;
    }//end func
    
    /**
     * calculate the stock when buying
     * @param price for calculating
     * @param quantity for calculation
     */
    public void buy(double price, int quantity ){
        //decalre var
        int totalPreQuantity;
        //calculate the buy
        totalPreQuantity = getQuantity();
        setQuantity(getQuantity() + quantity);
        setPrice(price);
        this.bookValue = bookValue * ((totalPreQuantity + (double)quantity)/totalPreQuantity);
        //count, and add the number of stock
        super.numberOfStock = super.numberOfStock + 1;
    }//end if
    
    /**
     * calculate for selling the stock
     * @param quantityToSell the amount to sell
     */
    public void sell(int quantityToSell){
        //declare var
        boolean printSell = false;
        double paymentRecieved = 0;
        twoDecimal.setMaximumFractionDigits(2);
        //check if the user is selling more then the quantity
        if(getQuantity() >= quantityToSell){
            //subtract the number of stock
            if(getQuantity() == quantityToSell){
                printSell = true;       
                numberOfStock = numberOfStock - 1;
            }//end if
            double totalPreQuantity;
            totalPreQuantity = getQuantity();
            setQuantity(getQuantity() - quantityToSell);
            bookValue = bookValue * ((totalPreQuantity - (double)quantityToSell)/totalPreQuantity);
            //payment recieved after sell
            paymentRecieved = (quantityToSell * getPrice()) - 9.99;
            System.out.println("Payment received "  + twoDecimal.format(paymentRecieved));
            //print if it is sold
            if(printSell == true){
                System.out.println("Selling all what you have...");
            }//end if
        }else{
            //remove the number of stock
            numberOfStock = numberOfStock - 1;
            //calculate and print
            paymentRecieved = (getQuantity() * getPrice()) - 9.99;
            System.out.println("QUANTITY OUT OF BOUND");
            System.out.println("Selling what you have...");
            System.out.println("Payment received "  + twoDecimal.format(paymentRecieved));
        }//end if
    }//end func
    
    /**
     * the data string that will be saved to a file
     * @return the string data to be saved to a file
     */
    public String data(){
        return ("s" + "@" + getSymbol() + "@" + getName() + "@" + getQuantity() + "@" + getPrice());
    }//end 
    
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
     * print the output of the stock
     * @return return the string of the stock
     */
    @Override public String toString(){
        twoDecimal.setMaximumFractionDigits(2);
        //display
        System.out.println("*************************************");
        System.out.println("Type: Stock");
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
