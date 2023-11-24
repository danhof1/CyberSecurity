package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class ThemeSelect extends Menu
{
	private Palette theme;
	private int xPos;
	private int yPos;
	private int size;
	private Button triggerBtn;
	
	
	/**
	 * Creates a theme select object
	 * @param pal associated color palette
	 * @param height and width
	 */
	public ThemeSelect(Palette pal, int s, int a, String name)
	{
		components = new ArrayList<Shape>();
		theme = pal;
		size = s;
		
		Rectangle box = Menu.icon(size, size, 0, 0, a, theme.getBgColor(), theme.getLineColor());
		components.add(box);
		
		xPos = 0;
		yPos = 0;
		
		Rectangle top = Menu.icon(size-4, (size-4)/3, 2, 2, a, theme.getPriColor(), theme.getLineColor());
		top.setStrokeWidth(0);
		components.add(top);
	
		Text title = new Text("T");
		title.setFont(theme.getTitleFont());
		title.setX(top.getX() + top.getWidth()/2 - size/5);
		title.setY(top.getY() + title.getFont().getSize() + (int)((top.getHeight() - title.getFont().getSize())*0.8 / 2));
		title.setFill(theme.getLineColor());
		components.add(title);

		Text subTitle = new Text("t");
		subTitle.setFont(theme.getSubTitleFont());
		subTitle.setX(title.getX() + size/5);
		subTitle.setY(top.getY() + (int)(title.getFont().getSize()*0.9) + (int)((top.getHeight() - subTitle.getFont().getSize())*0.8 / 2));
		subTitle.setFill(theme.getAcc2Color());
		components.add(subTitle);
	
		Text title2 = new Text("T");
		title2.setFont(theme.getTitle2Font());
		title2.setX(top.getX() + size/10);
		title2.setY(top.getY() + top.getHeight() + theme.getTitle2Font().getSize());
		title2.setFill(theme.getAcc3Color());
		components.add(title2);
		
		Text normal = new Text("t");
		normal.setFont(theme.getDefaultFont());
		normal.setX(top.getX() + size/10);
		normal.setY(top.getY() + top.getHeight() + theme.getTitle2Font().getSize() + theme.getDefaultFont().getSize());
		normal.setFill(theme.getLineColor());
		components.add(normal);

		Text subText = new Text("t");
		subText.setFont(theme.getSubTextFont());
		subText.setX(normal.getX() + size/10);;
		subText.setY(normal.getY());
		subText.setFill(theme.getLineColor());
		subText.setOpacity(0.5);
		components.add(subText);
		
		Rectangle secBox = icon(size/3, size/3, (int)(box.getX() + (size/2.5)-1), (int)(box.getY() + (size/2.5)), 10, theme.getSecColor(), null);
		components.add(secBox);
		
		Circle red = new Circle();
		red.setRadius(2*size/24);
		red.setCenterX(box.getX() + 8*size/10);
		red.setCenterY(top.getY() + size/6);
		red.setFill(theme.getRed());
		components.add(red);

		//Accent color boxes
		for(int i = 0; i < 4; i++)
		{
			Color acc = theme.getAcc1Color();
			switch(i)
			{
				case 1:
					acc = theme.getAcc2Color();
					break;
					
				case 2:
					acc = theme.getAcc4Color();
					break;
					
				case 3:
					acc = theme.getAcc3Color();
					break;
			}
			
			//									w					h						x										y
			Rectangle rect = Menu.icon((int)((size)*(2.0/12)), (int)((size)*(2.0/12)), (int)(box.getX() + (size-2) - (size-4)*(2.0/12)), (int)(top.getY() + ((size-4)/3) + ((size-4) * 2.0/12 * i)), 0, acc, null);
			components.add(rect);
		}
		
		if(theme.isLightTheme())
			name = name + " \u2600";
		else
			name = name + " \u263E";
		
		Text label = new Text(name);
		
		label.setFont(theme.getSubTitleFont());
		label.setFill(theme.getPriColor());
		label.setStroke(theme.getLineColor());
		label.setStrokeWidth(1);
		label.setStrokeType(StrokeType.OUTSIDE);
		label.setX(box.getX() + size/10);
		label.setY(box.getY() + size + size/7);
		label.setUnderline(true);
		components.add(label);
		
		buttons = new ArrayList<Control>();
		triggerBtn = Menu.makeButton(box, null, null);
		buttons.add(triggerBtn);
	}
	
	@Override
	public void setX(int x)
	{
		for(int i = 0; i < components.size(); i++)
		{
			Shape shape = components.get(i);
			shape.setTranslateX(x - shape.getLayoutX());
			
			if(i == 0)
				xPos = (int)shape.getTranslateX();
		}
				
		triggerBtn.setLayoutX(xPos);
		

	}
	
	@Override
	public double getX()
	{
		return xPos;
	}
	
	@Override
	public void setY(int y)
	{
		for(int i = 0; i < components.size(); i++)
		{
			Shape shape = components.get(i);
			shape.setTranslateY(shape.getLayoutY() + y);
			
			if(i == 0)
				yPos = (int)shape.getTranslateY();
		}
		triggerBtn.setLayoutY(yPos);
	}

	@Override
	public double getY()
	{
		return yPos;
	}
	
	public Button getButton()
	{
		return triggerBtn;
	}
	
	public Palette getPalette()
	{
		return theme;
	}
	
}
