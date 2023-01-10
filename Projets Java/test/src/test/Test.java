package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {

	public static void main(String[] args) {
		try {
			throw new IOException("AAAAAAAAAAAAAH");
		} catch (Exception e) {

			appendToFile(e);
		}

	}

	public static void appendToFile(Exception e) {
		try {
			FileWriter fstream = new FileWriter(".\\save.gmsv", true);
			BufferedWriter out = new BufferedWriter(fstream);
			PrintWriter pWriter = new PrintWriter(out, true);
			e.printStackTrace(pWriter);
		} catch (Exception ie) {
			throw new RuntimeException("Could not write Exception to file", ie);
		}
	}

}
