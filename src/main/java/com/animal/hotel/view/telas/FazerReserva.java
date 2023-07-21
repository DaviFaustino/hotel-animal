package com.animal.hotel.view.telas;

import com.animal.hotel.view.funcoes.EventosFazerReserva.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FazerReserva extends JFrame {
    public static ArrayList<JLabel> labelsFazerReserva;
    public static boolean hospedeIsCachorro;
    public static JRadioButton filhote, adulto, idoso, simPassear, naoPassear, simSocia, naoSocia;
    public static JComboBox menuRacoes, menuQuantCompG;
    public static ArrayList<JTextField> entradasTextoReservaHospede;
    public static JButton voltarReservaHospede, salvarReservaHospede;
    static ButtonGroup grupoSocia, grupoPassear, grupoFase;
    public static HashMap<String, Float> opcoesRacoes;

    public FazerReserva(boolean hospedeIsCachorro) {
        super();
        FazerReserva.hospedeIsCachorro = hospedeIsCachorro;

        setTitle("Fazer reserva de hospede");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Home.telaWidth, Home.telaHeight);
        setLocation(Home.posicaoTela);
        setLayout(null);

        addComponentListener(new EventosTela());

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
        voltarReservaHospede.addActionListener(new EventoBotaoVoltar());

        salvarReservaHospede = new JButton("Salvar");
        salvarReservaHospede.addActionListener(new EventoBotaoSalvar());


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
