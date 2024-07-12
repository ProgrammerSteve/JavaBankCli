package user;

import utils.DatabaseConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    DataSource dataSource= DatabaseConfig.createDataSource();
    private String sqlFindByUsername="select * from USERS where name=?";
    private String sqlCreateUser="insert into USERS (username, password) values (?, ?)";

    public Optional<User> findByUsername(String username){
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement ps=connection.prepareStatement(sqlFindByUsername);
            ps.setString(1,username);
            ResultSet resultSet=ps.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }
            User foundUser=new User(
                    resultSet.getString("username"),
                    resultSet.getString("password")
            );
            return Optional.of(foundUser);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean createUser(String username,String password){
        try(Connection connection= dataSource.getConnection()){
            PreparedStatement insertPs=connection.prepareStatement(sqlCreateUser);
            insertPs.setString(1,username);
            insertPs.setString(2,password);
            int insertCount=insertPs.executeUpdate();
            return insertCount==1;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
