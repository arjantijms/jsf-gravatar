package de.proskor.gravatar;

import static de.proskor.gravatar.GravatarUtils.BASE_URL;
import static de.proskor.gravatar.GravatarUtils.getHash;

import java.io.IOException;
import java.net.URLEncoder;

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

		String url = BASE_URL + getHash(gravatar.getEmail()) + "?d=" + URLEncoder.encode(gravatar.getDefault(), "UTF-8");
		int size = gravatar.getSize();

		if (size != Gravatar.DEFAULT_SIZE) {
			url += "&s=" + size;
		}

		writer.startElement("img", gravatar);
		writer.writeAttribute("id", gravatar.getClientId(), "id");
		writer.writeURIAttribute("src", url, "email");
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