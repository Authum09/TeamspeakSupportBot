import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Config {


    public static String host;
    public static int queryPort;
    public static String queryHostName;
    public static String queryPassword;
    public static String botName;
    public static String queryUUId;

    public static int defaultChannelId;
    public static int supportChannelId;
    // In Millis
    public static int listRefreshTime;


    public static void readYaml () {
        Yaml yaml = new Yaml();
        try {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.yaml");
            Map<String, Object> obj = yaml.load(inputStream);
            host = obj.get("host").toString();
            queryPort = (int) obj.get("queryPort");
            queryHostName = obj.get("queryName").toString();
            queryPassword = obj.get("queryPassword").toString();
            botName = obj.get("queryUserName").toString();
            queryUUId = obj.get("queryUUID").toString();
            defaultChannelId = (int) obj.get("defaultChannelId");
            supportChannelId = (int) obj.get("supportChannelId");
            listRefreshTime = (int) obj.get("listRefreshTime");
        } catch (NullPointerException e){
            System.out.println("Config invalid");
            System.exit(-1);
        }
    }
}
