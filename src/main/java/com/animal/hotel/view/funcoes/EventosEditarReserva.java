package com.animal.hotel.view.funcoes;

import com.animal.hotel.view.telas.EditarReserva;
import com.animal.hotel.view.telas.Reservas;
import com.animal.hotel.view.telas.Home;

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

import javax.swing.JFrame;
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
    public static class EventosTela implements ComponentListener {
        @Override
        public void componentHidden(ComponentEvent arg0) {}

        @Override
        public void componentMoved(ComponentEvent arg0) {
            Home.posicaoTela = ((JFrame) arg0.getSource()).getLocation();
        }

        @Override
        public void componentResized(ComponentEvent arg0) {
            JFrame telaComponent = (JFrame) arg0.getSource();
            EditarReserva.tamanhoLaterais = (telaComponent.getWidth() - EditarReserva.larguraPainelEditHospede) / 2;
            EditarReserva.tamanhoCimaBaixo = (telaComponent.getHeight() - EditarReserva.alturaPainelEditHospede) / 2;
            EditarReserva.paineisEditarReserva.get(1).setPreferredSize(new Dimension(EditarReserva.tamanhoLaterais, EditarReserva.tamanhoLaterais));
            EditarReserva.paineisEditarReserva.get(2).setPreferredSize(new Dimension(EditarReserva.tamanhoLaterais, EditarReserva.tamanhoLaterais));
            EditarReserva.paineisEditarReserva.get(3).setPreferredSize(new Dimension(EditarReserva.tamanhoCimaBaixo, EditarReserva.tamanhoCimaBaixo));
            EditarReserva.paineisEditarReserva.get(4).setPreferredSize(new Dimension(EditarReserva.tamanhoCimaBaixo, EditarReserva.tamanhoCimaBaixo));
            Home.telaWidth = telaComponent.getWidth();
            Home.telaHeight = telaComponent.getHeight();
        }

        @Override
        public void componentShown(ComponentEvent arg0) {}
    }

    public static class EventoBotaoEditNomeHospede implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            String entrada;

            entrada = JOptionPane.showInputDialog("Insira o nome do Hospede:");
            
            if (entrada != null && !entrada.equals("")) {
                EditarReserva.editNomeHospede.setText("Nome do Hospede: " + entrada);
                Reservas.hospedes.get(Reservas.indiceHospede).setNome(entrada);
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
                EditarReserva.editFaseDaVida.setText("Fase da vida: Filhote");
                Reservas.hospedes.get(Reservas.indiceHospede).setFaseDaVida("Filhote");
            } else {
                if (escolha == 1) {
                    EditarReserva.editFaseDaVida.setText("Fase da vida: Adulto");
                    Reservas.hospedes.get(Reservas.indiceHospede).setFaseDaVida("Adulto");
                } else {
                    if (escolha == 2) {
                        EditarReserva.editFaseDaVida.setText("Fase da vida: Idoso");
                        Reservas.hospedes.get(Reservas.indiceHospede).setFaseDaVida("Idoso");
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
            EditarReserva.dialogEscolhaNomeRacao = new JDialog(Home.telaEditarReserva);
            EditarReserva.dialogEscolhaNomeRacao.setSize(300, 100);
            EditarReserva.dialogEscolhaNomeRacao.setLocation((int) (Home.posicaoTela.getX() + Home.telaWidth / 2 - 150), (int) (Home.posicaoTela.getY() + Home.telaHeight / 2 - 50));

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
            EditarReserva.menuRacoes = new JComboBox<>(racoes);

            JPanel painelDialogRacao = new JPanel();
            painelDialogRacao.add(new JLabel("Escolha a ração do hospede:"));
            painelDialogRacao.add(EditarReserva.menuRacoes);
            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    EditarReserva.editNomeRacao.setText("Ração do hospede: " + (String) EditarReserva.menuRacoes.getSelectedItem());
                    Reservas.hospedes.get(Reservas.indiceHospede).setNomeRacao((String) EditarReserva.menuRacoes.getSelectedItem());

                    Reservas.hospedes.get(Reservas.indiceHospede).calculaGastoRacao();
                    EditarReserva.LabelGastoRacao.setText("Gasto com ração: " + Reservas.hospedes.get(Reservas.indiceHospede).getGastoComRacao());
                    atualizaCusto();

                    EditarReserva.dialogEscolhaNomeRacao.dispose();
                }
            });
            painelDialogRacao.add(okBut);

            EditarReserva.dialogEscolhaNomeRacao.add(painelDialogRacao);
            EditarReserva.dialogEscolhaNomeRacao.setVisible(true);
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
                EditarReserva.editQuantRacaoGramas.setText("Quantidade de ração (g): " + entrada);
                Reservas.hospedes.get(Reservas.indiceHospede).setQuantRacaoGramas(entradaInt);

                Reservas.hospedes.get(Reservas.indiceHospede).calculaGastoRacao();
                EditarReserva.LabelGastoRacao.setText("Gasto com ração: " + Reservas.hospedes.get(Reservas.indiceHospede).getGastoComRacao());

                atualizaCusto();
            }
        }
    }

    public static class EventoBotaoEditPodeSocializarComOutros implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            EditarReserva.dialogPodeSocializar = new JDialog(Home.telaEditarReserva);
            EditarReserva.dialogPodeSocializar.setSize(250, 100);
            EditarReserva.dialogPodeSocializar.setLocation((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 125, (int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 50);

            EditarReserva.simSocia = new JRadioButton("Sim");
            EditarReserva.naoSocia = new JRadioButton("Não");
            EditarReserva.naoSocia.setSelected(Reservas.hospedes.get(Reservas.indiceHospede).isPodeSocializarComOutros());

            EditarReserva.grupoSocia = new ButtonGroup();
            EditarReserva.grupoSocia.add(EditarReserva.simSocia);
            EditarReserva.grupoSocia.add(EditarReserva.naoSocia);

            JPanel painelDialogSocializar = new JPanel();
            painelDialogSocializar.add(new JLabel("O hospede pode socializar?"));
            painelDialogSocializar.add(EditarReserva.simSocia);
            painelDialogSocializar.add(EditarReserva.naoSocia);
            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if (EditarReserva.simSocia.isSelected()) {
                        EditarReserva.editPodeSocializarComOutros.setText("Pode socializar com outros animais: " + true);
                        Reservas.hospedes.get(Reservas.indiceHospede).setPodeSocializarComOutros(true);
                    } else {
                        EditarReserva.editPodeSocializarComOutros.setText("Pode socializar com outros animais: " + false);
                        Reservas.hospedes.get(Reservas.indiceHospede).setPodeSocializarComOutros(false);
                    }
                    atualizaCusto();

                    EditarReserva.dialogPodeSocializar.dispose();
                }
            });
            painelDialogSocializar.add(okBut);

            EditarReserva.dialogPodeSocializar.add(painelDialogSocializar);
            EditarReserva.dialogPodeSocializar.setVisible(true);
        }
    }

    public static class EventoBotaoEdDataCheckIn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            EditarReserva.dialogDataCheckIn = new JDialog(Home.telaEditarReserva);
            EditarReserva.dialogDataCheckIn.setSize(250, 100);
            EditarReserva.dialogDataCheckIn.setLocation(((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 150), ((int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 50));

            EditarReserva.entradasEditDataCheckIn = new ArrayList<JTextField>();
            for (int i = 0; i < 3; i++) {
                EditarReserva.entradasEditDataCheckIn.add(new JTextField());
            }
            EditarReserva.entradasEditDataCheckIn.get(0).setColumns(2);
            EditarReserva.entradasEditDataCheckIn.get(1).setColumns(2);
            EditarReserva.entradasEditDataCheckIn.get(2).setColumns(4);

            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    String[] data = {EditarReserva.entradasEditDataCheckIn.get(0).getText(), EditarReserva.entradasEditDataCheckIn.get(1).getText(), EditarReserva.entradasEditDataCheckIn.get(2).getText()};

                    EditarReserva.edDataCheckIn.setText("Data de check-in: " + Arrays.toString(data));
                    Reservas.hospedes.get(Reservas.indiceHospede).setDataCheckIn(data);
                    atualizaCusto();

                    EditarReserva.dialogDataCheckIn.dispose();
                }
            });

            JPanel painelDialogDataCheckIn = new JPanel();
            painelDialogDataCheckIn.add(new JLabel("   Informe a data de check-in:   "));
            painelDialogDataCheckIn.add(EditarReserva.entradasEditDataCheckIn.get(0));
            painelDialogDataCheckIn.add(EditarReserva.entradasEditDataCheckIn.get(1));
            painelDialogDataCheckIn.add(EditarReserva.entradasEditDataCheckIn.get(2));
            painelDialogDataCheckIn.add(okBut);

            EditarReserva.dialogDataCheckIn.add(painelDialogDataCheckIn);
            EditarReserva.dialogDataCheckIn.setVisible(true);
        }
    }

    public static class EventoBotaoEdDataCheckOut implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            EditarReserva.dialogDataCheckOut = new JDialog(Home.telaEditarReserva);
            EditarReserva.dialogDataCheckOut.setSize(250, 100);
            EditarReserva.dialogDataCheckOut.setLocation(((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 150), ((int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 50));

            EditarReserva.entradasEditDataCheckOut = new ArrayList<JTextField>();
            for (int i = 0; i < 3; i++) {
                EditarReserva.entradasEditDataCheckOut.add(new JTextField());
            }
            EditarReserva.entradasEditDataCheckOut.get(0).setColumns(2);
            EditarReserva.entradasEditDataCheckOut.get(1).setColumns(2);
            EditarReserva.entradasEditDataCheckOut.get(2).setColumns(4);

            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    String[] data = {EditarReserva.entradasEditDataCheckOut.get(0).getText(), EditarReserva.entradasEditDataCheckOut.get(1).getText(), EditarReserva.entradasEditDataCheckOut.get(2).getText()};

                    EditarReserva.edDataCheckOut.setText("Data de check-out: " + Arrays.toString(data));
                    Reservas.hospedes.get(Reservas.indiceHospede).setDataCheckOut(data);
                    atualizaCusto();

                    EditarReserva.dialogDataCheckOut.dispose();
                }
            });

            JPanel painelDialogDataCheckOut = new JPanel();
            painelDialogDataCheckOut.add(new JLabel("   Informe a data de check-out:   "));
            painelDialogDataCheckOut.add(EditarReserva.entradasEditDataCheckOut.get(0));
            painelDialogDataCheckOut.add(EditarReserva.entradasEditDataCheckOut.get(1));
            painelDialogDataCheckOut.add(EditarReserva.entradasEditDataCheckOut.get(2));
            painelDialogDataCheckOut.add(okBut);

            EditarReserva.dialogDataCheckOut.add(painelDialogDataCheckOut);
            EditarReserva.dialogDataCheckOut.setVisible(true);
        }
    }

    public static class EventoBotaoEditPodePassear implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            EditarReserva.dialogPodePassear = new JDialog(Home.telaEditarReserva);
            EditarReserva.dialogPodePassear.setSize(250, 100);
            EditarReserva.dialogPodePassear.setLocation((int) Home.posicaoTela.getX() + Home.telaWidth / 2 - 125, (int) Home.posicaoTela.getY() + Home.telaHeight / 2 - 50);

            EditarReserva.simPassear = new JRadioButton("Sim");
            EditarReserva.naoPassear = new JRadioButton("Não");
            EditarReserva.naoPassear.setSelected(true);
            
            EditarReserva.grupoPassear = new ButtonGroup();
            EditarReserva.grupoPassear.add(EditarReserva.simPassear);
            EditarReserva.grupoPassear.add(EditarReserva.naoPassear);

            JPanel painelDialogPodePassear = new JPanel();
            painelDialogPodePassear.add(new JLabel("    O hospede pode passear?    "));
            painelDialogPodePassear.add(EditarReserva.simPassear);
            painelDialogPodePassear.add(EditarReserva.naoPassear);

            JButton okBut = new JButton("   Ok   ");
            okBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Cachorro cachorro = (Cachorro) Reservas.hospedes.get(Reservas.indiceHospede);

                    if (EditarReserva.simPassear.isSelected()) {
                        cachorro.setPodePassear(true);
                        EditarReserva.editPodePassear.setText("Pode passear: " + true);
                    } else {
                        cachorro.setPodePassear(false);
                        EditarReserva.editPodePassear.setText("Pode passear: " + false);
                    }
                    Reservas.hospedes.set(Reservas.indiceHospede, (Hospede) cachorro);
                    atualizaCusto();
                    
                    EditarReserva.dialogPodePassear.dispose();
                }
            });
            painelDialogPodePassear.add(okBut);

            EditarReserva.dialogPodePassear.add(painelDialogPodePassear);
            EditarReserva.dialogPodePassear.setVisible(true);
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


            Gato gato = (Gato) Reservas.hospedes.get(Reservas.indiceHospede);
            if (escolhaC == -1) {
                escolhaC = gato.getQuantCompanheirosGatil();
            }
            gato.setQuantCompanheirosGatil(escolhaC);

            Reservas.hospedes.set(Reservas.indiceHospede, (Hospede) gato);
            EditarReserva.editQuantCompanheirosGa.setText("Quantidade de companheiros: " + escolhaC);

            atualizaCusto();
        }
    }

    public static class EventoBotaoVoltar implements ActionListener {
        @Override
            public void actionPerformed(ActionEvent arg0) {
            Home.telaReservas.setLocation(Home.posicaoTela);
            Home.telaReservas.setSize(Home.telaWidth, Home.telaHeight);
            Home.telaReservas.setVisible(true);

            if (Reservas.hospedeIsCachorro) {
                Reservas.opcoesHospedes.get(EditarReserva.indiceButton).getLabelNome().setText("Nome do cachorro: " + Reservas.hospedes.get(Reservas.indiceHospede).getNome());
            } else {
                Reservas.opcoesHospedes.get(EditarReserva.indiceButton).getLabelNome().setText("Nome do gato: " + Reservas.hospedes.get(Reservas.indiceHospede).getNome());
            }
            Reservas.opcoesHospedes.get(EditarReserva.indiceButton).getLabelCusto().setText("Custo da hospedagem: " + Reservas.hospedes.get(Reservas.indiceHospede).getCustoHospede());

            Home.telaEditarReserva.dispose();
        }
    }

    private static void atualizaCusto() {
        if (Reservas.hospedes.get(0).getClass() == Cachorro.class) {
            Cachorro cachorro = (Cachorro) Reservas.hospedes.get(Reservas.indiceHospede);
            cachorro.calculaCusto();
            
            Reservas.hospedes.get(Reservas.indiceHospede).setCustoHospede(cachorro.getCustoHospede());
            EditarReserva.LabelCustoHospede.setText("Custo do hospede:" + Float.toString(cachorro.getCustoHospede()));
        } else {
            Gato gato = (Gato) Reservas.hospedes.get(Reservas.indiceHospede);
            gato.calculaCusto();
            
            Reservas.hospedes.get(Reservas.indiceHospede).setCustoHospede(gato.getCustoHospede());
            EditarReserva.LabelCustoHospede.setText(Float.toString(gato.getCustoHospede()));
        }
    }
}
