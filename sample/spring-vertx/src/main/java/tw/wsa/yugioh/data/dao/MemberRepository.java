package tw.wsa.yugioh.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tw.wsa.yugioh.data.po.MemberPo;

public interface MemberRepository extends JpaRepository<MemberPo, Long>, JpaSpecificationExecutor<MemberPo> {
}
