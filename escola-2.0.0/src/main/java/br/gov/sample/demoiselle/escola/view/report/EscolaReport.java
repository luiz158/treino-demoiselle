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
 
package br.gov.sample.demoiselle.escola.view.report;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import br.gov.component.demoiselle.report.IReport;
import br.gov.component.demoiselle.report.Report;
import br.gov.component.demoiselle.report.ReportType;
import br.gov.framework.demoiselle.core.context.ContextLocator;
import br.gov.framework.demoiselle.core.layer.integration.Injection;
import br.gov.framework.demoiselle.web.transaction.WebTransactionContext;
import br.gov.sample.demoiselle.escola.bean.Aluno;
import br.gov.sample.demoiselle.escola.bean.Professor;
import br.gov.sample.demoiselle.escola.bean.Turma;
import br.gov.sample.demoiselle.escola.business.IAlunoBC;
import br.gov.sample.demoiselle.escola.business.IProfessorBC;
import br.gov.sample.demoiselle.escola.business.ITurmaBC;
import br.gov.sample.demoiselle.escola.business.implementation.AlunoBC;

*//**
 * @author SERPRO/CETEC/CTJEE
 *//*
public class EscolaReport implements IReport {

	private static final String REPORT_PATH_ALUNO     = "/jasperreports/ReportAluno.jasper";
	private static final String REPORT_PATH_PROFESSOR = "/jasperreports/ReportProfessor.jasper";
	private static final String REPORT_PATH_TURMA     = "/jasperreports/ReportTurma.jasper";
	
	@Injection
	private IAlunoBC alunoBC;

	@Injection
	private IProfessorBC professorBC;

	@Injection
	private ITurmaBC turmaBC;

	@Report(name = "relatorio_aluno_pdf", reportPath = REPORT_PATH_ALUNO)
	public List<Aluno> getAlunosPDF() {
		return alunoBC.listar();
	}

	@Report(name = "relatorio_aluno_odt", reportPath = REPORT_PATH_ALUNO, contentType = ReportType.ODT)
	public List<Aluno> getAlunosODT() {
		return alunoBC.listar();
	}

	@Report(name = "relatorio_professor_pdf", reportPath = REPORT_PATH_PROFESSOR)
	public List<Professor> getProfessoresPDF() {
		return professorBC.listar();
	}

	@Report(name = "relatorio_professor_odt", reportPath = REPORT_PATH_PROFESSOR, contentType = ReportType.ODT)
	public List<Professor> getProfessoresODT() {
		return professorBC.listar();
	}

	@Report(name = "relatorio_turma_pdf", reportPath = REPORT_PATH_TURMA)
	public List<Turma> getTurmasPDF() {
		return turmaBC.listar();
	}

	@Report(name = "relatorio_turma_odt", reportPath = REPORT_PATH_TURMA, contentType = ReportType.ODT)
	public List<Turma> getTurmasODT() {
		return turmaBC.listar();
	}

	public Map<String, String> getParameters() {
		
		String usuario = ContextLocator.getInstance()
			.getSecurityContext().getUserPrincipal().getName();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("USER_NAME", usuario);
		
		return params;
	}

	// FIXME: remover o método inteiro abaixo
	public static void main(String[] args) {
		try {
			
			String SUBREPORT_DIR = EscolaReport.class.getClassLoader()
					.getResource("jasperreports/").toString();
			HashMap<String, String> parameters = new HashMap<String, String>();
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);
			
			InputStream jasperStream = EscolaReport.class.getClassLoader()
					.getResourceAsStream("jasperreports/ReportAluno.jasper");
			WebTransactionContext.getInstance().init();
			
			List<Aluno> dataSource = new AlunoBC().listar();
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperStream, parameters, new JRBeanCollectionDataSource(
							dataSource));
			JasperViewer.viewReport(jasperPrint, true);
			
			WebTransactionContext.getInstance().end();
		} catch (Exception e) {
			WebTransactionContext.getInstance().end();
			e.printStackTrace();
		}
	}

}
*/