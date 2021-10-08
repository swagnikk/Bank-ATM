import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class BankingAppl {
    public static void main(String[] args){
        //DRIVER PROGRAM
        //all these operations happen in the class bankAccount

        HashMap<Integer,String> bankDetail = new HashMap<Integer,String>(Map.of(1234,"James", 2341,"Danny",3412,"Rohit"));//Creating HashMap
        HashMap<Integer,String> custDetail = new HashMap<Integer,String>(Map.of(1234,"SBI23886", 2341,"BOI26125",3412,"IB515426"));//Creating HashMap

        bankDetail.put(4231,"Leo");
        bankDetail.put(4123,"Dennis");
        bankDetail.put(3421,"Brendon");

        custDetail.put(4231,"UB875846");
        custDetail.put(4123,"ICI85195");
        custDetail.put(3421,"CB516674");

        System.out.println("ENTER PIN: ");
        int pin = (new Scanner(System.in)).nextInt();

        if ( bankDetail.containsKey(pin) ) {
            bankAccount obj1 = new bankAccount(bankDetail.get(pin),custDetail.get(pin));
            obj1.showMenu();
        }
        else{
            System.out.println("INVALID PIN");
        }
    }
}

class bankAccount{
    int balance, prevTransactions;
    String customerID, customerName;

    //we will need these values for displaying the welcome message
    bankAccount(String cname, String cid){
        customerName = cname;
        customerID = cid;
    }

    void deposit(int amount){
        if (amount > 0){
            balance += amount;
            prevTransactions -= amount;
        }
    }

    void withdraw(int amount){
        if (amount > 0){
           balance -= amount;
           prevTransactions = -amount;
        }
    }

    void showMenu(){
        ArrayList<Integer> prevTransact = new ArrayList<>();
        char option = '\0'; //initialize char with some value
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome "+customerName);
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
                    System.out.println("Balance: "+balance);
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
                            System.out.println(prevTransact.get(i));
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