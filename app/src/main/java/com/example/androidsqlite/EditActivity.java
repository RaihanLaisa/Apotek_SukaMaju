package com.example.androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class EditActivity extends AppCompatActivity {

    DatabaseManager dm;
    private EditText eNm,eStok,ekode;
    private Button bUbah,bHapus,bKembali;
    TableLayout tabel4tampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        dm = new DatabaseManager(this);
        tabel4tampil= (TableLayout) findViewById(R.id.tabelTampil);
        ekode = (EditText) findViewById(R.id.edtextkode2);
        eNm = (EditText) findViewById((R.id.edtextnama2));
        eStok = (EditText) findViewById(R.id.edtextstok2);
        bUbah = (Button) findViewById(R.id.btnUbah);
        bHapus = (Button) findViewById(R.id.btnHapus);
        bKembali = (Button) findViewById(R.id.btnKembali);

        bUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahTable();
            }
        }); updateTable();

        bHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusTable();
            }
        }); updateTable();

        bKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);

                // Memulai aktivitas baru
                startActivity(intent);
            }
        });
    }

    protected void ubahTable() {
        try {
            dm.UpdateRecord(Integer.parseInt(ekode.getText().toString()),eNm.getText().toString(),eStok.getText().toString());
            Toast.makeText(getBaseContext(),
                    eNm.getText().toString() + ", berhasil diubah", Toast.LENGTH_SHORT).show();
            updateTable();
            kosongkanField();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "gagal ubah, "+e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    protected void hapusTable() {
        try {
            dm.DeleteRecord(Integer.parseInt(ekode.getText().toString()));
            Toast.makeText(getBaseContext(),
                    eNm.getText().toString() + ", berhasil dihapus", Toast.LENGTH_SHORT).show();
            updateTable();
            kosongkanField();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "gagal hapus, "+e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    protected void kosongkanField() {
        eNm.setText("");
        eStok.setText("");
        ekode.setText("");
    }

    @SuppressLint("SetTextI18n")
    protected void  updateTable() {
        while (tabel4tampil.getChildCount() > 1) {
            tabel4tampil.removeViewAt(1);
        }

        ArrayList<ArrayList<Object>> data = dm.ambilSemuaBaris();
        for (int posisi = 0; posisi < data.size(); posisi++) {
            TableRow tabelBaris = new TableRow(this);
            ArrayList<Object> baris = data.get(posisi);

            TextView idTxt = new TextView(this);
            idTxt.setTextSize(24);
            idTxt.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            idTxt.setText(" "+baris.get(0).toString()+".      ");
            tabelBaris.addView((idTxt));

            TextView namaTxt = new TextView(this);
            namaTxt.setTextSize(24);
            namaTxt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            namaTxt.setText(baris.get(1).toString()+"      ");
            tabelBaris.addView(namaTxt);

            TextView stokTxt = new TextView(this);
            stokTxt.setTextSize(24);
            stokTxt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            stokTxt.setText("Stok: "+baris.get(2).toString());
            tabelBaris.addView(stokTxt);

            tabel4tampil.addView(tabelBaris);
        }

    }




}
