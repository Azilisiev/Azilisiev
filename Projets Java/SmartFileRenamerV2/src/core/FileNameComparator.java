package core;

import java.io.File;
import java.util.Comparator;

public class FileNameComparator implements Comparator<File> {
    public int compare( File a, File b ) {
        return a.getName().replaceAll(".png|.jpg|.jpeg|.txt", "").compareTo( b.getName().replaceAll(".png|.jpg|.jpeg|.txt", "") );
    }
}