package rft.tookit;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.rational.test.ft.object.interfaces.TestObject;

public class ImageTookit {

	public static BufferedImage toBufferedImage(Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	public static Image highlightTarget(Image image, TestObject parent,
			TestObject target, Color color, int lineStroke) {
		Rectangle rectangle0 = MiscUtils.getRectangle(parent);
		Rectangle rectangle1 = MiscUtils.getRectangle(target);

		BufferedImage bimg = toBufferedImage(image);
		Graphics2D g = (Graphics2D) bimg.getGraphics();
		g.setColor(color);
		if (lineStroke > 1) {
			g.setStroke(new BasicStroke(lineStroke));
		}
		g.drawRect((rectangle1.x - rectangle0.x) - 6,
				(rectangle1.y - rectangle0.y) - 3, rectangle1.width + 12,
				rectangle1.height + 6);

		return bimg;
	}

	public static void main(String[] args) {
		Image image = ScreenCapture.captureScreen();
		BufferedImage bimg = toBufferedImage(image);
		Graphics2D g = (Graphics2D) bimg.getGraphics();

		// g.setColor(Color.RED);
		// g.setStroke(new BasicStroke(5.0f));
		// g.drawRect(50, 50, 1000, 1000);
		//
		// g.setColor(Color.ORANGE);
		// g.setStroke(new BasicStroke(4.0f));
		// g.drawRect(60, 60, 800, 800);
		//
		// g.setColor(Color.YELLOW);
		// g.setStroke(new BasicStroke(3.0f));
		// g.drawRect(70, 70, 600, 600);
		//
		// g.setColor(Color.GREEN);
		// g.setStroke(new BasicStroke(2.0f));
		// g.drawRect(80, 80, 400, 400);
		//
		// g.setColor(Color.BLUE);
		// g.setStroke(new BasicStroke(1.0f));
		// g.drawRect(90, 90, 200, 200);

		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(1.0f));
		g.drawRect(90, 90, 600, 600);

		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(2.0f));
		g.drawRect(90, 90, 600 + 2, 600 + 2);

		try {
			ImageIO.write(bimg, "jpeg", new File("c://1.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Simplify image object for comparison
	 * @param image
	 * @return
	 */
	private static BufferedImage simplifyImage(Image image) {
		BufferedImage pic = new BufferedImage(256, 256, BufferedImage.TYPE_INT_BGR);
		Graphics2D graphics = (Graphics2D) pic.getGraphics();
		graphics.drawImage(image, 0, 0, 256, 256, null);
		graphics.dispose();
		pic.flush();

		return pic;
	}

	/**
	 * Compare Image Object
	 *
	 * @param image0
	 * @param image1
	 * @param accuracy
	 * @return
	 */
	public static boolean compareImage(Image image0, Image image1, int accuracy) {
		int count = 0;
		int[] c0 = new int[256 * 256];
		int[] c1 = new int[256 * 256];

		// Simplify Images
		BufferedImage pic0 = simplifyImage(image0);
		BufferedImage pic1 = simplifyImage(image1);

		// Get RGB data of images
		pic0.getRGB(0, 0, 256, 256, c0, 0, 256);
		pic1.getRGB(0, 0, 256, 256, c1, 0, 256);

		for (int i = 0; i < c0.length; i++) {
			if (c0[i] != c1[i]) {
				count++;
			}
		}
		float result = ((float) (256 * 256 - count) / c0.length) * 100;
		//System.out.println("accuracy:" + result);
		return accuracy <= result;
	}
}
