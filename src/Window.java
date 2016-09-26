import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JTextPane;

/*************************************************
 * Author: Raymond Yan and Victor Jifcu
 * Date : June 04, 2015.
 * Description : A database for the Multiplayer Online Battle Arena game made by Blizzard Entertainments,
 *				 Heroes of the Storm
 *
 *THIS IS THE MAIN JAVA CLASS THAT HAS THE MAIN METHOD TO RUN EVERYTHING ELSE
 *Also remember to read the README.TXT file
 ************************************************/
public class Window implements ActionListener
{
	//initialization of all fields
	private static final Color CLEAR = new Color(0, 0, 0, 0);
	private static final Color VALUE = new Color(230, 200, 250);
	private static final Color TITLE = new Color(190, 175, 215);
	private static final Color PURPLE = new Color(160, 100, 160);
	private static final Font DEFAULTFONT = new Font("Arial", Font.PLAIN, 10);
	private static final Font BOLDFONT = new Font("Arial", Font.BOLD, 12);
	private JRoundedScrollPane scrollPane;
	private JFrame frame;
	private JInfoButton[] heroButton;
	private ArrayList<Hero> defaultList;
	private ArrayList<Hero> visibleList;
	private JRoundedPane base;
	private JTextPane searchBar;
	private JRoundedPane scrollHolder;
	private boolean editable;
	private JSearchButton search;
	private JTextPane heroName, heroTitle, heroRole, heroWorld, health, healthRegeneration, mana, manaRegeneration, atkSpeed, damage, cost,
			healthGrowth, hRG, manaGrowth, mRG, damageGrowth, heroDesc;

	private JImagePane logo, heroGame, heroImage;
	private JButton deleteButton, addButton, btnEdit, close, min;
	private JButton[] worldButton, roleButton;

