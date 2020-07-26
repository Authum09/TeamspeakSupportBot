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
                    boolean isInSupporterGroup = isInGroup(client.getServerGroups(),Main.supporterGroupId);
                    boolean isInList = Main.supporterList.isInList(client.getId());
                    boolean isInBusyGroup = isInGroup(client.getServerGroups(),Main.busyGroupId);
                    if (isInSupporterGroup && !isInList && !isInBusyGroup) {
                        Main.supporterList.addUser(new Supporter(client, api));
                    } else if (!isInSupporterGroup && isInList) {
                        Main.supporterList.removeUser(client.getId());
                    }
                }
            }
            System.out.println("Supporter Liste: ");
            Main.supporterList.printList();
            System.out.println("Beneficiary Liste: ");
            Main.beneficiaryList.printList();
            System.out.println("Response Liste: ");
            Main.supporterResponseList.printList();
            System.out.println("------------------------");
        }


    public static boolean isInGroup(int[] arr, int groupId) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == groupId) {
                return true;
            }
        }
        return false;
    }

}
