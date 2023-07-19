package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.FrameClientes;
import com.animal.hotel.view.telas.FrameEditarCliente;
import com.animal.hotel.view.telas.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosClientes {
    public static class EventoBotoesClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            FrameClientes.indiceCliente = FrameClientes.opcoesClientes.indexOf(arg0.getSource());
            Home.telaEditarCliente = new FrameEditarCliente();
        }
    }
    
}
