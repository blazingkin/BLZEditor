package in.blazingk.blzeditor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import com.blazingkin.interpreter.executor.Executor;
import com.blazingkin.interpreter.library.BlzEventHandler;

public class BLZEditorEventHandler implements BlzEventHandler {
	
	String outputBuffer = "";
	
	private static boolean flushScheduled = false;
	final Runnable flush = new Runnable() {
		public void run() { 
			try{
				if (flushScheduled) {
					GraphicsHandler.pushOutput(outputBuffer);
					outputBuffer = "";
					flushScheduled = false;
				}
			}catch(Exception e){
				
			}
		}
	};
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	final ScheduledFuture<?> beeperHandle =
		       scheduler.scheduleAtFixedRate(flush, 100, 100, TimeUnit.MILLISECONDS);
	
	public BLZEditorEventHandler() {
		
	}
	
	@Override
	public void print(String contents) {
		try{
			outputBuffer += contents;
			if (!flushScheduled){
				
			}
			flushScheduled = true;
		}catch(Exception e){
			GraphicsHandler.pushOutput(contents);
		}

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
