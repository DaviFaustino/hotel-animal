package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.FrameEditarReserva;
import com.animal.hotel.view.telas.FrameReservas;
import com.animal.hotel.view.telas.Home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.animal.hotel.model.Cachorro;
import com.animal.hotel.model.Gato;
import com.animal.hotel.model.Hospede;

public class EventosEditarReserva {
    public static class EventoBotaoEditNomeHospede implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String entrada;

            entrada = JOptionPane.showInputDialog("Insira o nome do Hospede:");
            
            if (entrada != null && !entrada.equals("")) {
                FrameEditarReserva.editNomeHospede.setText("Nome do Hospede: " + entrada);
                FrameReservas.hospedes.get(FrameReservas.indiceHospede).setNome(entrada);
            }
        }
    }

    public static class EventoBotaoEditFaseDaVida implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String[] opcs = {"Filhote", " Adulto ", " Idoso "};
            int escolha;
                    
            escolha = JOptionPane.showOptionDialog(null, "Selecione a fase de vida do hospede:", null, 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                                        null, opcs, null);

            if (escolha == 0) {
                FrameEditarReserva.editFaseDaVida.setText("Fase da vida: Filhote");
                FrameReservas.hospedes.get(FrameReservas.indiceHospede).setFaseDaVida("Filhote");
            } else {
                if (escolha == 1) {
                    FrameEditarReserva.editFaseDaVida.setText("Fase da vida: Adulto");
                    FrameReservas.hospedes.get(FrameReservas.indiceHospede).setFaseDaVida("Adulto");
                } else {
                    if (escolha == 2) {
                        FrameEditarReserva.editFaseDaVida.setText("Fase da vida: Idoso");
                        FrameReservas.hospedes.get(FrameReservas.indiceHospede).setFaseDaVida("Idoso");
                    }
                }
            }

            atualizaCusto();
        }
    }

    public static class EventoBotaoEditNomeRacao implements ActionListener {
        private HashMap<String, Float> opcoesRacoes;

        @Override
        public void actionPerformed(ActionEvent arg0) {
            FrameEditarReserva.dialogEscolhaNomeRacao = new JDialog(Home.telaEditarReserva);
            FrameEditarReserva.dialogEscolhaNomeRacao.setSize(300, 100);
            FrameEditarReserva.dialogEscolhaNomeRacao.setLocation((int) (Home.posicaoTela.getX() + Home.telaWidth / 2 - 150), (int) (Home.posicaoTela.getY() + Home.telaHeight / 2 - 50));

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
            FrameEditarReserva.menuRacoes = new JComboBox<>(racoes);

            JPanel painelDialogRacao = new JPanel();
            painelDialogRacao.add(new JLabel("Escolha a ração do hospede:"));
            painelDialogRacao.add(FrameEditarReserva.menuRacoes);
            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    FrameEditarReserva.editNomeRacao.setText("Ração do hospede: " + (String) FrameEditarReserva.menuRacoes.getSelectedItem());
                    FrameReservas.hospedes.get(FrameReservas.indiceHospede).setNomeRacao((String) FrameEditarReserva.menuRacoes.getSelectedItem());

                    FrameReservas.hospedes.get(FrameReservas.indiceHospede).calculaGastoRacao();
                    FrameEditarReserva.LabelGastoRacao.setText("Gasto com ração: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getGastoComRacao());
                    atualizaCusto();

                    FrameEditarReserva.dialogEscolhaNomeRacao.dispose();
                }
            });
            painelDialogRacao.add(okBut);

            FrameEditarReserva.dialogEscolhaNomeRacao.add(painelDialogRacao);
            FrameEditarReserva.dialogEscolhaNomeRacao.setVisible(true);
        }
    }

    public static class EventoBotaoEditQuantRacaoGramas implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            int entradaInt = -1;
            String entrada;
            
            do { 
                entrada = JOptionPane.showInputDialog("Insira a quantidade de ração do hospede:");

                try {
                    entradaInt = Integer.parseInt(entrada);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } while (entradaInt < 0 && entrada != null);

            if (entrada != null && !entrada.equals("")) {
                FrameEditarReserva.editQuantRacaoGramas.setText("Quantidade de ração (g): " + entrada);
                FrameReservas.hospedes.get(FrameReservas.indiceHospede).setQuantRacaoGramas(entradaInt);

                FrameReservas.hospedes.get(FrameReservas.indiceHospede).calculaGastoRacao();
                FrameEditarReserva.LabelGastoRacao.setText("Gasto com ração: " + FrameReservas.hospedes.get(FrameReservas.indiceHospede).getGastoComRacao());

                atualizaCusto();
            }
        }
    }

    public static class EventoBotaoEditPodeSocializarComOutros implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            FrameEditarReserva.dialogPodeSocializar = new JDialog(Home.telaEditarReserva);
            FrameEditarReserva.dialogPodeSocializar.setSize(250, 100);
            FrameEditarReserva.dialogPodeSocializar.setLocation((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 125, (int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 50);

            FrameEditarReserva.simSocia = new JRadioButton("Sim");
            FrameEditarReserva.naoSocia = new JRadioButton("Não");
            FrameEditarReserva.naoSocia.setSelected(FrameReservas.hospedes.get(FrameReservas.indiceHospede).isPodeSocializarComOutros());

            FrameEditarReserva.grupoSocia = new ButtonGroup();
            FrameEditarReserva.grupoSocia.add(FrameEditarReserva.simSocia);
            FrameEditarReserva.grupoSocia.add(FrameEditarReserva.naoSocia);

            JPanel painelDialogSocializar = new JPanel();
            painelDialogSocializar.add(new JLabel("O hospede pode socializar?"));
            painelDialogSocializar.add(FrameEditarReserva.simSocia);
            painelDialogSocializar.add(FrameEditarReserva.naoSocia);
            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if (FrameEditarReserva.simSocia.isSelected()) {
                        FrameEditarReserva.editPodeSocializarComOutros.setText("Pode socializar com outros animais: " + true);
                        FrameReservas.hospedes.get(FrameReservas.indiceHospede).setPodeSocializarComOutros(true);
                    } else {
                        FrameEditarReserva.editPodeSocializarComOutros.setText("Pode socializar com outros animais: " + false);
                        FrameReservas.hospedes.get(FrameReservas.indiceHospede).setPodeSocializarComOutros(false);
                    }
                    atualizaCusto();

                    FrameEditarReserva.dialogPodeSocializar.dispose();
                }
            });
            painelDialogSocializar.add(okBut);

            FrameEditarReserva.dialogPodeSocializar.add(painelDialogSocializar);
            FrameEditarReserva.dialogPodeSocializar.setVisible(true);
        }
    }

    public static class EventoBotaoEdDataCheckIn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            FrameEditarReserva.dialogDataCheckIn = new JDialog(Home.telaEditarReserva);
            FrameEditarReserva.dialogDataCheckIn.setSize(250, 100);
            FrameEditarReserva.dialogDataCheckIn.setLocation(((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 150), ((int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 50));

            FrameEditarReserva.entradasEditDataCheckIn = new ArrayList<JTextField>();
            for (int i = 0; i < 3; i++) {
                FrameEditarReserva.entradasEditDataCheckIn.add(new JTextField());
            }
            FrameEditarReserva.entradasEditDataCheckIn.get(0).setColumns(2);
            FrameEditarReserva.entradasEditDataCheckIn.get(1).setColumns(2);
            FrameEditarReserva.entradasEditDataCheckIn.get(2).setColumns(4);

            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    String[] data = {FrameEditarReserva.entradasEditDataCheckIn.get(0).getText(), FrameEditarReserva.entradasEditDataCheckIn.get(1).getText(), FrameEditarReserva.entradasEditDataCheckIn.get(2).getText()};

                    FrameEditarReserva.edDataCheckIn.setText("Data de check-in: " + Arrays.toString(data));
                    FrameReservas.hospedes.get(FrameReservas.indiceHospede).setDataCheckIn(data);
                    atualizaCusto();

                    FrameEditarReserva.dialogDataCheckIn.dispose();
                }
            });

            JPanel painelDialogDataCheckIn = new JPanel();
            painelDialogDataCheckIn.add(new JLabel("   Informe a data de check-in:   "));
            painelDialogDataCheckIn.add(FrameEditarReserva.entradasEditDataCheckIn.get(0));
            painelDialogDataCheckIn.add(FrameEditarReserva.entradasEditDataCheckIn.get(1));
            painelDialogDataCheckIn.add(FrameEditarReserva.entradasEditDataCheckIn.get(2));
            painelDialogDataCheckIn.add(okBut);

            FrameEditarReserva.dialogDataCheckIn.add(painelDialogDataCheckIn);
            FrameEditarReserva.dialogDataCheckIn.setVisible(true);
        }
    }

    public static class EventoBotaoEdDataCheckOut implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            FrameEditarReserva.dialogDataCheckOut = new JDialog(Home.telaEditarReserva);
            FrameEditarReserva.dialogDataCheckOut.setSize(250, 100);
            FrameEditarReserva.dialogDataCheckOut.setLocation(((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 150), ((int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 50));

            FrameEditarReserva.entradasEditDataCheckOut = new ArrayList<JTextField>();
            for (int i = 0; i < 3; i++) {
                FrameEditarReserva.entradasEditDataCheckOut.add(new JTextField());
            }
            FrameEditarReserva.entradasEditDataCheckOut.get(0).setColumns(2);
            FrameEditarReserva.entradasEditDataCheckOut.get(1).setColumns(2);
            FrameEditarReserva.entradasEditDataCheckOut.get(2).setColumns(4);

            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    String[] data = {FrameEditarReserva.entradasEditDataCheckOut.get(0).getText(), FrameEditarReserva.entradasEditDataCheckOut.get(1).getText(), FrameEditarReserva.entradasEditDataCheckOut.get(2).getText()};

                    FrameEditarReserva.edDataCheckOut.setText("Data de check-out: " + Arrays.toString(data));
                    FrameReservas.hospedes.get(FrameReservas.indiceHospede).setDataCheckOut(data);
                    atualizaCusto();

                    FrameEditarReserva.dialogDataCheckOut.dispose();
                }
            });

            JPanel painelDialogDataCheckOut = new JPanel();
            painelDialogDataCheckOut.add(new JLabel("   Informe a data de check-out:   "));
            painelDialogDataCheckOut.add(FrameEditarReserva.entradasEditDataCheckOut.get(0));
            painelDialogDataCheckOut.add(FrameEditarReserva.entradasEditDataCheckOut.get(1));
            painelDialogDataCheckOut.add(FrameEditarReserva.entradasEditDataCheckOut.get(2));
            painelDialogDataCheckOut.add(okBut);

            FrameEditarReserva.dialogDataCheckOut.add(painelDialogDataCheckOut);
            FrameEditarReserva.dialogDataCheckOut.setVisible(true);
        }
    }

    public static class EventoBotaoEditPodePassear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            FrameEditarReserva.dialogPodePassear = new JDialog(Home.telaEditarReserva);
            FrameEditarReserva.dialogPodePassear.setSize(250, 100);
            FrameEditarReserva.dialogPodePassear.setLocation((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 125, (int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 50);

            FrameEditarReserva.simPassear = new JRadioButton("Sim");
            FrameEditarReserva.naoPassear = new JRadioButton("Não");
            FrameEditarReserva.naoPassear.setSelected(true);
            
            FrameEditarReserva.grupoPassear = new ButtonGroup();
            FrameEditarReserva.grupoPassear.add(FrameEditarReserva.simPassear);
            FrameEditarReserva.grupoPassear.add(FrameEditarReserva.naoPassear);

            JPanel painelDialogPodePassear = new JPanel();
            painelDialogPodePassear.add(new JLabel("    O hospede pode passear?    "));
            painelDialogPodePassear.add(FrameEditarReserva.simPassear);
            painelDialogPodePassear.add(FrameEditarReserva.naoPassear);

            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Cachorro cachorro = (Cachorro) FrameReservas.hospedes.get(FrameReservas.indiceHospede);

                    if (FrameEditarReserva.simPassear.isSelected()) {
                        cachorro.setPodePassear(true);
                        FrameEditarReserva.editPodePassear.setText("Pode passear: " + true);
                    } else {
                        cachorro.setPodePassear(false);
                        FrameEditarReserva.editPodePassear.setText("Pode passear: " + false);
                    }
                    FrameReservas.hospedes.set(FrameReservas.indiceHospede, (Hospede) cachorro);
                    atualizaCusto();
                    
                    FrameEditarReserva.dialogPodePassear.dispose();
                }
            });
            painelDialogPodePassear.add(okBut);

            FrameEditarReserva.dialogPodePassear.add(painelDialogPodePassear);
            FrameEditarReserva.dialogPodePassear.setVisible(true);
        }
    }

    public static class EventoBotaoEditQuantCompanheirosGatil implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
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
            FrameEditarReserva.editQuantCompanheirosGa.setText("Quantidade de companheiros: " + escolhaC);

            atualizaCusto();
        }
    }

    private static void atualizaCusto() {
        if (FrameReservas.hospedes.get(0).getClass() == Cachorro.class) {
            Cachorro cachorro = (Cachorro) FrameReservas.hospedes.get(FrameReservas.indiceHospede);
            cachorro.calculaCusto();
            
            FrameReservas.hospedes.get(FrameReservas.indiceHospede).setCustoHospede(cachorro.getCustoHospede());
            FrameEditarReserva.LabelCustoHospede.setText("Custo do hospede:" + Float.toString(cachorro.getCustoHospede()));
        } else {
            Gato gato = (Gato) FrameReservas.hospedes.get(FrameReservas.indiceHospede);
            gato.calculaCusto();
            
            FrameReservas.hospedes.get(FrameReservas.indiceHospede).setCustoHospede(gato.getCustoHospede());
            FrameEditarReserva.LabelCustoHospede.setText(Float.toString(gato.getCustoHospede()));
        }
    }
}
