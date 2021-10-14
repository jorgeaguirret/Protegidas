package com.ayminformatica.protegidas;

import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.ArrayList;

public class SMS {

    public static void sendSMS(ArrayList<String> a,String msg) {
        String phone_Num = null;
        for (String num : a) {
            if(num != null)
                phone_Num = num;
            String send_msg = msg;
            System.out.println("Numero de telefono es: "+phone_Num);
            try {
                SmsManager sms = SmsManager.getDefault(); // using android SmsManager
                sms.sendTextMessage(phone_Num, null, send_msg, null, null); // adding number and text
            } catch (Exception e) {
                Toast.makeText(null, "Sms no enviado", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
