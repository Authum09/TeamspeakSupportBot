import com.github.theholywaffle.teamspeak3.TS3Api;

public class Activator extends Thread {

    TS3Api api;

    public Activator(TS3Api api) {
        this.api = api;
    }

    public void run() {
        SupporterDetector supporterDetector = new SupporterDetector(api);
        SupportArea supportArea = new SupportArea(api);
        while (true) {
            supporterDetector.supporterList();
            supportArea.updateSupportChannel();
            try {
                Thread.sleep(Config.listRefreshTime);
            } catch (InterruptedException e) {
                System.out.println("Thread got interrupted during sleep");
                System.exit(-1);
            }
        }
    }
}
