/* 
 * Demoiselle Sample
 * Copyright (c) 2009 Serpro and other contributors as indicated
 * by the @author tag. See the copyright.txt in the distribution for a
 * full listing of contributors.
 * 
 * Demoiselle Sample is a set of open source Java EE project samples using
 * the Demoiselle Framework
 *   
 * Demoiselle Sample is released under the terms of the GPL license 3
 * http://www.gnu.org/licenses/gpl.html  GPL License 3
 *   
 * This file is part of Demoiselle Sample.
 * 
 * Demoiselle Sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3.
 * 
 * Demoiselle Sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Demoiselle - Sample.  If not, see <http://www.gnu.org/licenses/>
 */
package br.gov.sample.demoiselle.escola.persistence.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.persistence.JDBC.JDBCGenericDAO;
import br.gov.framework.demoiselle.persistence.JDBC.JDBCUtil;
import br.gov.sample.demoiselle.escola.bean.Funcionario;
import br.gov.sample.demoiselle.escola.message.ErrorMessage;
import br.gov.sample.demoiselle.escola.persistence.dao.IFuncionarioDAO;

/**
 * @author CETEC/CTJEE
 */
public class FuncionarioDAOJDBC extends JDBCGenericDAO<Funcionario> implements IFuncionarioDAO {

	public List<Funcionario> listar() {
		
		List<Funcionario> result = new ArrayList<Funcionario>();
		PreparedStatement prepStmt = null;
		Connection con = null;
		
		try {
			
			con = JDBCUtil.getInstance().getConnection();
			prepStmt = con.prepareStatement("SELECT * FROM funcionario ORDER BY nome ASC");
			ResultSet rs = prepStmt.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("id_funcionario");
				String nome = rs.getString("nome");
				Date nascimento = rs.getDate("nascimento");
				String lotacao = rs.getString("lotacao");
				String cpf = rs.getString("cpf");
				result.add(new Funcionario(id, nome, nascimento, lotacao, cpf));
			}
			
		} catch (SQLException e) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_005, e);
		} finally {
			closeStatement(prepStmt);
		}
		
		return result;
	}

	/**
	 * Retorna o valor do próximo ID da tabela funcionario
	 * 
	 * @return
	 */
	public Long getNextId() {
		
		Long nextId = null;
		Connection con = null;
		Statement seq = null;
		
		try {
			
			con = JDBCUtil.getInstance().getConnection();
			seq = con.createStatement();
			seq.execute("SELECT NEXT VALUE FOR sq_funcionario FROM funcionario");
			
			ResultSet rs = seq.getResultSet();
			rs.next();
			nextId = rs.getLong(0 + 1);
			
		} catch (SQLException e) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_006, e);
		} finally {
			closeStatement(seq);
		}
		
		return nextId;
	}

	/**
	 * Verifica se existe um funcionario na base
	 */
	public boolean exists(Funcionario pojo) {
		return (buscar(pojo) != null);
	}

	/**
	 * Insere um funcionario
	 */
	public Object insert(Funcionario pojo) {
		
		PreparedStatement prepStmt = null;
		Connection con = null;
		
		try {
			
			con = JDBCUtil.getInstance().getConnection();
			String insertStatement = "INSERT INTO funcionario (nome, nascimento, lotacao, cpf) VALUES (?, ?, ?, ?)";
			prepStmt = con.prepareStatement(insertStatement);
			
			prepStmt.setString(1, pojo.getNome());
			if (pojo.getNascimento() != null) {
				prepStmt.setDate(2, (new java.sql.Date(pojo.getNascimento().getTime())));
			} else {
				prepStmt.setDate(2, null);
			}
			prepStmt.setString(3, pojo.getLotacao());
			prepStmt.setString(4, pojo.getCpf());
			prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_001, e);
		} finally {
			closeStatement(prepStmt);
		}
		
		String selectStatement = "SELECT max(id_funcionario) AS id FROM funcionario";
		try {
			
			prepStmt = con.prepareStatement(selectStatement);
			ResultSet registroIncluido = prepStmt.executeQuery();
			if (registroIncluido.next()) {
				pojo.setId(registroIncluido.getLong("id"));
			}
			
		} catch (SQLException e) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_001, e);
		} finally {
			closeStatement(prepStmt);
		}
		
		return pojo;
	}

	/**
	 * Remover um funcionario
	 */
	public void remove(Funcionario pojo) {

		Connection con = null;
		PreparedStatement prepStmt = null;
		
		try {
			
			con = JDBCUtil.getInstance().getConnection();
			String insertStatement = "DELETE FROM funcionario WHERE id_funcionario = ?";
			prepStmt = con.prepareStatement(insertStatement);
			
			prepStmt.setLong(1, pojo.getId());
			prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_003, e);
		} finally {
			closeStatement(prepStmt);
		}
	}

	public void update(Funcionario pojo) {
		
		Connection con = null;
		PreparedStatement prepStmt = null;
		
		try {
			
			con = JDBCUtil.getInstance().getConnection();
			String insertStatement = "UPDATE funcionario SET id_funcionario=?, nome=?, nascimento=?, lotacao=?, cpf=? WHERE id_funcionario = ?";
			prepStmt = con.prepareStatement(insertStatement);
			
			prepStmt.setLong(1, pojo.getId());
			prepStmt.setString(2, pojo.getNome());
			if (pojo.getNascimento() != null) {
				prepStmt.setDate(3, (new java.sql.Date(pojo.getNascimento()
						.getTime())));
			} else {
				prepStmt.setDate(3, null);
			}
			prepStmt.setString(4, pojo.getLotacao());
			prepStmt.setString(5, pojo.getCpf());
			prepStmt.setLong(6, pojo.getId());
			prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_001, e);
		} finally {
			closeStatement(prepStmt);
		}
	}

	/**
	 * Verifica se a conexão ao statemente existe e se está aberto e fecha
	 */
	private void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e1) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_001, e1);
		}
	}

	public Funcionario buscar(Funcionario funcionario) {
		
		PreparedStatement prepStmt = null;
		Connection con = null;
		Funcionario retorno = null;
		
		try {
		
			con = JDBCUtil.getInstance().getConnection();
			prepStmt = con.prepareStatement("SELECT * FROM funcionario WHERE id_funcionario = ?");
			prepStmt.setLong(1, funcionario.getId());
			ResultSet rs = prepStmt.executeQuery();
			
			if (rs.next()) {
				long id = rs.getLong("id_funcionario");
				String nome = rs.getString("nome");
				Date nascimento = rs.getDate("nascimento");
				String lotacao = rs.getString("lotacao");
				String cpf = rs.getString("cpf");
				retorno = new Funcionario(id, nome, nascimento, lotacao, cpf);
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_007, e);
		} finally {
			closeStatement(prepStmt);
		}
		
		return retorno;
	}

	public List<Funcionario> filtrar(Funcionario pojo) {
		
		List<Funcionario> result = new ArrayList<Funcionario>();
		PreparedStatement prepStmt = null;
		Connection con = null;
		
		try {
		
			con = JDBCUtil.getInstance().getConnection();
			prepStmt = con.prepareStatement("SELECT * FROM funcionario WHERE nome like ? ORDER BY nome ASC");
			prepStmt.setString(1, "%" + pojo.getNome() + "%");
			ResultSet rs = prepStmt.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("id_funcionario");
				String nome = rs.getString("nome");
				Date nascimento = rs.getDate("nascimento");
				String lotacao = rs.getString("lotacao");
				String cpf = rs.getString("cpf");
				result.add(new Funcionario(id, nome, nascimento, lotacao, cpf));
			}
			
		} catch (SQLException e) {
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_005, e);
		} finally {
			closeStatement(prepStmt);
		}
		
		return result;
	}

}
