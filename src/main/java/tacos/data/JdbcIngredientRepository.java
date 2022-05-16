/*

package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public abstract class JdbcIngredientRepository  implements IngredientRepository{
private JdbcTemplate jdbc;

@Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc){
    this.jdbc = jdbc;
}
 @Override
    public Iterable<Ingredient> findAll(){
    return jdbc.query("SELECT id, name, type FROM Ingredient",
            this::mapRowToIngredient);
 }
 private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
    return  new Ingredient(
            rs.getString("id"),
            rs.getString("name"),
            Ingredient.Type.valueOf(rs.getString("type")));
 }
 @Override
    public Ingredient save(Ingredient ingredient){
    jdbc.update("INSERT into Ingredient(id,name,type) VALUES (?,?,?)",
            ingredient.getId(),
            ingredient.getName(),
            ingredient.getType().toString());
    return ingredient;
 }
}

*/
