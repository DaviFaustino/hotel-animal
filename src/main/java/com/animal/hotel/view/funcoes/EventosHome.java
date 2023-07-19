package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.FrameCadastrarCliente;
import com.animal.hotel.view.telas.FrameClientes;
import com.animal.hotel.view.telas.FrameReservas;
import com.animal.hotel.view.telas.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EventosHome {
    public static class EventoButaoCadastrarClientes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.telaCadastrarCliente = new FrameCadastrarCliente();
        }
    }

    public static class EventoButaoClientes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.alterar = true;
            Home.telaClientes = new FrameClientes();
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
                FrameReservas.hospedeIsCachorro = true;
            } else {
                if (escolha == 1) {
                    FrameReservas.hospedeIsCachorro = false;
                }
            }

            if (escolha != 2) {
                Home.telaReservas = new FrameReservas("0000", new float[2]);
                Home.telaPrincipal.setVisible(false);
            }
        }
    }
}
