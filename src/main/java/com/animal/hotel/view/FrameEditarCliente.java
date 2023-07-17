package com.animal.hotel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class FrameEditarCliente extends JFrame {
    static ArrayList<JPanel> paineisEditarCliente;
    static int larguraPainelEditCliente = 400;
    static int alturaPainelEditCliente = 300;
    public static int telaWidth;
    public static int telaHeight;
    public static Point posicaoTela;
    public int tamanhoLaterais;
    public int tamanhoCimaBaixo;
    static JButton editNomeCliente, editTelefoneCliente, editEmailCliente, fazerReserva, verReservas;

    FrameEditarCliente() {
        posicaoTela = FrameClientes.posicaoTela;
        telaWidth = FrameClientes.telaWidth;
        telaHeight = FrameClientes.telaHeight;

        setTitle("Detalhes Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(telaWidth, telaHeight);
        setLocation(posicaoTela);
        setLayout(new BorderLayout());
        setVisible(true);
        Sistema.telaClientes.setVisible(false);
        
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
        paineisEditarCliente.get(0).add(new JLabel("Número de identificação: " + FrameClientes.clientes.get(FrameClientes.indiceCliente).getNumeroIdentificacao()));
        paineisEditarCliente.get(0).getComponent(2).setFont(Sistema.fontePadrao);
        paineisEditarCliente.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarCliente.get(0).add(editNomeCliente = new JButton("Nome do cliente: " + FrameClientes.clientes.get(FrameClientes.indiceCliente).getNome()));
        paineisEditarCliente.get(0).add(editTelefoneCliente = new JButton("Número de telefone: " + FrameClientes.clientes.get(FrameClientes.indiceCliente).getNumeroTelefone()));
        paineisEditarCliente.get(0).add(editEmailCliente = new JButton("E-mail: " + FrameClientes.clientes.get(FrameClientes.indiceCliente).getEmail()));
        paineisEditarCliente.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarCliente.get(0).add(new JLabel("Débito: " + (FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos()[0] + FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos()[1])));
        paineisEditarCliente.get(0).getComponent(8).setFont(Sistema.fontePadrao);
        paineisEditarCliente.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarCliente.get(0).add(fazerReserva = new JButton("Fazer reservas de hospedes"));
        paineisEditarCliente.get(0).add(verReservas = new JButton("Ver reservas do cliente"));

        BotaoEventosEditarCliente eventoEditarCliente = new BotaoEventosEditarCliente();
        editNomeCliente.addActionListener(eventoEditarCliente);
        editTelefoneCliente.addActionListener(eventoEditarCliente);
        editEmailCliente.addActionListener(eventoEditarCliente);
        fazerReserva.addActionListener(eventoEditarCliente);
        verReservas.addActionListener(eventoEditarCliente);

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Sistema.telaClientes.setLocation(posicaoTela);
                Sistema.telaClientes.setSize(telaWidth, telaHeight);

                JPanel dados = new JPanel();
                dados.setLayout(new BoxLayout(dados, BoxLayout.Y_AXIS));
                dados.add(new JLabel("Id do cliente: " + FrameClientes.clientes.get(FrameClientes.indiceCliente).getNumeroIdentificacao()));
                dados.add(new JLabel("Nome do cliente: " + FrameClientes.clientes.get(FrameClientes.indiceCliente).getNome()));
                dados.add(new JLabel("Debito do cliente: " + String.valueOf(FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos()[0] + FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos()[1])));
                
                FrameClientes.opcoesClientes.get(FrameClientes.indiceCliente).remove(0);
                FrameClientes.opcoesClientes.get(FrameClientes.indiceCliente).add(dados);

                Sistema.telaClientes.setVisible(true);
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

    
    private static class BotaoEventosEditarCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Object source = arg0.getSource();
            String entrada;

            if (source == editNomeCliente) {
                entrada = JOptionPane.showInputDialog("Insira o nome do Cliente:");
                
                if (entrada != null && !entrada.equals("")) {
                    editNomeCliente.setText("Nome do cliente: " + entrada);
                    FrameClientes.clientes.get(FrameClientes.indiceCliente).setNome(entrada);
                }
            } else {
                if (source == editTelefoneCliente) {
                    entrada = JOptionPane.showInputDialog("Insira o número de telefone:");

                    if (entrada != null && !entrada.equals("")) {
                        editTelefoneCliente.setText("Número de telefone: " + entrada);
                        FrameClientes.clientes.get(FrameClientes.indiceCliente).setNumeroTelefone(entrada);
                    }
                } else {
                    if (source == editEmailCliente) {
                        entrada = JOptionPane.showInputDialog("Insira o e-mail do cliente:");

                        if (entrada != null && !entrada.equals("")) {
                            editEmailCliente.setText("E-mail: " + entrada);
                            FrameClientes.clientes.get(FrameClientes.indiceCliente).setEmail(entrada);
                        }
                    } else {
                        String[] opcs = {"Cachorro", "  Gato  ", "Cancelar"};
                        int escolha;
                        boolean hospedeIsCachorro = false;

                        if (source == fazerReserva) {
                            escolha = JOptionPane.showOptionDialog(null, "Você quer fazer reserva de cachorro ou de gato?", null, 
                                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                                        null, opcs, null);
    
                            if (escolha == 0) {
                                hospedeIsCachorro = true;
                            } else {
                                if (escolha == 1) {
                                    hospedeIsCachorro = false;
                                }
                            }

                            if (escolha != 2) {
                                Sistema.telaFazerReserva = new FrameFazerReserva(hospedeIsCachorro);
                            }

                        } else {
                            escolha = JOptionPane.showOptionDialog(null, "Você quer fazer reserva de cachorro ou de gato?", null, 
                                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                                        null, opcs, null);
    
                            if (escolha == 0) {
                                hospedeIsCachorro = true;
                            } else {
                                if (escolha == 1) {
                                    hospedeIsCachorro = false;
                                }
                            }

                            if (escolha != 2) {
                                FrameReservas.hospedeIsCachorro = hospedeIsCachorro;
                                Sistema.telaReservas = new FrameReservas(FrameClientes.clientes.get(FrameClientes.indiceCliente).getNumeroIdentificacao(), FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos());
                            }
                        }
                    }
                }
            }
        }     
    }
}
