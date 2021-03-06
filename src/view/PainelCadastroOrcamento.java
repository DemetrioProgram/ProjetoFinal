package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import controller.ControladoraUsuario;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ControllerCarro;
import controller.ControllerCliente;
import controller.ControllerMecanico;
import controller.ControllerOrcamento;
import controller.ControllerServico;
import controller.ControllerSituacao;
import controller.ControllerValidarCPF;
import model.entity.Carro;
import model.entity.Cliente;
import model.entity.Orcamento;
import model.entity.Situacao;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import java.awt.Font;

public class PainelCadastroOrcamento extends JPanel {
	private JTextField txtNome;
	private JTextField txtTelefone;
	private ControllerCliente controllerCliente = new ControllerCliente();
	private ControllerOrcamento controllerOrcamento = new ControllerOrcamento();
	private ControllerCarro controllerCarro = new ControllerCarro();
	private ControllerValidarCPF controllerValidarCPF = new ControllerValidarCPF();
	private Orcamento orcamento = new Orcamento();
	private Cliente cliente = new Cliente();
	private Carro carro = new Carro();
	private JTable table;
	private String msg = "";
	private JFormattedTextField txtCPF;
	private JTextField txtModelo;
	private JFormattedTextField txtPlaca;
	private JTextField txtDataSaida;
	private JTextField txtDataEntrada;
	private JTextField txtMarca;
	private JTextField txtAno;
	private JTextField txtCor;
	private JTextField txtDescricao;
	
