import java.util.ArrayList;

//A compilation of all sorting methods that will be used for the HoTs Database
public class Sort
{
	//Will return a Hero ArrayList in alphabetical order
	public static ArrayList<Hero> alphabetSort(ArrayList<Hero> list)
	{

		if (list.size() >= 2)
		{
			// Splits the list
			ArrayList<Hero> firstHalf = new ArrayList<Hero>();
			ArrayList<Hero> secondHalf = new ArrayList<Hero>();

			for (int i = 0; i < list.size() / 2; i++)
			{
				firstHalf.add(list.get(i));
			}

			for (int i = 0; i < (list.size() - list.size() / 2); i++)
			{
				secondHalf.add (list.get(firstHalf.size() + i));
			}

			// Continue splitting into smaller lists
			firstHalf = alphabetSort(firstHalf);
			secondHalf = alphabetSort(secondHalf);

			// Merge the two split lists
			list = merge(firstHalf, secondHalf);

			return list;
		}
		else
			// returns a list if it only has 1 element
			return list;
	}

	//Merges two already alphabetically sorted hero arraylists into one alphabetically organized arraylist
	public static ArrayList<Hero> merge(ArrayList<Hero> a, ArrayList<Hero> b)
	{
		int i = 0, j = 0;
		ArrayList <Hero> c = new ArrayList <Hero>();
		while (i < a.size() && j < b.size())
			//System.out.println(a.get(i).getName() + " " + b.get(j).getName());
			if (nameCompare(a.get(i).getName().toUpperCase(), b.get(j).getName().toUpperCase()))
				c.add(a.get(i++));
			else
				c.add(b.get(j++));

		while (i < a.size())
			/* pick up any remainder */
			c.add(a.get(i++));
		while (j < b.size())
			c.add(b.get(j++));
		return c;
	}
	
	//checks if String a comes before String b alphabetically
	public static boolean nameCompare(String a, String b)
	{
		if (a.length() == 0)
		{
			return true;
		}
		else if (b.length() == 0)
		{
			return false;
		}
		else if (a.charAt(0) == b.charAt(0))
		{
			return nameCompare(a.substring(1, a.length()), b.substring(1, b.length()));
		}
		else if (a.charAt(0) < b.charAt(0))
		{
			return true;
		}
		else
			return false;
	}
}