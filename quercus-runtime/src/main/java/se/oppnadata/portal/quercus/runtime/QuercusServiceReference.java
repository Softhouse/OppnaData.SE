package se.oppnadata.portal.quercus.runtime;

import org.tdr.bootstrap.TLSReference;

public class QuercusServiceReference {

	static final String SERVICE_REFERENCE_NAME = "CurrentQuercusServiceReference";
	
	public static void set(Object serviceReference) {
		TLSReference.set(SERVICE_REFERENCE_NAME, serviceReference);
	}
	
	public static Object get() {
		return TLSReference.get(SERVICE_REFERENCE_NAME);
	}
	
	public static void clear() {
		TLSReference.clear();
	}
}
