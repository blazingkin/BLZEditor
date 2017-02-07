package in.blazingk.blzeditor;

import javax.swing.JTextField;

public class TextFieldArg extends ArgComponent {
	
	public TextFieldArg(){
		comp = new JTextField();
		registerComponent();
	}


	public String generateArgument() {
		return " "+((JTextField)comp).getText();
	}
	
	
	
}
