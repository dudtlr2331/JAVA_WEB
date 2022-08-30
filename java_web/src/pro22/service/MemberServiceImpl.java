package pro22.service;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.dao.MemberDAO;

public class MemberServiceImpl implements MemberService {
	private MemberController memberDAO;

	public void setMemberDAO(MemberController memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMembers();
		return membersList;
	}

}