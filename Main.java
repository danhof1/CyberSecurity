package Main;
import javafx.application.Application; 

//Not JavaFX
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import Backend.*;
import Backend.MainThings.actionBranch;
import Backend.Scans.CountFiles;
import Backend.Scans.Scan;
import Backend.Scans.ScanManager;
//Scene stuff
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;


//Shape stuff
import javafx.scene.paint.Color; 
import javafx.scene.shape.StrokeType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

//Text
import javafx.scene.text.*; 
import javafx.scene.text.Font;

//User interactions
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;  

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

//Images
import javafx.scene.effect.ImageInput;  
import javafx.scene.image.Image;  

//Animations
import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.scene.transform.Rotate;  
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;  
import javafx.animation.FadeTransition;  
import javafx.util.Duration;  
import Frontend.eFXtend.*;

public class Main extends Application 
{ 
  @Override
  public void start(Stage stage) throws Exception, InterruptedException, IOException
  {
	  //~~~~~~~~~~~~~~~~~~~~~~ VARIABLES ~~~~~~~~~~~~~~~~~~~~~
	  //Variables > STAGE
	  stage.setResizable(false);
	  String verNum = "1.03.00";
	
	  stage.setTitle("R.A.T. Trap Antivirus v" + verNum);
	
	  Rectangle2D screenSize = Screen.getPrimary().getBounds();
  
	  //Variables > GROUPS & SCENES
	  //Sets GUI to 3/4 of screen size
	  int sceneWidth = (int)(screenSize.getWidth() * (3.0/4)); 
	  int sceneHeight = (int)((9.0 / 16) * sceneWidth);  
	  	  
	  ArrayList<Scene> scenes = new ArrayList<Scene>();
	  
	  Group rootMM = new Group();
	  Scene menuScene = new Scene(rootMM, sceneWidth, sceneHeight);
	  //scenes.add(menuScene);
	  
	  Group rootLI = new Group();
	  Scene logInScene = new Scene(rootLI, sceneWidth, sceneHeight);
	  //scenes.add(logInScene);

	  Group rootCA = new Group();
	  Scene createAcctScene = new Scene(rootCA, sceneWidth, sceneHeight);
	  //scenes.add(createAcctScene);

	  Group rootSC = new Group(); //todo
	  Scene scanScene = new Scene(rootSC, sceneWidth, sceneHeight);
	  //scenes.add(scanScene);

	  Group rootST = new Group();
	  Scene statusScene = new Scene(rootST, sceneWidth, sceneHeight);
	  //scenes.add(statusScene);

	  Group rootAB = new Group();
	  Scene aboutScene = new Scene(rootAB, sceneWidth, sceneHeight);
	  //scenes.add(aboutScene);

	  Group rootUP = new Group();
	  Scene updateScene = new Scene(rootUP, sceneWidth, sceneHeight);
	  //scenes.add(updateScene);

	  Group rootHS = new Group(); //todo
	  Scene historyScene = new Scene(rootHS, sceneWidth, sceneHeight);
	  //scenes.add(historyScene);	  
	  
	  Group rootMD = new Group(); //todo
	  Scene devicesScene = new Scene(rootMD, sceneWidth, sceneHeight);
	  //scenes.add(devicesScene);

	  Group rootNO = new Group(); //todo
	  Scene notifScene = new Scene(rootNO, sceneWidth, sceneHeight);
	  //scenes.add(notifScene);
	  
	  Group rootLO = new Group(); //todo
	  Scene layoutScene = new Scene(rootLO, sceneWidth, sceneHeight);
	  //scenes.add(layoutScene);
	  
	  //Variables > COLORS
	  /* NOTE: each color has a "proper name"
	   * and a second name ending in number 1 - 6 based on value
	   * 1 is lightest and 6 is darkest.*/
	  
	  //Variables > Colors > GRAY
	  Color ceramicWhite = Color.web("fdfef8");
	  Color celesteGray = Color.web("d0d1cb");
	  Color graniteGray = Color.web("7b7c77");
	  Color vampireGray = Color.web("4f514c");
	  Color balticSeaGray = Color.web("272725");
	  Color cinderGray = Color.web("191917");
	  
	  //Variables > Colors > RED
	  Color burntPink = Color.web("c3145a");
	  Color wineRed = Color.web("850035");
	  
	  //Variables > Colors > GREEN
	  Color dragonGreen = Color.web("57ffa0");
	  Color shamrockGreen = Color.web("46d384");
	  Color seaGreen = Color.web("2b7d4d");
	  Color pineGreen = Color.web("1c5232");
	  
	  //Variables > Colors > YELLOW
	  Color honeysuckleYellow = Color.web("fef495");
	  Color daisyYellow = Color.web("ffed65");
	  Color sycamoreYellow = Color.web("a79c42");
	   	  
	  //PURPLE
	  Color ravenPurple = Color.web("77729a");
	  Color wisteriaPurple = Color.web("958ec1");
	  Color lilacPurple  = Color.web("b5ade9");
	  Color moonPurple = Color.web("ccc7f0");
	  
	  	  
	  
	  //Variables > Fonts
	  int titleSize = (int)(sceneHeight / 25.0);
	  
	  //Palette setup
	  //									bg			pri				sec			acc1			acc2			acc3		acc4			acc5
	  Palette lightTheme = new Palette(ceramicWhite, shamrockGreen, dragonGreen, daisyYellow, honeysuckleYellow, seaGreen, sycamoreYellow, cinderGray, true);
	  lightTheme.setName("Light");
	  lightTheme.setFonts("Maiandra GD", titleSize, "Segoe UI", (int)(titleSize*0.75));	  
	  
	  Palette curPalette = new Palette();
	  curPalette.setPalette(lightTheme);
	  
	  String sampleText = ("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
	  
	  String curPath = ("file:" + System.getProperty("user.dir").replace("\\", "/") + "/graphics/");
	  
	  //BACKEND STUFF
	  //ScanManager scanMan = new ScanManager();
	  
	  //~~~~~~~~~~~~~~~~~~~~~~ MAIN MENU ~~~~~~~~~~~~~~~~~~~~~
	  //COMPOSITION:
	  //Top Bar: 1/7 height
	  //Side bar: 1/7 width
	  //Bottom bar: 1/3 height, 6/7 width
	  
	  menuScene.setFill(curPalette.getBgColor());
	  stage.show();
	  
	  //Main Menu > LAYOUT
	  //Main Menu > Layout > Top bar
	  Rectangle topBar = new Rectangle();
	  topBar.setWidth(sceneWidth);
	  topBar.setHeight((int) (sceneHeight / 7.0));
	  topBar.setFill(curPalette.getPriColor());
	  topBar.setStroke(curPalette.getLineColor());
	  topBar.setStrokeType(StrokeType.INSIDE);
	  topBar.setStrokeWidth(2);
	  
	  //` Menu > Layout > Side Line
	  Line sideLine = new Line(sceneWidth-1, 0, sceneWidth-1, sceneHeight);
	  sideLine.setStroke(curPalette.getLineColor());
	  sideLine.setStrokeWidth(2);
	  
	  //Main Menu > Layout > Side Bar
	  Rectangle sideBar = new Rectangle();
	  sideBar.setWidth((int)(sceneWidth / 7.0));
	  sideBar.setHeight(sceneHeight);
	  sideBar.setFill(curPalette.getPriColor());
	  sideBar.setStroke(curPalette.getLineColor());
	  sideBar.setStrokeType(StrokeType.INSIDE);
	  sideBar.setStrokeWidth(2);
	  rootMM.getChildren().add(sideBar);
	  
	  
	  //Main Menu > Layout > VARIABLES
	  int barrier1 = (int)sideBar.getWidth();
	  int barrier2 = barrier1 + (sceneWidth - barrier1 ) / 3;
	  
	  int barrier3 = 2 * (barrier2) - barrier1;
	  int topBarrier = (int)topBar.getHeight();
	  
	  int buffer = (int)(topBarrier * (1.0 / 8));
	  int iconSize = topBarrier - 2 * buffer;
	  int icon2Height = ( (sceneHeight - buffer*4) - topBarrier - (sceneHeight / 3)) / 3;
	  int buffer2 = (barrier1 - icon2Height) / 2;
	  int icon2Width = barrier2 - barrier1 - buffer2*2;

	  //startx, starty, endx, endy
	  Line line = new Line(0, topBarrier-1, barrier1-1, topBarrier-1);
	  line.setStroke(curPalette.getLineColor());
	  line.setStrokeWidth(2);
	  rootMM.getChildren().add(line);
	  
	  //Main Menu > Layout > Bottom
	  ArrayList<ButtonX> bottomButtons = new ArrayList<ButtonX>();
	  ArrayList<FillTransition> buttonFlashes = new ArrayList<FillTransition>();

	  for(int i = 0; i < 4; i++) 
	  {
		  int w = (int)((sceneWidth - barrier1) / 4) + 2;
		  int h = sceneHeight / 3;
		  int x = (barrier1 - 1) + ((w - 2) * i);
		  int y = sceneHeight - h;
		  Rectangle bottomDiv = new Rectangle();
		  bottomDiv.setX(x);
		  bottomDiv.setY(y);
		  bottomDiv.setWidth(w);
		  bottomDiv.setHeight(h);
		  bottomDiv.setFill(curPalette.getBgColor());
		  bottomDiv.setStroke(curPalette.getLineColor());
		  bottomDiv.setStrokeWidth(2);
		  bottomDiv.setStrokeType(StrokeType.INSIDE);
		  rootMM.getChildren().add(bottomDiv);
		  
		  String path = new String();
		  String txt = new String();
		  switch(i)
		  {
		  case 0:
			  //path = curPath + "updatesIcon.PNG";
			  path = curPath + "updatesIcon.PNG";
			  txt = "Updates";
			  break;
		  case 1:
			  path = curPath + "historyIcon.PNG";
			  txt = "History";
			  break;
		  case 2:
			  path = curPath + "helpIcon.PNG";
			  txt = "Help";
			  break;
		  case 3:
			  path = curPath + "devicesIcon.PNG";
			  txt = "My Devices";
			  break;
		  }
		  
		  Rectangle bottomIcon = Menu.icon((int)(w*0.5), (int)(w*0.5), bottomDiv, path);
		  rootMM.getChildren().add(bottomIcon);
		  
		  Text bottomText = new Text(txt);
		  bottomText.setFill(curPalette.getLineColor());
		  bottomText.setWrappingWidth(w);
		  bottomText.setX(x);
		  bottomText.setY(sceneHeight - buffer);
		  bottomText.setTextAlignment(TextAlignment.CENTER);
		  bottomText.setFont(curPalette.getTitleFont());
		  rootMM.getChildren().add(bottomText);

		  //Creates button
		  ButtonX bottomBtn = new ButtonX(bottomDiv, curPalette.getAcc4Color(), buttonFlashes);
		  
		  //Adds to buttom buttons list
		  bottomButtons.add(bottomBtn);

		  bottomBtn.addToGroup(rootMM);
	  }
	 
	  //Main Menu > GRAPHICS
	

	  
	  //Main Menu > Layout > Title
	  Text ratTrap = new Text("R.A.T. Trap Antivirus");
	  ratTrap.setY((topBarrier / 2));
	  ratTrap.setFont(curPalette.getTitleFont());
	  ratTrap.setWrappingWidth(sceneWidth);
	  ratTrap.setTextAlignment(TextAlignment.CENTER);
	  ratTrap.setFill(curPalette.getLineColor());
	  
	  Text about = new Text ("about");
	  about.setY((topBarrier * (3.0 / 4)) );
	  about.setX(-icon2Height/4);
	  about.setFont(curPalette.getSubTitleFont());
	  about.setWrappingWidth(sceneWidth);
	  about.setTextAlignment(TextAlignment.CENTER);
	  about.setFill(curPalette.getAcc2Color());
	  about.setUnderline(true);
	  
	  Rectangle aboutIcon = Menu.icon(icon2Height/4, icon2Height/4, sceneWidth/2 + icon2Height/8, (int)(about.getY() - (icon2Height * (1.5/8))), curPath + "infoIcon.PNG");
	  
	  Button aboutBtn = new Button();
	  ImageInput aboutInput = (ImageInput)(aboutIcon.getEffect());
	  aboutBtn.setPrefWidth((int)(titleSize * 0.45)*5 + aboutInput.getSource().getWidth());
	  aboutBtn.setPrefHeight((int)titleSize*0.75);
	  aboutBtn.setLayoutX(aboutInput.getX() - aboutBtn.getPrefWidth() + aboutInput.getSource().getWidth());
	  aboutBtn.setLayoutY(((ImageInput)aboutIcon.getEffect()).getY());
	  aboutBtn.setOpacity(0);
	
	  rootMM.getChildren().add(aboutBtn);
	
	  FillTransition aboutFill = new FillTransition();  
	  aboutFill.setAutoReverse(true);  	      
	  aboutFill.setCycleCount(1);  
	  aboutFill.setDuration(Duration.millis(100));  
	  aboutFill.setToValue(curPalette.getLineColor());  
	  aboutFill.setShape(about);  
	  buttonFlashes.add(aboutFill);	    
	  
	  //aboutFill.setOnFinished((finish) -> aboutFill.setToValue(curPalette.getAcc2Color()));
	  
	  //Main Menu > Graphics > Login Box

	  
	  Rectangle loginBox = Menu.icon(iconSize, iconSize, sceneWidth - buffer2 - iconSize, buffer, 10, curPalette.getPriColor(), curPalette.getLineColor());
	  Rectangle loginIcon = Menu.icon((int)(iconSize * 0.8), (int)(iconSize * 0.8), loginBox, curPath + "loginIcon.PNG");
	  Button loginBtn = Menu.makeButton(loginBox, curPalette.getAcc3Color(), buttonFlashes);
	  
	  //Main Menu > Graphics > Icon Box
	  Rectangle iconBox = Menu.icon(iconSize, iconSize, buffer2, buffer, 10, curPalette.getPriColor(), curPalette.getLineColor());
	  Rectangle headerIcon = Menu.icon((int)(iconSize * 0.8), (int)(iconSize * 0.8), iconBox, curPath + "settingsIcon.PNG");
	  
	  //							w			h			x				y
	  Rectangle notifIcon = Menu.icon(iconSize, iconSize, barrier1 + buffer2, buffer, curPath + "notifIcon.PNG");
	  Button notifBtn = new Button();
	  notifBtn.setLayoutX(barrier1 + buffer2);
	  notifBtn.setLayoutY(buffer);
	  notifBtn.setPrefWidth(iconSize);
	  notifBtn.setPrefHeight(iconSize);
	  notifBtn.setOpacity(0);
	  
	  //Main Menu > Graphics > Settings boxes
	  ArrayList<Rectangle> settingsBar = new ArrayList<Rectangle>();
	  ArrayList<ButtonX> settingsButtons = new ArrayList<ButtonX>();
	  
	  for(int i = 0; i < 4; i++)
	  {
		  String path = new String();
		  switch(i)
		  {
		  case 0:
			  path = curPath + "layoutIcon.PNG";
			  break;
		  case 1:
			  path = curPath + "calIcon.PNG";
			  break;
		  case 2:
			  path = curPath + "skipIcon.PNG";
			  break;
		  case 3:
			  path = curPath + "newsIcon.PNG";
			  break;
		  
		  }
		  //								w			h			x							y
		  Rectangle settingsBox = Menu.icon(icon2Height, icon2Height, buffer2, buffer + topBarrier + (icon2Height + buffer) * i, 20, curPalette.getAcc2Color(), curPalette.getLineColor());
		  Rectangle settingsIcon = Menu.icon((int)(icon2Height * 0.8), (int)(icon2Height * 0.8), settingsBox, path);
		  ButtonX settingsBtn = new ButtonX(settingsBox, curPalette.getAcc4Color(), buttonFlashes);
		  
		  
		  rootMM.getChildren().add(settingsBox);
		  rootMM.getChildren().add(settingsIcon);
		  settingsBtn.addToGroup(rootMM);
		  settingsBar.add(settingsBox);
		  settingsButtons.add(settingsBtn);
	  }
	  
	  //Calendar box
	  ArrayList<Shape> calPopUp = new ArrayList<Shape>();
	  ArrayList<Control> calPopUpBtns = new ArrayList<Control>();

	  ButtonX calBtn = settingsButtons.get(1);
	  Rectangle calBox = settingsBar.get(1);
	  Rectangle calPopUpBg = Menu.icon((int)(settingsBar.get(2).getY() + icon2Height- calBox.getY()), (int)(settingsBar.get(2).getY() + icon2Height - calBox.getY()), (int)(calBox.getX()) + icon2Height, (int)calBox.getY(), 20, curPalette.getBgColor(), curPalette.getLineColor());
	  calPopUp.add(calPopUpBg);
	  //rootMM.getChildren().add(calPopUpBg);
	  
	 
	  ArrayList<String> shAction = new ArrayList<String>();
	  shAction.add("F. Scan");
	  shAction.add("Q. Scan");
	  shAction.add("C. Scan");
	  shAction.add("Backup");
	  //											w									x											y		strings			color				font
	  CycleButton calCB1 = new CycleButton((int)calPopUpBg.getWidth()/2, (int)calPopUpBg.getX(), (int)(calPopUpBg.getY() + titleSize), shAction, curPalette.getRed(), curPalette.getDefaultFont());
	  calCB1.addToArrays(calPopUp, calPopUpBtns);
	  
	  
	  ArrayList<String> recurrance = new ArrayList<String>();
	  recurrance.add("weekly");
	  recurrance.add("monthly");
	  recurrance.add("once");
	  
	  //											w									x											y		strings			color				font
	  CycleButton calCB2 = new CycleButton((int)calPopUpBg.getWidth()/2, (int)(calPopUpBg.getX() + calPopUpBg.getWidth()/2), (int)(calPopUpBg.getY() + titleSize), recurrance, curPalette.getRed(), curPalette.getDefaultFont());
	  calCB2.addToArrays(calPopUp, calPopUpBtns);
	  
	  ArrayList<String> preps = new ArrayList<String>();
	  preps.add("on");
	  preps.add("on the first");
	  preps.add("next");
	  
	  Text calTxt = new Text(preps.get(0));
	  Menu.centerText(calPopUpBg, calTxt);
	  calTxt.setY((int)calCB2.getY() + titleSize);
	  calTxt.setFont(curPalette.getDefaultFont());
	  calTxt.setFill(curPalette.getLineColor());
	  calTxt.setOpacity(0.5);
	  calPopUp.add(calTxt);
	  
	  EventHandler<MouseEvent> changePrep = new EventHandler<MouseEvent>() 
	  {
		  @Override  
	      public void handle(MouseEvent event) 
		  {  
			  int index = preps.indexOf(calTxt.getText());
			  if(index != preps.size()-1)
				  index++;
			  else
				  index = 0;
			  
			  calTxt.setText(preps.get(index));
			  
			  event.consume();
		  }
	  };
	  calCB2.getButtons().get(0).setOnMouseClicked(changePrep);
 
	  
	  ArrayList<String> days = new ArrayList<String>();
	  days.add("M");
	  days.add("T");
	  days.add("W");
	  days.add("R");
	  days.add("F");
	  days.add("S");
	  days.add("U");

	  //												w						h						x						y
	  ButtonSwitch calBS1 = new ButtonSwitch((int)calPopUpBg.getWidth(), (int)(titleSize*0.75), (int)calPopUpBg.getX(), (int)calTxt.getY() + titleSize + buffer/2, false, days, curPalette.getLineColor(), curPalette.getRed(), curPalette.getDefaultFont());  
	  calBS1.addToArrays(calPopUp, calPopUpBtns);
	  
	  Text calTxt2 = new Text("at");
	  calTxt2.setX(calPopUpBg.getX() + buffer*2);
	  calTxt2.setY((int)calBS1.getY() + titleSize);
	  calTxt2.setFont(curPalette.getDefaultFont());
	  calTxt2.setFill(curPalette.getLineColor());
	  calTxt2.setOpacity(0.5);
	  calPopUp.add(calTxt2);
	  
	  TimeSelect ts1 = new TimeSelect(10, curPalette.getAcc1Color(), curPalette.getLineColor(), curPalette.getDefaultFont());
	  ts1.setX( (int)(calPopUpBg.getX() + (calPopUpBg.getWidth() - ts1.getWidth() - buffer*2)) );
	  ts1.setY((int)calBS1.getY() + titleSize/4);
	  ts1.setMinInc(30);
	  calPopUp.addAll(ts1.getComponents());
	  calPopUpBtns.addAll(ts1.getButtons());

	  Rectangle calSubmit = Menu.icon((int)(titleSize), (int)(titleSize), (int)( calPopUpBg.getX() + (calPopUpBg.getWidth() - titleSize)/2 ), (int)(ts1.getY() + ts1.getHeight() + buffer*1.5), 10, curPalette.getSecColor(), curPalette.getLineColor());
	  calPopUp.add(calSubmit);
	  
	  Text calTxt3 = new Text("+");
	  Menu.centerText(calSubmit, calTxt3);
	  calTxt3.setFont(curPalette.getDefaultFont());
	  calTxt3.setFill(curPalette.getBgColor());
	  calPopUp.add(calTxt3);
	  
	  ButtonX calSubmitBtn = new ButtonX(calSubmit, curPalette.getAcc3Color(), buttonFlashes);
	  calPopUpBtns.add(calSubmitBtn.getButton());
	  
	  PopUp calPP = new PopUp(calBtn, 100, calPopUp, calPopUpBtns);
	  calPP.addToGroup(rootMM);
	  
	  EventHandler<Event> calSubmitEvent = new EventHandler<Event>() 
	  {	  
		  @Override  
	      public void handle(Event event) 
		  {  
			  String path = new String();
			  
			  if(calCB1.getValue().equals("Backup"))
			  {
				  FileChooser file = new FileChooser();  
		          file.setTitle("Choose Files to Backup");

		          List<File> files = file.showOpenMultipleDialog(stage);
		          path = files.toString();
			  }
			  
			  else if (calCB1.getValue().equals("C. Scan"))
			  {
				  DirectoryChooser dir = new DirectoryChooser();  
		          dir.setTitle("Choose a File to Backup");

		          File dir1 = dir.showDialog(stage);
		          path = dir1.toString();
			  }
			  
			  else
				  path = null;
			  
			  
			  //calPP.getButton().getOnAction().handle((ActionEvent) event);			  
			  calPP.getButtonX().getActions().get(1).handle(event);
			  
			  System.out.println(calCB1.getValue() + "-" + calCB2.getValue() + "-" + calBS1.getValue() + "-" + ts1.getValue() + "-" + path);

			  event.consume();
		  }
	  };
	  calSubmitBtn.addAction(calSubmitEvent);
	  
	  EventHandler<Event> folderSkip = new EventHandler<Event>() 
	  {	  
		  @Override  
	      public void handle(Event event) 
		  {  			
			  DirectoryChooser dir = new DirectoryChooser();  
		      dir.setTitle("Choose a Folder to Skip When Scanning");

		      File dir1 = dir.showDialog(stage);
		      System.out.println(dir1.toString()); //TEMP
	          event.consume();
		  }
	  };
	  //settingsButtons.get(2).setOnMouseClicked(folderSkip);
	  settingsButtons.get(2).addAction(folderSkip);
	  
	  //Main Menu > Graphics > Actions
	  ArrayList<Rectangle> actionsBar = new ArrayList<Rectangle>();
	  ArrayList<ButtonX> actionButtons = new ArrayList<ButtonX>();
	  ArrayList<Text> actionsText = new ArrayList<Text>();
	  ArrayList<Rectangle> actionsIcons = new ArrayList<Rectangle>();
	  
	  for(int i = 0; i < 3; i++)
	  {
		  String path = new String();
		  String txt = new String();
		  switch(i)
		  {
		  case 0:
			  path = curPath + "scanIcon.PNG";
			  txt = "Scan";
			  break;
		  case 1:
			  path = curPath + "quarentineIcon.PNG";
			  txt = "Quarentine";
			  break;
		  case 2:
			  path = curPath + "backupIcon.PNG";
			  txt = "Backup";
			  break;
		  }
		  
		  //								w			h			x						y
		  Rectangle actionBox = Menu.icon(icon2Width, icon2Height, barrier1 + buffer2, buffer + topBarrier + (icon2Height + buffer) * i, 20, curPalette.getAcc1Color(), curPalette.getLineColor());
		  Rectangle actionIcon = Menu.icon((int)(icon2Height * 0.8), (int)(icon2Height * 0.8), actionBox, path);
		  ImageInput imgInput = (ImageInput)actionIcon.getEffect();
		  imgInput.setX(actionBox.getX() + (int)(icon2Height * 0.1));
		  
		  Text actionText = new Text(txt);
		  actionText.setFont(curPalette.getTitleFont());
		  actionText.setFill(curPalette.getLineColor());
		  
		  //actionText.setX(actionBox.getX() + icon2Height);
		  //actionText.setY(actionBox.getY() + titleSize + (int)((icon2Height - titleSize) / 2));
		  //actionText.setWrappingWidth(icon2Width - icon2Height);
		  //actionText.setTextAlignment(TextAlignment.CENTER);

		  //				w						h						x						y				text
		  Menu.centerText(icon2Width-icon2Height, icon2Height, (int)actionBox.getX() + icon2Height, (int)actionBox.getY(), actionText);		  
		  //Button actionBtn = Menu.makeButton(actionBox, curPalette.getAcc4Color(), buttonFlashes);
		  ButtonX actionBtn = new ButtonX(actionBox, curPalette.getAcc4Color(), buttonFlashes);
		  
		  rootMM.getChildren().add(actionBox);
		  rootMM.getChildren().add(actionIcon);
		  rootMM.getChildren().add(actionText);
		  actionButtons.add(actionBtn);
		  actionsBar.add(actionBox);
		  actionsText.add(actionText);
		  actionsIcons.add(actionIcon);
		  actionBtn.addToGroup(rootMM);
	  }
	  
	  
	  ButtonX scanBtn = actionButtons.get(0);
	  
	  //												w				h					x												y
	  Rectangle scanDropdown = Menu.icon(icon2Height/4, icon2Height/4, (int)(actionsBar.get(0).getX() + icon2Width * 0.85), sceneHeight/4, curPath + "dropdownIcon.PNG");
	  rootMM.getChildren().add(scanDropdown);
	  
	  scanDropdown.setWidth(icon2Height/4);
	  scanDropdown.setHeight(icon2Height/4);
	  scanDropdown.setX((int)(actionsBar.get(0).getX() + icon2Width * 0.85));
	  scanDropdown.setY(sceneHeight/4);
	  
	  ArrayList<Shape> scanDrawerList = new ArrayList<Shape>();
	  
	  //								w						h				x												y
	  Rectangle scanDrawer = Menu.icon( (int)(icon2Width*0.8), icon2Height, (int)(actionsBar.get(0).getX() + icon2Width*0.2), (int)actionsBar.get(0).getY(), 20, curPalette.getBgColor(), curPalette.getLineColor());
	  scanDrawerList.add(scanDrawer);
	  
	  
	 
	  for(int i = 0; i < 3; i++)
	  {
		  int x = (int)scanDrawer.getX();
		  int y = (int)(scanDrawer.getY() + icon2Height/3 * (i+1));
		  int w = (int) (icon2Width*0.8);
		  
		  if(i < 2)
		  {
			  Line div = new Line(x + 1, y, x + w - 1, y);
			  div.setStrokeWidth(2);
			  div.setStroke(curPalette.getLineColor());
			  scanDrawerList.add(div);
			  div.setOpacity(0.5);
		  }
		  
	  }
	  
	  //HERE 
	 
	  ArrayList<String> scanTypes = new ArrayList<String>();
	  scanTypes.add("Full Scan");
	  scanTypes.add("Quick Scan");
	  scanTypes.add("Custom Scan");

	  ButtonSwitch scanBS = new ButtonSwitch((int)(icon2Width*0.8), icon2Height, (int)scanDrawer.getX(), (int)scanDrawer.getY(), true, scanTypes, curPalette.getLineColor(), curPalette.getRed(), curPalette.getSubTextFont());
	  ArrayList<Control> scanBSBtns = scanBS.getButtons();
	  ArrayList<Shape> scanBSTxts = scanBS.getComponents();
	  	  
	  for(int i = 0; i < scanTypes.size(); i++)
	  {
		 Text curTxt = (Text)scanBSTxts.get(i);
		 Button curBtn = (Button)scanBSBtns.get(i);
		 
		 curBtn.setTranslateY(icon2Height);
		 scanDrawerList.add(curTxt);
		 curBtn.setVisible(false);
	  }
	  	  
	  DropDown scanDD = new DropDown(scanDropdown, icon2Height, 500, scanDrawerList, scanBSBtns);
	  Button scanDropdownBtn = scanDD.getButton();
	  scanDD.addToGroup(rootMM);	  	  
	  rootMM.getChildren().add(scanDropdownBtn);
	  
	  actionsBar.get(0).toFront();
	  actionsText.get(0).toFront();
	  actionsIcons.get(0).toFront();
	  actionsText.get(0).toFront();
	  actionButtons.get(0).getButton().toFront();
	  scanDropdown.toFront();
	  scanDropdownBtn.toFront();	  
	
	  //Backup stuff
	  
	  EventHandler<Event> fileBackup = new EventHandler<Event>() 
	  {	  
		  @Override  
	      public void handle(Event event) 
		  {  			
			  FileChooser file = new FileChooser();  
	          file.setTitle("Choose Files to Backup");

	          List<File> files = file.showOpenMultipleDialog(stage);
	          System.out.println(files); //TEMP
	          
	          event.consume();
		  }
	  };
	  actionButtons.get(2).addAction(fileBackup);
	  
	  EventHandler<Event> fileQuarentine = new EventHandler<Event>() 
	  {	  
		  @Override  
	      public void handle(Event event) 
		  {  			
			  FileChooser file = new FileChooser();  
	          file.setTitle("Choose Files to Quarentine");

	          List<File> files = file.showOpenMultipleDialog(stage);
	          System.out.println(files); //TEMP
	          
	          event.consume();
		  }
	  };
	  actionButtons.get(1).addAction(fileQuarentine);
	  
	  //Main Menu > Graphics > STATUS
	  Rectangle statusIconBig = Menu.icon(barrier3 - barrier2 - buffer2*2, barrier3 - barrier2 - buffer2*2, barrier2 + buffer2, topBarrier + buffer2, curPath + "statusGoodBig.PNG");
	  
	  rootMM.getChildren().add(statusIconBig);
	  Circle statusBtn = new Circle();
	  statusBtn.setCenterX((barrier3 + barrier2)/2);
	  statusBtn.setCenterY(topBarrier + (barrier3/2 - barrier2/2));
	  statusBtn.setRadius(barrier3/2 - barrier2/2 - buffer2);
	  statusBtn.setOpacity(0);
	  rootMM.getChildren().add(statusBtn);
	  
	  
	  //Main Menu > Graphics > Log
	  ArrayList<Text> logs = new ArrayList<Text>();
	  
	  for(int i = 0; i < 3; i++)
	  {
		  Text text = new Text();
		  text.setFill(curPalette.getAcc3Color());
		  text.setFont(curPalette.getSubTitleFont());
		  
		  String str = new String("Error");
		  switch (i)
		  {
		  	case 0:	str = "Last Scan: ";
		  			break;
		  			
		  	case 1:	str = "Next Scan: ";
		  			break;
		  			
		  	case 2: str = "Files Quarentined: ";
		  			break;
		  }
		  text.setText(str);
		  text.setX(barrier3 + buffer2);
		  text.setY((int)(titleSize * 3.0 / 9) + buffer*2 + topBarrier + (icon2Height + buffer) * i);
		  
		  rootMM.getChildren().add(text);
		  logs.add(text);
	  }
	  
		  ArrayList<javafx.scene.Node> addToAll = new ArrayList<javafx.scene.Node>();
		  addToAll.add(topBar);
		  addToAll.add(ratTrap);
		  addToAll.add(about);
		  addToAll.add(aboutIcon);
		  addToAll.add(loginBox);
		  addToAll.add(iconBox);
		  addToAll.add(sideLine);
		  addToAll.add(notifIcon);
		  addToAll.add(loginIcon);
		  addToAll.add(loginBtn);
		  addToAll.add(headerIcon);
		  addToAll.add(aboutBtn);
		  addToAll.add(notifBtn);
		  
		  Menu.changeScene(rootMM, addToAll);
		  stage.setScene(menuScene); 
		  
		  headerIcon.toFront();
		  topBar.toBack();
		  
		  //Change to Menu screen
		  EventHandler<Event> toMenuScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  				  
				  stage.setScene(menuScene);
				  Menu.changeScene(rootMM, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "settingsIcon.PNG", true);
				  scanDropdown.toFront();
				  scanDropdownBtn.toFront();					  

				  event.consume();
		      }    
		  };  
		  SceneControl sc = new SceneControl();
		  sc.addScene(menuScene, toMenuScreen);
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ LOG IN ~~~~~~~~~~~~~~~~~~~~~
		  logInScene.setFill(curPalette.getPriColor());

