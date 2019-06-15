package br.edu.faculdadedelta.locadoradeveicolos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Fabricante;
import br.edu.faculdadedelta.locadoradeveicolos.repositorio.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository repository;
	
	public Fabricante incluir(Fabricante fabricante){
		fabricante.setId(null);// Para garantir a inclusão retiramos o ID.
		return repository.save(fabricante);
	}
	
	public Fabricante alterar(Fabricante fabricante) {
		pesquisarPorId(fabricante.getId());// garantindo que o ID para alteração existe.
		return repository.save(fabricante);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Fabricante> listar(){
		return repository.findAll();
	}
	
	public Fabricante pesquisarPorId(Long id) {
		
		return repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(0));
	}
}
