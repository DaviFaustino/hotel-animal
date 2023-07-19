package com.animal.hotel.view.telas;

import com.animal.hotel.view.funcoes.EventosCadastrarCliente.*;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Point;

public class CadastrarCliente extends JFrame {
    public static JButton voltarCadCliente, salvarCadCliente;
    public static ArrayList<JLabel> labelsCadCliente;
    public static ArrayList<JTextField> entradasTextoCadCliente;
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


        addComponentListener(new EventosTela());

        
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
        voltarCadCliente.addActionListener(new EventoBotaoVoltar());

        salvarCadCliente = new JButton("Salvar");
        salvarCadCliente.addActionListener(new EventoBotaoSalvar());


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
