package br.edu.faculdadedelta.locadoradeveicolos.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo Placa deve ser preenchido!")
	private String placa;

	@NotBlank(message = "O campo Chassi deve ser preenchido!")
	private String chassi;

	@NotNull(message = "O campo Valor da Diária é obrigatório!")
	@Column(precision = 10, scale = 2)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorDaDiaria;

	@ManyToOne
	@JoinColumn(name = "id_modelo")
	@NotNull(message = "O campo Modelo é obrigatório")
	private Modelo modelo;

	public Carro() {
		super();
	}

	public Carro(Long id, @NotBlank(message = "O campo Placa deve ser preenchido!") String placa,
			@NotBlank(message = "O campo Chassi deve ser preenchido!") String chassi,
			@NotNull(message = "O campo Valor da Diária é obrigatório!") BigDecimal valorDaDiaria,
			@NotNull(message = "O campo Modelo é obrigatório") Modelo modelo) {
		super();
		this.id = id;
		this.placa = placa;
		this.chassi = chassi;
		this.valorDaDiaria = valorDaDiaria;
		this.modelo = modelo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public BigDecimal getValorDaDiaria() {
		return valorDaDiaria;
	}

	public void setValorDaDiaria(BigDecimal valorDaDiaria) {
		this.valorDaDiaria = valorDaDiaria;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Carro(Long id) {
		super();
		this.id = id;
	}

}
