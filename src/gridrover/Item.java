/*
    GridRover -- A game to teach programming skills
    Copyright (C) 2008  "Lucas" Adam M. Paul

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

/**
* This is an item in the GridRover world.  Currently inert, items can be
* picked up and carried around.
*
* @author Lucas Adam M. Paul
* @version 0.0.0
*/
public class Item implements PhysicalObject
{
	private String name;
	private double mass, bulk;

	/**
	* Makes a new item with specified name, mass, and bulk
	*
	* @param name A short name for an itme.  Does not need to be unique.
	* @param mass Mass of an item in kilograms
	* @param bulk Very rough, boxy estimate of volume in cubic meters
	*/
	private Item(String name, double mass, double bulk)
	{
		this.name = name;
		this.mass = mass;
		this.bulk = bulk;
	}
	
	/**
	* Returns the name of the item
	*
	* @return Item's name
	*/
	public String getName()
	{
		return name;
	}

	/**
	* Returns the mass of the item
	*
	* @return Item's mass
	*/
	public double getMass()
	{
		return mass;
	}

	/**
	* Returns the bulk of the item
	*
	* @return Item's bulk
	*/
	public double getBulk()
	{
		return bulk;
	}
}
