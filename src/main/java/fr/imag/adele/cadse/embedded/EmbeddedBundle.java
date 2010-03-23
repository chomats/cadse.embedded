package fr.imag.adele.cadse.embedded;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

public class EmbeddedBundle implements Bundle {

	BundleContext _bundleContext;
	private String _symbolicName;
	
	public EmbeddedBundle(BundleContext bundleContext, String symbolicName) {
		_bundleContext = bundleContext;
		_symbolicName = symbolicName;
	}

	@Override
	public Enumeration findEntries(String path, String filePattern,
			boolean recurse) {
		return null;
	}

	@Override
	public BundleContext getBundleContext() {
		return _bundleContext;
	}

	@Override
	public long getBundleId() {
		return 0;
	}

	@Override
	public URL getEntry(String path) {
		return null;
	}

	@Override
	public Enumeration getEntryPaths(String path) {
		return null;
	}

	@Override
	public Dictionary getHeaders() {
		return null;
	}

	@Override
	public Dictionary getHeaders(String locale) {
		return null;
	}

	@Override
	public long getLastModified() {
		return 0;
	}

	@Override
	public String getLocation() {
		return null;
	}

	@Override
	public ServiceReference[] getRegisteredServices() {
		return null;
	}

	@Override
	public URL getResource(String name) {
		return null;
	}

	@Override
	public Enumeration getResources(String name) throws IOException {
		return null;
	}

	@Override
	public ServiceReference[] getServicesInUse() {
		return null;
	}

	@Override
	public Map getSignerCertificates(int signersType) {
		return null;
	}

	@Override
	public int getState() {
		return 0;
	}

	@Override
	public String getSymbolicName() {
		return _symbolicName;
	}

	@Override
	public Version getVersion() {
		return Version.emptyVersion;
	}

	@Override
	public boolean hasPermission(Object permission) {
		return false;
	}

	@Override
	public Class loadClass(String name) throws ClassNotFoundException {
		return getClass().getClassLoader().loadClass(name);
	}

	@Override
	public void start() throws BundleException {
	}

	@Override
	public void start(int options) throws BundleException {

	}

	@Override
	public void stop() throws BundleException {

	}

	@Override
	public void stop(int options) throws BundleException {

	}

	@Override
	public void uninstall() throws BundleException {

	}

	@Override
	public void update() throws BundleException {

	}

	@Override
	public void update(InputStream input) throws BundleException {

	}

}
