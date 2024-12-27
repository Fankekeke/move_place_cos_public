package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.WithdrawalRecord;
import cc.mrbird.febs.cos.dao.WithdrawalRecordMapper;
import cc.mrbird.febs.cos.service.IWithdrawalRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class WithdrawalRecordServiceImpl extends ServiceImpl<WithdrawalRecordMapper, WithdrawalRecord> implements IWithdrawalRecordService {

    /**
     * 分页获取提现记录
     *
     * @param page        分页对象
     * @param withdrawalRecord 提现记录
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryWithdrawalPage(Page<WithdrawalRecord> page, WithdrawalRecord withdrawalRecord) {
        return baseMapper.queryWithdrawalPage(page, withdrawalRecord);
    }
}
