package com.pedidos.segracsa.dao;

import com.pedidos.segracsa.modelo.Usuario;

public interface IUsuarioDao {

    public Usuario login(String correo, String password);
}
