import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;

public class Config {


    public static String host;
    public static int queryPort;
    public static String queryHostName;
    public static String queryPassword;
    public static String botName;
    public static int virtualServerId;

    public static String supportMessage;
    public static int defaultChannelId;
    public static int supportChannelId;
    public static int supporterGroupId;
    public static int busyGroupId;

    public static int listRefreshTime;


    public static void readYaml() {
        Yaml yaml = new Yaml();
        try {
            File config = new File("config.yaml");
            InputStream inputStream = new FileInputStream(config);
            Map<String, Object> obj = yaml.load(inputStream);
            host = obj.get("host").toString();
            queryPort = (int) obj.get("queryPort");
            queryHostName = obj.get("queryName").toString();
            queryPassword = obj.get("queryPassword").toString();
            botName = obj.get("botUserName").toString();
            virtualServerId = (int) obj.get("virtualServerId");
            supportMessage = obj.get("supportMessage").toString();
            supporterGroupId = (int) obj.get("supporterGroupId");
            busyGroupId = (int) obj.get("busyGroupId");
            defaultChannelId = (int) obj.get("defaultChannelId");
            supportChannelId = (int) obj.get("supportChannelId");

            listRefreshTime = (int) obj.get("listRefreshTime");
        } catch (NullPointerException e) {
            System.out.println("Config invalid");
            System.exit(-1);
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found");
            System.exit(-1);
        }
    }
}
