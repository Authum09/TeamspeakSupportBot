import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;

public class Main {

    public static userList<Beneficiary> beneficiaryList = new userList<>();
    public static userList<Supporter> supporterList = new userList<>();
    public static int supporterGroupId = 9;

    public static void main(String[] args) {
        final TS3Config config = new TS3Config();
        config.setHost("authum.de");
        config.setFloodRate(TS3Query.FloodRate.UNLIMITED);

        final TS3Query query = new TS3Query(config);
        query.connect();

        TS3Api api = query.getApi();
        api.login("support","vFbwcluL");

        api.selectVirtualServerById(1);
        api.setNickname("SupportBot");

        SupportArea supportArea = new SupportArea(api);
        supportArea.createSubChannel("tst");

        ChannelEvent channelEvent = new ChannelEvent(api);
        channelEvent.start();

        Activator activator = new Activator(api);
        activator.start();

        MessageListener messageListener = new MessageListener(api);
        messageListener.start();

    }
}
