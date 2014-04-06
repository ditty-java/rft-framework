package rft.report;

import java.awt.Image;

/**
 * Report Image Block
 * 
 * @author Bing Qu
 */
public class ReportImage extends ReportBlock {
	/**
	 * Constructor
	 */
	protected ReportImage() {
		//NOP
	}

	private Image image;

	/**
	 * Get Image
	 * @return Image object
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * Set Image
	 * @param image Image object
	 */
	public void setImage(Image image) {
		this.image = image;
	}
}
