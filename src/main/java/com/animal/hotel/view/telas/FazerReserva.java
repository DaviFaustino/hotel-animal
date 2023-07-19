package com.animal.hotel.view.telas;

import com.animal.hotel.model.Cachorro;
import com.animal.hotel.model.Gato;
import com.animal.hotel.model.Hospede;
import com.animal.hotel.uteis.Arquivos;
import com.animal.hotel.uteis.InputDados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FazerReserva extends JFrame {
    static ArrayList<JLabel> labelsFazerReserva;
    static boolean hospedeIsCachorro;
    static JRadioButton filhote, adulto, idoso, simPassear, naoPassear, simSocia, naoSocia;
    static JComboBox menuRacoes, menuQuantCompG;
    static ArrayList<JTextField> entradasTextoReservaHospede;
    static JButton voltarReservaHospede, salvarReservaHospede;
    static ButtonGroup grupoSocia, grupoPassear, grupoFase;
    static HashMap<String, Float> opcoesRacoes;

    public FazerReserva(boolean hospedeIsCachorro) {
        super();
        FazerReserva.hospedeIsCachorro = hospedeIsCachorro;

        setTitle("Fazer reserva de hospede");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Home.telaWidth, Home.telaHeight);
        setLocation(Home.posicaoTela);
        setLayout(null);

        addComponentListener(new ComponentListener() {
            @Override
            public void componentHidden(ComponentEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void componentMoved(ComponentEvent arg0) {
                Home.posicaoTela = getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                int centroX = getWidth() / 2;
                int centroY = getHeight() / 2;
                labelsFazerReserva.get(0).setBounds(centroX - 120, centroY - 200, 200, 30);
                labelsFazerReserva.get(1).setBounds(centroX - 82, centroY - 165, 200, 30);
                labelsFazerReserva.get(2).setBounds(centroX - 124, centroY - 130, 200, 30);
                labelsFazerReserva.get(3).setBounds(centroX - 164, centroY - 95, 250, 30);
                labelsFazerReserva.get(4).setBounds(centroX - 113, centroY - 60, 200, 30);
                labelsFazerReserva.get(5).setBounds(centroX - 122, centroY - 25, 200, 30);
                labelsFazerReserva.get(6).setBounds(centroX - 250, centroY + 10, 300, 30);
                if (FazerReserva.hospedeIsCachorro) {
                    labelsFazerReserva.get(7).setBounds(centroX - 137, centroY + 45, 200, 30);
                    simPassear.setBounds(centroX + 85, centroY + 45, 70, 25);
                    naoPassear.setBounds(centroX + 15, centroY + 45, 70, 25);
                } else {
                    labelsFazerReserva.get(7).setBounds(centroX - 260, centroY + 45, 300, 30);
                    menuQuantCompG.setBounds(centroX + 15, centroY + 45, 50, 25);
                }
                entradasTextoReservaHospede.get(0).setBounds(centroX + 15, centroY - 200, 300, 25);
                filhote.setBounds(centroX + 15, centroY - 165, 80, 25);
                adulto.setBounds(centroX + 95, centroY - 165, 80, 25);
                idoso.setBounds(centroX + 175, centroY - 165, 80, 25);
                menuRacoes.setBounds(centroX + 15, centroY - 130, 150, 25);
                entradasTextoReservaHospede.get(1).setBounds(centroX + 15, centroY - 95, 100, 25);
                entradasTextoReservaHospede.get(2).setBounds(centroX + 15, centroY - 60, 25, 25);
                entradasTextoReservaHospede.get(3).setBounds(centroX + 40, centroY - 60, 25, 25);
                entradasTextoReservaHospede.get(4).setBounds(centroX + 65, centroY - 60, 50, 25);
                entradasTextoReservaHospede.get(5).setBounds(centroX + 15, centroY - 25, 25, 25);
                entradasTextoReservaHospede.get(6).setBounds(centroX + 40, centroY - 25, 25, 25);
                entradasTextoReservaHospede.get(7).setBounds(centroX + 65, centroY - 25, 50, 25);
                simSocia.setBounds(centroX + 85, centroY + 10, 70, 25);
                naoSocia.setBounds(centroX + 15, centroY + 10, 70, 25);
                voltarReservaHospede.setBounds(centroX - 200, centroY + 100, 100, 30);
                salvarReservaHospede.setBounds(centroX + 50, centroY + 100, 100, 30);
            }

            @Override
            public void componentShown(ComponentEvent arg0) {
                // TODO Auto-generated method stub
                
            }
        });



        labelsFazerReserva = new ArrayList<>();
        labelsFazerReserva.add(new JLabel("Nome do hospede"));
        labelsFazerReserva.add(new JLabel("Fase da vida"));
        labelsFazerReserva.add(new JLabel("Ração do Hospede"));
        labelsFazerReserva.add(new JLabel("Quantidade da ração (g)"));
        labelsFazerReserva.add(new JLabel("Data de check-in"));
        labelsFazerReserva.add(new JLabel("Data de check-out"));
        labelsFazerReserva.add(new JLabel("Pode socializar com outros animais?"));
        if (hospedeIsCachorro) {
            labelsFazerReserva.add(new JLabel("Pode pode passear?"));
        } else {
            labelsFazerReserva.add(new JLabel("Quantidade de companheiros de gatil"));
        }
        
        entradasTextoReservaHospede = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            entradasTextoReservaHospede.add(new JTextField());
        }

        filhote = new JRadioButton("Filhote");
        adulto = new JRadioButton("Adulto");
        idoso = new JRadioButton("Idoso");
        adulto.setSelected(true);
        
        grupoFase = new ButtonGroup();
        grupoFase.add(filhote);
        grupoFase.add(adulto);
        grupoFase.add(idoso);

        opcoesRacoes = new HashMap<String, Float>();
        try {
            File arquivo = new File("src/arquivos/racoes.txt");
            Scanner lerArquivo = new Scanner(arquivo);

            String[] chaveValor;

            while (lerArquivo.hasNextLine()) {
                chaveValor = lerArquivo.nextLine().replace("\n", "").split(";");
                opcoesRacoes.put(chaveValor[0], Float.valueOf(chaveValor[1]));
            }

            lerArquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Object[] racoes = opcoesRacoes.keySet().toArray();
        menuRacoes = new JComboBox<>(racoes);

        simSocia = new JRadioButton("Sim");
        naoSocia = new JRadioButton("Não");
        naoSocia.setSelected(true);
        
        grupoSocia = new ButtonGroup();
        grupoSocia.add(simSocia);
        grupoSocia.add(naoSocia);

        simPassear = new JRadioButton("Sim");
        naoPassear = new JRadioButton("Não");
        naoPassear.setSelected(true);
        
        grupoPassear = new ButtonGroup();
        grupoPassear.add(simPassear);
        grupoPassear.add(naoPassear);

        String[] quant = {"0", "1", "2"};
        menuQuantCompG = new JComboBox<>(quant);

        voltarReservaHospede = new JButton("Volta");
        voltarReservaHospede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Home.telaEditarCliente.setLocation(Home.posicaoTela);
                Home.telaEditarCliente.setSize(Home.telaWidth, Home.telaHeight);
                Home.telaEditarCliente.setVisible(true);
                dispose();
            }
        });

        salvarReservaHospede = new JButton("Salvar");
        salvarReservaHospede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                Hospede hospede;
                if (FazerReserva.hospedeIsCachorro) {
                    hospede = new Cachorro();
                } else {
                    hospede = new Gato();
                }

                hospede.setNumeroIdentificacaoResponsavel(Clientes.clientes.get(Clientes.indiceCliente).getNumeroIdentificacao());
                hospede.setNome(entradasTextoReservaHospede.get(0).getText());
                if (filhote.isSelected()) {
                    hospede.setFaseDaVida("Filhote");
                } else {
                    if (adulto.isSelected()) {
                        hospede.setFaseDaVida("Adulto");
                    } else {
                        hospede.setFaseDaVida("Idoso");
                    }
                }
                hospede.setPrecoRacaoKilo(opcoesRacoes.get((String) menuRacoes.getSelectedItem()));
                hospede.setNomeRacao((String) menuRacoes.getSelectedItem());
                hospede.setQuantRacaoGramas(Integer.parseInt(entradasTextoReservaHospede.get(1).getText()));
                for (int i = 0; i < 3; i++) {
                    hospede.getDataCheckIn()[i] = entradasTextoReservaHospede.get(i + 2).getText();
                    hospede.getDataCheckOut()[i] = entradasTextoReservaHospede.get(i + 5).getText();
                }
                if (simSocia.isSelected()) {
                    hospede.setPodeSocializarComOutros(true);
                } else {
                    hospede.setPodeSocializarComOutros(false);
                }

                if (FazerReserva.hospedeIsCachorro) {
                    Cachorro cachorro = (Cachorro) hospede;

                    if (simPassear.isSelected()) {
                        cachorro.setPodePassear(true);
                    } else {
                        cachorro.setPodePassear(false);
                    }
                    
                    String[] opcs = {"Sim", "Não"};
                    int escolha = JOptionPane.showOptionDialog(null, "O custo é de "+ cachorro.calculaCusto() +". Você realmente deseja fazer a reserva?", null, 
                                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                            null, opcs, null);

                    if (escolha == 0) {
                        cachorro.setNumeroIdentificacao(InputDados.numeroIdentificacao(true));

                        try {
                            Arquivos.addAosArquivos(cachorro);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        voltarReservaHospede.doClick();
                    }
                } else {
                    Gato gato = (Gato) hospede;

                    gato.setQuantCompanheirosGatil(Integer.parseInt((String) menuQuantCompG.getSelectedItem()));

                    String[] opcs = {"Sim", "Não"};
                    int escolha = JOptionPane.showOptionDialog(null, "O custo é de "+ gato.calculaCusto() +". Você realmente deseja fazer a reserva?", null, 
                                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                            null, opcs, null);

                    if (escolha == 0) {
                        gato.setNumeroIdentificacao(InputDados.numeroIdentificacao(true));

                        try {
                            Arquivos.addAosArquivos(gato);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        voltarReservaHospede.doClick();
                    }
                }
            }
        });


        for (JLabel label: labelsFazerReserva) {
            add(label);
        }
        for (JTextField caixa: entradasTextoReservaHospede) {
            add(caixa);
        }
        add(simPassear);
        add(naoPassear);
        add(simSocia);
        add(naoSocia);
        add(menuRacoes);
        add(menuQuantCompG);
        add(filhote);
        add(adulto);
        add(idoso);
        add(voltarReservaHospede);
        add(salvarReservaHospede);
        
        setVisible(true);
        Home.telaEditarCliente.setVisible(false);
    }
}
