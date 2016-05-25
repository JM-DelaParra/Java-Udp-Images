package app;

import com.mortennobel.imagescaling.ResampleOp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.imageio.ImageIO;

import serial.ObjectsHandler;
import serial.objects.Image;

public class MainClass {

	public static void main(String[] args) {
		MainClass main = new MainClass();
		main.mainWrapper();
	}// main(String [] args)

	public void mainWrapper() {
		System.out.println("Server");
		try {
			DatagramSocket ss = new DatagramSocket(2500);
			byte[] buffer = new byte[250000];
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
			ss.receive(dp);
			ObjectsHandler oh = new ObjectsHandler();
			Image received = oh.byteToImage(dp.getData());
			ResampleOp resampleOp = new ResampleOp(1920, 1080); // (16:9) 640x360, 1280x720, 1920x1080 ...
			BufferedImage resizedImage = resampleOp.filter(received.getImage(), null);
			File outputFileResized = new File("resizedImage.jpg");
			ImageIO.write(resizedImage, "jpg", outputFileResized);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End");
	}// mainWrapper()

}// MainClass