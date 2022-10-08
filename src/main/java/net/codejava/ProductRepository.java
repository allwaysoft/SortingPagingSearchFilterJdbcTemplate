package net.codejava;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository

public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // sort by single column name
    public List<Product> findAll(Sort sort) {

        Order order = sort.toList().get(0);

        return jdbcTemplate.query("SELECT * FROM product ORDER BY " + order.getProperty() + " " + order.getDirection().name(),
                (rs, rowNum) -> mapUserResult(rs));
    }

    private Product mapUserResult(final ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setBrand(rs.getString("brand"));
        product.setMadein(rs.getString("madein"));
        product.setPrice(rs.getLong("price"));
        return product;
    }

    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM product", Integer.class);
    }

    // defaults sorts by Id if order not provided
    public Page<Product> findAll(Pageable page) {

        Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("ID");

        List<Product> products = jdbcTemplate.query("SELECT * FROM product ORDER BY " + order.getProperty() + " "
                + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset(),
                (rs, rowNum) -> mapUserResult(rs));

        return new PageImpl<Product>(products, page, count());
    }

    public Page<Product> findAllByContaining(String keyword, Pageable page) {

        Order order = !page.getSort().isEmpty() ? page.getSort().toList().get(0) : Order.by("ID");

        List<Product> products = jdbcTemplate.query("SELECT * FROM product WHERE CONCAT(id, ' ', name, ' ' , brand, ' ' , madein, ' ' , price) LIKE CONCAT('%',?,'%') ORDER BY " + order.getProperty() + " "
                + order.getDirection().name() + " LIMIT " + page.getPageSize() + " OFFSET " + page.getOffset(),
                (rs, rowNum) -> mapUserResult(rs), keyword
        );

        return new PageImpl<Product>(products, page, count());
    }

    public void save(Product product) {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = "insert into product (name, brand,madein,price) values (:name, :brand,:madein,:price)";
        SqlParameterSource paramSource = new MapSqlParameterSource().addValue("name", product.getName()).addValue("brand", product.getBrand()
        ).addValue("madein", product.getMadein()).addValue("price", product.getPrice());
        namedParameterJdbcTemplate.update(sql, paramSource, generatedKeyHolder);
        long id = generatedKeyHolder.getKey().longValue();
        System.out.println(id);
        product.setId(id);
    }

    public Product findById(long id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "SELECT * FROM product WHERE id = :id";
        Map parameters = new HashMap();
        parameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, parameters, BeanPropertyRowMapper.newInstance(Product.class));
    }

    public void update(Product product) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "UPDATE product SET name=:name, brand=:brand, madein=:madein, price=:price WHERE id=:id";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(product);
        int i = namedParameterJdbcTemplate.update(sql, param);
        System.out.println(i);
    }

    public void deleteById(long id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = "DELETE FROM product WHERE id = :id";

        SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
        int i = namedParameterJdbcTemplate.update(sql, paramSource);
        System.out.println(i);
    }
}
