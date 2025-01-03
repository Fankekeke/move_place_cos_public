package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.NotifyInfo;
import cc.mrbird.febs.cos.dao.NotifyInfoMapper;
import cc.mrbird.febs.cos.service.INotifyInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class NotifyInfoServiceImpl extends ServiceImpl<NotifyInfoMapper, NotifyInfo> implements INotifyInfoService {

    /**
     * 分页获取消息通知信息
     *
     * @param page       分页对象
     * @param notifyInfo 消息通知信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryNotifyPage(Page<NotifyInfo> page, NotifyInfo notifyInfo) {
        return baseMapper.queryNotifyPage(page, notifyInfo);
    }
}
