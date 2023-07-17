package com.animal.hotel.view;

import com.animal.hotel.model.Cachorro;
import com.animal.hotel.model.Gato;
import com.animal.hotel.model.Hospede;
import com.animal.hotel.uteis.Arquivos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class FrameReservas extends JFrame {
    static int larguraPainelHospedes = 400;
    static boolean hospedeIsCachorro;
    static List<Hospede> hospedes;
    static ArrayList<JButtonHospede> opcoesHospedes;
    static ArrayList<JPanel> paineisHospedes;
    static int tamanhoLaterais;
    static int tamanhoCimaBaixo;
    static int indiceHospede;
    static float[] custoCachorrosGatos;
    
    FrameReservas(String idResponsavel, float[] custoCachorrosGatos) {
        super();
        FrameReservas.custoCachorrosGatos = custoCachorrosGatos;

        String jsonString = "[]";

        try {
            Path path;
            if (hospedeIsCachorro) {
                path = Paths.get("src/arquivos/cachorros.json");
            } else {
                path = Paths.get("src/arquivos/gatos.json");
            }
            jsonString = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        if (hospedeIsCachorro) {
            hospedes = gson.fromJson(jsonString, new TypeToken<List<Cachorro>>() {}.getType());
        } else {
            hospedes = gson.fromJson(jsonString, new TypeToken<List<Gato>>() {}.getType());
        }

        if (hospedeIsCachorro) {
            setTitle("Cachorro");
        } else {
            setTitle("Gato");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Home.telaWidth, Home.telaHeight);
        setLocation(Home.posicaoTela);
        setLayout(new BorderLayout(0, 20));
        setVisible(true);
        if (Home.alterar) {
            Home.telaEditarCliente.setVisible(false);
        } else {
            Home.telaPrincipal.setVisible(false);
        }
        
        paineisHospedes = new ArrayList<>();
        paineisHospedes.add(new JPanel());
        paineisHospedes.get(0).setLayout(new BoxLayout(paineisHospedes.get(0), BoxLayout.Y_AXIS));
        paineisHospedes.add(new JPanel());
        paineisHospedes.add(new JPanel());
        paineisHospedes.add(new JPanel());
        paineisHospedes.add(new JPanel());
        
        tamanhoLaterais = (getWidth() - larguraPainelHospedes) / 2;
        tamanhoCimaBaixo = 60;

        paineisHospedes.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisHospedes.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisHospedes.get(3).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
        paineisHospedes.get(4).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));

        addComponentListener(new ComponentListener() {
            @Override
            public void componentHidden(ComponentEvent arg0) {}

            @Override
            public void componentMoved(ComponentEvent arg0) {
                Home.posicaoTela = getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                tamanhoLaterais = (getWidth() - larguraPainelHospedes) / 2;
                paineisHospedes.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                paineisHospedes.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                Home.telaWidth = getWidth();
                Home.telaHeight = getHeight();
            }

            @Override
            public void componentShown(ComponentEvent arg0) {}
        });

        BotaoEventosHospedes eventoHospede = new BotaoEventosHospedes();

        opcoesHospedes = new ArrayList<>();
        int contButHospe = -1;

        for (int i = 0; i < hospedes.size(); i++) {
            if ((Home.alterar && hospedes.get(i).getNumeroIdentificacaoResponsavel().equals(idResponsavel)) || !Home.alterar) {
                contButHospe++;
                opcoesHospedes.add(new JButtonHospede());
                opcoesHospedes.get(contButHospe).addActionListener(eventoHospede);
                opcoesHospedes.get(contButHospe).setIndiceHospede(i);
                opcoesHospedes.get(contButHospe).setIndiceButton(contButHospe);

                if (hospedeIsCachorro) {
                    opcoesHospedes.get(contButHospe).getLabelIdResponsavel().setText("Id do responsável: " + hospedes.get(i).getNumeroIdentificacaoResponsavel());
                    opcoesHospedes.get(contButHospe).getLabelIdHospede().setText("Id do cachorro: " + hospedes.get(i).getNumeroIdentificacao());
                    opcoesHospedes.get(contButHospe).getLabelNome().setText("Nome do cachorro: " + hospedes.get(i).getNome());
                    opcoesHospedes.get(contButHospe).getLabelCusto().setText("Custo da hospedagem: " + hospedes.get(i).getCustoHospede());
                } else {
                    opcoesHospedes.get(contButHospe).getLabelIdResponsavel().setText("Id do responsável: " + hospedes.get(i).getNumeroIdentificacaoResponsavel());
                    opcoesHospedes.get(contButHospe).getLabelIdHospede().setText("Id do gato: " + hospedes.get(i).getNumeroIdentificacao());
                    opcoesHospedes.get(contButHospe).getLabelNome().setText("Nome do gato: " + hospedes.get(i).getNome());
                    opcoesHospedes.get(contButHospe).getLabelCusto().setText("Custo da hospedagem: " + hospedes.get(i).getCustoHospede());
                }
            }
        }

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (Home.alterar) {
                    try {
                        float somaCustos = 0f;
                        for (int i = 0; i < hospedes.size(); i++) {
                            if (hospedes.get(i).getNumeroIdentificacaoResponsavel().equals(FrameClientes.clientes.get(FrameClientes.indiceCliente).getNumeroIdentificacao())) {
                                somaCustos += hospedes.get(i).getCustoHospede();
                            }
                        }

                        if (hospedeIsCachorro) {
                            FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos()[0] = somaCustos;
                            Arquivos.salvar(hospedes, 0);
                        } else {
                            FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos()[1] = somaCustos;
                            Arquivos.salvar(hospedes, 1);
                        }
                        
                        ((JLabel) FrameEditarCliente.paineisEditarCliente.get(0).getComponent(8)).setText("Débito: " + (FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos()[0] + FrameClientes.clientes.get(FrameClientes.indiceCliente).getCustoCachorrosGatos()[1]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Home.telaEditarCliente.setLocation(Home.posicaoTela);
                    Home.telaEditarCliente.setSize(Home.telaWidth, Home.telaHeight);
                    Home.telaEditarCliente.setVisible(true);
                    dispose();
                } else {
                    Home.telaPrincipal.setLocation(Home.posicaoTela);
                    Home.telaPrincipal.setSize(Home.telaWidth, Home.telaHeight);
                    Home.telaPrincipal.setVisible(true);
                    dispose();
                }
            }
        });

        add(new JScrollPane(paineisHospedes.get(0)), BorderLayout.CENTER); 
        add(paineisHospedes.get(1), BorderLayout.WEST); 
        add(paineisHospedes.get(2), BorderLayout.EAST); 
        add(paineisHospedes.get(3), BorderLayout.NORTH); 
        add(paineisHospedes.get(4), BorderLayout.SOUTH);
        
        for (JButton opcaoHospede: opcoesHospedes) {
            paineisHospedes.get(0).add(opcaoHospede);
        }
        
        paineisHospedes.get(4).add(voltar);
    }
    
    private static class BotaoEventosHospedes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            indiceHospede = ((JButtonHospede) arg0.getSource()).getIndiceHospede();

            if (Home.alterar) {
                Home.telaEditarReserva = new FrameEditarReserva(((JButtonHospede) arg0.getSource()).getIndiceButton());
            } else {
                new DialogVerReserva();
            }
        }
    }
}
