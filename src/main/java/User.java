import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class User {

    protected TS3Api api;
    protected Client client;
    protected int userId;

    public User(Client client, TS3Api api) {
        userId = client.getId();
        this.api = api;
        this.client = client;
    }


    public void move(int channelId) {
        api.moveClient(userId, channelId);
    }

}
