package es.miguelgonzalezgomez.dataBaseFun.bd;

import es.miguelgonzalezgomez.dataBaseFun.bd.domain.TiposBasesDeDatos;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miguel González
 */
public class TestAnalizadorTextoConsulta {
    
    private AnalizadorTextoConsulta analizadorTextoConsulta;
    
    public TestAnalizadorTextoConsulta() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void consultaSimpleOracle() {
        String consultaSQLAnalizar = "SELECT * FROM TABLA";
        analizadorTextoConsulta = new AnalizadorTextoConsulta(
                TiposBasesDeDatos.TIPO_BASE_DATOS.ORACLE,
                consultaSQLAnalizar);
        
        assertEquals(
                "Se esperaba obtener una consulta SQL",
                1,
                analizadorTextoConsulta.numConsultasExistentes()
        );
        
        assertEquals(
                "Se esperaba obtener la consulta SQL inicial",
                consultaSQLAnalizar,
                analizadorTextoConsulta.getConsulta(1)
        );
        
        assertEquals(
                "Se esperaba que la consulta fuera de tipo Ejecutar",
                true,
                analizadorTextoConsulta.isEjecutarQuery(1)
        );
    }
    
    @Test
    public void consultaDobleOracle() {
        String consultaSQLAnalizar = "SELECT * FROM TABLA;SELECT * FROM TABLA";
        analizadorTextoConsulta = new AnalizadorTextoConsulta(
                TiposBasesDeDatos.TIPO_BASE_DATOS.ORACLE,
                consultaSQLAnalizar);
        
        assertEquals(
                "Se esperaba obtener dos consultas SQL",
                2,
                analizadorTextoConsulta.numConsultasExistentes()
        );
        
        assertEquals(
                "Se esperaba obtener la primera consulta SQL inicial",
                "SELECT * FROM TABLA",
                analizadorTextoConsulta.getConsulta(1)
        );
        
        assertEquals(
                "Se esperaba obtener la segunda consulta SQL inicial",
                "SELECT * FROM TABLA",
                analizadorTextoConsulta.getConsulta(2)
        );
        
        assertEquals(
                "Se esperaba que la primera consulta fuera de tipo Ejecutar",
                true,
                analizadorTextoConsulta.isEjecutarQuery(1)
        );
        
        assertEquals(
                "Se esperaba que la segunda consulta fuera de tipo Ejecutar",
                true,
                analizadorTextoConsulta.isEjecutarQuery(2)
        );
    }
    
    @Test
    public void consultaComplejaOracle() {
        String consultaSQLAnalizar = "SELECT * FROM TABLA wheRe palabra like \'\";\';SELECT * FROM TABLA";
        analizadorTextoConsulta = new AnalizadorTextoConsulta(
                TiposBasesDeDatos.TIPO_BASE_DATOS.ORACLE,
                consultaSQLAnalizar);
        
        assertEquals(
                "Se esperaba obtener dos consultas SQL",
                2,
                analizadorTextoConsulta.numConsultasExistentes()
        );
        
        assertEquals(
                "Se esperaba obtener la primera consulta SQL inicial",
                "SELECT * FROM TABLA wheRe palabra like \'\";\'",
                analizadorTextoConsulta.getConsulta(1)
        );
        
        assertEquals(
                "Se esperaba obtener la segunda consulta SQL inicial",
                "SELECT * FROM TABLA",
                analizadorTextoConsulta.getConsulta(2)
        );
        
        assertEquals(
                "Se esperaba que la primera consulta fuera de tipo Ejecutar",
                true,
                analizadorTextoConsulta.isEjecutarQuery(1)
        );
        
        assertEquals(
                "Se esperaba que la segunda consulta fuera de tipo Ejecutar",
                true,
                analizadorTextoConsulta.isEjecutarQuery(2)
        );
    }
    
    @Test
    public void consultaProcedureOracle() {
        String procedimientoOracleSQL = "CREATE OR REPLACE Procedure UpdateCourse "
                + "( name_in IN varchar2 ) IS cnumber number; cursor c1 is select "
                + "course_number from courses_tbl where course_name = name_in; "
                + "BEGIN open c1; fetch c1 into cnumber; if c1%notfound then "
                + "cnumber := 9999; end if; insert into student_courses ( "
                + "course_name, course_number ) values ( name_in, cnumber ); "
                + "commit; close c1; EXCEPTION WHEN OTHERS THEN "
                + "raise_application_error(-20001,'An error was encountered"
                + " - '||SQLCODE||' -ERROR- '||SQLERRM); END;";
        String segundaConsulta = "SELECT * FROM TABLA";
        
        analizadorTextoConsulta = new AnalizadorTextoConsulta(
                TiposBasesDeDatos.TIPO_BASE_DATOS.ORACLE,
                procedimientoOracleSQL + segundaConsulta);
        
        assertEquals(
                "Se esperaba obtener una consulta SQL",
                2,
                analizadorTextoConsulta.numConsultasExistentes()
        );
        
        assertEquals(
                "Se esperaba obtener la consulta SQL inicial",
                procedimientoOracleSQL.substring(0, procedimientoOracleSQL.length() - 1),
                analizadorTextoConsulta.getConsulta(1)
        );
        
        assertEquals(
                "Se esperaba que la consulta no fuera de tipo Ejecutar",
                false,
                analizadorTextoConsulta.isEjecutarQuery(1)
        );
        
        assertEquals(
                "Se esperaba obtener la consulta SQL inicial",
                segundaConsulta,
                analizadorTextoConsulta.getConsulta(2)
        );
        
        assertEquals(
                "Se esperaba que la consulta fuera de tipo Ejecutar",
                true,
                analizadorTextoConsulta.isEjecutarQuery(2)
        );
    }
}