package rft.tookit;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import com.rational.test.ft.object.interfaces.BrowserTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.TestObject;

public class ScreenCapture {
	/**
	 * Capture the Full Screen
	 *
	 * @return image
	 */
	public static Image captureScreen() {
        try {
            Robot robot = new Robot();
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            return robot.createScreenCapture(new Rectangle(0, 0, dimension.width, dimension.height));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        return null;
    }

	/**
	 * Capture the screen of target test object
	 *
	 * @param target
	 * @return
	 */
	public static Image captureScreen(TestObject target) {
		Rectangle rectangle = MiscUtils.getRectangle(target);
        
        try {
            Robot robot = new Robot();
            return robot.createScreenCapture(rectangle);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        return null;
    }

	/**
	 * Capture the screen of target test object
	 *
	 * @param target
	 * @return
	 */
	public static List<Image> captureMultipleScreens(BrowserTestObject browser, GuiTestObject scrollTarget) {
		List<Image> images = new ArrayList<>();
		Image image = null;
		while (true) {
			scrollTarget.click();
			image = captureScreen(browser);
			if (!images.isEmpty() && ImageTookit.compareImage(image, images.get(images.size() - 1), 99)) {
				break;
			} else {
				images.add(image);
				//Page Down
				browser.inputKeys("{ExtPgDn}");
			}
		}
		return images;
    }

	/**
	 * Capture the screen with specified rectangle
	 *
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static Image captureScreen(int x, int y, int width, int height) {
        try {
            Robot robot = new Robot();
            return robot.createScreenCapture(new Rectangle(x, y, width, height));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
