package thuvienvuive.Database;

import java.sql.*;

public class Connection {
    String host = "";
    String userName = "";
    String password = "";
    String dbName = "";

    java.sql.Connection connection = null;
    Statement stament = null;
    ResultSet resultSet = null;

    public Connection(String host, String userName, String password, String dbName) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.dbName = dbName;
    }

    protected void testDriver(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    protected java.sql.Connection getConnection() throws Exception{
        if (this.connection == null){
            testDriver();
            String url = "jdbc:sqlserver://" + this.host + ":1143;databasename=" + this.dbName;
            try{
                this.connection = DriverManager.getConnection(url, this.userName, this.password);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return this.connection;
    }

    protected Statement getStament() throws Exception{
        if (this.stament == null || this.stament.isClosed()){
            this.stament = this.getConnection().createStatement();
        }
        return this.stament;
    }

    public ResultSet excutedQuery(String query) throws Exception{
        try{
            this.resultSet = getStament().executeQuery(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return this.resultSet;
    }

    public int excuteUpdate(String query) throws Exception{
        int res = Integer.MAX_VALUE;
        try{
            res = getStament().executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public void closeConnect() throws SQLException {
        if(this.resultSet != null && !this.resultSet.isClosed()){
            this.resultSet.close();
            this.resultSet = null;
        }
        if(this.stament != null && !this.stament.isClosed()){
            this.stament.close();
            this.stament = null;
        }
        if(this.connection != null && !this.connection.isClosed()){
            this.connection.close();
            this.connection = null;
        }
    }
}