package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vendas;

public class VendasJdbcDAO {

	private Connection conn;
	
	
	public VendasJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	public void salvar(Vendas c) throws SQLException {
		String sql = "insert into tb_vendas (id_cliente, data, vlr_total, desconto, vlr_pago) values ('"+c.getId_cliente()+"','"+c.getData()+"','"+c.getVlr_total()+"','"+c.getDesconto()+"','"+c.getVlr_pago()+"');";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
        prepareStatement.close();
	}
	
	public void alterar(Vendas c) {
		String sql = "update tb_vendas set id_cliente='"+c.getId_cliente()+"',data='"+c.getData()+"',vlr_total='"+c.getVlr_total()+"',desconto='"+c.getDesconto()+"',vlr_pago='"+c.getVlr_pago()+"';";
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
		String sql = "delete from tb_vendas where id='"+id+"';";
		System.out.println(sql);
        try {
    		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
    		prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}                		
	}
	
	public List<Vendas> listar() {
		String sql = "select * from tb_vendas";
        System.out.println(sql);		
        List<Vendas> alunos = new ArrayList<Vendas>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				int id_cliente = Integer.parseInt(rs.getString("id_cliente"));
				Date data = Date.valueOf(rs.getString("data"));
				double vlr_total = Double.parseDouble(rs.getString("vlr_total"));
				double desconto = Double.parseDouble(rs.getString("desconto"));
				double vlr_pago = Double.parseDouble(rs.getString("vlr_pago"));
				Vendas vendas = new Vendas();
				vendas.setId_cliente(id_cliente);
				vendas.setData(data);
				vendas.setVlr_total(vlr_total);
				vendas.setDesconto(desconto);
				vendas.setVlr_pago(vlr_pago);
			}
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alunos;
	}
}
