package util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class OnlineCounterListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		OnlineCounter.raise();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		OnlineCounter.reduce();

	}

}
