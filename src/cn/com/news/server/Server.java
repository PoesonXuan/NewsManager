package cn.com.news.server;

import cn.com.news.dao.inter.IDao;
import cn.com.news.model.inter.IModel;
import cn.com.news.server.inter.IServer;
import java.util.List;
import java.util.Map;
/***
 * 逻辑处理类
 * @author XuanZP
 *
 */
public class Server implements IServer {
	private IDao dao;

	public IModel save(IModel model) {
		return this.dao.save(model);
	}

	public boolean delete(IModel model) {
		return this.dao.delete(model);
	}

	public IModel update(IModel model) {
		return this.dao.update(model);
	}

	public IModel getById(long id) {
		return this.dao.getById(id);
	}

	public IDao getDao() {
		return this.dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	public List<IModel> get(IModel model) {
		return this.dao.get(model);
	}

	@Override
	public List<IModel> getByParams(IModel paramIModel,
			Map<String, Object> params) {
		return this.dao.getByParams(paramIModel, params);
	}
}