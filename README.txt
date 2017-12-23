Student Information
####################
Name: Vicky Mohammad
ID: 0895381
Version: 2.0

An individual may have a portfolio that holds different 
kinds of investments and allows you to buy or sell investments, 
search for some investments, update prices, and compute the total 
gain of the portfolio.

How to run the program:
	----------------------------------------------------------------------------
    Running through NetBean:
	- this program was created through netbean
	- assuming that this runs on netbeans
	- first open netbeans
	- go to "run" and then click "set configuration" then "customize" on navbar
	- set the "command argument" with the file name such as "dataName.txt"
	----------------------------------------------------------------------------
    Running through command line:
    - go to the package directory "stock"
    - type "javac *.java" on bash to compile
    - exit bash and run type "cd .." to package folder parent of "stock"
    - type: "java stock.Menu data.txt" to run the program
    ----------------------------------------------------------------------------

Assumptions:
	- fei song said that we can use any data format as long as it work
    - when user input sell quantity more than the quantity it has
      it will sell all the remaining of that investment stated
    - the data type format is "type@nsymbol@name@quantity@price" per line per array element
      this data format is NOT MEANT TO BE READABLE TO USER


Limitation:
    - there must be an comand argument with a file name or the program will not run
    - the data file is parse with split @ delimiter FORMAT 
      and may not work with other data type or format
    - do not input any string that has the "@" sign or it will ruin the program
    - data file MUST be .txt