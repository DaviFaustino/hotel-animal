package com.animal.hotel.view.componentes;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JButtonHospede extends JButton {
    private int indiceHospede;
    private int indiceButton;
    private JPanel dados;
    private JLabel labelIdResponsavel = new JLabel(), labelIdHospede = new JLabel(), labelNome = new JLabel(), labelCusto = new JLabel();

    public JButtonHospede() {
        super();
        dados = new JPanel();
        add(dados);
        dados.setLayout(new BoxLayout(dados, BoxLayout.Y_AXIS));
        dados.add(labelIdResponsavel);
        dados.add(labelIdHospede);
        dados.add(labelNome);
        dados.add(labelCusto);
    }

    public int getIndiceHospede() {
        return indiceHospede;
    }

    public void setIndiceHospede(int indiceHospede) {
        this.indiceHospede = indiceHospede;
    }

    public JLabel getLabelIdResponsavel() {
        return labelIdResponsavel;
    }

    public JLabel getLabelIdHospede() {
        return labelIdHospede;
    }

    public JLabel getLabelNome() {
        return labelNome;
    }

    public JLabel getLabelCusto() {
        return labelCusto;
    }
   
    public int getIndiceButton() {
        return indiceButton;
    }

    public void setIndiceButton(int indiceButton) {
        this.indiceButton = indiceButton;
    }
}
