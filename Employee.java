public class Employee implements java.io.Serializable {
    private static final long serialVersionUID = -4666704137939530367L;
    private static int idCounter = 1;
    private int id;
    private String lastName;

    public Employee(String firstName, String lastName) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = idCounter++;
    }

    private String firstName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void showEmployeeInfoLong() {
        System.out.println("-*- Employee -*-");
        System.out.println("First name: " + this.getFirstName());
        System.out.println("Last name: " + this.getLastName());
        System.out.println("Employee number: " + this.getId());
        System.out.println("-----------------------------"); //nice line
    }

    public void showEmployeeInfoShort() {
        System.out.println("-*- Employee number " + this.getId() + ": " + this.getFirstName() + " " + this.getLastName() + " -*-");
    }
}
