package rft.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Comment Block (multiple lines supported)
 *
 * @author Bing Qu
 */
public abstract class Comment extends ReportBlock implements Outputable {

	/**
	 * Constructor
	 *
	 * @param lines 
	 */
	public Comment(String... lines) {
		this.lines.addAll(Arrays.asList(lines));
	}

	private List<String> lines = new ArrayList<>();

	/**
	 * @return lines in comment
	 */
	protected List<String> getLines() {
		return lines;
	}
}
