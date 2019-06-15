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
import br.edu.faculdadedelta.locadoradeveicolos.modelo.Fabricante;
import br.edu.faculdadedelta.locadoradeveicolos.modelo.Locacao;
import br.edu.faculdadedelta.locadoradeveicolos.modelo.Modelo;
import br.edu.faculdadedelta.locadoradeveicolos.modelo.Motorista;
import br.edu.faculdadedelta.locadoradeveicolos.modelo.enumeradores.Categoria;
import br.edu.faculdadedelta.locadoradeveicolos.repositorio.LocacaoRepository;
import br.edu.faculdadedelta.locadoradeveicolos.service.CarroService;
import br.edu.faculdadedelta.locadoradeveicolos.service.FabricanteService;
import br.edu.faculdadedelta.locadoradeveicolos.service.LocacaoService;
import br.edu.faculdadedelta.locadoradeveicolos.service.ModeloService;
import br.edu.faculdadedelta.locadoradeveicolos.service.MotoristaService;

@Controller
@RequestMapping("/locacao")
public class LocacaoController {
	@Autowired
    private LocacaoService serviceLocacao;
    
    @Autowired
    private CarroService serviceCarro;
    
    @Autowired
    private MotoristaService serviceMotorista;
    
	private static final String LOCACAO_CADASTRO = "locacaoCadastro";
    private static final String LOCACAO_LISTA =  "locacaoLista";

    @RequestMapping("/novo")
    public ModelAndView novo(){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_CADASTRO);
        modelAndView.addObject (new Locacao());
        return modelAndView;
    }
    
    @ModelAttribute(name = "todosCarros")
	public List<Carro> todosCarros(){
		return serviceCarro.listar();
	}
    
    @ModelAttribute(name = "todosMotoristas")
	public List<Motorista> todosMotoristas(){
		return serviceMotorista.listar();
	}

    @PostMapping
    public ModelAndView salvar(@Valid Locacao locacao, Errors errors, RedirectAttributes redirectAttributes){

        if (errors.hasErrors ()){

            return new ModelAndView (LOCACAO_CADASTRO);
        }

        if (locacao.getId () == null){
            serviceLocacao.incluir(locacao);

            redirectAttributes.addFlashAttribute ("mensagem", "Inclusão realizada com sucesso.");
        } else {

            serviceLocacao.alterar(locacao);
            redirectAttributes.addFlashAttribute ("mensagem", "Alteração realizada com sucesso.");
        }

        return new ModelAndView ("redirect:/locacao/novo");
    }

    @GetMapping
    public ModelAndView listar(){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_LISTA);

        modelAndView.addObject ("locacao", serviceLocacao.listar());

        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView (LOCACAO_CADASTRO);

        modelAndView.addObject (serviceLocacao.pesquisarPorId(id));

        return modelAndView;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/locacao");

        serviceLocacao.excluir(id);

        return modelAndView;
    }
}
