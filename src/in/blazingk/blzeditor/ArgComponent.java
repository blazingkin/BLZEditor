package in.blazingk.blzeditor;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class ArgComponent {
	public Component comp;
	public abstract String generateArgument();
	public void registerComponent(){
		comp.addPropertyChangeListener(new PropertyChangeListener(){

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				BLZEditor.genCode();
			}
			
		});
		comp.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				BLZEditor.genCode();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				BLZEditor.genCode();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				BLZEditor.genCode();
			}
			
		});
		comp.setFont(BLZEditor.editorFont);
	}
}
