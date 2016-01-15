package com.softgridtech.reading_files_from_assets_folder;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class MainActivity extends Activity {

    private TextView fileContent;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        fileContent = (TextView) findViewById(R.id.file_content);
        Button readFileButton = (Button) findViewById(R.id.read_file);
        readFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readAndDisplayFileContentFromAssetsFolder();
            }
        });
    }


    private void readAndDisplayFileContentFromAssetsFolder() {
        AssetManager mgr = this.context.getAssets();
        String filename = null;
        try {
            filename = "assets.txt";
            System.out.println("filename : " + filename);
            InputStream in = mgr.open(filename, AssetManager.ACCESS_BUFFER);
            String sHTML = StreamToString(in);
            in.close();
            fileContent.setText(sHTML);
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String StreamToString(InputStream in) throws IOException {
        if (in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
        }
        return writer.toString();
    }
}