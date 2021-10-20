package com.ayminformatica.protegidas;

import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

public class SMS {

    public static void sendSMS(ArrayList<String> a,String mensaje) {
        String num_Fono = null;
        for (String num : a) {
            if(num != null)
                num_Fono = num;
            String enviar_mensaje = mensaje;
            System.out.println("El numero de telefono es "+num_Fono);
            try {
                SmsManager sms = SmsManager.getDefault(); // usando android SmsManager
                sms.sendTextMessage(num_Fono, null, enviar_mensaje, null, null); // agregando número y texto
            } catch (Exception e) {
                Toast.makeText(null, "SMS no enviado", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
