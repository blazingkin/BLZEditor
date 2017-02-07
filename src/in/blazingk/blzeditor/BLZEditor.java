package in.blazingk.blzeditor;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.blazingkin.interpreter.Interpreter;
import com.blazingkin.interpreter.executor.Executor;
public class BLZEditor {
	public static int outputWidth, outputHeight = 900;
	public static boolean queueCodeToRun = false;
	public static Font editorFont;
	public static TextArea codeArea;
	public static int lineHeight = 50;
	public static int spacing = 10;
	
	public static ArrayList<InstructionLine> theCode = new ArrayList<InstructionLine>();
	
	
	public static void genCode(){
		String buildingString = "";
		for (int i = 0; i < theCode.size(); i++){
			buildingString += theCode.get(i).generateInstruction()+"\n";
		}
		codeArea.setText(buildingString);
	}
	
	public static void main(String[] args) {
		editorFont = new Font("Verdana", Font.BOLD, 30);
		lineHeight = editorFont.getSize()*5;
		JFrame window = new JFrame("BLZEditor");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		window.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.getContentPane().setLayout(null);
		codeArea = new TextArea();
		codeArea.setBounds(window.getWidth() * 2/3,window.getHeight() / 3, window.getWidth() /3, window.getHeight() * 2/3);
		codeArea.setText(":main\nECHO Hello World!\nEND");
		codeArea.setFont(editorFont);
		window.add(codeArea);
		JButton runCodeBut = new JButton("Run Code");
		runCodeBut.setFocusable(false);
		runCodeBut.setFont(editorFont);
		runCodeBut.setBounds(window.getWidth() * 2/3, 0, window.getWidth()/3, window.getHeight()/3);
		window.add(runCodeBut);
		runCodeBut.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    BLZEditor.queueCodeToRun = true;
				  } 
				});
		theCode.add(new InstructionLine());
		theCode.add(new InstructionLine());
		theCode.add(new InstructionLine());
		theCode.add(new InstructionLine());
		theCode.add(new InstructionLine());
		for (int i = 0; i < theCode.size(); i++){
			theCode.get(i).setBounds(0, (i+1)*(lineHeight+spacing), window.getWidth() * 2/3, lineHeight);
			window.add(theCode.get(i));
		}	
			
		List<String> ags = new ArrayList<String>();
		ags.add("-m");
		ags.add("main");
		while (true){
			window.repaint();
			for (int i = 0; i < theCode.size(); i++){
				theCode.get(i).repaint();
			}
			if (queueCodeToRun){
				ArrayList<String> userCode = new ArrayList<String>();
				for (String s: codeArea.getText().split("\n")){
					userCode.add(s);
				}
				BLZEditor.runCode(userCode, "main");
				queueCodeToRun = false;
			}
		}
	}
	
	
	public static void runCode(ArrayList<String> code, String main){
		JFrame output = new JFrame("Your Program");
		output.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		output.setSize(outputWidth, outputHeight);
		List<String> ags = new ArrayList<String>();
		ags.add("-m");
		ags.add(main);
		BLZEditorEventHandler eventHandler = new BLZEditorEventHandler(output);
		try{
			Executor.cleanup();
			Interpreter.executeCodeAsLibrary(code, ags, eventHandler);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
