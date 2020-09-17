package de.pdbm.mobile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.Resource;
import javax.faces.application.ResourceWrapper;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonArray;

public class WebAppManifestResource extends ResourceWrapper {
	
	@SuppressWarnings("deprecation")
	public WebAppManifestResource() {
	}

	@Override
	public Resource getWrapped() {
		return this;
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		//return WebAppManifestResource.class.getResourceAsStream("manifest.json");
		return new ByteArrayInputStream(getManifest().getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public Map<String, String> getResponseHeaders() {
		return new HashMap<>();
	}

	@Override
	public String getRequestPath() {
		return getApplicationContextPath() + "/javax.faces.resource/manifest.json.xhtml?ln=jsfpraxis";
		//return "/mobile/javax.faces.resource/manifest.json.xhtml?ln=jsfpraxis";
	}

	@Override
	public boolean userAgentNeedsUpdate(FacesContext context) {
		return true;
	}
	
	@Override
	public String getContentType() {
		//return MediaType.APPLICATION_JSON; // JAX-RS
		return "application/json";
	}

	
	private String getManifest() {
		JsonArray icons = Json.createArrayBuilder()
				.add(Json.createObjectBuilder().add("src", getApplicationContextPath() + "/javax.faces.resource/icon1.png.xhtml?ln=icons").add("sizes", "128x128"))
				.add(Json.createObjectBuilder().add("src", getApplicationContextPath() + "/javax.faces.resource/icon2.png.xhtml?ln=icons").add("sizes", "96x96"))
				.build();
		return Json.createObjectBuilder()
				.add("name", "JSF Praxis Mobile")
				.add("description", "Beispiel für Web-App-Manifest")
				.add("icons", icons)
				.build().toString();
	}
	
	private String getApplicationContextPath() {
		// weder FacesContext noch ExternalContext in Resource injizierbar
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
	}
	
}
