package com.pedidos.segracsa.controlador;

import com.pedidos.segracsa.dao.ComprobanteDaoIm;
import com.pedidos.segracsa.dao.UsuarioDaoIm;
import com.pedidos.segracsa.modelo.Compra;
import com.pedidos.segracsa.modelo.ReporteVenta;
import com.pedidos.segracsa.modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerIndex {

    @Autowired
    private UsuarioDaoIm usuarioDao;
    @Autowired
    private ComprobanteDaoIm comprobanteDao;
    @Autowired
    private HttpSession session;

    @RequestMapping("/")
    public String login(Model model) {
        if (session.getAttribute("usuario") != null) {
            return "redirect:/home";
        }

        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String login(Usuario obj, Model model) {
        if (session.getAttribute("usuario") != null) {
            return "redirect:/home";
        }

        Usuario objLogin = usuarioDao.login(obj.getCorreo(), obj.getPassword());

        if (objLogin != null) {
            session.setAttribute("usuario", objLogin);
            return "redirect:/home";
        }

        model.addAttribute("usuario", obj);
        return "login";
    }

    @RequestMapping("/home")
    public String home() {
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        session.removeAttribute("usuario");
        return "redirect:/";
    }

    @RequestMapping("/consultar")
    public String ConsultarComp() {
        return "consultarComprobante";
    }

    @GetMapping("/consultar_comprobante")
    public Object ConsultarComprobante(@RequestParam("codigoSeguridad") String codSeg,
            @RequestParam("codigoComprobante") int codCompro,
            Model modelo) {
        boolean valida = comprobanteDao.validarComprobante(codCompro, codSeg);
        ReporteVenta obj = null;
        List<Compra> lista;
        if (valida) {
            obj = comprobanteDao.CabeceraComprobante(codCompro);
            lista = comprobanteDao.listaDetalleCompra(codCompro);
        } else {
            obj = null;
            lista = new ArrayList<>();
        }

        modelo.addAttribute("cabecera", obj);
        modelo.addAttribute("detalle", lista);
        return "detalleComp";
    }
}
