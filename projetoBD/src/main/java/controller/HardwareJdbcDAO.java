package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Hardware;


public class HardwareJdbcDAO {

	private Connection conn;
	
	
	public HardwareJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	public void salvar(Hardware c) throws SQLException {
		String sql = "insert into hardware (descricao, preco_unit, qtde_atual, qtde_minima) values ('"+c.getDescricao()+"','"+c.getPreco_unit()+"','"+c.getQtde_atual()+"','" + c.getQtde_minima() + "');";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
        prepareStatement.close();
	}
	
	public void alterar(Hardware c) {
		String sql = "update hardware set descricao='"+c.getDescricao()+"',preco_unit='"+c.getPreco_unit()+"',qtde_atual='"+c.getQtde_atual()+"',qtde_minima='"+c.getQtde_minima()+"';";
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
		String sql = "delete from hardware where id='"+id+"';";
		System.out.println(sql);
        try {
    		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
    		prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}                		
	}
	
	public List<Hardware> listar() {
		String sql = "select * from hardware";
        System.out.println(sql);		
        List<Hardware> alunos = new ArrayList<Hardware>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				int id_hardware = rs.getInt("id_hardware");
				String descricao = rs.getString("descricao");
				double preco_unit = Double.parseDouble(rs.getString("preco_unit"));
				int qtde_atual = Integer.parseInt(rs.getString("qtde_atual"));
				int qtde_minima = Integer.parseInt(rs.getString("qtde_minima"));
				Hardware aluno = new Hardware();
				aluno.setDescricao(descricao);
				aluno.setPreco_unit(preco_unit);;
				aluno.setQtde_atual(qtde_atual);
				aluno.setQtde_minima(qtde_minima);
			}
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alunos;
	}
}
