package serial.objects;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;
	private byte[] image;

	public Image(BufferedImage bi) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "jpg", baos);
			baos.flush();
			this.image = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// Image(BufferedImage bi)

	public BufferedImage getImage() {
		InputStream in = new ByteArrayInputStream(image);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}// getImage()

}// Image implements Serializable