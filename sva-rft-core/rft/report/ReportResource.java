package rft.report;

import java.util.HashMap;
import java.util.Map;

import rft.report.ReportConfiguration.Language;

/**
 * Report Resource Provider
 *
 * @author Bing Qu
 */
public class ReportResource {
	/**
	 * Enumeration of Resource Definition
	 * (TODO: instead by property files)
	 */
	public static enum ResourceKey {
		FunctionName("[Function Name]", "【機能名称】", "【功能名称】"),
		CaseNo("[Case No]", "【ケース番号】", "【Case　No】"),
		Description("[Description]", "【説明】", "【说明】"),
		ExecutionDate("[Execution Date]", "【実施時間】", "【执行日期】"),
		Scenario("[Scenario]", "【シナリオ】", "【操作】"),
		Step("[Step]", "【ステップ】", "【步骤】"),
		TargetObject("[Target Object]", "【検証対象】", "【验证对象】"),
		ExpectValue("[Expect Value]", "【想定結果】", "【预期值】"),
		ActualValue("[Actual Value]", "【実績結果】", "【实际值】"),
		ValidationResult("[Validation Result]", "【検証結果】", "【验证结果】"),
		TestResult("[Test Result]", "【実行結果】", "【测试结果】"),
		ExecutionTime("[Execution Time]", "【実行時間】", "【执行时间】"),
		Successed("Successed", "成功", "成功"),
		Failed("Failed", "失敗", "失败"),
		OK("OK", "OK", "NG"),
		NG("NG", "NG", "NG"),
		Seconds("Seconds", "秒", "秒"),
		;

		private Map<Language, String> messages = new HashMap<>();
		private ResourceKey(String en, String jp, String cn) {
			messages.put(Language.EN, en);
			messages.put(Language.JP, jp);
			messages.put(Language.CN, cn);
		}

		public String getResourceValue(Language lang) {
			return messages.get(lang);
		}
	}
	/**
	 * Get resource value
	 * @param key Resource key
	 * @param lang Language
	 * @return Resource Value
	 */
	public static String getResourceValue(ResourceKey key, Language lang) {
		return key.getResourceValue(lang);
	}
}
