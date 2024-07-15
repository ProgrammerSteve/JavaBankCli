### Setting up connection to postgresql database
- Navigate to directory: `src/main/resources`
- create a file called: `db.properties`
- Should follow the format:
```
jdbc.url=jdbc:postgresql://XXXXXXX/XXXXXXX
jdbc.username=XXXXXXX
jdbc.password=XXXXXXX
```

### Setting up tables on database
- There is a one-to-one relationship between two tables: `users`, `accounts`
```
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS accounts (
    account_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE,
    balance DECIMAL(10, 2) CHECK (balance >= 0),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```

### If unauthenticated the CLI will look like:
```
Choose an action:
1:Login
2:Register
3:Exit
```

### If authenticated the CLI will look like:
```
Hello {NAME}, Choose an action:
1:ViewBalance
2:Withdraw
3:Deposit
4:Logout
5:Exit
```