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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Email;
import br.gov.demoiselle.escola.bean.Endereco;
import br.gov.demoiselle.escola.bean.Foto;
import br.gov.demoiselle.escola.bean.Telefone;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.bean.Usuario;
import br.gov.demoiselle.escola.business.IAlunoBC;
import br.gov.demoiselle.escola.business.IUsuarioBC;
import br.gov.demoiselle.escola.config.EscolaConfig;
import br.gov.demoiselle.escola.message.ErrorMessage;
import br.gov.demoiselle.escola.message.InfoMessage;
import br.gov.demoiselle.escola.persistence.dao.IAlunoDAO;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.exception.ApplicationRuntimeException;
import br.gov.frameworkdemoiselle.pagination.Page;
import br.gov.frameworkdemoiselle.pagination.PagedResult;

public class AlunoBC implements IAlunoBC {/*@fwk-methods-begin*/
	
	@Inject
	IAlunoDAO alunoDao;	

	@Inject
	IUsuarioBC usuarioBC;	

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void inserir(Aluno aluno, Foto foto) {
		try{
			Usuario usuario = aluno.getUsuario();
			usuarioBC.inserir(usuario);
			aluno.setUsuario(usuario);
			alunoDao.insert(aluno);
			salvarFoto(aluno, foto);
			ContextLocator.getInstance().getMessageContext().addMessage(InfoMessage.ALUNO_INSERIDO_OK);
		}catch(Exception e){
			throw new ApplicationRuntimeException(ErrorMessage.ALUNO_001, e);
		}
	}	

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void remover(Aluno arg0) {
		alunoDao.delete(arg0);
	}

	//TODO @RequiredRole(roles=AliasRole.ROLE_ADMINISTRADOR)
	public void alterar(Aluno aluno, Foto foto) { //TODO throws AuthorizationException{
		salvarFoto(aluno, foto);
		alunoDao.update(aluno);
	}	

	public List<Aluno> listar() {
		return alunoDao.listarAluno();
	}
	
	/**
	 * Lista paginada
	 */
	public PagedResult<Aluno> listar(Page page) {
		return alunoDao.listarAluno(page);
	}
	
	public List<Aluno> filtrar(Aluno arg0) {
		return alunoDao.filtrarAluno(arg0);
	}
	

	public PagedResult<Aluno> filtrar(String nome, Page page) {
		Aluno alunofiltro = new Aluno();
		alunofiltro.setNome(nome);
		return alunoDao.filtrarAluno(alunofiltro, page);
	}
	
	
	public Aluno buscarAlunoUsuario(Usuario usuario){
		return alunoDao.buscarAluno(usuario);
	}
	

	public Aluno buscar(Aluno arg0) {
		return alunoDao.buscarAluno(arg0);
	}/*@fwk-methods-end*/
	
	/*---------------------- ENDERECO ----------------------------------*/
	public Aluno inserirEndereco(Aluno aluno, Endereco detalheEndereco){		
		aluno = buscar(aluno);
		Set<Endereco> listaEndereco = aluno.getEnderecos();
		detalheEndereco.setAluno(aluno);
		listaEndereco.add(detalheEndereco);
		return aluno;
	}	
	
	public Aluno alterarEndereco(Aluno aluno, Endereco detalheEndereco){
		alunoDao.alterarDetalhe(detalheEndereco);
		return buscar(aluno);
	}		
	
	public Aluno removerEndereco(Aluno aluno, Endereco detalheEndereco){		
		aluno = buscar(aluno);
		Set<Endereco> listaEndereco = aluno.getEnderecos();
		listaEndereco.remove(detalheEndereco);
		return aluno;
	}
	
	/*---------------------- EMAIL ----------------------------------*/
	
	public Aluno inserirEmail(Aluno aluno, Email detalheEmail){		
		aluno = buscar(aluno);
		Set<Email> listaEmail = aluno.getEmails();
		detalheEmail.setAluno(aluno);
		listaEmail.add(detalheEmail);
		return aluno;
	}	
	
