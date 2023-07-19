package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.Clientes;
import com.animal.hotel.view.telas.EditarCliente;
import com.animal.hotel.view.telas.FazerReserva;
import com.animal.hotel.view.telas.Reservas;
import com.animal.hotel.view.telas.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EventosEditarCliente {
    public static class EventoBotaoEditarNome implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String entrada;

            entrada = JOptionPane.showInputDialog("Insira o nome do Cliente:");
            
            if (entrada != null && !entrada.equals("")) {
                EditarCliente.editNomeCliente.setText("Nome do cliente: " + entrada);
                Clientes.clientes.get(Clientes.indiceCliente).setNome(entrada);
            }
        }     
    }

    public static class EventoBotaoEditarTelefone implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String entrada;

            entrada = JOptionPane.showInputDialog("Insira o número de telefone:");

            if (entrada != null && !entrada.equals("")) {
                EditarCliente.editTelefoneCliente.setText("Número de telefone: " + entrada);
                Clientes.clientes.get(Clientes.indiceCliente).setNumeroTelefone(entrada);
            }
        }     
    }

    public static class EventoBotaoEditarEmail implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String entrada;

            entrada = JOptionPane.showInputDialog("Insira o e-mail do cliente:");

            if (entrada != null && !entrada.equals("")) {
                EditarCliente.editEmailCliente.setText("E-mail: " + entrada);
                Clientes.clientes.get(Clientes.indiceCliente).setEmail(entrada);
            }
        }     
    }

    public static class EventoBotaoFazerReserva implements ActionListener {
        String[] opcs = {"Cachorro", "  Gato  ", "Cancelar"};
        int escolha;
        boolean hospedeIsCachorro = false;

        @Override
        public void actionPerformed(ActionEvent arg0) {
            escolha = JOptionPane.showOptionDialog(null, "Você quer fazer reserva de cachorro ou de gato?", null, 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                        null, opcs, null);

            if (escolha == 0) {
                hospedeIsCachorro = true;
            } else {
                if (escolha == 1) {
                    hospedeIsCachorro = false;
                }
            }

            if (escolha != 2) {
                Home.telaFazerReserva = new FazerReserva(hospedeIsCachorro);
            }
        }     
    }

    public static class EventoBotaoVerReserva implements ActionListener {
        String[] opcs = {"Cachorro", "  Gato  ", "Cancelar"};
        int escolha;
        boolean hospedeIsCachorro = false;

        @Override
        public void actionPerformed(ActionEvent arg0) {
            escolha = JOptionPane.showOptionDialog(null, "Você quer fazer reserva de cachorro ou de gato?", null, 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                        null, opcs, null);

            if (escolha == 0) {
                hospedeIsCachorro = true;
            } else {
                if (escolha == 1) {
                    hospedeIsCachorro = false;
                }
            }

            if (escolha != 2) {
                Reservas.hospedeIsCachorro = hospedeIsCachorro;
                Home.telaReservas = new Reservas(Clientes.clientes.get(Clientes.indiceCliente).getNumeroIdentificacao(), Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos());
            }
        }     
    }
}
