package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmartRenamerV1Updater {

	public static void main(String[] args) {
		File storedNames = new File("D:\\Programmes\\AutoHotkey\\scripts\\smartRenameFileNames.txt");
		File dir2 = new File("H:\\Mon Drive\\Wallp\\WallpRaw");

		HashMap<String, Integer> nameListS =  null;
		HashMap<String, Integer> nameListDir = null;

		Path filePath = Path.of(storedNames.getAbsolutePath());
		try {
			String content = Files.readString(filePath);
			String[] names = content.split("\n");
			nameListS = getNames(names);
			
			File[] dir1files = dir2.listFiles();
			String namesD = "";
			int count = 0;
			for (File file : dir1files) {
				if (count==0) {
					namesD = file.getName();
				}
				else {
					namesD = namesD + "\n" +  file.getName();
				}
				count++;
				
			}
			nameListDir = getNames(namesD.split("\n"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(nameListS.toString() + "\n" + nameListDir.toString());
		
		String newContent = "";
		int count = 0;
		TreeMap<String, Integer> nameListSorted = sortbykey(nameListDir);
		for (Entry<String, Integer> entry : nameListSorted.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if(count>0) {
				newContent = newContent + "\n" + convertToString(key, value);
			}
			else {
				newContent = convertToString(key, value);
			}
			
			count++;
		}
		System.out.println(newContent);
		

	}

	private static HashMap<String, Integer> getNames(String[] names) {
		HashMap<String, Integer> nameList = new HashMap<String, Integer>();
		for (String name : names) {
			if (!name.matches(".*(bis|ter|quat|quint|sex|sept|ini).*")) {
				name = name.replaceAll("(.png|.jpg|.jpeg|.txt)*$", "");
				String numPart;
				Pattern pattern = Pattern.compile("(\\d{1,3}$)");
				Matcher matcher = pattern.matcher(name);
				if (matcher.find()) {
					numPart = matcher.group(1);
				} else {
					numPart = "01";
				}

				String[] nameParts = name.split("(\\d{1,3}$)");
				String namePart = nameParts[0];
				int num = Integer.parseInt(numPart);
				if (num == 0) {
					num = 1;
				}
				if (nameList.containsKey(namePart)) {
					if (num > nameList.get(namePart)) {
						nameList.put(namePart, num);
					}
				} else {
					nameList.put(namePart, num);
				}
			}

		}
		return nameList;

	}

	private static String convertToString(String name, int num) {
		String string = name;
		if (num < 10) {
			string = string + "0" + String.valueOf(num);
		} else {
			string = string + String.valueOf(num);
		}
		return string;
	}

	public static TreeMap<String, Integer> sortbykey(HashMap<String, Integer> map) {
		// TreeMap to store values of HashMap
		HashMap<String, Integer> newMap = new HashMap<String, Integer>();
		TreeMap<String, Integer> sorted = new TreeMap<String, Integer>();

		// Copy all data from hashMap into TreeMap
		sorted.putAll(map);

		return sorted;
	}

}
