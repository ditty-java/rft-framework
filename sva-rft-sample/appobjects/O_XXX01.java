package appobjects;

import resources.appobjects.O_XXX01Helper;
import rft.widgets.WBrowser;
import rft.widgets.WButton;
import rft.widgets.WTextField;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.TextGuiTestObject;

public class O_XXX01 extends O_XXX01Helper {

	public WBrowser getBrowser_htmlBrowser() {
		TestObject to = browser_htmlBrowser(ANY, NO_STATE);
		return new WBrowser(to);
	}

	public WButton getButton_search_submit() {
		TestObject to = button_search_submit(ANY, NO_STATE);
		return new WButton(to);
	}

	public WTextField getText_wd() {
		TestObject to = text_wd(ANY, NO_STATE);
		return new WTextField(to);
	}

	public WTextField getText_wd2() {
		TestObject to = text_wd2(ANY, NO_STATE);
		return new WTextField(to);
	}
	
	public TextGuiTestObject text_wd() {
		return text_wd(ANY, NO_STATE);
	}
}
