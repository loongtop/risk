package sample.utils;

import sample.model.Country;
import sample.model.Player;

import java.util.*;

public class GameUtil {

	public static Map<Country,Integer> tempArmyDistributeMap = new HashMap<> ();

	public static List<Country> findAllConnectedCountry(Player player, Country country){

		Set<Country> validOptions = new HashSet<> ();

		connectedCountryUtil (country,validOptions,player.getTerritory ());

		List<Country> sortedArrayList = new ArrayList<> (validOptions);

		sortedArrayList.sort ((i,j)->{

			if (!i.getContinent ().equals (j.getContinent ())) {

				return i.getContinent ().getName ().compareTo (j.getContinent ().getName ());
			}

			else return i.getName ().compareTo (j.getName ());

		});

		return sortedArrayList;

	}

	private static void connectedCountryUtil(Country c, Set<Country> visited, Set<Country> available){

		for (Country country: c.getAdjacentCountry ()){

			if (!visited.contains (c) && available.contains (c)){

				visited.add (country);

				connectedCountryUtil (country, visited, available);
			}
		}
	}

	public static void tempDistrubuteArmy(Country country, int army) {

		tempArmyDistributeMap.put (country, army);

	}

	public static void resetTempMap(){

		tempArmyDistributeMap.clear ();
	}

	public static void initTempMap(Player player) {

		resetTempMap ();

		player.getTerritory ().forEach (c -> tempArmyDistributeMap.put (c,c.getArmy ()));
	}
}
