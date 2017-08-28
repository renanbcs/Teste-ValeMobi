package Valemobi;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.*;

public class ViewCadastrarCliente extends JFrame{
	
	private JLabel lid,lcpf,lnome,lativo,ltotal;
	private JTextField tfid,tfnome,tfcpf,tftotal;
	private JComboBox cbativo;
	private JPanel panel1;
	private JButton bcadastrar, bconsultar;
	private Container c;
	private String nome;
	private double valortotal;
	private int id;
	private long cpf;
	private boolean atividade; 
	private AcessoBD bd;
	private Connection conn = null;
	
	
	public ViewCadastrarCliente()
	{
		super("Teste Valemobi");
		
		lid = new JLabel("ID_Cliente:");
		lnome = new JLabel("Nome:");
		lcpf = new JLabel("CPF_CNPJ:");
		lativo = new JLabel("Status Cliente:");
		ltotal = new JLabel("Valor Total:");
		
		tfid = new JTextField(10);
		tfnome = new JTextField(10);
		tfcpf = new JTextField(20);
		tftotal = new JTextField(10);
		
		bcadastrar = new JButton("Cadastrar Cliente");
		bcadastrar.addActionListener(
		         new ActionListener() 
		         {
		            public void actionPerformed( ActionEvent event )
		            {
		            	
		            	if(tfid.getText().equals("")|| tfnome.getText().equals(" ") || tfcpf.getText().equals(" ") || tftotal.getText().equals(" ")){
		            		
		            		JOptionPane.showMessageDialog(null, "Preencha todos os dados");
		            	}
		            	else{
		            		
		            
			                try
			                {
			                	id = Integer.parseInt(tfid.getText());
			                	nome = tfnome.getText();
			                	cpf = Long.parseLong(tfcpf.getText());
			                	if(cbativo.getSelectedIndex()==1)
			                	{
			                		atividade = false;
			                	}
			                	else
			                	{
			                		atividade = true;      		
			                	}
			                	valortotal = Double.parseDouble(tftotal.getText());
			                	
			                	Cliente cl = new Cliente(id,nome,cpf,atividade,valortotal);
			                	
			                	try{
			                		
			                		bd = new AcessoBD();
			                        conn = bd.obtemConexao();
			                        conn.setAutoCommit(false);
			                        boolean certo = cl.cadastrarCliente(conn);
			                        conn.commit();
			                        if(certo == true){
			                        	
			                        	JOptionPane.showMessageDialog(null, "Cliente Cadastrado Corretamente");
			                        	tfid.setText("");
			                        	tfnome.setText("");
			                        	tfcpf.setText("");
			                        	tftotal.setText("");
			                        }
			                        else JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar esse cliente.\nRevise o Id do Cliente.");
			                		
			                	}catch(Exception e){
			                		
			                		e.printStackTrace();
			                		
			                	}
			                	
			                	
			                }catch (Exception e){
			                	
			                	JOptionPane.showMessageDialog(null, "Preencha os dados corretamente");
			                	
			                }
		            	}
		               
		             }
		            
		         }  
		                           );
		
		
		bconsultar = new JButton("Consultar Clientes");
		bconsultar.addActionListener(
	            new ActionListener() 
	            {
	               public void actionPerformed( ActionEvent event )
	               {
	            	   ViewConsultarCliente vcc = new ViewConsultarCliente();
	            	   vcc.TelaConsultar();
	            	   
	                                        
	               }
	            
	            }  
	                           );
		
		String atividade []= {"Ativo" ,"Inativo"};
		cbativo = new JComboBox(atividade);
		
		panel1 = new JPanel();
		
		panel1.setLayout(new GridLayout(6,2));
		panel1.add(lid);
		panel1.add(tfid);
		panel1.add(lnome);
		panel1.add(tfnome);
		panel1.add(lcpf);
		panel1.add(tfcpf);
		panel1.add(lativo);
		panel1.add(cbativo);
		panel1.add(ltotal);
		panel1.add(tftotal);
		panel1.add(bconsultar);
		panel1.add(bcadastrar);
		
		c = getContentPane();
		c.setLayout(new GridLayout(1,1));
		c.add(panel1); 	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}