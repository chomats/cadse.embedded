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

import java.util.HashMap;
import java.util.HashSet;

import fr.imag.adele.cadse.core.CadseRuntime;
import fr.imag.adele.cadse.core.LogicalWorkspace;
import fr.imag.adele.cadse.core.WSModelState;
import fr.imag.adele.cadse.core.impl.internal.CadseDomainImpl;
import fr.imag.adele.cadse.workspace.si.defaultclassreferencer.DefaultClassReferencer;
import fr.imag.adele.fede.workspace.as.initmodel.ErrorWhenLoadedModel;
import fr.imag.adele.fede.workspace.si.initmodel.InitModel;
import fr.imag.adele.fede.workspace.si.persistence.Persistence;
import fr.imag.adele.teamwork.db.impl.ModelVersionDBImpl2;

public class EmbeddedCadse {
	protected Persistence persistence;
	protected InitModel initModel;
	protected CadseDomainImpl cadseDomainImpl;
	protected EmbeddedPlatform mainPlatform;
	private EmbeddedBundleContext embeddedBundleContext;
	ModelVersionDBImpl2 modelVersionDBImpl2;
	EmbeddedFindModel embeddedFindModel;
	private boolean readPersistence;

	public EmbeddedCadse(boolean readPersistence) {
		this.readPersistence = readPersistence;
		persistence = new Persistence();
		initModel = new InitModel();
		initModel.classReferencer = new DefaultClassReferencer();
		cadseDomainImpl = new CadseDomainImpl();
		cadseDomainImpl._initModelService = initModel;
		mainPlatform = new EmbeddedPlatform();
		cadseDomainImpl._platformService = mainPlatform;
		embeddedBundleContext = new EmbeddedBundleContext();
		mainPlatform._bundleContext = embeddedBundleContext;
		modelVersionDBImpl2 = new ModelVersionDBImpl2(embeddedBundleContext);
		cadseDomainImpl._modelDB2Service = modelVersionDBImpl2;
		initModel.workspaceCU = cadseDomainImpl;
		initModel.platformService = mainPlatform;
		embeddedFindModel = new EmbeddedFindModel();
		initModel.findModel = embeddedFindModel;
	}

	public void start() throws ErrorWhenLoadedModel {
		cadseDomainImpl.start();
		modelVersionDBImpl2.start();
		embeddedFindModel.start();
		
		// load all cadse
		CadseRuntime[] cadsePresents = initModel.loadCadses();

		// find cadse to run
		HashMap<String, CadseRuntime> crByName = new HashMap<String, CadseRuntime>();
		for (CadseRuntime cr : cadsePresents) {
			crByName.put(cr.getName(), cr);
		}

		String[] cadsesNameSaved = null;
		if (readPersistence)
			cadsesNameSaved = persistence.getCadsesName();
		HashSet<CadseRuntime> toRun = new HashSet<CadseRuntime>();

		if (cadsesNameSaved != null) {
			for (String name : cadsesNameSaved) {
				name = name.trim();
				if (name.length() == 0) {
					continue;
				}
				CadseRuntime cr = crByName.get(name);
				if (cr != null) {
					toRun.add(cr);
				}
			}
		}
		// look the properties CADSES_TO_EXECUTE
		String addCadseName = System
				.getProperty(LogicalWorkspace.CADSES_TO_EXECUTE);
		if (addCadseName != null) {
			String[] addCadseNameArray = addCadseName.split(",");

			for (String name : addCadseNameArray) {
				name = name.trim();
				if (name.length() == 0) {
					continue;
				}
				CadseRuntime cr = crByName.get(name);
				if (cr != null) {
					toRun.add(cr);
				} else {
					System.err.println("*** NOT FOUND CADSE " + name + " ***");
				}
			}
		}
		// load model
		initModel.executeCadses(toRun.toArray(new CadseRuntime[toRun.size()]));

		// cadsesNameSaved
		LogicalWorkspace theCurrentModel = cadseDomainImpl
				.getLogicalWorkspace();

		theCurrentModel.setState(WSModelState.LOAD);
		// load persistence
		if (readPersistence && cadsesNameSaved != null) {
			// if exits cadse to load
			persistence.load();
		}
		theCurrentModel.setState(WSModelState.RUN);

	}

	public void stop() {
		cadseDomainImpl.stop();
	}

	public Persistence getPersistence() {
		return persistence;
	}

	public InitModel getInitModel() {
		return initModel;
	}

	public CadseDomainImpl getCadseDomain() {
		return cadseDomainImpl;
	}

	public EmbeddedPlatform getMainPlatform() {
		return mainPlatform;
	}

	public EmbeddedBundleContext getEmbeddedBundleContext() {
		return embeddedBundleContext;
	}

	public ModelVersionDBImpl2 getModelVersionDBImpl2() {
		return modelVersionDBImpl2;
	}

	public EmbeddedFindModel getEmbeddedFindModel() {
		return embeddedFindModel;
	}
}
