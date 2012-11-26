package se.oppnadata.portal.quercus.runtime;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.tdr.bootstrap.GlobalReference;
import org.tdr.bootstrap.TLSReference;

import com.caucho.quercus.env.Env;
import com.caucho.quercus.module.AbstractQuercusModule;

public class ServiceModule extends AbstractQuercusModule {

	// TODO: Add commons logging here!!
	private BundleContext bundleContext = (BundleContext) GlobalReference.get("osgi/bundleContext");

	public Object service_ref(Env env, String serviceName) {
		try {
			ServiceReference[] serviceReferences = bundleContext.getAllServiceReferences(null, null);
			for ( ServiceReference serviceRef : serviceReferences ) {
				String name = (String) serviceRef.getProperty("serviceName");
				if ( name != null && serviceName.equals(name) ) {
					Object service = this.bundleContext.getService(serviceRef);
					QuercusServiceReference.set(service);
					return service;
				}
			}
		}
		catch ( Exception e ) {}
		return null;
	}
	
}
