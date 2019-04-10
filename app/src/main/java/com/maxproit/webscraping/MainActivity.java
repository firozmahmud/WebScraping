package com.maxproit.webscraping;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView headlineTextView;
    private TextView articleTextView;
    private String title = "";
    private String desc = "";
    private static final String URL = "https://www.tutorialspoint.com/kotlin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headlineTextView = findViewById(R.id.headline);
        articleTextView = findViewById(R.id.article);

        new GetDataFromWeb().execute();
    }

    class GetDataFromWeb extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            StringBuilder builder = new StringBuilder();

            try {
                Document doc = Jsoup.connect("https://www.tutorialspoint.com/kotlin").get();
                title = doc.title();
                Elements body = doc.select("p");

                desc = body.get(0).text();

                builder.append(body.get(0).text()).append("\n\n")
                        .append(body.get(1).text()).append("\n\n")
                        .append(body.get(2).text()).append("\n\n\n");


            } catch (IOException e) {
                builder.append("Error : ").append(e.getMessage()).append("\n");
            }

            desc = builder.toString();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            headlineTextView.setText(title);
            articleTextView.setText(desc);

        }
    }
}
