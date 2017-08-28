package Valemobi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
	
	private String nome;
	private double valortotal;
	private int id;
	private long cpf;
	private boolean atividade; 
	private Connection conn = null;
	private ArrayList<Cliente> listaCliente;
	private Cliente cl;
	
	public ClienteDAO(int id, String nome,long cpf, boolean atividade,double valortotal){
		
		setId(id);
		setNome(nome);
        setCpf(cpf);
        setAtividade(atividade);
        setValortotal(valortotal);
		
	}
	
	public ClienteDAO()
	{
		
		
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValortotal() {
		return valortotal;
	}

	public void setValortotal(double valortotal) {
		this.valortotal = valortotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public boolean isAtividade() {
		return atividade;
	}

	public void setAtividade(boolean atividade) {
		this.atividade = atividade;
	}
	
	public boolean cadastrarCliente(Connection i)
	{
		conn =i;
		
		String sqlInsert = "INSERT INTO tb_custumer_account(id_custumer,nm_custumer,cpf_cnpj,is_active,vl_total) VALUES (?,?,?,?,?)";
	    PreparedStatement stm = null;
	      try
	      {
	      
	         stm = conn.prepareStatement(sqlInsert);
	        
	         stm.setInt(1, getId());
	         stm.setString(2, getNome());
	         stm.setLong(3, getCpf());
	         stm.setBoolean(4, isAtividade());
	         stm.setDouble(5, getValortotal());
	         stm.execute();
	         return true;
	      
	      }
	      catch (Exception e)
	      {
	         //e.printStackTrace();
	         try
	         {
	            conn.rollback();
	            return false;
	         }
	         catch (SQLException e1)
	         {
	            //System.out.print(e1.getStackTrace());
	            return false;
	            
	         }
	      }
	      finally
	      {
	         if (stm != null)
	         {
	            try
	            {
	               stm.close();
	            }
	            catch (SQLException e1)
	            {
	               //System.out.print(e1.getStackTrace());
	            }
	         }
	      }
		
	}
	
	
	public ArrayList<Cliente> cunsultarCliente(int a,int b, double c,Connection conn)
	{
		
		  PreparedStatement stm = null;
	      
	      listaCliente = new ArrayList<Cliente>();
	      
	      String sqlListar = "select * from  tb_custumer_account where id_custumer between ? and ? and vl_total > ? order by vl_total desc";
	   	
	      try{
	      
	         stm = conn.prepareStatement(sqlListar);
	         stm.setInt(1, a);
	         stm.setInt(2, b);
	         stm.setDouble(3, c);
	         ResultSet resultSet = stm.executeQuery();
	      
	         while(resultSet.next())
	         {
	         
	            int id = resultSet.getInt("id_custumer");
	            String nome = resultSet.getString("nm_custumer");
	            long cpf = resultSet.getLong("cpf_cnpj");
	            boolean status = resultSet.getBoolean("is_active");
	            double valorTotal = resultSet.getDouble("vl_total");
	         
	            cl = new Cliente(id,nome,cpf,status,valorTotal);
	            listaCliente.add(cl);
	                        
	         }
	      
	      
	         stm.close();
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	         try
	         {
	            conn.rollback();
	         }
	         catch (SQLException e1)
	         {
	            System.out.print(e1.getStackTrace());
	         }
	      }
	      finally
	      {
	         if (stm != null)
	         {
	            try
	            {
	               stm.close();
	            }
	            catch (SQLException e1)
	            {
	               System.out.print(e1.getStackTrace());
	            }
	         }
	      }
	      
	      return listaCliente;
	}

}
