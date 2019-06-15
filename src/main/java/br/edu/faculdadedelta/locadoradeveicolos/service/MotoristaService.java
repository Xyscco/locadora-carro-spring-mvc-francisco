package br.edu.faculdadedelta.locadoradeveicolos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Motorista;
import br.edu.faculdadedelta.locadoradeveicolos.repositorio.MotoristaRepository;

@Service
public class MotoristaService {

	@Autowired
	private MotoristaRepository repository;
	
	public Motorista incluir(Motorista serie){
		serie.setId(null);// Para garantir a inclusão retiramos o ID.
		return repository.save(serie);
	}
	
	public Motorista alterar(Motorista serie) {
		pesquisarPorId(serie.getId());// garantindo que o ID para alteração existe.
		return repository.save(serie);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Motorista> listar(){
		return repository.findAll();
	}
	
	public Motorista pesquisarPorId(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
	}
}
