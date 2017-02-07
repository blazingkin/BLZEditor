package in.blazingk.blzeditor;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.blazingkin.interpreter.executor.Instruction;

public class InstructionLine extends JPanel {
	private static final long serialVersionUID = 3152170750547310455L;
	public Instruction intr = Instruction.INVALID;
	public ArrayList<ArgComponent> arguments = new ArrayList<ArgComponent>(); 
	public InstructionLine(){
		setLayout(null);
		JComboBox<Instruction> instr = new JComboBox<Instruction>(Instruction.values());
		instr.setBounds(this.getX(), this.getY(), 100, BLZEditor.lineHeight);
		instr.setSelectedItem(intr);
		instr.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					@SuppressWarnings("unchecked")
					JComboBox<Instruction> source = (JComboBox<Instruction>) e.getSource();
					if (intr != (Instruction) source.getSelectedItem()){
						intr = (Instruction) source.getSelectedItem();
						for (int i = 0; i < arguments.size(); i++){
							remove(arguments.get(i).comp);
						}
						arguments = new ArrayList<ArgComponent>();
						update();
						BLZEditor.genCode();
					}
				}
				
			});
		this.add(instr);
		update();
		repaint();
		setVisible(true);
	}
	
	public void setInstruction(Instruction i){
		intr = i;
		update();
	}
	
	public void update(){
		switch(intr){
		case INVALID:
			break;
		case ECHO:
			{
				TextFieldArg out = new TextFieldArg();
				out.comp.setBounds(150, 0, 100, BLZEditor.lineHeight);
				arguments.add(out);
			}
			break;
		case SAMELINEECHO:
			{
				TextFieldArg out = new TextFieldArg();
				out.comp.setBounds(150, 0, 100, BLZEditor.lineHeight);
				arguments.add(out);
			}
			break;
		case STORE:
			{
				TextFieldArg var = new TextFieldArg();
				var.comp.setBounds(150, 0, 100, BLZEditor.lineHeight);
				arguments.add(var);
				LabelArg equalsSign = new LabelArg("=");
				equalsSign.comp.setBounds(300, 0, BLZEditor.editorFont.getSize(), BLZEditor.lineHeight);
				arguments.add(equalsSign);
				TextFieldArg var2 = new TextFieldArg();
				var2.comp.setBounds(350, 0, 100, BLZEditor.lineHeight);
				arguments.add(var2);
			}
			break;
		default:
			break;
		}
		for (int i = 0; i < arguments.size(); i++){
			this.add(arguments.get(i).comp);
		}
		repaint();
	}
	
	public String generateInstruction(){
		String buildingString = intr.instruction;
		for (ArgComponent ac: arguments){
			buildingString += ac.generateArgument();
		}
		buildingString.trim();
		return buildingString;
	}
	
	
}
