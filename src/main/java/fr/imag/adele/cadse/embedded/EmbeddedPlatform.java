/**
 * 
 */
package fr.imag.adele.cadse.embedded;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import fr.imag.adele.cadse.as.platformide.IPlatformIDE;
import fr.imag.adele.cadse.as.platformide.IPlatformListener;
import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.Item;
import fr.imag.adele.cadse.core.content.ContentItem;

public final class EmbeddedPlatform implements IPlatformIDE {
	BundleContext _bundleContext ;
	
	@Override
	public void waitUI() {
	}

	@Override
	public void setReadOnly(Item item, boolean readonly) {
		
	}

	@Override
	public void setItemPersistenceID(String projectName, Item item)
			throws CadseException {
		
	}

	@Override
	public void removeListener(IPlatformListener l) {
		
	}

	@Override
	public void refresh(Item item) {
		
	}

	@Override
	public CadseRuntime[] openDialog(boolean askToErase) {
		return null;
	}

	@Override
	public void notifieChangedContent(Item item) {
		
	}

	@Override
	public void log(String type, String message, Throwable e, Item item) {
		System.err.println(message);
		e.printStackTrace();
		
	}

	@Override
	public void log(String type, String message, Throwable e) {
		System.err.println(message);
		e.printStackTrace();
	}

	@Override
	public boolean isUIStarted() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isResourceStarted() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean inDevelopmentMode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public File getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getLocation(boolean wait) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bundle> findBundlePrefix(String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bundle findBundle(String symbolicName) {
		return new EmbeddedBundle(_bundleContext, symbolicName);
	}

	@Override
	public void endRule(Object rule) {
		
	}

	@Override
	public void copyResource(Item item, String path, URL data)
			throws CadseException {
		
	}

	@Override
	public void beginRule(Object rule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListener(IPlatformListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateIDE() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRessourceName(ContentItem contentItem) {
		// TODO Auto-generated method stub
		return null;
	}
}