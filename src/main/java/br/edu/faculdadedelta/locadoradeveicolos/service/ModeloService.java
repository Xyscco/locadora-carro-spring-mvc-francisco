package br.edu.faculdadedelta.locadoradeveicolos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Modelo;
import br.edu.faculdadedelta.locadoradeveicolos.repositorio.ModeloRepository;

@Service
public class ModeloService {
	
	@Autowired
	private ModeloRepository repository;
	
	public Modelo incluir(Modelo serie){
		serie.setId(null);// Para garantir a inclusão retiramos o ID.
		return repository.save(serie);
	}
	
	public Modelo alterar(Modelo serie) {
		pesquisarPorId(serie.getId());// garantindo que o ID para alteração existe.
		return repository.save(serie);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Modelo> listar(){
		return repository.findAll();
	}
	
	public Modelo pesquisarPorId(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
	}
}
