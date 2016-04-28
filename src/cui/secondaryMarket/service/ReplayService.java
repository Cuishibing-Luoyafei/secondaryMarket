package cui.secondaryMarket.service;

import java.util.List;

import com.secondaryMarket.bean.Reply;

public interface ReplayService {
	public Reply getReplyInId(Integer replyId);
	public List<Reply> getReplys(Integer start,Integer size);
	public boolean insertReply(Reply reply);
	public boolean deleteReply(Reply reply);
}
