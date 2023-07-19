package com.animal.hotel.view.telas;

import com.animal.hotel.model.Cachorro;
import com.animal.hotel.model.Gato;
import com.animal.hotel.view.funcoes.EventosEditarReserva.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    public static JButton editNomeHospede, editFaseDaVida, editQuantRacaoGramas, editNomeRacao, editPodeSocializarComOutros, 
                    edDataCheckIn, edDataCheckOut, editPodePassear, editQuantCompanheirosGa;
    public static JLabel LabelGastoRacao, LabelCustoHospede;
    public static JDialog dialogEscolhaNomeRacao, dialogPodeSocializar, dialogDataCheckIn, dialogDataCheckOut, dialogPodePassear;
    public static ArrayList<JTextField> entradasEditDataCheckIn, entradasEditDataCheckOut;
    public static JComboBox menuRacoes;
    public static ButtonGroup grupoSocia, grupoPassear;
    public static JRadioButton simPassear, naoPassear, simSocia, naoSocia;
    static int indiceButton;

    public FrameEditarReserva(int indiceButton) {
        super();
        FrameEditarReserva.indiceButton = indiceButton;

        if (FrameReservas.hospedeIsCachorro) {
            setTitle("Detalhes Cachorro");
        } else {
            setTitle("Detalhes Gato");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Home.telaWidth, Home.telaHeight);
        setLocation(Home.posicaoTela);
        setLayout(new BorderLayout());
        setVisible(true);
        Home.telaReservas.setVisible(false);
        
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
                Home.posicaoTela = getLocation();
            }

            @Override
            public void componentResized(ComponentEvent arg0) {
                tamanhoLaterais = (getWidth() - larguraPainelEditHospede) / 2;
                tamanhoCimaBaixo = (getHeight() - alturaPainelEditHospede) / 2;
                paineisEditarReserva.get(1).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                paineisEditarReserva.get(2).setPreferredSize(new Dimension(tamanhoLaterais, tamanhoLaterais));
                paineisEditarReserva.get(3).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
                paineisEditarReserva.get(4).setPreferredSize(new Dimension(tamanhoCimaBaixo, tamanhoCimaBaixo));
                Home.telaWidth = getWidth();
                Home.telaHeight = getHeight();
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
        paineisEditarReserva.get(0).getComponent(0).setFont(Home.fontePadrao);
        paineisEditarReserva.get(0).getComponent(2).setFont(Home.fontePadrao);
        paineisEditarReserva.get(0).getComponent(3).setFont(Home.fontePadrao);
        paineisEditarReserva.get(0).getComponent(8).setFont(Home.fontePadrao);
        paineisEditarReserva.get(0).getComponent(14).setFont(Home.fontePadrao);

        editNomeHospede.addActionListener(new EventoBotaoEditNomeHospede());
        editFaseDaVida.addActionListener(new EventoBotaoEditFaseDaVida());
        editNomeRacao.addActionListener(new EventoBotaoEditNomeRacao());
        editQuantRacaoGramas.addActionListener(new EventoBotaoEditQuantRacaoGramas());
        editPodeSocializarComOutros.addActionListener(new EventoBotaoEditPodeSocializarComOutros());
        edDataCheckIn.addActionListener(new EventoBotaoEdDataCheckIn());
        edDataCheckOut.addActionListener(new EventoBotaoEdDataCheckOut());
        if (FrameReservas.hospedes.get(0).getClass() == Cachorro.class) {
            editPodePassear.addActionListener(new EventoBotaoEditPodePassear());
        } else {
            editQuantCompanheirosGa.addActionListener(new EventoBotaoEditQuantCompanheirosGatil());
        }
        

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Home.telaReservas.setLocation(Home.posicaoTela);
                Home.telaReservas.setSize(Home.telaWidth, Home.telaHeight);
                Home.telaReservas.setVisible(true);

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

}
