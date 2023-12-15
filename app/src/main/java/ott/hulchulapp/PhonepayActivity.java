package ott.hulchulapp;

import static org.apache.commons.codec.digest.DigestUtils.sha256;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.phonepe.intent.sdk.api.PhonePe;
import com.phonepe.intent.sdk.api.PhonePeInitException;
import com.phonepe.intent.sdk.api.UPIApplicationInfo;
import com.phonepe.intent.sdk.api.models.PhonePeEnvironment;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.List;


public class PhonepayActivity extends AppCompatActivity {
    Context context;


   /* {
        "keyIndex": 1,
            "key": "3901e920-aab1-44eb-b8ac-932b56fdb531"
    }*/

  //  String apiEndPoint = "/pg/v1/pay";
   // String apiEndPoint = "https://api-preprod.phonepe.com/pg/v1/pay";
    String apiEndPoint = "https://api.phonepe.com/apis/hermes/pg/v1/pay";
    String salt = "3901e920-aab1-44eb-b8ac-932b56fdb531"; // salt key
    String MERCHANT_ID = "M1GQPJSNH1HW";  // Merhcant id;
    //String MERCHANT_TID = "181020231969857";
    String BASE_URL = "https://api-preprod.phonepe.com/";
    Button pay;
   // PhonePeEnvironment environment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonepay_kotline);
        pay = (Button) findViewById(R.id.button);

        context=this;
        PhonePe.init(context, PhonePeEnvironment.UAT,MERCHANT_ID, "com.phonepe.app");

        try {
            PhonePe.setFlowId("2323PH"); // Recommended, not mandatory , An alphanumeric string without any special character
            List<UPIApplicationInfo> upiApps = PhonePe.getUpiApps();
        } catch (PhonePeInitException exception) {
            exception.printStackTrace();
        }


    }
}
