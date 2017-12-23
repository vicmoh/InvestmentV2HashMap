/*Name: Vicky Mohamamd
 *ID: 0895381
 */
package stock;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * a class for array list of investment
 * to make thing easier 
 * @author vicky mohammad
 */
public class Portfolio {
    //declare var
    ArrayList<Investment> list = new ArrayList<Investment>();
    
    /**
     * function to be able to get the methods of stock in array
     * by casting the array of the object
     * @param x the element of the list
     * @return the stock of the list
     */
    public Stock getStock(int x){
        return (Stock)list.get(x);
    }//end func
    
    /**
     * function to be able to get the methods of mutual in array
     * by casting the array of the object
     * @param x the element of the list
     * @return the mutual fund
     */
    public MutualFund getMutualFund(int x){
        return (MutualFund)list.get(x);
    }//end func
    
    /**
     * add a new stock to the portfolio
     * @param symbol of the object
     * @param name of the object
     * @param quaintity of the object
     * @param price of the object
     */
    public void addStock(String symbol, String name, int quaintity, double price){
        list.add(new Stock(symbol, name, quaintity, price));
    }//end func
    
    /**
     * add a mutual fund to the portfolio
     * @param symbol of the object
     * @param name of the object
     * @param quaintity of the object
     * @param price of the object
     */
    public void addMutual(String symbol, String name, int quaintity, double price){
        list.add(new MutualFund(symbol, name, quaintity, price));
    }//end func
    
    /**
     * print all the list
     */
    public void toStringList(){
        //set object of 2 decimal format
        DecimalFormat twoDecimal = new DecimalFormat();
        twoDecimal.setMaximumFractionDigits(2);
        //display book 
        for(int x = 0; x < list.size();x++){
            if(list.get(x) instanceof Stock){
                Stock stock = (Stock)list.get(x);
                stock.toString();
            }else{
                MutualFund mutual= (MutualFund)list.get(x);
                mutual.toString();
            }//end if
        }//end for
    }//end func
}//end class
