package de.proskor.gravatar;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

@FacesComponent(Gravatar.COMPONENT_TYPE)
public class Gravatar extends UIComponentBase {

	public static final String COMPONENT_TYPE = "de.proskor.gravatar.Gravatar";
	public static final String COMPONENT_FAMILY = "de.proskor.gravatar";
	public static final String DEFAULT_IMAGE = "mm";
	public static final int DEFAULT_SIZE = 80;

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public String getEmail() {
		return (String) getStateHelper().eval("email");
	}

	public void setEmail(String value) {
		getStateHelper().put("email", value);
	}

	public String getDefault() {
		return (String) getStateHelper().eval("default", DEFAULT_IMAGE);
	}

	public void setDefault(String value) {
		getStateHelper().put("default", value);
	}

	public int getSize() {
		return (Integer) getStateHelper().eval("size", DEFAULT_SIZE);
	}

	public void setSize(int value) {
		getStateHelper().put("size", value);
	}

	public String getTooltip() {
		return (String) getStateHelper().eval("tooltip");
	}

	public void setTooltip(String tooltip) {
		getStateHelper().put("tooltip", tooltip);
	}

	public String getStyleClass() {
		return (String) getStateHelper().eval("styleClass");
	}

	public void setStyleClass(String styleClass) {
		getStateHelper().put("styleClass", styleClass);
	}

	public boolean isDeferred() {
		return (Boolean) getStateHelper().eval("deferred", false);
	}

	public void setDeferred(boolean deferred) {
		getStateHelper().put("deferred", deferred);
	}

}