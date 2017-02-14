package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbDao {
	private Connection connection;
	private String driver;
	private String url;
	private String username;
	private String password;
	public DbDao(){}
	public DbDao(String driver, String url, String username, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	//成员属性的getter和setter方法
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//获取数据库连接
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		if(connection == null){
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}
	//插入记录
	public boolean insert(String sql,Object...args) throws ClassNotFoundException, SQLException{
		PreparedStatement pstm = getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		for(int i = 0; i < args.length; i++){
			pstm.setObject(i + 1, args[i]);
		}
		if(pstm.executeUpdate() != 1){
			return false;
		}
		return true;
	}
	//执行查询
	public ResultSet query(String sql,Object...args) throws ClassNotFoundException, SQLException{
		PreparedStatement pstm = getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		for(int i = 0; i < args.length; i++){
			pstm.setObject(i + 1, args[i]);
		}
		return pstm.executeQuery();
	}
	//执行修改
	public void modify(String sql,Object...args) throws ClassNotFoundException, SQLException{
		PreparedStatement pstm = getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		for(int i = 0; i < args.length; i++){
			pstm.setObject(i + 1, args[i]);
		}
		pstm.executeUpdate();
		pstm.close();
	}
	//关闭数据库连接
	public void closeConnection() throws SQLException{
		if(connection != null && !connection.isClosed()){
			connection.close();
		}
	}
}
