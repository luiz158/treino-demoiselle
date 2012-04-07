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

/**
 * Classe para agrupar os contatos telefônicos das entidades. Ela serve a
 * {@link Aluno}, {@link Funcionario} e {@link Professor}.
 * 
 * @author SERPRO/CETEC/CTCTA
 */
@Entity
@Table(name = "contato")
@SequenceGenerator(name = "sq_contato", sequenceName = "sq_contato")
public class Contato implements IPojo {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sq_contato")
	@Column(name = "id", nullable = false)
	protected Long id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	protected Set<Telefone> telefones;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Email> emails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Telefone> getListaTelefone() {
		return new ArrayList<Telefone>(telefones);
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public List<Email> getListaEmail() {
		return new ArrayList<Email>(emails);
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", telefones=" + telefones + "]";
	}
	
}
