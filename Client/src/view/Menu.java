package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Menu allowing the user to choose between offline play, hosting a match or joining a match
 * @author Michał Lipiński
 * @date 10.09.2018
 * @updated 10.09.2018 version 0.3
 */
public class Menu extends HBox{

	/** Instance of main class to delegate user's selection */
	private final PaperFootball main;
	
	/** Button changes the screen to the offline game setup screen */
	private final Button playOffline;
	
	/** Button changes the screen to the join Game screen */
	private final Button joinGame;
	
	
	public Menu(PaperFootball main) {
		super();
		
		this.main = main;
		
		
		this.setPrefWidth(8 * PaperFootball.LINE_LENGTH + PaperFootball.POINT_RADIUS + PaperFootball.BUFFER_AROUND_FIELD);
		this.setPrefHeight(12 * PaperFootball.LINE_LENGTH + PaperFootball.POINT_RADIUS + PaperFootball.BUFFER_AROUND_FIELD);
		this.setMaxWidth(8 * PaperFootball.LINE_LENGTH + PaperFootball.POINT_RADIUS + PaperFootball.BUFFER_AROUND_FIELD);
		this.setMaxHeight(12 * PaperFootball.LINE_LENGTH + PaperFootball.POINT_RADIUS + PaperFootball.BUFFER_AROUND_FIELD);
		
		this.setAlignment(Pos.CENTER);
		
		playOffline = new Button("Play offline");
		playOffline.setOnAction(e -> {
			this.main.changeToOfflineGame();
		});
		
		joinGame = new Button("Play online");
		joinGame.setOnAction(e -> {
			this.main.showLogin();
			connect();
		});
		
		
		this.getChildren().addAll(playOffline, joinGame);
	}
	
	/**
	 * Connect to the game server with the given ip address
	 * @param ip
	 */
	private void connect() {
		main.connectToServer();
	}
	
}
