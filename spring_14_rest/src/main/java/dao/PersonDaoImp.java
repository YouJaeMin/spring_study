package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import dto.PersonDTO;

public class PersonDaoImp implements PersonDao {

	private SqlSessionTemplate sqlSession;

	public PersonDaoImp() {
	}

	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<PersonDTO> list() {
		return sqlSession.selectList("person.all");
	}

	@Override
	public PersonDTO list(int num) {
		return sqlSession.selectOne("person.one", num);
	}

	@Override
	public PersonDTO list(PersonDTO dto) {
		return sqlSession.selectOne("person.search", dto);
	}

	@Override
	public void register(PersonDTO dto) {
		sqlSession.insert("person.ins",dto);
	}

	@Override
	public void update(PersonDTO dto) {
		sqlSession.update("person.upt", dto);
	}

	@Override
	public void delete(int num) {
		sqlSession.delete("person.del", num);

	}

}
