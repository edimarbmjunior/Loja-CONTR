package com.edimar.loja.service.validator;

import com.edimar.loja.model.dto.ClienteBO;
import com.edimar.loja.services.Util.CalculoFrete;
import com.edimar.loja.services.exceptions.GenericExcpetion;

public class ValidacaoCliente {
	
	public static void validacaoConsultar(Integer id) {
		isIdentificadorValido(id);
	}
	
	public static void validacaoAtualizar(ClienteBO clienteBO) {
		isIdentificadorValido(clienteBO.getId());
		validacaoNome(clienteBO.getNome());
		validaCPF(clienteBO.getCpf());
		validaEndereco(clienteBO.getEndereco());
		validaCep(clienteBO.getCep());
		validaUF(clienteBO.getUf());
		validaBairro(clienteBO.getBairro());
		validaCidade(clienteBO.getCidade());
		validaTelefeone(clienteBO.getTelefeone());
	}
	
	private static void isIdentificadorValido(Integer id) {
		if(null==id
				|| id <= 0) {
			throw new GenericExcpetion("Identificador enviado é inválido!");
		}
	}
	
	private static void validacaoNome(String nome) {
		if(nome != null && nome.length() <= 5) {
			throw new GenericExcpetion("Nome é inválido. Quantidade mínima de carateres é 6!");
		}
	}
	
	private static void validaCPF(String cpf) {
		if(cpf != null && !ValidaCPF.isCPF(cpf)) {
			throw new GenericExcpetion("CPF é inválido!");
		}
	}
	
	private static void validaEndereco(String endereco) {
		if(endereco != null && endereco.length() <= 10) {
			throw new GenericExcpetion("Endereço é inválido. Quantidade mínima de carateres é 11!");
		}
	}
	
	private static void validaCep(Integer cep) {
		if(cep != null && cep.toString().length() != 8) {
			throw new GenericExcpetion("CEP é inválido!");
		}
	}
	
	private static void validaBairro(String bairro) {
		if(bairro != null && bairro.length() <= 5) {
			throw new GenericExcpetion("Bairro é inválido. Quantidade mínima de carateres é 6!");
		}
	}
	
	private static void validaCidade(String cidade) {
		if(cidade != null && cidade.length() <= 5) {
			throw new GenericExcpetion("Cidade é inválida. Quantidade mínima de carateres é 6!");
		}
	}
	
	private static void validaUF(String uf) {
		if(uf != null && uf.length() != 2) {
			throw new GenericExcpetion("UF é inválido. Quantidade mínima de carateres é 6!");
		}
		Boolean existe = true;
		for (CalculoFrete unidade : CalculoFrete.values()) {
			if(unidade.getUf().equalsIgnoreCase(uf)) {
				existe = false;
			}
		}
		if(existe) {
			throw new GenericExcpetion("UF é inválido.");
		}
	}
	
	private static void validaTelefeone(String telefone) {
		if(telefone != null && (telefone.length() != 10 && telefone.length() != 11)) {
			throw new GenericExcpetion("Telefeone é inválido!");
		}
	}
	
	private static boolean isInteiro(String s) {
		// cria um array de char
		char[] c = s.toCharArray();
		boolean d = true;

		for ( int i = 0; i < c.length; i++ ) {
		    // verifica se o char não é um dígito
		    if ( !Character.isDigit( c[ i ] ) ) {
		        d = false;
		        break;
		    }
		}

		return d;
	}
}
