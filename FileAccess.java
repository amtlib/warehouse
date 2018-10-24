import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

public class FileAccess {
	private RandomAccessFile file;
	private String fileName;
	
	
	public FileAccess(String fileName) {
		this.fileName = fileName;
	}
	
	public void saveWarehouse(Warehouse warehouse) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			this.file = new RandomAccessFile(this.fileName, "rw");
		} catch (FileNotFoundException e) {}
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(warehouse);
		  out.flush();
		  byte[] yourBytes = bos.toByteArray();
		  this.file.write(yourBytes);
		  this.file.close();
		} catch(IOException e){
			// ignore this exception
		}finally {
		  try {
		    bos.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
	}
	public Warehouse readWarehouse() {
		Warehouse w = null;
		byte[] document = null;
		try {
			this.file = new RandomAccessFile(this.fileName, "rw");
		} catch (FileNotFoundException e) {}
		try {
			document = new byte[(int) file.length()];
			this.file.readFully(document);
			this.file.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	    
		ByteArrayInputStream bis = new ByteArrayInputStream(document);
		ObjectInput in = null;
		try {
		  in = new ObjectInputStream(bis);
		  w = (Warehouse)in.readObject(); 
		  
		} catch(Exception e){
			
		}finally {
		  try {
		    if (in != null) {
		      in.close();
		    }
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		
		return w;
	}
}
