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
package br.gov.sample.demoiselle.escola.business.implementation;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import br.gov.component.demoiselle.authorization.AuthorizationException;
import br.gov.component.demoiselle.authorization.RequiredRole;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.framework.demoiselle.web.message.WebMessageContext;
import br.gov.sample.demoiselle.escola.bean.Aluno;
import br.gov.sample.demoiselle.escola.bean.Email;
import br.gov.sample.demoiselle.escola.bean.Endereco;
import br.gov.sample.demoiselle.escola.bean.Foto;
import br.gov.sample.demoiselle.escola.bean.Telefone;
import br.gov.sample.demoiselle.escola.bean.Turma;
import br.gov.sample.demoiselle.escola.bean.Usuario;
import br.gov.sample.demoiselle.escola.business.IAlunoBC;
import br.gov.sample.demoiselle.escola.business.ITurmaBC;
import br.gov.sample.demoiselle.escola.config.EscolaConfig;
import br.gov.sample.demoiselle.escola.constant.AliasRole;
import br.gov.sample.demoiselle.escola.message.ErrorMessage;
import br.gov.sample.demoiselle.escola.message.InfoMessage;
import br.gov.sample.demoiselle.escola.persistence.dao.IAlunoDAO;
import br.gov.sample.demoiselle.escola.util.PathUtil;

/**
 * @author CETEC/CTJEE
 */
public class AlunoBC implements IAlunoBC {

	@Inject
	private IAlunoDAO alunoDAO;

	@Inject
	private ITurmaBC turmaBC;

	@Inject
	private EscolaConfig config;

