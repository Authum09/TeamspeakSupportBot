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
                    if (e.getTargetChannelId() == Config.supportChannelId) {
                        Beneficiary user = new Beneficiary(client, api);
                        Main.beneficiaryList.addUser(user);
                        Main.beneficiaryList.printList();
                        Supporter supporter = Main.supporterList.getFirstObjectFromList();
                        supporter.sendMessage("\n" + user.client.getNickname() + " möchte supportet werden. \n" +
                                "Bist du bereit dafür?");
                        supporter.putBusyGroup();
                        supporter.beneficiary = user;
                        Main.supporterList.removeUser(supporter);
                        Main.supporterResponseList.addUser(supporter);
                    } else {
                        Main.beneficiaryList.removeUser(e.getClientId());
                    }
                    int[] groupList = client.getServerGroups();
                    boolean isInBusyGroup = SupporterDetector.isInGroup(groupList,Main.busyGroupId);
                    if (e.getInvokerId() == -1 && isInBusyGroup) {
                        api.removeClientFromServerGroup(Main.busyGroupId, client.getDatabaseId());
                    }
                }


            }


        });

    }

}
