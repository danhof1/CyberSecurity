package Frontend.eFXtend;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PageMenu extends Menu
{
	ArrayList<Menu> pages;
	
	Text prevBtn;
	Text nextBtn;
	Text txt;
	
	int xPos;
	int yPos;
	int height;
	int width;
	
	int curPage;
	
	Group gRoot;
	
	
	public PageMenu(Menu firstPage, int w, int h, int x, int y)
	{
		super();
		curPage = 0;
		
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		
		pages = new ArrayList<Menu>();
		pages.add(firstPage);
	}
	
	public void addPage(Menu menu)
	{
		pages.add(menu);
		
		txt.setVisible(true);
		nextBtn.setVisible(true);
		//prevBtn.setVisible(true);
		
		menu.addToGroup(gRoot);
		
		if(pages.size() > 1)
			menu.setVisible(false);
		
		txt.setText(curPage+1 + " / " + pages.size());
	}
	
	public Menu getPage(int index)
	{
		return pages.get(index-1);
	}
	
	public ArrayList<Menu> getPages()
	{
		return pages;
	}
	
	public int getPageCount()
	{
		return pages.size();
	}
	
	public int getCurrentPage()
	{
		return curPage+1;
	}
	
	public void setButtons(Font font, Color c1, Color c2)
	{
		txt = new Text(curPage+1 + " / " + pages.size());
		components.add(txt);
		txt.setFont(font);
		txt.setFill(c1);
		Menu.centerText(width, xPos, yPos + height - (int)font.getSize(), txt);
		
		prevBtn = new Text("< prev");
		prevBtn.setFont(font);
		components.add(prevBtn);
		prevBtn.setFill(c2);
		Menu.centerText(width/8, xPos + width/8, yPos + height - (int)font.getSize(), prevBtn);

		
		nextBtn = new Text("next >");
		nextBtn.setFont(font);
		components.add(nextBtn);
		nextBtn.setFill(c2);
		Menu.centerText(width/8, xPos + 7*width/8, yPos + height - (int)font.getSize(), nextBtn);
		
		
		prevBtn.setVisible(false);
		txt.setVisible(false);
		nextBtn.setVisible(false);
	}
	
	@Override
	public void addToGroup(Group root)
	{
		super.addToGroup(root);
		gRoot = root;
		
		nextBtn.setOnMouseClicked((event) ->
			{
				//hides old page
				pages.get(curPage).setVisible(false);
				curPage++;
				
				//shows new page
				pages.get(curPage).setVisible(true);
				prevBtn.setVisible(true);
				
				//hide button if at end
				if(curPage == pages.size() - 1)
				{
					nextBtn.setVisible(false);
				}
				
				//update text
				txt.setText(curPage+1 + " / " + pages.size());

			});
		
		prevBtn.setOnMouseClicked((event) ->
		{
			//hides old page
			pages.get(curPage).setVisible(false);
			curPage--;
			
			//shows new page
			pages.get(curPage).setVisible(true);
			nextBtn.setVisible(true);
			
			//hide button if at end
			if(curPage == 0)
			{
				prevBtn.setVisible(false);
			}
			
			//update text
			txt.setText(curPage+1 + " / " + pages.size());

		});
	}
	
	public void reset()
	{
		if(curPage == 0)
			return;
		
		pages.get(curPage).setVisible(false);
		curPage = 0;
		pages.get(curPage).setVisible(true);
		txt.setText(curPage+1 + " / " + pages.size());

	}
}
