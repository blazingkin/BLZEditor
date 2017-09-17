package in.blazingk.blzeditor;

import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GraphicsHandler {
	
	public static TextArea codeArea;
	public static JTextArea outputArea;
	public static Font editorFont;
	public static Font consoleFont;
	public static JFrame window;
	public static int lineHeight = 50;
	public static int spacing = 10;
	
	public static JFrame setup(String[] code) {
		editorFont = new Font("Verdana", Font.BOLD, 20);
		consoleFont = new Font("Verdana", Font.PLAIN, 20);
		lineHeight = editorFont.getSize()*5;
		
		window = new JFrame("BLZEditor");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		window.setSize(screenSize.width, screenSize.height);
		window.setLocation(screenSize.x, screenSize.y);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.getContentPane().setLayout(new GridBagLayout());
		
		window.add(setupTextArea(window, code));
		
		window.add(setupRunCode(window));
		
		window.add(setupOutputArea(window));

		return window;
	}
	
	private static TextArea setupTextArea(JFrame window, String[] code) {
		codeArea = new TextArea();
		codeArea.setBounds(0,window.getHeight() / 12, window.getWidth(), (int)(window.getHeight() * 9.0/12.0));
		codeArea.setText(format(code));
		codeArea.setFont(editorFont);
		JScrollPane scrollPane = new JScrollPane(codeArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		window.add(scrollPane);
		return codeArea;
	}
	
	private static JButton setupRunCode(JFrame window) {
		JButton runCodeBut = new JButton("Run");
		runCodeBut.setFocusable(false);
		runCodeBut.setFont(editorFont);
		runCodeBut.setBounds(0, 0, window.getWidth()/5, window.getHeight()/12);
		runCodeBut.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    BLZEditor.queueCodeToRun = true;
				    outputArea.setText("");
				  } 
				});
		return runCodeBut;
	}
	
	private static Component setupOutputArea(JFrame window) {
		outputArea = new JTextArea();
		outputArea.setBounds(0, (int)(window.getHeight() * 5.0 / 6.0), window.getWidth(), (int)(window.getWidth() / 12.0));
		outputArea.setFont(consoleFont);
		outputArea.setEditable(false);
		//outputArea.setWrapStyleWord(true);
		//outputArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(outputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		window.getContentPane().add(scrollPane);
		return outputArea;
	}
	
	public static String[] getCode() {
		return codeArea.getText().split("\n");
	}
	
	public static void pushOutput(String output) {
		outputArea.setText(outputArea.getText()+output);
	}
	
	private static String format(String[] code) {
		String buildingString = "";
		for (String s : code) {
			buildingString += s + "\n";
		}
		return buildingString;
	}

}
