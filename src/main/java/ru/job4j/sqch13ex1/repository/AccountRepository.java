package ru.job4j.sqch13ex1.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.sqch13ex1.AccountRowMapper;
import ru.job4j.sqch13ex1.model.Account;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountRepository {
    private final JdbcTemplate jdbc;

    public AccountRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Account findAccountById(long id) {
        String sql = "select * from account where id = ?";
        return jdbc.queryForObject(sql, new AccountRowMapper(), id);
    }

    public void changeAmount(long id, BigDecimal amount) {
        String sql = "update account set amount = ? where id = ?";
        jdbc.update(sql, amount, id);
    }

    public List<Account> findAllAccounts() {
        String sql = "select * from account";
        return jdbc.query(sql, new AccountRowMapper());
    }
}
