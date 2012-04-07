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
package br.gov.demoiselle.escola.business;

import java.util.List;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Email;
import br.gov.demoiselle.escola.bean.Endereco;
import br.gov.demoiselle.escola.bean.Foto;
import br.gov.demoiselle.escola.bean.Telefone;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.framework.demoiselle.core.layer.IBusinessController;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;

@BusinessController
public interface IAlunoBC extends IBusinessController {
	
	public void inserir(Aluno aluno, Foto foto);

	public void remover(Aluno aluno);

	public void alterar(Aluno aluno, Foto foto);

	public List<Aluno> listar();
	
	public PagedResult<Aluno> listar(Page page);

	public List<Aluno> filtrar(Aluno aluno);
	
	public PagedResult<Aluno> filtrar(String nome, Page page);

	public Aluno inserirEndereco(Aluno aluno, Endereco detalheEndereco);

	public Aluno alterarEndereco(Aluno aluno, Endereco detalheEndereco);

	public Aluno removerEndereco(Aluno aluno, Endereco detalheEndereco);

	public Aluno inserirEmail(Aluno aluno, Email detalheEmail);

	public Aluno alterarEmail(Aluno aluno, Email detalheEmail);

	public Aluno removerEmail(Aluno aluno, Email detalheEmail);

	public Aluno inserirTelefone(Aluno aluno, Telefone detalheTelefone);

	public Aluno alterarTelefone(Aluno aluno, Telefone detalheTelefone);

	public Aluno removerTelefone(Aluno aluno, Telefone detalheTelefone);

	public Aluno incluirTurma(Aluno aluno, Turma detalheTurma);

	public Aluno removerTurma(Aluno aluno, Turma detalheTurma);

	public Aluno buscar(Aluno aluno);

	public Aluno buscarPorUsuario(Usuario usuario);

}
