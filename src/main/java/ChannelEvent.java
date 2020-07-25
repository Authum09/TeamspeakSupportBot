import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class ChannelEvent extends Thread {

    TS3Api api;

    public ChannelEvent(TS3Api api) {
        this.api = api;
    }

    public void run() {

        api.registerEvent(TS3EventType.CHANNEL);
        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onClientMoved(ClientMovedEvent e) {
                Client client = api.getClientByNameExact(api.getClientInfo(e.getClientId()).getNickname(), false);
                if (!client.isServerQueryClient()) {
                    if (e.getTargetChannelId() == 2) {
                        Beneficiary user = new Beneficiary(client, api);
                        Main.beneficiaryList.addUser(user);
                        Main.beneficiaryList.printList();
                        Main.supporterList.getFirstObjectFromList().sendMessage(user.client.getNickname() + " möchte supportet werden. \n" +
                                "Bist du bereit dafür?");
                        Main.supporterList.getFirstObjectFromList().beneficiary = user;
                    } else {
                        Main.beneficiaryList.removeUser(e.getClientId());
                    }
                }
            }


        });

    }

}
