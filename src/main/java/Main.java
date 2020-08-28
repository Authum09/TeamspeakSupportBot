import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;

public class Main {


    public static userList<Beneficiary> beneficiaryList = new userList<>();
    public static userList<Supporter> supporterList = new userList<>();
    public static userList<Supporter> supporterResponseList = new userList<>();

    public static void main(String[] args) {

        Config.readYaml();
        final TS3Config config = new TS3Config();
        config.setHost(Config.host);
        config.setQueryPort(Config.queryPort);
        config.setFloodRate(TS3Query.FloodRate.UNLIMITED);

        final TS3Query query = new TS3Query(config);
        query.connect();


        TS3Api api = query.getApi();
        api.login(Config.queryHostName, Config.queryPassword);

        api.selectVirtualServerById(Config.virtualServerId);
        api.setNickname(Config.botName);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            api.setNickname(Config.botName + "@EXIT");
            query.exit();
        }));


        ChannelEvent channelEvent = new ChannelEvent(api);
        channelEvent.start();

        Activator activator = new Activator(api);
        activator.start();

        MessageListener messageListener = new MessageListener(api);
        messageListener.start();


    }
}
