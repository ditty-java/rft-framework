/**
 * 
 */
package rft.tookit;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.util.OptionManager;

/**
 * MISC Utilities
 *
 * @author Bing Qu
 *
 */
public class MiscUtils {

	/**
	 * Prepare DataPool for Child Test Scripts(Invoked without testMain method)
	 *
	 * @param rts
	 */
	public static void prepareDataPool(RationalTestScript rts) {
		if (rts == null) {
			return;
		}

		String datapoolName = rts.getScriptDefinition().getDatapoolName();
	    if (datapoolName != null) {
	        rts.dpInitialization(
	        		new File(OptionManager.getString("rt.datastore"), datapoolName), 
	        		-1, 
	        		rts.getScriptDefinition().getDatapoolIteratorClassName());
	        rts.dpInitialize(rts.getDatapool(), -1, 1);
	        rts.getDatapool().getDefaultEquivalenceClassIndex();
	    }
	}

	/**
	 * Get the image from system clip board
	 * 
	 * @return image
	 */
	public static Image getSysClipboardImage() {
		Image ret = null;
		Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable clipTrans = sysClip.getContents(null);

		if (clipTrans != null) {
			if (clipTrans.isDataFlavorSupported(DataFlavor.imageFlavor)) {
				try {
					ret = (Image) clipTrans.getTransferData(DataFlavor.imageFlavor);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return ret;
	}
	
	/**
	 * get Rectangle of Target Test Object.
	 *
	 * @param target
	 * @return
	 */
	public static Rectangle getRectangle(TestObject target) {
		Rectangle rectangle = null;
		
		if (target.getProperties().containsKey(".bounds")) {// html
			rectangle = (Rectangle) target.getProperty(".bounds");
		} else if (target.getProperties().containsKey(".screenRectangle")) {// win
			rectangle = (Rectangle) target.getProperty(".screenRectangle");
		} else if (target.getProperties().containsKey("bounds")) {// swing
			rectangle = (Rectangle) target.getProperty("bounds");
			java.awt.Point point = null;

			if (target.getProperties().containsKey("location")) { // swt
				point = (Point) target.getProperty("location");
			} else {
				point = (Point) target.getProperty("locationOnScreen");
			}

			if (point != null) {
				rectangle.setLocation(point);
			}
		} else {
			System.out.println("Error in captureScreen: could not capture test object");
		}

		return rectangle;
	}
	
	/**
	 * Get the property value from the leaf object of target
	 *
	 * @param target
	 * @param propertyName
	 * @return
	 */
	public static String drillLeafProperty(TestObject target, String propertyName) {
		if (target == null || propertyName == null) {
			return null;
		}

		String value = (String)target.getProperty(propertyName);
		while (value != null && !value.isEmpty() && target.getChildren().length > 0) {
			target = target.getChildren()[0];
			value = (String)target.getProperty(propertyName);
		}

		return (String)target.getProperty(propertyName);
	}

	public static List<String[]> loadHtmlTable(TestObject table) {
		List<String[]> tableData = new ArrayList<>();

		TestObject[] rows = table.getChildren();
		if (rows[0].getProperty(".class").equals("Html.TBODY")) {
			rows = rows[0].getChildren();
		}

		if (rows[0].getProperty(".class").equals("Html.TR")) {
			for (TestObject row : rows) {
				//System.out.println("row:");
				//System.out.println(row.getProperty(".class"));
				TestObject[] cells = row.getChildren();
				List<String> htmlRow = new ArrayList<>(cells.length);
				
				for (TestObject cell : cells) {
					if (rows[0].getProperty(".class").equals("Html.TD")) {
						//System.out.println(cell.getProperty(".class"));
						String cellValue = MiscUtils.drillLeafProperty(cell, ".text");
						htmlRow.add(cellValue);
						//System.out.println("cell:" + cellValue);
					}
				}
				tableData.add(htmlRow.toArray(new String[0]));
			}
		}

		return tableData;
	}
}
