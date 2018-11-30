package items;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

import asciiPanel.AsciiPanel;
import items.Item.Type;
import wolrdbuilding.Palette;
import wolrdbuilding.World;

public class ItemFactory implements Serializable
{
	private World world;
	public Random r;
	
	public ItemFactory(World world)
	{
		this.world = world;
		this.r = new Random();
	}
	public ItemFactory()
	{
		this.world = null;
	}

	public Item newReflectiveShall()
	{
		Item reflectiveShall = new Item((char)121, AsciiPanel.brightBlue, Type.STEALTH ,
				"Reflective Shall", 
				"A loose shall which drapes over your neck and reflects all the light the hits it.",
				0, 0
				, 25);
		if(world != null)
			world.spawnInside(r.nextInt(5), reflectiveShall);
		return reflectiveShall;
	}
	public Item newSparklingBoots()
	{
		Item sparklingBoots = new Item((char)121, AsciiPanel.brightBlue, Type.STEALTH ,
				"Sparkling Boots", 
				"Some glittery sparkling boots have found you, and now it's time to find yourself!",
				0, 0
				, 10);
		if(world != null)
			world.spawnInside(r.nextInt(5), sparklingBoots);
		return sparklingBoots;
	}
	public Item newTunnelAxe()
	{
		Item tunnelAxe = new Item((char)121, AsciiPanel.brightBlue, Type.DEVICE ,
				"Tunneling Axe", 
				"It sports a nice grip, and an even nicer blade. It's sharp so be carefull. It will cut metal, wood, dirt, enemies, allies, and anything else that gets in its way. It's a damn sharp thing, and it's a force to be reckoned with.",
				10, 10
				, 0);
		if(world != null)
			world.spawnInside(r.nextInt(5), tunnelAxe);
		return tunnelAxe;
	}
	public Item newDiscoBall()
	{
		Item discoBall = new Item((char)121, AsciiPanel.brightblue, Type.WEAPON ,
				"Disco Ball", 
				"An egg shaped disco ball. Some groovy bird bust've given birth to this thing",
				30, 0
				, 0);
		if(world != null)
			world.spawnInside(r.nextInt(5), discoBall);
		return discoBall;
	}
}