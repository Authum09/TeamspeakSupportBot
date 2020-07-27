import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class Main {

    public static userList<Beneficiary> beneficiaryList = new userList<>();
    public static userList<Supporter> supporterList = new userList<>();
    public static userList<Supporter> supporterResponseList = new userList<>();
    public static int supporterGroupId = 9;
    public static int busyGroupId = 10;
    public static int queryClientId;

    public static void main(String[] args) {
        Config.readYaml();
        final TS3Config config = new TS3Config();
        config.setHost(Config.host);
        config.setQueryPort(Config.queryPort);
        config.setFloodRate(TS3Query.FloodRate.UNLIMITED);

        final TS3Query query = new TS3Query(config);
        query.connect();

        TS3Api api = query.getApi();
        api.login(Config.queryHostName,Config.queryPassword);

        api.selectVirtualServerById(1);
        api.setNickname(Config.botName);


        Client querry = api.getClientByUId(Config.queryUUId);
        queryClientId = querry.getId();

        ChannelEvent channelEvent = new ChannelEvent(api);
        channelEvent.start();

        Activator activator = new Activator(api);
        activator.start();

        MessageListener messageListener = new MessageListener(api);
        messageListener.start();

    }
}
