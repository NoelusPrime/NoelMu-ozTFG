package plugin;

import org.tzi.use.api.UseSystemApi;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.tzi.use.uml.sys.MObject;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

/**
 * @author Paula Mu&ntilde;oz - University of M&atilde;laga
 * <p>
 * Plugin's main class
 */
public class DigitalTwinConnectorPlugin implements IPluginActionDelegate {

	

    /**
     * Default constructor
     */
    public DigitalTwinConnectorPlugin() {
    	
    }

    /**
     * This is the Action Method called from the Action Proxy
     *
     * @param pluginAction This is the reference to the current USE running
     *                     instance.
     */
    public void performAction(IPluginAction pluginAction) {

        UseSystemApi api = UseSystemApi.create(pluginAction.getSession());
        Set<MObject> setObjects = api.getSystem().state().allObjects();
        
        try {
			CSVWriter csvWriter = new CSVWriter(new FileWriter("example.csv"));
			for (MObject m : setObjects) {
					//Me interesan los atributos center, angle, pos, length, width y height
					//Aquellos objetos que no ocupen un espacio y sean puntos, pues simplemente se les guardaran los atributos
					//length, width y height en cero
				csvWriter.writeNext(new String[]{m.name(),m.value().toString()});
				
	        }
		} catch (IOException e) {
			System.out.println("Hubo problemas: "+e.getMessage());
		}
    }

}