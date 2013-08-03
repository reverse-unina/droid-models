package com.nofatclips.androidtesting.guitree;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nofatclips.androidtesting.model.UserInput;
import com.nofatclips.androidtesting.model.WrapperInterface;
import com.nofatclips.androidtesting.xml.XmlGraph;

public class TestCaseInput extends TestCaseInteraction implements UserInput {

	public TestCaseInput () {
		super();
	}
	
	public TestCaseInput (Element e) {
		super (e);
	}
	
	public TestCaseInput (XmlGraph g) {
		super (g, TAG);
	}
	
	public TestCaseInput (Document d) {
		super (d, TAG);
	}
	
	public WrapperInterface getWrapper(Element e) {
		return new TestCaseInput (e);
	}

	public String getType() {
		return getAttribute("type");
	}

	public String getName() {
		return getWidget().getName();
	}


	public String getValue() {
		return getAttribute("value");
	}
	
	public boolean hasValue() {
		return hasAttribute("value");
	}
	
	public void setType (String type) {
		setAttribute("type",type);
	}

	public void setValue (String value) {
		setAttribute("value", value);
	}
	
	public void setId (String id) {
		setAttribute("id",id);
	}
	
	public String getId() {
		return getAttribute("id");
	}

	public static TestCaseInput createInput(GuiTree theSession) {
		TestCaseInput input = new TestCaseInput(theSession);
		return input;
	}

	public static TestCaseInput createInput(Document theSession) {
		return new TestCaseInput(theSession);
	}
	
	public TestCaseInput clone () {
		TestCaseInput i = createInput(this.getElement().getOwnerDocument());
		i.setType(this.getType());
		if (this.hasValue()) {
			i.setValue(this.getValue());
		}
		i.setId(this.getId());
		i.setWidget(this.getWidget().clone());
		return i;
	}

	public static String TAG = "INPUT";

}