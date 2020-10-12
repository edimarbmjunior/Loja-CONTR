package com.edimar.loja.services.Util;

public enum CalculoFrete {
	
	ACRE				("AC", "Acre", 69900000, 69999999, 62.75d),
	ALAGOAS				("AL", "Alagoas", 57000000, 57999999, 5.21d),
	AMAZONAS_PARTE1		("AM", "Amazonas", 69000000, 69299999, 53.74D),
	AMAZONAS_PARTE2		("AM", "Amazonas", 69400000, 69899999, 53.74D),
	AMAPA				("AP", "Amapá", 68900000, 68999999, 44.12d),
	BAHIA				("BA", "Bahia", 40000000, 48999999, 24.36d),
	CEARA				("CE", "Ceará", 60000000, 63999999, 33.52d),
	BRASILIA_PARTE1		("DF", "Brasília", 70000000, 72799999, 19.54d),
	BRASILIA_PARTE2		("DF", "Brasília", 73000000, 73699999, 19.54d),
	ESPIRITO_SANTO		("ES", "Espírito Santo", 29000000, 29999999, 47.96d),
	GOAIS_PARTE1		("GO", "Goiás", 69300000, 69399999, 19.89d),
	GOAIS_PARTE2		("GO", "Goiás", 73700000, 76799999, 19.89d),
	MARANHAO			("MA", "Maranhão", 65000000, 65999999, 16.57d),
	MINAS_GERAIS		("MG", "Minas Gerais", 30000000, 39999999, 0d),
	MATO_GROSSO_SUL		("MS", "Mato Grosso do Sul", 79000000, 79999999, 8.95d),
	MATO_GROSSO			("MT", "Mato rosso", 78000000, 78899999, 14.59d),
	PARA				("PA", "Pará", 66000000, 68899999, 52.41d),
	PARAIBA				("PB", "Paríba", 58000000, 58999999, 19.84d),
	PERNAMBUCO			("PE", "Pernambuco", 50000000, 56999999, 42.76d),
	PIAUI				("PI", "Piauí", 64000000, 64999999, 29.65d),
	PARANA				("PR", "Paraná", 80000000, 87999999, 24.24d),
	RIO_DE_JANEIRO		("RJ", "Rio de Janeiro", 20000000, 28999999, 24.65d),
	RIO_GRANDE_NORTE	("RN", "Rio Grande do Norte", 59000000, 59999999, 56.77d),
	RONDONIA_PARTE1		("RO", "Rondônia", 76800000, 76999999, 68.48d),
	RONDONIA_PARTE2		("RO", "Rondônia", 78900000, 78999999, 68.48d),
	RORAIMA				("RR", "Roraima", 69300000, 69399999, 57.78d),
	RIO_GRANDE_SUL		("RS", "Rio Grande do Sul", 90000000, 99999999, 22.57d),
	SANTA_CATARINA		("SC", "Santa Catarina", 88000000, 89999999, 16.45d),
	SERGIPE				("SE", "Sergipe", 49000000, 49999999, 18.98d),
	SAO_PAULO			("SP", "São Paulo", 01000000, 19999999, 2d),
	TOCATINS			("TO", "Tocatins", 77000000, 77999999, 8.59d);

	private Integer cepInicial;
	private Integer cepFinal;
	private Double valorFrete;
	private String uf;
	private String estado;

	private CalculoFrete(String uf, String estado, Integer cepInicial, Integer cepFinal, Double valorFrete) {
		this.cepInicial = cepInicial;
		this.cepFinal = cepFinal;
		this.valorFrete = valorFrete;
		this.uf = uf;
		this.estado = estado;
	}

	public Integer getCepInicial() {
		return cepInicial;
	}

	public Integer getCepFinal() {
		return cepFinal;
	}

	public Double getValorFrete() {
		return RecursosUtil.casasDecimais(valorFrete);
	}

	public String getUf() {
		return uf;
	}

	public String getEstado() {
		return estado;
	}
	
	public static CalculoFrete verificarCepDados(Integer cep) {
		CalculoFrete calculo = null;
		for (CalculoFrete cepFrete : CalculoFrete.values()) {
			if(cep >= cepFrete.getCepInicial() && cep <= cepFrete.getCepFinal()) {
				calculo = cepFrete;
			}
		}
		return calculo;
	}
}
