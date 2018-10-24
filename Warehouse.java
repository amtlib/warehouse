import java.io.IOException;
import java.util.*;

public class Warehouse implements java.io.Serializable {
    private static final long serialVersionUID = -5080612003712429459L;
    private Shelf[] allShelves = new Shelf[100];
    
    private Employee[] allEmployees = new Employee[50]; 
    private Product[] stock = new Product[1000]; 
    private transient FileAccess fileAccess = new FileAccess("baza");
    
    
    public Warehouse() {
    	Warehouse w = fileAccess.readWarehouse();
    	if(w != null) {
    		this.allEmployees = w.getAllEmployees();
    		this.stock = w.getStock();
    		this.allShelves = w.getAllShelves();    		
    	}
    }
    
    public Shelf[] getAllShelves() {
		return allShelves;
	}
    
	public void setAllShelves(Shelf[] allShelves) {
		this.allShelves = allShelves;
	}
	
	public Employee[] getAllEmployees() {
		return allEmployees;
	}
	
	public void setAllEmployees(Employee[] allEmployees) {
		this.allEmployees = allEmployees;
	}
	
	public Product[] getStock() {
		return stock;
	}
	
	public void setStock(Product[] stock) {
		this.stock = stock;
	}

    public FileAccess getFileAccess() {
		return fileAccess;
	}

	public void setFileAccess(FileAccess fileAccess) {
		this.fileAccess = fileAccess;
	}

	public int getNumberOfEmployees() { // in menu
        int count = 0;
        for (int i = 0; i < allEmployees.length; i++) {
            if (allEmployees[i] != null) count++;
        }
        return count;
    }

    public int getNumberOfShelves() { // in menu
        int count = 0;
        for (int i = 0; i < allShelves.length; i++) {
            if (allShelves[i] != null) count++;
        }
        return count;
    }

