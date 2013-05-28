package de.proskor.gravatar;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(rendererType = "de.proskor.gravatar.GravatarRenderer", componentFamily = "de.proskor.gravatar")
public class GravatarRenderer extends Renderer {

	private final GravatarUtils gravatarUtils = GravatarUtils.getInstance();

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		Gravatar gravatar = (Gravatar) component;

		String url = GravatarUtils.BASE_URL + gravatarUtils.getHash(gravatar.getEmail()) + "?d=" + gravatar.getDefault();

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
