package tests;

import java.io.File;

import javax.swing.JFrame;
import core.Explorer;

class ExplorerTest extends JFrame {

	private static final long serialVersionUID = 8469191482426784020L;

	ExplorerTest(String path) {
		super("Windows Exploder - Javatpoint");
		add(new Explorer(path), "Center");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ExplorerTest(File.listRoots()[1].getAbsolutePath());
	}
}
