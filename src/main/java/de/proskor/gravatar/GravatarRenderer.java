package de.proskor.gravatar;

import static de.proskor.gravatar.GravatarUtils.getURL;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(rendererType = GravatarRenderer.RENDERER_TYPE, componentFamily = Gravatar.COMPONENT_FAMILY)
public class GravatarRenderer extends Renderer {

	public static final String RENDERER_TYPE = "de.proskor.gravatar.GravatarRenderer";

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		Gravatar gravatar = (Gravatar) component;
		String url = getURL(gravatar);
		int size = gravatar.getSize();

		writer.startElement("img", gravatar);
		writer.writeAttribute("id", gravatar.getClientId(), "id");
		if (gravatar.isDeferred()) {
			writer.writeURIAttribute("data-defer-src", url, "email");
		} else {
			writer.writeURIAttribute("src", url, "email");
		}
		writer.writeAttribute("width", size, "size");
		writer.writeAttribute("height", size, "size");
		writer.writeAttribute("alt", " ", "alt");

		String tooltip = gravatar.getTooltip();

		if (tooltip != null) {
			writer.writeAttribute("tooltip", tooltip, "tooltip");
		}

		String styleClass = gravatar.getStyleClass();

		if (styleClass != null) {
			writer.writeAttribute("class", styleClass, "styleClass");
		}

		writer.endElement("img");
	}

}