package tp.pr5.mv.vistas.swing.Paneles.CenterPanel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tp.pr5.mv.eventos.listeners.CpuStackEventListener;
import tp.pr5.mv.modulos.controladores.ControladorSwing;
import tp.pr5.mv.vistas.swing.Botones.BtnPop;
import tp.pr5.mv.vistas.swing.Botones.BtnPush;
import tp.pr5.mv.vistas.swing.Paneles.Panel;

@SuppressWarnings("serial")
public class PnlStack extends Panel implements CpuStackEventListener {

	private String stackText;
	private BtnPush btnPush;
	private JTextField txtPush;
	private BtnPop btnPop;
	private JTextArea txtStack;
	private JLabel lblValor;
	
	public PnlStack(ControladorSwing control) {
		super(control,"Pila de operandos");
		stackText = "";
		init();
	}
	
	public void init(){
		JPanel pnlActions = new JPanel();
		JPanel pnlPush = new JPanel();
		JScrollPane stackScroll;
		
		txtStack = new JTextArea(11,31);
		txtPush = new JTextField(5);
		btnPush= new BtnPush(this.control,txtPush);
		btnPop = new BtnPop(this.control);
		lblValor = new JLabel("Valor:");
		stackScroll  = new JScrollPane(txtStack);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		pnlActions.setLayout(new BoxLayout(pnlActions,BoxLayout.Y_AXIS));
		
		btnPop.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtStack.setEditable(false);
		txtStack.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		txtPush.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		
		pnlPush.add(lblValor);
		pnlPush.add(txtPush);
		pnlPush.add(btnPush);
		pnlActions.add(pnlPush);
		pnlActions.add(btnPop);
		
		this.add(stackScroll);
		this.add(pnlActions);
		
		this.control.CpuAddListenerStack(this);
	}

	public void onPush(int value) {
		stackText = String.valueOf(value) + "\n" + stackText;
		this.updateStackText();
	}

	public void onPop() {
		int offset;
		
		offset = stackText.indexOf('\n') + 1;
		if(offset > 0)
			stackText = stackText.substring(offset);
		else
			stackText = "";
		this.updateStackText();
	}
	
	private void updateStackText(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				txtStack.setText(stackText);
			}
		});
	}

}
