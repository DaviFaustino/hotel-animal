package com.animal.hotel.view.funcoes;

import com.animal.hotel.uteis.Arquivos;
import com.animal.hotel.view.componentes.JButtonHospede;
import com.animal.hotel.view.telas.Clientes;
import com.animal.hotel.view.telas.DialogVerReserva;
import com.animal.hotel.view.telas.EditarCliente;
import com.animal.hotel.view.telas.EditarReserva;
import com.animal.hotel.view.telas.Reservas;
import com.animal.hotel.view.telas.Home;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventosReservas {
    public static class EventosTela implements ComponentListener {
        @Override
            public void componentHidden(ComponentEvent arg0) {}

            @Override
            public void componentMoved(ComponentEvent arg0) {
                Home.posicaoTela = ((JFrame) arg0.getSource()).getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                JFrame telaComponent = ((JFrame) arg0.getSource());
                Reservas.tamanhoLaterais = (telaComponent.getWidth() - Reservas.larguraPainelHospedes) / 2;
                Reservas.paineisHospedes.get(1).setPreferredSize(new Dimension(Reservas.tamanhoLaterais, Reservas.tamanhoLaterais));
                Reservas.paineisHospedes.get(2).setPreferredSize(new Dimension(Reservas.tamanhoLaterais, Reservas.tamanhoLaterais));
                Home.telaWidth = telaComponent.getWidth();
                Home.telaHeight = telaComponent.getHeight();
            }

            @Override
            public void componentShown(ComponentEvent arg0) {}
    }

    public static class EventoBotaoReserva implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Reservas.indiceHospede = ((JButtonHospede) arg0.getSource()).getIndiceHospede();

            if (Home.alterar) {
                Home.telaEditarReserva = new EditarReserva(((JButtonHospede) arg0.getSource()).getIndiceButton());
            } else {
                new DialogVerReserva();
            }
        }
    }

    public static class EventoBotaoVoltar implements ActionListener {
        @Override
            public void actionPerformed(ActionEvent arg0) {
                if (Home.alterar) {
                    try {
                        float somaCustos = 0f;
                        for (int i = 0; i < Reservas.hospedes.size(); i++) {
                            if (Reservas.hospedes.get(i).getNumeroIdentificacaoResponsavel().equals(Clientes.clientes.get(Clientes.indiceCliente).getNumeroIdentificacao())) {
                                somaCustos += Reservas.hospedes.get(i).getCustoHospede();
                            }
                        }

                        if (Reservas.hospedeIsCachorro) {
                            Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[0] = somaCustos;
                            Arquivos.salvar(Reservas.hospedes, 0);
                        } else {
                            Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[1] = somaCustos;
                            Arquivos.salvar(Reservas.hospedes, 1);
                        }
                        
                        ((JLabel) EditarCliente.paineisEditarCliente.get(0).getComponent(8)).setText("Débito: " + (Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[0] + Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[1]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Home.telaEditarCliente.setLocation(Home.posicaoTela);
                    Home.telaEditarCliente.setSize(Home.telaWidth, Home.telaHeight);
                    Home.telaEditarCliente.setVisible(true);
                    Home.telaReservas.dispose();
                } else {
                    Home.telaPrincipal.setLocation(Home.posicaoTela);
                    Home.telaPrincipal.setSize(Home.telaWidth, Home.telaHeight);
                    Home.telaPrincipal.setVisible(true);
                    Home.telaReservas.dispose();
                }
            }
    }
}
