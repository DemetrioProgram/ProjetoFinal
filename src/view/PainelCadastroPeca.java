package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MaskFormatter;

import controller.ControllerPeca;
import model.entity.Peca;
import java.awt.Font;

public class PainelCadastroPeca extends JPanel {
	private JTextField txtNomePeca;
	private JTextField txtValorCompra;
	private JTextField txtValorVenda;
	private JTextField txtQuantidade;
	private JTextField txtDataEntrada;
	private Peca peca = new Peca();
	private ControllerPeca controllerPeca = new ControllerPeca();
	
	DecimalFormat decimalFormata = new DecimalFormat("0.00");
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	/**
	 * Create the panel.
	 */
	public PainelCadastroPeca() {
		
		MaskFormatter formato;
		try {
			formato = new MaskFormatter("###");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		JLabel lblCadastroDePeca = new JLabel("Cadastro de Pe\u00E7a");
		lblCadastroDePeca.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNomeDaPeca = new JLabel("Nome da Peca:");
		
		JLabel lblValorCompra = new JLabel("Valor de Compra:");
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		
		txtNomePeca = new JTextField();
		txtNomePeca.setColumns(10);
		
		txtValorCompra = new JTextField();
		txtValorCompra.setColumns(10);
		try {
			formato = new MaskFormatter("##,##");
			 txtValorCompra = new JFormattedTextField(formato);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "";
				String nomePeca = txtNomePeca.getText();
				String valorCompra = txtValorCompra.getText().replace(",", "");
				String valorVenda = txtValorVenda.getText().replace(",", "");
				String quantidade = txtQuantidade.getText();
				String dataEntrada = txtDataEntrada.getText();
				msg = controllerPeca.validarCamposPeca(nomePeca, valorCompra, valorVenda, quantidade, dataEntrada);
				
				if (msg.isEmpty()) {
				peca.setNomePeca(txtNomePeca.getText());
				peca.setValCompra(Double.parseDouble(txtValorCompra.getText().replace(",", ".")));
				peca.setValVenda(Double.parseDouble(txtValorVenda.getText().replace(",", ".")));
				peca.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
				peca.setDataEntrada(LocalDate.parse((txtDataEntrada.getText()), format));
				controllerPeca.cadastrarPeca(peca);
				limparCampos();
				
				JOptionPane.showMessageDialog(null, " Pe�a cadastrada com sucesso! ");
				
				}else {
					JOptionPane.showMessageDialog(null, msg, " Aten��o! ", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
			
		
		txtValorVenda = new JTextField();
		txtValorVenda.setColumns(10);
		try {
			formato = new MaskFormatter("##,##");
			 txtValorVenda = new JFormattedTextField(formato);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		JLabel lblValorVenda = new JLabel("Valor de Venda:");
		
		JLabel lblDtEntrada = new JLabel("Data de Entrada:");
		
		MaskFormatter formatoDataEntrada;
		txtDataEntrada = new JTextField();
		txtDataEntrada.setColumns(10);
		txtDataEntrada.setColumns(10);
		try {
			formatoDataEntrada = new MaskFormatter("##/##/####");
			 txtDataEntrada = new JFormattedTextField(formatoDataEntrada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(btnFechar)
					.addPreferredGap(ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
					.addComponent(btnCadastrar)
					.addGap(176))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(212)
					.addComponent(lblCadastroDePeca)
					.addContainerGap(259, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(119)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNomeDaPeca)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblValorCompra)
									.addGap(6))
								.addComponent(lblDtEntrada, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblValorVenda, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblQuantidade)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNomePeca, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(txtDataEntrada, Alignment.LEADING)
							.addComponent(txtQuantidade, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(txtValorVenda, Alignment.LEADING)
							.addComponent(txtValorCompra, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
					.addGap(216))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblCadastroDePeca)
					.addGap(103)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomeDaPeca)
						.addComponent(txtNomePeca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtValorCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValorCompra))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblValorVenda)
						.addComponent(txtValorVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuantidade)
						.addComponent(txtQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDataEntrada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDtEntrada))
					.addGap(123)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnFechar)
						.addComponent(btnCadastrar))
					.addGap(75))
		);
		setLayout(groupLayout);
		
	}

	private void limparCampos() {
		txtNomePeca.setText("");
		txtValorCompra.setText("");
		txtValorVenda.setText("");
		txtQuantidade.setText("");
		txtDataEntrada.setText("");
		
		
	}
		
	
}


