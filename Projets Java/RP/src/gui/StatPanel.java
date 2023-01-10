package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class StatPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int amount;
	private int max;
	private Actioner actioner;
	private JLabel stat;

	public StatPanel(String name, int amount, int max, Actioner actioner) {
		super();
		this.name = name;
		this.amount = amount;
		this.max = max;
		this.actioner = actioner;
		setLayout(new BorderLayout());

		JButton plusButton = new JButton("+");
		stat = new JLabel(name + " : " + amount,SwingConstants.CENTER);
		JTextPane addAmount = new JTextPane();
		addAmount.setPreferredSize(new Dimension(20, 20));
		addAmount.setText("0");
		JButton minusButton = new JButton("-");
		
		StyledDocument doc = addAmount.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		plusButton.setPreferredSize(new Dimension(10, 20));
		stat.setPreferredSize(new Dimension(70, 20));
		minusButton.setPreferredSize(new Dimension(10, 20));

		add(plusButton, BorderLayout.NORTH);
		add(stat, BorderLayout.CENTER);
		add(addAmount, BorderLayout.EAST);
		add(minusButton, BorderLayout.SOUTH);
		plusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int textAmount = Integer.parseInt(addAmount.getText());
				if (getAmount() + textAmount < getMax()) {
					setAmount(getAmount() + textAmount);
					actioner.modifyCharacterStat(name,textAmount);
					
				}
				else {
					setAmount(max);
					actioner.setCharacterStat(name, max);
				}
				addAmount.setText("0");
				stat.setText(name + " : " + getAmount());

			}
		});

		minusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int textAmount = Integer.parseInt(addAmount.getText());
				if (getAmount() - textAmount > 0) {
					setAmount(getAmount() - Integer.parseInt(addAmount.getText()));
					actioner.modifyCharacterStat(name,-textAmount);
				}
				else {
					setAmount(0);
					actioner.setCharacterStat(name, 0);
				}
				addAmount.setText("0");
				stat.setText(name + " : " + getAmount());

			}
		});

	}

	public JLabel getStat() {
		return stat;
	}

	public void setStat(JLabel stat) {
		this.stat = stat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Actioner getActioner() {
		return actioner;
	}

	public void setActioner(Actioner actioner) {
		this.actioner = actioner;
	}

}
