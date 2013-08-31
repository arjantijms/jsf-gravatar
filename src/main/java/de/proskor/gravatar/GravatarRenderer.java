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

		String styleClass = (String) gravatar.getAttributes().get("styleClass");

		if (styleClass != null) {
			writer.writeAttribute("class", styleClass, "class");
		}

		writer.writeAttribute("id", gravatar.getClientId(), "id");
		writer.writeURIAttribute("src", url, null);
		writer.writeAttribute("width", size, null);
		writer.writeAttribute("height", size, null);
		writer.writeAttribute("alt", "", null);

		writer.endElement("img");
	}

}