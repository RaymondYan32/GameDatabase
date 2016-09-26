import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


//An Objct class containing all the fields and methods needed for a hero
public class Hero
{
	private static int heroCounter;
	private int heroNum;
	private String name, title, world, role, description;
	private short cost, damage, health, mana, damageGrowth, healthGrowth, manaGrowth;
	private float atkSpeed, hRegenGrowth, mRegenGrowth, healthRegen, manaRegen;

	//Creates a blank hero, if create is true it is a brand new hero that will be added to the default list otherwise it is a temporary object
	public Hero(boolean create)
	{
		if (create)
		heroNum = heroCounter++;
	}
	
	//Creates a new hero with a name, title, and world fields filled
	public Hero(String name, String title, String world, String role)
	{
		this(true);
		this.name = name;
		this.title = title;
		this.world = world;
		this.role = role;
		this.description = "";
	}
	
	//resets the hero numbers  so that order of the default list matches with the heroNums
	public static void resetHeroNums(ArrayList<Hero> defaultList)
	{
		heroCounter = 0;
		for (int i = 0; i < defaultList.size(); i++)
		{
			defaultList.get(i).heroNum = heroCounter++;
		}
	}
	
	//GETTERS to get private fields
	public int getHeroNum()
	{
		return heroNum;	
	}

	public String getName()
	{
		return name;
	}

	public String getTitle()
	{
		return title;
	}

	public String getWorld()
	{
		return world;
	}
	
	public String getRole()
	{
		return role;
	}

	public String getDesc()
	{
		return description;
	}
	
	public short getHealth()
	{
		return health;
	}
	
	public short getHealthGrowth()
	{
		return healthGrowth;
	}
	
	public short getMana()
	{
		return mana;
	}
	
	public short getManaGrowth()
	{
		return manaGrowth;
	}
	
	public short getDamage()
	{
		return damage;
	}
	
	public short getDamageGrowth()
	{
		return damageGrowth;
	}
	
	public short getCost()
	{
		return cost;
	}
	
	public float getHealthRegen()
	{
		return healthRegen;
	}
	
	public float getHRG()
	{
		return hRegenGrowth;
	}
	
	public float getManaRegen()
	{
		return manaRegen;
	}
	
	public float getMRG()
	{
		return mRegenGrowth;
	}
	
	public float getAtkSpeed()
	{
		return atkSpeed;
	}
	
	//SETTERS to set private fields
	public void setName(String name)
	{
		this.name = name;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setWorld(String world)
	{
		this.world = world;
	}
	
	public void setRole(String role)
	{
		this.role = role;
	}

	public void setDesc(String desc)
	{
		this.description = desc;
	}
	
	public void setHealth(short health)
	{
		this.health = health;
	}
	
	public void setHealthGrowth(short healthGrowth)
	{
		this.healthGrowth = healthGrowth;
	}
	
	public void setMana(short mana)
	{
		this.mana = mana;
	}
	
	public void setManaGrowth(short manaGrowth)
	{
		this.manaGrowth = manaGrowth;
	}
	
	public void setDamage(short damage)
	{
		this.damage = damage;
	}
	
	public void setDamageGrowth(short damageGrowth)
	{
		this.damageGrowth = damageGrowth;
	}
	
	public void setCost(short cost)
	{
		this.cost = cost;
	}
	
	public void setHealthRegen(float healthRegen)
	{
		this.healthRegen = healthRegen;
	}
	
	public void setHRG(float hRegenGrowth)
	{
		this.hRegenGrowth = hRegenGrowth;
	}
	
	public void setManaRegen(float manaRegen)
	{
		this.manaRegen = manaRegen;
	}
	
	public void setMRG(float mRegenGrowth)
	{
		this.mRegenGrowth = mRegenGrowth;
	}
	
	public void setAtkSpeed(float atkSpeed)
	{
		this.atkSpeed = atkSpeed;
	}
	
	//Override of the toString method, used for bug testing
	public String toString()
	{
		return name + ", " + title + ", " + role + ", " + world + ", " + cost*1000 + ", " + health + "(" + healthGrowth + "), " + 
				healthRegen + "(" + hRegenGrowth + "), " + mana + "(" + manaGrowth + "), " + manaRegen + "(" + mRegenGrowth + "), " + 
				atkSpeed + ", " + damage + "(" + damageGrowth + ") " + 
				"\n" + description;
	}
	
	//Translates all fields into a string that can be writted into a data txt file
	public String fileVersion()
	{
		return name + "~" + title + "~" + role + "~" + world + "~" + cost + "~" + health + "~" + healthGrowth + "~" + 
				healthRegen + "~" + hRegenGrowth + "~" + mana + "~" + manaGrowth + "~" + manaRegen + "~" + mRegenGrowth + "~" + 
				atkSpeed + "~" + damage + "~" + damageGrowth;
	}

	// reads a line of the file
	public void readFile(String info, String description)
	{

		name = info.substring(0, info.indexOf('~'));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		title = info.substring(0, info.indexOf('~'));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		role = info.substring(0, info.indexOf('~'));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		world = info.substring(0, info.indexOf('~'));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		cost = Short.parseShort(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		health = Short.parseShort(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		healthGrowth = Short.parseShort(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		healthRegen = Float.parseFloat(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());

		hRegenGrowth = Float.parseFloat(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		mana = Short.parseShort(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		manaGrowth = Short.parseShort(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		manaRegen = Float.parseFloat(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		mRegenGrowth = Float.parseFloat(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		atkSpeed = Float.parseFloat(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		damage = Short.parseShort(info.substring(0, info.indexOf('~')));
		info = info.substring(info.indexOf('~') + 1, info.length());
		
		damageGrowth = Short.parseShort(info);
		
		this.description = description;

	}
	
	
	//Updates the text file
	public static void writeFile(ArrayList<Hero> hero)
	{
		try
		{

			File file = new File("HOTS.txt");

			// if file doesnt exists, then create it
			if (!file.exists())
			{
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < hero.size(); i++)
			{
				bw.write(hero.get(i).fileVersion());
				bw.newLine();
				bw.write(hero.get(i).getDesc());
				bw.newLine();
			}
			bw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


}
