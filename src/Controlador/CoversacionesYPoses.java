package Controlador;

import personajes.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class CoversacionesYPoses {

    //  Personajes
    private HellerKratos hk;
    private Maminha mh;
    private CP_Salame sm;

    //  V. Control Dialogo
    private int dialogoActual;

    public String obtenerDialogoAc1(int d) {

        String dialogo = "";

        switch (d) {

        //  Acto 1.1
            case 1:
                dialogo = hk.obtenerDialogo("d1");
            case 2:
                dialogo = mh.obtenerDialogo("d1");
            case 3:
                dialogo = sm.obtenerDialogo("d1");
            case 4:
                dialogo = hk.obtenerDialogo("d2");
            case 5:
                dialogo = sm.obtenerDialogo("d3");
            case 6:
                dialogo = mh.obtenerDialogo("d3");
        //  Acto 2.2
                
            default:

        }

        return dialogo;
    }

    public String obtenerPosePersonajeAc1(int p) {

        return " ";
    }

}
