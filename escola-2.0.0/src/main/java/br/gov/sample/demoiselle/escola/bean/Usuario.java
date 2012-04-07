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
package br.gov.sample.demoiselle.escola.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.framework.demoiselle.core.bean.IPojo;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "sq_usuario", sequenceName = "sq_usuario")
public class Usuario implements IPojo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_usuario")
	@Column(name = "id_usuario")
	private Long id;

	@Column(length = 50)
	private String nome;

	@Column(length = 11)
	private String cpf;

	@Column(length = 10)
	private String login;

	@Column(length = 10)
	private String senha;

	@Column(length = 50)
	private String skin;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PapelUsuario> papeis;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Usuario() {
		super();
	}

	public Usuario(String cpf) {
		this.cpf = cpf;
	}

	public Usuario(String nome, String login, String senha, String skin) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.skin = skin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<PapelUsuario> getPapeis() {
		return papeis;
	}

	public void setPapeis(Set<PapelUsuario> papeis) {
		this.papeis = papeis;
	}

	public List<PapelUsuario> getListaPapeis() {
		return new ArrayList<PapelUsuario>(papeis);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", nome=" + nome + "]";
	}
	
}