		  //Log in> white Space
		  Rectangle whiteSpace = Menu.icon(sceneWidth / 3, sceneHeight - topBarrier + 2, 0, topBarrier - 2, 0, curPalette.getBgColor(), curPalette.getLineColor());
		  whiteSpace.setFill(curPalette.getBgColor());
		  whiteSpace.setStroke(curPalette.getLineColor());
		  
		  //Log in > Username Box
		  
		  Rectangle usrBox = Menu.icon( (int)(sceneWidth * (2.0/3) - buffer2*2), iconSize, (int)(sceneWidth * (1.0/3)) + buffer2, topBarrier + buffer2*2 + buffer, 10, curPalette.getBgColor(), curPalette.getLineColor());
		  		  
		  Text usrText = new Text("Username:");
		  usrText.setFont(curPalette.getSubTitleFont());
		  usrText.setFill(curPalette.getLineColor());
		  usrText.setX((int)(sceneWidth * (1.0/3)) + buffer2);
		  usrText.setY(topBarrier + buffer2*2);
		  
		  TextField usrField = Menu.makeTextField(usrBox, curPalette.getDefaultFont()); 
		  
		  //Log in > Password Box
		  
		  Rectangle pwdBox = Menu.icon( (int)(sceneWidth * (2.0/3) - buffer2*2), iconSize, (int)(sceneWidth * (1.0/3)) + buffer2, topBarrier + buffer2*4 + buffer*2 + iconSize, 10, curPalette.getBgColor(), curPalette.getLineColor());
		  
