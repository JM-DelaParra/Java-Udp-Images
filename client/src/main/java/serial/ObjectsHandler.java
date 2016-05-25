package serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import serial.objects.Image;

public class ObjectsHandler {

	public ObjectsHandler() {
	}// ObjectsHandler()

	public byte[] objectToByte(Object o) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}// objectToByte(Object o)

	public Image byteToImage(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(bais);
			Image image = (Image) ois.readObject();
			ois.close();
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}// byteToImage(byte[] bytes)

}// ObjectsHandler