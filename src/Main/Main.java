package Main;
import javafx.application.Application;
import javafx.application.Platform;

//Not JavaFX
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Backend.*;
import Backend.MainThings.actionBranch;
import Backend.MainThings.clamstart;
import Backend.MainThings.freshClam;
import Backend.Scans.CountFiles;
import Backend.Scans.CustomScan;
import Backend.Scans.FileX;
import Backend.Scans.FullScan;
import Backend.Scans.QuickScan;
import Backend.Scans.Rat;
import Backend.Scans.Scan2;
import Backend.Scans.Scan2.ScanType;
import Backend.Scans.ScanManager;
import Backend.Scans.StatusCheck.Severity;
import Backend.Scans.StatusInfo;
import Backend.Scheduler.ScheduleMethods;
//Scene stuff
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
import Frontend.PrefReader;
import Frontend.ScanMonitor;
import Frontend.eFXtend.*;
import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
public class Main extends Application 
{ 
  @SuppressWarnings("unchecked")
@Override
  public void start(Stage stage) throws Exception, InterruptedException, IOException
  {
	  //~~~~~~~~~~~~~~~~~~~~~~ VARIABLES ~~~~~~~~~~~~~~~~~~~~~
	  /*
	   * NOTE: most of this can be ignored. This is where I set up some variables.
	   */
	  
	  //Eventually will not need actionbranch
	  actionBranch ab = new actionBranch();
	 
	  //Starts clamav engine
	  ExecutorService executorService = Executors.newFixedThreadPool(3); //number of threads?
	  freshClam myFresh = new freshClam();	  
      executorService.execute(myFresh);
      clamstart cs = new clamstart();
      executorService.execute(cs);
      
	  //Variables > STAGE
	  stage.setResizable(false);
	  String verNum = "1.07.00";
	
	  stage.setTitle("R.A.T. Trap Antivirus v" + verNum);
	
	  Rectangle2D screenSize = Screen.getPrimary().getBounds();
  
	  //Variables > GROUPS & SCENES
	  //Sets GUI to 3/4 of screen size
	  int sceneWidth = (int)(screenSize.getWidth() * (3.0/4)); 
	  int sceneHeight = (int)((9.0 / 16) * sceneWidth);  
	  System.out.println("Screen Size: " + sceneWidth + " x " + sceneHeight);	  
	  
	  //ArrayList<Scene> scenes = new ArrayList<Scene>();
	  
	  Group rootLG = new Group();
	  Scene loadingScene = new Scene(rootLG, sceneWidth, sceneHeight);
	  
	  Group rootMM = new Group();
	  Scene menuScene = new Scene(rootMM, sceneWidth, sceneHeight);
	  //scenes.add(menuScene);
	  
	  Group rootLI = new Group();
	  Scene logInScene = new Scene(rootLI, sceneWidth, sceneHeight);
	  //scenes.add(logInScene);

	  Group rootCA = new Group();
	  Scene createAcctScene = new Scene(rootCA, sceneWidth, sceneHeight);
	  //scenes.add(createAcctScene);

	  Group rootSC = new Group(); 
	  Scene scanScene = new Scene(rootSC, sceneWidth, sceneHeight);
	  //scenes.add(scanScene);

	  Group rootSF = new Group();
	  Scene scanFScene = new Scene(rootSF, sceneWidth, sceneHeight);
	  
	  Group rootST = new Group();
	  Scene statusScene = new Scene(rootST, sceneWidth, sceneHeight);
	  //scenes.add(statusScene);

	  Group rootAB = new Group();
	  Scene aboutScene = new Scene(rootAB, sceneWidth, sceneHeight);
	  //scenes.add(aboutScene);

	  Group rootUP = new Group();
	  Scene updateScene = new Scene(rootUP, sceneWidth, sceneHeight);
	  //scenes.add(updateScene);

	  Group rootHS = new Group(); 
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
	  
	  Group rootCL = new Group();
	  Scene calScene = new Scene(rootCL, sceneWidth, sceneHeight);
	  
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
	  
	  StatusInfo.refresh();
	  
	  String curPath = ("file:" + System.getProperty("user.dir").replace("\\", "/") + "/graphics/");
	  String statusIconPath = curPath + "statusGoodBig.PNG";
	  
	  
	  ArrayList<AnimatedMenu> animMenus = new ArrayList<AnimatedMenu>();
	  
	 
	  
	  //~~~~~~~~~~~~~~~~~~~~~~ MAIN MENU ~~~~~~~~~~~~~~~~~~~~~
	  //COMPOSITION:
	  //Top Bar: 1/7 height
	  //Side bar: 1/7 width
	  //Bottom bar: 1/3 height, 6/7 width
	  
	  menuScene.setFill(curPalette.getBgColor());
	  
	  //Main Menu > LAYOUT
	  //Main Menu > Layout > Top bar
	  Rectangle topBar = Menu.icon(sceneWidth, sceneHeight/7, 0, 0, 0, curPalette.getPriColor(), curPalette.getLineColor());
	  //Menu.addShadow(topBar, 30, 0);
	  //System.out.println(topBar.getHeight());
	
	  
	  // Menu > Layout > Side Line
	  Line sideLine = new Line(sceneWidth-1, 0, sceneWidth-1, sceneHeight);
	  sideLine.setStroke(curPalette.getLineColor());
	  sideLine.setStrokeWidth(2);
	  
	  //Main Menu > Layout > Side Bar
	  Rectangle sideBar = Menu.icon(sceneWidth/7, sceneHeight, 0, 0, 0, curPalette.getPriColor(), curPalette.getLineColor());
	  rootMM.getChildren().add(sideBar);
	  
	  //Main Menu > Layout > VARIABLES
	  /*
	   * I use these variables to position things
	   */
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
	 
	  bottomButtons.get(2).addAction(new EventHandler<Event>() //kill scan
			  {
		    public void handle(Event event)
		    {
		    	getHostServices().showDocument("https://rattrapav.neocities.org/FAQ");
		    }
	  });		
    	
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
	  ImageInput aboutInput = (ImageInput)(aboutIcon.getEffect());

	  //About button stuff
	  Button aboutBtn = new Button();
	  aboutBtn.setPrefWidth((int)(3*titleSize) + aboutInput.getSource().getWidth());
	  aboutBtn.setPrefHeight((int)titleSize*0.75);
	  aboutBtn.setLayoutX(aboutInput.getX() - aboutBtn.getPrefWidth() + aboutInput.getSource().getWidth());
	  aboutBtn.setLayoutY(((ImageInput)aboutIcon.getEffect()).getY());
	  aboutBtn.setOpacity(0);
	  	  
	  FillTransition aboutFill = new FillTransition();  
	  aboutFill.setAutoReverse(true);  	      
	  aboutFill.setCycleCount(1);  
	  aboutFill.setDuration(Duration.millis(100));  
	  aboutFill.setToValue(curPalette.getLineColor());  
	  aboutFill.setShape(about);  
	  buttonFlashes.add(aboutFill);	    
	  	  
	  //Main Menu > Graphics > Login Box
	  Rectangle loginBox = Menu.icon(iconSize, iconSize, sceneWidth - buffer2 - iconSize, buffer, 10, curPalette.getPriColor(), curPalette.getLineColor());
	  Rectangle loginIcon = Menu.icon((int)(iconSize * 0.8), (int)(iconSize * 0.8), loginBox, curPath + "loginIcon.PNG");
	  ButtonX loginBtn = new ButtonX(loginBox, curPalette.getAcc3Color(), buttonFlashes);
	  
	  
	  //Main Menu > Graphics > Icon Box
	  Rectangle iconBox = Menu.icon(iconSize, iconSize, buffer2, buffer, 10, curPalette.getPriColor(), curPalette.getLineColor());
	  Rectangle headerIcon = Menu.icon((int)(iconSize * 0.8), (int)(iconSize * 0.8), iconBox, curPath + "settingsIcon.PNG");
	  
	  //							w			h			x				y
	  /*Rectangle notifIcon = Menu.icon(iconSize, iconSize, barrier1 + buffer2, buffer, curPath + "notifIcon.PNG");
	  Button notifBtn = new Button();
	  notifBtn.setLayoutX(barrier1 + buffer2);
	  notifBtn.setLayoutY(buffer);
	  notifBtn.setPrefWidth(iconSize);
	  notifBtn.setPrefHeight(iconSize);
	  notifBtn.setOpacity(0);*/
	  
	 
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
	  //ArrayList<Shape> calPopUp = new ArrayList<Shape>();
	  //ArrayList<Control> calPopUpBtns = new ArrayList<Control>();

	  ButtonX calBtn = settingsButtons.get(1);
	  Rectangle calBox = settingsBar.get(1);
	  //Rectangle calPopUpBg = Menu.icon((int)(settingsBar.get(2).getY() + icon2Height- calBox.getY()), (int)(settingsBar.get(2).getY() + icon2Height - calBox.getY()), (int)(calBox.getX()) + icon2Height, (int)calBox.getY(), 20, curPalette.getBgColor(), curPalette.getLineColor());
	  //calPopUp.add(calPopUpBg);
	  //rootMM.getChildren().add(calPopUpBg);
	  
	  calScene.setFill(curPalette.getBgColor());
	  
	  Line calDiv1 = new Line((sceneWidth/2) * 0.8, 0, (sceneWidth/2) * 0.8, sceneHeight);
	  calDiv1.setStrokeWidth(2);
	  calDiv1.setStroke(curPalette.getLineColor());
	  calDiv1.setOpacity(0.5);
	  rootCL.getChildren().add(calDiv1);
	 
	  Line calDiv2 = new Line((sceneWidth/2) * 1.02 + iconSize, 0, (sceneWidth/2) * 1.02 + iconSize, sceneHeight);
	  calDiv2.setStrokeWidth(2);
	  calDiv2.setStroke(curPalette.getLineColor());
	  calDiv2.setOpacity(0.5);
	  rootCL.getChildren().add(calDiv2);
	  
	  Menu addCalEvent = new Menu();
	  
	  
	  Toggle calTog= new Toggle((int)(icon2Width/1.5), (iconSize), (int)calDiv2.getStartX() + (int)((sceneWidth - calDiv2.getStartX()) - (3*icon2Width/4))/2, topBarrier+buffer2);
	  calTog.setColors(curPalette.getAcc1Color(), curPalette.getSecColor(), curPalette.getBgColor(), curPalette.getLineColor());
	  calTog.setText("Scan", "Backup", curPalette.getSubTitleFont());
	  calTog.getText().setOpacity(1);
	  calTog.addToGroup(rootCL);
	  calTog.addToArrays(addCalEvent.getComponents(), addCalEvent.getButtons());
	  
	  Text eventTxt = new Text("Event:");
	  eventTxt.setFill(curPalette.getAcc3Color());
	  eventTxt.setFont(curPalette.getTitle2Font());
	  eventTxt.setX(calDiv1.getStartX() + buffer);
	  eventTxt.setY(calTog.getBg().getY() + + calTog.getBg().getHeight()/2);
	  rootCL.getChildren().add(eventTxt);
	  addCalEvent.add(eventTxt);
	  
	  ArrayList<String> shAction = new ArrayList<String>();
	  shAction.add("Full");
	  shAction.add("Quick");
	  shAction.add("Custom");
	  //shAction.add("Backup");
	  //											w										h						x				y		strings			color				font
	  ButtonSwitch calScanBS = new ButtonSwitch(sceneWidth - (int)calDiv2.getStartX(), titleSize, (int)calDiv2.getStartX(), (int)(calTog.getBg().getY() + calTog.getBg().getHeight() + buffer2 + titleSize), false, shAction, curPalette.getLineColor(), curPalette.getRed(), curPalette.getTitleFont());
	  calScanBS.addToGroup(rootCL);
	  calScanBS.addToArrays(addCalEvent.getComponents(), addCalEvent.getButtons());
	  
	  Text scanTypeTxt = new Text("Options:");
	  scanTypeTxt.setFill(curPalette.getAcc3Color());
	  scanTypeTxt.setFont(curPalette.getTitle2Font());
	  scanTypeTxt.setX(calDiv1.getStartX() + buffer);
	  scanTypeTxt.setY(calScanBS.getY());
	  rootCL.getChildren().add(scanTypeTxt);
	  addCalEvent.add(scanTypeTxt);

	  Rectangle calPickerBox = Menu.icon((int)(curPalette.getTitle2Font().getSize() * 5), (int)(curPalette.getTitle2Font().getSize()), (int)(calDiv2.getStartX() + ((sceneWidth - calDiv2.getStartX()) - curPalette.getTitle2Font().getSize()*5 )/2), (int)calScanBS.getY()  + buffer2, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
	  Text calPickerTxt = new Text("Choose File");
	  calPickerTxt.setFont(curPalette.getTitleFont());
	  calPickerTxt.setFill(curPalette.getLineColor());
	  ButtonX calPickerBtn = new ButtonX(calPickerBox, curPalette.getAcc4Color(), calPickerTxt, buttonFlashes);
	  calPickerBtn.addToGroup(rootCL);
	  calPickerBtn.addToArrays(addCalEvent.getComponents(), addCalEvent.getButtons());

	  Text curPathTxt = new Text("No files chosen");
	  curPathTxt.setFont(curPalette.getDefaultFont());
	  curPathTxt.setFill(curPalette.getAcc4Color());
	  Menu.centerText(sceneWidth - (int)calDiv2.getStartX(), (int)calDiv2.getStartX(), (int)(calPickerBox.getY() + calPickerBox.getHeight() + buffer2), curPathTxt);
	  rootCL.getChildren().add(curPathTxt);
	  addCalEvent.add(curPathTxt);
	  
	  //List<File> calFiles = new ArrayList<File>();

	  calPickerBtn.addAction(new EventHandler<Event>()
	  {
		  	File file1 = null;
		    public void handle(Event event)
		    {
		    	
		    	if(calTog.getValue()) //backup
		    	{
		    		calPickerBox.setStroke(curPalette.getLineColor());
		    		
		    		FileChooser file = new FileChooser();  
		    		file.setTitle("Choose a File to Backup");

		    		file1 = file.showOpenDialog(stage);		    		
		    	}
		    	else //scan
		    	{
		    		DirectoryChooser file = new DirectoryChooser();  
		    		file.setTitle("Choose a Folder to Scan");
		    		file1 = file.showDialog(stage);
		    	}
		    	
		    	//both
		    	
		    	//Update text
	    		if(file1 != null)
	    		{
	    			curPathTxt.setText(file1.getAbsolutePath());
	    		}
	    		else //null
	    		{
	    			curPathTxt.setText("No files chosen");
	    		}

		    }
	  });
	  
	  calTog.addAction(new EventHandler<Event>()
	  {
		    public void handle(Event event)
		    {
		    	calScanBS.setVisible(!calTog.getValue());
		    	calPickerBtn.setVisible(calTog.getValue() || calScanBS.getValue().equals("Custom"));
		    	curPathTxt.setVisible(calTog.getValue() || calScanBS.getValue().equals("Custom"));
		    	calTog.getBg().setStroke(curPalette.getLineColor());
		    	
		    	curPathTxt.setText("No files chosen");
		    }
	  });
	  
	  calBtn.addAction(new EventHandler<Event>()
	  {
		    public void handle(Event event)
		    {
		    	calScanBS.setVisible(!calTog.getValue());
		    	calPickerBtn.setVisible(calTog.getValue() || calScanBS.getValue().equals("Custom"));
		    	curPathTxt.setVisible(calTog.getValue() || calScanBS.getValue().equals("Custom"));
		    	
		    	curPathTxt.setText("No files chosen");
		    	
		    	event.consume();
		    }
	  });	
	  
	  for(ButtonX btnX : calScanBS.getButtonXs())
	  {
		  btnX.addAction(new EventHandler<Event>()
		  {
			    public void handle(Event event)
			    {
			    	calPickerBtn.setVisible(calScanBS.getValue().equals("Custom"));
			    	curPathTxt.setVisible(calScanBS.getValue().equals("Custom"));
			    	
			    	curPathTxt.setText("No files chosen");
			    }
		  });
	  }
	  
	  ArrayList<String> recurrance = new ArrayList<String>();
	  recurrance.add("Weekly");
	  recurrance.add("Monthly");
	  recurrance.add("Daily");
	  recurrance.add("Once");
	  
	 
	  ButtonSwitch recurBS = new ButtonSwitch(sceneWidth - (int)calDiv2.getStartX(), titleSize, (int)calDiv2.getStartX(), (int)(calScanBS.getY() + titleSize*3 + buffer2*2), false, recurrance, curPalette.getLineColor(), curPalette.getRed(), curPalette.getTitleFont());
	  recurBS.addToGroup(rootCL);
	  recurBS.addToArrays(addCalEvent.getComponents(), addCalEvent.getButtons());

	 
	  ArrayList<String> days = new ArrayList<String>();
	  days.add("M");
	  days.add("T");
	  days.add("W");
	  days.add("R");
	  days.add("F");
	  days.add("S");
	  days.add("U");
	  
	  //												w						h						x						y
	  ButtonSwitch dayBS = new ButtonSwitch(sceneWidth - (int)calDiv2.getStartX(), titleSize, (int)calDiv2.getStartX(), (int)(recurBS.getY() + titleSize + buffer2), false, days, curPalette.getLineColor(), curPalette.getRed(), curPalette.getTitleFont());
	  dayBS.addToGroup(rootCL);
	  dayBS.addToArrays(addCalEvent.getComponents(), addCalEvent.getButtons());

	  Text recurTxt = new Text("Every...");
	  recurTxt.setFill(curPalette.getAcc3Color());
	  recurTxt.setFont(curPalette.getTitle2Font());
	  recurTxt.setX(calDiv1.getStartX() + buffer);
	  recurTxt.setY(dayBS.getY());
	  rootCL.getChildren().add(recurTxt);
	  addCalEvent.add(recurTxt);
	  
	  for(ButtonX btnX : recurBS.getButtonXs())
	  {
		  btnX.addAction(new EventHandler<Event>()
		  {
			    public void handle(Event event)
			    {
			    	if(recurBS.getValue().equals("Weekly"))
			    	{
			    		recurTxt.setText("Every...");
			    		recurTxt.setVisible(true);
			    		dayBS.setVisible(true);
			    	}
			    	else if(recurBS.getValue().equals("Monthly"))
			    	{
			    		recurTxt.setText("The First...");
			    		recurTxt.setVisible(true);
			    		dayBS.setVisible(true);
			    	}
			    	else if(recurBS.getValue().equals("Daily"))
			    	{
			    		recurTxt.setVisible(false);
			    		dayBS.setVisible(false);
			    	}
			    	else if(recurBS.getValue().equals("Once"))
			    	{
			    		recurTxt.setText("Next...");
			    		recurTxt.setVisible(true);
			    		dayBS.setVisible(true);
			    	}
			    }
		  });	  
	  }
	  
	  TimeSelect ts1 = new TimeSelect(10, curPalette.getAcc1Color(), curPalette.getLineColor(), curPalette.getTitleFont());
	  ts1.setX( (int)(calDiv2.getStartX() + (((sceneWidth - calDiv2.getStartX())) - ts1.getWidth())/2 ) );
	  ts1.setY((int)dayBS.getY() + buffer2);
	  ts1.setMinInc(5);
	  ts1.addToGroup(rootCL);
	  ts1.addToArrays(addCalEvent.getComponents(), addCalEvent.getButtons());
	  
	  Text timeTxt = new Text("At...");
	  timeTxt.setFill(curPalette.getAcc3Color());
	  timeTxt.setFont(curPalette.getTitle2Font());
	  timeTxt.setX(calDiv1.getStartX() + buffer);
	  timeTxt.setY(ts1.getY() + buffer2);
	  rootCL.getChildren().add(timeTxt);
	  addCalEvent.add(timeTxt);
	  
	  //Submit
	  Rectangle calSubmit = Menu.icon((int)(curPalette.getTitle2Font().getSize() * 3), (int)(curPalette.getTitle2Font().getSize()), (int)(calDiv2.getStartX() + ((sceneWidth - calDiv2.getStartX()) - curPalette.getTitle2Font().getSize()*3 )/4), (int)(ts1.getY() + ts1.getHeight() + buffer2), 10, curPalette.getSecColor(), curPalette.getLineColor());
	  rootCL.getChildren().add(calSubmit);
	  
	  Text calTxt3 = new Text("Add");
	  Menu.centerText(calSubmit, calTxt3);
	  calTxt3.setFont(curPalette.getTitleFont());
	  calTxt3.setFill(curPalette.getBgColor());
	  rootCL.getChildren().add(calTxt3);
	  
	  ButtonX calSubmitBtn = new ButtonX(calSubmit, curPalette.getLineColor(), buttonFlashes);
	  rootCL.getChildren().add(calSubmitBtn.getButton());
	  calSubmitBtn.addToArrays(addCalEvent.getComponents(), addCalEvent.getButtons());

	  
	  //Cancel
	  Rectangle calCancel = Menu.icon((int)(curPalette.getTitle2Font().getSize() * 3), (int)(curPalette.getTitle2Font().getSize()), (int)(sceneWidth - (calSubmit.getX() + calSubmit.getWidth() - calDiv2.getStartX()) ), (int)(ts1.getY() + ts1.getHeight() + buffer2), 10, curPalette.getRed(), curPalette.getLineColor());
	  rootCL.getChildren().add(calCancel);
	  
	  Text calTxt4 = new Text("Cancel");
	  Menu.centerText(calCancel, calTxt4);
	  calTxt4.setFont(curPalette.getTitleFont());
	  calTxt4.setFill(curPalette.getBgColor());
	  rootCL.getChildren().add(calTxt4);
	  
	  ButtonX calCancelBtn = new ButtonX(calCancel, curPalette.getAcc3Color(), buttonFlashes);
	  rootCL.getChildren().add(calCancelBtn.getButton());
	  calCancelBtn.addToArrays(addCalEvent.getComponents(), addCalEvent.getButtons());
	  
	  Text calErrTxt = new Text("");
	  calErrTxt.setFill(curPalette.getRed());
	  calErrTxt.setFont(curPalette.getDefaultFont());
	  Menu.centerText(sceneWidth - (int)calDiv2.getStartX(), (int)calDiv2.getStartX(), (int)(calSubmit.getY() + calSubmit.getHeight() + buffer2/2), calErrTxt);
	  rootCL.getChildren().add(calErrTxt);
	  
	  Rectangle blockCalAdd = Menu.icon(sceneWidth - (int)calDiv1.getStartX(), sceneHeight - topBarrier, (int)calDiv1.getStartX(), topBarrier, 10, curPalette.getBgColor(), curPalette.getBgColor());
	  blockCalAdd.setStrokeWidth(0);
	  rootCL.getChildren().add(blockCalAdd);
	  calDiv1.toFront();
	  
	  Rectangle addEventBox = Menu.icon((int)curPalette.getTitle2Font().getSize()*5, (int)(curPalette.getTitle2Font().getSize()*1.8), (int)( calDiv1.getStartX() + ((sceneWidth - calDiv1.getStartX()) - (curPalette.getTitle2Font().getSize()*5) )/2), (int)( ((sceneHeight - topBarrier) - (curPalette.getTitle2Font().getSize()*1.8))/2 + topBarrier ), 10, curPalette.getSecColor(), curPalette.getLineColor());
	  Text addEventText = new Text("Add Event");
	  addEventText.setFont(curPalette.getTitle2Font());
	  addEventText.setFill(curPalette.getBgColor());
	  ButtonX addEventBtn = new ButtonX(addEventBox, curPalette.getAcc4Color(), addEventText, buttonFlashes);
	  addEventBtn.addToGroup(rootCL);
	  
	  addEventBtn.addAction(new EventHandler<Event>()
	  {
		    public void handle(Event event)
		    {
		    	blockCalAdd.setVisible(false);
		    	addEventBtn.setVisible(false);
		    	calErrTxt.setText("");
		    	
		    	calTog.getBg().setStroke(curPalette.getLineColor());
		    	calPickerBox.setStroke(curPalette.getLineColor());
		    	
		    	event.consume();
		    }
	  });
	  
	  
	  calCancelBtn.addAction(new EventHandler<Event>()
	  {
		    public void handle(Event event)
		    {
		    	blockCalAdd.setVisible(true);
		    	addEventBtn.setVisible(true);
		    	
		    	event.consume();
		    }
	  });
	  
	  Rectangle backCalBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
	  
	  Text backCalText = new Text("Back");
	  backCalText.setFont(curPalette.getTitleFont());
	  backCalText.setFill(curPalette.getLineColor());
	  backCalText.setTextAlignment(TextAlignment.CENTER);

	  ButtonX backCalBtn = new ButtonX(backCalBox, curPalette.getAcc4Color(), backCalText, buttonFlashes);
	  
	  
	  //PopUp calPP = new PopUp(calBtn, 100, calPopUp, calPopUpBtns);
	  //animMenus.add(calPP);

	  //calPP.addToGroup(rootMM);
	  
	  Text calTitle = new Text("Scheduled Events");
	  calTitle.setFont(curPalette.getTitle2Font());
	  calTitle.setFill(curPalette.getAcc3Color());
	  Menu.centerText((int)calDiv1.getStartX(), 0, topBarrier + (int)curPalette.getTitle2Font().getSize(), calTitle);
	  rootCL.getChildren().add(calTitle);
	  
	  ScheduleMethods SM = new ScheduleMethods();
	  
	  ArrayList<Text> calEntries = new ArrayList<Text>();
	  ArrayList<ButtonX> delEntries = new ArrayList<ButtonX>();
	  
	  EventHandler<Event> calRefresh = new EventHandler<Event>() 
	  {	  
		  @Override  
	      public void handle(Event event) 
		  {    
			  System.out.println("Refreshing Calendar...");
			  
			  //remove old entries
			  for(Text txt : calEntries)
			  {
				  rootCL.getChildren().remove(txt);
			  }
			  for(ButtonX btn : delEntries)
			  {
				  btn.removeFromGroup(rootCL);
			  }
			  
			  calEntries.clear();
			  delEntries.clear();
			  
			  //Add new entries
			  int count = 0;
			  int yPos = (int)calTitle.getY() + titleSize + buffer2;
			  
			  for(String[] strArr : SM.mySql.toArray())
			  {
				  //if(count >= 5)
				//	  break;
				  

				  String str = new String();
				  if(strArr[2].equals("BACKUP"))
				  {
					  str = "Backup";
					  
					  String path = new String();
					  if(strArr[4].length() > 10)
						  path = "..." + strArr[4].substring(strArr[4].length() - 11);
					  else
						  path = strArr[4];
					  
					  str += " " + path;
				  }
				  else //scan
				  {
					  ScanType st = ScanType.valueOf(strArr[2]);
					  
					  switch(st)
					  {
					  	case FULL:
					  		str = "Full Scan";
					  		break;
					  	case QUICK:
					  		str = "Quick Scan";
					  		break;
					  	case CUSTOM:
					  		str = "Custom Scan";
					  		String path = new String();
							  if(strArr[4].length() > 10)
								  path = "..." + strArr[4].substring(strArr[4].length() - 11);
							  else
								  path = strArr[4];
							  str += " " + path;
					  		break;
					  }
					  
				  } //end scan
				  
				  //both scan, backup
				  
				  //recurrance
				  if(strArr[3].equals("DAILY"))
				  {
					 str += " daily"; 
				  }
				  else //weekly/monthly/once
				  {
					  if(strArr[3].equals("WEEKLY"))
						  str += " every "; 
					  
					  else if(strArr[3].equals("MONTHLY"))
						  str += " monthly on the first ";
					  else
						  str += " once, next ";
					  
					  //week day

					  char day = strArr[5].charAt(0);
					  switch(day)
					  {
					  		case 'M':
					  			str += "Mon";
					  			break;
					  		case 'T':
					  			str += "Tue";
					  			break;
					  		case 'W':
					  			str += "Wed";
					  			break;
					  		case 'R':
					  			str += "Thu";
					  			break;
					  		case 'F':
					  			str += "Fri";
					  			break;
					  		case 'S':
					  			str += "Sat";
					  			break;
					  		case 'U':
					  			str += "Sun";
					  			break;
					  } //week day end
				  }//recur end
				  
				  //time				  
				  String sub = new String(" AM");
				  
				  int hr = Integer.parseInt(strArr[1].substring(strArr[1].indexOf('T')+1,  strArr[1].indexOf(':')));
				  String min = (strArr[1].substring(strArr[1].indexOf(':')+1,  strArr[1].lastIndexOf(':')));

				  if(hr == 0)
				  {
					  hr = 12;
				  }
				  if(hr > 12)
				  {
					  sub = " PM";
					  hr -= 12;
				  }
				  str += " at " + hr + ":" + min + sub;
				  
				  Text entry = new Text(str);
				  entry.setFont(curPalette.getDefaultFont());
				  entry.setFill(curPalette.getLineColor());
				  Menu.centerText((int)calDiv1.getStartX(), 0, yPos, entry);
				  rootCL.getChildren().add(entry);
				  
				  Rectangle delEntryBox = Menu.icon(titleSize, titleSize, (int)(calDiv1.getStartX() - titleSize*2), yPos - 3*titleSize/4, 10, curPalette.getRed(), curPalette.getLineColor());
				  Text delEntryText = new Text("X");
				  delEntryText.setFont(curPalette.getSubTitleFont());
				  delEntryText.setFill(curPalette.getBgColor());
				  ButtonX delEntryBtn = new ButtonX(delEntryBox, curPalette.getLineColor(), delEntryText, buttonFlashes);
				  delEntryBtn.addToGroup(rootCL);
				  
				  calEntries.add(entry);
				  delEntries.add(delEntryBtn);
				  
				  delEntryBtn.addAction(new EventHandler<Event>()
				  {
					    public void handle(Event event)
					    {
					    	SM.rmSchedule(Integer.parseInt(strArr[0]));
					    	entry.setStrikethrough(true);
					    	entry.setOpacity(0.5);
					    	delEntryBtn.setVisible(false);
					    	event.consume();
					    }
				  });
				  
				  yPos += titleSize + buffer2;
				  count++;
			  }
			  
			  event.consume();
		  }
	  };
	  EventHandler<Event> calSubmitEvent = new EventHandler<Event>() 
	  {	  
		  @Override  
	      public void handle(Event event) 
		  {    
			  String[] strArr = new String[6];
			  	//0: event type
			  	//1: subtype
			  	//2: args
			  	//3: recurrance
			  	//4: day
			  	//5: time
			  
			  if(calTog.getValue())
			  {
				  //Event type
				  strArr[0] = "BACKUP";
				  
				  //Subtype
				  strArr[1] = null;
				  
				  //Args
				  strArr[2] = curPathTxt.getText();
			  }
			  else
			  {
				  //Event type
				  strArr[0] = "SCAN";
				  
				  //Subtype
				  ScanType st = ScanType.valueOf(calScanBS.getValue().toUpperCase());
				  strArr[1] = st.toString();
				  
				  //Args
				  switch(st)
				  {
				  	case CUSTOM:
				  		strArr[2] = curPathTxt.getText();
				  		break;
				  		
				  	default:
				  		strArr[2] = null; //full and quick don't need filepath
				  }
			  }
			  //FOR BOTH
			  
			  //Recurrance
			  strArr[3] = recurBS.getValue().toUpperCase();
			  
			  //Day
			  if(strArr[3].equals("DAILY"))
				  strArr[4] = "X";
			  else
				  strArr[4] = dayBS.getValue();
			  
			  strArr[5] = ts1.getValue();
			  
			  //Errors
			  String err = new String();
			  Boolean errBool = false; //errors present
			  
			  //Backup not implemented
			  if(strArr[0].equals("BACKUP"))
			  {
				  calErrTxt.setText("Err: Backup not implemented yet. Please try again.");
				  calTog.getBg().setStroke(curPalette.getRed());
				  
				  errBool = true;
			  }
			  
			  //No file chosen
			  else if((strArr[1].equals("CUSTOM") || strArr[0].equals("BACKUP"))&& (strArr[2] == null || strArr[2].isEmpty()))
			  {
				  calErrTxt.setText("Err: No file chosen. Please try again");
				  calPickerBox.setStroke(curPalette.getRed());
				  
				  errBool = true;
			  }
			  
			  ArrayList<String[]> table = SM.mySql.toArray();
			  for(String[] line : table)
			  {
				  if(line[2].equals(strArr[1])) //same event
				  {
					  //custom/backup --> check filepath
					  if(strArr[1].equals("CUSTOM") || strArr[1].equals("BACKUP"))
					  {
						  if(line[4].equals(strArr[2]))
						  {
							  calErrTxt.setText("Err: An event of this type is already scheduled, please try again.");
							  errBool = true;
							  break;
						  }
					  }
					  else //full/quick
					  {
						  calErrTxt.setText("Err: An event of this type is already scheduled, please try again.");
						  errBool = true;
						  break;
					  }
				  }

				  //same time
				  if(strArr[4].equals(line[5]) && (Integer.parseInt(strArr[5].substring(0, strArr[5].indexOf('-'))) == Integer.parseInt(line[1].substring(line[1].indexOf('T')+1,  line[1].indexOf(':'))) ))
				  {
					  calErrTxt.setText("Err: An event is already scheduled too close to this time, please try again.");
					  errBool = true;
					  break;
				  }
			  }
			  
			  if(!errBool)
			  {
				  //Add event
				  SM.addToScheulde(strArr);
				
				  calRefresh.handle(event);
			  }
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
	  
	  
	  settingsButtons.get(3).addAction(new EventHandler<Event>() //kill scan
			  {
		    public void handle(Event event)
		    {
		    	getHostServices().showDocument("https://rattrapav.neocities.org/RatNews");
		    }
	  });

	  
	  
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
	  animMenus.add(scanDD);
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
	  Rectangle statusIconBig = Menu.icon(barrier3 - barrier2 - buffer2*2, barrier3 - barrier2 - buffer2*2, barrier2 + buffer2, topBarrier + buffer2, statusIconPath);
	  
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
		  
		  Text subText = new Text();
		  subText.setFill(curPalette.getLineColor());
		  subText.setFont(curPalette.getDefaultFont());
		  subText.setX(text.getX());
		  subText.setY(text.getY() + 3*titleSize/4);
		  
		  rootMM.getChildren().add(text);
		  rootMM.getChildren().add(subText);
		  logs.add(subText);
	  }
	  	  //Home button
	  	  ButtonX homeBtn = new ButtonX(Menu.makeButton(iconBox, curPalette.getAcc3Color(), buttonFlashes));

	  
		  ArrayList<javafx.scene.Node> addToAll = new ArrayList<javafx.scene.Node>();
		  addToAll.add(topBar);
		  addToAll.add(ratTrap);
		  addToAll.add(about);
		  addToAll.add(aboutIcon);
		  //addToAll.add(loginBox);
		  addToAll.add(iconBox);
		  addToAll.add(sideLine);
		  //addToAll.add(notifIcon);
		  //addToAll.add(loginIcon);
		  //loginBtn.addToArray(addToAll);
		  addToAll.add(headerIcon);
		  addToAll.add(aboutBtn);
		  //addToAll.add(notifBtn);
		  homeBtn.addToArray(addToAll);
		  
		  Menu.changeScene(rootMM, addToAll);
		  
		  headerIcon.toFront();
		  topBar.toBack();
		  
		  //Change to Menu screen
		  FileX logInfo = new FileX("Resources/LogInfo.txt");
		  
		  logInfo.parse();
		  for(int i = 0; i < 3; i++)
		  {
			  String str = logInfo.readLine(i+1);
			  str = str.substring(str.indexOf('\t')+1);
			  logs.get(i).setText(str);
		  }
		  
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
				  
				  
				  //Refresh Logs Display
				  logInfo.writeFile();
				
				  for(int i = 0; i < 3; i++)
				  {
					  String str = logInfo.readLine(i+1);
					  str = str.substring(str.indexOf('\t')+1);
					  logs.get(i).setText(str);
				  }
				  
				  
				  event.consume();
		      }    
		  };  
		  SceneControl sc = new SceneControl();
		  sc.addScene(menuScene, toMenuScreen);
		  sc.addScene(loadingScene, null);
		  backCalBtn.addAction(toMenuScreen);
		  backCalBtn.addToGroup(rootCL);
		  
		  EventHandler<Event> toCalScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  				  
				  stage.setScene(calScene);
				  Menu.changeScene(rootCL, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);

				  blockCalAdd.setVisible(true);
				  addEventBtn.setVisible(true);
				  
				  calRefresh.handle(event);
				  
				  event.consume();
		      }    
		  };  
		  sc.addScene(calScene, toCalScreen);
		  calBtn.addAction(toCalScreen);
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ LOADING ~~~~~~~~~~~~~~~~~~~~~

		  loadingScene.setFill(curPalette.getBgColor());
		  stage.setScene(loadingScene); 
		  stage.show();

		  Text loadTxt = new Text("Loading...");
		  loadTxt.setFont(curPalette.getTitleFont());
		  Menu.centerText(sceneWidth, sceneHeight, 0, 0, loadTxt);
		  loadTxt.setFill(curPalette.getPriColor());
		  loadTxt.textProperty().bind(cs.valueProperty());
		  rootLG.getChildren().add(loadTxt);
		  
		  loadTxt.textProperty().addListener(new ChangeListener()
	      {
	          @Override public void changed(ObservableValue o,Object oldVal, Object newVal)
	          {
	        	 	if(newVal.equals("All ready!"))
		 			{
	        	 		System.out.println("All ready!");
	        	 		stage.setScene(menuScene);
	        	 		loadTxt.textProperty().unbind();
		 			}
	          }
	      });
		  
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ LOG IN ~~~~~~~~~~~~~~~~~~~~~
		  homeBtn.addAction(new EventHandler<Event>() //kill scan
				  {
				    public void handle(Event event)
				    {
				    	if(stage.getScene() != menuScene)
				    	{
					    	toMenuScreen.handle(event);
					    	if(ScanManager.liveScan())
								ScanManager.killScan();
				    	}
				    	event.consume();
				    }
			  });
		  
		  logInScene.setFill(curPalette.getPriColor());

		  //Log in> white Space
		  Rectangle whiteSpace = Menu.icon(sceneWidth / 3, sceneHeight - topBarrier + 2, 0, topBarrier - 2, 0, curPalette.getBgColor(), curPalette.getLineColor());
		  whiteSpace.setFill(curPalette.getBgColor());
		  whiteSpace.setStroke(curPalette.getLineColor());
		  
		  //Rat scott image on left
		  Rectangle ratScot = Menu.icon((int)(whiteSpace.getWidth()*1.2), (int)(whiteSpace.getWidth()*1.2), (int)(whiteSpace.getX()-whiteSpace.getWidth()*0.1), (int)(whiteSpace.getY() + (whiteSpace.getHeight()-whiteSpace.getWidth()*1.2)/2), Menu.getImgPath("Rat-Scott.PNG"));
		  
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
		  ButtonX loginSubmitBtn = new ButtonX(submitBox, curPalette.getAcc3Color(), buttonFlashes);
		  loginSubmitBtn.addToGroup(rootLI);
		  
		  Rectangle guestBox = Menu.icon((int)(submitBox.getWidth() / 3), iconSize, (int)(whiteSpace.getWidth() + buffer2), sceneHeight - buffer2 - iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  Text guestText = new Text("Back");
		  guestText.setFont(curPalette.getTitleFont());
		  guestText.setWrappingWidth(guestBox.getWidth());
		  guestText.setFill(curPalette.getLineColor());
		  Menu.centerText(guestBox, guestText);
		  guestText.setTextAlignment(TextAlignment.CENTER);
		  
		  ButtonX guestBtn = new ButtonX(guestBox, curPalette.getAcc4Color(), buttonFlashes);
		  guestBtn.addToGroup(rootLI);
		  
		  Rectangle signUpBox = Menu.icon((int)(submitBox.getWidth() / 3), iconSize, sceneWidth - (int)(submitBox.getWidth() / 3) - buffer2, sceneHeight - buffer2 - iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  Text signUpText = new Text("Create Account");
		  signUpText.setFont(curPalette.getTitleFont());
		  signUpText.setWrappingWidth(guestBox.getWidth());
		  Menu.centerText(signUpBox, signUpText);
		  signUpText.setTextAlignment(TextAlignment.CENTER);
		  
		  ButtonX signUpBtn = new ButtonX(signUpBox, curPalette.getAcc4Color(), buttonFlashes);
		  signUpBtn.addToGroup(rootLI);		  
		  
		  ArrayList<javafx.scene.Node> addToLogin = new ArrayList<javafx.scene.Node>();
		  addToLogin.add(whiteSpace);
		  addToLogin.add(ratScot);
		  addToLogin.add(usrBox);
		  addToLogin.add(usrText);
		  addToLogin.add(pwdText);
		  addToLogin.add(pwdBox);
		  addToLogin.add(guestBox);
		  addToLogin.add(guestText);
		  guestBtn.addToArray(addToLogin);
		  addToLogin.add(signUpBox);
		  addToLogin.add(signUpText);
		  signUpBtn.addToArray(addToLogin);
		  addToLogin.add(usrField);
		  addToLogin.add(pwdField);
		  
		  Menu.changeScene(rootLI, addToLogin);
		  
		  //Change to login screen
		  EventHandler<Event> toLoginScreen = new EventHandler<Event>() 
		  {
			  Scene oldScene = menuScene;
			  @Override  
		      public void handle(Event event) 
			  {  
				  
				  if(!stage.getScene().equals(createAcctScene))
					  oldScene = stage.getScene();
				  
				  //String path = (((ImageInput)headerIcon.getEffect()).getSource().getUrl());
				  stage.setScene(logInScene);
				  Menu.changeScene(rootLI, addToAll);
				  rootLI.getChildren().remove(loginBtn.getButton());
				  rootLI.getChildren().remove(loginIcon);
				  rootLI.getChildren().remove(loginBox);
				  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
				  
				  
				  Menu.changeScene(rootLI, addToLogin);
				  signUpBtn.getButton().toFront();
				  guestText.toFront();
				  guestBtn.getButton().toFront();
				  
				  usrField.clear();
				  pwdField.clear();
				  
				  EventHandler<Event> toOldScene = sc.toScene(oldScene);
				  if(toOldScene != null)
					  guestBtn.addAction(toOldScene);					
				  
				  event.consume();
		      }    
		  };  
		  loginBtn.addAction(toLoginScreen); 
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
  
		  ButtonX backLIBtn = new ButtonX(guestBox,  curPalette.getAcc4Color(), buttonFlashes);
		  backLIBtn.addToGroup(rootCA);
		  backLIBtn.addAction(toLoginScreen);
		  
		  //Change to Create Account Screen
		  EventHandler<Event> toSignUpScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  stage.setScene(createAcctScene);
				  Menu.changeScene(rootCA, addToAll);
				  rootCA.getChildren().remove(loginBtn.getButton());
				  rootCA.getChildren().remove(loginIcon);
				  rootCA.getChildren().remove(loginBox);
				  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
				  Menu.changeScene(rootCA, addToLogin);
				  backLIBtn.getButton().toFront();
				  
				  usrField.clear();
				  pwdField.clear();
				  confirmField.clear();
				  
				  event.consume();
		      }    
		  };  
		  signUpBtn.addAction(toSignUpScreen); 
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
		  
		  		  //0: curDir
		  //1: prog
		  //2: scanPercent
		  //3: filesScanned
		  //4: ratsFound
		  //5: cancelText
		  //6: scanTitle
		  
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
		  
		  //this is kinda a mess bc of buggy CSS stuff :(
		  
		  //Shows progress through scan
		  ProgressBar prog = Menu.makeProgressBar((int)(sceneWidth*0.9), (int)(titleSize*1.5), (int)(sceneWidth*0.05), topBarrier + buffer*2, curPalette.getSecColor(), curPalette.getBgColor(), curPalette.getLineColor());

				  // ProgressBarX((int)(sceneWidth*0.9), titleSize, (int)(sceneWidth*0.05), topBarrier + buffer*2);
		 
		  
		  /*prog.setPrefWidth((int)(sceneWidth*0.9));
		  prog.setPrefHeight(titleSize);
		  prog.setLayoutX((int)(sceneWidth*0.05));
		  prog.setLayoutY(topBarrier + buffer*2);*/
		  
		  //prog.addToGroup(rootSC);
		  rootSC.getChildren().add(prog);
		  
		  //mess ends here!
		
		  Text scanPercent = new Text("0%");
		  scanPercent.setFont(curPalette.getDefaultFont());
		  scanPercent.setFill(curPalette.getLineColor());
		  scanPercent.setX(prog.getLayoutX() + buffer);
		  scanPercent.setY(curDir.getY());
		  rootSC.getChildren().add(scanPercent);
		  
		  Text filesScanned = new Text("0 Files Scanned");
		  filesScanned.setFont(curPalette.getDefaultFont());
		  filesScanned.setFill(curPalette.getLineColor());
		  filesScanned.setX(prog.getLayoutX() + buffer);
		  filesScanned.setY(curDir.getY());
		  filesScanned.setWrappingWidth(prog.getPrefWidth() - buffer*2);
		  filesScanned.setTextAlignment(TextAlignment.RIGHT);
		  rootSC.getChildren().add(filesScanned);
		  
		  Text ratsFound = new Text("0 Rats Found");
		  ratsFound.setFont(curPalette.getDefaultFont());
		  ratsFound.setFill(curPalette.getLineColor());
		  ratsFound.setX(prog.getLayoutX() + buffer);
		  ratsFound.setY(curDir.getY() + titleSize);
		  ratsFound.setWrappingWidth(prog.getPrefWidth() - buffer*2);
		  ratsFound.setTextAlignment(TextAlignment.RIGHT);
		  rootSC.getChildren().add(ratsFound);
		  
		  //status icon here
		  Rectangle statusIcon = Menu.icon(iconSize, iconSize, buffer, topBarrier + buffer2, statusIconPath.replace("Big", "Small"));
		  rootST.getChildren().add(statusIcon);
		  
		  //Will be ratscot gif
		  Rectangle ratGif = Menu.icon((int)(sceneHeight/2.5), (int)(sceneHeight/2.5), (sceneWidth - (int)(sceneHeight/2.5))/2, sceneHeight/2 - buffer2, curPath + "hamster-wheel.gif");
		  rootSC.getChildren().add(ratGif);
		  
		  //SCAN FINISHED SCREEN
		  scanFScene.setFill(curPalette.getBgColor());
		  sc.addScene(scanFScene, null);
		  
		  //Title
		  Text resultsTitle = new Text("Scan Results:");
		  resultsTitle.setFont(curPalette.getTitle2Font());
		  resultsTitle.setFill(curPalette.getAcc3Color());
		  Menu.centerText(sceneWidth, 0, 0, resultsTitle);
		  resultsTitle.setY(topBarrier+buffer2*2);
		  rootSF.getChildren().add(resultsTitle);
		  
		  //Image
		  Rectangle resultsIcon = Menu.icon((int)(sceneHeight/2.5), (int)(sceneHeight/2.5), (sceneWidth - (int)(sceneHeight/2.5))/2, sceneHeight/2 - buffer2, statusIconPath);
		  rootSF.getChildren().add(resultsIcon);
		  
		  //Title 2
		  Text resultsTitle2 = new Text("Results");
		  resultsTitle2.setFont(curPalette.getTitleFont());
		  resultsTitle2.setFill(curPalette.getPriColor());
		  Menu.centerText(sceneWidth, 0, 0, resultsTitle2);
		  resultsTitle2.setY(resultsTitle.getY() + titleSize*2);
		  rootSF.getChildren().add(resultsTitle2);
		  
		  //Body text
		  Text resBody = new Text("Results");
		  resBody.setFont(curPalette.getDefaultFont());
		  resBody.setFill(curPalette.getLineColor());
		  Menu.centerText(sceneWidth, sceneHeight, 0, 0, resBody);
		  resBody.setY(resultsTitle2.getY() + titleSize);
		  rootSF.getChildren().add(resBody);
		  
		  
		  //Back button
		  Rectangle finishBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  rootSF.getChildren().add(finishBox);
		  
		  Text finishTxt = new Text("Finish");
		  finishTxt.setFont(curPalette.getTitleFont());
		  finishTxt.setFill(curPalette.getLineColor());
		  
		  ButtonX finishBtn = new ButtonX(finishBox, curPalette.getAcc4Color(), finishTxt, buttonFlashes);
		  finishBtn.addAction(toMenuScreen);
		  finishBtn.addToGroup(rootSF);
		  
		  //Next button
		  Rectangle nextBox = Menu.icon(icon2Width/2, iconSize, sceneWidth - icon2Width/2 - buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  
		  Text nextTxt = new Text("Next");
		  nextTxt.setFont(curPalette.getTitleFont());
		  nextTxt.setFill(curPalette.getLineColor());
		  
		  ButtonX nextBtn = new ButtonX(nextBox, curPalette.getAcc4Color(), nextTxt, buttonFlashes);
		  nextBtn.addToGroup(rootSF);
		  nextBtn.setVisible(false);
		  
		  //SCAN FINISHED SCREEN END
		  
		  
	      prog.progressProperty().addListener(new ChangeListener()
			      {
			          @Override public void changed(ObservableValue o,Object oldVal, Object newVal)
			          {
			        	  scanPercent.setText(Math.round((Double)(newVal)*100) + "%");
			        	  filesScanned.setText(Scan2.count + " Files Scanned");
			        	  ratsFound.setText(Scan2.rats + " Rats Found");	
			          }
			      });
	      
	      curDir.textProperty().addListener(new ChangeListener()
	      {
	          @Override public void changed(ObservableValue o,Object oldVal, Object newVal)
	          {
	        	  if(curDir.getText().equals("Scan Completed")
	        			  || curDir.getText().equals("Scan Cancelled"))
    			  {
	        		  stage.setScene(scanFScene);
	        		  Menu.changeScene(rootSF, addToAll);
			  	      homeBtn.getButton().setVisible(true);
			  	      
			  	      resultsTitle.setText(curDir.getText());
			  	      
		  			  nextBtn.setVisible(Scan2.rats != 0);

		  			  //Get time
		  			  File temp = new File("ScanLogs");
		  			  String timeStr = (temp.list()[temp.list().length-1]);
		  			  timeStr = timeStr.substring(timeStr.indexOf(' ')+1, timeStr.indexOf('.'));
			  	      timeStr = displayTime(timeStr);
		  			  
		  			  logInfo.writeLine("Last Scan:\t" + timeStr, 1);
		  			  logInfo.writeFile();
		  			  
			  	      //No threats found
			  	      if(Scan2.rats == 0)
			  	      {

			  	    	  finishTxt.setText("Finish");
			  	    	  resultsTitle2.setFill(curPalette.getPriColor());
			  	    	  resultsTitle2.setText("Looks like you're all good!");
			  	    	  
			  	    	  resBody.setText(filesScanned.getText() + "\n"
			  	    	  	+ ratsFound.getText());
			  	    	  
			  	    	  curPalette.changeImg(resultsIcon, statusIconPath, true);
			  	    	  
			  	    	  if(scanTitle.getText().contains("Full")) //full scan, no threats			  	   
			  	    	  {
			  	    		  curPalette.changeImg(statusIcon, statusIconPath.replace("Big", "Small"), true);
			  	    		  curPalette.changeImg(statusIconBig, statusIconPath, true);
			  	    		  StatusInfo.clearRats();
			  	    	  }
			  	      }
			  	      
			  	      //threats found
			  	      else
			  	      {					  	    	  
			  	    	  finishTxt.setText("Later");
			  	    	  resultsTitle2.setFill(curPalette.getRed());
			  	    	  resultsTitle2.setText("We trapped some rats for you!");
			  	    	  resBody.setText("Don't sweat it!,\n Hit 'Next' and well sort that out real quick.");
			  	    	  
			  	    	  curPalette.changeImg(resultsIcon, curPath + "ratCage.png", true);
			  	    	  curPalette.changeImg(statusIcon, curPath + "statusBadSmall.PNG", true);
			  	    	  curPalette.changeImg(statusIconBig,curPath + "statusBadBig.PNG", true);
			  	      }
	  	    		  StatusInfo.refresh();
    			  }
	          }
	      });

  		Scan2.cs =cs;

	      EventHandler<Event> deployScan = new EventHandler<Event>()
		  {
			    @SuppressWarnings("unchecked")
				@Override  
			    public void handle(Event event)
			    {  
			    	//Reset values
			    	
			    	scanTitle.setText(scanBS.getValue());
			    	Palette.changeImg(ratGif, curPath + "hamster-wheel.gif");
			    	Scan2 scan = null;
			    	
			    	if(scanBS.getValue().contains("Quick"))
			    	{
			    		scan = new Scan2(ScanType.QUICK, null);			    		
			    		System.out.println("Quick Scan");
		                executorService.execute(scan);
			    	}
			    	else if(scanBS.getValue().contains("Custom"))
			    	{
			    		scan = new Scan2(ScanType.CUSTOM, ScanManager.custPath);			    		
			    		System.out.println("Custom Scan");
		                executorService.execute(scan);
		                
			    	}
			    	else 
			    	{
			    		System.out.println("Full Scan");
			    		scan = new Scan2(ScanType.FULL, null);			    		
			    	}
			    	
			    	curDir.textProperty().bind(scan.messageProperty());
		  	      	prog.progressProperty().bind(scan.progressProperty());
		  	      	homeBtn.getButton().setVisible(false);
		  	      	
		    		executorService.execute(scan);
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
						  ScanManager.custPath = dir1.toString();
						  System.out.println(dir1.toString()); //TEMP
						  //After file is chosen, starts scan process
						  Menu.changeScene(rootSC, addToAll); //adds objects
						  //curDir.setText(scanBS.getValue());
						  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
						  stage.setScene(scanScene); //changes to scan screen
						  
						  //Deploys scan
						  try
						  {
							  if(stage.getScene() == scanScene);
							  {
								System.out.println("Deploying scan...");
							  	deployScan.handle(event);
							  }
						  }
						  catch(NullPointerException e)
						  {
							  e.printStackTrace();
						  }
					  }

				  }
				  
				  else
				  {
					  Menu.changeScene(rootSC, addToAll); //adds objects
					  //curDir.setText(scanBS.getValue());
					  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
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
		  
		  		  
		  sc.addScene(scanScene, toScanScreen);
		  
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ STATUS ~~~~~~~~~~~~~~~~~~~~~
		  //Variables
		  StatusInfo.refresh();
		  if(StatusInfo.getTotalRats() > 0)  
			  curPalette.changeImg(statusIconBig, statusIconPath.replace("Good", "Bad"), true);
		  
		  
		  statusScene.setFill(curPalette.getBgColor());
		 
		  statusScene.setFill(curPalette.getBgColor());
		  
		  Line statDiv = new Line(sceneWidth/2, 0, sceneWidth/2, sceneHeight);
		  statDiv.setStrokeWidth(2);
		  statDiv.setStroke(curPalette.getLineColor());
		  rootST.getChildren().add(statDiv);
		  statDiv.setOpacity(0.5);
		  
		  Rectangle backSTBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  rootST.getChildren().add(backSTBox);
		  
		  Text backSTText = new Text("Done");
		  backSTText.setFont(curPalette.getTitleFont());
		  backSTText.setWrappingWidth(icon2Width/2);
		  Menu.centerText(backSTBox, backSTText);
		  rootST.getChildren().add(backSTText);
		  backSTText.setFill(curPalette.getLineColor());

		  ButtonX backSTBtn = new ButtonX(backSTBox, curPalette.getAcc4Color(), buttonFlashes);
		  backSTBtn.addAction(toMenuScreen);  
		  backSTBtn.addToGroup(rootST);
		  
		  Text statTitle = new Text();
		  statTitle.setText("Trapped Rats");
		  Menu.centerText((int)(sceneWidth/2*0.9), (int)(sceneWidth/2*0.05), topBarrier+buffer2*2, statTitle);
		  statTitle.setFont(curPalette.getTitle2Font());
		  statTitle.setFill(curPalette.getAcc3Color());
		  rootST.getChildren().add(statTitle);
		  
		  Text ratTitle = new Text();
		  Menu.centerText((int)(sceneWidth/2*0.9), sceneWidth/2 + (int)(sceneWidth/2*0.05), topBarrier+buffer2*2, ratTitle);
		  ratTitle.setFont(curPalette.getTitle2Font());
		  ratTitle.setFill(curPalette.getAcc3Color());
		  rootST.getChildren().add(ratTitle);
		  
		  Text sevTxt = new Text();
		  Menu.centerText((int)(sceneWidth/2*0.9), sceneWidth/2 + (int)(sceneWidth/2*0.05), (int)ratTitle.getY() + buffer2, sevTxt);
		  sevTxt.setFont(curPalette.getSubTitleFont());
		  sevTxt.setFill(curPalette.getLineColor());
		  rootST.getChildren().add(sevTxt);
		  
		  Text ratPath = new Text();
		  Menu.centerText((int)(sceneWidth/2*0.9), sceneWidth/2 + (int)(sceneWidth/2*0.05), (int)sevTxt.getY() + buffer2, ratPath);
		  ratPath.setFont(curPalette.getSubTitleFont());
		  ratPath.setFill(curPalette.getLineColor());
		  rootST.getChildren().add(ratPath);
		  
		  Text ratDesc = new Text();
		  Menu.centerText((int)(sceneWidth/2*0.9), sceneWidth/2 + (int)(sceneWidth/2*0.05), sceneHeight/2, ratDesc);
		  ratDesc.setFont(curPalette.getDefaultFont());
		  ratDesc.setFill(curPalette.getLineColor());
		  rootST.getChildren().add(ratDesc);
		  
		  Rectangle delRect = Menu.icon(icon2Width/2, iconSize, sceneWidth/2 + buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getRed(), curPalette.getLineColor());
		  Text delTxt = new Text("Delete Rat");
		  delTxt.setFont(curPalette.getSubTitleFont());
		  delTxt.setFill(curPalette.getBgColor());
		  ButtonX delBtn = new ButtonX(delRect, curPalette.getLineColor(), delTxt, buttonFlashes);
		  //delBtn.addToGroup(rootST);
		  
		  Rectangle whiteRect = Menu.icon(icon2Width/2, iconSize, sceneWidth - icon2Width/2 - buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getSecColor(), curPalette.getLineColor());
		  Text whiteTxt = new Text("Mark as Safe");
		  whiteTxt.setFont(curPalette.getSubTitleFont());
		  whiteTxt.setFill(curPalette.getLineColor());
		  ButtonX whiteBtn = new ButtonX(whiteRect, curPalette.getAcc3Color(), whiteTxt, buttonFlashes);
		  //whiteBtn.addToGroup(rootST);
		  
		  Menu scanActions = new Menu();
		  delBtn.addToArrays(scanActions.getComponents(), scanActions.getButtons());
		  whiteBtn.addToArrays(scanActions.getComponents(), scanActions.getButtons());

		  
		  //CONFIRMATION POPUP
		  ArrayList<Shape> confComp = new ArrayList<Shape>();
		  ArrayList<Control> confBtns = new ArrayList<Control>();

		  Rectangle blockScreen = Menu.icon(sceneWidth, sceneHeight, 0, 0, 0, curPalette.getLineColor(), curPalette.getLineColor());
		  blockScreen.setOpacity(0.5);
		  confComp.add(blockScreen);
		  
		  Rectangle confBg = Menu.icon(sceneWidth/2, sceneHeight/2, sceneWidth/4, sceneHeight/4, 10, curPalette.getBgColor(), curPalette.getLineColor());
		  confComp.add(confBg);
		  
		  Rectangle confTop = Menu.icon(sceneWidth/2, sceneHeight/8, sceneWidth/4, sceneHeight/4, 10, curPalette.getPriColor(), curPalette.getLineColor());
		  confComp.add(confTop);
		  
		  Rectangle confIcon = Menu.icon((int)(confTop.getHeight()*0.5), (int)(confTop.getHeight()*0.5), sceneWidth/4 + (int)(confTop.getHeight()*0.5), sceneHeight/4 + (int)(confTop.getHeight()*0.25), curPath + "statusOkSmall.PNG");
		  confComp.add(confIcon);
		  
		  ArrayList<ButtonX> confBtnXs = new ArrayList<ButtonX>();
		  for(int i = 0; i < 2; i++)
		  {
			  Rectangle rect = Menu.icon((int)(1.5*icon2Width/4), (int)(1.5*iconSize/2), 0, (int)(3*confBg.getHeight()/4 + confBg.getY()), 10, curPalette.getBgColor(), curPalette.getLineColor());
			  Text txt = new Text();
			  txt.setFont(curPalette.getSubTitleFont());
			  txt.setFill(curPalette.getLineColor());
			  
			  //Set variables
			  switch(i)
			  {
			  	case 0:
			  		txt.setText("No");
			  		rect.setX(confBg.getX() + buffer2);
			  		break;
			  	case 1:
			  		txt.setText("Yes");
			  		rect.setX(confBg.getX() + confBg.getWidth() - buffer2 - rect.getWidth());
			  		break;
			  }
			  ButtonX btnX = new ButtonX(rect, curPalette.getSecColor(), txt, buttonFlashes);
			  btnX.addAction((new EventHandler<Event>()
			  {
				  @Override  
				  public void handle(Event event) 
				  {
					  whiteBtn.getActions().get(1).handle(event);
					  blockScreen.setVisible(false);
					  
					  event.consume();
				  }
			  }));
			  
			  confBtnXs.add(btnX);
			  btnX.addToGroup(rootST);
			  
			  confComp.add(rect);
			  confComp.add(txt);
			  confBtns.add(btnX.getButton());
		  }
		  
		  Text confTitle = new Text("Are you sure you want to do this?");
		  confTitle.setFont(curPalette.getTitleFont());
		  confTitle.setFill(curPalette.getLineColor());
		  Menu.centerText(confTop, confTitle);
		  confComp.add(confTitle);
		  
		  Text confBody = new Text("ONLY hit 'yes' if you are 100% sure this file is safe: such as if you created it or it's from a trusted individual."
				  + "\nThis file will be maked as 'safe' and no longer be trapped by scans."
				  + "\nOther Rats of this type will still be Trapped.");
		  confBody.setFont(curPalette.getDefaultFont());
		  confBody.setFill(curPalette.getLineColor());
		  Menu.centerText((int)(confBg.getWidth()*0.9), (int)(confBg.getX() + confBg.getWidth()*0.05), (int)(confTop.getY() + confTop.getHeight() + buffer2), confBody);
		  confComp.add(confBody);
		  
		  PopUp confPP = new PopUp(whiteBtn, 100, confComp, confBtns);
		  confPP.addToGroup(rootST);
		  animMenus.add(confPP);
		  
		  Text statLabel = new Text("Take action:");
		  statLabel.setFont(curPalette.getTitleFont());
		  statLabel.setFill(curPalette.getAcc3Color());
		  Menu.centerText(sceneWidth/2, sceneWidth/2, (int)(6.5*sceneHeight/8), statLabel);
		  scanActions.getComponents().add(statLabel);
		  scanActions.addToGroup(rootST);
		  
		  Rectangle delAllBox = Menu.icon(icon2Width/2, iconSize, sceneWidth/2 - buffer2 - icon2Width/2, sceneHeight-buffer2-iconSize, 10, curPalette.getRed(), curPalette.getLineColor());
		  rootST.getChildren().add(delAllBox);
		  
		  Text delAllTxt = new Text("Delete All Rats");
		  delAllTxt.setFont(curPalette.getSubTitleFont());
		  delAllTxt.setWrappingWidth(icon2Width/2);
		  delAllTxt.setFill(curPalette.getBgColor());

		  ButtonX delAllBtn = new ButtonX(delAllBox, curPalette.getLineColor(), delAllTxt, buttonFlashes);
		  delAllBtn.addToGroup(rootST);
		  
		  ArrayList<Rat> rats = new ArrayList<Rat>();
		  Rat curRat = new Rat();
		  //ButtonSwitch ratSelect = new ButtonSwitch(sceneWidth/2, sceneHeight/2, buffer2, topBarrier+iconSize+titleSize+buffer2, true, null, curPalette.getLineColor(), curPalette.getRed(), curPalette.getDefaultFont());
		  
		  Text elipses = new Text("...");
		  elipses.setFont(curPalette.getDefaultFont());
		  elipses.setFill(curPalette.getLineColor());
		  Menu.centerText(sceneWidth/2, 0, (int)backSTBox.getY() - buffer2, elipses);
		  rootST.getChildren().add(elipses);
		  
		  ArrayList<ButtonSwitch> bs = new ArrayList<ButtonSwitch>();
		  EventHandler<Event> refreshStatus = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  System.out.println("Refreshing Status Display...");
				  StatusInfo.refresh();
				  
				  scanActions.setVisible(StatusInfo.getTotalRats() != 0);
				  
				  
				  statTitle.setText(("Trapped Rats (X)").replace("X", StatusInfo.getTotalRats()+""));

				  if(StatusInfo.getTotalRats() > 0)
				  {
					  curPalette.changeImg(statusIcon, (statusIconPath.replace("Good", "Bad")).replace("Big", "Small"), true);
					  curPalette.changeImg(statusIconBig, statusIconPath.replace("Good", "Bad"), true);
				  }
				  else
				  {
					  ratTitle.setText("No Rats Found");
					  ratDesc.setText("You're all good! Come back here once we've trapped some Rats for you.");
					  sevTxt.setText("");
					  ratPath.setText("");
					  curPalette.changeImg(statusIcon, (statusIconPath.replace("Bad", "Good")).replace("Big", "Small"), true);
					  curPalette.changeImg(statusIconBig, statusIconPath.replace("Bad", "Good"), true);

					  if(!bs.isEmpty())
					  {
						  bs.get(0).removeFromGroup(rootST);
					  }
					  
					  event.consume();
					  return;
				  }
				  
				  int count = 0;
				  ArrayList<String> ratStrs = new ArrayList<String>();
				  rats.clear();
				  for(Rat rat : StatusInfo.getRats())
				  {
					  if(count > 5)
						  break;
					  
					  rats.add(rat);
					  String temp = rat.getFilePath()	+ " -- " + rat.getCategory() + " -- " + rat.getSeverity();
					  if(temp.length() > 50)
						  temp = "..." + temp.substring(temp.length()-50);
					  
					  ratStrs.add(temp);
					  count++;
				  }
				  
				  ButtonSwitch ratSelect = new ButtonSwitch(sceneWidth/2, sceneHeight/2, buffer2, (int)(statTitle.getY() + buffer2), true, ratStrs, curPalette.getLineColor(), curPalette.getRed(), curPalette.getDefaultFont());
				  for(int i = 0; i < ratSelect.size(); i++)
				  {
					  final int index = i;
					  ratSelect.getButtonXs().get(i).addAction((new EventHandler<Event>()
					  {
						  @Override  
						  public void handle(Event event) 
						  {							  
							  Rat rat = rats.get(index);
							  curRat.setRat(rat.getId());
							  
							  //Virus type
							  ratTitle.setText("Threat level: " + rat.getCategory());
							  
							  //Threat level
							  Severity sev = rat.getSeverity();
							  sevTxt.setText(sev.toString());
							  Color c = null;
							  
							  switch(sev)
							  {
							  	case LOW:
							  		c = (curPalette.getSecColor());
							  		break;
							  	case MEDIUM:
							  		c = (curPalette.getAcc4Color());
							  		break;
							  	default:
							  		c = (curPalette.getRed());
							  		break;
							  }
							  sevTxt.setFill(c);
							  
							  //Infected file
							  String temp = rat.getFilePath();
							  //if(temp.length() > 50)
							  //  temp = "..." + temp.substring(temp.length()-50);

							  ratPath.setText(temp);
							  
							  //virus description
							  String desc = new String("We think we trapped a(n) " + rat.getCategory().toLowerCase() + " Rat in the above file.\n\n" 
							  + StatusInfo.getDescription(rat.getCategory()));
							  if(desc.contains("N/A"))
								  desc = "We think we trapped a Rat in the above file";
							  
							  ratDesc.setText(desc);
							  
						  }
					  }
					 ));
				  }
				  if(ratTitle.getText().isBlank())
				  {
					  Text txt = (Text)ratSelect.getComponents().get(0);
					  txt.setFill(curPalette.getLineColor());
					  txt.setUnderline(false);
					  
				  }
						  
				  bs.add(ratSelect);				  
				  ratSelect.addToGroup(rootST);
				  
				  if(bs.size() > 1)
				  {
					  bs.get(0).removeFromGroup(rootST);
					  bs.remove(0);
				  }
				  
				  if(StatusInfo.getTotalRats() > 5)
					  elipses.setVisible(true);
				  else
					  elipses.setVisible(false);
				  
				  
				  if(StatusInfo.getTotalRats() != 1)
					  scanActions.setVisible(false);
				  else // only one rat
				  {
					  curRat.setRat(rats.get(0).getId());

				  }
				  
				  event.consume();
			  }  
		  };
		  
		  
		  //Remove rat from statusinfo IMPORTANT
		  EventHandler<Event> removeRat = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  if(curRat.getId() == null)
				  {
					  System.out.println("ERR, ratId is null");
				  }
				  else
				  {
					  StatusInfo.removeRat(curRat.getId());
					  StatusInfo.refresh();
				  }
				  event.consume();
			  }
		  };
		  
		  //Delete rat IMPORTANT
		  delBtn.addAction((new EventHandler<Event>()
		  {
			  @Override  
			  public void handle(Event event) 
			  {
				  System.out.println("Deleting " + curRat.getFilePath() + " -- " + curRat.getCategory());
				  
				  removeRat.handle(event);
				  event.consume();
			  }
		  }));
		  
		  //Delete all rats
		  delAllBtn.addAction((new EventHandler<Event>()
		  {
			  @Override  
			  public void handle(Event event) 
			  {
				  System.out.println("Deleting all Rats...");

				  for(Rat rat : StatusInfo.getRats())
				  {
					  if(rat.getId() != null)
					  {
						  System.out.println("Deleting " + rat.getFilePath() + " -- " + rat.getCategory());
						  StatusInfo.removeRat(rat.getId());
					  }
				  }
				  StatusInfo.refresh();
			  }
		  }));
		  
		  //Whitelist rat IMPORTANT
		  confBtnXs.get(1).addAction((new EventHandler<Event>()
		  {
			  @Override  
			  public void handle(Event event) 
			  {
				  System.out.println("Whitelisting " + curRat.getFilePath() + " -- " + curRat.getCategory());

				  removeRat.handle(event);
				  event.consume();
			  }
		  }));
		  
		  EventHandler<Event> toStatusScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  
				  stage.setScene(statusScene);
				  Menu.changeScene(rootST, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
				  refreshStatus.handle(event);
				  
				  if(StatusInfo.getTotalRats() >= 1) //more than 1 rat
				  {
					  ratTitle.setText("");
					  ratPath.setText("");
					  sevTxt.setText("");
					  ratDesc.setText("Select a Rat from the lefthand side of the screen to begin.");
				  
				  }
				  else //if (StatusInfo.getTotalRats() == 1)
				  {
					  
				  }
				  event.consume();
		      }    
		  };  
		  statusBtn.setOnMouseClicked(toStatusScreen);
		  delBtn.addAction(refreshStatus);
		  confBtnXs.get(1).addAction(refreshStatus);
		  delAllBtn.addAction(refreshStatus);
		  
		  sc.addScene(statusScene, toStatusScreen);
		 
		  
		  nextBtn.addAction(toStatusScreen);
		  

		  //~~~~~~~~~~~~~~~~~~~~~~ UPDATE ~~~~~~~~~~~~~~~~~~~~~
		  updateScene.setFill(curPalette.getBgColor());
		  Rectangle backUPBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());
		  
		  Text backUPText = new Text("Back");
		  backUPText.setFont(curPalette.getTitleFont());
		  backUPText.setWrappingWidth(icon2Width/2);
		  backUPText.setX(backUPBox.getX());
		  backUPText.setFill(curPalette.getLineColor());
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
				  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
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
		  
		  //History scene
		  
		  Rectangle backHSBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());		 
		  Text backHSTxt = new Text("Back");
		  backHSTxt.setFont(curPalette.getTitleFont());
		  backHSTxt.setFill(curPalette.getLineColor());
		  ButtonX backHSBtn = new ButtonX(backHSBox, curPalette.getAcc4Color(), backHSTxt, buttonFlashes);
		  backHSBtn.addToGroup(rootHS);
		  backHSBtn.addAction(toMenuScreen); 
		  
		  ArrayList<File> scanLogs = new ArrayList<File>();
		  
		  EventHandler<Event> refreshHist = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  { 
				  File[] files = (new File("ScanLogs")).listFiles();
				  scanLogs.clear();
				  
				  if(files.length == 0) //empty
				  {
					  
				  }
				  else
				  {
					  int i = files.length-1;
					  
					  while(i >= 0 && i > files.length-5)
					  {
						  
					  }
				  }
				  
				  event.consume();
			  }
		  };
		  
		  EventHandler<Event> toHistoryScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  
				  stage.setScene(historyScene);
				  Menu.changeScene(rootHS, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
				  
				  event.consume();
		      }    
		  };  
		  sc.addScene(historyScene, toHistoryScreen);
		  bottomButtons.get(1).addAction(toUpdateScreen);
		  
		  
		  
		//~~~~~~~~~~~~~~~~~~~~~~ About ~~~~~~~~~~~~~~~~~~~~~
		aboutScene.setFill(curPalette.getBgColor());
		Button backABBtn = Menu.makeButton(backUPBox, curPalette.getAcc4Color(), buttonFlashes);		
		rootAB.getChildren().add(backABBtn);
		
		aboutFill.setFromValue((curPalette.getAcc2Color()));
		aboutFill.setToValue(curPalette.getLineColor());
		
	    EventHandler<Event> toAboutScreen = new EventHandler<Event>()
		{  
            Scene oldScene = menuScene;
            @Override  
            public void handle(Event arg0) 
            {  
            	if(!oldScene.equals(aboutScene))
            		oldScene = stage.getScene();
            	            	
            	aboutFill.play();
            	Color temp = (Color)about.getFill();
            	aboutFill.setFromValue(aboutFill.getToValue());
            	aboutFill.setToValue(temp);
            	stage.setScene(aboutScene);
				Menu.changeScene(rootAB, addToAll);
				
				curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
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
				arg0.consume();
            }  
	    };  
	    
		ButtonX aboutBtnX = new ButtonX(aboutBtn);
		
		aboutBtnX.addAction(new EventHandler<Event>() //kill scan
				  {
			    public void handle(Event event)
			    {
			    	getHostServices().showDocument("https://rattrapav.neocities.org");
			    }
		  });		aboutBtnX.addToGroup(rootMM);
	    
		sc.addScene(aboutScene, toAboutScreen);  
	    
		
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
		/*EventHandler<Event> toNotifScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  stage.setScene(notifScene);
				  Menu.changeScene(rootNO, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
				  event.consume();

		      }    
		  };  
		  //notifBtn.setOnMouseClicked(toNotifScreen); 
		  sc.addScene(notifScene, toNotifScreen);*/
		  
		  //~~~~~~~~~~~~~~~~~~~~~~ Layout ~~~~~~~~~~~~~~~~~~~~~
		  layoutScene.setFill(curPalette.getBgColor());
		  		  
		  Rectangle backLOBox = Menu.icon(icon2Width/2, iconSize, buffer2, sceneHeight-buffer2-iconSize, 10, curPalette.getAcc1Color(), curPalette.getLineColor());		 
		  Text backLOTxt = new Text("Back");
		  backLOTxt.setFont(curPalette.getTitleFont());
		  backLOTxt.setFill(curPalette.getLineColor());
		  ButtonX backLOBtn = new ButtonX(backLOBox, curPalette.getAcc4Color(), backLOTxt, buttonFlashes);
		  backLOBtn.addToGroup(rootLO);
		  backLOBtn.addAction(toMenuScreen); 
		  
		  //ScrollMenu
		  //								w			h											comp width						comp height				 x  	y	  columns
		  ScrollMenu sm = new ScrollMenu(sceneWidth, (int)(backLOBox.getY()) - topBarrier, (int)(icon2Height*1.5) + titleSize, (int)(icon2Height*1.5) + titleSize, 0, topBarrier, 4, curPath + "dropdownIcon.PNG");
		  sm.setColors(curPalette.getLineColor(), curPalette.getSecColor(), curPalette.getSecColor(), curPalette.getLineColor(), curPath + "dropdownIcon.PNG");		  
		  
		  ArrayList<Shape> exclude = new ArrayList<Shape>();
		  
		  ArrayList<String> themeNames = new ArrayList<String>();
		  ArrayList<Palette> themes = new ArrayList<Palette>();

		  //Light (Default) Theme
		  ThemeSelect lightSelect = new ThemeSelect(lightTheme, (int)(icon2Height*1.5), 10, lightTheme.getName());
		  lightSelect.addToArrays(exclude, null);
		  lightSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(lightTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(lightSelect);
		  themeNames.add(lightTheme.getName());
		  themes.add(lightTheme);
		  
		  //									bg				pri					sec					acc1		acc2					acc3				acc4		outline
		  Palette lightHCTheme = new Palette(Color.WHITE, Color.MEDIUMSEAGREEN, Color.DEEPSKYBLUE, Color.PLUM, Color.LAVENDERBLUSH, Color.MIDNIGHTBLUE.darker(), Color.PURPLE, Color.BLACK, true);
		  lightHCTheme.setRed(Color.MEDIUMVIOLETRED);
		  lightHCTheme.setFonts("Segoe UI Semibold", titleSize, "Segoe UI", (int)(titleSize*0.75));	  
		  lightHCTheme.setName("Hi-Con L.");
		  ThemeSelect lightHCSelect = new ThemeSelect(lightHCTheme, (int)(icon2Height*1.5), 10, lightHCTheme.getName()); 
		  lightHCSelect.addToArrays(exclude, null);
		  lightHCSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(lightHCTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(lightHCSelect);
		  themeNames.add(lightHCTheme.getName());
		  themes.add(lightHCTheme);
		  
		  //								bg				pri			sec				acc1		acc2			acc3		acc4			outline
		  Palette darkTheme = new Palette(balticSeaGray, pineGreen, shamrockGreen, ravenPurple, wisteriaPurple, dragonGreen, lilacPurple, ceramicWhite, false);
		  darkTheme.setFonts("Maiandra GD", titleSize, "Segoe UI", (int)(titleSize*0.75));	  
		  darkTheme.setName("Dark");
		  ThemeSelect darkSelect = new ThemeSelect(darkTheme, (int)(icon2Height*1.5), 10, "Dark");
		  darkSelect.addToArrays(exclude, null);
		  darkSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(darkTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(darkSelect);
		  themeNames.add(darkTheme.getName());
		  themes.add(lightTheme);
		  
		  //										bg				pri			sec				acc1		acc2			acc3		acc4			outline
		  Palette darkHCTheme = new Palette(Color.BLACK, Color.SEAGREEN, Color.LIGHTGREEN, Color.PURPLE, Color.VIOLET, Color.SKYBLUE.brighter(), Color.LAVENDERBLUSH, Color.WHITE, false);
		  darkHCTheme.setRed(wineRed);
		  darkHCTheme.setFonts("Segoe UI Semibold", titleSize, "Segoe UI", (int)(titleSize*0.75));	  
		  darkHCTheme.setName("Hi-Con D.");
		  ThemeSelect darkHCSelect = new ThemeSelect(darkHCTheme, (int)(icon2Height*1.5), 10, darkHCTheme.getName());
		  darkHCSelect.addToArrays(exclude, null);
		  darkHCSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(darkHCTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(darkHCSelect);
		  themeNames.add(darkHCTheme.getName());
		  themes.add(darkHCTheme);

		  
		  //									bg				pri			sec				acc1		acc2				acc3					acc4			outline
		  Palette lavanderTheme = new Palette(celesteGray, lilacPurple, moonPurple, ravenPurple, Color.web("585570"), Color.web("1c5232"), Color.web("383747"), balticSeaGray ,false);
		  lavanderTheme.setFonts("Adobe Garamond Pro Bold", titleSize, "Adobe Hebrew", (int)(titleSize*0.75));	  
		  lavanderTheme.setName("Lavander");
		  ThemeSelect lavanderSelect = new ThemeSelect(lavanderTheme, (int)(icon2Height*1.5), 10, lavanderTheme.getName());
		  lavanderSelect.addToArrays(exclude, null);
		  lavanderSelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(lavanderTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(lavanderSelect);
		  themeNames.add(lavanderTheme.getName());
		  themes.add(lavanderTheme);

		  //									bg					pri							sec				acc1				acc2				acc3					acc4			outline
		  Palette cherryTheme = new Palette(Color.web("f5ecef"), Color.web("fe9fb5"), Color.web("e8697a"), daisyYellow, honeysuckleYellow, Color.web("b91230"), sycamoreYellow, Color.web("442415") ,true);
		  cherryTheme.setRed(Color.web("d13d55"));
		  cherryTheme.setFonts("Harrington", titleSize, "Perpetua", (int)(titleSize*0.75));	  
		  cherryTheme.setName("Cherry");
		  ThemeSelect cherrySelect = new ThemeSelect(cherryTheme, (int)(icon2Height*1.5), 10, cherryTheme.getName());
		  cherrySelect.addToArrays(exclude, null);
		  cherrySelect.getButton().setOnMouseClicked(event -> curPalette.changePalette(cherryTheme, sc.toArray(), buttonFlashes, exclude));
		  sm.addComponent(cherrySelect);
		  themeNames.add(cherryTheme.getName());
		  themes.add(cherryTheme);

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
		  themeNames.add(draculaTheme.getName());
		  themes.add(draculaTheme);

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
		  themeNames.add(nordicTheme.getName());
		  themes.add(nordicTheme);

		  sm.addToGroup(rootLO);  
		  
		  backLOBtn.addAction(new EventHandler<Event>()
			{
				@Override
				public void handle(Event arg0)
	            {
					PrefReader.updatePref("Theme", curPalette.getName());
	            }
			});		 
		  
		  EventHandler<Event> toLayoutScreen = new EventHandler<Event>() 
		  {
			  @Override  
		      public void handle(Event event) 
			  {  
				  sm.reset();
				  stage.setScene(layoutScene);
				  rootLO.requestFocus();
				  Menu.changeScene(rootLO, addToAll);
				  curPalette.changeImg(headerIcon, curPath + "RatHome.PNG", true);
				  scanDropdown.toFront();
				  scanDropdownBtn.toFront();
				  event.consume();
		      }    
		  };  

		  //settingsButtons.get(0).setOnMouseClicked(toLayoutScreen);
		  settingsButtons.get(0).addAction(toLayoutScreen);
		  sc.addScene(layoutScene, toLayoutScreen);  
		  
		  							
		  Toggle animTog= new Toggle(icon2Width/2, iconSize, sceneWidth/4, sceneHeight-buffer2-iconSize);
		  animTog.setColors(curPalette.getBgColor(), curPalette.getSecColor(), curPalette.getBgColor(), curPalette.getLineColor());
		  animTog.setText("Off", "On", curPalette.getDefaultFont());
		  animTog.addToGroup(rootLO);
		  animTog.addAction(new EventHandler<Event>()
			{
			  @Override  
		      public void handle(Event event) 
			  {
				  for(int i = 0; i < animMenus.size(); i++)
				  {
					  animMenus.get(i).toggleAnimations();
				  }
			  }
			});
		  
		  Text animTogLabel = new Text("Animations");
		  animTogLabel.setFont(curPalette.getSubTitleFont());
		  animTogLabel.setFill(curPalette.getLineColor());
		  Menu.centerText(icon2Width/2, sceneWidth/4, (int)(animTog.getBg().getY()-buffer/2), animTogLabel);
		  rootLO.getChildren().add(animTogLabel);
  	
		  //Preferences
		  PrefReader.parse();
		  
		  String prefTheme = (String)(PrefReader.getPref("Theme"));
		  
		  System.out.println("Setting palette to + " + prefTheme + " -- " + themeNames.indexOf(prefTheme));
		  curPalette.changePalette(themes.get(themeNames.indexOf(prefTheme)),sc.toArray(), buttonFlashes, exclude);
		  curPalette.setPalette(themes.get(themeNames.indexOf(prefTheme)));		  
		  
		  if((Boolean)(PrefReader.getPref("Animations")) == false)
		  {
			  for(int i = 0; i < animMenus.size(); i++)
			  {
				  animMenus.get(i).toggleAnimations();
			  }
			 
		  }
		  else
		  {
			  animTog.move();
			  animTog.getBg().setFill(curPalette.getSecColor());
		  }
		  
		  
		  //Rectangle mark = Menu.icon(icon2Height*2, icon2Height*2, 0, 0, curPath + "markiplier.jpg");
		  //rootMM.getChildren().add(mark);		  

		  
		  //Close program
		  stage.setOnCloseRequest(new EventHandler<WindowEvent>()
		  {
	          public void handle(WindowEvent event)
	          {
	              System.out.println("Exiting...");
	  	          stage.close();
	  			  try
	  			  {
					Runtime.getRuntime().exec("Taskkill /IM clamd.exe /F");
					Runtime.getRuntime().exec("Taskkill /IM clamdscan.exe /F");

	  			  }
	  			  catch (IOException e)
	  			  {
					// TODO Auto-generated catch block
					e.printStackTrace();
	  			  }
		          
	  			  Platform.exit();
	  			  System.exit(0);

	          	}
	      });  
		  
  	}  	
  
  	public static String displayTime(String str)
  	{
  		String newStr = new String();
  		String[] numStrs = str.split("-");
  		int[] nums = new int[5];
  		
  		for(int i = 0; i < 5; i++)
  		{
  			nums[i] = Integer.parseInt(numStrs[i]);
  		}
  		
  		switch(nums[0])
  		{
  			case 1:
  				newStr = "Jan";
  				break;
  			case 2:
  				newStr = "Feb";
  				break;
  			case 3:
  				newStr = "Mar";
  				break;
  			case 4:
  				newStr = "Apr";
  				break;
  			case 5:
  				newStr = "May";
  				break;
  			case 6:
  				newStr = "Jun";
  				break;
  			case 7:
  				newStr = "Jul";
  				break;
  			case 8:
  				newStr = "Aug";
  				break;
  			case 9:
  				newStr = "Sep";
  				break;
  			case 10:
  				newStr = "Oct";
  				break;
  			case 11:
  				newStr = "Nov";
  				break;
  			case 12:
  				newStr = "Dec";
  				break;
  		}
  		
  		newStr += " " + nums[1] + " at ";
  		
  		//change from military time
  		String sub = new String(" AM");
  		if(nums[3] == 0)
  		{
  			nums[3] = 12;
  		}
  		if(nums[3] > 12)
  		{
  			sub = " PM";
  			nums[3] -= 12;
  		}
  		
  		newStr += nums[3] + ":" + numStrs[4] + sub;
  		
  		return newStr;
  	}
  
	public static void main(String[] args) throws IOException
	{
		  launch(args);
	}
}