package org.sample.seckill.model.entity;

import org.sample.model.BaseDO;

import java.time.LocalDateTime;

public class SeckillRecord extends BaseDO {

    private Integer seckillId;

    private String userMobile;

    private Seckill seckill;

    private LocalDateTime createTime;

    public SeckillRecord() {
    }

    public SeckillRecord(Integer seckillId, String userMobile) {
        this.seckillId = seckillId;
        this.userMobile = userMobile;
    }

    public SeckillRecord(Integer seckillId, String userMobile, LocalDateTime createTime) {
        this.seckillId = seckillId;
        this.userMobile = userMobile;
        this.createTime = createTime;
    }

    public Integer getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Integer seckillId) {
        this.seckillId = seckillId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SeckillRecord other = (SeckillRecord) that;
        return (this.userMobile == null ? other.userMobile == null : this.userMobile.equals(other.userMobile)) && //
                (this.seckillId == null ? other.seckillId == null : this.seckillId.equals(other.seckillId));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userMobile == null) ? 0 : userMobile.hashCode());
        result = prime * result + ((seckillId == null) ? 0 : seckillId.hashCode());
        return result;
    }
}