package tw.wsa.yugioh.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tw.wsa.yugioh.data.po.MessagePo;

public interface MessageRepository extends JpaRepository<MessagePo, Long>, JpaSpecificationExecutor<MessagePo> {
}
