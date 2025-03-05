package es.cesguiro.daw1.bookstore.persistence.dao.db;

import es.cesguiro.daw1.bookstore.util.property.PropertyUtil;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private final String table;
    @Getter
    private String query;
    @Getter
    private List<Object> params = new ArrayList<>();
    private Logger logger = LogManager.getLogger(QueryBuilder.class);

    private QueryBuilder(String table) {
        this.table = table;
        this.query = "SELECT * FROM " + table;
    }

    public static QueryBuilder table(String table) {
        return new QueryBuilder(table);
    }

    public QueryBuilder select(String fields) {
        query = query.replace("*", fields);
        return this;
    }

    public QueryBuilder where(String field, String operator, Object value) {
        if (value != null) {
            query += " WHERE " + field + " " + operator + " ?";
            params.add(value);
        }
        return this;
    }

    public QueryBuilder page(int page, int size) {
        query += " LIMIT " + size + " OFFSET " + (page - 1) * size;
        return this;
    }

    public QueryBuilder join(String joinTable, String joinColumn, String inverseJoinColumn) {
        query += " JOIN " + joinTable + " ON " + joinColumn + " = " + inverseJoinColumn;
        return this;
    }

    public ResultSet find(Object id) {
        this.query += " WHERE id = ?";
        params.add(id);
        ResultSet resultSet = this.get();
        try {
            if(resultSet.next()) {
                return resultSet;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error finding by id", e);
        }
    }

    public ResultSet get() {
        if (PropertyUtil.getPropertyProvider().getBooleanProperty("app.querybuilder.showSql", false)) {
            logger.info(this.query);
        }
        return RawSql.select(this.query, this.params);
    }

    public ResultSet getOne() {
        ResultSet resultSet = this.get();
        try {
            if(resultSet.next()) {
                return resultSet;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting one. Query: " + this.query, e);
        }
    }

    public Long count() {
        this.query = "SELECT COUNT(*) FROM " + this.table;
        ResultSet resultSet = this.get();
        try {
            if(resultSet.next()) {
                return resultSet.getLong(1);
            }
            return 0L;
        } catch (SQLException e) {
            throw new RuntimeException("Error counting rows. Query: " + this.query, e);
        }
    }

    public void commit() {
        RawSql.commit();
    }

    public void rollback() {
        RawSql.rollback();
    }

}
