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
package br.gov.sample.demoiselle.escola.factory;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import br.gov.component.demoiselle.common.pojo.helpers.CEP;
import br.gov.component.demoiselle.common.pojo.helpers.CPF;
import br.gov.sample.demoiselle.escola.bean.Aluno;
import br.gov.sample.demoiselle.escola.bean.Contato;
import br.gov.sample.demoiselle.escola.bean.Disciplina;
import br.gov.sample.demoiselle.escola.bean.Email;
import br.gov.sample.demoiselle.escola.bean.Endereco;
import br.gov.sample.demoiselle.escola.bean.Foto;
import br.gov.sample.demoiselle.escola.bean.Funcionario;
import br.gov.sample.demoiselle.escola.bean.Nota;
import br.gov.sample.demoiselle.escola.bean.PapelUsuario;
import br.gov.sample.demoiselle.escola.bean.Professor;
import br.gov.sample.demoiselle.escola.bean.Telefone;
import br.gov.sample.demoiselle.escola.bean.Turma;
import br.gov.sample.demoiselle.escola.bean.Usuario;

/**
 * Esta classe se destina a prover instâncias com atributos preenchidos para uso
 * nos testes.
 * 
 * @author SERPRO
 */
public class BeanFactory {
	
	private static final String[] ALFABETO = { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z" };

	private static final long MILISSEGUNDOS_EM_30_ANOS = 933120000000l;

	private static final String[] TIPOS_TELEFONE = {
			Telefone.TIPO_CELULAR, Telefone.TIPO_COMERCIAL, Telefone.TIPO_RESIDENCIAL };

	private static final String[] NOMES_MULHERES = {
			"Ana", "Beatriz", "Camila", "Diocléia", "Eloah", "Fernanda", "Giovana", 
			"Helena", "Ivete", "Juliana", "Kátia", "Luciana", "Maria", "Noemi", "Ohana",
			"Patrícia", "Quênia", "Roberta", "Sueli", "Tatiana", "Ursula", "Vânia",
			"Walkiria", "Ximena", "Yara", "Zélia"};
	
	private static final String[] NOMES_HOMENS = {
			"Arthur", "Bernardo", "Carlos", "Dênis", "Emerson", "Fabrício", "Guilherme",
			"Heitor", "Inácio", "Jonas", "Kleber", "Leonardo", "Mário", "Nivaldo", "Otávio",
			"Paulo", "Quasímodo", "Rodrigo", "Saulo", "Túlio", "Umbertus", "Victor",
			"Wagner", "Xen", "Yannick", "Zoroastro"};
	
	private static final String[] SOBRENOMES = {
			"Alves", "Assis", "Born", "Coelho", "Cunha", "Dias", "Duarte", "Farias",
			"Fernandes", "Fonseca", "Gusmão", "Lima", "Lopes", "Maia", "Melo",
			"Mendes", "Miranda", "Oliveira", "Pereira", "Ribeiro", "Rodrigues"};

	private static final String[] MATERIAS = {
			"Português", "Matemática", "Literatura", "Inglês", "Francês", "Espanhol", "Latim",
			"Ciências", "História", "Física", "Química", "Educação Física", "Artes", "Geometria"};

	private static Random random = new Random(System.currentTimeMillis());
	
	/**
	 * Cria um conjunto de endereços fictícios.
	 */
	public static Set<Endereco> createEnderecos() {
		Set<Endereco> enderecos = new HashSet<Endereco>();
		int quantidade = (int) (random.nextInt(2) + 1);
		for (int i = 0; i < quantidade; i++) {
			enderecos.add(createEndereco());
		}
		return enderecos;
	}

	public static int createTipoEndereco() {
		int tipoEndereco = (int) (random.nextInt(2));
		tipoEndereco = tipoEndereco > 0 ? 1 : tipoEndereco;
		return tipoEndereco;
	}

	/**
	 * Cria um conjunto de emails fictícios.
	 */
	public static Set<Email> createEmails() {
		Set<Email> emails = new HashSet<Email>();
		int quantidade = (int) (random.nextInt(3) + 1);
		for (int i = 0; i < quantidade; i++) {
			emails.add(createEmail());
		}
		return emails;
	}

	/**
	 * Cria um endereço fictício de email.
	 * 
	 * @return	String
	 */
	private static String criaEmail() {
		
		String nome = montarPalavra(random.nextInt(15) + 1);
		String servidor = montarPalavra(random.nextInt(10) + 1);
		String tipo = montarPalavra(random.nextInt(3) + 1);
		String local = montarPalavra(2);

		return nome + "@" + servidor + "." + tipo + "." + local;
	}

	/**
	 * Retorna uma string com ordenamento de letras aleatório.
	 * 
	 * @param tamanho
	 */
	private static String montarPalavra(int tamanho) {
		String palavra = "";
		int indice;

		for (int i = 0; i < tamanho; i++) {
			indice = (int) (Math.random() * ALFABETO.length);
			indice = indice >= ALFABETO.length ? ALFABETO.length - 1 : indice;
			palavra = palavra + ALFABETO[indice];
		}
		return palavra;
	}

	private static String montarNumero(int tamanho) {
		String numero = "";
		int indice;
		for (int i = 0; i < tamanho; i++) {
			indice = (int) (Math.random() * 10);
			numero = numero + indice;
		}
		return numero;
	}

	public static Foto createFoto() {
		// verifique no demoiselle.properties o caminho de upload
		// e se o arquivo de imagem está nele.
		Foto foto = new Foto("foto_generica.png", BeanFactory.createInputStream());
		return foto;
	}

	public static InputStream createInputStream() {
		InputStream inputStream = System.in;
		return inputStream;
	}

	/**
	 * Gera uma string aleatoriamente.
	 * 
	 * @return String
	 */
	public static String createNome() {
		return montarPalavra(10) + " " + montarPalavra(10) + " " +
				montarPalavra(2) + " " + montarPalavra(10);
	}

	/**
	 * Gera um nome completo aleatoriamente.
	 * 
	 * @return String
	 */
	public static String createNomeCompleto() {
		
		String nome = null;
		if (random.nextBoolean()) {
			nome = NOMES_MULHERES[random.nextInt(NOMES_MULHERES.length)];
		} else {
			nome = NOMES_HOMENS[random.nextInt(NOMES_HOMENS.length)];
		}
		
		String sobrenome = createSobrenome();
		
		return nome + " " + sobrenome;
	}

	/**
	 * Gera aleatoriamente um sobrenome.
	 * 
	 * @return String
	 */
	private static String createSobrenome() {
		
		String sobrenome =
			SOBRENOMES[random.nextInt(SOBRENOMES.length)] + " " +
			SOBRENOMES[random.nextInt(SOBRENOMES.length)];
		
		return sobrenome;
	}

	/**
	 * Cria uma data aleatória.
	 * 
	 * @return	Date
	 */
	public static Date createData() {
		
		Date date = new Date();
		long milissegundos = (long) Math.random() * MILISSEGUNDOS_EM_30_ANOS;
		date.setTime(milissegundos);
		
		return date;
	}

	/**
	 * Retorna uma data aleatória respeitando os intervalos definidos.
	 * 
	 * @param idadeMinima	menor idade
	 * @param idadeMaxima	maior idade
	 * @return	Date
	 */
	public static Date createData(int idadeMinima, int idadeMaxima) {
		
		Calendar cal = Calendar.getInstance();
		int atual = cal.get(Calendar.YEAR);
		
		// data mínima
		cal.set(atual - idadeMinima, 0, 1);
		long min = cal.getTimeInMillis();
		
		// data máxima
		long max = 0;
		if (idadeMaxima > 0) {
			cal.set(atual - idadeMaxima, 11, 31);
			max = cal.getTimeInMillis();
		} else {
			max = Calendar.getInstance().getTimeInMillis();
		}
		
		// sorteio da data
		long millis = (long) (max + (Math.random() * (min - max)));
		cal.setTimeInMillis(millis);
		
		return cal.getTime();
	}
	
	/**
	 * Retorna uma data no passado até o dia atual.
	 * 
	 * @param idadeMinima	menor idade
	 * @return	Date
	 */
	public static Date createData(int idadeMinima) {
		return createData(idadeMinima, 0);
	}
	
	/**
	 * Cria um conjunto de telefones fictícios
	 */
	public static Set<Telefone> createTelefones() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		int quantidade = (int) (random.nextInt(3));
		for (int i = 0; i < quantidade; i++) {
			telefones.add(createTelefone());
		}
		return telefones;
	}

