package rft.report;

import java.io.IOException;

/**
 * Output capability definition
 *
 * @author Bing Qu
 *
 */
public interface Outputable {
	/**
	 * output the content with special format implementation.
	 *
	 * @throws IOException
	 */
	public void output() throws IOException;
}
