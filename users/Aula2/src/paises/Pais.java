package paises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pais {
	private int id;
	private String nome;
	private long populacao;
	private double area;
	
	public Pais() {
		
	}
	
	public Pais(int id, String nome, long populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	static {
	try {
			Class.forName("com.mysql.jdbc.Driver");
		}	catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	

	public Connection obtemConexao () throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/atividade-2?user=root&password=sync710n&useSSL=false");
	}

	public void createPais() {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString	(1, getNome());
			stm.setString	(2, String.valueOf(getPopulacao()));
			stm.setString	(3, String.valueOf(getArea()));
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadPais() {
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(Long.parseLong(rs.getString("populacao")));
					setArea(Double.parseDouble(rs.getString("area")));
				} else {
					setId(-1);
					setNome(null);
					setPopulacao(0);
					setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
	}
	
	public void updatePais() {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, getNome());
			stm.setString(2, String.valueOf(getPopulacao()));
			stm.setString(3, String.valueOf(getArea()));
			stm.setInt(4, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletePais() {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String maiorPopulacao() {
		String sqlSelect = "SELECT nome, populacao FROM pais ORDER BY populacao DESC LIMIT 1";
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					String nome = rs.getString("nome");
					String populacao = rs.getString("populacao");
					System.out.println(nome);
					System.out.println(populacao);
					return nome;

				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return null;
	}
	
	public String menorArea() {
		String sqlSelect = "SELECT nome, area FROM pais ORDER BY area LIMIT 1";

		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					String nome = rs.getString("nome");
					String area = rs.getString("area");
					System.out.println(nome);
					System.out.println(area);
					return nome;

				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return null;
	}
	
	public Pais[] vetorTresPaises(){
		int id = 0;
		
		Pais pais1 = new Pais(id, "Pais1", 11111, 111111);
		Pais pais2 = new Pais(id, "Pais2", 22222, 222222);
		Pais pais3 = new Pais(id, "Pais3", 33333, 333333);
		Pais[] vet = {pais1, pais2, pais3};
		return vet;
	}
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public long getPopulacao() {
		return populacao;
	}


	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}


	public double getArea() {
		return area;
	}


	public void setArea(double area) {
		this.area = area;
	}


	@Override
	public String toString() {
		return "Pais [id=" + id + ", nome=" + nome + ", populacao=" + populacao + ", area=" + area + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(area);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + (int) (populacao ^ (populacao >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (Double.doubleToLongBits(area) != Double.doubleToLongBits(other.area))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (populacao != other.populacao)
			return false;
		return true;
	}


}
