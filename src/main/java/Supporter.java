import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class Supporter extends User{

    public Beneficiary beneficiary;

    public Supporter(Client client, TS3Api api) {
        super(client,api);
    }


    public void sendMessage(String message) {
        api.sendPrivateMessage(client.getId(),message);
    }

    public void putBusyGroup() {
        api.addClientToServerGroup(Config.busyGroupId,client.getDatabaseId());
    }


}
