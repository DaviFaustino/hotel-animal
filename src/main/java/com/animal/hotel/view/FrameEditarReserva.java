package com.animal.hotel.view;

import com.animal.hotel.model.Cachorro;
import com.animal.hotel.model.Gato;
import com.animal.hotel.model.Hospede;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class FrameEditarReserva extends JFrame {
    static ArrayList<JPanel> paineisEditarReserva;
    static int larguraPainelEditHospede = 400;
    static int alturaPainelEditHospede = 370;
    public int tamanhoLaterais;
    public int tamanhoCimaBaixo;
    static JButton editNomeHospede, editFaseDaVida, editQuantRacaoGramas, editNomeRacao, editPodeSocializarComOutros, 
                    edDataCheckIn, edDataCheckOut, editPodePassear, editQuantCompanheirosGa;
    static JLabel LabelGastoRacao, LabelCustoHospede;
    static JDialog dialogEscolhaNomeRacao, dialogPodeSocializar, dialogDataCheckIn, dialogDataCheckOut, dialogPodePassear;
    static ArrayList<JTextField> entradasEditDataCheckIn, entradasEditDataCheckOut;
    static JComboBox menuRacoes;
    static HashMap<String, Float> opcoesRacoes;
    static ButtonGroup grupoSocia, grupoPassear;
    static JRadioButton simPassear, naoPassear, simSocia, naoSocia;
    static int indiceButton;

    FrameEditarReserva(int indiceButton) {
        super();
        FrameEditarReserva.indiceButton = indiceButton;

        if (FrameReservas.hospedeIsCachorro) {
            setTitle("Detalhes Cachorro");
        } else {
            setTitle("Detalhes Gato");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Sistema.telaWidth, Sistema.telaHeight);
        setLocation(Sistema.posicaoTela);
        setLayout(new BorderLayout());
        setVisible(true);
        Sistema.telaReservas.setVisible(false);
        
        paineisEditarReserva = new ArrayList<>();
        paineisEditarReserva.add(new JPanel());
        paineisEditarReserva.get(0).setLayout(new BoxLayout(paineisEditarReserva.get(0), BoxLayout.Y_AXIS));
        paineisEditarReserva.add(new JPanel());
        paineisEditarReserva.add(new JPanel());
        paineisEditarReserva.add(new JPanel());
        paineisEditarReserva.add(new JPanel());
        
        tamanhoLaterais = (getWidth() - larguraPainelEditHospede) / 2;
        tamanhoCimaBaixo = (getHeight() - alturaPainelEditHospede) / 2;

        paineisEditarReserva.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisEditarReserva.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
        paineisEditarReserva.get(3).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
        paineisEditarReserva.get(4).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));

        addComponentListener(new ComponentListener() {
            @Override
            public void componentHidden(ComponentEvent arg0) {}

            @Override
            public void componentMoved(ComponentEvent arg0) {
                Sistema.posicaoTela = getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                tamanhoLaterais = (getWidth() - larguraPainelEditHospede) / 2;
                tamanhoCimaBaixo = (getHeight() - alturaPainelEditHospede) / 2;
                paineisEditarReserva.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                paineisEditarReserva.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                paineisEditarReserva.get(3).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
                paineisEditarReserva.get(4).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
                Sistema.telaWidth = getWidth();
                Sistema.telaHeight = getHeight();
            }

            @Override
            public void componentShown(ComponentEvent arg0) {}
        });


        paineisEditarReserva.get(0).add(new JLabel("Escolha um dado para editar"));
        paineisEditarReserva.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarReserva.get(0).add(new JLabel("Número de ID responsável: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNumeroIdentificacaoResponsavel()));
        paineisEditarReserva.get(0).add(new JLabel("Número de identificação: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNumeroIdentificacao()));
        paineisEditarReserva.get(0).add(editNomeHospede = new JButton("Nome do hospede: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNome()));
        paineisEditarReserva.get(0).add(editFaseDaVida = new JButton("Fase da vida: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getFaseDaVida()));
        paineisEditarReserva.get(0).add(editNomeRacao = new JButton("Ração do hospede: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNomeRacao()));
        paineisEditarReserva.get(0).add(editQuantRacaoGramas = new JButton("Quantidade de ração (g): " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getQuantRacaoGramas()));
        paineisEditarReserva.get(0).add(LabelGastoRacao = new JLabel("Gasto com ração: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getGastoComRacao()));
        paineisEditarReserva.get(0).add(editPodeSocializarComOutros = new JButton("Pode socializar com outros animais: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).isPodeSocializarComOutros()));
        paineisEditarReserva.get(0).add(edDataCheckIn = new JButton("Data de check-in: " + Arrays.toString(FrameReservas.hospedes.get(FrameReservas.indiceHospede).getDataCheckIn())));
        paineisEditarReserva.get(0).add(edDataCheckOut = new JButton("Data de check-out: " + Arrays.toString(FrameReservas.hospedes.get(FrameReservas.indiceHospede).getDataCheckOut())));  
        if (FrameReservas.hospedes.get(0).getClass() == Cachorro.class) {
            Cachorro cachorro = (Cachorro) FrameReservas.hospedes.get(FrameReservas.indiceHospede);

            paineisEditarReserva.get(0).add(editPodePassear = new JButton("Pode passear: " + cachorro.getPodePassear()));
        } else {
            Gato gato = (Gato) FrameReservas.hospedes.get(FrameReservas.indiceHospede);

            paineisEditarReserva.get(0).add(editQuantCompanheirosGa = new JButton("Quantidade de companheiros: " + gato.getQuantCompanheirosGatil()));
        }
        paineisEditarReserva.get(0).add(new JSeparator(JSeparator.HORIZONTAL));
        paineisEditarReserva.get(0).add(LabelCustoHospede = new JLabel("Custo do hospede:" + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getCustoHospede()));
        paineisEditarReserva.get(0).getComponent(0).setFont(Sistema.fontePadrao);
        paineisEditarReserva.get(0).getComponent(2).setFont(Sistema.fontePadrao);
        paineisEditarReserva.get(0).getComponent(3).setFont(Sistema.fontePadrao);
        paineisEditarReserva.get(0).getComponent(8).setFont(Sistema.fontePadrao);
        paineisEditarReserva.get(0).getComponent(14).setFont(Sistema.fontePadrao);

        BotaoEventosEditarHospede eventoEditarHospede = new BotaoEventosEditarHospede();
        editNomeHospede.addActionListener(eventoEditarHospede);
        editFaseDaVida.addActionListener(eventoEditarHospede);
        editNomeRacao.addActionListener(eventoEditarHospede);
        editQuantRacaoGramas.addActionListener(eventoEditarHospede);
        editPodeSocializarComOutros.addActionListener(eventoEditarHospede);
        edDataCheckIn.addActionListener(eventoEditarHospede);
        edDataCheckOut.addActionListener(eventoEditarHospede);
        if (FrameReservas.hospedes.get(0).getClass() == Cachorro.class) {
            editPodePassear.addActionListener(eventoEditarHospede);
        } else {
            editQuantCompanheirosGa.addActionListener(eventoEditarHospede);
        }
        

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Sistema.telaReservas.setLocation(Sistema.posicaoTela);
                Sistema.telaReservas.setSize(Sistema.telaWidth, Sistema.telaHeight);
                Sistema.telaReservas.setVisible(true);

                if (FrameReservas.hospedeIsCachorro) {
                    FrameReservas.opcoesHospedes.get(FrameEditarReserva.indiceButton).getLabelNome().setText("Nome do cachorro: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNome());
                } else {
                    FrameReservas.opcoesHospedes.get(FrameEditarReserva.indiceButton).getLabelNome().setText("Nome do gato: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getNome());
                }
                FrameReservas.opcoesHospedes.get(FrameEditarReserva.indiceButton).getLabelCusto().setText("Custo da hospedagem: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getCustoHospede());

                dispose();
            }
        });

        paineisEditarReserva.get(4).add(voltar);

        add(paineisEditarReserva.get(0), BorderLayout.CENTER); 
        add(paineisEditarReserva.get(1), BorderLayout.WEST); 
        add(paineisEditarReserva.get(2), BorderLayout.EAST); 
        add(paineisEditarReserva.get(3), BorderLayout.NORTH); 
        add(paineisEditarReserva.get(4), BorderLayout.SOUTH);
    }

    static private class BotaoEventosEditarHospede implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            Object source = arg0.getSource();
            String entrada;

            if (source == editNomeHospede) {
                entrada = JOptionPane.showInputDialog("Insira o nome do Hospede:");
                
                if (entrada != null && !entrada.equals("")) {
                    editNomeHospede.setText("Nome do Hospede: " + entrada);
                    FrameReservas.hospedes.get(FrameReservas.indiceHospede).setNome(entrada);
                }
            } else { 
                if (source == editFaseDaVida) {
                    String[] opcs = {"Filhote", " Adulto ", " Idoso "};
                    int escolha;
                           
                    escolha = JOptionPane.showOptionDialog(null, "Selecione a fase de vida do hospede:", null, 
                                                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                                null, opcs, null);

                    if (escolha == 0) {
                        editFaseDaVida.setText("Fase da vida: Filhote");
                        FrameReservas.hospedes.get(FrameReservas.indiceHospede).setFaseDaVida("Filhote");
                    } else {
                        if (escolha == 1) {
                            editFaseDaVida.setText("Fase da vida: Adulto");
                            FrameReservas.hospedes.get(FrameReservas.indiceHospede).setFaseDaVida("Adulto");
                        } else {
                            if (escolha == 2) {
                                editFaseDaVida.setText("Fase da vida: Idoso");
                                FrameReservas.hospedes.get(FrameReservas.indiceHospede).setFaseDaVida("Idoso");
                            }
                        }
                    }

                    atualizaCusto();
                } else {
                    if (source == editNomeRacao) {
                        dialogEscolhaNomeRacao = new JDialog(Sistema.telaEditarReserva);
                        dialogEscolhaNomeRacao.setSize(300, 100);
                        dialogEscolhaNomeRacao.setLocation((int) (Sistema.posicaoTela.getX() + Sistema.telaWidth / 2 - 150), (int) (Sistema.posicaoTela.getY() + Sistema.telaHeight / 2 - 50));

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

                        JPanel painelDialogRacao = new JPanel();
                        painelDialogRacao.add(new JLabel("Escolha a ração do hospede:"));
                        painelDialogRacao.add(menuRacoes);
                        JButton okBut = new JButton("   Ok   ");
                        okBut.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent arg0) {
                                editNomeRacao.setText("Ração do hospede: " + (String) menuRacoes.getSelectedItem());
                                FrameReservas.hospedes.get(FrameReservas.indiceHospede).setNomeRacao((String) menuRacoes.getSelectedItem());

                                FrameReservas.hospedes.get(FrameReservas.indiceHospede).calculaGastoRacao();
                                LabelGastoRacao.setText("Gasto com ração: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getGastoComRacao());
                                atualizaCusto();

                                dialogEscolhaNomeRacao.dispose();
                            }
                        });
                        painelDialogRacao.add(okBut);

                        dialogEscolhaNomeRacao.add(painelDialogRacao);
                        dialogEscolhaNomeRacao.setVisible(true);
                    } else {
                        if (source == editQuantRacaoGramas) {
                            int entradaInt = -1;
                            do { 
                                entrada = JOptionPane.showInputDialog("Insira a quantidade de ração do hospede:");

                                try {
                                    entradaInt = Integer.parseInt(entrada);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } while (entradaInt < 0 && entrada != null);

                            if (entrada != null && !entrada.equals("")) {
                                editQuantRacaoGramas.setText("Quantidade de ração (g): " + entrada);
                                FrameReservas.hospedes.get(FrameReservas.indiceHospede).setQuantRacaoGramas(entradaInt);

                                FrameReservas.hospedes.get(FrameReservas.indiceHospede).calculaGastoRacao();
                                LabelGastoRacao.setText("Gasto com ração: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getGastoComRacao());

                                atualizaCusto();
                            }
                        } else {
                            if (source == editPodeSocializarComOutros) {
                                dialogPodeSocializar = new JDialog(Sistema.telaEditarReserva);
                                dialogPodeSocializar.setSize(250, 100);
                                dialogPodeSocializar.setLocation((int) Sistema.posicaoTela.getX() + Sistema.telaWidth / 2 - 125, (int) Sistema.posicaoTela.getY() + Sistema.telaHeight / 2 - 50);

                                simSocia = new JRadioButton("Sim");
                                naoSocia = new JRadioButton("Não");
                                naoSocia.setSelected(FrameReservas.hospedes.get(FrameReservas.indiceHospede).isPodeSocializarComOutros());

                                grupoSocia = new ButtonGroup();
                                grupoSocia.add(simSocia);
                                grupoSocia.add(naoSocia);

                                JPanel painelDialogSocializar = new JPanel();
                                painelDialogSocializar.add(new JLabel("O hospede pode socializar?"));
                                painelDialogSocializar.add(simSocia);
                                painelDialogSocializar.add(naoSocia);
                                JButton okBut = new JButton("   Ok   ");
                                okBut.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent arg0) {
                                        if (simSocia.isSelected()) {
                                            editPodeSocializarComOutros.setText("Pode socializar com outros animais: " + true);
                                            FrameReservas.hospedes.get(FrameReservas.indiceHospede).setPodeSocializarComOutros(true);
                                        } else {
                                            editPodeSocializarComOutros.setText("Pode socializar com outros animais: " + false);
                                            FrameReservas.hospedes.get(FrameReservas.indiceHospede).setPodeSocializarComOutros(false);
                                        }
                                        atualizaCusto();

                                        dialogPodeSocializar.dispose();
                                    }
                                });
                                painelDialogSocializar.add(okBut);

                                dialogPodeSocializar.add(painelDialogSocializar);
                                dialogPodeSocializar.setVisible(true);
                            } else {
                                if (source == edDataCheckIn) {
                                    dialogDataCheckIn = new JDialog(Sistema.telaEditarReserva);
                                    dialogDataCheckIn.setSize(250, 100);
                                    dialogDataCheckIn.setLocation(((int) Sistema.posicaoTela.getX() + Sistema.telaWidth / 2 - 150), ((int) Sistema.posicaoTela.getY() + Sistema.telaHeight / 2 - 50));

                                    entradasEditDataCheckIn = new ArrayList<JTextField>();
                                    for (int i = 0; i < 3; i++) {
                                        entradasEditDataCheckIn.add(new JTextField());
                                    }
                                    entradasEditDataCheckIn.get(0).setColumns(2);
                                    entradasEditDataCheckIn.get(1).setColumns(2);
                                    entradasEditDataCheckIn.get(2).setColumns(4);

                                    JButton okBut = new JButton("   Ok   ");
                                    okBut.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent arg0) {
                                            String[] data = {entradasEditDataCheckIn.get(0).getText(), entradasEditDataCheckIn.get(1).getText(), entradasEditDataCheckIn.get(2).getText()};

                                            edDataCheckIn.setText("Data de check-in: " + Arrays.toString(data));
                                            FrameReservas.hospedes.get(FrameReservas.indiceHospede).setDataCheckIn(data);
                                            atualizaCusto();

                                            dialogDataCheckIn.dispose();
                                        }
                                    });

                                    JPanel painelDialogDataCheckIn = new JPanel();
                                    painelDialogDataCheckIn.add(new JLabel("   Informe a data de check-in:   "));
                                    painelDialogDataCheckIn.add(entradasEditDataCheckIn.get(0));
                                    painelDialogDataCheckIn.add(entradasEditDataCheckIn.get(1));
                                    painelDialogDataCheckIn.add(entradasEditDataCheckIn.get(2));
                                    painelDialogDataCheckIn.add(okBut);

                                    dialogDataCheckIn.add(painelDialogDataCheckIn);
                                    dialogDataCheckIn.setVisible(true);
                                } else {
                                    if (source == edDataCheckOut) {
                                        dialogDataCheckOut = new JDialog(Sistema.telaEditarReserva);
                                        dialogDataCheckOut.setSize(250, 100);
                                        dialogDataCheckOut.setLocation(((int) Sistema.posicaoTela.getX() + Sistema.telaWidth / 2 - 150), ((int) Sistema.posicaoTela.getY() + Sistema.telaHeight / 2 - 50));
    
                                        entradasEditDataCheckOut = new ArrayList<JTextField>();
                                        for (int i = 0; i < 3; i++) {
                                            entradasEditDataCheckOut.add(new JTextField());
                                        }
                                        entradasEditDataCheckOut.get(0).setColumns(2);
                                        entradasEditDataCheckOut.get(1).setColumns(2);
                                        entradasEditDataCheckOut.get(2).setColumns(4);
    
                                        JButton okBut = new JButton("   Ok   ");
                                        okBut.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent arg0) {
                                                String[] data = {entradasEditDataCheckOut.get(0).getText(), entradasEditDataCheckOut.get(1).getText(), entradasEditDataCheckOut.get(2).getText()};
    
                                                edDataCheckOut.setText("Data de check-out: " + Arrays.toString(data));
                                                FrameReservas.hospedes.get(FrameReservas.indiceHospede).setDataCheckOut(data);
                                                atualizaCusto();
    
                                                dialogDataCheckOut.dispose();
                                            }
                                        });
    
                                        JPanel painelDialogDataCheckOut = new JPanel();
                                        painelDialogDataCheckOut.add(new JLabel("   Informe a data de check-out:   "));
                                        painelDialogDataCheckOut.add(entradasEditDataCheckOut.get(0));
                                        painelDialogDataCheckOut.add(entradasEditDataCheckOut.get(1));
                                        painelDialogDataCheckOut.add(entradasEditDataCheckOut.get(2));
                                        painelDialogDataCheckOut.add(okBut);
    
                                        dialogDataCheckOut.add(painelDialogDataCheckOut);
                                        dialogDataCheckOut.setVisible(true);
                                    } else {
                                        if (FrameReservas.hospedes.get(0).getClass() == Cachorro.class) {
                                            dialogPodePassear = new JDialog(Sistema.telaEditarReserva);
                                            dialogPodePassear.setSize(250, 100);
                                            dialogPodePassear.setLocation((int) Sistema.posicaoTela.getX() + Sistema.telaWidth / 2 - 125, (int) Sistema.posicaoTela.getY() + Sistema.telaHeight / 2 - 50);

                                            simPassear = new JRadioButton("Sim");
                                            naoPassear = new JRadioButton("Não");
                                            naoPassear.setSelected(true);
                                            
                                            grupoPassear = new ButtonGroup();
                                            grupoPassear.add(simPassear);
                                            grupoPassear.add(naoPassear);

                                            JPanel painelDialogPodePassear = new JPanel();
                                            painelDialogPodePassear.add(new JLabel("    O hospede pode passear?    "));
                                            painelDialogPodePassear.add(simPassear);
                                            painelDialogPodePassear.add(naoPassear);

                                            JButton okBut = new JButton("   Ok   ");
                                            okBut.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent arg0) {
                                                    Cachorro cachorro = (Cachorro) FrameReservas.hospedes.get(FrameReservas.indiceHospede);

                                                    if (simPassear.isSelected()) {
                                                        cachorro.setPodePassear(true);
                                                        editPodePassear.setText("Pode passear: " + true);
                                                    } else {
                                                        cachorro.setPodePassear(false);
                                                        editPodePassear.setText("Pode passear: " + false);
                                                    }
                                                    FrameReservas.hospedes.set(FrameReservas.indiceHospede, (Hospede) cachorro);
                                                    atualizaCusto();
                                                    
                                                    dialogPodePassear.dispose();
                                                }
                                            });
                                            painelDialogPodePassear.add(okBut);

                                            dialogPodePassear.add(painelDialogPodePassear);
                                            dialogPodePassear.setVisible(true);
                                        } else {
                                            String[] opcsC = {" 0 ", " 1 ", " 2 ", " 3 "};
                                            int escolhaC;
                                                    
                                            escolhaC = JOptionPane.showOptionDialog(null, "Selecione a quantidade de companheiros do gato:", null, 
                                                                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                                                    null, opcsC, null);


                                            Gato gato = (Gato) FrameReservas.hospedes.get(FrameReservas.indiceHospede);
                                            if (escolhaC == -1) {
                                                escolhaC = gato.getQuantCompanheirosGatil();
                                            }
                                            gato.setQuantCompanheirosGatil(escolhaC);

                                            FrameReservas.hospedes.set(FrameReservas.indiceHospede, (Hospede) gato);
                                            editQuantCompanheirosGa.setText("Quantidade de companheiros: " + escolhaC);

                                            atualizaCusto();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void atualizaCusto() {
        if (FrameReservas.hospedes.get(0).getClass() == Cachorro.class) {
            Cachorro cachorro = (Cachorro) FrameReservas.hospedes.get(FrameReservas.indiceHospede);
            cachorro.calculaCusto();
            
            FrameReservas.hospedes.get(FrameReservas.indiceHospede).setCustoHospede(cachorro.getCustoHospede());
            LabelCustoHospede.setText("Custo do hospede:" + Float.toString(cachorro.getCustoHospede()));
        } else {
            Gato gato = (Gato) FrameReservas.hospedes.get(FrameReservas.indiceHospede);
            gato.calculaCusto();
            
            FrameReservas.hospedes.get(FrameReservas.indiceHospede).setCustoHospede(gato.getCustoHospede());
            LabelCustoHospede.setText(Float.toString(gato.getCustoHospede()));
        }
    }
}
