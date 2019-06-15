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

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Fabricante;
import br.edu.faculdadedelta.locadoradeveicolos.modelo.Modelo;
import br.edu.faculdadedelta.locadoradeveicolos.modelo.enumeradores.Categoria;
import br.edu.faculdadedelta.locadoradeveicolos.service.FabricanteService;
import br.edu.faculdadedelta.locadoradeveicolos.service.ModeloService;

@Controller
@RequestMapping("/modelo")
public class ModeloController {
	@Autowired
    private ModeloService serviceModelo;
    
    @Autowired
    private FabricanteService serviceFabricante;
    
	private static final String MODELO_CADASTRO = "modeloCadastro";
    private static final String MODELO_LISTA =  "modeloLista";

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (MODELO_CADASTRO);
        modelAndView.addObject (new Modelo());
        return modelAndView;
    }
    
    @ModelAttribute(name = "todosFabricantes")
	public List<Fabricante> todosFabricantes(){
		return serviceFabricante.listar();
	}
    
    @ModelAttribute(name = "todasCategorias")
	public Categoria[] todosStatus() {
		return Categoria.values();
	}

    @PostMapping
    public ModelAndView salvar(@Valid Modelo modelo, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (MODELO_CADASTRO);
        }

        if (modelo.getId () == null){
            serviceModelo.incluir(modelo);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            serviceModelo.alterar(modelo);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/modelo/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (MODELO_LISTA);

        modelAndView.addObject ("modelo", serviceModelo.listar());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (MODELO_CADASTRO);

        modelAndView.addObject (serviceModelo.pesquisarPorId(id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/modelo");

        serviceModelo.excluir(id);

        return modelAndView;
    }
}
