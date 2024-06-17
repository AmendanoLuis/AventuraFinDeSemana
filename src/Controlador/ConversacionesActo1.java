package Controlador;

import java.util.List;
import java.util.Map;
import personajes.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class ConversacionesActo1 {

    private HellerKratos hk;
    private Maminha mh;
    private CP_Salami sm;

    private String[] dialogos;
    private String[] poses;

    private int dialogoActual = 0;
    private int poseActual = 0;

    private static final int cantConversacion = 11;

    public ConversacionesActo1() {
        hk = new HellerKratos();
        mh = new Maminha();
        sm = new CP_Salami();

        this.dialogos = new String[cantConversacion];
        this.poses = new String[cantConversacion];

        guardarConversacionOrdenada();
        guardarPosesOrdenada();
    }

    private void guardarConversacionOrdenada() {
        dialogos[0] = hk.obtenerDialogo("d1");
        dialogos[1] = sm.obtenerDialogo("d1");
        dialogos[2] = mh.obtenerDialogo("d1");
        dialogos[3] = hk.obtenerDialogo("d2");
        dialogos[4] = sm.obtenerDialogo("d2");
        dialogos[5] = hk.obtenerDialogo("d3");

    }

    private void guardarPosesOrdenada() {

        poses[0] = hk.obtenerPose("p1");
        poses[1] = sm.obtenerPose("p2");
        poses[2] = mh.obtenerPose("p1");
        poses[3] = hk.obtenerPose("p1");
        poses[4] = sm.obtenerPose("p2");
        poses[5] = hk.obtenerPose("p1");

    }

    public void cargarDialogoCahorros() {
        dialogos[6] = "Cahorros 1";
        dialogos[7] = "Cahorros 2";
        dialogos[8] = "Cahorros 3";
        dialogos[9] = "Cahorros 4";

        poses[6] = sm.obtenerPose("p2");
        poses[7] = sm.obtenerPose("p3");
        poses[8] = sm.obtenerPose("p1");
        poses[9] = sm.obtenerPose("p2");

        dialogoActual = 6;
        poseActual = 6;
    }

    public void cargarDialogoSierraNevada() {
        dialogos[6] = "Sierra Nevada 1";
        dialogos[7] = "Sierra Nevada 2";
        dialogos[8] = "Sierra Nevada 3";
        dialogos[9] = "Sierra Nevada 4";

        poses[6] = hk.obtenerPose("p1");
        poses[7] = hk.obtenerPose("p2");
        poses[8] = hk.obtenerPose("p2");
        poses[9] = hk.obtenerPose("p1");

        dialogoActual = 6;
        poseActual = 6;

    }

    public String obtenerDialogos() {
        if (dialogoActual >= 0 && dialogoActual <= cantConversacion) {
            String dialogo = dialogos[dialogoActual];
            dialogoActual++;
            return dialogo;
        } else {
            return "Fin de la conversación";
        }
    }

    public String obtenerPoses() {
        if (poseActual >= 0 && poseActual < cantConversacion) {
            String pose = poses[poseActual];
            poseActual++;
            return pose;
        } else {
            return "Fin de las poses";
        }
    }
}