    public void showProductsShort() { // in menu
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] != null) {
                stock[i].showProductInfoShort();
            }
        }
    }

    public void showProductsLong() { // in menu
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] != null) {
                stock[i].showProductInfoLong();
            }
        }
    }

    public void showEmployeesShort() { // in menu
        for (int i = 0; i < allEmployees.length; i++) {
            if (allEmployees[i] != null) {
                allEmployees[i].showEmployeeInfoShort();
            }
        }
    }

    public void showEmployeesLong() { // in menu
        for (int i = 0; i < allEmployees.length; i++) {
            if (allEmployees[i] != null) {
                allEmployees[i].showEmployeeInfoLong();
            }
        }
    }

    public void addEmployee(String firstName, String lastName) { // in menu
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < allEmployees.length; i++) {
            if (allEmployees[i] == null) {
                allEmployees[i] = employee;
                fileAccess.saveWarehouse(this);
                return;
            }
        }
        System.out.println("You do not have space for a new employee. Please fire someone first.");
    }

    public void addProduct(String barcode, String name, double size, double weight) { // in menu
        // losowy regal
        Shelf shelf;
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i < allShelves.length; i++) {
            if (allShelves[i] != null) ids.add(i);
        }

        int rnd = new Random().nextInt(ids.size());
        shelf = allShelves[rnd];

        Product newProduct = new Product(barcode, name, size, weight, shelf);
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] == null) {
                stock[i] = newProduct;
                fileAccess.saveWarehouse(this);
                return;
            }
        }
        System.out.println("You do not have space for a new product. Please make some.");

    }

    public void addShelf() { // in menu
        Shelf shelf = new Shelf();
        for (int i = 0; i < allShelves.length; i++) {
            if (allShelves[i] == null) {
                allShelves[i] = shelf;       
                fileAccess.saveWarehouse(this);
                return;
            }
        }
        System.out.println("You do not have space for a new shelf");
    }

    public void releaseProduct(String name) { // releases all products with given name
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] != null) {
                if (stock[i].getName().equals(name)) {
                    stock[i] = null;
                    fileAccess.saveWarehouse(this);
                    return;
                }
            }
        }
        System.out.println("Product not found");
    }

    public Employee findEmployee(String firstName, String lastName) { // in menu
        for (int i = 0; i < allEmployees.length; i++) {
            if (allEmployees[i] != null) {
                if (allEmployees[i].getFirstName().toLowerCase().equals(firstName.toLowerCase()) && allEmployees[i].getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                    return allEmployees[i];
                }
            }
        }
        System.out.println("Employee not found");
        return null;
    }

    public Product getProductLargerAndHeavierThan(double size, double weight) { // in menu

        for (int i = 0; i < stock.length; i++) {
            if (stock[i] != null) {
                if (stock[i].getSize() > size && stock[i].getWeight() > weight) {
                    return stock[i];
                }
            }
        }
        System.out.println("Product not found");
        return null;
    }

    public void showProductsSorted() { // in menu

        Arrays.sort(stock, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                try {
                    return o1.getName().compareTo(o2.getName());
                } catch (Exception e) {
                    return 0;
                }
            }
        });
        showProductsShort();
    }


    public void showMainMenu() {
        System.out.println();
        System.out.println("Main menu. Please enter proper number");
        System.out.println("Enter \"exit\" to exit");
        System.out.println("To show this menu, press \"h\"");
        System.out.println("-*- Employees -*-");
        System.out.println("1) Number of employees");
        System.out.println("2) Show employees short");
        System.out.println("3) Show employees long");
        System.out.println("4) Find employee by name");
        System.out.println("5) Add new employee");
        System.out.println("6) Edit employee");
        System.out.println("-*- Shelves -*-");
        System.out.println("7) Number of shelves");
        System.out.println("8) Add shelf");
        System.out.println("9) Edit employee responsible for specified shelf");
        System.out.println("-*- Products -*-");
        System.out.println("10) Show products short");
        System.out.println("11) Show products long");
        System.out.println("12) Add product");
        System.out.println("13) Edit product");
        System.out.println("14) Release product");
        System.out.println("15) Find product larger and heavier than given input");
        System.out.println("16) Show products sorted");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();
        warehouse.showMainMenu();
        String userInput;
        boolean scannerError = false;
        while (true) {
            if (scannerError) {
                scannerError = false;
            } else {
                System.out.print("> ");
            }
            userInput = scanner.nextLine();
            if (userInput.equals("h")) {
                warehouse.showMainMenu();
            }
            if (userInput.equals("1")) { //Number of employees
                System.out.println("Number of employees: " + warehouse.getNumberOfEmployees());
            } else if (userInput.equals("2")) { //Show employees short
                warehouse.showEmployeesShort();
            } else if (userInput.equals("3")) { //Show employees long
                warehouse.showEmployeesLong();
            } else if (userInput.equals("4")) { //Find employee by name
                System.out.print("Please enter employee first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Please enter employee last name: ");
                String lastName = scanner.nextLine();
                Employee found = warehouse.findEmployee(firstName, lastName);
                if (found != null) {
                    found.showEmployeeInfoShort();
                } else {
                    System.out.println("Employee not found.");
                }
            } else if (userInput.equals("5")) { //Add new employee
                System.out.print("Please enter employee first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Please enter employee last name: ");
                String lastName = scanner.nextLine();
                warehouse.addEmployee(firstName, lastName);
            } else if (userInput.equals("6")) { // Edit employee
                System.out.print("Please enter employee first name: ");
                String firstName = scanner.nextLine();
                System.out.print("Please enter employee last name: ");
                String lastName = scanner.nextLine();
                Employee found = warehouse.findEmployee(firstName, lastName);
                if (found != null) {
                    System.out.println("I found");
                    found.showEmployeeInfoLong();
                    System.out.println("What you want to edit?");
                    System.out.println("1) First name");
                    System.out.println("2) Last name");

                    int selection = 0;

                    try {
                        System.out.print("> ");
                        selection = scanner.nextInt();

                        if (selection != 1 && selection != 2) {
                            System.out.println("Please enter number 1 or 2");
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println("Please enter proper number");
                        scannerError = true;
                        continue;
                    }

                    if (selection == 1) { // edit first name
                        System.out.print("Please enter new first name for this employee: ");
                        scanner.nextLine(); // clear buffer
                        found.setFirstName(scanner.nextLine());
                    } else { // edit last name
                        System.out.print("Please enter new last name for this employee: ");
                        scanner.nextLine(); // clear buffer
                        found.setLastName(scanner.nextLine());
                    }
                    warehouse.fileAccess.saveWarehouse(warehouse);
                    System.out.println("Done");
                }
            } else if (userInput.equals("7")) { //Number of shelves
                System.out.println("Number of shelves: " + warehouse.getNumberOfShelves());
            } else if (userInput.equals("8")) { //Add shelf
                warehouse.addShelf();
                System.out.println("Done");
            } else if (userInput.equals("9")) { // edit employee responsible for specific shelf
                int[] shelvesIds = new int[warehouse.getNumberOfShelves()];
                int counter = 0;
                for (int i = 0; i < warehouse.allShelves.length; i++) {
                    if (warehouse.allShelves[i] != null) {
                        shelvesIds[counter++] = warehouse.allShelves[i].getId();
                    }
                }
                System.out.println("Available shelves (IDs):");
                for (int i = 0; i < shelvesIds.length; i++) {
                    System.out.println("Shelf with id " + shelvesIds[i]);
                }
                System.out.print("Your selection: ");
                int selection;
                try {
                    selection = scanner.nextInt();
                    if (selection < 1 || selection >= shelvesIds.length) {
                        System.out.println("Input error");
                        continue;
                    }

                } catch (Exception e) {
                    System.out.println("Input error");
                    continue;
                }
                // find shelf
                Shelf found = null;
                for (int i = 0; i < warehouse.allShelves.length; i++) {
                    if (warehouse.allShelves[i] != null && warehouse.allShelves[i].getId() == selection) {
                        found = warehouse.allShelves[i];
                    }
                }
                if (found == null) {
                    System.out.println("Input error");
                    continue;
                }
                if (found.getResponsibleEmployee() == null) {
                    System.out.println("Currently this shelf does not have responsible employee");
                } else {
                    System.out.println("Currently this shelf's responsible employee is " + found.getResponsibleEmployee().getFullName());
                }
                System.out.println("Available employees: ");
                warehouse.showEmployeesShort();
                System.out.print("Enter employee's id: ");
                int id = -1;
                try {
                    id = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Input error");
                    continue;
                }
                Employee foundEmployee = null;
                for (int i = 0; i < warehouse.allEmployees.length; i++) {
                    if (warehouse.allEmployees[i] != null && warehouse.allEmployees[i].getId() == id) {
                        foundEmployee = warehouse.allEmployees[i];
                        break;
                    }
                }
                if (foundEmployee != null) {
                    found.setResponsibleEmployee(foundEmployee);
                    System.out.println("Done");
                } else {
                    System.out.println("Employee not found");
                    continue;
                }

                warehouse.showEmployeesLong();
            } else if (userInput.equals("10")) { //Show products short
                warehouse.showProductsShort();
            } else if (userInput.equals("11")) { //Show products long
                warehouse.showProductsLong();
            } else if (userInput.equals("12")) { //Add product
                if (warehouse.getNumberOfShelves() == 0) {
                    System.out.println("Add at least one shelf before\n");
                    continue;
                }
                System.out.print("Enter barcode: ");
                String barcode = scanner.nextLine();
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter size (in m3): ");

                double weight;
                double size;

                if (scanner.hasNextDouble()) {
                    size = scanner.nextDouble();
                } else {
                    System.out.println("Please enter proper value.");
                    continue;
                }

                System.out.print("Enter weight (in kg): ");
                if (scanner.hasNextDouble()) {
                    weight = scanner.nextDouble();
                } else {
                    System.out.println("Please enter proper value.");
                    continue;
                }
                warehouse.addProduct(barcode, name, size, weight);
                System.out.println("Done");

            } else if (userInput.equals("13")) { //Edit product
                System.out.println("List of products: ");
                warehouse.showProductsShort();
                System.out.print("Which one you want to edit? (enter id): ");
                int id = -1;
                try {
                    id = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Input error");
                    continue;
                }
                Product product = null;
                for (int i = 0; i < warehouse.stock.length; i++) {
                    if (warehouse.stock[i] != null && warehouse.stock[i].getId() == id) {
                        product = warehouse.stock[i];
                        break;
                    }
                }
                if (product == null) {
                    System.out.println("Product not found");
                    continue;
                }
                System.out.println("Product info: ");
                product.showProductInfoLong();

                System.out.println("What you want to edit?");
                System.out.println("1) Barcode");
                System.out.println("2) Name");
                System.out.println("3) Size");
                System.out.println("4) Weight");
                System.out.println("5) Shelf");
                int selection = -1;
                try {
                    selection = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Input error");
                    continue;
                }
                if (selection < 1 || selection > 5) {
                    System.out.println("Input error");
                    continue;
                }
                switch (selection) {
                    case 1:
                        System.out.print("Enter new barcode of this product: ");
                        scanner.nextLine(); //clear buffor
                        String barcode = scanner.nextLine();
                        product.setBarCode(barcode);
                        System.out.println("Done");
                        break;
                    case 2:
                        System.out.print("Enter new name of this product: ");
                        scanner.nextLine(); //clear buffor
                        String name = scanner.nextLine();
                        product.setName(name);
                        System.out.println("Done");
                        break;
                    case 3:
                        System.out.print("Enter new size of this product: ");
                        scanner.nextLine(); // clear buffor
                        double size = 0;
                        try {
                        	size = scanner.nextDouble();                        	
                        }catch(Exception e) {
                        	System.out.println("Input error");
                        	break;
                        }
                        product.setSize(size);
                        System.out.println("Done");
                        break;
                    case 4:
                        System.out.print("Enter new weight of this product: ");
                        double weight;
                        try {
                        	weight = scanner.nextDouble();                        	
                        }catch(Exception e) {
                        	System.out.println("Input error");
                        	break;
                        }
                        product.setWeight(weight);
                        System.out.println("Done");
                        break;
                    case 5:
                        int[] shelvesIds = new int[warehouse.getNumberOfShelves()];
                        int counter = 0;
                        for (int i = 0; i < warehouse.allShelves.length; i++) {
                            if (warehouse.allShelves[i] != null) {
                                shelvesIds[counter++] = warehouse.allShelves[i].getId();
                            }
                        }
                        System.out.println("Available shelves (IDs):");
                        for (int i = 0; i < shelvesIds.length; i++) {
                            System.out.println("Shelf with id " + shelvesIds[i]);
                        }
                        int shelfID = -1;
                        System.out.print("Enter shelf id: ");
                        try {
                            shelfID = scanner.nextInt();
                        } catch (Exception e) {
                            System.out.println("Input error");
                        }
                        Shelf foundShelf = null;
                        for(int i = 0;i<warehouse.allShelves.length;i++){
                            if(warehouse.allShelves[i] != null && warehouse.allShelves[i].getId() == shelfID){
                                foundShelf = warehouse.allShelves[i];
                                break;
                            }
                        }
                        if(foundShelf == null) {
                        	System.out.println("Shelf not found");                        	
                        }else {
                        	product.setShelf(foundShelf);
                        	System.out.println("Done");             	
                        }
                        break;
                }

            } else if (userInput.equals("14")) { //Release product
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();
                warehouse.releaseProduct(name);
            } else if (userInput.equals("15")) { //Find product larger and heavier than given input
                System.out.print("Products larger than (in m3): ");
                double size = scanner.nextDouble();
                System.out.print("Products heavier than (in kg): ");
                double weight = scanner.nextDouble();
                Product found = warehouse.getProductLargerAndHeavierThan(size, weight);
                if (found != null) {
                    found.showProductInfoLong();
                }
            } else if (userInput.equals("16")) { //Show products sorted
                warehouse.showProductsSorted();
            }
            else if (userInput.equals("exit")) {
            	warehouse.fileAccess.saveWarehouse(warehouse);
                return;
            } else if (userInput.equals("")) {
                scannerError = true;
                continue;
            } else {
                System.out.println("I do not know what you want. Please enter proper number.");
            }
        }
    }

}
