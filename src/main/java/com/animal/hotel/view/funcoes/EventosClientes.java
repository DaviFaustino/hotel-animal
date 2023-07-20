package com.animal.hotel.view.funcoes;

import com.animal.hotel.uteis.Arquivos;
import com.animal.hotel.view.telas.Clientes;
import com.animal.hotel.view.telas.EditarCliente;
import com.animal.hotel.view.telas.Home;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JFrame;

public class EventosClientes {
    public static class EventosTela implements ComponentListener {
        @Override
            public void componentHidden(ComponentEvent arg0) {}

            @Override
            public void componentMoved(ComponentEvent arg0) {
                Clientes.posicaoTela = ((JFrame) arg0.getSource()).getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                Clientes.tamanhoLaterais = (((JFrame) arg0.getSource()).getWidth() - Clientes.larguraPainelCliente) / 2;
                Clientes.paineisClientes.get(1).setPreferredSize(new Dimension(Clientes.tamanhoLaterais, Clientes.tamanhoLaterais));
                Clientes.paineisClientes.get(2).setPreferredSize(new Dimension(Clientes.tamanhoLaterais, Clientes.tamanhoLaterais));
                Clientes.telaWidth = ((JFrame) arg0.getSource()).getWidth();
                Clientes.telaHeight = ((JFrame) arg0.getSource()).getHeight();
            }

            @Override
            public void componentShown(ComponentEvent arg0) {}
    }

    public static class EventoBotoesClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Clientes.indiceCliente = Clientes.opcoesClientes.indexOf(arg0.getSource());
            Home.telaEditarCliente = new EditarCliente();
        }
    }
    
    public static class EventoBotaoVoltar implements ActionListener {
        @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Arquivos.salvar(Clientes.clientes, 2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Home.telaPrincipal.setLocation(Clientes.posicaoTela);
                Home.telaPrincipal.setSize(Clientes.telaWidth, Clientes.telaHeight);
                Home.telaPrincipal.setVisible(true);
                Home.telaClientes.dispose();
            }
    }
}
