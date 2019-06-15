package br.edu.faculdadedelta.locadoradeveicolos.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.enumeradores.Sexo;

@Entity
public class Motorista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo Nome deve ser preenchido!")
	private String nome;

	@NotBlank(message = "O campo CPF deve ser preenchido!")
	private String cpf;
	
	@NotBlank(message = "O campo CNH deve ser preenchido!")
	private String cnh;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo Sexo deve ser selecionado")
	private Sexo sexo;

	public Motorista() {
		super();
	}

	public Motorista(Long id, String nome, String cpf, String cnh, Sexo sexo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cnh = cnh;
		this.sexo = sexo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Motorista(Long id) {
		super();
		this.id = id;
	}

}
