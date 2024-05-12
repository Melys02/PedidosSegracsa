package com.pedidos.segracsa.controlador;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.pedidos.segracsa.dao.*;
import com.pedidos.segracsa.modelo.*;
import com.pedidos.segracsa.util.Util;

@Controller
@RequestMapping("comprobante")
public class ControllerComprobante {

    @Autowired
    private TipoComprobanteDaoImp tipoComprobanteDao;

    @Autowired
    private ClienteDaoImp clienteDao;

    @Autowired
    private ProductoDaoImp productoDao;

    @Autowired
    private ComprobanteDaoIm comprobanteDao;

    @Autowired
    private HttpSession sesion;

    @Autowired
    private InventarioDetalleDaoImp inventarioDetDao;

    @RequestMapping("/")
    public String abririndex(Model modelo) {
        List<TipoComprobante> listaCom = tipoComprobanteDao.findAll();
        List<Cliente> listaCli = clienteDao.findAll();
        List<Producto> listaProd = productoDao.findAllOrderBy();
        List<Compra> carrito = ObtenerCarrito();
        List<Comprobante> cpe = comprobanteDao.listaComprobante();

        modelo.addAttribute("listacliente", listaCli);
        modelo.addAttribute("listaproductos", listaProd);
        modelo.addAttribute("comprobantes", listaCom);
        modelo.addAttribute("lstComprobante", cpe);
        modelo.addAttribute("carrito", carrito);
        modelo.addAttribute("total", Total(carrito));
        modelo.addAttribute("igv", IGV(carrito));
        modelo.addAttribute("subtotal", SubTotal(carrito));
        return "guardarcomprobante";
    }

    @GetMapping("/obtener_numeracion")
    @ResponseBody
    public String ObtenerNumeracion(String id) {
        String numeracion = comprobanteDao.IdNumeracion(id);
        return numeracion;
    }

    @RequestMapping("/procesar")
    public String procesarCompra(Comprobante objComp, RedirectAttributes attributes) {

        List<Compra> carrito = ObtenerCarrito();
        DecimalFormat df = new DecimalFormat("#.##");
        List<InventarioDetalle> stock = inventarioDetDao.findAll();

        objComp.setSerieCom(objComp.getCodigo_tipocom().equals("BOLETA") ? "BB01" : "FF01");
        objComp.setNumeracionCom(comprobanteDao.IdNumeracion(objComp.getCodigo_tipocom()));
        objComp.setNumeracionmasuno(objComp.getNumeracionmasuno());
        objComp.setSubTotal(SubTotal(carrito));
        objComp.setIgv(IGV(carrito));
        objComp.setPreciototal(Total(carrito));
        objComp.setCodigo_seguridad(Util.generarCodigoSeguridad());

        int res = comprobanteDao.save(objComp);

        if (res > 0) {

            int idComprobante = comprobanteDao.LastIdComp();
            Detallecomp objDet = null;
            for (Compra c : carrito) {
                objDet = new Detallecomp();
                objDet.setCantidadPro(c.getCantidad());
                objDet.setId_producto(c.getId_producto());
                objDet.setCodigo_com(idComprobante);
                objDet.setSubTotal(c.getPrecioPro());
                comprobanteDao.saveDetalle(objDet);

                //Entra a inventario (-)
                InventarioDetalle detalle = inventarioDetDao.buscarIdProd(c.getId_producto());
                int stockSalida = 0, salidaTotal = 0;
                stockSalida = detalle.getStock() - c.getCantidad();
                salidaTotal = detalle.getSalida() + c.getCantidad();
                int g = inventarioDetDao.updateStockInventario(detalle.getId_producto(), detalle.getEntrada(), salidaTotal, stockSalida);
                System.out.println("Guardar cantidad: " + stockSalida);
                System.out.println("Guardar stock actual: " + salidaTotal);
            }

            limpiarCarrito();

            attributes.addFlashAttribute("success", "Se genero correctamente el comprobante N°" + idComprobante);
        } else {
            attributes.addFlashAttribute("error", "No se ha podido generar comprobante");
        }

        return "redirect:/comprobante/";
    }

    @GetMapping("/carrito/agregar")
    public String MiCarrito(@RequestParam("id_producto") int id,
            @RequestParam("cantidad") int cantidad) {
        List<Compra> carrito = ObtenerCarrito();

        Producto p = productoDao.findById(id);
        Compra c = new Compra();
        c.setId_producto(p.getId_producto());
        c.setNombrePro(p.getNombrePro());
        c.setPrecioPro(p.getPrecioPro());
        c.setCantidad(cantidad);

        int posc = BuscarItem(carrito, c.getId_producto());
        if (posc == -1) {
            carrito.add(c);
        } else {
            c.setCantidad(carrito.get(posc).getCantidad() + cantidad);
            carrito.set(posc, c);
        }

        sesion.setAttribute("carrito", carrito);

        return "redirect:/comprobante/";
    }

    @GetMapping("/carrito/eliminar/{indice}")
    public String EliminarItemCarrito(@PathVariable("indice") int indice) {
        List<Compra> carrito = ObtenerCarrito();
        if (indice >= 0 && indice < carrito.size()) {
            carrito.remove(indice);
            sesion.setAttribute("carrito", carrito);
        }
        return "redirect:/comprobante/";
    }

    public ArrayList<Compra> ObtenerCarrito() {
        ArrayList<Compra> carrito;

        if (sesion.getAttribute("carrito") == null) {
            carrito = new ArrayList<>();
        } else {
            carrito = (ArrayList<Compra>) sesion.getAttribute("carrito");
        }
        return carrito;
    }

    public int BuscarItem(List<Compra> carrito, int idProducto) {
        for (int i = 0; i < carrito.size(); i++) {
            if (Objects.equals(carrito.get(i).getId_producto(), idProducto)) {
                return i;
            }
        }
        return -1;
    }

    public double SubTotal(List<Compra> carrito) {
        double total = 0.0;
        for (Compra c : carrito) {
            //total += (c.Total()/1.18);
            total += c.Total() / 1.18;
        }

        return total;
    }

    public double IGV(List<Compra> carrito) {
        //double total = 0.0;
        double igv = 0.0;
        for (Compra c : carrito) {
            igv += ((c.Total() / 1.18) * 0.18);
            //total=c.Total()*(18/100);
            //igv=c.Total()*(0.18);
        }

        //return total;
        return igv;
    }

    public double Total(List<Compra> carrito) {
        double total = 0.0;
        for (Compra c : carrito) {
            total += c.Total();
        }

        return total;
    }

    private void limpiarCarrito() {
        sesion.setAttribute("carrito", new ArrayList<Compra>());
    }

    @GetMapping("/consulta")
    public Object ConsultarComprobante(@RequestParam("id") int id, Model modelo) {
        ReporteVenta obj = comprobanteDao.CabeceraComprobante(id);
        List<Compra> lista = comprobanteDao.listaDetalleCompra(id);
        modelo.addAttribute("cabecera", obj);
        modelo.addAttribute("detalle", lista);
        return "detalleComp";
    }

}