	public Aluno alterarEmail(Aluno aluno, Email detalheEmail){
		alunoDao.alterarDetalhe(detalheEmail);
		return buscar(aluno);
	}		
	
	public Aluno removerEmail(Aluno aluno, Email detalheEmail){		
		aluno = buscar(aluno);
		Set<Email> listaEmail = aluno.getEmails();
		listaEmail.remove(detalheEmail);
		return aluno;
	}	
	
	
	/*---------------------- TELEFONE ----------------------------------*/
	
	public Aluno inserirTelefone(Aluno aluno, Telefone detalheTelefone){		
		aluno = buscar(aluno);
		Set<Telefone> listaTelefone = aluno.getTelefones();
		detalheTelefone.setAluno(aluno);
		listaTelefone.add(detalheTelefone);
		return aluno;
	}	
	
	public Aluno alterarTelefone(Aluno aluno, Telefone detalheTelefone){
		alunoDao.alterarDetalhe(detalheTelefone);
		return buscar(aluno);
	}		
	
	public Aluno removerTelefone(Aluno aluno, Telefone detalheTelefone){		
		aluno = buscar(aluno);
		Set<Telefone> listaTelefone = aluno.getTelefones();
		listaTelefone.remove(detalheTelefone);
		return aluno;
	}
	
	
	/*---------------------- TURMA ----------------------------------*/
	
	/**
	 * Incluir um aluno a uma turma
	 */
	public Aluno incluirTurma(Aluno aluno, Turma detalheTurma){
		aluno = buscar(aluno);	
		detalheTurma = (new TurmaBC()).buscar(detalheTurma);	
		
		/**Trata lotação da turma*/
		if (detalheTurma.getAlunos().size() == detalheTurma.getLotacao() ){
			throw new ApplicationRuntimeException(ErrorMessage.ALUNO_002_01);
		}
		
		/**Trata inclusão duplicada*/
		if (!aluno.getTurmas().contains(detalheTurma)){
			aluno.getTurmas().add(detalheTurma);	
		}else{			
			throw new ApplicationRuntimeException(ErrorMessage.ALUNO_002_02);
		}
		
		detalheTurma.getAlunos().add(aluno);
//TODO		WebMessageContext.getInstance().addMessage(InfoMessage.ALUNO_MATRICULADO_OK);		
		return aluno;
	}	
	
	public Aluno removerTurma(Aluno aluno, Turma detalheTurma){
		aluno = buscar(aluno);
		detalheTurma = (new TurmaBC()).buscar(detalheTurma);		
		aluno.getTurmas().remove(detalheTurma);
		detalheTurma.getAlunos().remove(aluno);
		return aluno;
	}		
	
	/*---------------------- UPLOAD -----------------------------*/	

	
	public void salvarFoto(Aluno aluno, Foto foto) {
//TODO Salvar foto... sem depender de ServletContext
//		if (foto != null){
//			try {			
//				aluno.setFoto(aluno.getId() + "_" + foto.getNome());
//				FacesContext aFacesContext = FacesContext.getCurrentInstance();
//				ServletContext context = (ServletContext)aFacesContext.getExternalContext().getContext();
//
//				String path = context.getRealPath(EscolaConfig.getInstance().getUploadPath() + aluno.getFoto());
//				
//				File file = new File(path);				
//		        BufferedInputStream bufferedInputStream = new BufferedInputStream(foto.getInputStream());  
//		        FileOutputStream fileOutputStream = new FileOutputStream(file);
//		      try {  
//		          byte[] buffer = new byte[1024];  
//		          int count;  
//		          while ((count = bufferedInputStream.read(buffer)) > 0)  
//		              fileOutputStream.write(buffer, 0, count);  
//		      } finally {  
//		          bufferedInputStream.close();  
//		          fileOutputStream.close();  
//		      }  
//		  } catch (IOException exception) {			  
//		      exception.printStackTrace();
//		  }		
//		}
	}

	public Aluno buscarPorUsuario(Usuario usuario) {
		return alunoDao.buscarPorUsuario(usuario);
	}



	
}//End of class