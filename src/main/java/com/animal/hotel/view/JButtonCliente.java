package com.animal.hotel.view;

import com.animal.hotel.model.Cliente;
import javax.swing.JButton;

public class JButtonCliente extends JButton {
    Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}