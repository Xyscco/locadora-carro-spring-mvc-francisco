package br.edu.faculdadedelta.locadoradeveicolos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Carro;
import br.edu.faculdadedelta.locadoradeveicolos.modelo.Modelo;
import br.edu.faculdadedelta.locadoradeveicolos.service.CarroService;
import br.edu.faculdadedelta.locadoradeveicolos.service.ModeloService;

@Controller
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
    private CarroService serviceCarro;
	
	@Autowired
    private ModeloService serviceModelo;
    
    
	private static final String CARRO_CADASTRO = "carroCadastro";
    private static final String CARRO_LISTA =  "carroLista";

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (CARRO_CADASTRO);
        modelAndView.addObject (new Carro());
        return modelAndView;
    }
    
    @ModelAttribute(name = "todosModelos")
	public List<Modelo> todosModelos(){
		return serviceModelo.listar();
	}

    @PostMapping
    public ModelAndView salvar(@Valid Carro carro, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (CARRO_CADASTRO);
        }

        if (carro.getId () == null){
            serviceCarro.incluir(carro);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            serviceCarro.alterar(carro);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/carro/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (CARRO_LISTA);

        modelAndView.addObject ("carro", serviceCarro.listar());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (CARRO_CADASTRO);

        modelAndView.addObject (serviceCarro.pesquisarPorId(id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/carro");

        serviceCarro.excluir(id);

        return modelAndView;
    }
}
