package br.edu.faculdadedelta.locadoradeveicolos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Carro;
import br.edu.faculdadedelta.locadoradeveicolos.repositorio.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;
	
	public Carro incluir(Carro serie){
		serie.setId(null);// Para garantir a inclusão retiramos o ID.
		return repository.save(serie);
	}
	
	public Carro alterar(Carro serie) {
		pesquisarPorId(serie.getId());// garantindo que o ID para alteração existe.
		return repository.save(serie);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Carro> listar(){
		return repository.findAll();
	}
	
	public Carro pesquisarPorId(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
	}
}
