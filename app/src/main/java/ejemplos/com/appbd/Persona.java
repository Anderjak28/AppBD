package ejemplos.com.appbd;

import android.content.ContentValues;
import android.database.Cursor;

import ejemplos.com.appbd.PersonaContract.PersonaEntry;

import java.util.UUID;

/**
 * Entidad "abogado"
 */
public class Persona {
    private String id;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String edad;


    public Persona(String nombre,
                  String apellidos, String sexo,
                  String edad) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.edad = edad;

    }

    public Persona(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(PersonaEntry.ID));
        nombre = cursor.getString(cursor.getColumnIndex(PersonaEntry.NOMBRE));
        apellidos = cursor.getString(cursor.getColumnIndex(PersonaEntry.APELLIDOS));
        sexo = cursor.getString(cursor.getColumnIndex(PersonaEntry.SEXO));
        edad = cursor.getString(cursor.getColumnIndex(PersonaEntry.EDAD));

    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(PersonaEntry.ID, id);
        values.put(PersonaEntry.NOMBRE, nombre);
        values.put(PersonaEntry.APELLIDOS, apellidos);
        values.put(PersonaEntry.SEXO, sexo);
        values.put(PersonaEntry.EDAD, edad);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEdad() {
        return edad;
    }
}

