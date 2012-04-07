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
package br.gov.demoiselle.escola.business.implementation;/* Imports list */

import java.util.List;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Funcionario;
import br.gov.demoiselle.escola.business.IFuncionarioBC;
import br.gov.demoiselle.escola.constant.AliasRole;
import br.gov.demoiselle.escola.factory.EscolaDAOFactory;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.persistence.dao.IFuncionarioDAO;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.framework.demoiselle.core.layer.integration.Factory;

@Factory(factory=EscolaDAOFactory.class)
public class FuncionarioBC implements IFuncionarioBC {
	
	@Inject
	IFuncionarioDAO acessoDao;	

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Funcionario acesso) {
		try{
			acessoDao.insert(acesso);
		}catch(Exception e){
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_001, e);
		}	
	}	
	
	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Funcionario funcionario) {
		try{
			acessoDao.update(funcionario);
		}catch(Exception e){
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_001, e);
		}
	}	
	
	
	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public Funcionario buscar(Funcionario funcionario) {
		try{
			return acessoDao.buscar(funcionario);
		}catch(Exception e){
			throw new ApplicationRuntimeException(ErrorMessage.FUNCIONARIO_001, e);
		}
	}	

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Funcionario acesso) {
		acessoDao.remove(acesso);
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public List<Funcionario> listar() {
		return acessoDao.listar();
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public List<Funcionario> filtrar(Funcionario pojo) {		
		return acessoDao.filtrar(pojo);
	}
	
}