package br.gov.sample.demoiselle.escola.persistence.dao;/* Imports list */

import java.util.List;

import br.gov.framework.demoiselle.core.layer.IDAO;
import br.gov.framework.demoiselle.util.page.Page;
import br.gov.framework.demoiselle.util.page.PagedResult;
import br.gov.sample.demoiselle.escola.bean.Endereco;

public interface IEnderecoDAO extends IDAO<Endereco> {/* @fwk-methods-begin */
	public List<Endereco> listar();

	public PagedResult<Endereco> listar(Page page);

	public List<Endereco> filtrar(Endereco endereco);

	public PagedResult<Endereco> filtrar(Endereco endereco, Page page);

	public Endereco buscar(Endereco endereco);

	/* @fwk-methods-end */
}