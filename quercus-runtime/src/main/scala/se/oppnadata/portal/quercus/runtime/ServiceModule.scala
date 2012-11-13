package se.oppnadata.portal.quercus.runtime

import scala.collection.JavaConversions._
import com.caucho.quercus.module.AbstractQuercusModule
import com.caucho.quercus.env.Env
import org.osgi.framework.BundleContext
import org.tdr.bootstrap.GlobalReference
import org.osgi.framework.ServiceReference

class ServiceModule extends AbstractQuercusModule {

  println("Initializing OSGi Service module...")
  
  val bundleContext = GlobalReference.get("osgi/bundleContext").asInstanceOf[BundleContext]
  
  def service_ref(env : Env, serviceName : String) : Any = {
  
    // TODO: Use a service tracker here instead?
    // TODO: Write this in Java instead? Does it give anything to have it in Scala here...??
    
    println("Finding reference for service: " + serviceName)
    try {
		var serviceReferences = bundleContext.getAllServiceReferences(null, null)
		for ( serviceRef <- serviceReferences ) { 
			var name = serviceRef.getProperty("serviceName").asInstanceOf[String]
			if ( serviceName.equals(name) ) {
				return this.bundleContext.getService(serviceRef)
			}
		}
	}
	catch {
	  case e : Exception => 
	} 
    return null
  }


}