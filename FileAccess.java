import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

public class FileAccess implements java.io.Serializable {
    private static final long serialVersionUID = 3614364148119987323L;
    private String filePath;
    
    private RandomAccessFile file;

   
    public FileAccess(String filePath) {
    	this.filePath = filePath;
    	try {
			this.file = new RandomAccessFile(this.filePath, "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void saveWarehouse(Warehouse warehouse) {
        try {
        	FileOutputStream fos = new FileOutputStream(this.file.getFD());
        	ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
			objectOutputStream.writeObject(warehouse);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (Exception e) {
		}
        
    }
    public Warehouse readWarehouse() {
    	Warehouse warehouse = null;
    	try {
    		FileInputStream fis = new FileInputStream(this.file.getFD());
			ObjectInputStream objectInputStream = new ObjectInputStream(fis);
			warehouse = (Warehouse)objectInputStream.readObject();
			warehouse.setFileAccess(new FileAccess("baza.txt"));
		} catch (Exception e) {
		}
    	
    	return warehouse;
    }

}
