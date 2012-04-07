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
package br.gov.sample.demoiselle.escola.init;

import static br.gov.frameworkdemoiselle.annotation.Startup.MIN_PRIORITY;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;

import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.stereotype.Facade;
import br.gov.frameworkdemoiselle.util.ResourceBundle;
import br.gov.sample.demoiselle.escola.bean.Aluno;
import br.gov.sample.demoiselle.escola.bean.Disciplina;
import br.gov.sample.demoiselle.escola.bean.Funcionario;
import br.gov.sample.demoiselle.escola.bean.Nota;
import br.gov.sample.demoiselle.escola.bean.Professor;
import br.gov.sample.demoiselle.escola.bean.Turma;
import br.gov.sample.demoiselle.escola.factory.BeanFactory;
import br.gov.sample.demoiselle.escola.persistence.dao.IAlunoDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.IDisciplinaDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.IFuncionarioDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.INotaDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.IProfessorDAO;
import br.gov.sample.demoiselle.escola.persistence.dao.ITurmaDAO;

/**
 * @author SERPRO/CETEC/CTCTA
 */
@Facade
public class DataSampleLoader {

	@Inject
	private Logger log;

	@Inject
	private ResourceBundle bundle;

	@Inject
	private IAlunoDAO alunoDAO;
	
	@Inject
	private IDisciplinaDAO disciplinaDAO;

	@Inject
	private IFuncionarioDAO funcionarioDAO;
	
	@Inject
	private IProfessorDAO professorDAO;
	
	@Inject
	private ITurmaDAO turmaDAO;

	@Inject
	private INotaDAO notaDAO;
	
	private void popularTabelas() {
		try {
		
			boolean loaded = false;
			WebTransactionContext.getInstance().init();
			if (!this.verificarDados()) {
				log.debug(bundle.getString("sample-loader-loading-data"));
				loaded = this.incluirDados();
			}
			WebTransactionContext.getInstance().end();
			
			if (loaded) {
				log.debug(bundle.getString("sample-loader-data-loaded"));
			}
			
		} catch (Exception e) {
			log.error(bundle.getString("sample-loader-error-loading", e.getMessage()));
		}
	} 

	/**
	 * Verifica se a base de dados já está populada.
	 * 
	 * @return	boolean
	 */
	private boolean verificarDados() {
		List<Turma> lista = turmaDAO.listar();
		return (lista != null && !lista.isEmpty());
	}

	/**
	 * Efetua a inclusão dos registros no banco de dados.
	 * 
	 * @return	boolean
	 */
	private boolean incluirDados() {

		final int QTDE_FUNCIONARIOS = 25;
		final int QTDE_ALUNOS = 100;
		final int QTDE_TURMAS = 15;
		
		Set<Turma> turmas = new HashSet<Turma>();

		// incluir funcionários
		Funcionario funcionario = null;
		for (int i = 0; i < QTDE_FUNCIONARIOS; i++) {
			funcionario = BeanFactory.createFuncionario();
			funcionarioDAO.insert(funcionario);
		}
		
		// incluir alunos
		Aluno aluno = null;
		for (int i = 0; i < QTDE_ALUNOS; i++) {
			aluno = BeanFactory.createAluno();
			alunoDAO.insert(aluno);
		}

		// reordenar lista de alunos
		List<Aluno> alunos = alunoDAO.listarAluno();
		Collections.sort(alunos, new Comparator<Aluno>() {
			public int compare(Aluno a1, Aluno a2) {
				return a1.getId().compareTo(a2.getId());
			}
		});
		Iterator<Aluno> iterAlunos = alunos.iterator();
		
		// incluir turmas (cada uma com um professor e uma disciplina)
		Turma turma;
		Professor professor;
		Disciplina disciplina;
		for (int i = 0; i < QTDE_TURMAS; i++) {
			turma = BeanFactory.createTurma();
			
			// matricular alguns alunos na turma de acordo com a lotação máxima
			int ocupacao = (int) (turma.getLotacao() - 10 + Math.random() * 5);
			for (int j = 0; j < ocupacao; j++) {
				if (!iterAlunos.hasNext()) {
					iterAlunos = alunos.iterator();
				}
				aluno = iterAlunos.next();
				turma.getAlunos().add(aluno);
				aluno.getTurmas().add(turma);
			}
			
			// incluir professor
			professor = BeanFactory.createProfessor();
			professorDAO.insert(professor);
			turma.setProfessor(professor);

			// incluir disciplina
			disciplina = BeanFactory.createDisciplina();
			disciplinaDAO.insert(disciplina);
			turma.setDisciplina(disciplina);

			// incluir turma
			turmaDAO.insert(turma);
			turmas.add(turma);
			
			// lançar notas dos alunos na turma
			Iterator<Aluno> it = turma.getAlunos().iterator();
			while (it.hasNext()) {
				Aluno al = (Aluno) it.next();
				Nota nota = new Nota();
				nota.setAluno(al);
				nota.setDisciplina(disciplina);
				nota.setDataRegistro(BeanFactory.createData());
				nota.setFrequencia(BeanFactory.createFloatValue(70f, 100f));
				nota.setMedia(BeanFactory.createFloatValue(10f));
				nota.setValor(nota.getMedia());
				notaDAO.insert(nota);
			}
		}
		
		return true;
	}
	
	@Startup(priority = MIN_PRIORITY)
	public void execute() {
		popularTabelas();
	}

	public static void main(String[] args) {
		DataSampleLoader loader = new DataSampleLoader();
		loader.popularTabelas();
	}

}
