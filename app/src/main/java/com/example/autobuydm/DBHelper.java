package com.example.autobuydm;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nome = "BaseDados_AutoBuy.db";


    public DBHelper(@Nullable Context context) {
        super(context, nome, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String str = "CREATE TABLE Usuario(username TEXT PRIMARY KEY, senha TEXT);";
        db.execSQL(str);
        String anuncio = "CREATE TABLE Anuncio(modelo TEXT PRIMARY KEY, marca TEXT, preco NUMBER, foto BLOB, cliente TEXT);";
        db.execSQL(anuncio);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuario;");
        onCreate(db);
    }

    public long CriarUsuario(String username, String senha) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("senha", senha);
        long result = db.insert("Usuario", null, cv);
        return result;

    }


    public String ValidarLogin(String username, String senha) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Usuario WHERE username=? AND senha=?", new String[]{username, senha});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }


    public long UpdateUsuario(String username, String senha) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("senha", senha);
        return db.update("Usuario", cv, "username=?", new String[]{username});
    }

    public long DeleteUsuario(String username, String senha) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Usuario", "username=?", new String[]{username});
    }


    public Cursor SelectAllUsuario() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Usuario", null);
    }

    public Cursor SelectByUsernameUsuario(String username) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Usuario WHERE username=?",
                new String[]{username});
    }
    /*----------Anuncio----------*/
    public long criarAnuncio(Anuncio anuncio){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("marca", anuncio.getMarca());
        cv.put("modelo", anuncio.getModelo());
        cv.put("preco", anuncio.getPreco());
        cv.put("foto", anuncio.getFoto());
        cv.put("cliente", anuncio.getUsuario());
        long result = db.insert("Anuncio", null, cv);
        return result;
    }

    public long DeleteAnuncio(String modelo) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Anuncio", "modelo=?", new String[]{modelo});
    }

    public Cursor SelectAllAnuncio() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Anuncio", null);
    }

    public Cursor SelectByAnuncio(String modelo) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Anuncio WHERE modelo=?", new String[]{modelo});
    }

    public Cursor SelectByUsuario(String cliente) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM Anuncio WHERE cliente=?", new String[]{cliente});
    }
}
