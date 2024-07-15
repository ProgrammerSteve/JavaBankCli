package account;


import utils.DatabaseConfig;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountRepository {
    DataSource dataSource= DatabaseConfig.createDataSource();
    private String sqlInsertAccount = "INSERT INTO accounts (balance, user_id) VALUES (?, ?)";
    private String sqlViewBalance = "SELECT balance FROM accounts WHERE user_id=?";
    private String sqlUpdateBalance = "UPDATE accounts SET balance = ? WHERE user_id = ?";



    public boolean createAccount(Account account){
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement insertPs=connection.prepareStatement(sqlInsertAccount);
            insertPs.setBigDecimal(1,account.getBalance());
            insertPs.setInt(2,account.getUserId());
            int insertCount=insertPs.executeUpdate();
            return insertCount>0;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateBalance(int userId, BigDecimal newBalance) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlUpdateBalance)) {
            ps.setBigDecimal(1, newBalance);
            ps.setInt(2, userId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Optional<BigDecimal> viewBalance(int userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sqlViewBalance)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BigDecimal balance = rs.getBigDecimal("balance");
                return Optional.of(balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
