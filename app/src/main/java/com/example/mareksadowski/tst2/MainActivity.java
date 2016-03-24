package com.example.mareksadowski.tst2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.StrictMode;
import java.util.HashMap;
import java.util.Map;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView txtView;
    String sentimentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);
        txtView = (TextView) findViewById(R.id.textView);
        sentimentType = "";


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("OnClick Fired");
                txtView.setText("OnClick Fired");


                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            AlchemyLanguage service = new AlchemyLanguage();
                            service.setApiKey("844de29ef7761fd309c079582e53bc304c10829f");

                            Map<String, Object> params = new HashMap<String, Object>();
                            params.put(AlchemyLanguage.TEXT,
                                    "Extending the Value of Your Java Apps for Mobile by Marek Sadowski - developer advocate");
                            DocumentSentiment sentiment = service.getSentiment(params);

                            System.out.println(sentiment);
                            sentimentType = sentiment.getSentiment().getType().name();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                thread.start();
                txtView.setText(sentimentType);
            }
        });

    }



}

