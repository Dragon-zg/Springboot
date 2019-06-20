package com.lnnk.mybatis.p6spy;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

/**
 * P6spy SQL 日志信息格式化
 * @author lnnk
 * @date 2019/6/20 14:53
 **/
public class P6spyLogFormat implements MessageFormattingStrategy {

    /**
     * Formats a log message for the logging module
     *
     * @param connectionId the id of the connection
     * @param now          the current ime expressing in milliseconds
     * @param elapsed      the time in milliseconds that the operation took to complete
     * @param category     the category of the operation
     * @param prepared     the SQL statement with all bind variables replaced with actual values
     * @param sql          the sql statement executed
     * @param url          the database url where the sql statement executed
     * @return the formatted log message
     */
    @Override
    public String formatMessage(final int connectionId, final String now, final long elapsed, final String category, final String prepared, final String sql, final String url) {
        return StringUtils.isNotEmpty(sql) ? new StringBuilder()
                .append("Execute SQL：").append(sql.replaceAll("[\\s]+", StringPool.SPACE))
                .append(" (").append(elapsed).append(" ms)").toString() : null;
    }
}
