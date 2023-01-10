package core;

import java.io.File;
import java.math.*;
import java.util.Arrays;
import java.util.Comparator;

public class FolderComparator {

	public static void main(String... args) {
        File dir1 = new File("H:\\Mon Drive\\Wallp\\WallpAdj20_9");
        File dir2 = new File("H:\\Mon Drive\\Wallp\\WallpRaw");
        File[] dir1files = dir1.listFiles();
        File[] dir2files = dir2.listFiles();
        sortDirectory(dir1files);
        sortDirectory(dir2files);
        Compare(dir1files,dir2files);
    }

    public static void showFiles(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getAbsolutePath());
                showFiles(file.listFiles()); // Calls same method again.
            } else {
                System.out.println("File: " + file.getAbsolutePath());
            }
        }
    }
    
    public static void sortDirectory(File[] directory) {
    	Arrays.sort( directory, new FileNameComparator() );
    }
    
    public static int Compare(File[] filelist1, File[] filelist2) {
    	int maxlength = Math.max(filelist1.length, filelist2.length);
       for (int i = 0; i < maxlength-1; i++) {
    	   File file1 = filelist1[i];
    	   File file2 = filelist2[i];
    	   String name1 = file1.getName();
    	   String name2 = file2.getName();
    	   name1 = name1.replaceAll(".png|.jpg|.jpeg|.txt","");
    	   name2 = name2.replaceAll(".png|.jpg|.jpeg|.txt","");
    	   //System.out.println(name1 + " " + name2);
    	   if(name1.equals(name2)) {
    		   //System.out.println(name1);
    	   }
    	   else {
    		   System.err.println(name1 + " != " + name2);
    		   return 1; 
    	   }
		
	}
       return 0;
        
    }

}
    
  
