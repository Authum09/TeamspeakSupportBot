import java.util.ArrayList;

public class userList<T extends User> {

    protected ArrayList<T> users;

    public userList() {
        users = new ArrayList<>();
    }

    public synchronized void addUser(T user) {
        users.add(user);
    }

    public boolean isInList(int clientId) {
        for (T user : users) {
            if ((user.client.getId() == clientId)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void removeUser(User userOb) {
        users.remove(userOb);
    }

    public synchronized void removeUser(int clientId) {
        for (T user : users) {
            if (user.client.getId() == clientId) {
                users.remove(user);
                break;
            }
        }
    }


    public int size() {
        return users.size();
    }

    public T getFirstObjectFromList() {
        return users.get(0);
    }

    public T getObjectByClientId(int clientId) {
        for (T user : users) {
            if (user.client.getId() == clientId) {
                return user;
            }
        }
        return null;
    }

}
