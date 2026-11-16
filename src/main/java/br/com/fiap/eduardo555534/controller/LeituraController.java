package br.com.fiap.eduardo555534.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.eduardo555534.dto.LeituraDTO;
import br.com.fiap.eduardo555534.service.LeituraService;
import br.com.fiap.eduardo555534.service.TrechoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/leituras")
@RequiredArgsConstructor
public class LeituraController {

    private final LeituraService service;
    private final TrechoService trechoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", service.listar());
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO());
        carregarTrechos(model);
        return "leituras/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("leitura", service.buscar(id));
        carregarTrechos(model);
        return "leituras/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("leitura") LeituraDTO leitura,
                         BindingResult resultado,
                         Model model,
                         RedirectAttributes redirect) {
        if (resultado.hasErrors()) {
            carregarTrechos(model);
            return "leituras/form";
        }
        service.salvar(leitura);
        redirect.addFlashAttribute("mensagem", "Leitura salva com sucesso.");
        return "redirect:/leituras";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        service.excluir(id);
        redirect.addFlashAttribute("mensagem", "Leitura excluída com sucesso.");
        return "redirect:/leituras";
    }

    private void carregarTrechos(Model model) {
        model.addAttribute("trechos", trechoService.listar());
    }
}
