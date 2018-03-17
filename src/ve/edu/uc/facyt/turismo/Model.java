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
     * @return
     * @throws SQLException
     */

    public static ResultSet executeSelectQuery(String SQL, List<Object> parameters,Connection conn) throws SQLException{
        int index = 1;
        PreparedStatement stmt = conn.prepareStatement(SQL);
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
        return stmt.executeQuery();
    }

    public static Boolean executePostQuery(String SQL, List<Object> parameters, Connection conn){
        return true;
    }
}