		  Text pwdText = new Text("Password:");
		  pwdText.setFont(curPalette.getSubTitleFont());
		  pwdText.setFill(curPalette.getLineColor());
		  pwdText.setX((int)(sceneWidth * (1.0/3)) + buffer2);
		  pwdText.setY(topBarrier + buffer2*4 + buffer + iconSize);

		  TextField pwdField = Menu.makePwdField(pwdBox, curPalette.getDefaultFont());
		  
		  //Log in > bottom buttons
		  Rectangle submitBox = Menu.icon( (int)(sceneWidth * (2.0/3) - buffer2*2), iconSize, (int)(sceneWidth * (1.0/3)) + buffer2, topBarrier + buffer2*5 + buffer*2 + iconSize*2, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  rootLI.getChildren().add(submitBox);
		  Text submitText = new Text("Log in");
		  submitText.setFont(curPalette.getTitleFont());
		  submitText.setWrappingWidth(submitBox.getWidth());
		  Menu.centerText(submitBox, submitText);
		  submitText.setTextAlignment(TextAlignment.CENTER);
		  rootLI.getChildren().add(submitText);
				  
		  Rectangle guestBox = Menu.icon((int)(submitBox.getWidth() / 3), iconSize, (int)(whiteSpace.getWidth() + buffer2), sceneHeight - buffer2 - iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  Text guestText = new Text("Back");
		  guestText.setFont(curPalette.getTitleFont());
		  guestText.setWrappingWidth(guestBox.getWidth());
		  Menu.centerText(guestBox, guestText);
		  guestText.setTextAlignment(TextAlignment.CENTER);
		  
		  Button guestBtn = Menu.makeButton(guestBox, curPalette.getAcc4Color(), buttonFlashes);
		  rootLI.getChildren().add(guestBtn);
		  
		  Rectangle signUpBox = Menu.icon((int)(submitBox.getWidth() / 3), iconSize, sceneWidth - (int)(submitBox.getWidth() / 3) - buffer2, sceneHeight - buffer2 - iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  Text signUpText = new Text("Create Account");
		  signUpText.setFont(curPalette.getTitleFont());
		  signUpText.setWrappingWidth(guestBox.getWidth());
		  Menu.centerText(signUpBox, signUpText);
		  signUpText.setTextAlignment(TextAlignment.CENTER);
		  
		  Button signUpBtn = Menu.makeButton(signUpBox, curPalette.getAcc4Color(), buttonFlashes);
		  rootLI.getChildren().add(signUpBtn);
		  
		  ArrayList<javafx.scene.Node> addToLogin = new ArrayList<javafx.scene.Node>();
		  addToLogin.add(whiteSpace);
		  addToLogin.add(usrBox);
		  addToLogin.add(usrText);
		  addToLogin.add(pwdText);
		  addToLogin.add(pwdBox);
		  addToLogin.add(guestBox);
		  addToLogin.add(guestText);
		  addToLogin.add(signUpBox);
		  addToLogin.add(signUpText);
		  addToLogin.add(usrField);
		  addToLogin.add(pwdField);
		  
		  Menu.changeScene(rootLI, addToLogin);
		  
		  //Change to login screen
		  EventHandler<MouseEvent> toLoginScreen = new EventHandler<MouseEvent>() 
		  {
			  Scene oldScene = menuScene;
			  @Override  
		      public void handle(MouseEvent event) 
			  {  
				  
				  if(!stage.getScene().equals(createAcctScene))
					  oldScene = stage.getScene();
				  
				  //String path = (((ImageInput)headerIcon.getEffect()).getSource().getUrl());
				  stage.setScene(logInScene);
				  Menu.changeScene(rootLI, addToAll);
				  rootLI.getChildren().remove(loginBtn);
				  rootLI.getChildren().remove(loginIcon);
				  rootLI.getChildren().remove(loginBox);
				  curPalette.changeImg(headerIcon, curPath + "loginIcon.PNG", true);
				  
				  if(!rootLI.getChildren().contains(addToLogin.get(0)))
					  Menu.changeScene(rootLI, addToLogin);
				  
				  signUpBtn.toFront();
				  guestBtn.toFront();
				  
				  EventHandler<? super MouseEvent> toOldScene = sc.toScene(oldScene);
				  if(toOldScene != null)
					  guestBtn.setOnMouseClicked(toOldScene);
					
				  event.consume();
		      }    
		  };  
		  loginBtn.setOnMouseClicked(toLoginScreen); 
		  sc.addScene(logInScene, toLoginScreen);
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ CREATE ACCOUNT~~~~~~~~~~~~~~~~~~~~~
		  createAcctScene.setFill(curPalette.getPriColor());
  
		  //Create account: confirm pwd box
		  Rectangle confirmBox = Menu.icon( (int)(sceneWidth * (2.0/3) - buffer2*2), iconSize, (int)(sceneWidth * (1.0/3)) + buffer2, topBarrier + buffer2*5 + buffer*3 + iconSize*2, 10, curPalette.getBgColor(), curPalette.getLineColor());
		  rootCA.getChildren().add(confirmBox);
		  
		  PasswordField confirmField = Menu.makePwdField(confirmBox, curPalette.getDefaultFont());
		  rootCA.getChildren().add(confirmField);
		  
		  Text confirmText = new Text("Confirm Password:");
		  confirmText.setFont(curPalette.getSubTitleFont());
		  confirmText.setFill(curPalette.getLineColor());
		  confirmText.setX((int)(sceneWidth * (1.0/3)) + buffer2);
		  confirmText.setY(topBarrier + buffer2*5 + buffer*2 + iconSize*2);
		  rootCA.getChildren().add(confirmText);
  
		  Button backLIBtn = Menu.makeButton(guestBox,  curPalette.getAcc4Color(), buttonFlashes);
		  rootCA.getChildren().add(backLIBtn);
		  backLIBtn.setOnMouseClicked(toLoginScreen);
		  
		  //Change to Create Account Screen
		  EventHandler<MouseEvent> toSignUpScreen = new EventHandler<MouseEvent>() 
		  {
			  @Override  
		      public void handle(MouseEvent event) 
			  {  
				  stage.setScene(createAcctScene);
				  Menu.changeScene(rootCA, addToAll);
				  rootCA.getChildren().remove(loginBtn);
				  rootCA.getChildren().remove(loginIcon);
				  rootCA.getChildren().remove(loginBox);
				  curPalette.changeImg(headerIcon, curPath + "createAcctIcon.PNG", true);
				  Menu.changeScene(rootCA, addToLogin);
				  backLIBtn.toFront();
				  event.consume();
		      }    
		  };  
		  signUpBtn.setOnMouseClicked(toSignUpScreen); 
		  sc.addScene(createAcctScene, toSignUpScreen);
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ SCAN ~~~~~~~~~~~~~~~~~~~~~
		  scanScene.setFill(curPalette.getBgColor());
		  
		  Rectangle cancelBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  rootSC.getChildren().add(cancelBox);
		  
		  Text cancelText = new Text("Cancel");
		  cancelText.setFont(curPalette.getTitleFont());
		  cancelText.setWrappingWidth(icon2Width/2);
		  Menu.centerText(cancelBox, cancelText);
		  cancelText.setFill(curPalette.getLineColor());
		  
		  rootSC.getChildren().add(cancelText);
		  cancelText.setTextAlignment(TextAlignment.CENTER);

		  ButtonX cancelBtn = new ButtonX(cancelBox, curPalette.getAcc4Color(), buttonFlashes);
		  cancelBtn.addAction(toMenuScreen);
		  cancelBtn.addAction(new EventHandler<Event>() //kill scan
			  {
			    public void handle(Event event)
			    {
					ScanManager.killScan();

			    	event.consume();
			    }
			  });
		  
		  cancelBtn.addToGroup(rootSC);
		  
		  
		  ArrayList<javafx.scene.Node> scanStuff = new ArrayList<javafx.scene.Node>();
		  //0: curDir
		  //1: prog
		  //2: scanPercent
		  //3: filesScanned
		  //4: ratsFound
		  
		  
		  Text scanTitle = new Text("ERR");
		  scanTitle.setFont(curPalette.getTitle2Font());
		  scanTitle.setFill(curPalette.getAcc3Color());
		  Menu.centerText(sceneWidth, 0, 0, scanTitle);
		  scanTitle.setY(topBarrier + (int)(titleSize*5));
		  rootSC.getChildren().add(scanTitle);
		  
		  //Shows current file(?) being scanned
		  Text curDir = new Text();
		  curDir.setFont(curPalette.getDefaultFont());
		  curDir.setFill(curPalette.getLineColor());
		  Menu.centerText(sceneWidth, sceneHeight, 0, 0, curDir);
		  curDir.setY(topBarrier + (int)(titleSize*3.5));
		  rootSC.getChildren().add(curDir);
		  scanStuff.add(curDir); //0

		  
		  //this is kinda a mess bc of buggy CSS stuff :(
		  
		  //Shows progress through scan
		  ProgressBar prog = Menu.makeProgressBar((int)(sceneWidth*0.9), (int)(titleSize*1.5), (int)(sceneWidth*0.05), topBarrier + buffer*2, curPalette.getSecColor(), curPalette.getBgColor(), curPalette.getLineColor());

				  // ProgressBarX((int)(sceneWidth*0.9), titleSize, (int)(sceneWidth*0.05), topBarrier + buffer*2);
		  prog.setProgress(0.25);
		 
		  
		  /*prog.setPrefWidth((int)(sceneWidth*0.9));
		  prog.setPrefHeight(titleSize);
		  prog.setLayoutX((int)(sceneWidth*0.05));
		  prog.setLayoutY(topBarrier + buffer*2);*/
		  
		  //prog.addToGroup(rootSC);
		  rootSC.getChildren().add(prog);
		  scanStuff.add(prog); //1
		  
		  //mess ends here!
		
		  Text scanPercent = new Text("0%");
		  scanPercent.setFont(curPalette.getDefaultFont());
		  scanPercent.setFill(curPalette.getLineColor());
		  scanPercent.setX(prog.getLayoutX() + buffer);
		  scanPercent.setY(curDir.getY());
		  rootSC.getChildren().add(scanPercent);
		  scanStuff.add(scanPercent); //2
		  
		  Text filesScanned = new Text("0 Files Scanned");
		  filesScanned.setFont(curPalette.getDefaultFont());
		  filesScanned.setFill(curPalette.getLineColor());
		  filesScanned.setX(prog.getLayoutX() + buffer);
		  filesScanned.setY(curDir.getY());
		  filesScanned.setWrappingWidth(prog.getPrefWidth() - buffer*2);
		  filesScanned.setTextAlignment(TextAlignment.RIGHT);
		  rootSC.getChildren().add(filesScanned);
		  scanStuff.add(filesScanned); //3
		  
		  Text ratsFound = new Text("0 Rats Found");
		  ratsFound.setFont(curPalette.getDefaultFont());
		  ratsFound.setFill(curPalette.getLineColor());
		  ratsFound.setX(prog.getLayoutX() + buffer);
		  ratsFound.setY(curDir.getY() + titleSize);
		  ratsFound.setWrappingWidth(prog.getPrefWidth() - buffer*2);
		  ratsFound.setTextAlignment(TextAlignment.RIGHT);
		  rootSC.getChildren().add(ratsFound);
		  scanStuff.add(ratsFound); //4
		  
		  //Will be ratscot gif
		  //Rectangle ratGif = Menu.icon(200, 200, (sceneWidth - 200)/2, sceneHeight-210, curPath + "hamster-wheel.gif");
		  //rootSC.getChildren().add(ratGif);
		  		  
		  EventHandler<Event> deployScan = new EventHandler<Event>()
		  {
			    @Override  
			    public void handle(Event event)
			    {  
			    	scanTitle.setText(scanBS.getValue());
			        // Wrap the operation in a CompletableFuture
			        CompletableFuture.runAsync(() -> 
			        {
			            actionBranch ab = new actionBranch(scanStuff);

			            //Quick Scan
			            if (scanBS.getValue().contains("Quick")) 
			            {
					    	curDir.setText("Starting Scan...");
			            	//curDir.setText("C:\\Windows\\System32");
			                try 
			                {
			                     ab.actionMethod(1);
			                } 
			                catch (IOException | InterruptedException e) 
			                {
			                	System.out.println("ERROR");
			                    e.printStackTrace();
			                }
			            }
			            
			            //Custom Scan
			            else if(scanBS.getValue().contains("Custom"))
			            {
			            	try 
			                {
			                    ab.actionMethod(4);
			                } 
			            	catch (IOException | InterruptedException e)
			            	{
			                	System.out.println("ERROR");
			                    e.printStackTrace();
			                }
			            }
			            
			            
			        });

			        event.consume();
			    }    
			};

		  EventHandler<Event> toScanScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event)
			  {  
				  if(scanDD.isOpen())
					  scanDD.getButtonX().getActions().get(0).handle(event);
				  
				  
				  if(scanBS.getValue().contains("Custom"))
				  {
					  DirectoryChooser dir = new DirectoryChooser();  
					  dir.setTitle("Choose a Folder to Scan");
					  
					  File dir1 = dir.showDialog(stage);
					  
					  if(dir1 != null)
					  {
						  curDir.setText(dir1.toString());
						  System.out.println(dir1.toString()); //TEMP
						  
						  //After file is chosen, starts scan process
						  Menu.changeScene(rootSC, addToAll); //adds objects
						  //curDir.setText(scanBS.getValue());
						  curPalette.changeImg(headerIcon, curPath + "scanIcon.PNG", true);
						  stage.setScene(scanScene); //changes to scan screen
						  
						  //Deploys scan
						  if(stage.getScene() == scanScene);
						  {
							System.out.println("Deploying scan...");
						  	deployScan.handle(event);
						  }
					  }

				  }
				  
				  else
				  {
					  Menu.changeScene(rootSC, addToAll); //adds objects
					  curDir.setText(scanBS.getValue());
					  curPalette.changeImg(headerIcon, curPath + "scanIcon.PNG", true);
					  stage.setScene(scanScene); //changes to scan screen
					  if(stage.getScene() == scanScene);
					  {
						System.out.println("Deploying scan...");
					  	deployScan.handle(event);
					  }
				  }
				  
				  event.consume();
		      }    
		  };  
		  scanBtn.addAction(toScanScreen); 		  
		  
		  
		  //scanBtn.addAction(deployScan);
		  
		  sc.addScene(scanScene, toScanScreen);
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ STATUS ~~~~~~~~~~~~~~~~~~~~~
		  statusScene.setFill(curPalette.getBgColor());
		  Line temp = new Line(sceneWidth/3, 0, sceneWidth/3, sceneHeight);
		  rootST.getChildren().add(temp);
		  statusScene.setFill(curPalette.getBgColor());
		  
		  Rectangle backSTBox = Menu.icon(icon2Width/2, iconSize, (int)(sceneWidth/3 + (sceneWidth*(2.0/3) - icon2Width/2)/2), sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  rootST.getChildren().add(backSTBox);
		  
		  Text backSTText = new Text("Back");
		  backSTText.setFont(curPalette.getTitleFont());
		  backSTText.setWrappingWidth(icon2Width/2);
		  backSTText.setX(backSTBox.getX());
		  backSTText.setY((int)(sceneHeight-buffer2-titleSize*(3.0/4)));
		  rootST.getChildren().add(backSTText);
		  backSTText.setTextAlignment(TextAlignment.CENTER);

		  Button backSTBtn = Menu.makeButton(backSTBox, curPalette.getAcc4Color(), buttonFlashes);
		  backSTBtn.setOnMouseClicked(toMenuScreen);  
		  rootST.getChildren().add(backSTBtn);
  
		  Rectangle statusIcon = Menu.icon((int)(iconSize*2), (int)(iconSize*2), (int)(sceneWidth/3 - iconSize*2)/2, topBarrier + buffer2, curPath + "statusGoodSmall.PNG");
		  rootST.getChildren().add(statusIcon);
		  
		  
		  EventHandler<MouseEvent> toStatusScreen = new EventHandler<MouseEvent>() 
		  {
			  @Override  
		      public void handle(MouseEvent event) 
			  {  
				  
				  stage.setScene(statusScene);
				  Menu.changeScene(rootST, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "statusGoodSmall.PNG", true);
				  event.consume();
		      }    
		  };  
		  statusBtn.setOnMouseClicked(toStatusScreen);
		  sc.addScene(statusScene, toStatusScreen);
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ UPDATE ~~~~~~~~~~~~~~~~~~~~~
		  updateScene.setFill(curPalette.getBgColor());
		  Rectangle backUPBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  
		  Text backUPText = new Text("Back");
		  backUPText.setFont(curPalette.getTitleFont());
		  backUPText.setWrappingWidth(icon2Width/2);
		  backUPText.setX(backUPBox.getX());
		  backUPText.setY((int)(sceneHeight-buffer2-titleSize*(3.0/4)));
		  backUPText.setTextAlignment(TextAlignment.CENTER);

		  Button backUPBtn = Menu.makeButton(backUPBox, curPalette.getAcc4Color(), buttonFlashes);
		  backUPBtn.setOnMouseClicked(toMenuScreen);  
		  rootUP.getChildren().add(backUPBtn);
		  
		  Text versText = new Text("Version: " + verNum);
		  versText.setFont(curPalette.getTitle2Font());
		  versText.setFill(curPalette.getAcc3Color());
		  versText.setX(buffer2);
		  versText.setY(topBarrier+buffer2*2);
		  rootUP.getChildren().add(versText);
		  //							w			h			x													y						arc
		  Rectangle refreshBox = Menu.icon(iconSize, iconSize, buffer2 + (int)(titleSize * 0.75) * versText.getText().length(), topBarrier + buffer2/2, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  rootUP.getChildren().add(refreshBox);
		  Rectangle refreshIcon = Menu.icon((int)(iconSize*0.8), (int)(iconSize*0.8), refreshBox, curPath + "refreshIcon.PNG");
		  rootUP.getChildren().add(refreshIcon);
		  
		  Text updateBody = new Text(sampleText.substring(0, 100));
		  updateBody.setFont(curPalette.getDefaultFont());
		  updateBody.setWrappingWidth(sceneWidth - buffer2*2);
		  updateBody.setX(buffer2);
		  updateBody.setY(topBarrier + buffer2*4);
		  updateBody.setFill(curPalette.getLineColor());
		  rootUP.getChildren().add(updateBody);
		  
		  ButtonX updateBtn = bottomButtons.get(0);//new Button();

		  EventHandler<Event> toUpdateScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  
				  stage.setScene(updateScene);
				  Menu.changeScene(rootUP, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "updatesIcon.PNG", true);
				  if(!rootUP.getChildren().contains(backUPBox))
				  {
					  rootUP.getChildren().add(backUPBox);
					  rootUP.getChildren().add(backUPText);
				  }
				  backUPBtn.toFront();

				  event.consume();
		      }    
		  };  
		  updateBtn.addAction(toUpdateScreen);
		  sc.addScene(updateScene, toUpdateScreen);
		  
		//~~~~~~~~~~~~~~~~~~~~~~ About ~~~~~~~~~~~~~~~~~~~~~
		aboutScene.setFill(curPalette.getBgColor());
		Button backABBtn = Menu.makeButton(backUPBox, curPalette.getAcc4Color(), buttonFlashes);
		rootAB.getChildren().add(backABBtn);
		
		aboutFill.setFromValue((curPalette.getAcc2Color()));
		aboutFill.setToValue(curPalette.getLineColor());
		
	    aboutBtn.setOnAction(new EventHandler<ActionEvent>() 
	    {  
            Scene oldScene = menuScene;
            @Override  
            public void handle(ActionEvent arg0) 
            {  
            	if(!oldScene.equals(aboutScene))
            		oldScene = stage.getScene();
            	            	
            	aboutFill.play();
            	Color temp = (Color)about.getFill();
            	
            	aboutFill.setFromValue(aboutFill.getToValue());
            	aboutFill.setToValue(temp);
            	
            	stage.setScene(aboutScene);
				Menu.changeScene(rootAB, addToAll);
				
				curPalette.changeImg(headerIcon, curPath + "infoIcon.PNG", true);
				if(!rootAB.getChildren().contains(backUPBox))
				{
					rootAB.getChildren().add(backUPBox);
					rootAB.getChildren().add(backUPText);
				}
				backABBtn.toFront();
				backABBtn.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent arg0)
		            {
						aboutFill.play();
						Color temp = (Color)about.getFill();
		            	aboutFill.setFromValue(aboutFill.getToValue());
		            	aboutFill.setToValue(temp);
		            }
				});
				backABBtn.setOnMouseClicked(sc.toScene(oldScene));
				
            }  
	    } );  
	    aboutBtn.toFront();
		sc.addScene(aboutScene, aboutBtn.getOnMouseClicked());  
	    
		Text aboutTitle = new Text("About Us:");
		aboutTitle.setFont(curPalette.getTitle2Font());
		aboutTitle.setFill(curPalette.getAcc3Color());
		aboutTitle.setX(buffer2);
		aboutTitle.setY(topBarrier + buffer2*2);
		rootAB.getChildren().add(aboutTitle);
		  
		Text aboutBody = new Text(sampleText);
		aboutBody.setFont(curPalette.getDefaultFont());
		aboutBody.setWrappingWidth(sceneWidth - buffer2*2);
		aboutBody.setX(buffer2);
		aboutBody.setY(topBarrier + buffer2*3 + buffer);
		aboutBody.setFill(curPalette.getLineColor());
		rootAB.getChildren().add(aboutBody);

		
		//~~~~~~~~~~~~~~~~~~~~~~ Notifications ~~~~~~~~~~~~~~~~~~~~~
		EventHandler<MouseEvent> toNotifScreen = new EventHandler<MouseEvent>() 
		  {
			  @Override  
		      public void handle(MouseEvent event) 
			  {  
				  stage.setScene(notifScene);
				  Menu.changeScene(rootNO, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "notifIcon.PNG", true);
				  event.consume();

		      }    
		  };  
		  //notifBtn.setOnMouseClicked(toNotifScreen); 
		  sc.addScene(notifScene, toNotifScreen);
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ Layout ~~~~~~~~~~~~~~~~~~~~~
		  layoutScene.setFill(curPalette.getBgColor());
		  		  
		
		  Rectangle backLOBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  //ArrayList<javafx.scene.Node> backLOBtn = Menu.makeButton(backLOBox, curPalette.getAcc4Color(), buttonFlashes, toMenuScreen, "Back", curPalette.getTitleFont());
		 
		  Text backLOTxt = new Text("Back");
		  backLOTxt.setFont(curPalette.getTitleFont());
		  ButtonX backLOBtn = new ButtonX(backLOBox, curPalette.getAcc4Color(), backLOTxt, buttonFlashes);
		  backLOBtn.addToGroup(rootLO);
		  backLOBtn.addAction(toMenuScreen);
		  
		  //ScrollMenu
		  //								w			h											comp width						comp height				 x  	y	  columns
		  ScrollMenu sm = new ScrollMenu(sceneWidth, (int)(backLOBox.getY()) - topBarrier, (int)(icon2Height*1.5) + titleSize, (int)(icon2Height*1.5) + titleSize, 0, topBarrier, 4, curPath + "dropdownIcon.PNG");
		  sm.setColors(curPalette.getLineColor(), curPalette.getSecColor(), curPalette.getSecColor(), curPalette.getLineColor(), curPath + "dropdownIcon.PNG");		  
		  
		  ArrayList<Shape> exclude = new ArrayList<Shape>();
		  
		  //Light (Default) Theme
		  ThemeSelect lightSelect = new ThemeSelect(lightTheme, (int)(icon2Height*1.5), 10, lightTheme.getName());
		  lightSelect.addToArrays(exclude, null);
		  lightSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(lightTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(lightSelect);
		  
		  //									bg				pri					sec					acc1		acc2					acc3				acc4		outline
		  Palette lightHCTheme = new Palette(Color.WHITE, Color.MEDIUMSEAGREEN, Color.DEEPSKYBLUE, Color.PLUM, Color.LAVENDERBLUSH, Color.MIDNIGHTBLUE.darker(), Color.PURPLE, Color.BLACK, true);
		  lightHCTheme.setRed(Color.MEDIUMVIOLETRED);
		  lightHCTheme.setFonts("Segoe UI Semibold", titleSize, "Segoe UI", (int)(titleSize*0.75));	  
		  lightHCTheme.setName("Hi-Con L.");
		  ThemeSelect lightHCSelect = new ThemeSelect(lightHCTheme, (int)(icon2Height*1.5), 10, lightHCTheme.getName()); 
		  lightHCSelect.addToArrays(exclude, null);
		  lightHCSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(lightHCTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(lightHCSelect);
		  
		  //								bg				pri			sec				acc1		acc2			acc3		acc4			outline
		  Palette darkTheme = new Palette(balticSeaGray, pineGreen, shamrockGreen, ravenPurple, wisteriaPurple, dragonGreen, lilacPurple, ceramicWhite, false);
		  darkTheme.setFonts("Maiandra GD", titleSize, "Segoe UI", (int)(titleSize*0.75));	  
		  darkTheme.setName("Dark");
		  ThemeSelect darkSelect = new ThemeSelect(darkTheme, (int)(icon2Height*1.5), 10, "Dark");
		  darkSelect.addToArrays(exclude, null);
		  darkSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(darkTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(darkSelect);
		  
		  //										bg				pri			sec				acc1		acc2			acc3		acc4			outline
		  Palette darkHCTheme = new Palette(Color.BLACK, Color.SEAGREEN, Color.LIGHTGREEN, Color.PURPLE, Color.VIOLET, Color.SKYBLUE.brighter(), Color.LAVENDERBLUSH, Color.WHITE, false);
		  darkHCTheme.setRed(wineRed);
		  darkHCTheme.setFonts("Segoe UI Semibold", titleSize, "Segoe UI", (int)(titleSize*0.75));	  
		  darkHCTheme.setName("Hi-Con D.");
		  ThemeSelect darkHCSelect = new ThemeSelect(darkHCTheme, (int)(icon2Height*1.5), 10, darkHCTheme.getName());
		  darkHCSelect.addToArrays(exclude, null);
		  darkHCSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(darkHCTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(darkHCSelect);
		  
		  //									bg				pri			sec				acc1		acc2				acc3					acc4			outline
		  Palette lavanderTheme = new Palette(celesteGray, lilacPurple, moonPurple, ravenPurple, Color.web("585570"), Color.web("1c5232"), Color.web("383747"), balticSeaGray ,false);
		  lavanderTheme.setFonts("Adobe Garamond Pro Bold", titleSize, "Adobe Hebrew", (int)(titleSize*0.75));	  
		  lavanderTheme.setName("Lavander");
		  ThemeSelect lavanderSelect = new ThemeSelect(lavanderTheme, (int)(icon2Height*1.5), 10, lavanderTheme.getName());
		  lavanderSelect.addToArrays(exclude, null);
		  lavanderSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(lavanderTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(lavanderSelect);
		  
		  //									bg					pri							sec				acc1				acc2				acc3					acc4			outline
		  Palette cherryTheme = new Palette(Color.web("f5ecef"), Color.web("fe9fb5"), Color.web("e8697a"), daisyYellow, honeysuckleYellow, Color.web("b91230"), sycamoreYellow, Color.web("442415") ,true);
		  cherryTheme.setRed(Color.web("d13d55"));
		  cherryTheme.setFonts("Harrington", titleSize, "Perpetua", (int)(titleSize*0.75));	  
		  cherryTheme.setName("Cherry");
		  ThemeSelect cherrySelect = new ThemeSelect(cherryTheme, (int)(icon2Height*1.5), 10, cherryTheme.getName());
		  cherrySelect.addToArrays(exclude, null);
		  cherrySelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(cherryTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(cherrySelect);
		  
		  //									bg					pri							sec				acc1				acc2				acc3					acc4			outline
		  Palette draculaTheme = new Palette(Color.web("282a37"), Color.web("44465b"), Color.web("86e9ff"), Color.web("bc91fc"), Color.web("ff71c6"), Color.web("f1fc84"), Color.web("6171a5"), Color.web("f5f7f9") ,false);
		  draculaTheme.setRed(Color.web("ff534f"));
		  draculaTheme.setFonts("Book Antiqua", titleSize, "Chaparral Pro", (int)(titleSize*0.75));	 
		  draculaTheme.setTitleWeight(FontWeight.BLACK);
		  draculaTheme.setName("Dracula");
		  ThemeSelect draculaSelect = new ThemeSelect(draculaTheme, (int)(icon2Height*1.5), 10, draculaTheme.getName());
		  draculaSelect.addToArrays(exclude, null);
		  draculaSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(draculaTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(draculaSelect);
		  
		  //										bg					pri							sec				acc1				acc2				acc3					acc4			outline
		  Palette nordicTheme = new Palette(Color.web("ECEFF4"), Color.web("D8DEE9"), Color.web("8FBCBB"), Color.web("81A1C1"), Color.web("88C0D0"), Color.web("4C566A"), Color.web("5E81AC"), Color.web("2E3440"), true);
		  nordicTheme.setRed(Color.web("BF616A"));
		  nordicTheme.setFonts("Calibri", titleSize, "Calibri", (int)(titleSize*0.75));	 
		  nordicTheme.setTitleWeight(FontWeight.BLACK);
		  nordicTheme.setName("Nordic");
		  ThemeSelect nordicSelect = new ThemeSelect(nordicTheme, (int)(icon2Height*1.5), 10, nordicTheme.getName());
		  nordicSelect.addToArrays(exclude, null);
		  nordicSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(nordicTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(nordicSelect);
		  	
		  //										bg					pri							sec				acc1				acc2				acc3					acc4			outline
		  /*Palette catppuccinTheme = new Palette(Color.web("303446"), Color.web("232634"), Color.web("8FBCBB"), Color.web("81A1C1"), Color.web("88C0D0"), Color.web("4C566A"), Color.web("5E81AC"), Color.web("c6d0f5"), false);
		  catppuccinTheme.setRed(Color.web("BF616A"));
		  catppuccinTheme.setFonts("Calibri", titleSize, "Calibri", (int)(titleSize*0.75));	 
		  catppuccinTheme.setTitleWeight(FontWeight.BLACK);
		  catppuccinTheme.setName("Catppuccin");
		  ThemeSelect catppuccinSelect = new ThemeSelect(catppuccinTheme, (int)(icon2Height*1.5), 10, catppuccinTheme.getName());
		  catppuccinSelect.addToArrays(exclude, null);
		  catppuccinSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(catppuccinTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(catppuccinSelect);*/
		  
		  sm.addToGroup(rootLO);  
		  
		  EventHandler<Event> toLayoutScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  sm.reset();
				  stage.setScene(layoutScene);
				  rootLO.requestFocus();
				  Menu.changeScene(rootLO, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "layoutIcon.PNG", true);
				  scanDropdown.toFront();
				  scanDropdownBtn.toFront();
				  event.consume();
		      }    
		  };  

		  //settingsButtons.get(0).setOnMouseClicked(toLayoutScreen);
		  settingsButtons.get(0).addAction(toLayoutScreen);
		  sc.addScene(layoutScene, toLayoutScreen);  
  } 
 
 
	public static void main(String[] args) throws IOException
	{
		  launch(args);
	}
}