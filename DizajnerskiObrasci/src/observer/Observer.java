package observer;

import java.util.ArrayList;

public interface Observer {
	void updateUI(ArrayList<Boolean> enableList);
	void saveLog(String log);
}
