package application.eFXtend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.FillTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Palette 
{
	private Color bgColor;
	private Color priColor;
	private Color secColor;
	private Color acc1Color;
	private Color acc2Color;
	private Color acc3Color;
	private Color acc4Color;
	private Color lineColor;
	private Color red;
	
	private Boolean lightTheme;
	private ArrayList<Color> colors;
	
	private Font title;
	private Font title2;
	private Font subTitle;
	
	private Font normal;
	private Font subText;
	
	private ArrayList<Font> fonts;
	private String name;
	
	/**
	 * Blank palette, all colors instantiated to white
	 */
	public Palette()
	{
		ArrayList<Font> fonts = new ArrayList<Font>();
		colors = new ArrayList();
		bgColor = (Color.WHITE);
		colors.add(bgColor);
		priColor = (Color.WHITE);
		colors.add(priColor);
		secColor = (Color.WHITE);
		colors.add(secColor);
		acc1Color = (Color.WHITE);
		colors.add(acc1Color);
		acc2Color = (Color.WHITE);
		colors.add(acc2Color);
		acc3Color = (Color.WHITE);
		colors.add(acc3Color);
		acc4Color = (Color.WHITE);
		colors.add(acc4Color);
		red = (Color.WHITE);
		colors.add(red);
		lightTheme = true;
		
		title = (new Text()).getFont();
		title2 = (new Text()).getFont();
		subTitle = (new Text()).getFont();
		normal = (new Text()).getFont();
		subText = (new Text()).getFont();
		
		fonts.add(title);
		fonts.add(title2);
		fonts.add(subTitle);
		fonts.add(normal);
		fonts.add(subText);
	}
	
	public Palette(Color background, Color primary, Color secondary, Color accents1, Color accents2, Color accents3, Color accents4, Color outline, Boolean light)
	{
		ArrayList<Font> fonts = new ArrayList<Font>();
		colors = new ArrayList<Color>();
		bgColor = background;
		colors.add(bgColor);
		priColor = primary;
		colors.add(priColor);
		secColor = secondary;
		colors.add(secColor);
		acc1Color = accents1;
		colors.add(acc1Color);
		acc2Color = accents2;
		colors.add(acc2Color);
		acc3Color = accents3;
		colors.add(acc3Color);
		acc4Color = accents4;
		colors.add(acc4Color);
		lineColor = outline;
		colors.add(lineColor);
		lightTheme = light;

		if(lightTheme)
		{
			red = Color.web("c3145a");
		}
		else
		{
			red = Color.web("850035");

		}
		colors.add(red);
		
		
		
		//green
		
		title = (new Text()).getFont();
		title2 = (new Text()).getFont();
		subTitle = (new Text()).getFont();
		normal = (new Text()).getFont();
		subText = (new Text()).getFont();
		
		fonts.add(title);
		fonts.add(title2);
		fonts.add(subTitle);
		fonts.add(normal);
		fonts.add(subText);
	}
	/**
	 * Sets the fonts for the theme
	 * @param name1 font family of titles
	 * @param size1 size of title font
	 * @param name2 default text font
	 * @param size2 size of default text
	 */
	public void setFonts(String name1, double size1, String name2, double size2)
	{
		if(fonts == null)
			fonts = new ArrayList<Font>();
		
		//Titles
		title = Font.font(name1, size1);
		title2 = Font.font(name1, (int)(size1*1.5));
		subTitle = Font.font(name1, (int)(size1 * (3.0 / 4)));
		
		fonts.add(title);
		fonts.add(title2);
		fonts.add(subTitle);
		
		//Normal
		normal = Font.font(name2, size2);
		subText = Font.font(name2, (int)(size2*0.75));
		fonts.add(normal);
		fonts.add(subText);
	}
	
	public Font getTitleFont()
	{
		return title;
	}
	
	public Font getTitle2Font()
	{
		return title2;
	}
	
	public Font getSubTitleFont()
	{
		return subTitle;
	}
	
	public Font getDefaultFont()
	{
		return normal;
	}
	
	public Font getSubTextFont()
	{
		return subText;
	}
	
	public Color getBgColor()
	{
		return bgColor;
	}
	
	public void setBgColor(Color background)
	{
		bgColor = background;
	}
	
	public Color getPriColor()
	{
		return priColor;
	}
	
	public void setPriColor(Color primary)
	{
		priColor = primary;
	}
	
	public Color getSecColor()
	{
		return secColor;
	}
	
	public void setSecColor(Color secondary)
	{
		secColor = secondary;
	}
	
	public Color getAcc1Color()
	{
		return acc1Color;
	}
	
	public void setAcc1Color(Color accents1)
	{
		acc1Color = accents1;
	}
	
	public Color getAcc2Color()
	{
		return acc2Color;
	}
	
	public void setAcc2Color(Color accents2)
	{
		acc1Color = accents2;
	}
	
	public Color getAcc3Color()
	{
		return acc3Color;
	}
	
	public void setAcc3Color(Color accents3)
	{
		acc3Color = accents3;
	}

	public Color getAcc4Color()
	{
		return acc4Color;
	}
	
	public void setAcc4Color(Color accents4)
	{
		acc3Color = accents4;
	}
	
	public Color getLineColor()
	{
		return lineColor;
	}
	
	public void setLineColor(Color outline)
	{
		lineColor = outline;
	}
	
	public ArrayList<Color> toArray()
	{
		return colors;
	}
	
	
	public ArrayList<Font> getFonts()
	{
		return fonts;
	}
	
	public void toggleLightTheme()
	{
		lightTheme = !lightTheme;
	}
	
	public Boolean isLightTheme()
	{
		return lightTheme;
	}
	
	public void setRed(Color r)
	{
		red = r;
	}
	
	public Color getRed()
	{
		return red;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		//Name
		String str = new String();
		if(name != null)
			str = str + name;
		else
			str = "null";
		
		//Color codes
		for(int i = 0; i < colors.size(); i++)
		{
			str = str + " " + colors.get(i).toString().substring(2, 8);
		}
		
		//Light theme boolean
		str = str + " " + lightTheme.toString();

		//Fonts
		for(int i = 0; i < fonts.size(); i++)
		{
			str = str + " " + fonts.get(i).toString();
		}
		
		return str;
	}
	
	/**
	 * Changes the image on a rectangle
	 * @param rectangle containing image to be changed
	 * @param imgPath path to new image
	 */
	public void changeImg(Rectangle rect, String imgPath, boolean adjustToTheme)
	{
		ImageInput imgIn = (ImageInput)rect.getEffect();
		Image oldImg = imgIn.getSource();
		
		if(adjustToTheme && !lightTheme)
		{
			imgPath = imgPath.replace(".PNG", "Dark.PNG");
		}
		
		Image newImg = new Image(imgPath, oldImg.getWidth(), oldImg.getHeight(), false, false);
		imgIn.setSource(newImg);
	}
	
	
	public void setPalette(Palette newPalette)
	{
		colors = new ArrayList<Color>();
		
		bgColor = newPalette.getBgColor();
		colors.add(bgColor);
		priColor = newPalette.getPriColor();
		colors.add(priColor);
		secColor = newPalette.getSecColor();
		colors.add(secColor);
		acc1Color = newPalette.getAcc1Color();
		colors.add(acc1Color);
		acc2Color = newPalette.getAcc2Color();
		colors.add(acc2Color);
		acc3Color = newPalette.getAcc3Color();
		colors.add(acc3Color);
		acc4Color = newPalette.getAcc4Color();
		colors.add(acc4Color);
		lineColor = newPalette.getLineColor();
		colors.add(lineColor);
		red = newPalette.getRed();
		colors.add(red);
		lightTheme = newPalette.isLightTheme();
		
		fonts = new ArrayList<Font>();
		
		title = newPalette.getTitleFont();
		title2 = newPalette.getTitle2Font();
		subTitle = newPalette.getSubTitleFont();
		normal = newPalette.getDefaultFont();
		subText = newPalette.getSubTextFont();
		
		fonts.add(title);
		fonts.add(title2);
		fonts.add(subTitle);
		fonts.add(normal);
		fonts.add(subText);
	}
	
	/**
	 * Changes the color palette of the app
	 * @param current color palette
	 * @param new color palette
	 * @param list of all scenes
	 * @param list of all button flash animations
	 * @param list of shapes to exclude from theme change
	 */
	public void changePalette(Palette newPalette, ArrayList<Scene> scenes, ArrayList<FillTransition> buttonFlashes, ArrayList<Shape> exclude)
	{		
		if(newPalette == this)
			return;
		
		ArrayList<Color> newColors = newPalette.toArray();

		for(int k = 0; k < scenes.size(); k++) //loops through scenes
		{
			Scene scene = scenes.get(k);
			Group root = (Group)scene.getRoot();
			List list = root.getChildren();
			int N = list.size();
			
			if(scene.getFill().equals(bgColor)) //scene has default color
				scene.setFill(newPalette.getBgColor());
			
			else //scene has a different color
			{
				Color sceneColor = (Color)scene.getFill();
				int index = colors.indexOf(sceneColor);
				
				if(index != -1)
					scene.setFill(newColors.get(index));
				//else
					//System.out.println("ERR: " + scene.toString() + " scene fill color not found");
			}
			
			for(int i = 0; i < N; i++) //loops through objects in scene
			{
				javafx.scene.Node obj = (javafx.scene.Node)(root.getChildren().get(i));
				if(exclude.contains(obj))
					continue;
				
				//if object is a shape				
				if(Shape.class.isAssignableFrom(obj.getClass()))
				{	
					Shape shape = (Shape)obj;
					Color fill = (Color)shape.getFill();
					Color stroke = (Color)shape.getStroke();
						
					//find & set fill color
					int index;
					if(shape.getFill() != null)
					{
						index = colors.indexOf(fill);
						
						if(index != -1)
						{
							shape.setFill(newColors.get(index));
						}
					}
					
					if(shape.getStroke() != null)
					{
					//find & set stroke color
						index = colors.indexOf(stroke);
						if(index != -1)
						{
							shape.setStroke(newColors.get(index));
						}
					}
					
					if(shape.getEffect() != null)
					{
						if(this.isLightTheme() != newPalette.isLightTheme())
						{
							String path = ((ImageInput)(shape.getEffect())).getSource().getUrl();
							if(newPalette.isLightTheme())
							{	
								path = path.replace("Dark", "");
							}
							
							else //to dark theme
							{
								path = path.replace(".PNG", "Dark.PNG");
							}
							changeImg((Rectangle)shape, path, false);
						}
					}
					
					//adjusts fonts if not null
					if(Text.class.isAssignableFrom(shape.getClass()) && fonts != null && newPalette.getFonts() != null)
					{
						Text txt = (Text)shape;
						index = fonts.indexOf(txt.getFont());
												
						if(index != -1)
						{
							txt.setFont(newPalette.getFonts().get(index));
						}
					}
					
				}//end shape
				else if((Control.class.isAssignableFrom(obj.getClass())))
				{
					Control control = (Control)obj;
					Color fill = Color.WHITE;
					Color stroke = Color.WHITE;
					
					if(control.getBackground() != null)
					{
						fill = (Color)control.getBackground().getFills().get(0).getFill();
					
						//find & set fill color
						int index;
						CornerRadii arc = new CornerRadii(5);

						
						index = colors.indexOf(fill);
						
						if(index != -1)
						{
							Background bgFill = new Background( new BackgroundFill(newColors.get(index), arc, null) );
							control.setBackground(bgFill);	
						}
												
						if(control.getBorder() != null)
						{
							//find & set stroke color
							stroke = (Color)control.getBorder().getStrokes().get(0).getTopStroke();	
							index = colors.indexOf(stroke);
							if(index != -1)
							{
								Border border = new Border(new BorderStroke(newColors.get(index), BorderStrokeStyle.SOLID, arc, new BorderWidths(2)));
								control.setBorder(border);
							}
						}
						//change text field text color
						if((TextField.class.isAssignableFrom(control.getClass())) && fonts != null && newPalette.getFonts() != null)
						{
							TextField tf = (TextField)control;
							index = fonts.indexOf(tf.getFont());
							if(index != -1)
							{
								tf.setFont(newPalette.getFonts().get(index));
							}
							
							String style = tf.getStyle(); //cs script
							
							//finds valid hex code
							int start = style.indexOf('#');
							int end = start + 7;
							if(start != -1)
							{
								Color oldFill = Color.web(style.substring(start, end));
								index = colors.indexOf(oldFill);
								
								String newStyle = new String();
								if(index != -1)
								{
									newStyle = style.substring(0, start);
									newStyle = newStyle + "#" + newColors.get(index).toString().substring(2, 8);
									newStyle = newStyle + style.substring(end);
								}
								tf.setStyle(newStyle);
							}
						}
					}
				}
			}
		}
		if(buttonFlashes != null)
		{
			for(int i = 0; i < buttonFlashes.size(); i++)
			{
				FillTransition fill = buttonFlashes.get(i);
				for(int j = 0; j < this.toArray().size(); j++)
				{
	
					Color c = this.toArray().get(j);
					if(fill.getToValue().equals(c))
					{
						fill.setToValue(newPalette.toArray().get(j));
					}
				}
			}
		}
		this.setPalette(newPalette);
	}
	
	public void addToConf() throws IOException
	{
		File conf = new File("config/palette.conf"); //Palette config file
		if(!conf.exists())
		{
			conf.createNewFile();
		}
		
		FileWriter fw = new FileWriter(conf, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		System.out.println(toString());
				
		bw.append(toString());
		bw.newLine();
		
		bw.close();
		
	}
	
	public ArrayList<Palette> parse() throws IOException
	{			
		File conf = new File("config/palette.conf"); //Palette config file
		if(!conf.exists())
		{
			conf.createNewFile();
		}
		
		ArrayList<Palette> list = new ArrayList<Palette>(); //Custom themes to return
		
		BufferedReader reader = new BufferedReader(new FileReader(conf)); //File reader

		//File writer
		FileWriter fw = new FileWriter(conf, true);
		BufferedWriter bw = new BufferedWriter(fw);
		LineNumberReader lnr = new LineNumberReader(new FileReader(conf));
		
		Palette pal; //new palette
		
		String line = reader.readLine();		
		
		bw.write("test");
		
		while (line != null) 
		{
			line = line.strip();
			//Not blank or a comment
			if(!line.isBlank() && line.charAt(0) != '#' )
			{
				//System.out.println(line);
			}
			line = reader.readLine();
		}
		
			bw.close();
			lnr.close();
			reader.close();
			
			return list;
	}
	
	public void setTitleWeight(FontWeight weight)
	{
		String name = title.getFamily();
		
		title = Font.font(name, weight, title.getSize());
		title2 = Font.font(name, weight, title2.getSize());
		subTitle = Font.font(name, weight, subTitle.getSize());
		
		fonts.set(0, title);
		fonts.set(1, title2);
		fonts.set(2, subTitle);
	}
	
	public void setNormalWeight(FontWeight weight)
	{
		String name = normal.getFamily();
		
		normal = Font.font(name, weight, normal.getSize());
		subText = Font.font(name, weight, subText.getSize());
		
		fonts.set(3, normal);
		fonts.set(4, subText);
	}
	
}
