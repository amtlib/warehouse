import java.io.Serializable;

public class Product implements Comparable<Product>, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5690368189523712528L;
	private String barcode;
    private String name;
    private double size;
    private double weight;
    private Shelf shelf;
    private int id;
    private static int idCounter = 0;

    public Product(String barcode, String name, double size, double weight, Shelf shelf) {
        super();
        this.barcode = barcode;
        this.name = name;
        this.size = size;
        this.weight = weight;
        this.shelf = shelf;
        this.id = idCounter++;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getBarcode() {
        return barcode;
    }

    public void setBarCode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double mass) {
        this.weight = mass;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    public void showProductInfoShort() {
        System.out.println("-*- " + this.getName() + " -*-");
        System.out.println("ID: " + this.getId());
        System.out.println("Barcode: " + this.getBarcode());
        System.out.println("Shelf: " + this.getShelf().getId());
        System.out.println("-----------------------------"); //nice line
    }

    public void showProductInfoLong() {
        System.out.println("-*- " + this.getName() + " -*-");
        System.out.println("ID: " + this.getId());
        System.out.println("Barcode: " + this.getBarcode());
        System.out.println("Size: " + this.getSize() + " m3");
        System.out.println("Weight: " + this.getWeight() + " kg");
        System.out.println("Shelf: " + this.getShelf().getId());
        if (this.getShelf().getResponsibleEmployee() != null) {
            System.out.println("Employee responsible for this shelf: " + this.getShelf().getResponsibleEmployee().getFullName());
        }
        System.out.println("-----------------------------"); //nice line
    }

    @Override
    public int compareTo(Product o) {
        return getName().compareTo(o.getName());
    }


}
