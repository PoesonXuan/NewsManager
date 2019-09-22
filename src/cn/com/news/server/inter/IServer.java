package cn.com.news.server.inter;

import cn.com.news.model.inter.IModel;
import java.util.List;
import java.util.Map;
/***
 * 逻辑处理接口
 * @author XuanZP
 *
 */
public abstract interface IServer {
	public abstract IModel save(IModel paramIModel);

	public abstract boolean delete(IModel paramIModel);

	public abstract IModel update(IModel paramIModel);

	public abstract IModel getById(long paramLong);

	public abstract List<IModel> get(IModel paramIModel);
	
	public abstract List<IModel> getByParams(IModel paramIModel,Map<String,Object> params);
}