package plugin;

import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseModelApi;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.runtime.gui.IPluginAction;
import org.tzi.use.runtime.gui.IPluginActionDelegate;
import org.tzi.use.uml.mm.MAttribute;
import org.tzi.use.uml.mm.MClass;
import org.tzi.use.uml.ocl.value.Value;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.MObjectState;
import org.tzi.use.uml.sys.MOperationCall;
import org.tzi.use.uml.sys.MSystemException;
import org.tzi.use.uml.sys.soil.MStatement;

import com.opencsv.CSVWriter;

//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Paula Mu&ntilde;oz - University of M&atilde;laga
 * <p>
 * Plugin's main class
 */
public class DigitalTwinConnectorPlugin implements IPluginActionDelegate {

    private UseSystemApi api;
    private UseModelApi api2;
    //private JedisPool jedisPool;
    //private ExecutorService executor;
    //private boolean shutDown;
    //private OutPubService outPublisher;
    //private OutPubService commandOutPublisher;
    //private InPubService inPublisher;

    /**
     * Default constructor
     */
    public DigitalTwinConnectorPlugin() {
        //this.executor = Executors.newFixedThreadPool(3);
        //this.shutDown = true;
    }

    /**
     * This is the Action Method called from the Action Proxy
     *
     * @param pluginAction This is the reference to the current USE running
     *                     instance.
     */
    public void performAction(IPluginAction pluginAction) {
    	
    	api = UseSystemApi.create(pluginAction.getSession());
    	//api2 = new UseModelApi(api.getSystem().model());
    	
    	try {
    		//api2.
			saveDataOnCSV(getSnapshots(api),"example.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//api2 = new UseModelApi(api.getSystem().model());
    	
    	//try {
			//Scanner sc = new Scanner(new File("example.csv"));
			//while(sc.hasNextLine()) {
				//String comando = sc.nextLine();
				//MStatement m;
    	/*
				MOperationCall s;
				
				try {
					api.getSystem().enterQueryOperation(null, null, false);
				} catch (MSystemException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				//EvalContext;
				//api.getSystem().enterQueryOperation(null, null, false);
				
				//api.getSystem().state().
				//m.
				//api.getSystem().execute(null);
			//}
		//} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//} catch (MSystemException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
    	//
    	/*
    	try {
			CSVWriter w = new CSVWriter(new FileWriter("example.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	*/
    	/*
    	api = UseSystemApi.create(pluginAction.getSession());
    	//getObjects(api);
    	try {
    		saveDataOnCSV(getSnapshots(api),"example.csv");
			deleteSnapshots(api);
		} catch (UseApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	/*
    	 While(hayMasComandos){
    	 	comando = siguienteComando();
    	 	ejecutar(comando);
    	 	if (comando.esTick()) {
    	 		getSnapshots();						--COMPLETADO
    	 		guardarDatosSnapshotEnCSV();
    	 		deleteSnapshots();					--COMPLETADO
    	 	}
    	 }
    	 */
    	
    	
        /*if (shutDown) {
            api = UseSystemApi.create(pluginAction.getSession());
            jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");

            checkConnectionWithDatabase();
            //this.outPublisher = new OutPubService(DTPubSub.DT_OUT_CHANNEL, api, jedisPool, 5000, new OutputSnapshotsManager());
            //this.commandOutPublisher = new OutPubService(DTPubSub.COMMAND_OUT_CHANNEL, api, jedisPool, 5000, new CommandsManager());
            //this.inPublisher = new InPubService(DTPubSub.DT_IN_CHANNEL, jedisPool, 5000);

            if (executor.isShutdown()) {
                executor = Executors.newFixedThreadPool(3);
            }

            //executor.submit(outPublisher);
            //executor.submit(commandOutPublisher);
            //executor.submit(inPublisher);

            new Thread(new SubService(api, jedisPool, DTPubSub.DT_OUT_CHANNEL), "subscriber " + DTPubSub.DT_OUT_CHANNEL + " thread").start();
            new Thread(new SubService(api, jedisPool, DTPubSub.COMMAND_OUT_CHANNEL), "subscriber " + DTPubSub.COMMAND_OUT_CHANNEL + " thread").start();
            //new Thread(new SubService(api, jedisPool, DTPubSub.DT_IN_CHANNEL), "subscriber " + DTPubSub.DT_IN_CHANNEL + " thread").start();
            shutDown = false;
        } else {
            outPublisher.stop();
            commandOutPublisher.stop();
            //inPublisher.stop();
            shutDown = true;
            System.out.println("[INFO-DT] Connection ended successfully");*/
        }
	
	public List<MObjectState> getSnapshots(UseSystemApi api) {
		List<MObjectState> list = new ArrayList<>();
		for (MObject o : api.getSystem().state().allObjects()) {
			if (o.cls().name().equals("Snapshot")) {
				MObjectState m = o.state(api.getSystem().state());
				list.add(m);
			}
		}
		return list;
    }
	
	public void deleteSnapshots(UseSystemApi api) throws UseApiException {
		List<MObjectState> list = getSnapshots(api);
		for (MObjectState m : list) {
			api.deleteObject(m.object().name());
		}
		list.clear();
	}
	
	public void saveDataOnCSV(List<MObjectState> dataList,String fileName) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(fileName));
		Map<MAttribute, Value> map = new HashMap<>();
		for (MObjectState objectState : dataList) {
			writer.writeNext(new String[] {objectState.object().toString()});
			map = objectState.attributeValueMap();
			Iterator<MAttribute> iter = map.keySet().iterator();
			while(iter.hasNext()) {
				MAttribute attribute = iter.next();
				if (attribute.type().toString().equals("Environment")) {
					//api.getObject("environment1").value();
				}
				writer.writeNext(new String[] {attribute.toString(),map.get(attribute).toString(),map.get(attribute).type().toString()});
			}
			//map.clear();
		}
		writer.close();
	}
}