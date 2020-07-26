import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Channel;

public class MessageListener extends Thread{

    TS3Api api;

    public MessageListener(TS3Api api) {
        this.api = api;
    }

    public void run() {
        api.registerEvent(TS3EventType.TEXT_PRIVATE);
        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                String msg = e.getMessage().toLowerCase();

                if (msg.contains("ja")) {
                    SupportArea supportArea = new SupportArea(api);
                    Supporter supporter = Main.supporterResponseList.getObjectByClientId(e.getInvokerId());
                    String name = "Support: " + supporter.beneficiary.client.getNickname();
                    supportArea.createSubChannel(name);
                    Channel channel = api.getChannelByNameExact(name,false);
                    supporter.move(channel.getId());
                    supporter.beneficiary.move(channel.getId());
                    api.moveClient(Main.queryClientId,Config.defaultChannelId);
                    Main.supporterResponseList.removeUser(supporter);
                }
            }
        });
    }
}
