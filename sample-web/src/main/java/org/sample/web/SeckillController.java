package org.sample.web;

import org.sample.api.SeckillService;
import org.sample.api.impl.SeckillServiceImpl;
import org.sample.dto.Exposer;
import org.sample.entity.PaginationResult;
import org.sample.enums.ResultCode;
import org.sample.exception.BusinessException;
import org.sample.model.Seckill;
import org.sample.model.SeckillRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("seckill")
public class SeckillController {

    private SeckillService service = SeckillServiceImpl.INSTANCE;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        PaginationResult<Seckill> result = service.search(1);
        model.addAttribute("list", result.getList());
        return "list";
    }

    @RequestMapping(value = "{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable Integer seckillId, Model model) {
        if (seckillId == null)
            return "redirect:/seckill/list";
        Seckill seckill = service.detail(seckillId);
        if (seckill == null)
            return "forward:/seckill/list";
        model.addAttribute(seckill);
        return "detail";
    }

    @RequestMapping(value = "{seckillId}/exposer", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Exposer exposer(@PathVariable Integer seckillId) {
        Exposer result = service.exposer(seckillId);
        return result;
    }

    @RequestMapping(value = "{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public SeckillRecord execute(@PathVariable Integer seckillId, //
            @PathVariable String md5, //
            @CookieValue(value = "userMobile", required = false) String userMobile) {
        if (userMobile == null)
            throw new BusinessException(ResultCode.S400);
        SeckillRecord result = service.execute(seckillId, md5, userMobile);
        return result;
    }

    @RequestMapping(value = "time/now ", method = RequestMethod.GET)
    @ResponseBody
    public long currentTime() {
        return System.currentTimeMillis();
    }

}