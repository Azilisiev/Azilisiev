package core;

import java.io.File;
import java.util.Comparator;

public class FileSizeComparator implements Comparator<File> {
    public int compare( File a, File b ) {
        long aSize = a.getTotalSpace();
        long bSize = b.getTotalSpace();
        if ( aSize == bSize ) {
            return 0;
        }
        else {
            return Long.compare(aSize, bSize);
        }
    }
}
