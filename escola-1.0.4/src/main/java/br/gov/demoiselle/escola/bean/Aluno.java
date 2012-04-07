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
package br.gov.demoiselle.escola.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="aluno")
public class Aluno{	
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(generator="sq_aluno")
	@Column(name="id_aluno")
	private Long id;
	
	@Column(name="nome", length=100)
	private String nome;
	
	@Column(name="pai", length=100)
	private String pai;
	
	@Column(name="mae", length=100)
	private String mae;
	
	@Column(name="nascimento")
	@Temporal(TemporalType.DATE)
	private Date nascimento;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="usuario")
	private Usuario usuario;
	
	@Column(name="foto", length=100)
	private String foto;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Endereco> enderecos;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Telefone> telefones;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Email> emails;

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<Turma> turmas;
	
	public Aluno() {
		enderecos = new HashSet<Endereco>();
		telefones  = new HashSet<Telefone>();
		emails = new HashSet<Email>();
		turmas = new HashSet<Turma>();
		usuario = new Usuario();
	}

	public Aluno(long arg0) {
		this();
		this.id = arg0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public List<Endereco> getListaEndereco() {
		return new ArrayList<Endereco>(enderecos);
	}
	
	public List<Telefone> getListaTelefone() {		
		return new ArrayList<Telefone>(telefones);
	}	
	
	public List<Email> getListaEmail() {		
		return new ArrayList<Email>(emails);
	}
	
	public List<Turma> getListaTurma() {
		return new ArrayList<Turma>(turmas);
	}
	
	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}	

}
