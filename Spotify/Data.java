import java.sql.*;

//interface data
public interface Data {
    void view() throws SQLException;
    void insert() throws SQLException;
    void update() throws SQLException;
    void save() throws SQLException;
    void delete() throws SQLException;
}