	/****************************
	 *  THE MAIN METHOD IS HERE
	 ****************************/
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Window window = new Window();

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**************************
	 *  Creates the main frame
	 **************************/
	public Window()
	{
		initialize();
		frame.setSize(714, 495);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/****************************************************
	 *  Initializes all the components in the application
	 ***************************************************/
	private void initialize()
	{
		defaultList = new ArrayList<Hero>();
		defaultList = getHeroes();
		visibleList = new ArrayList<Hero>(defaultList);

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(250, 120, 200));
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		min = new JButton("", new ImageIcon("Misc/min.png"));
		min.setBounds(615, 1, 46, 42);
		min.setBorderPainted(false);
		min.setBackground(null);
		min.addActionListener(this);
		frame.getContentPane().add(min);
		
		close = new JButton("", new ImageIcon("Misc/close.png"));
		close.setBounds(661, 1, 43, 42);
		close.setBorderPainted(false);
		close.setBackground(null);
		close.addActionListener(this);
		frame.getContentPane().add(close);

		logo = new JImagePane("hots.png");
		logo.setBounds(10, 0, 140, 64);
		logo.setBackground(CLEAR);
		frame.getContentPane().add(logo);

		searchBar = new JTextPane();
		searchBar.setBounds(173, 12, 173, 20);
		frame.getContentPane().add(searchBar);
		
		base = new JRoundedPane(CLEAR);
		base.setBounds(5, 70, 160, 353);
		frame.getContentPane().add(base);
		base.setLayout(null);

		JRoundedPane heroData = new JRoundedPane(PURPLE);
		heroData.setBounds(167, 44, 537, 379);
		frame.getContentPane().add(heroData);

		/********************************
		 *  Constant Textboxes
		 *********************************/
		JRoundedPane info = new JRoundedPane(PURPLE);
		info.setBounds(176, 101, 351, 160);
		info.setForeground(Color.WHITE);
		info.setBackground(PURPLE);
		info.setBorder(BorderFactory.createBevelBorder(1));
		heroData.add(info);
		info.setLayout(null);

		final JTextPane HEALTHTXT = new JTextPane();
		HEALTHTXT.setBounds(10, 36, 45, 20);
		info.add(HEALTHTXT);
		HEALTHTXT.setEditable(editable);
		HEALTHTXT.setBackground(TITLE);
		HEALTHTXT.setText("Health");

		final JTextPane HEALTHREGENERATIONTEXT = new JTextPane();
		HEALTHREGENERATIONTEXT.setBounds(10, 67, 45, 20);
		info.add(HEALTHREGENERATIONTEXT);
		HEALTHREGENERATIONTEXT.setEditable(editable);
		HEALTHREGENERATIONTEXT.setText("Regen");
		HEALTHREGENERATIONTEXT.setBackground(TITLE);

		final JTextPane MANATEXT = new JTextPane();
		MANATEXT.setBounds(10, 98, 45, 20);
		info.add(MANATEXT);
		MANATEXT.setText("Mana");
		MANATEXT.setEditable(editable);
		MANATEXT.setBackground(TITLE);

		final JTextPane MANAREGENERATIONTEXT = new JTextPane();
		MANAREGENERATIONTEXT.setBounds(10, 129, 45, 20);
		info.add(MANAREGENERATIONTEXT);
		MANAREGENERATIONTEXT.setText("Regen");
		MANAREGENERATIONTEXT.setEditable(editable);
		MANAREGENERATIONTEXT.setBackground(TITLE);

		final JTextPane DAMAGETEXT = new JTextPane();
		DAMAGETEXT.setBounds(173, 36, 54, 20);
		info.add(DAMAGETEXT);
		DAMAGETEXT.setText("Damage\r\n");
		DAMAGETEXT.setEditable(editable);
		DAMAGETEXT.setBackground(TITLE);

		final JTextPane ATKSPEEDTEXT = new JTextPane();
		ATKSPEEDTEXT.setBounds(173, 98, 77, 20);
		info.add(ATKSPEEDTEXT);
		ATKSPEEDTEXT.setText("Attack Speed");
		ATKSPEEDTEXT.setEditable(editable);
		ATKSPEEDTEXT.setBackground(TITLE);

		final JTextPane HEROCOSTTEXT = new JTextPane();
		HEROCOSTTEXT.setBounds(173, 129, 77, 20);
		info.add(HEROCOSTTEXT);
		HEROCOSTTEXT.setText("Hero Cost");
		HEROCOSTTEXT.setEditable(editable);
		HEROCOSTTEXT.setBackground(TITLE);

		final JTextPane VALUETEXT1 = new JTextPane();
		VALUETEXT1.setEditable(editable);
		VALUETEXT1.setText("Value");
		VALUETEXT1.setBounds(68, 11, 35, 20);
		VALUETEXT1.setBackground(TITLE);
		info.add(VALUETEXT1);

		final JTextPane GROWTHTEXT1 = new JTextPane();
		GROWTHTEXT1.setEditable(editable);
		GROWTHTEXT1.setText("Growth");
		GROWTHTEXT1.setBounds(124, 11, 45, 20);
		GROWTHTEXT1.setBackground(TITLE);
		info.add(GROWTHTEXT1);

		final JTextPane VALUETEXT2 = new JTextPane();
		VALUETEXT2.setEditable(false);
		VALUETEXT2.setText("Value");
		VALUETEXT2.setBounds(250, 11, 35, 20);
		VALUETEXT2.setBackground(TITLE);
		info.add(VALUETEXT2);

		final JTextPane GROWTHTEXT2 = new JTextPane();
		GROWTHTEXT2.setEditable(editable);
		GROWTHTEXT2.setText("Growth");
		GROWTHTEXT2.setBounds(295, 11, 45, 20);
		GROWTHTEXT2.setBackground(TITLE);
		info.add(GROWTHTEXT2);

		/********************************
		 *  Hero specific data text boxes
		 *********************************/
		editable = false;
		heroName = new JTextPane();
		heroName.setBounds(176, 11, 258, 38);
		heroName.setFont(new Font("Arial", Font.PLAIN, 33));
		heroName.setForeground(new Color(80, 140, 255));
		heroName.setBackground(PURPLE);
		heroName.setText(visibleList.get(0).getName());
		heroName.setBorder(BorderFactory.createBevelBorder(0));
		heroName.setEditable(editable);
		heroData.add(heroName);

		heroTitle = new JTextPane();
		heroTitle.setBounds(176, 51, 258, 28);
		heroTitle.setFont(new Font("Arial", Font.BOLD, 18));
		heroTitle.setBackground(PURPLE);
		heroTitle.setText(visibleList.get(0).getTitle());
		heroTitle.setBorder(BorderFactory.createBevelBorder(0));
		heroTitle.setEditable(editable);
		heroData.add(heroTitle);

		heroRole = new JTextPane();
		heroRole.setFont(new Font("Arial", Font.ITALIC, 16));
		heroRole.setBounds(176, 80, 258, 20);
		heroRole.setBackground(PURPLE);
		heroRole.setText(visibleList.get(0).getRole());
		heroRole.setBorder(BorderFactory.createBevelBorder(0));
		heroRole.setEditable(editable);
		heroData.add(heroRole);

		heroGame = new JImagePane("Misc/" + visibleList.get(0).getWorld() + ".png");
		heroGame.setBounds(437, -3, 90, 90);
		heroGame.setBackground(CLEAR);
		heroData.add(heroGame);
		
		heroWorld = new JTextPane();
		heroWorld.setFont(new Font("Arial", Font.BOLD, 11));
		heroWorld.setBackground(PURPLE);
		heroWorld.setText(visibleList.get(0).getWorld());
		heroWorld.setBounds(454, 84, 55, 16);
		heroWorld.setBorder(BorderFactory.createBevelBorder(0));
		heroWorld.setEditable(editable);
		heroData.add(heroWorld);
		
		health = new JTextPane();
		health.setBounds(68, 36, 45, 20);
		info.add(health);
		health.setText(visibleList.get(0).getHealth() + "");
		health.setEditable(editable);
		health.setBackground(VALUE);

		healthRegeneration = new JTextPane();
		healthRegeneration.setBounds(68, 67, 45, 20);
		info.add(healthRegeneration);
		healthRegeneration.setText(visibleList.get(0).getHealthRegen() + "");
		healthRegeneration.setEditable(editable);
		healthRegeneration.setBackground(VALUE);

		mana = new JTextPane();
		mana.setBounds(68, 98, 45, 20);
		info.add(mana);
		mana.setText(visibleList.get(0).getMana() + "");
		mana.setEditable(editable);
		mana.setBackground(VALUE);

		manaRegeneration = new JTextPane();
		manaRegeneration.setBounds(67, 129, 46, 20);
		info.add(manaRegeneration);
		manaRegeneration.setText(visibleList.get(0).getManaRegen() + "");
		manaRegeneration.setEditable(editable);
		manaRegeneration.setBackground(VALUE);

		atkSpeed = new JTextPane();
		atkSpeed.setBounds(263, 98, 77, 20);
		info.add(atkSpeed);
		atkSpeed.setText(visibleList.get(0).getAtkSpeed() + "");
		atkSpeed.setEditable(editable);
		atkSpeed.setBackground(VALUE);

		damage = new JTextPane();
		damage.setBounds(250, 36, 45, 20);
		info.add(damage);
		damage.setText(visibleList.get(0).getDamage() + "");
		damage.setEditable(editable);
		damage.setBackground(VALUE);

		cost = new JTextPane();
		cost.setBounds(263, 129, 77, 20);
		info.add(cost);
		cost.setText("1");
		cost.setEditable(editable);
		cost.setBackground(VALUE);

		healthGrowth = new JTextPane();
		healthGrowth.setText(visibleList.get(0).getHealthGrowth() + "");
		healthGrowth.setEditable(editable);
		healthGrowth.setBackground(VALUE);
		healthGrowth.setBounds(124, 36, 35, 20);
		info.add(healthGrowth);

		hRG = new JTextPane();
		hRG.setText(visibleList.get(0).getHRG() + "");
		hRG.setEditable(editable);
		hRG.setBackground(VALUE);
		hRG.setBounds(124, 67, 35, 20);
		info.add(hRG);

		manaGrowth = new JTextPane();
		manaGrowth.setText(visibleList.get(0).getManaGrowth() + "");
		manaGrowth.setEditable(editable);
		manaGrowth.setBackground(VALUE);
		manaGrowth.setBounds(124, 98, 35, 20);
		info.add(manaGrowth);

		mRG = new JTextPane();
		mRG.setText(visibleList.get(0).getMRG() + "");
		mRG.setEditable(editable);
		mRG.setBackground(VALUE);
		mRG.setBounds(124, 129, 35, 20);
		info.add(mRG);

		damageGrowth = new JTextPane();
		damageGrowth.setText(visibleList.get(0).getDamageGrowth() + "");
		damageGrowth.setEditable(editable);
		damageGrowth.setBackground(VALUE);
		damageGrowth.setBounds(305, 36, 35, 20);
		info.add(damageGrowth);

		heroDesc = new JTextPane();
		heroDesc.setBounds(10, 273, 517, 97);
		heroDesc.setFont(new Font("Arial", Font.PLAIN, 18));
		heroDesc.setForeground(Color.WHITE);
		heroDesc.setBackground(PURPLE);
		heroDesc.setText(visibleList.get(0).getDesc());
		heroDesc.setEditable(editable);
		heroDesc.setBorder(BorderFactory.createBevelBorder(1));
		heroData.add(heroDesc);
		
		heroImage = new JImagePane("Fulls/" + visibleList.get(0).getName() + ".png");
		heroImage.setBounds(10, 11, 156, 250);
		heroImage.setBackground(CLEAR);
		heroData.setLayout(null);
		heroData.add(heroImage);
		
		/***********
		 * BUTTONS
		 ***********/
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(599, 450, 89, 23);
		frame.getContentPane().add(deleteButton);
		deleteButton.addActionListener(this);

		addButton = new JButton("Add Hero");
		addButton.setBounds(510, 450, 89, 23);
		frame.getContentPane().add(addButton);
		addButton.addActionListener(this);
		
		btnEdit = new JButton("Edit: " + editable);
		btnEdit.setBounds(497, 13, 89, 23);
		btnEdit.addActionListener(this);
		frame.getContentPane().add(btnEdit);

		search = new JSearchButton();
		search.setBounds(356, 10, 114, 28);
		search.addActionListener(this);
		frame.getContentPane().add(search);
		search.setText("Search");
		
		worldButton = new JButton[3];
		worldButton[0] = new JButton("Starcraft");
		worldButton[1] = new JButton("Diablo");
		worldButton[2] = new JButton("Warcraft");

		for (int i = 0; i < worldButton.length; i++)
		{
			worldButton[i].setFont(DEFAULTFONT);
			worldButton[i].setBounds(5 + 95 * i, 433, 90, 23);
			worldButton[i].addActionListener(this);
			frame.getContentPane().add(worldButton[i]);
		}

		roleButton = new JButton[4];
		roleButton[0] = new JButton("Warrior");
		roleButton[1] = new JButton("Assassin");
		roleButton[2] = new JButton("Support");
		roleButton[3] = new JButton("Specialist");
		for (int i = 0; i < roleButton.length; i++)
		{
			roleButton[i].setFont(DEFAULTFONT);
			roleButton[i].setBounds(5 + 95 * i, 465, 90, 23);
			roleButton[i].addActionListener(this);
			frame.getContentPane().add(roleButton[i]);
		}
		
		heroButtonsUpdate();
	}

