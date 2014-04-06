package rft.report;

/**
 * Report Configuration
 *
 * @author Bing Qu
 */
public class ReportConfiguration {

	/**
	 * Enumeration of Language supported 
	 */
	public static enum Language {
		/** English */
		EN,
		/** Chinese */
		CN,
		/** Japnese */
		JP;
	}

	// trace setting
	private TraceSetting traceSetting;

	//language setting
	private Language language = Language.EN;

	/**
	 * get the trace setting
	 *
	 * @return trace setting
	 */
	public TraceSetting getTraceSetting() {
		return traceSetting;
	}
	/**
	 * set the trace setting
	 *
	 * @param traceSetting trace setting
	 */
	public void setTraceSetting(TraceSetting traceSetting) {
		this.traceSetting = traceSetting;
	}
	/**
	 * get language setting
	 *
	 * @return language setting
	 */
	public Language getLanguage() {
		return language;
	}
	/**
	 * set language setting
	 * @param language language setting
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}
	/**
	 * check the trace setting enabled
	 *
	 * @return trace setting enabled
	 */
	public boolean isTraceEnabled() {
		return traceSetting != null;
	}
}
