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
package br.gov.demoiselle.escola.business.implementation;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Disciplina;
import br.gov.demoiselle.escola.bean.Nota;
import br.gov.demoiselle.escola.business.IAlunoBC;
import br.gov.demoiselle.escola.business.IDisciplinaBC;
import br.gov.demoiselle.escola.business.INotaBC;
import br.gov.demoiselle.escola.persistence.dao.INotaDAO;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class NotaBC implements INotaBC {

	private static final Logger log = Logger.getLogger(NotaBC.class);

	@Inject
	private IAlunoBC alunoBC;

	@Inject
	private IDisciplinaBC disciplinaBC;

	@Inject
	private INotaDAO notaBC;

	//TODO @RequiredRole(roles = AliasRole.ROLE_PROFESSOR)
	public void inserir(Nota nota) {
		log.debug("inserir");
		Aluno aluno = alunoBC.buscar(nota.getAluno());
		Disciplina disciplina = disciplinaBC.buscar(nota.getDisciplina());
		nota.setAluno(aluno);
		nota.setDisciplina(disciplina);
		
		// que é isso?!! arquitetura em camadas foi pro ralo com esse código:
//		HibernateUtil.getInstance().getSession().save(nota);
		
		notaBC.insert(nota);
		enviarEmail(nota);
	}

	//TODO @RequiredRole(roles = AliasRole.ROLE_PROFESSOR)
	public void alterar(Nota nota) {
		log.debug("alterar");
		notaBC.update(nota);
	}

	//TODO @RequiredRole(roles = AliasRole.ROLE_PROFESSOR)
	public void remover(Nota nota) {
		log.debug("remover");
		notaBC.remove(nota);
	}

	//TODO @RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public Nota buscar(Nota nota) {
		return notaBC.buscar(nota);
	}

	/**
	 * Lista as nota de maneira paginada
	 */
	public PagedResult<Nota> listar(Page page) {
		return notaBC.listar(page);
	}

	public List<Nota> listar(Aluno nota) {
		return notaBC.listar(nota);
	}

	public List<Nota> listar(Disciplina nota) {
		return notaBC.listar(nota);
	}

	//TODO @RequiredRole(roles = AliasRole.ROLE_PROFESSOR)
	private void enviarEmail(Nota nota) {
		
		// TODO: buscar como fazer o envio de e-mail no Demoiselle 2.0
		
		/*
		try {
			log.debug("enviarEmail");
			MailClient mailClient = new MailClient();
			mailClient.send(new String[] { "vanderson.silva@serpro.gov.br" },
					"Assunto", "Texto");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
}