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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="endereco")
public class Endereco{
	
	private static final long serialVersionUID = 1L;
	
	public static final Integer TIPO_RESIDENCIAL = 0;
	public static final Integer TIPO_COMERCIAL = 1;
	
//	@EqualsField
	@Id @GeneratedValue(generator="sq_endereco")
	@Column(name="id_endereco")
	private Long id;
	
//	@EqualsField
//	@Description(methods={OutputType.TO_STRING, OutputType.TO_LOG})
//	TODO Criar tratamento semelhante
	@Column(name="logradouro", length=100)
	private String logradouro;
	
//	@Description(methods={OutputType.TO_STRING})
	@Column(name="numero", length=100)
	private String numero;
	
//	@Description
	@Column(name="complemento", length=100)
	private String complemento;
	
//	@Description
	@Column(name="bairro", length=100)
	private String bairro;
	
//	@Description
	@Column(name="cep", length=100)
	private String cep;
	
	@Column(name="municipio", length=100)
	private String municipio;
	
//	@Description
	@Column(name="tipo")
	private Integer tipo;
	
//	@Description
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="aluno")
	private Aluno aluno;
	
	public Endereco() {}

	
	
	public Endereco(String logradouro, String numero, String complemento,
			String bairro, String cep, String Municipio, Integer tipo) {
		super();
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.municipio = Municipio;
		this.tipo = tipo;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String Municipio) {
		this.municipio = Municipio;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}	
}
