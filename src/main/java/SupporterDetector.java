import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

import java.util.List;

public class SupporterDetector extends Thread {

    TS3Api api;

    public SupporterDetector(TS3Api api) {
        this.api = api;
    }


    public void supporterList() {
        List<Client> clients = api.getClients();

        for (Client client : clients) {
            if (!client.isServerQueryClient()) {
                boolean isInSupporterGroup = isInGroup(client.getServerGroups(), Config.supporterGroupId);
                boolean isInList = Main.supporterList.isInList(client.getId());
                boolean isInBusyGroup = isInGroup(client.getServerGroups(), Config.busyGroupId);
                if (isInSupporterGroup && !isInList && !isInBusyGroup) {
                    Main.supporterList.addUser(new Supporter(client, api));
                } else if (!isInSupporterGroup && isInList) {
                    Main.supporterList.removeUser(client.getId());
                }
            }
        }
    }


    public static boolean isInGroup(int[] arr, int groupId) {
        for (int j : arr) {
            if (j == groupId) {
                return true;
            }
        }
        return false;
    }

}
