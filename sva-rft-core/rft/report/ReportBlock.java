package rft.report;

/**
 * Report Block
 *
 * @author Bing Qu
 */
public abstract class ReportBlock {
	//report object
	private Report report;

	/**
	 * get the report object
	 *
	 * @return report object
	 */
	protected Report getReport() {
		return report;
	}

	/**
	 * set the report object
	 *
	 * @param report report object
	 */
	protected void setReport(Report report) {
		this.report = report;
	}
}
