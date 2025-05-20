import java.time.LocalDate;
import java.util.*;
import java.io.*;

class Transaction{
    String transactionType;
    String transactionCategory;
    int transactionAmount;
    LocalDate transactionDate;

    Transaction(String transactionType, String transactionCategory,int transactionAmount,LocalDate transactionDate){
        this.transactionType=transactionType;
        this.transactionCategory=transactionCategory;
        this.transactionAmount=transactionAmount;
        this.transactionDate=transactionDate;
    }

    @Override
    public String toString(){
        return "Transaction Type:"+transactionType+" Category:"+transactionCategory+" Amount:"+ transactionAmount+
                " Date:"+transactionDate;
    }
}
public class MainClass{
    public static void main(String args[]){
        int transactionCount, transactionAmount, month, monthlyExpense=0, monthlyIncome=0;
        String transactionType, transactionCategory, transactionDate, inputType;
        List<Transaction> transactionList=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);

        //Requesting Input type
        System.out.println("Would you like input data from File/Manually");
        inputType=scanner.next();

        if(inputType.equalsIgnoreCase("File")){
            try{
                System.out.println("Enter file path");
                String filePath=scanner.next();
                Scanner fileScanner=new Scanner(new File(filePath));
                while(fileScanner.hasNextLine()){
                    //System.out.println(fileScanner.nextLine());
                    String[] transactionValues=fileScanner.nextLine().split(" ");
                    transactionList.add(new Transaction(transactionValues[0],transactionValues[1],
                            Integer.parseInt(transactionValues[2]),LocalDate.parse(transactionValues[3])));
                }
            }   
            catch(FileNotFoundException exception){
                System.out.println("File not found. Recheck file path");
            }
        }
        else{
            System.out.println("Enter number of transactions");
            transactionCount=scanner.nextInt();
            for(int i=0;i<transactionCount;i++){
                System.out.println("Enter Details for transaction "+(i+1));
                System.out.println("Enter transaction type: Income/Expense");
                transactionType=scanner.next();

                //Taking category input for Income transactions
                if(transactionType.equalsIgnoreCase("Income")){
                    System.out.println("Enter transaction Category: Salary/Business");
                    transactionCategory=scanner.next();
                }
                //Taking category input for Expense transactions
                else{
                    System.out.println("Enter transaction Category: Food/Rent/Travel");
                    transactionCategory=scanner.next();
                }

                System.out.println("Enter transaction amount");
                transactionAmount=scanner.nextInt();

                System.out.println("Enter transaction date yyyy-MM-dd");
                transactionDate=scanner.next();

                //Adding object to list
                transactionList.add(new Transaction(transactionType,transactionCategory,
                                    transactionAmount,LocalDate.parse(transactionDate)));
            }
        }   

        //Categorizing based on month
        System.out.println("Printing data based on month; Enter the month in digits");
        month=scanner.nextInt();
        for(int i=0;i<transactionList.size();i++){
            Transaction transaction=transactionList.get(i);
            if(transaction.transactionDate.getMonthValue()==month){
                if(transaction.transactionType.equalsIgnoreCase("Income"))
                    monthlyIncome+=transaction.transactionAmount;
                else
                    monthlyExpense+=transaction.transactionAmount;
                System.out.println(transaction);
            }
        }
        //Printing Monthly Summary
        System.out.println("Monthly Summary: Total Income: "+monthlyIncome+" Total Expenses: "+monthlyExpense+
        " Balance by end of month: "+(monthlyIncome-monthlyExpense));
    }
}