	public static int createTipoTelefone() {
		return (int) Math.random() * 3;
	}

	/**
	 * Cria uma instância de aluno com todos os atributos configurados com dados
	 * fictícios.
	 */
	public static Aluno createAluno() {
		Aluno aluno = new Aluno();

		aluno.setCpf(BeanFactory.createCPF());
		aluno.setEnderecos(BeanFactory.createEnderecos());
		aluno.setFoto(BeanFactory.createFoto().getNome());
		aluno.setNascimento(BeanFactory.createData(10, 15));
		aluno.setContato(createContato());

		boolean mulher = random.nextBoolean();
		String nome = null;
		if (mulher) {
			nome = NOMES_MULHERES[random.nextInt(NOMES_MULHERES.length)];
		} else {
			nome = NOMES_HOMENS[random.nextInt(NOMES_HOMENS.length)];
		}

		String sobrenome = createSobrenome();
		int ipai = random.nextInt(NOMES_HOMENS.length);
		int imae = random.nextInt(NOMES_MULHERES.length);
		
		aluno.setNome(nome + " " + sobrenome);
		aluno.setMae(NOMES_MULHERES[imae] + " " + sobrenome);
		aluno.setPai(NOMES_HOMENS[ipai] + " " + sobrenome);

		return aluno;
	}

