package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.CadastrarCliente;
import com.animal.hotel.view.telas.Clientes;
import com.animal.hotel.view.telas.Reservas;
import com.animal.hotel.view.telas.Home;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JOptionPane;

public class EventosHome {
    public static class EventosTela implements ComponentListener {
        @Override
        public void componentHidden(ComponentEvent arg0) {}

        @Override
        public void componentMoved(ComponentEvent arg0) {
            Home.posicaoTela = Home.telaPrincipal.getLocation();
        }

        @Override
        public void componentResized(ComponentEvent arg0) {
            Home.tamanhoLaterais = (Home.telaPrincipal.getWidth() - Home.larguraPainelPrincipal) / 2;
            Home.tamanhoCimaBaixo = (Home.telaPrincipal.getHeight() - Home.alturaPainelPrincipal) / 2;
            Home.paineisPrincipal.get(1).setPreferredSize(new Dimension(Home.tamanhoLaterais, Home.tamanhoLaterais));
            Home.paineisPrincipal.get(2).setPreferredSize(new Dimension(Home.tamanhoLaterais, Home.tamanhoLaterais));
            Home.paineisPrincipal.get(3).setPreferredSize(new Dimension(Home.tamanhoCimaBaixo, Home.tamanhoCimaBaixo));
            Home.paineisPrincipal.get(4).setPreferredSize(new Dimension(Home.tamanhoCimaBaixo, Home.tamanhoCimaBaixo));
            Home.telaWidth = Home.telaPrincipal.getWidth();
            Home.telaHeight = Home.telaPrincipal.getHeight();
        }

        @Override
        public void componentShown(ComponentEvent arg0) {}
    }

    public static class EventoButaoCadastrarClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.telaCadastrarCliente = new CadastrarCliente();
        }
    }

    public static class EventoButaoClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.alterar = true;
            Home.telaClientes = new Clientes();
        }
    }

    public static class EventoButaoExit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.telaPrincipal.dispose();
        }
    }

    public static class EventoButaoHospedes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.alterar = false;

            String[] opcs = {"Cachorros", "  Gatos  ", "Cancelar"};
            int escolha;

            escolha = JOptionPane.showOptionDialog(null, "Você quer ver cachorros ou gatos?", null, 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                        null, opcs, null);

            if (escolha == 0) {
                Reservas.hospedeIsCachorro = true;
            } else {
                if (escolha == 1) {
                    Reservas.hospedeIsCachorro = false;
                }
            }

            if (escolha != 2) {
                Home.telaReservas = new Reservas("0000", new float[2]);
                Home.telaPrincipal.setVisible(false);
            }
        }
    }
}
