package ejemplos.com.appbd;
import android.provider.BaseColumns;

/**
 * Esquema de la base de datos para abogados
 */
public class PersonaContract {

    public static abstract class PersonaEntry implements BaseColumns{
        public static final String TABLE_NAME ="persona";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String APELLIDOS = "apellidos";
        public static final String SEXO = "sexo";
        public static final String EDAD = "edad";

    }
}

