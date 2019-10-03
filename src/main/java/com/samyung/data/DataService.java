package com.samyung.data;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.samyung.entity.Customer;
import com.samyung.entity.CustomerStatus;
import com.samyung.entity.Note;
import com.samyung.param.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataService {
  private final static Logger LOGGER = Logger.getLogger(DataService.class.getName());

  private static DataService instance = null;
  private ComboPooledDataSource cpds = null;
  private String host = "localhost";
  private int port = 3306;
  private String dbName = "propellerhead";
  //private static String username = "root";
  //private static String password = "112233";
  private String username = "propellerhead";
  private String password = "123456";

  public static DataService getInstance() {
    if (instance == null) {
      instance = new DataService();
    }

    return instance;
  }
  private DataService(){
    try {
      // The newInstance() call is a work around for some
      // broken Java implementations

      //Class.forName("com.mysql.jdbc.Driver");

      cpds = new ComboPooledDataSource();
      cpds.setDriverClass("org.mariadb.jdbc.Driver"); //loads the jdbc driver
      cpds.setJdbcUrl("jdbc:mariadb://"+host+":"+port+"/"+dbName+"?useSSL=false&autoReconnect=true&allowPublicKeyRetrieval=true");
      cpds.setUser(username);
      cpds.setPassword(password);

      // the settings below are optional -- c3p0 can work with defaults
      cpds.setInitialPoolSize(1);
      cpds.setMinPoolSize(1);
      cpds.setAcquireIncrement(1);
      cpds.setMaxPoolSize(5);
      cpds.setAutomaticTestTable("connection_test");
      cpds.setIdleConnectionTestPeriod(100);
      cpds.setAcquireRetryAttempts(10);
      cpds.setAcquireRetryDelay(1000);
      cpds.setMaxIdleTime(1000);

      cpds.setAutoCommitOnClose(true);


    } catch (Exception ex) {
      // handle the error
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
  }


  private Connection getConnection(){

    Connection conn = null;
    try {
      conn = cpds.getConnection();
    } catch (SQLException ex) {
      LOGGER.log(Level.SEVERE, "DB Connection failed, SQLException: " + ex.getMessage() + "SQLState: " + ex.getSQLState() + "VendorError: " + ex.getErrorCode(), ex);
    }

    return conn;
  }

  public List<Customer> getCustomers(GetCustomersCriteria criteria){
    int count = 0;

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = null;

    List<Customer> customerList = new LinkedList<>();

    sql = "SELECT ";
    sql += " `id`";
    sql += ", `name`";
    sql += ", `status`";
    sql += ", `creationDateTime`";
    sql += ", `phone`";
    sql += ", `address1`";
    sql += ", `address2`";
    sql += ", `address3`";
    sql += ", `email`";
    sql += " FROM customer ";
    sql += " WHERE 1=1 ";

    if (criteria.getId() > 0) {
      sql += " AND id=? ";
    }

    if (criteria.getName() != null) {
      sql += " AND name like ? ";
    }

    if (criteria.getStatus() != null && criteria.getStatus().getValue() >= 0) {
      sql += " AND status = ? ";
    }

    if (criteria.getCreationDateTimeFrom() > 0) {
      sql += " AND creationDateTime >= ? ";
    }

    if (criteria.getCreationDateTimeTo() > 0) {
      sql += " AND creationDateTime <= ? ";
    }

    if (criteria.getPhone() != null) {
      sql += " AND phone like ? ";
    }

    if (criteria.getAddress() != null) {
      sql += " AND (address1 LIKE ? OR address2 LIKE ? OR address3 LIKE ?) ";
    }

    if (criteria.getEmail() != null) {
      sql += " AND email like ? ";
    }

    if (criteria.getSortBy() != null) {
      sql += " ORDER BY ";
      switch (criteria.getSortBy()) {
        case STATUS:
          sql += "status ";
          break;
        case CREATION_DATE_TIME:
          sql += "creationDateTime ";
          break;
      }

      if (criteria.isDecsending()) {
        sql += "DESC ";
      }
    }

    try{
      conn = this.getConnection();
      stmt = conn.prepareStatement(sql);

      int paramIndex = 1;

      if (criteria.getId() > 0) {
        stmt.setLong(paramIndex++, criteria.getId());
      }

      if (criteria.getName() != null) {
        stmt.setString(paramIndex++, "%"+criteria.getName()+"%");
      }

      if (criteria.getStatus() != null && criteria.getStatus().getValue() >= 0) {
        stmt.setInt(paramIndex++, criteria.getStatus().getValue());
      }

      if (criteria.getCreationDateTimeFrom() > 0) {
        stmt.setLong(paramIndex++, criteria.getCreationDateTimeFrom());
      }

      if (criteria.getCreationDateTimeTo() > 0) {
        stmt.setLong(paramIndex++, criteria.getCreationDateTimeTo());
      }

      if (criteria.getPhone() != null) {
        stmt.setString(paramIndex++, "%"+criteria.getPhone()+"%");
      }

      if (criteria.getAddress() != null) {
        stmt.setString(paramIndex++, "%"+criteria.getAddress()+"%");
        stmt.setString(paramIndex++, "%"+criteria.getAddress()+"%");
        stmt.setString(paramIndex++, "%"+criteria.getAddress()+"%");
      }

      if (criteria.getEmail() != null) {
        stmt.setString(paramIndex++, "%"+criteria.getEmail()+"%");
      }

      rs = stmt.executeQuery();

      while (rs.next()){
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setStatus(CustomerStatus.parse(rs.getInt("status")));
        customer.setCreationDateTime(rs.getLong("creationDateTime"));
        customer.setPhone(rs.getString("phone"));
        customer.setAddress1(rs.getString("address1"));
        customer.setAddress2(rs.getString("address2"));
        customer.setAddress3(rs.getString("address3"));
        customer.setEmail(rs.getString("email"));

        customerList.add(customer);
      }

    }
    catch(SQLException ex){
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    finally{
      try{rs.close();rs=null;}catch(Exception ignored){}
      try{stmt.close();stmt=null;}catch(Exception ignored){}
      try{conn.close();conn=null;}catch(Exception ignored){}
    }

    return customerList;
  }

  public Customer getCustomerDetails(GetCustomerDetailsCriteria criteria){
    int count = 0;

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = null;

    List<Customer> customerList = new LinkedList<>();

    sql = "SELECT ";
    sql += " `id`";
    sql += ", `name`";
    sql += ", `status`";
    sql += ", `creationDateTime`";
    sql += ", `phone`";
    sql += ", `address1`";
    sql += ", `address2`";
    sql += ", `address3`";
    sql += ", `email`";
    sql += " FROM customer ";
    sql += " WHERE id = ? ";

    LOGGER.finest(sql);

    Customer customer = null;

    try{
      conn = this.getConnection();
      stmt = conn.prepareStatement(sql);

      stmt.setLong(1, criteria.getId());

      rs = stmt.executeQuery();

      if (rs.next()){
        customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setStatus(CustomerStatus.parse(rs.getInt("status")));
        customer.setCreationDateTime(rs.getLong("creationDateTime"));
        customer.setPhone(rs.getString("phone"));
        customer.setAddress1(rs.getString("address1"));
        customer.setAddress2(rs.getString("address2"));
        customer.setAddress3(rs.getString("address3"));
        customer.setEmail(rs.getString("email"));
      }

    }
    catch(SQLException ex){
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    finally{
      try{rs.close();rs=null;}catch(Exception ignored){}
      try{stmt.close();stmt=null;}catch(Exception ignored){}
      try{conn.close();conn=null;}catch(Exception ignored){}
    }

    return customer;
  }

  public List<Note> getNotes(GetNotesCriteria criteria){
    int count = 0;

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    String sql = null;

    List<Note> noteList = new LinkedList<>();

    sql = "SELECT ";
    sql += " `id`";
    sql += ", `customerId`";
    sql += ", `content`";
    sql += ", `creationDateTime`";
    sql += " FROM note ";
    sql += " WHERE customerId = ? ";


    LOGGER.finest(sql);

    try{
      conn = this.getConnection();
      stmt = conn.prepareStatement(sql);

      stmt.setLong(1, criteria.getCustomerId());

      rs = stmt.executeQuery();

      while (rs.next()){
        Note note = new Note();
        note.setId(rs.getLong("id"));
        note.setCustomerId(rs.getLong("customerId"));
        note.setContent(rs.getString("content"));
        note.setCreationDateTime(rs.getLong("creationDateTime"));

        noteList.add(note);
      }

    }
    catch(SQLException ex){
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    finally{
      try{rs.close();rs=null;}catch(Exception ignored){}
      try{stmt.close();stmt=null;}catch(Exception ignored){}
      try{conn.close();conn=null;}catch(Exception ignored){}
    }

    return noteList;
  }

  public int insertNote(AddNoteParam param){

    int count = 0;

    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = null;

    sql = "INSERT INTO `note` ";
    sql += "( `customerId`";
    sql += ", `content`";
    sql += ", `creationDateTime`";
    sql += ")";
    sql += " value ";
    sql += "( ";
    sql += "?,?,?";
    sql += ") ";

    LOGGER.finest(sql);

    try{
      conn = this.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, param.getCustomerId());
      pstmt.setString(2, param.getContent());
      pstmt.setLong(3, System.currentTimeMillis());

      count = pstmt.executeUpdate();

    }
    catch(SQLException ex){
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    finally{
      try{pstmt.close();pstmt=null;}catch(Exception ignored){}
      try{conn.close();conn=null;}catch(Exception ignored){}
    }

    return count;
  }

  public int deleteNote(DeleteNoteParam param){

    int count = 0;

    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = null;

    sql = "DELETE FROM note ";
    sql += " WHERE id = ? ";

    LOGGER.finest(sql);

    try{
      conn = this.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, param.getId());

      count = pstmt.executeUpdate();

    }
    catch(SQLException ex){
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    finally{
      try{pstmt.close();pstmt=null;}catch(Exception ignored){}
      try{conn.close();conn=null;}catch(Exception ignored){}
    }

    return count;
  }

  public int updateNote(EditNoteParam param){

    int count = 0;

    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = null;

    sql = "Update note ";
    sql += " SET content = ? ";
    sql += " WHERE id = ? ";

    LOGGER.finest(sql);

    try{
      conn = this.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, param.getContent());
      pstmt.setLong(2, param.getId());

      count = pstmt.executeUpdate();

    }
    catch(SQLException ex){
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    finally{
      try{pstmt.close();pstmt=null;}catch(Exception ignored){}
      try{conn.close();conn=null;}catch(Exception ignored){}
    }

    return count;
  }

  public int updateCustomerStatus(ChangeCustomerStatusParam param){

    int count = 0;

    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = null;

    sql = "UPDATE customer SET status = ? ";
    sql += " WHERE id = ? ";

    LOGGER.finest(sql);

    try{
      conn = this.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, param.getStatus().getValue());
      pstmt.setLong(2, param.getId());

      count = pstmt.executeUpdate();

    }
    catch(SQLException ex){
      LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
    }
    finally{
      try{pstmt.close();pstmt=null;}catch(Exception ignored){}
      try{conn.close();conn=null;}catch(Exception ignored){}
    }

    return count;
  }
}
