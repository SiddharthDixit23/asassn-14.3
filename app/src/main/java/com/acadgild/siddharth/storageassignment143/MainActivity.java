package com.acadgild.siddharth.storageassignment143;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Button b1,b2;
    static final int READ_BLOCK_SIZE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.savefile);
        b2 = (Button) findViewById(R.id.checkfile);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });
    }

    private void readFile() {
        try{
            FileInputStream fileInputStream= openFileInput("myTextFilee.txt");
            InputStreamReader inputStreamReader= new InputStreamReader(fileInputStream);

            char [] inputBuffer=new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while((charRead=inputStreamReader.read(inputBuffer))>0){
                String readString=String.copyValueOf(inputBuffer,0,charRead);
                s+=readString;
            }
            inputStreamReader.close();

            Toast.makeText(getApplicationContext(),"File exists!!",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"File not found",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile()
    {
        try{
            FileOutputStream fileout= openFileOutput("myTextFilee.txt",MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileout);
            outputStreamWriter.write("my new file");
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
