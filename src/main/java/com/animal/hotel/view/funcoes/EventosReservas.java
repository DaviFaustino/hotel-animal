package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.componentes.JButtonHospede;
import com.animal.hotel.view.telas.DialogVerReserva;
import com.animal.hotel.view.telas.FrameEditarReserva;
import com.animal.hotel.view.telas.FrameReservas;
import com.animal.hotel.view.telas.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosReservas {
    public static class EventoBotaoReserva implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            FrameReservas.indiceHospede = ((JButtonHospede) arg0.getSource()).getIndiceHospede();

            if (Home.alterar) {
                Home.telaEditarReserva = new FrameEditarReserva(((JButtonHospede) arg0.getSource()).getIndiceButton());
            } else {
                new DialogVerReserva();
            }
        }
    }
}
