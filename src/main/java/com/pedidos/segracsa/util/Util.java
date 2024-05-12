package com.pedidos.segracsa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Util {

    public static String dateToStringFormarReport() {
        String result = "";
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
        result = fechaHora.format(new Date());
        return result;
    }

    public static String generarCodigoSeguridad() {
        Random random = new Random();
        int numero = random.nextInt(10000);
        return String.format("%04d", numero);
    }
}
