package application.eFXtend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UIFonts 
{	
	public static List<String> fonts = Arrays.asList(new String[]
			{
					"Adobe Caslon Pro",
					"Adobe Fangsong Std R",
					"Adobe Garamond Pro",
					"Adobe Hebrew",
					"Adobe Ming Std L",
					"Algerian",
					"Arial",
					"Bell MT",
					"Berlin Sans FB",
					"Book Antiqua",
					"Britannic Bold",
					"Broadway",
					"Calibri",
					"Calisto MT",
					"Candara Light",
					"Century",
					"Century Gothic",
					"Chaparral Pro",
					"Comic Sans MS",
					"Cooper Std Black",
					"Corbel",
					"Courier New",
					"Elephant",
					"Eras Light ITC",
					"Footlight MT Light",
					"Georgia",
					"Goudy Old Style",
					"Harrington",
					"High Tower Text",
					"Hobo STD",
					"Impact",
					"Imprint MT Shadow",
					"Ink Free",
					"Javanese Text",
					"Jokerman",
					"Kristen ITC",
					"Lucida Fax",
					"Lucida Calligraphy",
					"MS Gothic",
					"MS Reference Sans Serif",
					"MV Boli",
					"Matura MT Script Capitals",
					"Minion Pro",
					"Nueva Std",
					"Old English Text MT",
					"Rockwell",
					"Rosewood STD Regular",
					"Tekton Pro",
					"Tempus Sans ITC",
					"Times New Roman",
					"Trebuchet MS",
					"Verdana",
					"Viner Hand ITC"
			});	

	private static FontWeight[] allWeights = FontWeight.values();

	
	public static ArrayList<FontWeight> getWeights(String fontName)
	{
		Font font = Font.font(fontName, FontWeight.NORMAL, 10);
		ArrayList<FontWeight> weights = new ArrayList<FontWeight>();

		if(!fonts.contains(fontName))
		{
			System.out.println(fontName + " is not a UIFont");
		}
		
		for(int i = 0; i < allWeights.length; i++)
		{
			FontWeight curWeight = allWeights[i];
			if(curWeight == FontWeight.NORMAL)
			{
				weights.add(curWeight);
				continue;
			}
			
			Font font2 = Font.font(fontName, curWeight, 10);
			
			if(!font.equals(font2))
			{
				weights.add(curWeight);
			}
		}
		return weights;	
	}
}
