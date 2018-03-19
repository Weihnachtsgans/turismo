package ve.edu.uc.facyt.turismo;

import java.sql.*;
import java.util.List;

public class Model {

    private String table;
    private String[] primaryKeys;
    private String[] attributes;

    /**
     * Ejecuta una query.
     * @param SQL Representa la query
     * @param parameters Representa los parámetros de la query
     * @return Resultado de la query
     * @throws SQLException
     */

    public static ResultSet executeSelectQuery(String SQL, List<Object> parameters,Connection conn) throws SQLException{
        int index = 1;
        PreparedStatement stmt = conn.prepareStatement(SQL);
        stmt = setPreparedStatementValues(parameters, index, stmt);
        return stmt.executeQuery();
    }

    /**
     * Ejecuta una query que no requiera retornar datos
     * @param SQL Representa la query
     * @param parameters Lista de parámetros
     * @param conn Conexión a la BD
     * @return Booleano representando si hubo cambios o no
     * @throws SQLException
     */

    public static Boolean executePostQuery(String SQL, List<Object> parameters, Connection conn) throws SQLException{
        int index = 1;
        PreparedStatement stmt = conn.prepareStatement(SQL);
        stmt = setPreparedStatementValues(parameters, index, stmt);
        return stmt.executeUpdate() > 0;
    }

    private static PreparedStatement setPreparedStatementValues(List<Object> parameters, int index, PreparedStatement stmt) throws SQLException {
        for(Object ob: parameters){
            if(ob.getClass() == String.class){
                stmt.setString(index,(String)ob);
            }
            else if(ob.getClass() == int.class){
                stmt.setInt(index,(int)ob);
            }
            else if(ob.getClass() == Boolean.class){
                stmt.setBoolean(index,(Boolean)ob);
            }
            else if(ob.getClass() == float.class){
                stmt.setFloat(index,(float)ob);
            }
            index++;
        }
        return stmt;
    }
}
