package com.example.fagossa.japanesevocabulary;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.fagossa.japanesevocabulary.parsing.Word;
import com.example.fagossa.japanesevocabulary.parsing.WordParser;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String DB_URL = "https://raw.githubusercontent.com/fagossa/JapaneseVocabulary/data/data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WordParser wordParser = WordParser.loadFromUrl(DB_URL);

        buildComponents(wordParser);
    }

    private void buildComponents(final WordParser wordParser) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word aWord = wordParser.randomWord();
                if (aWord != null) {
                    Snackbar.make(view, aWord.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
        TextView label = (TextView) findViewById(R.id.Label);

        // option 1
        Button option1 = (Button) findViewById(R.id.Option1);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "First Action feedback", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // option 2
        Button option2 = (Button) findViewById(R.id.Option2);
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Second Action feedback", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // option 3
        Button option3 = (Button) findViewById(R.id.Option3);
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Third Action feedback", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
