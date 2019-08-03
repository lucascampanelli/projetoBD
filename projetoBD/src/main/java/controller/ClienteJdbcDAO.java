package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteJdbcDAO {

	private Connection conn;
	
	
	public ClienteJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	public void salvar(Cliente c) throws SQLException {
		String sql = "insert into cliente (nome, endereco, bairro) values ('"+c.getNome()+"','"+c.getEndereco()+"','"+c.getFone()+"','" + c.getEmail() + "');";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
        prepareStatement.close();
	}
	
	public void alterar(Cliente c) {
		String sql = "update cliente set nome='"+c.getNome()+"',endereco='"+c.getEndereco()+"',bairro='"+c.getFone()+"',email='"+c.getEmail()+"';";
		System.out.println(sql);
		PreparedStatement prepareStatement;
		try {
			prepareStatement = this.conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
            prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void excluir(int id) {
		String sql = "delete from cliente where id='"+id+"';";
		System.out.println(sql);
        try {
    		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
    		prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}                		
	}
	
	public List<Cliente> listar() {
		String sql = "select * from tb_clientes";
        System.out.println(sql);		
        List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setId_cliente(rs.getInt("id_cliente"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setFone(rs.getString("fone"));
				cliente.setEmail(rs.getString("email"));

				clientes.add(cliente);
			}
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
}
