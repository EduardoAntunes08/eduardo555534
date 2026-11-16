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

import br.com.fiap.eduardo555534.domain.Trecho;
import br.com.fiap.eduardo555534.dto.TrechoDTO;
import br.com.fiap.eduardo555534.exception.RegraNegocioException;
import br.com.fiap.eduardo555534.service.BairroService;
import br.com.fiap.eduardo555534.service.TrechoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/trechos")
@RequiredArgsConstructor
public class TrechoController {

    private final TrechoService service;
    private final BairroService bairroService;

    @ModelAttribute("tiposCalcada")
    public List<String> tiposCalcada() {
        return Trecho.TIPOS_CALCADA;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trechos", service.listar());
        return "trechos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("trecho", new TrechoDTO());
        carregarBairros(model);
        return "trechos/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("trecho", service.buscar(id));
        carregarBairros(model);
        return "trechos/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("trecho") TrechoDTO trecho,
                         BindingResult resultado,
                         Model model,
                         RedirectAttributes redirect) {
        if (resultado.hasErrors()) {
            carregarBairros(model);
            return "trechos/form";
        }
        service.salvar(trecho);
        redirect.addFlashAttribute("mensagem", "Trecho salvo com sucesso.");
        return "redirect:/trechos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        try {
            service.excluir(id);
            redirect.addFlashAttribute("mensagem", "Trecho excluído com sucesso.");
        } catch (RegraNegocioException e) {
            redirect.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/trechos";
    }

    private void carregarBairros(Model model) {
        model.addAttribute("bairros", bairroService.listar());
    }
}