	@RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Aluno aluno, Foto foto) {
		try {
			alunoDAO.insert(aluno);
			salvarFoto(aluno, foto);
			ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ALUNO_INSERIDO_OK);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(ErrorMessage.ALUNO_001, e);
		}
	}

	@RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Aluno aluno) {
		try {
			alunoDAO.insert(aluno);
			ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ALUNO_INSERIDO_OK);
		} catch (Exception e) {
			throw new ApplicationRuntimeException(ErrorMessage.ALUNO_001, e);
		}
	}

	@RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Aluno aluno) {
		alunoDAO.remove(aluno);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ALUNO_EXCLUIDO_OK);
	}

	@RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Aluno aluno, Foto foto) throws AuthorizationException {
		salvarFoto(aluno, foto);
		alunoDAO.update(aluno);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ALUNO_ALTERADO_OK);
	}

	@RequiredRole(roles = AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Aluno aluno) throws AuthorizationException {
		alunoDAO.update(aluno);
		ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ALUNO_ALTERADO_OK);
	}

	public List<Aluno> listar() {
		return alunoDAO.listarAluno();
	}

	public PagedResult<Aluno> listar(Page page) {
		return alunoDAO.listarAluno(page);
	}

	public List<Aluno> filtrar(Aluno aluno) {
		return alunoDAO.filtrarAluno(aluno);
	}

	public PagedResult<Aluno> filtrar(String nome, Page page) {
		Aluno alunofiltro = new Aluno();
		alunofiltro.setNome(nome);
		return alunoDAO.filtrarAluno(alunofiltro, page);
	}

	public Aluno buscarAlunoUsuario(Usuario usuario) {
		return alunoDAO.buscarAluno(usuario);
	}

	public Aluno buscar(Aluno aluno) {
		return alunoDAO.buscarAluno(aluno);
	}

	/*---------------------- ENDERECO ----------------------------------*/
	/**
	 * A inclusão é feita em memória, para que seja persistida quando o objeto
	 * aluno for persistido
	 */
	public Aluno inserirEndereco(Aluno aluno, Endereco detalheEndereco) {
		aluno = buscar(aluno);
		Set<Endereco> listaEndereco = aluno.getEnderecos();
		listaEndereco.add(detalheEndereco);
		aluno.setEnderecos(listaEndereco);
		return aluno;
	}

	/**
	 * No caso de alteração de endereço, há operação de persistência, pois isso
	 * irá afetar os dados no banco.
	 */
	public Aluno alterarEndereco(Aluno aluno, Endereco detalheEndereco) {
		alunoDAO.alterarDetalhe(detalheEndereco);
		return buscar(aluno);
	}

	/**
	 * A remoção é feita em memória, para que quando o objeto aluno for
	 * persistido, a associação seja eliminada.
	 */
	public Aluno removerEndereco(Aluno aluno, Endereco detalheEndereco) {
		aluno = buscar(aluno);
		Set<Endereco> listaEndereco = aluno.getEnderecos();
		listaEndereco.remove(detalheEndereco);
		aluno.setEnderecos(listaEndereco);
		return aluno;
	}

	/*---------------------- EMAIL ----------------------------------*/

	public Aluno inserirEmail(Aluno aluno, Email detalheEmail) {
		aluno = buscar(aluno);
		Set<Email> listaEmail = aluno.getContato().getEmails();
		listaEmail.add(detalheEmail);
		return aluno;
	}

	public Aluno alterarEmail(Aluno aluno, Email detalheEmail) {
		alunoDAO.alterarDetalhe(detalheEmail);
		return buscar(aluno);
	}

	public Aluno removerEmail(Aluno aluno, Email detalheEmail) {
		aluno = buscar(aluno);
		Set<Email> listaEmail = aluno.getContato().getEmails();
		listaEmail.remove(detalheEmail);
		return aluno;
	}

	/*---------------------- TELEFONE ----------------------------------*/

	public Aluno inserirTelefone(Aluno aluno, Telefone detalheTelefone) {
		aluno = buscar(aluno);
		Set<Telefone> listaTelefone = aluno.getContato().getTelefones();
		listaTelefone.add(detalheTelefone);
		return aluno;
	}

	public Aluno alterarTelefone(Aluno aluno, Telefone detalheTelefone) {
		alunoDAO.alterarDetalhe(detalheTelefone);
		return buscar(aluno);
	}

	public Aluno removerTelefone(Aluno aluno, Telefone detalheTelefone) {
		aluno = buscar(aluno);
		Set<Telefone> listaTelefone = aluno.getContato().getTelefones();
		listaTelefone.remove(detalheTelefone);
		return aluno;
	}

	/*---------------------- TURMA ----------------------------------*/

	/**
	 * Incluir um aluno a uma turma
	 */
	public Aluno incluirTurma(Aluno aluno, Turma detalheTurma) {
		aluno = buscar(aluno);
		detalheTurma = turmaBC.buscar(detalheTurma);

		/** Trata lotação da turma */
		if (detalheTurma.getAlunos().size() == detalheTurma.getLotacao()) {
			throw new ApplicationRuntimeException(ErrorMessage.ALUNO_002_01);
		}

		/** Trata inclusão duplicada */
		if (!aluno.getTurmas().contains(detalheTurma)) {
			aluno.getTurmas().add(detalheTurma);
		} else {
			throw new ApplicationRuntimeException(ErrorMessage.ALUNO_002_02);
		}

		detalheTurma.getAlunos().add(aluno);
		WebMessageContext.getInstance().addMessage(InfoMessage.ALUNO_MATRICULADO_OK);
		return aluno;
	}

	public Aluno removerTurma(Aluno aluno, Turma detalheTurma) {
		aluno = buscar(aluno);
		detalheTurma = (new TurmaBC()).buscar(detalheTurma);
		aluno.getTurmas().remove(detalheTurma);
		detalheTurma.getAlunos().remove(aluno);
		return aluno;
	}

	/*---------------------- UPLOAD -----------------------------*/

	public void salvarFoto(Aluno aluno, Foto foto) {
		if (foto != null) {
			try {
				aluno.setFoto(foto.getNome());

				String path = PathUtil.getRealPath(config.getUploadPath() + aluno.getFoto());

				File file = new File(path);
				BufferedInputStream bufferedInputStream = new BufferedInputStream(foto.getInputStream());
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				
				try {
					byte[] buffer = new byte[1024];
					int count = 0;
					while ((count = bufferedInputStream.read(buffer)) > 0) {
						fileOutputStream.write(buffer, 0, count);
					}
				} finally {
					bufferedInputStream.close();
					fileOutputStream.close();
				}
				
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	public Aluno buscarPorUsuario(Usuario usuario) {
		return alunoDAO.buscarAluno(usuario);
	}

}