	ArrayList<String> situacoes; 
	JComboBox cbSituacao; 

	
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	/**
	 * Create the panel.
	 */
	public PainelCadastroOrcamento() {
		
		MaskFormatter formatoPlaca;
		try {
			formatoPlaca = new MaskFormatter("AAA-####");
			 txtPlaca = new JFormattedTextField(formatoPlaca);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		
		
		JLabel lblCadastroOrcamento = new JLabel("Cadastro de Or\u00E7amento");
		lblCadastroOrcamento.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblNomeCliente = new JLabel("Nome do Cliente:");
		
		JLabel lblCpf = new JLabel("CPF:");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		MaskFormatter formatoTelefone;
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		try {
			formatoTelefone = new MaskFormatter("(##)#####-####");
			 txtTelefone = new JFormattedTextField(formatoTelefone);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		MaskFormatter formatoCPF;
		try {
			formatoCPF = new MaskFormatter("###.###.###-##");
			 txtCPF = new JFormattedTextField(formatoCPF);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		JButton btnSalvar = new JButton("Cadastrar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "";
				String nome = txtNome.getText();
				String cpf = txtCPF.getText().replace(".", "").replace("-", "");
				String telefone = txtTelefone.getText().replace("(", "").replace(")", "").replace("-", "");
				String marca = txtMarca.getText();
				int ano = 0;
				String anoValor = txtAno.getText();
				if (!anoValor.equals("    ")) {
					ano = Integer.parseInt(txtAno.getText());
				}
				String cor = txtCor.getText();
				String modelo = txtModelo.getText();
				String placa = txtPlaca.getText();
				String dataEntrada = txtDataEntrada.getText();
				msg += controllerCliente.validarCamposCliente(nome, cpf, telefone);
				msg += controllerCarro.validarCamposCarro(marca, ano, cor, modelo, placa);
				msg += controllerValidarCPF.validaCPF(cpf);
				msg += controllerOrcamento.validarDataEntrada(dataEntrada);
				
				if (msg.isEmpty()) {
					carro.setMarca(marca);
					carro.setAno(ano);
					carro.setCor(cor);
					carro.setModelo(modelo);
					carro.setPlaca(placa);
				
					cliente.setNome(nome);
					cliente.setCpf(cpf);
					cliente.setTelefone(telefone);
					cliente.setCarro(carro);
					cliente = controllerCliente.cadastrarCliente(cliente);
					carro.setIdCliente(cliente.getIdCliente());
					carro = controllerCarro.cadastrarCarro(carro);
					
					orcamento.setCliente(cliente);
					orcamento.setCarro(carro);
					orcamento.setDescricao(txtDescricao.getText());
					orcamento.setDataInicio(LocalDate.parse((txtDataEntrada.getText()), format));
					Situacao situacao = new Situacao();
					situacao.setIdSituacao(0);
					orcamento.setSituacao(situacao);
					controllerOrcamento.cadastrarOrcamento(orcamento);
					limparCampos();
					
					JOptionPane.showMessageDialog(null, " Or�amento cadastrado com sucesso! ");
					
				}else {
					JOptionPane.showMessageDialog(null, msg, " Aten��o! ", JOptionPane.WARNING_MESSAGE);
				}
	
			}
		});
		
		txtCPF.setText("   .   .   -   ");
		
		JLabel lblServico = new JLabel("Servi\u00E7o:");
		
		JLabel lblModelo = new JLabel("Modelo:");
		
		txtModelo = new JTextField();
		txtModelo.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa:");
		
		
		txtPlaca.setText("   -    ");
		txtPlaca.setColumns(10);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		
		JLabel lblMecanico = new JLabel("Mecanico:");
		
		JLabel lblPeca = new JLabel("Pe\u00E7a:");
		
		JComboBox cbPeca = new JComboBox();
		
		JButton btnAdd = new JButton("Add");
		
		JLabel lblDataEntrada = new JLabel("Data de Entrada:");
		
		JLabel lblDataSada = new JLabel("Data de Sa\u00EDda:");
		
		txtDataSaida = new JTextField();
		txtDataSaida.setColumns(10);
		
		JLabel lblSituacao = new JLabel("Situa\u00E7\u00E3o:");
		
		JButton btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		
		abaMecanico();
		
		
		ControllerSituacao controllerSituacao = new ControllerSituacao();
		situacoes = controllerSituacao.consultarSituacao();	
		cbSituacao = new JComboBox(situacoes.toArray());
		cbSituacao.setEnabled(false);
		cbSituacao.setSelectedIndex(-1);
		
		MaskFormatter formatoDataEntrada;
		txtDataEntrada = new JTextField();
		txtDataEntrada.setColumns(10);
		try {
			formatoDataEntrada = new MaskFormatter("##/##/####");
			 txtDataEntrada = new JFormattedTextField(formatoDataEntrada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		
		JLabel lblMarca = new JLabel("Marca:");
		
		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		
		JLabel lblAno = new JLabel("Ano:");
		
		MaskFormatter formatoAno;
		txtAno = new JTextField();
		txtAno.setColumns(10);
		try {
			formatoAno = new MaskFormatter("####");
			 txtAno = new JFormattedTextField(formatoAno);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		JLabel lblCor = new JLabel("Cor:");
		
		txtCor = new JTextField();
		txtCor.setColumns(10);
		
		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		
		ControllerServico controllerServico = new ControllerServico();
		ArrayList<String> servicos = controllerServico.consultarServico();
		JComboBox cbServico = new JComboBox(servicos.toArray());
		cbServico.setSelectedIndex(-1);
		cbServico.setEnabled(false);
		
		table = new JTable();
		
		ControllerMecanico controllerMecanico = new ControllerMecanico();
		ArrayList<String> mecanicos = controllerMecanico.consultarMecanico();
		JComboBox cbMecanico = new JComboBox(mecanicos.toArray());
		cbMecanico.setSelectedIndex(-1);
		cbMecanico.setEnabled(false);
		cbPeca.setEnabled(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(149)
							.addComponent(btnFechar)
							.addGap(427)
							.addComponent(btnSalvar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(88)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblPlaca)
										.addComponent(lblModelo)
										.addComponent(lblDataEntrada)
										.addComponent(lblServico)
										.addComponent(lblDescricao)
										.addComponent(lblMarca)
										.addComponent(lblAno)
										.addComponent(lblMecanico)
										.addComponent(lblSituacao)
										.addComponent(lblPeca))
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDataSada)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtAno, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(cbPeca, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(cbSituacao, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addComponent(btnAdd))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
													.addComponent(txtDescricao, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
													.addComponent(table, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(txtModelo, 147, 147, 147)
														.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
														.addComponent(lblCor)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(txtCor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(208))
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(txtDataEntrada, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
														.addGap(464)))
												.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(cbMecanico, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(cbServico, Alignment.LEADING, 0, 274, Short.MAX_VALUE)))))
								.addComponent(txtDataSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblCpf)
								.addComponent(lblNomeCliente)
								.addComponent(lblTelefone))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtTelefone, Alignment.LEADING)
									.addComponent(txtCPF, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
								.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 474, GroupLayout.PREFERRED_SIZE))
							.addGap(78)))
					.addGap(170))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(311)
					.addComponent(lblCadastroOrcamento, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(361, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(lblCadastroOrcamento)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNomeCliente)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(txtCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefone))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModelo)
						.addComponent(lblCor)
						.addComponent(txtCor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlaca)
						.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMarca)
						.addComponent(txtMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAno)
						.addComponent(txtAno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescricao)
						.addComponent(txtDescricao, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblServico)
						.addComponent(cbServico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMecanico)
						.addComponent(cbMecanico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataEntrada)
						.addComponent(txtDataEntrada, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbSituacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSituacao))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataSada)
						.addComponent(txtDataSaida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbPeca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd)
						.addComponent(lblPeca))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnFechar))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		abaMecanico();
		
	}
	
	public void abaMecanico() {
		txtDataSaida.setEnabled(false);
		
	}
	
	public void limparCampos() {
		txtNome.setText("");
		txtCPF.setText("");
		txtTelefone.setText("");
		txtCor.setText("");
		txtAno.setText("");
		txtDescricao.setText("");
		txtModelo.setText("");
		txtMarca.setText("");
		txtDataEntrada.setText("");
		txtPlaca.setText("");
		cbSituacao.setSelectedIndex(-1);
	}
	
	
}
