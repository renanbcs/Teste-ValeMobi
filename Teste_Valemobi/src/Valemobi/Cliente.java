package Valemobi;

import java.sql.Connection;
import java.util.ArrayList;

public class Cliente {

	private String nome;
	private double valortotal;
	private int id;
	private long cpf;
	private boolean atividade; 
	
	public Cliente(int id, String nome,long cpf, boolean atividade,double valortotal){
		
		setId(id);
		setNome(nome);
        setCpf(cpf);
        setAtividade(atividade);
        setValortotal(valortotal);
		
	}
	
	public Cliente()
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
	
	public boolean cadastrarCliente(Connection conn)
	{
		ClienteDAO cl = new ClienteDAO(id,nome,cpf,atividade,valortotal);
		boolean certo = cl.cadastrarCliente(conn);
		return certo;
		
	}
	
	public ArrayList<Cliente> cunsultarCliente(int a,int b, double c,Connection conn)
	{
		ClienteDAO cl = new ClienteDAO();
		return cl.cunsultarCliente(a,b,c,conn);
		
	}
	
}
