package org.sample.web.web;

import org.sample.commons.lang.StringUtils;
import org.sample.seckill.dto.Exposer;
import org.sample.model.APIResult;
import org.sample.model.Pagination;
import org.sample.exception.APIException;
import org.sample.seckill.enums.ResultCodes;
import org.sample.seckill.model.Seckill;
import org.sample.seckill.model.SeckillRecord;
import org.sample.web.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("seckill")
public class SeckillController {

    @Autowired
    private SeckillService service;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        Pagination<Seckill> result = service.search(1).getData();
        model.addAttribute("list", result.getList());
        return "list";
    }

    @RequestMapping(value = "api/list", method = RequestMethod.GET)
    @ResponseBody
    @Cacheable(value = "seckill", key = "#name")
    public List<Seckill> list(@RequestParam(value = "name", required = false) String name) {
        Seckill seckill = new Seckill();
        if (StringUtils.isNotBlank(name))
            seckill.setName(name);
        Pagination<Seckill> result = service.search(seckill).getData();
        return result.getList();
    }

    @RequestMapping(value = "{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable Integer seckillId, Model model) {
        if (seckillId == null)
            return "redirect:/seckill/list";
        Seckill seckill = service.detail(seckillId).getData();
        if (seckill == null)
            return "forward:/seckill/list";
        model.addAttribute(seckill);
        return "detail";
    }

    @RequestMapping(value = "{seckillId}/exposer", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<Exposer> exposer(@PathVariable Integer seckillId) {
        APIResult<Exposer> result = service.exposer(seckillId);
        return result;
    }

    @RequestMapping(value = "{seckillId}/{md5}/execution", method = RequestMethod.POST)
    @ResponseBody
    public APIResult<SeckillRecord> execute(@PathVariable Integer seckillId, @PathVariable String md5,
                                            @CookieValue(value = "userMobile", required = false) String userMobile) {
        if (userMobile == null)
            throw new APIException(ResultCodes.S400);
        APIResult<SeckillRecord> result = service.execute(seckillId, md5, userMobile);
        return result;
    }

    @RequestMapping(value = "time/now", method = RequestMethod.GET)
    @ResponseBody
    public long currentTime() {
        return System.currentTimeMillis();
    }

}