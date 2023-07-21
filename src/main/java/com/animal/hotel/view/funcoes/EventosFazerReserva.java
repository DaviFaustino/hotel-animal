package com.animal.hotel.view.funcoes;

import com.animal.hotel.model.Cachorro;
import com.animal.hotel.model.Gato;
import com.animal.hotel.model.Hospede;
import com.animal.hotel.uteis.Arquivos;
import com.animal.hotel.uteis.InputDados;
import com.animal.hotel.view.telas.Clientes;
import com.animal.hotel.view.telas.FazerReserva;
import com.animal.hotel.view.telas.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;

import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EventosFazerReserva {
    public static class EventosTela implements ComponentListener {
        @Override
        public void componentHidden(ComponentEvent arg0) {}

        @Override
        public void componentMoved(ComponentEvent arg0) {
            Home.posicaoTela = ((JFrame) arg0.getSource()).getLocation();
        }

        @Override
        public void componentResized(ComponentEvent arg0) {
            int centroX = ((JFrame) arg0.getSource()).getWidth() / 2;
            int centroY = ((JFrame) arg0.getSource()).getHeight() / 2;
            FazerReserva.labelsFazerReserva.get(0).setBounds(centroX - 120, centroY - 200, 200, 30);
            FazerReserva.labelsFazerReserva.get(1).setBounds(centroX - 82, centroY - 165, 200, 30);
            FazerReserva.labelsFazerReserva.get(2).setBounds(centroX - 124, centroY - 130, 200, 30);
            FazerReserva.labelsFazerReserva.get(3).setBounds(centroX - 164, centroY - 95, 250, 30);
            FazerReserva.labelsFazerReserva.get(4).setBounds(centroX - 113, centroY - 60, 200, 30);
            FazerReserva.labelsFazerReserva.get(5).setBounds(centroX - 122, centroY - 25, 200, 30);
            FazerReserva.labelsFazerReserva.get(6).setBounds(centroX - 250, centroY + 10, 300, 30);
            if (FazerReserva.hospedeIsCachorro) {
                FazerReserva.labelsFazerReserva.get(7).setBounds(centroX - 137, centroY + 45, 200, 30);
                FazerReserva.simPassear.setBounds(centroX + 85, centroY + 45, 70, 25);
                FazerReserva.naoPassear.setBounds(centroX + 15, centroY + 45, 70, 25);
            } else {
                FazerReserva.labelsFazerReserva.get(7).setBounds(centroX - 260, centroY + 45, 300, 30);
                FazerReserva.menuQuantCompG.setBounds(centroX + 15, centroY + 45, 50, 25);
            }
            FazerReserva.entradasTextoReservaHospede.get(0).setBounds(centroX + 15, centroY - 200, 300, 25);
            FazerReserva.filhote.setBounds(centroX + 15, centroY - 165, 80, 25);
            FazerReserva.adulto.setBounds(centroX + 95, centroY - 165, 80, 25);
            FazerReserva.idoso.setBounds(centroX + 175, centroY - 165, 80, 25);
            FazerReserva.menuRacoes.setBounds(centroX + 15, centroY - 130, 150, 25);
            FazerReserva.entradasTextoReservaHospede.get(1).setBounds(centroX + 15, centroY - 95, 100, 25);
            FazerReserva.entradasTextoReservaHospede.get(2).setBounds(centroX + 15, centroY - 60, 25, 25);
            FazerReserva.entradasTextoReservaHospede.get(3).setBounds(centroX + 40, centroY - 60, 25, 25);
            FazerReserva.entradasTextoReservaHospede.get(4).setBounds(centroX + 65, centroY - 60, 50, 25);
            FazerReserva.entradasTextoReservaHospede.get(5).setBounds(centroX + 15, centroY - 25, 25, 25);
            FazerReserva.entradasTextoReservaHospede.get(6).setBounds(centroX + 40, centroY - 25, 25, 25);
            FazerReserva.entradasTextoReservaHospede.get(7).setBounds(centroX + 65, centroY - 25, 50, 25);
            FazerReserva.simSocia.setBounds(centroX + 85, centroY + 10, 70, 25);
            FazerReserva.naoSocia.setBounds(centroX + 15, centroY + 10, 70, 25);
            FazerReserva.voltarReservaHospede.setBounds(centroX - 200, centroY + 100, 100, 30);
            FazerReserva.salvarReservaHospede.setBounds(centroX + 50, centroY + 100, 100, 30);
        }

        @Override
        public void componentShown(ComponentEvent arg0) {}
    }

    public static class EventoBotaoSalvar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Hospede hospede;
            if (FazerReserva.hospedeIsCachorro) {
                hospede = new Cachorro();
            } else {
                hospede = new Gato();
            }

            hospede.setNumeroIdentificacaoResponsavel(Clientes.clientes.get(Clientes.indiceCliente).getNumeroIdentificacao());
            hospede.setNome(FazerReserva.entradasTextoReservaHospede.get(0).getText());
            if (FazerReserva.filhote.isSelected()) {
                hospede.setFaseDaVida("Filhote");
            } else {
                if (FazerReserva.adulto.isSelected()) {
                    hospede.setFaseDaVida("Adulto");
                } else {
                    hospede.setFaseDaVida("Idoso");
                }
            }
            hospede.setPrecoRacaoKilo(FazerReserva.opcoesRacoes.get((String) FazerReserva.menuRacoes.getSelectedItem()));
            hospede.setNomeRacao((String) FazerReserva.menuRacoes.getSelectedItem());
            hospede.setQuantRacaoGramas(Integer.parseInt(FazerReserva.entradasTextoReservaHospede.get(1).getText()));
            for (int i = 0; i < 3; i++) {
                hospede.getDataCheckIn()[i] = FazerReserva.entradasTextoReservaHospede.get(i + 2).getText();
                hospede.getDataCheckOut()[i] = FazerReserva.entradasTextoReservaHospede.get(i + 5).getText();
            }
            if (FazerReserva.simSocia.isSelected()) {
                hospede.setPodeSocializarComOutros(true);
            } else {
                hospede.setPodeSocializarComOutros(false);
            }

            if (FazerReserva.hospedeIsCachorro) {
                Cachorro cachorro = (Cachorro) hospede;

                if (FazerReserva.simPassear.isSelected()) {
                    cachorro.setPodePassear(true);
                } else {
                    cachorro.setPodePassear(false);
                }
                
                String[] opcs = {"Sim", "Não"};
                int escolha = JOptionPane.showOptionDialog(null, "O custo é de "+ cachorro.calculaCusto() +". Você realmente deseja fazer a reserva?", null, 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                        null, opcs, null);

                if (escolha == 0) {
                    cachorro.setNumeroIdentificacao(InputDados.numeroIdentificacao(true));

                    try {
                        Arquivos.addAosArquivos(cachorro);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FazerReserva.voltarReservaHospede.doClick();
                }
            } else {
                Gato gato = (Gato) hospede;

                gato.setQuantCompanheirosGatil(Integer.parseInt((String) FazerReserva.menuQuantCompG.getSelectedItem()));

                String[] opcs = {"Sim", "Não"};
                int escolha = JOptionPane.showOptionDialog(null, "O custo é de "+ gato.calculaCusto() +". Você realmente deseja fazer a reserva?", null, 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                        null, opcs, null);

                if (escolha == 0) {
                    gato.setNumeroIdentificacao(InputDados.numeroIdentificacao(true));

                    try {
                        Arquivos.addAosArquivos(gato);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FazerReserva.voltarReservaHospede.doClick();
                }
            }
        }
    }

    public static class EventoBotaoVoltar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.telaEditarCliente.setLocation(Home.posicaoTela);
            Home.telaEditarCliente.setSize(Home.telaWidth, Home.telaHeight);
            Home.telaEditarCliente.setVisible(true);
            Home.telaFazerReserva.dispose();
        }
    }
}
