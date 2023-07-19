package com.animal.hotel.view.telas;

import com.animal.hotel.model.Cliente;
import com.animal.hotel.uteis.Arquivos;
import com.animal.hotel.uteis.InputDados;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.Point;

public class CadastrarCliente extends JFrame {
    static JButton voltarCadCliente, salvarCadCliente;
    static ArrayList<JLabel> labelsCadCliente;
    static ArrayList<JTextField> entradasTextoCadCliente;
    public static int telaWidth;
    public static int telaHeight;
    public static Point posicaoTela;

    public CadastrarCliente() {
        posicaoTela = Home.posicaoTela;
        telaWidth = Home.telaWidth;
        telaHeight = Home.telaHeight;

        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(telaWidth, telaHeight);
        setLocation(posicaoTela);
        setLayout(null);


        addComponentListener(new ComponentListener() {
            @Override
            public void componentHidden(ComponentEvent arg0) {}

            @Override
            public void componentMoved(ComponentEvent arg0) {
                posicaoTela = getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                int centroX = getWidth() / 2;
                int centroY = getHeight() / 2;
                labelsCadCliente.get(0).setBounds(centroX - 220, centroY - 80, 200, 30);
                labelsCadCliente.get(1).setBounds(centroX - 250, centroY - 50, 200, 30);
                labelsCadCliente.get(2).setBounds(centroX - 220, centroY - 20, 200, 30);
                entradasTextoCadCliente.get(0).setBounds(centroX - 80, centroY - 76, 350, 20);
                entradasTextoCadCliente.get(1).setBounds(centroX - 80, centroY - 46, 350, 20);
                entradasTextoCadCliente.get(2).setBounds(centroX - 80, centroY - 16, 350, 20);
                voltarCadCliente.setBounds(centroX + 70, centroY + 100, 100, 30);
                salvarCadCliente.setBounds(centroX - 70, centroY + 100, 100, 30);
                telaWidth = getWidth();
                telaHeight = getHeight();
            }

            @Override
            public void componentShown(ComponentEvent arg0) {}
        });

        
        labelsCadCliente = new ArrayList<>();
        labelsCadCliente.add(new JLabel("Nome do cliente"));
        labelsCadCliente.add(new JLabel("Número de telefone"));
        labelsCadCliente.add(new JLabel("E-mail do cliente"));
        labelsCadCliente.get(0).setFont(Home.fontePadrao);
        labelsCadCliente.get(1).setFont(Home.fontePadrao);
        labelsCadCliente.get(2).setFont(Home.fontePadrao);

        
        entradasTextoCadCliente = new ArrayList<>();
        entradasTextoCadCliente.add(new JTextField());
        entradasTextoCadCliente.add(new JTextField());
        entradasTextoCadCliente.add(new JTextField());


        voltarCadCliente = new JButton("Volta");
        voltarCadCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Home.telaPrincipal.setLocation(posicaoTela);
                Home.telaPrincipal.setSize(telaWidth, telaHeight);
                Home.telaPrincipal.setVisible(true);
                dispose();
            }
        });

        salvarCadCliente = new JButton("Salvar");
        salvarCadCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Cliente cliente = new Cliente();
                cliente.setNome(entradasTextoCadCliente.get(0).getText());
                cliente.setNumeroTelefone(entradasTextoCadCliente.get(1).getText());
                cliente.setEmail(entradasTextoCadCliente.get(2).getText());

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
                    
                    for (JTextField entrada: entradasTextoCadCliente) {
                        entrada.setText("");
                    }
                }
            }
        });


        add(labelsCadCliente.get(0));
        add(labelsCadCliente.get(1));
        add(labelsCadCliente.get(2));
        add(entradasTextoCadCliente.get(0));
        add(entradasTextoCadCliente.get(1));
        add(entradasTextoCadCliente.get(2));
        add(voltarCadCliente);
        add(salvarCadCliente);


        setVisible(true);
        Home.telaPrincipal.setVisible(false);
    }
}
