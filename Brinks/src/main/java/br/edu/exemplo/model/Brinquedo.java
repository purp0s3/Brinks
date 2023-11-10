package br.edu.exemplo.model;

import javax.servlet.http.Part;

public class Brinquedo {
	
	/* Java Bean - É uma classe que contenha todos os atributos privados, possua getters e setters para seus atributos
	e usada para encapsular e abstrair uma entidade */
	
	// atributos
	private int id;
	private String descricao;
	private String categoria;
	private String marca;
	private int valor;
	private String detalhes;
	private String imagem;
	private Part imagemFile;

	// construtor
	public Brinquedo(int id, String descricao, String categoria, String marca, int valor, String detalhes, String imagem) {
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.marca = marca;
		this.valor = valor;
		this.detalhes = detalhes;
		this.imagem = imagem;
	}
	
	// construtor vazio
	public Brinquedo() {
	}
	
	// getters e setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
	    this.imagem = imagem;
	}
	public Part getImagemFile() {
		return imagemFile;
	}
	public void setImagemFile(Part imagemFile) {
		this.imagemFile = imagemFile;
	}
}