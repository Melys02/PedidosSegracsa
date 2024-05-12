package com.pedidos.segracsa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pedidos.segracsa.dao.*;
import com.pedidos.segracsa.modelo.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControllerCliente {

    @Autowired
    private ClienteDaoImp dao;

    @GetMapping("/listar")
    public String listar(Model modelo) {
        List<Cliente> lista = dao.findAll();
        modelo.addAttribute("listacliente", lista);
        return "listadocliente";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model modelo) {
        Cliente cli = new Cliente();
        modelo.addAttribute("cliente", cli);
        return "guardaracliente";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cliente") Cliente cli) {
        dao.save(cli);
        return "redirect:/listar";

    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable("id") int id, RedirectAttributes attributes) {
        try {
            dao.delete(id);
            attributes.addFlashAttribute("success", "Cliente eliminado");

        } catch (Exception ex) {
            attributes.addFlashAttribute("error", "No se pudo eliminar cliente por que ya se encuentra asociado a una compra");
        //    ex.printStackTrace();
        }
        
        return "redirect:/listar";
    }

    @GetMapping("/editarC/{id}")
    public String editar(@PathVariable("id") int id, Model modelo) {
        Cliente cli = dao.findById(id);
        modelo.addAttribute("cliente", cli);

        return "editarcliente";
    }

    @PostMapping("/updateC")
    public String update(@ModelAttribute("cliente") Cliente cli) {
        dao.update(cli);
        return "redirect:/listar";
    }
}
