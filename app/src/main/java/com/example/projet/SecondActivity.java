package com.example.projet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class SecondActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton appel;
    private ImageButton localisation;
    private ImageButton lampetorche;
    private ImageButton niveau;
    private ImageButton enceinte;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        appel = (ImageButton) findViewById(R.id.btnappel);
        appel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, AppelActivity.class);
                startActivity(intent);
            }

        });
        localisation = (ImageButton) findViewById(R.id.btnlocalisation);
        localisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Uri uri = Uri.parse("http://www.gitab.univ-nantes.fr ");
              //  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
              //  startActivity(intent);
            }

        });
        lampetorche = (ImageButton) findViewById(R.id.btnlampe);
        lampetorche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Uri uri = Uri.parse("http://www.webmail-etu.univ-nantes.fr");
                // Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                // startActivity(intent);
            }
        });

        niveau = (ImageButton) findViewById(R.id.btnniveau);
        niveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uri uri = Uri.parse("http://www.webmail-etu.univ-nantes.fr");
                // Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                // startActivity(intent);
            }
        });

        enceinte = (ImageButton) findViewById(R.id.btnenceinte);
        enceinte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Uri uri = Uri.parse("http://www.webmail-etu.univ-nantes.fr");
                // Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                // startActivity(intent);
            }
        });
    }


}
