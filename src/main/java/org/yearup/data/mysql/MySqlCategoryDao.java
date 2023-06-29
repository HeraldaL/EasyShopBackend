package org.yearup.data.mysql;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MySqlCategoryDao implements CategoryDao {
    private final JdbcTemplate jdbcTemplate;

    public MySqlCategoryDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Category> getAllCategories() {
        String sql = "SELECT * FROM categories";
        try {
            return jdbcTemplate.query(sql, this::mapRow);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category getById(int categoryId) {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{categoryId},
                    (rs, rowNum) -> {
                        Category category = new Category();
                        category.setCategoryId(rs.getInt("category_id"));
                        category.setName(rs.getString("name"));
                        category.setDescription(rs.getString("description"));
                        return category;
                    });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public Category create(Category category) {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            return statement;
        }, keyHolder);
        Number generateId = keyHolder.getKey();
        if (generateId != null) {
            int categoryId = generateId.intValue();
            category.setCategoryId(categoryId);
        }
        return category;
        }


    @Override
    public void update(int categoryId, Category category) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        try {
            jdbcTemplate.update(sql, category.getName(), category.getDescription(), categoryId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void delete(int categoryId) {
        String sql = "DELETE FROM categories  WHERE category_id = ?";
        try {
            jdbcTemplate.update(sql, categoryId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet row, int rowNum) throws SQLException {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category() {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }
}
