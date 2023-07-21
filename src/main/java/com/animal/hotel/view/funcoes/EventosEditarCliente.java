package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.Clientes;
import com.animal.hotel.view.telas.EditarCliente;
import com.animal.hotel.view.telas.FazerReserva;
import com.animal.hotel.view.telas.Reservas;
import com.animal.hotel.view.telas.Home;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EventosEditarCliente {
    public static class EventosTela implements ComponentListener {
        @Override
        public void componentHidden(ComponentEvent arg0) {}

        @Override
        public void componentMoved(ComponentEvent arg0) {
            EditarCliente.posicaoTela = ((JFrame) arg0.getSource()).getLocation();
        }

        @Override
        public void componentResized(ComponentEvent arg0) {
            EditarCliente.tamanhoLaterais = (((JFrame) arg0.getSource()).getWidth() - EditarCliente.larguraPainelEditCliente) / 2;
            EditarCliente.tamanhoCimaBaixo = (((JFrame) arg0.getSource()).getHeight() - EditarCliente.alturaPainelEditCliente) / 2;
            EditarCliente.paineisEditarCliente.get(1).setPreferredSize(new Dimension(EditarCliente.tamanhoLaterais, EditarCliente.tamanhoLaterais));
            EditarCliente.paineisEditarCliente.get(2).setPreferredSize(new Dimension(EditarCliente.tamanhoLaterais, EditarCliente.tamanhoLaterais));
            EditarCliente.paineisEditarCliente.get(3).setPreferredSize(new Dimension(EditarCliente.tamanhoCimaBaixo, EditarCliente.tamanhoCimaBaixo));
            EditarCliente.paineisEditarCliente.get(4).setPreferredSize(new Dimension(EditarCliente.tamanhoCimaBaixo, EditarCliente.tamanhoCimaBaixo));
            EditarCliente.telaWidth = ((JFrame) arg0.getSource()).getWidth();
            EditarCliente.telaHeight = ((JFrame) arg0.getSource()).getHeight();
        }

        @Override
        public void componentShown(ComponentEvent arg0) {}
    }

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

    public static class EventoBotaoVoltar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.telaClientes.setLocation(EditarCliente.posicaoTela);
            Home.telaClientes.setSize(EditarCliente.telaWidth, EditarCliente.telaHeight);

            JPanel dados = new JPanel();
            dados.setLayout(new BoxLayout(dados, BoxLayout.Y_AXIS));
            dados.add(new JLabel("Id do cliente: " + Clientes.clientes.get(Clientes.indiceCliente).getNumeroIdentificacao()));
            dados.add(new JLabel("Nome do cliente: " + Clientes.clientes.get(Clientes.indiceCliente).getNome()));
            dados.add(new JLabel("Debito do cliente: " + String.valueOf(Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[0] + Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[1])));
            
            Clientes.opcoesClientes.get(Clientes.indiceCliente).remove(0);
            Clientes.opcoesClientes.get(Clientes.indiceCliente).add(dados);

            Home.telaClientes.setVisible(true);
            Home.telaEditarCliente.dispose();
        }
    }
}
