package br.edu.faculdadedelta.locadoradeveicolos.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Locacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Campo Valor obrigatório!")
	@Column(precision = 10, scale = 2)
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorTotal;

	@NotNull(message = "O campo data de locação é obrigatório!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeLocacao;
	
	@NotNull(message = "O campo data de devolução é obrigatório!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeDevolucao;
	
	@ManyToOne
	@JoinColumn(name = "id_motorista")
	@NotNull(message = "O campo Motorista é obrigatório!")
	private Motorista motorista;
	
	@ManyToOne
	@JoinColumn(name = "id_carro")
	@NotNull(message = "O campo Carro é obrigatório!")
	private Carro carro;

	public Locacao() {
		super();
	}

	public Locacao(Long id, BigDecimal valorTotal, LocalDate dataDeLocacao, LocalDate dataDeDevolucao,
			Motorista motorista, Carro carro) {
		super();
		this.id = id;
		this.valorTotal = valorTotal;
		this.dataDeLocacao = dataDeLocacao;
		this.dataDeDevolucao = dataDeDevolucao;
		this.motorista = motorista;
		this.carro = carro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataDeLocacao() {
		return dataDeLocacao;
	}

	public void setDataDeLocacao(LocalDate dataDeLocacao) {
		this.dataDeLocacao = dataDeLocacao;
	}

	public LocalDate getDataDeDevolucao() {
		return dataDeDevolucao;
	}

	public void setDataDeDevolucao(LocalDate dataDeDevolucao) {
		this.dataDeDevolucao = dataDeDevolucao;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
