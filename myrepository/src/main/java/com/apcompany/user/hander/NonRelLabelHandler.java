package com.apcompany.user.hander;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class NonRelLabelHandler  extends BaseTypeHandler<Integer> {

	@Override
	public Integer getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int resultcome=rs.getInt(columnName);
		return null;
	}

	@Override
	public Integer getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNullableResult(CallableStatement rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	// 由于BaseTypeHandler中已经把parameter为null的情况做了处理，所以这里我们就不用再判断parameter是否为空了，直接用就可以了
	@Override
	public void setNonNullParameter(PreparedStatement rs, int i, Integer parameter, JdbcType jdbcType) throws SQLException {
		
	}

}
