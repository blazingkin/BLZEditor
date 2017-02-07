package in.blazingk.blzeditor;

import javax.swing.JLabel;

public class LabelArg extends ArgComponent {

	public LabelArg(String texxt){
		comp = new JLabel(texxt);
		registerComponent();
	}
	
	public String generateArgument() {
		return "";
	}

}
