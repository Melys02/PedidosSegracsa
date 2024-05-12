package com.pedidos.segracsa.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.pedidos.segracsa.dao.*;
import com.pedidos.segracsa.modelo.*;

@Component("guardarcomprobante")
public class ListadoComprobanteExcel extends AbstractXlsxView {

    String filename = "RegistroVentaRebecca" + Util.dateToStringFormarReport();
    @Autowired
    private ClienteDaoImp clienteDao;

    @Autowired
    private TipoComprobanteDaoImp tipoComprobanteDao;

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".xlsx");
        DecimalFormat df = new DecimalFormat("#.##");
        Sheet hoja = workbook.createSheet("Comprobantes Rebecca´s");

        CellStyle tituloEstilo = workbook.createCellStyle();
        tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
        tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);

        Font fuenteTitulo = workbook.createFont();
        fuenteTitulo.setFontName("Arial");
        fuenteTitulo.setBold(true);
        fuenteTitulo.setFontHeightInPoints((short) 14);
        tituloEstilo.setFont(fuenteTitulo);

        Row filaTitulo = hoja.createRow(1);
        Cell celdaTitulo = filaTitulo.createCell(1);
        celdaTitulo.setCellStyle(tituloEstilo);
        celdaTitulo.setCellValue("Reporte de Venta - Rebecca´s");
        hoja.addMergedRegion(new CellRangeAddress(1, 2, 1, 9));

        String[] columnas = {"", "#", "Fecha", "Cliente", "Tipo Comprobante", "Serie", "Numero", "SubTotal", "IGV", "Total Neto"};

        CellStyle cabeceraEstilo = workbook.createCellStyle();
        cabeceraEstilo.setFillForegroundColor(IndexedColors.OLIVE_GREEN.getIndex());
        cabeceraEstilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cabeceraEstilo.setBorderBottom(BorderStyle.THIN);
        cabeceraEstilo.setBorderLeft(BorderStyle.THIN);
        cabeceraEstilo.setBorderRight(BorderStyle.THIN);
        cabeceraEstilo.setBorderBottom(BorderStyle.THIN);
        cabeceraEstilo.setAlignment(HorizontalAlignment.CENTER);
        cabeceraEstilo.setVerticalAlignment(VerticalAlignment.CENTER);

        Font fuenteCabecera = workbook.createFont();
        fuenteCabecera.setFontName("Arial");
        fuenteCabecera.setBold(true);
        fuenteCabecera.setFontHeightInPoints((short) 12);
        fuenteCabecera.setColor(IndexedColors.WHITE.getIndex());
        cabeceraEstilo.setFont(fuenteCabecera);
        hoja.setDefaultColumnWidth(25);
        hoja.setDefaultRowHeightInPoints(20);

        Row filaEncabezados = hoja.createRow(4);
        for (int i = 1; i < columnas.length; i++) {
            Cell celdaEncabezado = filaEncabezados.createCell(i);
            celdaEncabezado.setCellStyle(cabeceraEstilo);
            celdaEncabezado.setCellValue(columnas[i]);
        }

        List<Comprobante> listaCpe = (List<Comprobante>) model.get("lstComprobante");
        int numFila = 5;
        int numItem = 1;
        for (Comprobante comprobante : listaCpe) {

            Cliente cliente = clienteDao.findById(comprobante.getCodigo_cli());

            filaEncabezados = hoja.createRow(numFila);
            filaEncabezados.createCell(1).setCellValue(numItem);
            filaEncabezados.createCell(2).setCellValue(comprobante.getFechaCom());
            filaEncabezados.createCell(3).setCellValue(cliente.getNombreCli().concat(" ").concat(cliente.getApellidoPCli().concat(" ").concat(cliente.getApellidoMCli())));
            filaEncabezados.createCell(4).setCellValue(comprobante.getCodigo_tipocom());
            filaEncabezados.createCell(5).setCellValue(comprobante.getSerieCom());
            filaEncabezados.createCell(6).setCellValue(comprobante.getNumeracionCom());
            filaEncabezados.createCell(7).setCellValue("S/. " + df.format(comprobante.getSubTotal()));
            filaEncabezados.createCell(8).setCellValue("S/. " + df.format(comprobante.getIgv()));
            filaEncabezados.createCell(9).setCellValue("S/. " + df.format(comprobante.getPreciototal()));

            numFila++;
            numItem++;
        }
    }

}
