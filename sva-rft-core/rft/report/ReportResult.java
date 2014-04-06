package rft.report;

/**
 * Report Result Block
 *
 * @author Bing Qu
 */
public abstract class ReportResult extends ReportBlock implements Outputable{
	// final result(success/fail)
	private boolean finalResult = false;
	// Begin time stamp
	private long beginTimestamp;
	// End time stamp
	private long endTimestamp;

	/**
	 * Get the final result
	 * @return
	 */
	public boolean getFinalResult() {
		return finalResult;
	}
	/**
	 * Set final result
	 * @param finalResult
	 */
	public void setFinalResult(boolean finalResult) {
		this.finalResult = finalResult;
	}
	/**
	 * Get begin time stamp
	 * @return begin time stamp
	 */
	public long getBeginTimestamp() {
		return beginTimestamp;
	}
	/**
	 * Set the begin time stamp
	 * @param beginTimestamp
	 */
	public void setBeginTimestamp(long beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}
	/**
	 * Get the end time stamp
	 * @return end time stamp
	 */
	public long getEndTimestamp() {
		return endTimestamp;
	}
	/**
	 * Set the end time stamp
	 * @param endTimestamp
	 */
	public void setEndTimestamp(long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	/**
	 * Get total milliseconds
	 * @return total milliseconds
	 */
	public long getTotalMilliseconds() {
		return endTimestamp - beginTimestamp;
	}
}
