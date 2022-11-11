import java.util.ArrayList;
import java.util.Scanner;

class Stock {
    ArrayList<StockInformation> StockArraylist = new ArrayList<StockInformation>();
    Scanner scobj = new Scanner(System.in);
    Stock()
    {
//Adding some stock by default
        StockArraylist.add(new StockInformation("DHALLT","Toor Dhall",12.25f,100));
        StockArraylist.add(new StockInformation("DHALLM","Moong Dhall",50f,100));
        StockArraylist.add(new StockInformation("DHALLO","Orid Dhall",65f,100));
        StockArraylist.add(new StockInformation("R01","Rice Idly",195.50f,50));
        StockArraylist.add(new StockInformation("R02","Rice Ponni",84f,20));
        StockArraylist.add(new StockInformation("AR","Rava Anil",12f,10));
        StockArraylist.add(new StockInformation("NR","Rava Naga",84f,20));

    }
    void displayStockMenu() {
        System.out.println("Enter any option");
        System.out.println("1.Add");
        System.out.println("2.Delete");
        System.out.println("3.Update");
        System.out.println("4.Search");
        System.out.println("5.Display");
        System.out.println("6.Display empty stocks");
        System.out.println("7.Main Menu");
    }

    void addStock(String name,String desc,float price,float quantity) {

        StockArraylist.add(new StockInformation(name,desc,price,quantity));
        System.out.println("Added Successfully");
    }

    void removeStock(String stockCodeToDelete) {
        boolean result = false;
        for (int i = 0; i < StockArraylist.size(); i++) {
            if (StockArraylist.get(i).getItem_Name().compareTo(stockCodeToDelete) == 0) {
                result = true;
                System.out.println("Record found. Do you want to delete? yes/no");
                String deleteConfirm = scobj.next();
                if(deleteConfirm.compareTo("yes")==0) {
                    StockArraylist.remove(i);
                    System.out.println("Deleted successfully");
                }
            }
        }
        if(result == false)
        {
            System.out.println("No record found with code "+ stockCodeToDelete);
            System.out.println("Available records are");
        }
    }

    void displayStock() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-20s%-25s%-25s%-25s\n","Item Code","Item Description","Item Quantity","Item Price");
        System.out.println("---------------------------------------------------------------------------------");
        for(int i=0;i<StockArraylist.size();i++)
        {
            System.out.printf("%-20s%-25s%-25s%-25s\n",StockArraylist.get(i).getItem_Name(), StockArraylist.get(i).getItem_Description(),StockArraylist.get(i).getItem_Quantity(),StockArraylist.get(i).getItem_Price());
        }
    }
    void displayEmptyStock() {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-20s%-25s%-25s%-25s\n","Item Code","Item Description","Item Quantity","Item Price");
        System.out.println("---------------------------------------------------------------------------------");
        for(int i=0;i<StockArraylist.size();i++) {
            if (StockArraylist.get(i).getItem_Quantity() == 0) {
                System.out.printf("%-20s%-25s%-25s%-25s\n",StockArraylist.get(i).getItem_Name(), StockArraylist.get(i).getItem_Description(),StockArraylist.get(i).getItem_Quantity(),StockArraylist.get(i).getItem_Price());
            }
        }
    }

    void searchStock(String stockNameToSearch) {
        boolean result = false;
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-20s%-25s%-25s%-25s\n","Item Code","Item Description","Item Quantity","Item Price");
        System.out.println("---------------------------------------------------------------------------------");
    for(int j=0;j<StockArraylist.size();j++)
    {
        if(StockArraylist.get(j).getItem_Description().compareTo(stockNameToSearch)==0)
        {
            System.out.printf("%-20s%-25s%-25s%-25s\n",StockArraylist.get(j).getItem_Name(),StockArraylist.get(j).getItem_Description(),StockArraylist.get(j).getItem_Quantity(),StockArraylist.get(j).getItem_Price());
            result = true;
        }
    }
    if(result == false)
        System.out.println("No result found!");
}
    boolean searchStockExists(String stockCodeToSearch) {
        boolean result = false;
        for(int j=0;j<StockArraylist.size();j++)
        {
            if(StockArraylist.get(j).getItem_Name().compareTo(stockCodeToSearch)==0)
            {
                result = true;
            }
        }
return result;
    }
    void updateStockPrice(String stockCodeToUpdatePrice,float priceToBeUpdated) {
        boolean result = false;
        for (int k = 0; k < StockArraylist.size(); k++) {
            if (StockArraylist.get(k).getItem_Name().compareTo(stockCodeToUpdatePrice) == 0) {
                StockArraylist.get(k).setItem_Price(priceToBeUpdated);
                System.out.println("Price updated successfully");
                result = true;
            }
        }
        if(result == false)
                System.out.println("Record not found!");
    }
    void updateStockQuantity(String stockCodeToUpdateQuantity,float quantityToBeUpdated) {
        boolean result = false;
        for (int k = 0; k < StockArraylist.size(); k++) {
            if (StockArraylist.get(k).getItem_Name().compareTo(stockCodeToUpdateQuantity) == 0) {
                StockArraylist.get(k).setItem_Quantity(quantityToBeUpdated);
                System.out.println("Quantity updated successfully");
                result = true;
            }
        }
        if(result == false)
                System.out.println("Record not found!");
    }
    void updateStockQuantityPrice(String stockCodeToUpdateQuantity,float quantityToBeUpdated,float priceToBeUpdated) {
        boolean result = false;
        for (int k = 0; k < StockArraylist.size(); k++) {
            if (StockArraylist.get(k).getItem_Name().compareTo(stockCodeToUpdateQuantity) == 0) {
                StockArraylist.get(k).setItem_Quantity(quantityToBeUpdated);
                StockArraylist.get(k).setItem_Price(priceToBeUpdated);
                System.out.println("Quantity and Price updated successfully");
                result = true;
            }
        }
        if(result == false)
                System.out.println("Record not found!");
    }
    float findStockPrice(String itemCode)
        {
            float itemPrice = 0.00f;
            for(int j=0;j<StockArraylist.size();j++)
            {
                if(StockArraylist.get(j).getItem_Name().compareTo(itemCode)==0)
                {
                    itemPrice = StockArraylist.get(j).getItem_Price();
                }
            }
return itemPrice;
        }

    boolean checkStockAvailability(String itemCode,float itemQuantity)
    {
        boolean checkresult = false;
        for(int j=0;j<StockArraylist.size();j++)
        {
            if(StockArraylist.get(j).getItem_Name().compareTo(itemCode)==0 && StockArraylist.get(j).getItem_Quantity() >= itemQuantity) {
                checkresult = true;
            }
        }
        return checkresult;
    }

    boolean checkStockExistance(String itemCode)
    {
        boolean checkresult = false;
        for(int j=0;j<StockArraylist.size();j++)
        {
            if(StockArraylist.get(j).getItem_Name().compareTo(itemCode)==0) {
                checkresult = true;
            }
        }
        return checkresult;
    }
    String getStockName(String itemCode)
    {
        String itemName = "";
        for(int j=0;j<StockArraylist.size();j++)
        {
            if(StockArraylist.get(j).getItem_Name().compareTo(itemCode)==0) {
                itemName = StockArraylist.get(j).getItem_Description();
            }
        }
        return itemName;
    }
}

