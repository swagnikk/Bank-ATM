//import java.util.Map;
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.*;

public class BankingAppl {
    public static void main(String[] args){
        //DRIVER PROGRAM
        //all these operations happen in the class bankAccount
        HashMap<String,String> bankDetail = new HashMap<String,String>(Map.of("SBI2386","James","BOI2612","Danny","IB51542","Rohit"));//Creating HashMap
        HashMap<String,Integer> custDetail = new HashMap<String,Integer>(Map.of("SBI2386",1234,"BOI2612",2341,"IB51542",3412));//Creating HashMap

        bankDetail.put("UB87586","Leo");
        bankDetail.put("ICI8595","Dennis");
        bankDetail.put("CB51674","Brendon");

        custDetail.put("UB87586",4231);
        custDetail.put("ICI8595",4123);
        custDetail.put("CB51674",3421);

        System.out.println("ENTER ACCOUNT NUMBER: ");
        String accountNo = (new Scanner(System.in)).next();

        if ( custDetail.containsKey(accountNo) ) {
            System.out.println("ENTER PIN: ");
            int pin = (new Scanner(System.in).nextInt());
            if (custDetail.containsValue(pin)){
                bankAccount obj1 = new bankAccount(bankDetail.get(accountNo),accountNo);
                obj1.showMenu();
            }
            else{
                System.out.println("INVALID PIN");
            }
        }
        else{
            System.out.println("INVALID ACCOUNT NUMBER");
        }
    }
}

class bankAccount{
    int balance;
    String customerID, customerName;

    //we will need these values for displaying the welcome message
    bankAccount(String cname, String cid){
        customerName = cname;
        customerID = cid;
    }

    void deposit(int amount){
        if (amount > 0){
            balance += amount;
        }
    }

    void withdraw(int amount){
        if (amount > 0){
            balance -= amount;
            System.out.println("PLEASE COLLECT THE CASH AND YOUR CARD");
        }
    }

    void showMenu(){
        ArrayList<Integer> prevTransact = new ArrayList<>();
        char option = '\0'; //initialize char with some value
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome "+customerName+"!");
        System.out.println("Your Account ID: "+customerID+"\n");

        System.out.println("1: Check Balance");
        System.out.println("2: Deposit");
        System.out.println("3: Withdraw");
        System.out.println("4: Previous Transactions");
        System.out.println("5: Exit");

        do{
            System.out.println("--------------------------");
            System.out.println("SELECT AN OPTION: ");
            System.out.println("--------------------------");
            option = scanner.next().charAt(0);

            switch(option){
                case '1':{
                    System.out.println("\n--------------------------");
                    System.out.println("Balance: ₹"+balance);
                    System.out.println("--------------------------\n");
                    break;
                }
                case '2':{
                    System.out.println("\n--------------------------");
                    System.out.println("Enter an amount to deposit:");
                    System.out.println("--------------------------");
                    int amount1 = scanner.nextInt();
                    deposit(amount1);
                    break;
                }
                case '3':{
                    System.out.println("\n--------------------------");
                    System.out.println("Enter an amount to withdraw:");
                    System.out.println("--------------------------");

                    int amount2 = scanner.nextInt();
                    if (amount2 < balance){
                        System.out.println("Confirm withdrawal amount (Y/N)");
                        char confirm = scanner.next().charAt(0);
                        if (confirm == 'Y' || confirm == 'y'){
                            withdraw(amount2);
                            prevTransact.add(amount2);
                        }
                        else{
                            showMenu();
                        }
                    }
                    else{
                        System.out.println("\n--------------------------");
                        System.out.print("Withdrawal amount exceeds account balance.\n");
                        showMenu();
                        System.out.println("--------------------------");
                    }
                    break;
                }
                case '4':{
                    System.out.println("\n--------------------------");
                    System.out.println("Previous Transactions: ");
                    System.out.println("--------------------------");
                    if (prevTransact.size() == 0){
                        System.out.println("NO PREVIOUS TRANSACTION RECORDS");
                    }
                    else{
                        for (int i = 0; i < prevTransact.size(); i++) {
                            System.out.println("₹"+prevTransact.get(i));
                        }
                    }
                    break;
                }
                case '5':{
                    System.out.println("\n--------------------------");
                    System.out.println("THANK YOU FOR VISITING US!");
                    System.out.println("\n--------------------------");
                    break;
                }
                default:
                    System.out.println(("Invalid Option.\nPlease enter again"));
                    break;
            }
        } while(option != '5');
    }
}
