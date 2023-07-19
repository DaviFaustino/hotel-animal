package com.animal.hotel.view.funcoes;

import com.animal.hotel.model.Cliente;
import com.animal.hotel.view.telas.CadastrarCliente;
import com.animal.hotel.view.telas.Home;
import com.animal.hotel.uteis.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EventosCadastrarCliente {
    public static class EventosTela implements ComponentListener {

        @Override
        public void componentHidden(ComponentEvent arg0) {}

        @Override
        public void componentMoved(ComponentEvent arg0) {
            CadastrarCliente.posicaoTela = ((JFrame) arg0.getSource()).getLocation();
        }

        @Override
        public void componentResized(ComponentEvent arg0) {
            int centroX = ((JFrame) arg0.getSource()).getWidth() / 2;
            int centroY = ((JFrame) arg0.getSource()).getHeight() / 2;
            CadastrarCliente.labelsCadCliente.get(0).setBounds(centroX - 220, centroY - 80, 200, 30);
            CadastrarCliente.labelsCadCliente.get(1).setBounds(centroX - 250, centroY - 50, 200, 30);
            CadastrarCliente.labelsCadCliente.get(2).setBounds(centroX - 220, centroY - 20, 200, 30);
            CadastrarCliente.entradasTextoCadCliente.get(0).setBounds(centroX - 80, centroY - 76, 350, 20);
            CadastrarCliente.entradasTextoCadCliente.get(1).setBounds(centroX - 80, centroY - 46, 350, 20);
            CadastrarCliente.entradasTextoCadCliente.get(2).setBounds(centroX - 80, centroY - 16, 350, 20);
            CadastrarCliente.voltarCadCliente.setBounds(centroX + 70, centroY + 100, 100, 30);
            CadastrarCliente.salvarCadCliente.setBounds(centroX - 70, centroY + 100, 100, 30);
            CadastrarCliente.telaWidth = ((JFrame) arg0.getSource()).getWidth();
            CadastrarCliente.telaHeight = ((JFrame) arg0.getSource()).getHeight();
        }

        @Override
        public void componentShown(ComponentEvent arg0) {}
    }

    public static class EventoBotaoVoltar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Home.telaPrincipal.setLocation(CadastrarCliente.posicaoTela);
                Home.telaPrincipal.setSize(CadastrarCliente.telaWidth, CadastrarCliente.telaHeight);
                Home.telaPrincipal.setVisible(true);
                Home.telaCadastrarCliente.dispose();
        }
    }

    public static class EventoBotaoSalvar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Cliente cliente = new Cliente();
            cliente.setNome(CadastrarCliente.entradasTextoCadCliente.get(0).getText());
            cliente.setNumeroTelefone(CadastrarCliente.entradasTextoCadCliente.get(1).getText());
            cliente.setEmail(CadastrarCliente.entradasTextoCadCliente.get(2).getText());

            String[] opcs = {"Sim", "Não"};
            int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja realizar o cadastro?", null, 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                        null, opcs, null);

            if (escolha == 0) {
                cliente.setNumeroIdentificacao(InputDados.numeroIdentificacao(false));

                try {
                    Arquivos.addAosArquivos(cliente);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                for (JTextField entrada: CadastrarCliente.entradasTextoCadCliente) {
                    entrada.setText("");
                }
            }
        }
    }
}
