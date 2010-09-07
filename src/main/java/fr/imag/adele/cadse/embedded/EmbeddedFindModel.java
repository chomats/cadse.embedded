/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.embedded;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import fr.imag.adele.fede.workspace.as.initmodel.jaxb.CCadse;
import fr.imag.adele.fede.workspace.si.initmodel.internal.ModelRepository;
import fr.imag.adele.melusine.as.findmodel.CheckModel;
import fr.imag.adele.melusine.as.findmodel.IFindModel;
import fr.imag.adele.melusine.as.findmodel.ModelEntry;

final class EmbeddedFindModel implements IFindModel {
	
	public static final String DOMAIN_WORKSPACE = "Workspace";
	
	Map<String, EmbeddedModelEntry> _models = new HashMap<String, EmbeddedModelEntry>();
	
	static class EmbeddedModelEntry implements ModelEntry {

		private String _name;
		private URL _url;
		
		public EmbeddedModelEntry(String name, URL url) {
			_name =  name;
			_url = url;
		}
		
		@Override
		public String getDomainName() {
			return DOMAIN_WORKSPACE;
		}

		@Override
		public URL getEntry(String path) throws IOException {
			if (path.endsWith(ModelRepository.QUALIFIED_FILE_NAME)) {
				return _url;
			}
			return getClass().getClassLoader().getResource(path);
		}

		@Override
		public File getFile() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getName() {
			return _name;
		}

		@Override
		public ModelEntry getSubEntry(String path) {
			throw new UnsupportedOperationException();
		}

		@Override
		public ModelEntry[] list() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public void start() {
		String[] strClassPath = System.getProperty("java.class.path").split(":");
		System.out.println("Class path : "+Arrays.asList(strClassPath));
		for (String path : strClassPath) {
			File f = new File(path);
			if (f.exists()) {
				if (f.isDirectory()) {
					File cadseFile = new File(f, ModelRepository.QUALIFIED_FILE_NAME);
					if (cadseFile.exists() && cadseFile.isFile()) {
						try {
							add(cadseFile.toURI().toURL());
						} catch (JAXBException ignored) {
							ignored.printStackTrace();
						} catch (IOException ignored) {
							ignored.printStackTrace();
						}
					}
				} else {
					JarFile jf;
					try {
						jf = new JarFile(f);
						ZipEntry cadseEntry = jf.getEntry(ModelRepository.QUALIFIED_FILE_NAME);
						if (cadseEntry != null) {
							add(new URL("jar:"+f.toURI().toURL()+"!/"+ModelRepository.QUALIFIED_FILE_NAME));
						}						
					} catch (IOException ignored) {
						ignored.printStackTrace();
					} catch (JAXBException ignored) {
						ignored.printStackTrace();
					}
					
				}
			}
		}
	}
	
	private CCadse read(InputStream s) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance("fr.imag.adele.fede.workspace.as.initmodel.jaxb", this.getClass()
				.getClassLoader());
		Unmarshaller m = jc.createUnmarshaller();
		return (CCadse) m.unmarshal(s);
	}
	
	private void add(URL url) throws JAXBException, IOException {
		CCadse c = read(url.openStream());
		if (c != null) {
			_models.put(c.getName(), new EmbeddedModelEntry(c.getName(), url));
		}
	}

	@Override
	public ModelEntry findQualifiedModel(String domainName,
			String qualifiedModelName) {
		if (DOMAIN_WORKSPACE.equals(domainName)) {
			return _models.get(qualifiedModelName);
		}
		return null;
	}

	@Override
	public ModelEntry[] findModelEntries(String domainName, CheckModel check) {
		if (DOMAIN_WORKSPACE.equals(domainName)) {
			List<ModelEntry> ret = new ArrayList<ModelEntry>();
			for (EmbeddedModelEntry mme : _models.values()) {
				if (check.check(mme)) {
					ret.add(mme);
				}
			}
			return ret.toArray(new ModelEntry[ret.size()]);
		}
		return new ModelEntry[0];
	}

	@Override
	public ModelEntry[] findModelEntries(String domainName) {
		if (DOMAIN_WORKSPACE.equals(domainName)) {
			return _models.values().toArray(new ModelEntry[_models.size()]);
		}
		return new ModelEntry[0];
	}
}
