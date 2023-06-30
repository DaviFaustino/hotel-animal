package com.animal.hotel;

import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class DialogVerReserva extends JDialog {
    

    DialogVerReserva() {
        setTitle("Dados da reserva");
        setSize(380, 300);
        setLocation(((int) Sistema.posicaoTela.getX() + Sistema.telaWidth / 2 - 200), ((int) Sistema.posicaoTela.getY() + Sistema.telaHeight / 2 - 150));
        
        JPanel painelVerReserva = new JPanel();

        painelVerReserva.add(new JSeparator());
        painelVerReserva.add(new JLabel("Número de ID responsável: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNumeroIdentificacaoResponsavel()));
        painelVerReserva.add(new JLabel("Número de identificação: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNumeroIdentificacao()));
        painelVerReserva.add(new JLabel("Nome do hospede: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNome()));
        painelVerReserva.add(new JLabel("Fase da vida: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getFaseDaVida()));
        painelVerReserva.add(new JLabel("Ração do hospede: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNomeRacao()));
        painelVerReserva.add(new JLabel("Quantidade de ração (g): " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getQuantRacaoGramas()));
        painelVerReserva.add(new JLabel("Gasto com ração: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getGastoComRacao()));
        painelVerReserva.add(new JLabel("Pode socializar com outros animais: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).isPodeSocializarComOutros()));
        painelVerReserva.add(new JLabel("Data de check-in: " + Arrays.toString(FrameReservas.hospedes.get(FrameReservas.indiceHospede).getDataCheckIn())));
        painelVerReserva.add(new JLabel("Data de check-out: " + Arrays.toString(FrameReservas.hospedes.get(FrameReservas.indiceHospede).getDataCheckOut())));
        if (FrameReservas.hospedes.get(0).getClass() == Cachorro.class) {
            Cachorro cachorro = (Cachorro) FrameReservas.hospedes.get(FrameReservas.indiceHospede);
            add(new JLabel("Pode passear: " + cachorro.getPodePassear()));
        } else {
            Gato gato = (Gato) FrameReservas.hospedes.get(FrameReservas.indiceHospede);
            add(new JLabel("Quantidade de companheiros: " + gato.getQuantCompanheirosGatil()));
        }
        painelVerReserva.add(new JLabel("Custo do hospede:" + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getCustoHospede()));
        painelVerReserva.add(new JSeparator());

        for (int i = 0; i < painelVerReserva.getComponentCount() - 1; i++) {
            painelVerReserva.getComponent(i + 1).setFont(Sistema.fontePadrao);
        }

        add(painelVerReserva);
        setVisible(true);
    }
}
