import java.util.*;
public class Main {
    public static void main(String[] args) {
        Main mainobject = new Main();
        mainobject.initiate();
    }
    void initiate() {
        try
        {
            Stock st = new Stock();
            Bill bill = new Bill();
            Validation valobject = new Validation();
            Scanner sc = new Scanner(System.in);
        int userOption, userStockOption, userBillOption;
        System.out.println("Welcome!");
            System.out.println("Please enter your username: ");
            String username = sc.next();
            System.out.println("Please enter password: ");
            String password = sc.next();
            while(!valobject.validateUser(username,password))
            {
                System.out.println("User name and password invalid! Please enter valid username and password to continue;");
                System.out.println("Please enter your username: ");
                username = sc.next();
                System.out.println("Please enter password: ");
                password = sc.next();
            }

            do {
            System.out.println("Please select any option!");
            System.out.println("1. Stock Management");
            System.out.println("2. Bill Entry");
            System.out.println("3. Exit");
            userOption = sc.nextInt();
            switch (userOption) {
                case 1:
                    do {
                        st.displayStockMenu();
                        userStockOption = sc.nextInt();
                        switch (userStockOption) {
                            case 1:
                                System.out.println("Enter number of stock details going to be entered");
                                int noOfStock = sc.nextInt();
                                for (int i = 0; i < noOfStock; i++) {
                                    System.out.println("Enter " + "item no " + (i + 1) + " details below:");
                                    System.out.println("Enter Item Code");
                                    String nameTemp = sc.next();
                                    while(st.searchStockExists(nameTemp)) {
                                        System.out.println("Code already exists! Please enter a unique code");
                                        nameTemp = sc.next();
                                    }
                                    System.out.println("Enter Item Description");
                                    sc.nextLine(); //To avoid scanner skipping next line problem
                                    String descTemp = sc.nextLine();
                                    System.out.println("Enter Item Quantity");
                                    float quantityTemp = sc.nextFloat();
                                    System.out.println("Enter Item Price /Kg");
                                    float priceTemp = sc.nextFloat();
                                    st.addStock(nameTemp, descTemp, priceTemp,quantityTemp);
                                }
                                st.displayStock();
                                break;
                            case 2:
                                System.out.println("Enter stock code to delete");
                                String stockCodeToDelete = sc.next();
                                st.removeStock(stockCodeToDelete);
                                st.displayStock();
                                break;
                            case 3:
                                System.out.println("1. Update Quantity \n 2. Update Price \n 3. Update Quantity and Price \n 4. Back");
                                int userUpdateInput = sc.nextInt();
                                if(userUpdateInput == 1) {
                                    System.out.println("Enter stock code to update quantity details");
                                    String stockCodeToUpdateQuantity = sc.next();
                                    System.out.println("Enter new quantity to update for " + stockCodeToUpdateQuantity);
                                    float quantityToBeUpdated = sc.nextFloat();
                                    st.updateStockQuantity(stockCodeToUpdateQuantity, quantityToBeUpdated);
                                    st.displayStock();
                                }
                                else if(userUpdateInput == 2)
                                {
                                    System.out.println("Enter stock code to update price details");
                                    String stockCodeToUpdatePrice = sc.next();
                                    System.out.println("Enter new price to update for " + stockCodeToUpdatePrice);
                                    float priceToBeUpdated = sc.nextFloat();
                                    st.updateStockPrice(stockCodeToUpdatePrice, priceToBeUpdated);
                                    st.displayStock();
                                }
                                else if(userUpdateInput == 3)
                                {
                                    System.out.println("Enter stock code to update both quantity and price details");
                                    String stockCodeToUpdateQuantityPrice = sc.next();
                                    System.out.println("Enter new quantity to update for " + stockCodeToUpdateQuantityPrice);
                                    float quantityToBeUpdated = sc.nextFloat();
                                    System.out.println("Enter new price to update for " + stockCodeToUpdateQuantityPrice);
                                    float priceToBeUpdated = sc.nextFloat();
                                    st.updateStockQuantityPrice(stockCodeToUpdateQuantityPrice,quantityToBeUpdated, priceToBeUpdated);
                                    st.displayStock();
                                }
                                break;
                            case 4:
                                System.out.println("Enter stock name to search: ");
                                String stockNameToSearch = sc.next();
                                st.searchStock(stockNameToSearch);
                                break;
                            case 5:
                                st.displayStock();
                                break;
                            case 6:st.displayEmptyStock();
                                break;
                                case 7:break;
                            default:
                                System.out.println("Enter valid value");
                        }
                    } while (userStockOption != 7);
                    break;
                case 2:
                    do {
                        bill.displayBillMenu();
                        userBillOption = sc.nextInt();
                        switch (userBillOption) {
                            case 1:
                                System.out.println("Enter Customer Mobile Number: ");
                                String customerNumber = sc.next();
                                while(!valobject.validateMobileNumber(customerNumber))
                                {
                                    System.out.println("Mobile Number is invalid! Please enter valid mobile number");
                                    customerNumber = sc.next();
                                }
                                System.out.println("Enter number of items to be entered for this bill");
                                int noOfItems = sc.nextInt();
                                bill.addBill(st,customerNumber, noOfItems);
                                break;
                            case 2:
                                System.out.println("Enter a bill number to delete");
                                int billNumberToDelete = sc.nextInt();
                                bill.deleteBill(billNumberToDelete);
                                break;
                            case 3:
                                bill.displaySearchMenu();
                                int searchMenuOption = sc.nextInt();
                                switch (searchMenuOption) {
                                    case 1:System.out.println("Enter a bill number to search: ");
                                        int billNumberToSearch = sc.nextInt();
                                        bill.searchByBill(billNumberToSearch);
                                        break;
                                    case 2:
                                    System.out.println("Enter a customer mobile number to search: ");
                                    String customerNumberToSearch = sc.next();
                                    bill.searchByCustomer(customerNumberToSearch);
                                    break;
                                    default:System.out.println("Enter a valid value");
                                }
                                break;
                            case 4:bill.displayAllBills();
                                break;
                            default: System.out.println("Enter a valid value");
                        }
                    } while (userBillOption != 5);
                    break;
                case 3:
                    break;
                default: System.out.println("Enter a valid value");
            }
        } while (userOption != 3);
        sc.close();
    }
        catch(Exception e)
        {
            System.out.println("Exception occurred!");
        }
    }
}