	/**
	 * Gera um número CPF aleatório sem verificação dos dígitos.
	 * 
	 * @return	String
	 */
	private static String createCPF() {
		long num = (long) (random.nextDouble() * 1E11);
		String cpf = String.valueOf(num);
		while (cpf.length() < 11) {
			cpf = "0".concat(cpf);
		}
		cpf = CPF.formatCPF(cpf);
		return cpf;
	}

	/**
	 * Cria uma instância de disciplina com todos os atributos configurados com
	 * dados fictícios.
	 */
	public static Disciplina createDisciplina() {
		Disciplina disciplina = new Disciplina();
		disciplina.setNome(createNomeDisciplina());
		return disciplina;
	}

	/**
	 * Retorna o nome de uma disciplina aleatoriamente.
	 * 
	 * @return String
	 */
	private static String createNomeDisciplina() {
		int ii = random.nextInt(MATERIAS.length);
		String[] ORDEM = {"", " I", " II", " III", " IV"};
		String sufixo = ORDEM[random.nextInt(ORDEM.length)];
		return MATERIAS[ii] + sufixo;
	}

	/**
	 * Cria uma instância de funcionario com todos os atributos configurados com
	 * dados fictícios.
	 */
	public static Funcionario createFuncionario() {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(createNomeCompleto());
		funcionario.setNascimento(createData(30, 60));
		funcionario.setLotacao(createNome().toUpperCase());
		funcionario.setContato(createContato());
		funcionario.setCpf(createCPF());
		return funcionario;
	}

	public static Contato createContato() {
		Contato contato = new Contato();
		contato.setTelefones(createTelefones());
		contato.setEmails(createEmails());
		return contato;
	}

	public static Nota createNota() {
		Nota nota = new Nota();
		nota.setValor(createFloatValue());
		nota.setDisciplina(createDisciplina());
		nota.setDataRegistro(createData(1));
		nota.setAluno(createAluno());
		return nota;
	}

	/**
	 * Retorna um número entre os valores mínimo e máximo indicados.
	 * 
	 * @param min
	 * @param max
	 * @return float
	 */
	public static Float createFloatValue(float min, float max) {
		return (min + random.nextFloat() * (max - min));
	}

