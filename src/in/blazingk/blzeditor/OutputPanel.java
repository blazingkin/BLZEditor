package in.blazingk.blzeditor;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class OutputPanel extends JPanel {
	private static final long serialVersionUID = 156714065509033665L;
	private String text[] = {};
	public int spacing = 20;
	JFrame frame;
	
	public OutputPanel(JFrame parent){
		spacing = BLZEditor.editorFont.getSize();
		frame = parent;
	}
	
	public void addNewText(String text){
		String updatedText[] = new String[this.text.length + 1];
		for (int i = 0; i < this.text.length; i++){
			updatedText[i] = this.text[i];
		}
		updatedText[this.text.length] = text;
		this.text = updatedText;
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		setBounds(0, 0, frame.getWidth(), frame.getHeight());
		g.setFont(BLZEditor.editorFont);
		for (int i = 0; i < text.length; i++){
			g.drawString(text[i], 0, (i+1)*spacing);
		}
	}
	
}
