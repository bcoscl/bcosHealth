package cl.bcos.utils;

import cl.bcos.servlet.attentionList.ServletAddAttentionList;
import org.apache.log4j.Logger;

public class ConstantsPath {

    private static final Logger Log = Logger.getLogger(ServletAddAttentionList.class);
    
    public static String PATH;
    public static String ENDPOINT_PATH = "URLPATH";
    public static String CONTEXT;

    public ConstantsPath() {
        
        PATH = System.getenv(ENDPOINT_PATH);// se comenta para que apunte  produccion
        
        if (PATH != null && PATH.contains("localhost")) {
            CONTEXT = "http://" + PATH;
        } else {
            CONTEXT = "https://api.health.bcos.cl";
        }
        Log.info("URL PATH : "+CONTEXT);

    }

}
