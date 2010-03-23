package fr.imag.adele.cadse.embedded;

import fr.imag.adele.cadse.core.impl.internal.CadseDomainImpl;
import fr.imag.adele.cadse.workspace.si.defaultclassreferencer.DefaultClassReferencer;
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
	
	public EmbeddedCadse() {
		persistence  = new Persistence();
		initModel = new InitModel();
		initModel.classReferencer = new DefaultClassReferencer();
		cadseDomainImpl = new CadseDomainImpl();
		cadseDomainImpl._initModelService = initModel;
		mainPlatform = new EmbeddedPlatform();
		embeddedBundleContext = new EmbeddedBundleContext();
		mainPlatform._bundleContext = embeddedBundleContext;
		modelVersionDBImpl2 = new ModelVersionDBImpl2(mainPlatform._bundleContext);
		cadseDomainImpl._modelDB2Service = modelVersionDBImpl2;
		initModel.workspaceCU = cadseDomainImpl;
		initModel.platformService = mainPlatform;
		embeddedFindModel = new EmbeddedFindModel();
		initModel.findModel = embeddedFindModel;
	}
	
	public void start() {
		cadseDomainImpl.start();
		modelVersionDBImpl2.start();
		initModel.loadCadses();
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
