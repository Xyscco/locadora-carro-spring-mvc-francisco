package br.edu.faculdadedelta.locadoradeveicolos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Locacao;
import br.edu.faculdadedelta.locadoradeveicolos.repositorio.LocacaoRepository;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository repository;
	
	public Locacao incluir(Locacao serie){
		serie.setId(null);// Para garantir a inclusão retiramos o ID.
		return repository.save(serie);
	}
	
	public Locacao alterar(Locacao serie) {
		pesquisarPorId(serie.getId());// garantindo que o ID para alteração existe.
		return repository.save(serie);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Locacao> listar(){
		return repository.findAll();
	}
	
	public Locacao pesquisarPorId(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
	}
}
