package com.animal.hotel.view.telas;

import com.animal.hotel.model.Cachorro;
import com.animal.hotel.model.Gato;

import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class DialogVerReserva extends JDialog {
    
    public DialogVerReserva() {
        setTitle("Dados da reserva");
        setSize(380, 300);
        setLocation(((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 200), ((int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 150));
        
        JPanel painelVerReserva = new JPanel();

        painelVerReserva.add(new JSeparator());
        painelVerReserva.add(new JLabel("Número de ID responsável: " + Reservas.hospedes.get(Reservas.indiceHospede).getNumeroIdentificacaoResponsavel()));
        painelVerReserva.add(new JLabel("Número de identificação: " + Reservas.hospedes.get(Reservas.indiceHospede).getNumeroIdentificacao()));
        painelVerReserva.add(new JLabel("Nome do hospede: " + Reservas.hospedes.get(Reservas.indiceHospede).getNome()));
        painelVerReserva.add(new JLabel("Fase da vida: " + Reservas.hospedes.get(Reservas.indiceHospede).getFaseDaVida()));
        painelVerReserva.add(new JLabel("Ração do hospede: " + Reservas.hospedes.get(Reservas.indiceHospede).getNomeRacao()));
        painelVerReserva.add(new JLabel("Quantidade de ração (g): " + Reservas.hospedes.get(Reservas.indiceHospede).getQuantRacaoGramas()));
        painelVerReserva.add(new JLabel("Gasto com ração: " + Reservas.hospedes.get(Reservas.indiceHospede).getGastoComRacao()));
        painelVerReserva.add(new JLabel("Pode socializar com outros animais: " + Reservas.hospedes.get(Reservas.indiceHospede).isPodeSocializarComOutros()));
        painelVerReserva.add(new JLabel("Data de check-in: " + Arrays.toString(Reservas.hospedes.get(Reservas.indiceHospede).getDataCheckIn())));
        painelVerReserva.add(new JLabel("Data de check-out: " + Arrays.toString(Reservas.hospedes.get(Reservas.indiceHospede).getDataCheckOut())));
        if (Reservas.hospedes.get(0).getClass() == Cachorro.class) {
            Cachorro cachorro = (Cachorro) Reservas.hospedes.get(Reservas.indiceHospede);
            add(new JLabel("Pode passear: " + cachorro.getPodePassear()));
        } else {
            Gato gato = (Gato) Reservas.hospedes.get(Reservas.indiceHospede);
            add(new JLabel("Quantidade de companheiros: " + gato.getQuantCompanheirosGatil()));
        }
        painelVerReserva.add(new JLabel("Custo do hospede:" + Reservas.hospedes.get(Reservas.indiceHospede).getCustoHospede()));
        painelVerReserva.add(new JSeparator());

        for (int i = 0; i < painelVerReserva.getComponentCount() - 1; i++) {
            painelVerReserva.getComponent(i + 1).setFont(Home.fontePadrao);
        }

        add(painelVerReserva);
        setVisible(true);
    }
}
