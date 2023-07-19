package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.CadastrarCliente;
import com.animal.hotel.view.telas.Clientes;
import com.animal.hotel.view.telas.Reservas;
import com.animal.hotel.view.telas.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EventosHome {
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
