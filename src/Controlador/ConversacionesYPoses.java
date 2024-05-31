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
public class ConversacionesYPoses {

    private HellerKratos hk;
    private Maminha mh;
    private CP_Salami sm;

    public ConversacionesYPoses() {
        hk = new HellerKratos();
        mh = new Maminha();
        sm = new CP_Salami();

    }

    public String obtenerDialogoAc1(int d) {

        String dialogo = "";

        switch (d) {

            //  Acto 1.1
            case 1:
                dialogo = hk.obtenerDialogo("d1");
                break;
            case 2:
                dialogo = mh.obtenerDialogo("d1");
                break;
            case 3:
                dialogo = sm.obtenerDialogo("d1");
                break;
            case 4:
                dialogo = mh.obtenerDialogo("d2");
                break;
            case 5:
                dialogo = hk.obtenerDialogo("d3");
                break;
            case 6:
                dialogo = sm.obtenerDialogo("d2");
                break;
            case 7:
                dialogo = sm.obtenerDialogo("d3");
                break;
            case 8:
                dialogo = mh.obtenerDialogo("d3");
                break;
            case 9:
                dialogo = hk.obtenerDialogo("d3");
                break;

            //  Acto 2.2
            default:

        }

        return dialogo;
    }

    public String obtenerPosePersonajeAc1(int p) {

        String pose = "";

        switch (p) {

            //  Acto 1.1
            case 1:
                pose = hk.obtenerPose("p1");
                break;
            case 2:
                pose = mh.obtenerPose("p1");
                break;
            case 3:
                pose = sm.obtenerPose("p1");
                break;
            case 4:
                pose = mh.obtenerPose("p1");
                break;
            case 5:
                pose = hk.obtenerPose("p1");
                break;
            case 6:
                pose = sm.obtenerPose("p2");
                break;
            case 7:
                pose = sm.obtenerPose("p2");
                break;
            case 8:
                pose = mh.obtenerPose("p2");
                break;
            case 9:
                pose = hk.obtenerPose("p2");
                break;
            //  Acto 2.2

            default:

        }

        return pose;
    }

}
