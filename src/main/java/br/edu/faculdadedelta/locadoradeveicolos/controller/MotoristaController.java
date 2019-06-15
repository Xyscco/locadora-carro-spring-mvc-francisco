package br.edu.faculdadedelta.locadoradeveicolos.controller;

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

import br.edu.faculdadedelta.locadoradeveicolos.modelo.Motorista;
import br.edu.faculdadedelta.locadoradeveicolos.modelo.enumeradores.Sexo;
import br.edu.faculdadedelta.locadoradeveicolos.service.MotoristaService;

@Controller
@RequestMapping("/motorista")
public class MotoristaController {

	private static final String MOTORISTA_CADASTRO = "motoristaCadastro";
    private static final String MOTORISTA_LISTA =  "motoristaLista";

    @Autowired
    private MotoristaService service;

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (MOTORISTA_CADASTRO);
        modelAndView.addObject (new Motorista ());
        return modelAndView;
    }

    @ModelAttribute(name = "todosSexos")
    public Sexo[] todosSexos(){

        return Sexo.values ();
    }

    @PostMapping
    public ModelAndView salvar(@Valid Motorista motorista, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (MOTORISTA_CADASTRO);
        }

        if (motorista.getId () == null){
            service.incluir(motorista);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            service.alterar(motorista);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/motorista/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (MOTORISTA_LISTA);

        modelAndView.addObject ("motorista", service.listar());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (MOTORISTA_CADASTRO);

        modelAndView.addObject (service.pesquisarPorId(id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/motorista");

        service.excluir(id);

        return modelAndView;
    }
}
