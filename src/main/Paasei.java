package main;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Sanne Klaassen
 * Date: 24/05/17.
 */
class Paasei extends JFrame {
	Paasei() {
		NativeInterface.open();
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("YouTube Viewer");
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
			frame.setSize(800, 600);
			frame.setLocationByPlatform(true);
			frame.setVisible(true);
		});
		NativeInterface.runEventPump();
		// don't forget to properly close native components
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				NativeInterface.close();
			}
		}));
	}

		private static JPanel getBrowserPanel() {
			JPanel webBrowserPanel = new JPanel(new BorderLayout());
			JWebBrowser webBrowser = new JWebBrowser();
			webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
			webBrowser.setBarsVisible(false);
			webBrowser.navigate("https://www.youtube.com/v/b-Cr0EWwaTk?fs=1");
			return webBrowserPanel;
		}


}

