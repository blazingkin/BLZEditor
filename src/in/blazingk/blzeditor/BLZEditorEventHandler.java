package in.blazingk.blzeditor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.blazingkin.interpreter.executor.Executor;
import com.blazingkin.interpreter.library.BlzEventHandler;

public class BLZEditorEventHandler implements BlzEventHandler {

	JFrame window;
	OutputPanel output;
	
	int linesOut = 0;
	public BLZEditorEventHandler(JFrame window){
		this.window = window;
		window.pack();
		window.setVisible(true);
		window.setSize(BLZEditor.outputWidth,BLZEditor.outputHeight);
		output = new OutputPanel(window);
		output.setSize(window.getSize());
		window.getContentPane().setLayout(null);
		window.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        exitProgram("User requested Close");
		    }
		});
		output.setBounds(0, 0, window.getWidth(), window.getHeight());
		window.add(output);
		
		window.repaint();

		
	}
	
	@Override
	public void print(String contents) {
		output.addNewText(contents);
		output.repaint();
		window.repaint();
	}

	@Override
	public void exitProgram(String exitMessage) {
		Executor.cleanup();
		Executor.setCloseRequested(true);
	}

	public String getInput() {
		return (String)JOptionPane.showInputDialog("The program wants input:");
	}

}
