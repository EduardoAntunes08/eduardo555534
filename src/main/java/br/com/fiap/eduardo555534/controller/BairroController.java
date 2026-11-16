package br.com.fiap.eduardo555534.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.eduardo555534.domain.Bairro;
import br.com.fiap.eduardo555534.dto.BairroDTO;
import br.com.fiap.eduardo555534.exception.RegraNegocioException;
import br.com.fiap.eduardo555534.service.BairroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/bairros")
@RequiredArgsConstructor
public class BairroController {

    private final BairroService service;

    @ModelAttribute("zonas")
    public List<String> zonas() {
        return Bairro.ZONAS;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bairros", service.listar());
        return "bairros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bairro", new BairroDTO());
        return "bairros/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("bairro", service.buscar(id));
        return "bairros/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("bairro") BairroDTO bairro,
                         BindingResult resultado,
                         RedirectAttributes redirect) {
        if (resultado.hasErrors()) {
            return "bairros/form";
        }
        service.salvar(bairro);
        redirect.addFlashAttribute("mensagem", "Bairro salvo com sucesso.");
        return "redirect:/bairros";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            service.excluir(id);
            redirect.addFlashAttribute("mensagem", "Bairro excluído com sucesso.");
        } catch (RegraNegocioException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/bairros";
    }
}
