package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.Clientes;
import com.animal.hotel.view.telas.EditarCliente;
import com.animal.hotel.view.telas.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosClientes {
    public static class EventoBotoesClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Clientes.indiceCliente = Clientes.opcoesClientes.indexOf(arg0.getSource());
            Home.telaEditarCliente = new EditarCliente();
        }
    }
    
}
