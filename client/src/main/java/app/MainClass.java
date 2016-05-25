package app;

import java.awt.image.BufferedImage;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.File;
import java.net.InetAddress;

import javax.imageio.ImageIO;

import serial.ObjectsHandler;
import serial.objects.Image;

public class MainClass {

	public static void main(String[] args) {
		MainClass main = new MainClass();
		main.mainWrapper();
	}// main(String [] args)

	public void mainWrapper() {
		System.out.println("Client");
		try {
			ObjectsHandler oh = new ObjectsHandler();
			BufferedImage bi = ImageIO.read(new File("src/main/resources/images/image.jpg"));
			DatagramSocket clientSocket = new DatagramSocket();
			Image image = new Image(bi);
			byte[] objectData = oh.objectToByte(image);
			DatagramPacket dp = new DatagramPacket(objectData, objectData.length, InetAddress.getByName("127.0.0.1"), 2500);
			clientSocket.send(dp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End");
	}// mainWrapper()

}// MainClass
