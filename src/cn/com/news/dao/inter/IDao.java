package cn.com.news.dao.inter;

import cn.com.news.model.inter.IModel;
import java.util.List;
import java.util.Map;
/***
 * 数据处理层接口
 * @author XuanZP
 *
 */
public abstract interface IDao {
	public abstract IModel save(IModel paramIModel);

	public abstract boolean delete(IModel paramIModel);

	public abstract IModel update(IModel paramIModel);

	public abstract IModel getById(long paramLong);

	public abstract List<IModel> get(IModel paramIModel);
	
	public abstract List<IModel> getByParams(IModel paramIModel,Map<String,Object> params);
}