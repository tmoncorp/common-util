package com.tmoncorp.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(value = JdbcType.VARCHAR, includeNullJdbcType = true)
@MappedTypes(Json.class)
public class JsonTypeHandler extends BaseTypeHandler<Json> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Json parameter, JdbcType jdbcType) throws SQLException {
        String jsonString;
        try {
            jsonString = new ObjectMapper().writeValueAsString(parameter);
        } catch (JsonProcessingException e) {
            jsonString = "JsonProcessingException:" + e.getMessage();
        }
        ps.setString(i, jsonString);
    }

    @Override
    public Json getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String string = rs.getString(columnName);
        return toJson(string);
    }

    @Override
    public Json getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String string = rs.getString(columnIndex);
        return toJson(string);
    }

    @Override
    public Json getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String string = cs.getString(columnIndex);
        return toJson(string);
    }

    private Json toJson(String string) {
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        try {
            return objectMapper.readValue(string, Json.class);
        } catch (IOException e) {
            return null;
        }
    }
}
