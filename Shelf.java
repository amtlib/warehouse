public class Shelf implements java.io.Serializable {
    private static final long serialVersionUID = -7971985533179434404L;
    private static int idCounter = 1;
    private int id;
    private Employee responsibleEmployee;

    public Shelf() {
        this.id = idCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(Employee responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
    }

}
