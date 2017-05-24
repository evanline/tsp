package main;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.io.File;


/**
 * @author Sanne Klaassen & Ian Hildebrand
 *
 */
public class Main {
	public static void main(String[] args)
	{
		new Scherm().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		new JFXPanel();

//		new Thread(() ->
//		{
//			try
//			{
//				String bip = "src/main/meep.mp3";
//				Media hit = new Media(new File(bip).toURI().toString());
//				MediaPlayer mediaPlayer = new MediaPlayer(hit);
//				mediaPlayer.play();
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}).start();
	}
}