	/**
	 * Retorna um número entre 0,0 e o valor máximo indicado.
	 * 
	 * @param max
	 * @return float
	 */
	public static Float createFloatValue(float max) {
		return createFloatValue(0f, max);
	}

	/**
	 * Retorna um número entre 0,0 e 10,0.
	 * 
	 * @return float
	 */
	public static Float createFloatValue() {
		return createFloatValue(0f);
	}

	/**
	 * Cria uma instância de professor com todos os atributos configurados com
	 * dados fictícios
	 */
	public static Professor createProfessor() {
		Professor professor = new Professor();
		professor.setNome(createNomeCompleto());
		professor.setContato(createContato());
		professor.setCpf(createCPF());
		return professor;
	}

	public static Turma createTurma() {
		Turma turma = new Turma();
		turma.setProfessor(createProfessor());
		turma.setNome(createNomeTurma());
		turma.setLotacao(new Long((random.nextInt(5) + 2) * 10));
		turma.setLocal(createNomeSala());
		turma.setHorario(createHoraMinuto());
		turma.setDisciplina(createDisciplina());
		turma.setAlunos(createAlunos());
		return turma;
	}

	/**
	 * Retorna o nome de uma turma aleatoriamente.
	 * 
	 * @return String
	 */
	private static String createNomeTurma() {
		int ano = random.nextInt(8) + 1;
		String LETRAS = "ABCDE";
		char sufixo = LETRAS.charAt(random.nextInt(LETRAS.length()));
		return ano + "\u00BA " + sufixo;
	}

	/**
	 * Retorna o nome de uma turma aleatoriamente.
	 * 
	 * @return String
	 */
	private static String createNomeSala() {
		int num = (random.nextInt(30) + 1) * 10;
		return "Sala " + num;
	}
	
	private static Set<Aluno> createAlunos() {
		Set<Aluno> alunos = new HashSet<Aluno>();
		int quantidade = ((int) Math.random() * 40);
		for (int i = 0; i < quantidade; i++) {
			alunos.add(createAluno());
		}
		return alunos;
	}

	private static String createHoraMinuto() {
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(random.nextLong());
		
		DateFormat sf = new SimpleDateFormat("hh:mm");
		String retorno = sf.format(cal.getTime()).concat(":00");
		
		return retorno;
	}

	private static Long createLongValue() {
		return new Long(random.nextInt(100) + 1);
	}

	/**
	 * Cria uma instância de usuario com todos os atributos configurados com
	 * dados fictícios.
	 */
	public static Usuario createUsuario() {
		Usuario usuario = new Usuario();
		usuario.setCpf(montarNumero(11));
		usuario.setLogin(createNome());
		usuario.setNome(createNomeCompleto());
		usuario.setPapeis(createPapeis());
		return usuario;
	}

	private static Set<PapelUsuario> createPapeis() {
		Set<PapelUsuario> papeis = new HashSet<PapelUsuario>();
		int quantidade = ((int) Math.random() * 4);
		for (int i = 0; i < quantidade; i++) {
			papeis.add(createPapelUsuario());
		}
		return null;
	}

	private static PapelUsuario createPapelUsuario() {
		PapelUsuario papel = new PapelUsuario();
		papel.setPapel(createNome());
		return papel;
	}

	public static Endereco createEndereco() {
		String cep = CEP.formatCEP(montarNumero(8));
		return new Endereco(montarPalavra(30), montarNumero(3), montarPalavra(10),
				montarPalavra(20), cep, montarPalavra(30), createTipoEndereco());
	}

	public static Email createEmail() {
		return new Email(criaEmail());
	}

	public static Telefone createTelefone() {
		return new Telefone(montarNumero(8), TIPOS_TELEFONE[createTipoTelefone()]);
	}

	public static PapelUsuario createPapel() {
		PapelUsuario papel = new PapelUsuario();
		papel.setPapel(BeanFactory.createNome());
		return papel;
	}

	public static String createNumero() {
		return createLongValue().toString();
	}

}
