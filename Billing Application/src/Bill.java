import java.util.ArrayList;
import java.util.Scanner;
class Bill{
    int billNumberAuto = 1;
    int itemNumberAuto = 1;
    ArrayList<BillInformation> billArrayList = new ArrayList<BillInformation>();
    ArrayList<ItemInformation> itemArrayList = new ArrayList<ItemInformation>();
    Scanner scobject = new Scanner(System.in);
    void displayBillMenu()
    {
        System.out.println("Please select any option");
        System.out.println("1.Add a bill");
        System.out.println("2.Delete a bill");
        System.out.println("3.Search bill");
        System.out.println("4.Display All");
        System.out.println("5.Main Menu");
    }
    void displaySearchMenu()
    {
        System.out.println("Please select any option");
        System.out.println("1.Search by bill number");
        System.out.println("2.Search by customer mobile number");
        System.out.println("3.Main Menu");
    }
    void addBill(Stock st,String customer_number,int no_of_items)
    {
        itemNumberAuto = 1;
        float bill_sum_amount = 0.00f;
        for (int x = 0; x < no_of_items; x++) {
            System.out.println("Enter Item Code: ");
            String itemCode = scobject.next();

            while(!st.checkStockExistance(itemCode))
            {
                System.out.println("No such item exists! Please enter a valid item");
                itemCode = scobject.next();
            }
            String itemName = st.getStockName(itemCode);
            System.out.println("Enter Quantity: ");
            float itemQuantity = scobject.nextFloat();
            while(!st.checkStockAvailability(itemCode,itemQuantity))
            {
                System.out.println("Required quantity is not available! Please enter within the available quantity");
                itemQuantity = scobject.nextFloat();
            }
            float itemUnitPrice = st.findStockPrice(itemCode);
            System.out.println("Item Price: " + itemUnitPrice);
            float itemSubtotal = itemQuantity * itemUnitPrice;
            System.out.println("Sub total: " + itemSubtotal);
            itemArrayList.add(new ItemInformation(billNumberAuto,itemNumberAuto++,itemCode,itemName,itemQuantity,itemUnitPrice,itemSubtotal));
            for(int i=0;i<st.StockArraylist.size();i++)
            {
                if(st.StockArraylist.get(i).getItem_Name().compareTo(itemCode) == 0)
                {
                    float newQuantityTemp = st.StockArraylist.get(i).getItem_Quantity()-itemQuantity;
                    newQuantityTemp = newQuantityTemp < 0 ? 0 : newQuantityTemp;
                    st.StockArraylist.get(i).setItem_Quantity(newQuantityTemp);
                }
            }
            bill_sum_amount = bill_sum_amount + itemSubtotal;
        }
        billArrayList.add(new BillInformation(billNumberAuto++,customer_number,bill_sum_amount));
        System.out.println("Added successfully");
        displayBill(billNumberAuto-1);
    }
    void displayBill(int billNumber)
    {
        System.out.println("        -----------------------------------------------------------------------");
        System.out.printf("        %-20s%-20s%-20s%-20s\n","Bill Number" , "Customer Number" , "Bill Amount","Bill Date");
        for(int i=0;i<billArrayList.size();i++)
        {
            if(billArrayList.get(i).getBill_Number() == billNumber) {
                System.out.printf("        %-20s%-20s%-20s%-20s\n",billArrayList.get(i).getBill_Number(),billArrayList.get(i).getCustomer_Number(), billArrayList.get(i).getBill_Amount() , billArrayList.get(i).getBill_Date());
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n","Item Number","Item Code","Item Name" ,"Item Quantity" ,"Item Unit Price","Item sub Total");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                for (int j = 0; j < itemArrayList.size(); j++) {
                    if(itemArrayList.get(j).getBill_Number() == billNumber)
                        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n",itemArrayList.get(j).getItem_Number(),itemArrayList.get(j).getItem_Code(),itemArrayList.get(j).getItem_Name(),itemArrayList.get(j).getItem_Quantity(), itemArrayList.get(j).getItem_Price() , itemArrayList.get(j).getItem_Subtotal());
                }
            }
        }
    }
    void displayAllBills()
    {
        for(int i=0;i<billArrayList.size();i++)
        {
            System.out.println("        -----------------------------------------------------------------------");
            System.out.printf("        %-20s%-20s%-20s%-20s\n","Bill Number" , "Customer Number" , "Bill Amount","Bill Date");
            System.out.printf("        %-20s%-20s%-20s%-20s\n",billArrayList.get(i).getBill_Number(),billArrayList.get(i).getCustomer_Number(),billArrayList.get(i).getBill_Amount(),billArrayList.get(i).getBill_Date());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n","Item Number","Item Code","Item Name" ,"Item Quantity" ,"Item Unit Price","Item sub Total");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            for(int j=0;j<itemArrayList.size() ;j++)
            {
                if(itemArrayList.get(j).getBill_Number() == billArrayList.get(i).getBill_Number())
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n",itemArrayList.get(j).getItem_Number(), itemArrayList.get(j).getItem_Code() ,itemArrayList.get(j).getItem_Name() ,itemArrayList.get(j).getItem_Quantity() , itemArrayList.get(j).getItem_Price(), itemArrayList.get(j).getItem_Subtotal());
            }
        }
    }
    void searchByBill(int bill_number_to_search)
    {
        boolean searchresult = false;
        System.out.println("        -----------------------------------------------------------------------");
        System.out.printf("        %-20s%-20s%-20s%-20s\n","Bill Number" , "Customer Number" , "Bill Amount","Bill Date");
        for(int i=0;i<billArrayList.size();i++) {
            if (billArrayList.get(i).getBill_Number() == (bill_number_to_search)) {
                searchresult = true;
                System.out.printf("        %-20s%-20s%-20s%-20s\n",billArrayList.get(i).getBill_Number() ,billArrayList.get(i).getCustomer_Number(),billArrayList.get(i).getBill_Amount() , billArrayList.get(i).getBill_Date());
                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n","Item Number","Item Code","Item Name" ,"Item Quantity" ,"Item Unit Price","Item sub Total");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                for(int j=0;j<itemArrayList.size() ;j++)
                {
                    if(itemArrayList.get(j).getBill_Number() == billArrayList.get(i).getBill_Number())
                        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n",itemArrayList.get(j).getItem_Number(), itemArrayList.get(j).getItem_Code() ,itemArrayList.get(j).getItem_Name() ,itemArrayList.get(j).getItem_Quantity() , itemArrayList.get(j).getItem_Price(), itemArrayList.get(j).getItem_Subtotal());
                }
            }
        }
        if(searchresult == false)
        {
            System.out.println("No record found");
        }
    }
    void searchByCustomer(String customer_number_to_search) {
        boolean result = false;
        System.out.println("        -----------------------------------------------------------------------");
        System.out.printf("        %-20s%-20s%-20s%-20s\n","Bill Number" , "Customer Number" , "Bill Amount","Bill Date");
        for (int i = 0; i < billArrayList.size(); i++) {
            if (billArrayList.get(i).getCustomer_Number().compareTo(customer_number_to_search) == 0) {
                result = true;
                System.out.printf("        %-20s%-20s%-20s%-20s\n",billArrayList.get(i).getBill_Number(),billArrayList.get(i).getCustomer_Number(),billArrayList.get(i).getBill_Amount(),billArrayList.get(i).getBill_Date());
                for(int j=0;j<itemArrayList.size() ;j++)
                {
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n","Item Number","Item Code","Item Name" ,"Item Quantity" ,"Item Unit Price","Item sub Total");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------");
                    if(itemArrayList.get(j).getBill_Number() == billArrayList.get(i).getBill_Number())
                        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n",itemArrayList.get(j).getItem_Number(), itemArrayList.get(j).getItem_Code() ,itemArrayList.get(j).getItem_Name() ,itemArrayList.get(j).getItem_Quantity() , itemArrayList.get(j).getItem_Price(), itemArrayList.get(j).getItem_Subtotal());
                }
            }
        }
        if(result == false)
            System.out.println("No record found!");
    }
    void deleteBill(int bill_number_to_delete) {
        for (int i = 0; i < billArrayList.size(); i++) {
            if(billArrayList.get(i).getBill_Number() == bill_number_to_delete)
            {
                billArrayList.remove(i);
                System.out.println("Removed successfully");
            }
        }
    }
}