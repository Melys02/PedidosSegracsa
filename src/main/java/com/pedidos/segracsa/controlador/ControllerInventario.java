package com.pedidos.segracsa.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.pedidos.segracsa.dao.*;
import com.pedidos.segracsa.modelo.*;

@Controller
public class ControllerInventario {

    @Autowired
    private InventarioDaoImp dao;

    @GetMapping("/listarinventario")
    public String listar(Model modelo) {
        List<Inventario> lista = dao.findAll();
        modelo.addAttribute("listainventarios", lista);
        modelo.addAttribute("listaproductos", ListarProductos());

        return "listadoinventario";

    }

    public List<String> ListarProductos() {
        List<String> listProducto = new ArrayList<>();
        boolean encontro;

        List<Inventario> lista = dao.findAll();

        for (Inventario inv : lista) {
            String pro = inv.getNombrePro();
            encontro = false;
            for (String x : listProducto) {
                if (pro.equalsIgnoreCase(x)) {
                    encontro = true;
                    break;
                }
            }

            if (!encontro) {
                listProducto.add(pro);
            }
        }

        Collections.sort(listProducto);

        return listProducto;
    }

}
