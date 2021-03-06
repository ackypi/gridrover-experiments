/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008-2009  "Lucas" Adam M. Paul <reilithion@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package gridrover;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Calendar;

/**
* This is the game engine.  It initializes a map, a rover, and a lander.
* It interprets all rover commands and is primarily responsible for
* the game mechanics.
*
* @author Lucas Adam M. Paul
* @version 0.0.1
*/
public class GameEngine
{
	private MapGrid missionMap;
	private PriorityQueue<Event> eventQueue;

	/**
	* Makes a new game, with specified control interface, and randomly-generated map dimensions.
	*
	* @param controlInterface The interface with which the user, or program, will control the rover
	* @param width Desired width of the randomly-generated map, in squares
	* @param length Desired length of the randomly-generated map, in squares
	* @param maxElevation The maximum elevation of any generated square, in meters
	* @param precision The precision to which any given elevation might be generated.
	*                  2, for instance, might result in an elevation of 12.34 and 4
	*                  might result in an elevation of 12.3456
	*/
	protected GameEngine(RoverControlInterface controlInterface, int width, int length, double maxElevation, int precision)
	{
		missionMap = new MapGrid(width, length, maxElevation, precision);
		MapSquare startsquare = missionMap.getSquare(width/2, length/2);
		if (startsquare == null)
		{
			throw new Error("Something funky happened!  Really funky!");
		}
		new Lander("Lander", 348.0, 11.236).setLocation(startsquare); // Mass 348.0 kg, 2.65 meters diameter by 1.6 meters tall
		Rover rover = new Rover("Rover", 185.0, 5.52, 100.0, controlInterface); // Mass 185.0 kg, 1.5 meters tall by 2.3 meters wide by 1.6 meters long
		rover.setLocation(startsquare);
		eventQueue = new PriorityQueue<Event>();
		Event initialCommand = new CommandEvent(Calendar.getInstance(), eventQueue, rover);
		eventQueue.add(initialCommand);
	}

	/**
	* Makes a new game with a randomly generated map of the given dimensions.
	* This constructor does not create a rover.  At least one rover needs to be
	* supplied via the addRover method in order for the game to really be
	* playable.
	*
	* @param width Desired width of the randomly-generated map, in squares
	* @param length Desired length of the randomly-generated map, in squares
	* @param maxElevation The maximum elevation of any generated square, in meters
	* @param precision The precision to which any given elevation might be generated.
	*                  2, for instance, might result in an elevation of 12.34 and 4
	*                  might result in an elevation of 12.3456
	*/
	protected GameEngine(int width, int length, double maxElevation, int precision)
	{
		missionMap = new MapGrid(width, length, maxElevation, precision);
		eventQueue = new PriorityQueue<Event>();
	}

	/**
	* This method will add a rover to the map grid and give it a starting
	* command in the event queue.
	*/
	protected void addRover(Rover rover, int xPos, int yPos)
	{
		MapSquare startsquare = missionMap.getSquare(xPos, yPos);
		if (startsquare == null)
		{
			throw new Error("Could not locate start square for rover.");
		}
		new Lander("Lander", 348.0, 11.236).setLocation(startsquare); // Mass 348.0 kg, 2.65 meters diameter by 1.6 meters tall
		rover.setLocation(startsquare);
		Event initialCommand = new CommandEvent(Calendar.getInstance(), eventQueue, rover);
		eventQueue.add(initialCommand);
	}

	/**
	* Scatters random items randomly across the map.
	*/
	protected void scatterItemsRandomly(List<Thing> itemPrototypes, double rItemInSquare, int maxItemsInSquare)
	{
		missionMap.scatterItemsRandomly(itemPrototypes, rItemInSquare, maxItemsInSquare);
	}

	/**
	* This is the new main game loop.  Event evaluation takes place here.
	*/
	protected void eventLoop()
	{
		while (!eventQueue.isEmpty())
		{
			eventQueue.poll().apply();
		}
	}
}
