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
import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="turma")
public class Turma {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(generator="sq_turma")
	@Column(name="id_turma")
	private Long id;
	
	@Column(name="nome", length=100)
	private String nome;
	
	@Column(name="local", length=100)
	private String local;
	
	@Column(name="horario", length=100)
	private String horario;
	
	@Column(name="lotacao")
	private Long lotacao;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="professor")
	private Professor professor;

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="disciplina")
	private Disciplina disciplina;
	
	@Transient
	private Set<Aluno> alunos;

	public Turma() {		
		professor = new Professor();
		disciplina = new Disciplina();
		alunos = new HashSet<Aluno>();
	}	

	public Turma(long arg0) {
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public List<Aluno> getListaAluno() {		
		List<Aluno> lista = new ArrayList<Aluno>();
		Iterator<Aluno> it = alunos.iterator();
		while (it.hasNext()) 
			lista.add((Aluno)it.next());		
		return lista;
	}

	public Long getLotacao() {
		return (lotacao == null) ? 0 : lotacao;
	}

	public void setLotacao(Long lotacao) {
		this.lotacao = lotacao;
	}
	
	public String getLotacaoatual(){
		return getAlunos().size() + "/" + getLotacao();
	}

}
