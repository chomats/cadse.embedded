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
		return true;
	}

	@Override
	public boolean isResourceStarted() {
		return true;
	}

	@Override
	public boolean inDevelopmentMode() {
		return false;
	}

	@Override
	public File getLocation() {
		return null;
	}

	@Override
	public File getLocation(boolean wait) {
		return null;
	}

	@Override
	public List<Bundle> findBundlePrefix(String prefix) {
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
	}

	@Override
	public void addListener(IPlatformListener l) {		
	}

	@Override
	public void activateIDE() {
	}

	@Override
	public String getRessourceName(ContentItem contentItem) {
		return null;
	}
}
