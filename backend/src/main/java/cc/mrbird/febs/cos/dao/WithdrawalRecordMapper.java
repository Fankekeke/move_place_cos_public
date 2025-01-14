package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.WithdrawalRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface WithdrawalRecordMapper extends BaseMapper<WithdrawalRecord> {

    /**
     * 分页获取提现记录
     *
     * @param page        分页对象
     * @param withdrawalRecord 提现记录
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryWithdrawalPage(Page<WithdrawalRecord> page, @Param("withdrawalRecord") WithdrawalRecord withdrawalRecord);
}
