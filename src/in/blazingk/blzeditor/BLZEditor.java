package in.blazingk.blzeditor;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import com.blazingkin.interpreter.Interpreter;
import com.blazingkin.interpreter.executor.Executor;
public class BLZEditor {
	public static int outputWidth, outputHeight = 900;
	public static boolean queueCodeToRun = false;
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("No file specified!");
		}
		String[] code = {":main", "\tprint(\"Hello World!\")", "end"};
		try {
			code = readFile(args[0]);
		}catch(Exception e) {}
		
		JFrame window = GraphicsHandler.setup(code);
		
		List<String> ags = new ArrayList<String>();
		ags.add("-m");
		ags.add("main");
		while (true){
			window.repaint();
			if (queueCodeToRun){
				BLZEditor.runCode(GraphicsHandler.getCode(), "main");
				queueCodeToRun = false;
			}
		}
	}
	
	public static BLZEditorEventHandler eventHandler = new BLZEditorEventHandler();
	
	public static void runCode(String[] code, String main){
		JFrame output = new JFrame("Your Program");
		output.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		output.setSize(outputWidth, outputHeight);
		List<String> ags = new ArrayList<String>();
		ags.add("-m");
		ags.add(main);
		try{
			Executor.cleanup();
			Interpreter.executeCodeAsLibrary(code, ags, eventHandler);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static String[] readFile(String fileLocation) throws Exception {
		File f = new File(fileLocation);
		LinkedList<String> in = new LinkedList<String>();
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()) {
			in.add(sc.nextLine());
		}
		String[] out = new String[in.size()];
		in.toArray(out);
		sc.close();
		return out;
	}

}
