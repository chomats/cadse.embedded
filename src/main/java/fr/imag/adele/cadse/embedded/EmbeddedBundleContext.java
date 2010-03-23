package fr.imag.adele.cadse.embedded;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;

import javax.management.RuntimeErrorException;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;


public class EmbeddedBundleContext implements BundleContext {

	@Override
	public void addBundleListener(BundleListener listener) {
	}

	@Override
	public void addFrameworkListener(FrameworkListener listener) {
		
	}

	@Override
	public void addServiceListener(ServiceListener listener) {
		
	}

	@Override
	public void addServiceListener(ServiceListener listener, String filter)
			throws InvalidSyntaxException {
		
	}

	@Override
	public Filter createFilter(String filter) throws InvalidSyntaxException {
		return null;
	}

	@Override
	public ServiceReference[] getAllServiceReferences(String clazz,
			String filter) throws InvalidSyntaxException {
		return null;
	}

	@Override
	public Bundle getBundle() {
		return new EmbeddedBundle(this, "Main");
	}

	@Override
	public Bundle getBundle(long id) {
		return null;
	}

	@Override
	public Bundle[] getBundles() {
		return null;
	}

	@Override
	public File getDataFile(String filename) {
		try {
			return File.createTempFile("temp", filename);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getProperty(String key) {
		return null;
	}

	@Override
	public Object getService(ServiceReference reference) {
		return null;
	}

	@Override
	public ServiceReference getServiceReference(String clazz) {
		return null;
	}

	@Override
	public ServiceReference[] getServiceReferences(String clazz, String filter)
			throws InvalidSyntaxException {
		return null;
	}

	@Override
	public Bundle installBundle(String location) throws BundleException {
		return null;
	}

	@Override
	public Bundle installBundle(String location, InputStream input)
			throws BundleException {
		return null;
	}

	@Override
	public ServiceRegistration registerService(String[] clazzes,
			Object service, Dictionary properties) {
		return null;
	}

	@Override
	public ServiceRegistration registerService(String clazz, Object service,
			Dictionary properties) {
		return null;
	}

	@Override
	public void removeBundleListener(BundleListener listener) {
	}

	@Override
	public void removeFrameworkListener(FrameworkListener listener) {
	}

	@Override
	public void removeServiceListener(ServiceListener listener) {
	}

	@Override
	public boolean ungetService(ServiceReference reference) {
		return false;
	}

}
