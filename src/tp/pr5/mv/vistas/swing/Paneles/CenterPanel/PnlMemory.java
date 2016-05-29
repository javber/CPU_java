package tp.pr5.mv.vistas.swing.Paneles.CenterPanel;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import tp.pr5.mv.eventos.listeners.CpuMemoryEventListener;
import tp.pr5.mv.modulos.controladores.ControladorSwing;
import tp.pr5.mv.vistas.swing.Botones.BtnWrite;
import tp.pr5.mv.vistas.swing.Paneles.Panel;

@SuppressWarnings("serial")
public class PnlMemory extends Panel  implements CpuMemoryEventListener{
    private JTable tblMemory;
	private JLabel lblPos;
	private JLabel lblVal;
	private JTextField txtValor, txtPosicion;
	private BtnWrite btnWrite; 
	private DefaultTableModel model;

	
	public PnlMemory(ControladorSwing control) {
		super(control,"Memoria de la maquina");
		init();
	}
	public void init(){
		JScrollPane scrollPane;	
        JPanel pnlActions = new JPanel();
        JPanel pnlTexts = new JPanel();
        
        model= new DefaultTableModel();
		tblMemory = new JTable(model);		
		tblMemory.setEnabled(false);
		scrollPane = new JScrollPane(tblMemory);
		model.addColumn("Direccion");
		model.addColumn("Valor");
		
		lblPos = new JLabel("Pos:");
		lblVal = new JLabel("Val:");
		txtValor = new JTextField(5);
		txtPosicion = new JTextField(5);
		btnWrite = new BtnWrite(control,txtValor,txtPosicion);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        pnlActions.setLayout(new BoxLayout(pnlActions,BoxLayout.Y_AXIS));
        btnWrite.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlTexts.add(lblPos);
        pnlTexts.add(txtPosicion);
        pnlTexts.add(lblVal);
        pnlTexts.add(txtValor);
        
        pnlActions.add(pnlTexts);
        pnlActions.add(btnWrite);
        
        this.add(scrollPane);
        this.add(pnlActions);
        
        this.control.CpuAddListenerMemory(this);
	}
	
	
	public void onMemoryModify(int value, int direction) {
		boolean found = false;
		int first = 0;
		int last = this.model.getRowCount() - 1;
		int mid;
		
		while ( !found && (first <= last)) {
			mid = (first + last) / 2;
			if (direction > (int)model.getValueAt(mid, 0))
				first = mid + 1;
			else if (direction < (int)model.getValueAt(mid, 0))
				last = mid - 1;
			else{
				found = true;
				setValueInTable(value, mid, 1);	// modifica el valor
			}
		}
		if (!found)
			insertRowInTable(first, new Object[]{direction, value});
	}
	
	
	private void setValueInTable(final int value, final int row, final int column ){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				model.setValueAt(value, row, column);
			}
		});
	}
	
	
	private void insertRowInTable(final int row, final Object[] rowData){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				model.insertRow(row, rowData);
			}
		});
	}
	

}
