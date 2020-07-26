import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;

import java.util.HashMap;
import java.util.Map;

public class SupportArea {

    private TS3Api api;
    public SupportArea(TS3Api api) {
        this.api = api;
    }

    public void createSubChannel(String channelName) {
        Map<ChannelProperty, String> properties = new HashMap<>();
        properties.put(ChannelProperty.CHANNEL_FLAG_TEMPORARY,"1");
        properties.put(ChannelProperty.CHANNEL_FLAG_MAXCLIENTS_UNLIMITED, "1");
        properties.put(ChannelProperty.CPID,"2");

        api.createChannel(channelName,properties);

    }

    public void updateSupportChannel () {
        int maxClients = api.getChannelInfo(2).getMaxClients();
        int supporterOnline = Main.supporterList.size();
        String channelName = api.getChannelInfo(2).getName();

        if (!channelName.contains(supporterOnline == 0 ? "nicht" : "" + supporterOnline)) {
            Map<ChannelProperty, String> properties = new HashMap<>();
            properties.put(ChannelProperty.CHANNEL_NAME,"Support ["+ (supporterOnline == 0 ? "nicht" : "" + supporterOnline) + " verfügbar]");
            api.editChannel(2, properties);
        }

        if (Main.supporterList.size() == 0) {
            Map<ChannelProperty, String> properties = new HashMap<>();

            properties.put(ChannelProperty.CHANNEL_MAXCLIENTS, "0");
            if (maxClients != 0) {
                api.editChannel(2, properties);
            }
        } else if (Main.supporterList.size() > 0) {
            Map<ChannelProperty, String> properties = new HashMap<>();
            properties.put(ChannelProperty.CHANNEL_MAXCLIENTS, "10");
            if (maxClients == 0) {
                api.editChannel(2, properties);
            }
        }

    }
}