	/****************************************************
	 *  Asks the user to save changes made or not
	 ***************************************************/
	public void saveChanges()
	{
		//Creates a pop up asking if the user wants to save changes or not
		Object[] options = { "Yes", "no" };
		int n = 0;
		n = JOptionPane.showOptionDialog(null, "Do you wish to all save changes?", "Save Changes",
				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (n == 0)
		{
			//if the user clicks "yes", saves the changes and alphabet sort the default list, and then updates the HoTs.txt file
			Hero temp = new Hero(false);
			try
			{
				temp.setName(heroName.getText());
				temp.setTitle(heroTitle.getText());
				temp.setRole(heroRole.getText());
				temp.setWorld(heroWorld.getText());
				temp.setDesc(heroDesc.getText());
				temp.setAtkSpeed(Float.parseFloat(atkSpeed.getText()));
				temp.setHealth(Short.parseShort(health.getText()));
				temp.setHealthGrowth(Short.parseShort(healthGrowth.getText()));
				temp.setMana(Short.parseShort(mana.getText()));
				temp.setManaGrowth(Short.parseShort(manaGrowth.getText()));
				temp.setDamage(Short.parseShort(damage.getText()));
				temp.setCost((short) (Short.parseShort(cost.getText()) / 1000));
				temp.setDamageGrowth(Short.parseShort(damageGrowth.getText()));
				temp.setHealthRegen(Float.parseFloat(healthRegeneration.getText()));
				temp.setManaRegen(Float.parseFloat(manaRegeneration.getText()));
				temp.setHRG(Float.parseFloat(hRG.getText()));
				temp.setMRG(Float.parseFloat(mRG.getText()));
				defaultList.set(JInfoButton.getActive(), temp);
				defaultList = Sort.alphabetSort(defaultList);
				Hero.resetHeroNums(defaultList);
				heroButtonsUpdate();
				Hero.writeFile(defaultList);
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "A field was wrong, don't put a string to a numerical value", "What a dummy",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
		//Updates the Heroes data on the screen
		updateData();
	}

	//Updates the Buttons on the side based off search parameters
	public void heroButtonsUpdate()
	{
		visibleList = search.list(defaultList);
		// clears the old buttons
		base.removeAll();
		scrollHolder = new JRoundedPane(CLEAR);
		scrollHolder.setLayout(new GridLayout(0, 1, 0, 0));

		// recreates and renumbers based on the number of Heroes there are
		heroButton = new JInfoButton[visibleList.size()];
		for (int i = 0; i < visibleList.size(); i++)
		{
			heroButton[i] = new JInfoButton(visibleList.get(i).getName(), visibleList.get(i).getHeroNum());
			heroButton[i].setPreferredSize(new Dimension(160, 50));
			heroButton[i].setLayout(new GridLayout(1, 10));
			heroButton[i].setBackground(CLEAR);
			heroButton[i].addActionListener(this);
			heroButton[i].updateButton(i); // this line
			scrollHolder.add(heroButton[i]);
		}
		scrollPane = new JRoundedScrollPane(scrollHolder, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 160, 353);
		scrollPane.getVerticalScrollBar().setUnitIncrement(50);
		scrollPane.getVerticalScrollBar().setBlockIncrement(50);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBackground(new Color(100, 80, 100));
		base.add(scrollPane);
	}

	//Gets all heroes from a text document and compiles them into an ArrayList
	@SuppressWarnings("resource")
	public static ArrayList<Hero> getHeroes()
	{
		File file = new File("HOTS.txt");
		Scanner sc = null;
		try
		{
			sc = new Scanner(file);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		while (sc.hasNextLine())
		{
			Hero temp = new Hero(true);
			temp.readFile(sc.nextLine(), sc.nextLine());
			heroes.add(temp);
		}
		return heroes;
	}

	// Turns all hero specific data text boxes to on or off edit mode
	public void setEditable()
	{
		btnEdit.setText("Edit: " + editable);
		heroName.setEditable(editable);
		heroRole.setEditable(editable);
		heroTitle.setEditable(editable);
		heroWorld.setEditable(editable);

		// STATS
		health.setEditable(editable);
		healthRegeneration.setEditable(editable);
		mana.setEditable(editable);
		manaRegeneration.setEditable(editable);
		atkSpeed.setEditable(editable);
		damage.setEditable(editable);
		cost.setEditable(editable);
		healthGrowth.setEditable(editable);
		hRG.setEditable(editable);
		manaGrowth.setEditable(editable);
		mRG.setEditable(editable);
		damageGrowth.setEditable(editable);
		heroDesc.setEditable(editable);
		
		if (editable)
		{
			heroName.setBackground(Color.YELLOW);
			heroWorld.setBackground(Color.YELLOW);
			heroRole.setBackground(Color.YELLOW);
			heroTitle.setBackground(Color.YELLOW);
			heroWorld.setBackground(Color.YELLOW);
			
			health.setBackground(Color.YELLOW);
			healthRegeneration.setBackground(Color.YELLOW);
			mana.setBackground(Color.YELLOW);
			manaRegeneration.setBackground(Color.YELLOW);
			atkSpeed.setBackground(Color.YELLOW);
			damage.setBackground(Color.YELLOW);
			cost.setBackground(Color.YELLOW);
			healthGrowth.setBackground(Color.YELLOW);
			hRG.setBackground(Color.YELLOW);
			manaGrowth.setBackground(Color.YELLOW);
			mRG.setBackground(Color.YELLOW);
			damageGrowth.setBackground(Color.YELLOW);
			heroDesc.setForeground(Color.BLACK);
			heroDesc.setBackground(Color.YELLOW);
		}
		else
		{
			heroName.setBackground(PURPLE);
			heroWorld.setBackground(PURPLE);
			heroRole.setBackground(PURPLE);
			heroTitle.setBackground(PURPLE);
			heroWorld.setBackground(PURPLE);
			
			health.setBackground(VALUE);
			healthRegeneration.setBackground(VALUE);
			mana.setBackground(VALUE);
			manaRegeneration.setBackground(VALUE);
			atkSpeed.setBackground(VALUE);
			damage.setBackground(VALUE);
			cost.setBackground(VALUE);
			healthGrowth.setBackground(VALUE);
			hRG.setBackground(VALUE);
			manaGrowth.setBackground(VALUE);
			mRG.setBackground(VALUE);
			damageGrowth.setBackground(VALUE);
			heroDesc.setForeground(Color.WHITE);
			heroDesc.setBackground(PURPLE);
		}
		
	}

	// updates all the information shown for the hero
	public void updateData()
	{
		try
		{
			heroDesc.setText(visibleList.get(JInfoButton.getActive()).getDesc());
			heroName.setText(visibleList.get(JInfoButton.getActive()).getName());
			heroTitle.setText(visibleList.get(JInfoButton.getActive()).getTitle());
			heroRole.setText(visibleList.get(JInfoButton.getActive()).getRole());
			heroWorld.setText(visibleList.get(JInfoButton.getActive()).getWorld());
			health.setText(visibleList.get(JInfoButton.getActive()).getHealth() + "");
			healthRegeneration.setText(visibleList.get(JInfoButton.getActive()).getHealthRegen() + "");
			mana.setText(visibleList.get(JInfoButton.getActive()).getMana() + "");
			manaRegeneration.setText(visibleList.get(JInfoButton.getActive()).getManaRegen() + "");
			atkSpeed.setText(visibleList.get(JInfoButton.getActive()).getAtkSpeed() + "");
			damage.setText(visibleList.get(JInfoButton.getActive()).getDamage() + "");
			cost.setText(visibleList.get(JInfoButton.getActive()).getCost() * 1000 + "");
			healthGrowth.setText(visibleList.get(JInfoButton.getActive()).getHealthGrowth() + "");
			manaGrowth.setText(visibleList.get(JInfoButton.getActive()).getManaGrowth() + "");
			hRG.setText(visibleList.get(JInfoButton.getActive()).getHRG() + "");
			mRG.setText(visibleList.get(JInfoButton.getActive()).getMRG() + "");
			damageGrowth.setText(visibleList.get(JInfoButton.getActive()).getDamageGrowth() + "");

			heroImage.setImage("Fulls/" + visibleList.get(JInfoButton.getActive()).getName() + ".png");
			heroGame.setImage("Misc/" + visibleList.get(JInfoButton.getActive()).getWorld() + ".png");
		}
		catch (Exception e)
		{
		}
	}

	// A method containing all actions when user clicks on a button in the GUI
	public void actionPerformed(ActionEvent e)
	{

		for (int i = 0; i < heroButton.length; i++)
			if (e.getSource() == heroButton[i])
			{
				//turns off edit and updates data for the hero selected by the user
				if(editable)
					saveChanges();
				editable = false;
				setEditable();
				JInfoButton.setActive(i);
				for (int ii = 0; ii < heroButton.length; ii++)
					heroButton[ii].updateButton(ii);
				updateData();
			}
		for (int i = 0; i < worldButton.length; i++)
		{
			if (e.getSource() == worldButton[i])
			{
				//if world is already selected, removes it from the Search parameter 
				if (worldButton[i].getText().equals(search.getWorld()))
				{
					worldButton[i].setFont(DEFAULTFONT);
					search.setWorld(null);
				}
				else
				{
					//removes other worlds from the search parameter and add the selected to the search parameter
					for (int j = 0; j < worldButton.length; j++)
						worldButton[j].setFont(DEFAULTFONT);
					worldButton[i].setFont(BOLDFONT);
					search.setWorld(worldButton[i].getText());
				}
				//updates the hero buttons on the side with the new search parameters
				heroButtonsUpdate();
			}

		}
		for (int i = 0; i < roleButton.length; i++)
		{
			if (e.getSource() == roleButton[i])
			{
				//if role is already selected, removes it from the Search parameter 
				if (roleButton[i].getText().equals(search.getRole()))
				{
					roleButton[i].setFont(DEFAULTFONT);
					search.setRole(null);
				}
				else
				{
					//removes other roles from the search parameter and add the selected to the search parameter
					for (int j = 0; j < roleButton.length; j++)
						roleButton[j].setFont(DEFAULTFONT);
					roleButton[i].setFont(BOLDFONT);
					search.setRole(roleButton[i].getText());
				}
				//updates the hero buttons on the side with the new search parameters
				heroButtonsUpdate();
			}
		}

		if (e.getSource() == search)
		{
			//Adds the string in the searchbar to the search parameters
			search.setName(searchBar.getText());
			heroButtonsUpdate();
		}
		else if (e.getSource() == addButton)
		{
			//creates a new hero where the user can then edit
			defaultList.add(new Hero("Hero name", "Hero title", "blank", "Hero role"));
			heroButtonsUpdate();
			JInfoButton.setActive(visibleList.size() - 1);
			editable = true;
			setEditable();
			updateData();
		}
		else if (e.getSource() == deleteButton)
		{
			//deletes the hero the user is currently looking at
			defaultList.remove(JInfoButton.getActive());
			
			//if the last hero on the list was deleted
			if (JInfoButton.getActive() > defaultList.size() - 1)
			{
				JInfoButton.setActive(defaultList.size() - 1);
			}
			heroButtonsUpdate();
			updateData();
		}
		else if (e.getSource() == btnEdit)
		{
			//changes on/off edit mode
			if (editable)
			{
				editable = false;
				saveChanges();
			}
			else
			{
				editable = true;
			}
			setEditable();
		}
		else if(e.getSource() == close)
		{
			//when program closes, checks if user wants to save any changes then exit
			saveChanges();
			System.exit(0);
		}
		else if(e.getSource() == min)
			//minimize
			frame.setState(Frame.ICONIFIED);
		
		//updates the screen
		SwingUtilities.updateComponentTreeUI(frame);

	}
}
