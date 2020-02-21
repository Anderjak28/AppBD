package ejemplos.com.appbd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ejemplos.com.appbd.PersonaContract.PersonaEntry;

/**
 * Manejador de la base de datos
 */
public class PersonaDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Persona.db";

    public PersonaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PersonaEntry.TABLE_NAME + " ("
                + PersonaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PersonaEntry.ID + " TEXT NOT NULL,"
                + PersonaEntry.NOMBRE + " TEXT NOT NULL,"
                + PersonaEntry.APELLIDOS + " TEXT NOT NULL,"
                + PersonaEntry.SEXO + " TEXT NOT NULL,"
                + PersonaEntry.EDAD + " TEXT NOT NULL,"
                + "UNIQUE (" + PersonaEntry.ID + "))");



        // Insertar datos ficticios para prueba inicial
        llenarData(db);

    }

    private void llenarData(SQLiteDatabase sqLiteDatabase) {
        mockLawyer(sqLiteDatabase, new Persona("Carlos ", "Perez",
                "masculino", "23"));
        mockLawyer(sqLiteDatabase, new Persona("Daniel ", "Samper",
                "masculino", "32"));
        mockLawyer(sqLiteDatabase, new Persona("Lucia ", "Aristizabal",
                "femenino", "30"));

    }

    public long mockLawyer(SQLiteDatabase db, Persona lawyer) {
        return db.insert(
                PersonaEntry.TABLE_NAME,
                null,
                lawyer.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveLawyer(Persona lawyer) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                PersonaEntry.TABLE_NAME,
                null,
                lawyer.toContentValues());

    }


    public String[][] getdatos(Cursor c){
        String[][] respuesta = new String[c.getCount()][c.getColumnCount()];
        //String resp = "";
        int i=0;
        if(c.moveToFirst()){
            do {
                respuesta[i][0]=c.getString(1);
                respuesta[i][1]=c.getString(2);
                respuesta[i][2]=c.getString(3);
                respuesta[i][3]=c.getString(4);
                respuesta[i][4]=c.getString(5);
                //resp = resp + c.getString(2) + "\t";
                //c.getString(1);
            }while (c.moveToNext());
        }
        return respuesta;
    }


    public Cursor getAllPersonas() {
        return getReadableDatabase()
                .query(
                        PersonaEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getLawyerById(String lawyerId) {
        Cursor c = getReadableDatabase().query(
                PersonaEntry.TABLE_NAME,
                null,
                PersonaEntry.ID + " LIKE ?",
                new String[]{lawyerId},
                null,
                null,
                null);

        return c;
    }

    public int deleteLawyer(String lawyerId) {
        return getWritableDatabase().delete(
                PersonaEntry.TABLE_NAME,
                PersonaEntry.ID + " LIKE ?",
                new String[]{lawyerId});
    }

    public int updateLawyer(Persona lawyer, String lawyerId) {
        return getWritableDatabase().update(
                PersonaEntry.TABLE_NAME,
                lawyer.toContentValues(),
                PersonaEntry.ID + " LIKE ?",
                new String[]{lawyerId}
        );
    }
}

