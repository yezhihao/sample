package org.sample.seckill.model.entity;

import org.sample.commons.lang.DateUtils;
import org.sample.model.BaseDO;

import java.time.LocalDateTime;

public class Seckill extends BaseDO {

    private String name;

    private Integer count;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createTime;

    public Seckill() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getEndTimeMillis() {
        if (endTime == null)
            return null;
        return DateUtils.getMillis(endTime);
    }

    public Long getStartTimeMillis() {
        if (startTime == null)
            return null;
        return DateUtils.getMillis(startTime);
    }
}