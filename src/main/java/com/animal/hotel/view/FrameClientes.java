package com.animal.hotel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

public class FrameClientes extends JFrame {
    public int larguraPainelCliente = 400;
    public static List<Cliente> clientes;
    public static ArrayList<JButtonCliente> opcoesClientes;
    public ArrayList<JPanel> paineisClientes;
    public static int telaWidth;
    public static int telaHeight;
    public static Point posicaoTela;
    public int tamanhoLaterais;
    public int tamanhoCimaBaixo;
    public static int indiceCliente;

    FrameClientes() {
        posicaoTela = Sistema.posicaoTela;
        telaWidth = Sistema.telaWidth;
        telaHeight = Sistema.telaHeight;

        String jsonString = "";

        /* Primeiro é lido o arquivo json que possui todos os cadastros de clientes
        * armazenados no sistema e então transformado em uma List de objetos Cliente.
        *  */
        try {
            Path path = Paths.get("src/arquivos/clientes.json");
            jsonString = Files.readString(path);
        } catch (IOException e) {
            // e.printStackTrace();
            jsonString = "[]";
        }

        Gson gson = new Gson();
        clientes = gson.fromJson(jsonString, new TypeToken<List<Cliente>>() {}.getType());

        setTitle("Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(telaWidth, telaHeight);
        setLocation(posicaoTela);
        setLayout(new BorderLayout(0, 20));
        setVisible(true);
        Sistema.telaPrincipal.setVisible(false);
        
        paineisClientes = new ArrayList<>();
        paineisClientes.add(new JPanel());
        paineisClientes.get(0).setLayout(new BoxLayout(paineisClientes.get(0), BoxLayout.Y_AXIS));
        paineisClientes.add(new JPanel());
        paineisClientes.add(new JPanel());
        paineisClientes.add(new JPanel());
        paineisClientes.add(new JPanel());
        
        tamanhoLaterais = (getWidth() - larguraPainelCliente) / 2;
        tamanhoCimaBaixo = 60;

        paineisClientes.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisClientes.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisClientes.get(3).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
        paineisClientes.get(4).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));

        addComponentListener(new ComponentListener() {
            @Override
            public void componentHidden(ComponentEvent arg0) {}

            @Override
            public void componentMoved(ComponentEvent arg0) {
                posicaoTela = getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                tamanhoLaterais = (getWidth() - larguraPainelCliente) / 2;
                paineisClientes.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                paineisClientes.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                telaWidth = getWidth();
                telaHeight = getHeight();
            }

            @Override
            public void componentShown(ComponentEvent arg0) {}
        });

        BotaoEventosClientes eventoCliente = new BotaoEventosClientes();

        opcoesClientes = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            opcoesClientes.add(new JButtonCliente());
            opcoesClientes.get(i).setCliente(clientes.get(i));;
            opcoesClientes.get(i).addActionListener(eventoCliente);

            JPanel dados = new JPanel();
            opcoesClientes.get(i).add(dados);
            dados.setLayout(new BoxLayout(dados, BoxLayout.Y_AXIS));
            dados.add(new JLabel("Id do cliente: " + clientes.get(i).getNumeroIdentificacao()));
            dados.add(new JLabel("Nome do cliente: " + clientes.get(i).getNome()));
            dados.add(new JLabel("Debito do cliente: " + String.valueOf(clientes.get(i).getCustoCachorrosGatos()[0] + clientes.get(i).getCustoCachorrosGatos()[1])));
        }

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Arquivos.salvar(clientes, 2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Sistema.telaPrincipal.setLocation(posicaoTela);
                Sistema.telaPrincipal.setSize(telaWidth, telaHeight);
                Sistema.telaPrincipal.setVisible(true);
                dispose();
            }
        });

        add(new JScrollPane(paineisClientes.get(0)), BorderLayout.CENTER); 
        add(paineisClientes.get(1), BorderLayout.WEST); 
        add(paineisClientes.get(2), BorderLayout.EAST);
        add(paineisClientes.get(3), BorderLayout.NORTH);
        add(paineisClientes.get(4), BorderLayout.SOUTH);
        
        for (JButton opcaoCliente: opcoesClientes) {
            paineisClientes.get(0).add(opcaoCliente);
        }
        
        paineisClientes.get(4).add(voltar);
    }

    private static class BotaoEventosClientes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            indiceCliente = opcoesClientes.indexOf(arg0.getSource());
            Sistema.telaEditarCliente = new FrameEditarCliente();
        }
    }
}
    