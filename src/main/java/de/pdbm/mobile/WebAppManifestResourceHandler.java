package de.pdbm.mobile;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;

public class WebAppManifestResourceHandler extends ResourceHandlerWrapper  {

	private static final String JSFPRAXIS = "jsfpraxis";
	private static final String MANIFEST = "manifest.json";
	
	private ResourceHandler wrapped;
	
	@SuppressWarnings("deprecation")
	public WebAppManifestResourceHandler(ResourceHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ResourceHandler getWrapped() {
		return this.wrapped;
	}
	
	@Override
	public Resource createResource(String resourceName, String libraryName) {
		if (JSFPRAXIS.equals(libraryName) && MANIFEST.equals(resourceName)) {
			return new WebAppManifestResource(); 
		} else {
			return super.createResource(resourceName, libraryName);	
		}
	}

}
