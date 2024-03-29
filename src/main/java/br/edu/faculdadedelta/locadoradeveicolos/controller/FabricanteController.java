package br.edu.faculdadedelta.locadoradeveicolos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Fabricante;
import br.edu.faculdadedelta.locadoradeveicolos.service.FabricanteService;

@Controller
@RequestMapping("/fabricante")
public class FabricanteController {

	private static final String FABRICANTE_CADASTRO = "fabricanteCadastro";
    private static final String FABRICANTE_LISTA =  "fabricanteLista";

    @Autowired
    private FabricanteService service;

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (FABRICANTE_CADASTRO);
        modelAndView.addObject (new Fabricante());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView salvar(@Valid Fabricante fabricante, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (FABRICANTE_CADASTRO);
        }

        if (fabricante.getId () == null){
            service.incluir(fabricante);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            service.alterar(fabricante);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/fabricante/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (FABRICANTE_LISTA);

        modelAndView.addObject ("fabricante", service.listar());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (FABRICANTE_CADASTRO);

        modelAndView.addObject (service.pesquisarPorId(id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/fabricante");

        service.excluir(id);

        return modelAndView;
    }
}
