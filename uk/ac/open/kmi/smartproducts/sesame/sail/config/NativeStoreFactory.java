/*
 * Copyright Aduna (http://www.aduna-software.com/) (c) 2007.
 *
 * Licensed under the Aduna BSD-style license.
 */
package uk.ac.open.kmi.smartproducts.sesame.sail.config;

import org.openrdf.sail.Sail;
import org.openrdf.sail.config.SailConfigException;
import org.openrdf.sail.config.SailFactory;
import org.openrdf.sail.config.SailImplConfig;
import uk.ac.open.kmi.smartproducts.sesame.sail.AndroidNativeStore;

/**
 * A {@link SailFactory} that creates {@link AndroidNativeStore}s based on RDF
 * configuration data.
 * 
 * @author Arjohn Kampman
 */
public class NativeStoreFactory implements SailFactory {

	/**
	 * The type of repositories that are created by this factory.
	 * 
	 * @see SailFactory#getSailType()
	 */
	public static final String SAIL_TYPE = "openrdf:NativeStore";

	/**
	 * Returns the Sail's type: <tt>openrdf:NativeStore</tt>.
	 */
	public String getSailType() {
		return SAIL_TYPE;
	}

	public SailImplConfig getConfig() {
		return new NativeStoreConfig();
	}

	public Sail getSail(SailImplConfig config)
		throws SailConfigException
	{
		if (!SAIL_TYPE.equals(config.getType())) {
			throw new SailConfigException("Invalid Sail type: " + config.getType());
		}

		AndroidNativeStore nativeStore = new AndroidNativeStore();

		if (config instanceof NativeStoreConfig) {
			NativeStoreConfig nativeConfig = (NativeStoreConfig)config;

			nativeStore.setTripleIndexes(nativeConfig.getTripleIndexes());
			nativeStore.setForceSync(nativeConfig.getForceSync());

			if (nativeConfig.getValueCacheSize() >= 0) {
				nativeStore.setValueCacheSize(nativeConfig.getValueCacheSize());
			}
			if (nativeConfig.getValueIDCacheSize() >= 0) {
				nativeStore.setValueIDCacheSize(nativeConfig.getValueIDCacheSize());
			}
			if (nativeConfig.getNamespaceCacheSize() >= 0) {
				nativeStore.setNamespaceCacheSize(nativeConfig.getNamespaceCacheSize());
			}
			if (nativeConfig.getNamespaceIDCacheSize() >= 0) {
				nativeStore.setNamespaceIDCacheSize(nativeConfig.getNamespaceIDCacheSize());
			}
		}

		return nativeStore;
	}
}
