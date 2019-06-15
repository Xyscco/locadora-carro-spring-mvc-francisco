package br.edu.faculdadedelta.locadoradeveicolos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long>{

}
