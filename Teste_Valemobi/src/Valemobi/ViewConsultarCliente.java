package Valemobi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class ViewConsultarCliente extends JDialog{
	
	private JLabel lid1,late,lvalortotal,lmedia,lresultado;
	private JTextField tfidbusca,tfidbusca1,tfvalortotal;
	private JPanel panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9;
	private JButton bconsultar1,bvoltar;
	private String listaclienteMatrix[][];
	private JScrollPane sb;
	private Container c;
	private JTable tabela;
	private AcessoBD bd;
	private Connection conn = null;
	private ArrayList<Cliente> listaCliente;
	private DecimalFormat de;
	
	public ViewConsultarCliente()
	{
		super();
		setTitle("Consultar Cliente");
		de = new DecimalFormat("0.00");
		
	}
	
	
	public void TelaConsultar()
	{
	
		lid1 = new JLabel("Pesquisar por ID:");
		late = new JLabel("Até:");
		lvalortotal = new JLabel("Pesquisar por Valor Total maior que:");
		lmedia = new JLabel("Média dos Encontrados: ");
		lresultado = new JLabel("");
		
		tfidbusca = new JTextField(10);
		tfidbusca.setText(""+1500);
		tfidbusca1  = new JTextField(10);
		tfidbusca1.setText(""+2700);
		tfvalortotal = new JTextField(10);
		tfvalortotal.setText(""+560);
		
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();
		panel9 = new JPanel();
		
		String coluna [] = {"ID_Cliente","Nome","CPF_CNPJ","Status_Cliente","Valor_Total"};
		
		
		
		bconsultar1 = new JButton("Buscar");
		bconsultar1.addActionListener(
	            new ActionListener() 
	            {
	               public void actionPerformed( ActionEvent event )
	               {
	            	   
	            	   try{
		            	   
	            		   if(tfidbusca.getText().equals("")||tfidbusca1.getText().equals("")||(tfvalortotal.getText().equals("")||Integer.parseInt(tfidbusca.getText())<=0||Integer.parseInt(tfidbusca1.getText())<=0)){
	            			  
	            			   JOptionPane.showMessageDialog(null, "Preencha todos os dados corretamente");
	            		   }
	            		   else{
	            		   
		            		   int a = Integer.parseInt(tfidbusca.getText());
			            	   int b = Integer.parseInt(tfidbusca1.getText());
			            	   int c = Integer.parseInt(tfvalortotal.getText());
			            	   Cliente cliente = new Cliente();
			            	   
			            	   try{
			                		
			                		bd = new AcessoBD();
			                        conn = bd.obtemConexao();
			                        conn.setAutoCommit(false);
			                        listaCliente = new ArrayList<Cliente>();
			                        listaCliente = cliente.cunsultarCliente(a, b, c,conn);
			                        
			                        listaclienteMatrix = new String [listaCliente.size()][5];
			                        double total =0;
			                        
			                        for(int x = 0;x<listaCliente.size();x++)
			                        {
			                        	listaclienteMatrix [x][0] = ""+ listaCliente.get(x).getId();
			                        	listaclienteMatrix [x][1] = ""+ listaCliente.get(x).getNome();
			                        	listaclienteMatrix [x][2] = ""+ listaCliente.get(x).getCpf();
			                        	if(listaCliente.get(x).isAtividade()==true){
			                        		
			                        		listaclienteMatrix [x][3] = "Ativo";
			                        	}
			                        	else listaclienteMatrix [x][3] = "Inativo";
			                        	
			                        	listaclienteMatrix [x][4] = ""+ de.format(listaCliente.get(x).getValortotal());
			                        	
			                            total = total + listaCliente.get(x).getValortotal();
			                            
			                        	
			                        	
			                        	
			                        	
			                        }
			                      
			                		total = total/listaCliente.size();
			                        
			                        lresultado.setText(""+de.format(total));
			                        
			                        
			                		tabela = new JTable(listaclienteMatrix,coluna);
			                		
			                		tabela.revalidate();
			                		tabela.repaint();
			                		
			                		sb = new JScrollPane(tabela);
			                		sb.repaint();
			                		sb.revalidate();
			                        
			                        panel5 = new JPanel();
			                        panel5.setLayout(new GridLayout(1,1));
			                		panel5.add(sb);
			                		panel5.revalidate();
			                		panel5.repaint();
			                		
			                		panel7.add(panel5, BorderLayout.CENTER);
			                		panel7.revalidate();
			                		panel7.repaint();
			                		
			                		bconsultar1.setEnabled(false);
			                        
			                		JOptionPane.showMessageDialog(null, "Busca realizada com sucesso encontramos: "+listaCliente.size()+", Resultados para sua Busca:" );
			                		
			                		
			                		
			                        
			                        	
			                	}catch(Exception e){
			                		
			                		e.printStackTrace();
			                		
			                	}
			            	   
	            		   }
		            	   
	            	   }catch(Exception e){
	            		   
	            		   
	            		   
	            	   }
	                                        
	               }
	            
	            }  
	                           );
		
		
		bvoltar = new JButton("Voltar");
		bvoltar.addActionListener(
	            new ActionListener() 
	            {
	               public void actionPerformed( ActionEvent event )
	               {
	            	   dispose();                        
	               }
	            
	            }  
	                           );
		
		
		
		                                   
	    
	   
		
		
		
		panel2.setLayout(new GridLayout(1,4));
		panel2.add(lid1);
		panel2.add(tfidbusca);
		panel2.add(late);
		panel2.add(tfidbusca1);
		
		panel3.setLayout(new GridLayout(1,2));
		panel3.add(lvalortotal);
		panel3.add(tfvalortotal);
		
		panel4.setLayout(new BorderLayout(2,1));
		panel4.add(panel2, BorderLayout.NORTH);
		panel4.add(panel3, BorderLayout.SOUTH);
		
		
		
		panel6.setLayout(new GridLayout(1,2));
		panel6.add(bvoltar);
		panel6.add(bconsultar1);
		
		panel8.setLayout(new GridLayout(1,2));
		panel8.add(lmedia);
		panel8.add(lresultado);
		
		panel9.setLayout(new BorderLayout(2,1));
		panel9.add(panel8, BorderLayout.NORTH);
		panel9.add(panel6, BorderLayout.SOUTH);
		
		panel7.setLayout(new BorderLayout(2,1));
		panel7.add(panel4, BorderLayout.NORTH);
		panel7.add(panel9, BorderLayout.SOUTH);
		
		getContentPane().add(panel7);
	      
	    setSize(500,250);
	    setLocation(500,275);
	    setModal(true);
	    setVisible(true);
	    
	
	}

}
