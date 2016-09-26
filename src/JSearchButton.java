import java.util.ArrayList;
import javax.swing.JButton;

//List of things I should test before hand
public class JSearchButton extends JButton
{
	private String name, role, world;
	public JSearchButton()
	{
		name = null;
		role = null;
		world = null;
	}
	
	//Searches through the default list creating a smaller list based on search parameters that were set
	public ArrayList<Hero> list(ArrayList<Hero> defaultList)
	{
		ArrayList<Hero> visibleList = new ArrayList<Hero>(defaultList);
		
		if (name != null)
			visibleList = nameSearch(name, visibleList);
		if (role != null)
			visibleList = roleSearch(role, visibleList);
		if (world != null)
			visibleList = worldSearch(world, visibleList);
		return visibleList;
	}
	
	//Getters to acquire private fields
	public String getName ()
	{
		return name;
	}
	
	public String getRole ()
	{
		return role;
	}
	
	public String getWorld ()
	{
		return world;
	}

	//Setters to change private fields
	public void setName (String name)
	{
		this.name = name;
	}
	
	public void setRole (String role)
	{
		this.role = role;
	}
	
	public void setWorld (String world)
	{
		this.world = world;
	}

	// returns an array list of heroes with the given string in their name
	public static ArrayList<Hero> nameSearch(String string, ArrayList<Hero> hero)
	{
		ArrayList<Hero> searchList = new ArrayList<Hero>();
		for (int i = 0; i < hero.size(); i++)
		{
			if (hero.get(i).getName().toLowerCase().contains(string.toLowerCase()))
			{
				searchList.add(hero.get(i));
			}
		}
		return searchList;
	}

	//returns an array list of heroes that are of a certain role
	public static ArrayList<Hero> roleSearch(String role, ArrayList<Hero> hero)
	{
		ArrayList<Hero> searchList = new ArrayList<Hero>();
		for (int i = 0; i < hero.size(); i++)
		{
			if (hero.get(i).getRole().toLowerCase().contains(role.toLowerCase()))
			{
				searchList.add(hero.get(i));
			}
		}
		return searchList;
	}

	//returns an arraylist of heroes that are from a certain game universe
	public static ArrayList<Hero> worldSearch(String world, ArrayList<Hero> hero)
	{
		ArrayList<Hero> searchList = new ArrayList<Hero>();
		for (int i = 0; i < hero.size(); i++)
		{
			if (hero.get(i).getWorld().equalsIgnoreCase(world))
			{
				searchList.add(hero.get(i));
			}
		}
		return searchList;
	}

		
}
