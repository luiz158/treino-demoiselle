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
package br.gov.demoiselle.escola.ui.report;/* Imports list */

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.gov.demoiselle.escola.bean.Aluno;
import br.gov.demoiselle.escola.bean.Professor;
import br.gov.demoiselle.escola.bean.Turma;
import br.gov.demoiselle.escola.business.IAlunoBC;
import br.gov.demoiselle.escola.business.IProfessorBC;
import br.gov.demoiselle.escola.business.ITurmaBC;
import br.gov.demoiselle.escola.business.implementation.AlunoBC;
import br.gov.framework.demoiselle.core.context.ContextLocator;

/**
 * Relatório de Alunos
 * @author 03397040477
 *
 */
public class ReportEscola implements IReport  {
	
	@Inject
	private IAlunoBC alunoBC;
	
	@Inject
	private IProfessorBC listaProfessorBC;
	
	@Inject
	private ITurmaBC turmaBC;	
		
	@Report(name="relatorio_aluno_pdf", reportPath="/jasper/ReportAluno.jasper")
	public List<Aluno> getAlunosPDF() {	
		return alunoBC.listar();
	}	
	
	@Report(name="relatorio_aluno_odt", reportPath="/jasper/ReportAluno.jasper", contentType=ReportType.ODT)
	public Collection<Aluno> getAlunosODT() {	
		return alunoBC.listar();
	}
	
	// RELATÓRIO DE PROFESSOR
	
	@Report(name="relatorio_professor_pdf", reportPath="/jasper/ReportProfessor.jasper")
	public Collection<Professor> getProfessoresPDF() {		
		return listaProfessorBC.listar();
	}
	
	@Report(name="relatorio_professor_odt", reportPath="/jasper/ReportProfessor.jasper")
	public Collection<Professor> getProfessoresODT() {		
		return getProfessoresPDF();
	}
	
	
	// RELATÓRIO DE TURMA
	@Report(name="relatorio_turma_pdf", reportPath="/jasper/ReportTurma.jasper")
	public Collection<Turma> getTurmasPDF() {
		return turmaBC.listar();
	}

	@Report(name="relatorio_turma_odt", reportPath="/jasper/ReportTurma.jasper")
	public List<Turma> getTurmasODT() {
		return turmaBC.listar();
	}	
	
	// PARAMETROS
	public Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("USER_NAME", ContextLocator.getInstance().getSecurityContext().getUserPrincipal().getName());	
		return params;
	}
	
	/** Teste */	
	public static void main(String[] args){		
		try {			
			String SUBREPORT_DIR = ReportEscola.class.getClassLoader().getResource("jasper/").toString();
			HashMap<String, String> parameters = new HashMap<String, String>();
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);
			InputStream jasperStream = ReportEscola.class.getClassLoader().getResourceAsStream("jasper/ReportAluno.jasper");
			WebTransactionContext.getInstance().init();
			List<Aluno> dataSource = new AlunoBC().listar();				
			JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperStream, parameters, new JRBeanCollectionDataSource(dataSource));
			JasperViewer.viewReport(jasperPrint, true);
			WebTransactionContext.getInstance().end();
		} catch (Exception e) {
			WebTransactionContext.getInstance().end();
			e.printStackTrace();
		}		
	}
	
}// End of class
