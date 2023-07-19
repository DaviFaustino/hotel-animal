package com.animal.hotel.view.telas;

import com.animal.hotel.view.funcoes.EventosEditarCliente.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class EditarCliente extends JFrame {
    public static ArrayList<JPanel> paineisEditarCliente;
    static int larguraPainelEditCliente = 400;
    static int alturaPainelEditCliente = 300;
    public static int telaWidth;
    public static int telaHeight;
    public static Point posicaoTela;
    public int tamanhoLaterais;
    public int tamanhoCimaBaixo;
    public static JButton editNomeCliente, editTelefoneCliente, editEmailCliente, fazerReserva, verReservas;

    public EditarCliente() {
        posicaoTela = Clientes.posicaoTela;
        telaWidth = Clientes.telaWidth;
        telaHeight = Clientes.telaHeight;

        setTitle("Detalhes Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(telaWidth, telaHeight);
        setLocation(posicaoTela);
        setLayout(new BorderLayout());
        setVisible(true);
        Home.telaClientes.setVisible(false);
        
        paineisEditarCliente = new ArrayList<>();
        paineisEditarCliente.add(new JPanel());
        paineisEditarCliente.get(0).setLayout(new BoxLayout(paineisEditarCliente.get(0), BoxLayout.Y_AXIS));
        paineisEditarCliente.add(new JPanel());
        paineisEditarCliente.add(new JPanel());
        paineisEditarCliente.add(new JPanel());
        paineisEditarCliente.add(new JPanel());
        
        tamanhoLaterais = (getWidth() - larguraPainelEditCliente) / 2;
        tamanhoCimaBaixo = (getHeight() - alturaPainelEditCliente) / 2;

        paineisEditarCliente.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisEditarCliente.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisEditarCliente.get(3).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
        paineisEditarCliente.get(4).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));

        addComponentListener(new ComponentListener() {
            @Override
            public void componentHidden(ComponentEvent arg0) {}

            @Override
            public void componentMoved(ComponentEvent arg0) {
                posicaoTela = getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                tamanhoLaterais = (getWidth() - larguraPainelEditCliente) / 2;
                tamanhoCimaBaixo = (getHeight() - alturaPainelEditCliente) / 2;
                paineisEditarCliente.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                paineisEditarCliente.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                paineisEditarCliente.get(3).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
                paineisEditarCliente.get(4).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
                telaWidth = getWidth();
                telaHeight = getHeight();
            }

            @Override
            public void componentShown(ComponentEvent arg0) {}
        });


        paineisEditarCliente.get(0).add(new JLabel("Escolha um dado para editar"));
        paineisEditarCliente.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarCliente.get(0).add(new JLabel("Número de identificação: " + Clientes.clientes.get(Clientes.indiceCliente).getNumeroIdentificacao()));
        paineisEditarCliente.get(0).getComponent(2).setFont(Home.fontePadrao);
        paineisEditarCliente.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarCliente.get(0).add(editNomeCliente = new JButton("Nome do cliente: " + Clientes.clientes.get(Clientes.indiceCliente).getNome()));
        paineisEditarCliente.get(0).add(editTelefoneCliente = new JButton("Número de telefone: " + Clientes.clientes.get(Clientes.indiceCliente).getNumeroTelefone()));
        paineisEditarCliente.get(0).add(editEmailCliente = new JButton("E-mail: " + Clientes.clientes.get(Clientes.indiceCliente).getEmail()));
        paineisEditarCliente.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarCliente.get(0).add(new JLabel("Débito: " + (Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[0] + Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[1])));
        paineisEditarCliente.get(0).getComponent(8).setFont(Home.fontePadrao);
        paineisEditarCliente.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarCliente.get(0).add(fazerReserva = new JButton("Fazer reservas de hospedes"));
        paineisEditarCliente.get(0).add(verReservas = new JButton("Ver reservas do cliente"));

        editNomeCliente.addActionListener(new EventoBotaoEditarNome());
        editTelefoneCliente.addActionListener(new EventoBotaoEditarTelefone());
        editEmailCliente.addActionListener(new EventoBotaoEditarEmail());
        fazerReserva.addActionListener(new EventoBotaoFazerReserva());
        verReservas.addActionListener(new EventoBotaoVerReserva());

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Home.telaClientes.setLocation(posicaoTela);
                Home.telaClientes.setSize(telaWidth, telaHeight);

                JPanel dados = new JPanel();
                dados.setLayout(new BoxLayout(dados, BoxLayout.Y_AXIS));
                dados.add(new JLabel("Id do cliente: " + Clientes.clientes.get(Clientes.indiceCliente).getNumeroIdentificacao()));
                dados.add(new JLabel("Nome do cliente: " + Clientes.clientes.get(Clientes.indiceCliente).getNome()));
                dados.add(new JLabel("Debito do cliente: " + String.valueOf(Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[0] + Clientes.clientes.get(Clientes.indiceCliente).getCustoCachorrosGatos()[1])));
                
                Clientes.opcoesClientes.get(Clientes.indiceCliente).remove(0);
                Clientes.opcoesClientes.get(Clientes.indiceCliente).add(dados);

                Home.telaClientes.setVisible(true);
                dispose();
            }
        });

        paineisEditarCliente.get(4).add(voltar);

        add(paineisEditarCliente.get(0), BorderLayout.CENTER); 
        add(paineisEditarCliente.get(1), BorderLayout.WEST); 
        add(paineisEditarCliente.get(2), BorderLayout.EAST); 
        add(paineisEditarCliente.get(3), BorderLayout.NORTH); 
        add(paineisEditarCliente.get(4), BorderLayout.SOUTH);
    }
}
