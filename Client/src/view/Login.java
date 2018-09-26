package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Login extends VBox {

	private PaperFootball main;
	
	private Label usernamelabel;
	private TextArea usernamearea;
	private Label passwordlabel;
	private PasswordField passwordfield;
	private Button loginbutton;
	
	
	public Login(PaperFootball main) {
		super();
		
		
		this.main = main;
		
		
		this.setPrefWidth(8 * PaperFootball.LINE_LENGTH + PaperFootball.POINT_RADIUS + PaperFootball.BUFFER_AROUND_FIELD);
		this.setPrefHeight(12 * PaperFootball.LINE_LENGTH + PaperFootball.POINT_RADIUS + PaperFootball.BUFFER_AROUND_FIELD);
		this.setMaxWidth(8 * PaperFootball.LINE_LENGTH + PaperFootball.POINT_RADIUS + PaperFootball.BUFFER_AROUND_FIELD);
		this.setMaxHeight(12 * PaperFootball.LINE_LENGTH + PaperFootball.POINT_RADIUS + PaperFootball.BUFFER_AROUND_FIELD);
		
		
		this.setAlignment(Pos.CENTER);
		
		
		usernamelabel = new Label("Enter your username");
		
		
		usernamearea = new TextArea();
		usernamearea.setPromptText("Username");
		usernamearea.setMaxWidth(4 * PaperFootball.LINE_LENGTH);
		usernamearea.setMaxHeight(PaperFootball.LINE_LENGTH);
		usernamearea.setMinHeight(PaperFootball.LINE_LENGTH);
		usernamearea.setFont(new Font(22));
		
		passwordlabel = new Label("Enter your password");
		
		passwordfield = new PasswordField();
		passwordfield.setPromptText("Password");
		passwordfield.setFont(new Font(22));
		passwordfield.setMaxWidth(4 * PaperFootball.LINE_LENGTH);
		passwordfield.setMaxHeight(PaperFootball.LINE_LENGTH);
		passwordfield.setMinHeight(PaperFootball.LINE_LENGTH);
		
		loginbutton = new Button("Log in");
		loginbutton.setOnAction(e -> {
			String loginmsg = "LOGIN [" + usernamearea.getText() + ";" + passwordfield.getText() + "]";
			

//			main.client().send(loginmsg);
			System.out.println(loginmsg);
			this.main.changeToJoinGame();
			this.setPlayerName(usernamearea.getText());
		});
		
		
		
		Label filler1 = new Label();
		filler1.setMinHeight(50);
		filler1.setMaxHeight(50);
		
		Label filler2 = new Label();
		filler2.setMinHeight(50);
		filler2.setMaxHeight(50);
		
		this.getChildren().addAll(usernamelabel, usernamearea, filler1, passwordlabel, passwordfield, filler2, loginbutton);
		
	}
	
	
	public void setPlayerName(String playerName) {
		main.setPlayerName(playerName);
	}
	
	
	
	
}
