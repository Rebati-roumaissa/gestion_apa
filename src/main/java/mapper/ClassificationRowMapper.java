package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import guru.springframework.domain.Classification;
import guru.springframework.domain.Lot;

public class ClassificationRowMapper implements RowMapper<Classification> {

	@Override
	public Classification mapRow(ResultSet rs, int arg1) throws SQLException {
	Classification classification = new Classification();
	classification.setId(rs.getString("iddqs"));
	classification.setDescription(rs.getString("descriptqsdqsdion"));
	classification.setNom(rs.getString("nomkk"));
	classification.setLot(new Lot(rs.getString("lotuu")));
    return classification;
	}

}
