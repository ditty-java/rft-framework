package rft.report;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Report Step Block
 * 
 * @author Bing Qu
 */
public abstract class ReportStep extends ReportBlock implements Outputable{
	// description information
	private String stepDescription;
	// contents of step block
	private List<Outputable> contents = new ArrayList<>();

	protected void addContent(Outputable content) {
		contents.add(content);
	}

	/**
	 * Get description information
	 * 
	 * @return
	 */
	public String getStepDescription() {
		return stepDescription;
	}
	/**
	 * Set description information
	 * @param stepDescription
	 */
	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}

	/**
	 * Add comment line
	 *
	 * @param comment
	 */
	public abstract void addComment(String comment);
	
	/**
	 * Add Image from system clip board
	 */
	public abstract void addImage();

	/**
	 * Add Image
	 *
	 * @param image
	 */
	public abstract void addImage(Image image);

	/**
	 * Add Image list
	 * @param images
	 */
	public void addImages(List<Image> images) {
		for (Image image : images) {
			addImage(image);
		}
	}

	/**
	 * Add Variable Validation Block
	 *
	 * @param target Target object
	 * @param expectValue Expect Value
	 * @param actualValue Actual Value
	 */
	public abstract void addVariableValidation(String target, String expectValue, String actualValue);

	/**
	 * Check Validation Result
	 *
	 * @return
	 */
	public abstract boolean isValid();
	
	@Override
	public void output() throws IOException {
		for (Outputable content : contents) {
			content.output();
		}
	}
}
