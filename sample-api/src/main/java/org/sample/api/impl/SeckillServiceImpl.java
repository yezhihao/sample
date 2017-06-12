package org.sample.api.impl;

import org.sample.api.SeckillService;
import org.sample.commons.io.JsonUtils;
import org.sample.commons.net.HttpClient;
import org.sample.dto.Exposer;
import org.sample.model.PaginationResult;
import org.sample.model.Seckill;
import org.sample.model.SeckillRecord;

import com.fasterxml.jackson.core.type.TypeReference;

public enum SeckillServiceImpl implements SeckillService {
    INSTANCE;

    private HttpClient client = HttpClient.getInstance("server.seckill");

    @Override
    public PaginationResult<Seckill> search(Integer index) {
        String response = client.get("seckill/list/" + index);
        return JsonUtils.toObj(response, new TypeReference<PaginationResult<Seckill>>() {
        });
    }

    @Override
    public Seckill detail(Integer seckillId) {
        String response = client.get("seckill/" + seckillId + "/detail");
        return JsonUtils.toObj(response, Seckill.class);
    }

    @Override
    public Exposer exposer(Integer seckillId) {
        String response = client.post("seckill/" + seckillId + "/exposer");
        return JsonUtils.toObj(response, Exposer.class);
    }

    @Override
    public SeckillRecord execute(Integer seckillId, String md5, String userMobile) {
        String response = client.post("seckill/" + seckillId + "/" + md5 + "/" + userMobile + "/execution");
        return JsonUtils.toObj(response, SeckillRecord.class);
    }

}